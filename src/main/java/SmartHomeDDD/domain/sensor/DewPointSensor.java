package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.domain.sensor.sensorValues.DewPointValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.UUID;

public class DewPointSensor implements Sensor, DomainEntity {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for DewPointSensor, it validates the receiving VOs and creates a new SensorIDVO utilizing the UUID
     * library. Received VOs and SensorID are encapsulated within the object.
     * @param sensorName Sensor name
     * @param deviceID Device ID
     * @param sensorTypeID SensorType ID
     */
    public DewPointSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }
    /**
     * Obtains the dewPoint value. It receives a SimHardware external service (validating it)
     *
     * @param simHardware SimHardware external service
     * @return dewPointValue object
     * @throws InstantiationException
     */
    public ValueObject<Double> getReading(SimHardware simHardware) throws InstantiationException {
        if (simHardware == null) throw new IllegalArgumentException("Invalid external service");
        String simValue = simHardware.getValue();
        return new DewPointValue(simValue);
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for Name;
     */
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for SensorTypeID;
     */
    public SensorTypeIDVO getSensorTypeID(){
        return this.sensorTypeID;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for DeviceID;
     */
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Simple getter method, implemented from EntityDomain interface
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public DomainID getId() {
        return this.sensorID;
    }

    /**
     * Validates any number of object params against null
     * @param params Any object param
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
}