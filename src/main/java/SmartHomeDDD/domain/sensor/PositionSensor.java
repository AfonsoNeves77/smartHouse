package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensor.sensorValues.PositionValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;

import java.util.UUID;

import static java.lang.Integer.parseInt;

/**
 * PositionSensor is a domain entity that represents a sensor that measures the position/value in a scale.
 */
public class PositionSensor implements Sensor, DomainEntity {

    private SensorNameVO sensorName;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorType;
    private final SensorIDVO sensorID;


    /**
     * Constructor for PositionSensor, it validates the receiving VOs and generates a new SensorIDVO utilizing the UUID.
     * @param sensorName The name of the sensor.
     * @param deviceID The ID of the device to which the sensor is attached.
     * @param sensorType The type of the sensor.
     */
    public PositionSensor (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorType){
        if(!areParamsValid(sensorName, deviceID, sensorType)){
            throw new IllegalArgumentException("Invalid Parameters");
        } else {
            this.sensorName = sensorName;
            this.deviceID = deviceID;
            this.sensorType = sensorType;
            this.sensorID = new SensorIDVO(UUID.randomUUID());
        }
    }

    /**
     * Obtains the reading from the sensor. It receives
     * a SimHardware external service (validating it against null).
     * @param simHardware The external service that provides the sensor reading.
     * @return A PositionValue object as ValueObject<Integer>.
     * @throws InstantiationException If the external service is null.
     */
    public ValueObject<Integer> getReading(SimHardware simHardware) throws InstantiationException {
        if (simHardware == null) throw new IllegalArgumentException("Invalid external service");
        int simValue = parseInt(simHardware.getValue());
        return new PositionValue(simValue);
    }

    /**
     * Getter method for the sensor ID.
     * @return  The sensor ID.
     */
    @Override
    public DomainID getId(){
        return this.sensorID;
    }

    /**
     * Getter method for the sensor name.
     * @return  The sensor name.
     */
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Getter method for the device ID.
     * @return  The device ID.
     */
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Getter method for the sensor type.
     * @return  The sensor type.
     */
    public SensorTypeIDVO getSensorType(){
        return this.sensorType;
    }


    /**
     * Method to validate the parameters of the constructor.
     * @param sensorName The name of the sensor.
     * @param deviceID The ID of the device to which the sensor is attached.
     * @param sensorType The type of the sensor.
     * @return True if the parameters are valid, false otherwise.
     */
    private boolean areParamsValid (SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorType){
        if(sensorName == null || deviceID == null || sensorType == null) {
            return false;
        } else {
            return true;
        }
    }

}