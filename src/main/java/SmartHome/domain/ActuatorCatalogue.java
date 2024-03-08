package SmartHome.domain;

import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.SimHardwareAct;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActuatorCatalogue {

    private List<String> listOfInstantiableActuators;

    /**
     * Approach #1
     * To instantiate the actuator controller we can use the Apache library in order to create a configuration object.
     * This aproach does not dynamically import the permmitted types of actuator.
     * @param configuration
     * @throws InstantiationException
     */

    public ActuatorCatalogue(Configuration configuration) throws InstantiationException {
        if(!areConstructorParametersValid(configuration)){
            throw new InstantiationException("Invalid parameters");
        }
        String[] arrayInstantiableActuators = configuration.getStringArray("actuator");
        this.listOfInstantiableActuators = List.of(arrayInstantiableActuators);
    }

    /**
     * Approach #2
     * Another way of instantiating an actuator catalogue is by indicating the filepath to a configuration file (config.properties).
     * What this does its to dynamically import the permmitted type
     * @param filePath config.properties
     * @throws InstantiationException
     */

    public ActuatorCatalogue(String filePath) throws InstantiationException {
        if(!areConstructorParametersValid(filePath)){
            throw new InstantiationException("Invalid parameters");
        }
        try {
            Configurations configs = new Configurations();
            Configuration configuration = configs.properties(new File(filePath));

            String[] arrayInstantiableActuators = configuration.getStringArray("actuator");
            this.listOfInstantiableActuators = List.of(arrayInstantiableActuators);
        }catch (ConfigurationException e){
            throw new InstantiationException("Error reading file");
        }
    }

    private boolean areConstructorParametersValid(Configuration configuration){
        return configuration != null;
    }

    private boolean areConstructorParametersValid(String filePath){
        return filePath != null && !filePath.isEmpty() && !filePath.isBlank();
    }

    public List<String> getListOfInstantiableActuators() {
        return new ArrayList<>(this.listOfInstantiableActuators);
    }

    /**
     * This method starts by accessing the encapsulated list of instantiable actuators and checking if the input type is present.
     * If it is present the catalogue will attempt to access that type`s specific class and uses its constructor in order to
     * create an implementation of that actuator.
     * @param actuatorName actuatorName
     * @param actuatorType actuatorType
     * @param simHardwareAct external simulated connection
     * @return the created actuator object
     */

    public Actuator createActuator(String actuatorName, String actuatorType, SimHardwareAct simHardwareAct) {
        Optional<String> optionalActuator = this.listOfInstantiableActuators.stream().filter(s -> s.equalsIgnoreCase(actuatorType)).findFirst(); // verifies if the actuator is permitted
        if(optionalActuator.isPresent()){ // if actuator is permitted
            try{
                Class<?> classObj = Class.forName(actuatorType); // identifies the constructing class. for example, if blindroller is present in config.properties, it then checks if there is a class on the system called blindrolleractuator
                Constructor<?> constructor = classObj.getConstructor(String.class, SimHardwareAct.class); // attempts to call the constructor and validates if the input parameters are enough to instantiate an object. It does not check if the contents are valid, just the types
                Actuator actuator = (Actuator) constructor.newInstance(actuatorName, simHardwareAct); // Calls the constructor, and indirectly, validates the entry parameters (for example, they may be null or empty, which will then cause the constructor to fail)
                return actuator;
            }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                return null;
            }
        } else {
            return null;
        }
    }
}
