package SmartHomeDDD.domain.actuator;

import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

public class ActuatorFactory {

    private final String filePath = "actuator.properties";
    private Configuration configuration;

    /**
     * Constructor for FactoryActuator Class.
     * File path is encapsulated in the Class. If file path changes, this is the only place that requires an update.
     * @throws ConfigurationException If file path is invalid
     */
    public ActuatorFactory() throws ConfigurationException {
        initializeConfiguration(filePath);
    }

    /**
     * Creates an Actuator:
     * 1: Checks input parameters;
     * 2: Verifies whether the actuator type chosen exists in the file and has a correct path to its Class;
     * 3: Instantiates the actuator.
     * @param actuatorName Actuator name
     * @param actuatorTypeID Actuator type ID
     * @param deviceID Device ID to link the actuator to
     * @return The Actuator object in case of correct instantiation, null if operation does not succeed.
     */
    public Actuator createActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID) {
        if (!validParameters(actuatorName, actuatorTypeID, deviceID))
            throw new IllegalArgumentException("Invalid actuator parameters");
        Optional<String> actuatorType = getPath(actuatorTypeID);
        if(actuatorType.isPresent()) {
            try {
                Class<?> classObject = Class.forName(actuatorType.get());
                Constructor<?> constructor = classObject.getConstructor(ActuatorNameVO.class, ActuatorTypeIDVO.class, DeviceIDVO.class);
                return (Actuator) constructor.newInstance(actuatorName, actuatorTypeID, deviceID);

            } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException |
                     InstantiationException | IllegalAccessException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    /**
     * Validates all the parameters to instantiate an actuator.
     * @param parameters ActuatorName, ActuatorTypeID, DeviceID
     * @return True if all parameters are valid (not null), false otherwise.
     */
    private boolean validParameters(Object... parameters){
        for(Object param : parameters) {
            if (param == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Initializes the Configuration Object with the proper file path.
     * @param filePath File path to be considered, containing the required actuators' data
     * @throws ConfigurationException If file path is invalid.
     */
    private void initializeConfiguration(String filePath) throws ConfigurationException {
        Configurations configs = new Configurations();
        Configuration configuration = configs.properties(new File(filePath));
        this.configuration = configuration;
    }

    /**
     * Extracts from the file the path to the Class of the pretended actuator type.
     * @param actuatorTypeID ActuatorTypeID to get the correct path from the file
     * @return Optional container, which might be empty in case actuator type does not exist in the file.
     */
    private Optional<String> getPath(ActuatorTypeIDVO actuatorTypeID){
        String strActuatorType = actuatorTypeID.getID();
        if(this.configuration.getString(strActuatorType) == null)
            return Optional.empty();
        return Optional.of(this.configuration.getString(strActuatorType));
    }

}
