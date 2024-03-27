package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SensorFactory {
    private Configuration configuration;

    /**
     * Constructor for Sensor Factory. It takes a path and uses the external library "configuration" to create a queryable
     * list of system-permitted sensors. It also serves as a source path of each Sensor Class.
     * @param path Path to the file that contains the paths to each Sensor Class
     */
    public SensorFactory(String path) {
        try {
            Configurations configs = new Configurations();
            this.configuration = configs.properties(new File(path));
        } catch (NullPointerException | ConfigurationException e){
            throw new IllegalArgumentException("Error reading file");
        }
    }

    /**
     * Main method to create a sensor object. Validates parameters are not null. Accesses the encapsulated configuration
     * object. If any entries match the received SensorTypeID as String, it returns the path to the specific sensor's
     * Class constructor. It then proceeds to dynamically create the specific Sensor, by utilizing the path to call its
     * constructor. If no issues arise, the method returns the newly created Sensor Object.
     * @param sensorName SensorNameVO
     * @param deviceID DeviceIDVO
     * @param sensorTypeID SensorTypeIDVO
     * @return Sensor object
     */
    public Sensor createSensor (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID){
        if (!areParamsNull(sensorName,deviceID,sensorTypeID) && isTypePermitted(sensorTypeID.getID())){
            try{
                String sensorTypePath = this.configuration.getString(sensorTypeID.getID());
                Class<?> classObj = Class.forName(sensorTypePath);
                Constructor<?> constructor = classObj.getConstructor(SensorNameVO.class, DeviceIDVO.class, SensorTypeIDVO.class);
                return (Sensor) constructor.newInstance(sensorName, deviceID, sensorTypeID);
            } catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                return null;
            }
        }
        return null;
    }

    /**
     * Verifies parameters are not null;
     * @param params Object to validate
     * @return True or false
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * Queries the configuration object by calling the method containsKey, which matches the input string with a key;
     * @param type Type of the sensor.
     * @return True or false
     */
    private boolean isTypePermitted(String type){
        return this.configuration.containsKey(type);
    }
}
