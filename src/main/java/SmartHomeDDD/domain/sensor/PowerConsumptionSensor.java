package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.domain.sensor.sensorValues.PowerConsumptionValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.UUID;

import static java.lang.Integer.parseInt;

public class PowerConsumptionSensor implements Sensor, DomainEntity {

    private SensorNameVO sensorName;
    private final SensorIDVO sensorID;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for PowerConsumptionSensor. It validates the receiving VOs and creates a new SensorIDVO utilizing
     * the UUID library. Received VOs and SensorID are encapsulated within the object.
     * @param sensorName Sensor Name
     * @param deviceID Device ID
     * @param sensorTypeID SensorType ID
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public PowerConsumptionSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }


    /**
     * Simple getter method, implemented from EntityDomain interface
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public SensorIDVO getId() {
        return this.sensorID;
    }


    /**
     * Simple getter method
     * @return The encapsulated VO for Name;
     */
    public SensorNameVO getSensorName(){
        return sensorName;
    }


    /**
     * Simple getter method
     * @return The encapsulated VO for SensorTypeID;
     */
    public SensorTypeIDVO getSensorTypeID(){
        return sensorTypeID;
    }


    /**
     * Simple getter method
     * @return The encapsulated VO for DeviceID;
     */
    public DeviceIDVO getDeviceID(){
        return deviceID;
    }


    /**
     * Obtains the power consumption. It receives a SimHardware external service (validating it against null). Returns a
     * PowerConsumptionValue object as ValueObject<Integer>
     * @param simHardware SimHardware external service
     * @return PowerConsumptionValue object
     * @throws IllegalArgumentException if the external service is null
     */
    public ValueObject<Integer> getPowerConsumption(SimHardware simHardware) {
        if (areParamsNull(simHardware)){
            throw new IllegalArgumentException("Invalid external service");
        }
        int powerConsumption = parseInt(simHardware.getValue());
        return new PowerConsumptionValue(powerConsumption);
    }


    /**
     * Validates any number of object params against null
     * @param params Any number of objects
     * @return True if any of the params are null, False otherwise
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
