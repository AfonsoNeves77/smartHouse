package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensor.sensorValues.SolarIrradianceValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.UUID;

public class SolarIrradianceSensor implements Sensor, DomainEntity {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for SolarIrradianceSensor Class.
     * Starts by validating input parameter and proceeds with the creation of a unique sensor ID, using the Java's
     * built-in class named java.util.UUID.
     * @param sensorName Sensor name
     * @param deviceID Device ID where the sensor is going to be connected to
     * @param sensorTypeID ID of the sensor type chosen
     */
    public SolarIrradianceSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID){
        if(!validParameters(sensorName, deviceID, sensorTypeID))
            throw new IllegalArgumentException("Sensor parameters cannot be null");
        this.sensorID = new SensorIDVO(UUID.randomUUID());
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;
    }

    /**
     * Requests the sensor to retrieve its reading
     * @return Value<Integer> containing the reading of the sensor
     * @throws IllegalArgumentException If reading hardware is null or reading is invalid (null or wrong number format)
     */
    public ValueObject<Integer> getReading(SimHardware simHardware) {
        if(simHardware == null)
            throw new IllegalArgumentException("Invalid hardware");
        String reading = simHardware.getValue();
        SolarIrradianceValue readingValue = new SolarIrradianceValue(reading);
        return readingValue;
    }

    /**
     * Mandatory getter method to retrieve the SensorID, implemented from EntityDomain interface
     * @return The encapsulated VO for SensorID
     */
    @Override
    public DomainID getId() {
        return this.sensorID;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for Name
     */
    @Override
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for SensorTypeID
     */
    @Override
    public SensorTypeIDVO getSensorTypeID(){
        return this.sensorTypeID;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for DeviceID
     */
    @Override
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Verifies whether constructor parameters are valid (not null) or not
     * @param parameters Parameters to validate(sensorName, sensorTypeID, deviceID)
     * @return True if all parameters are not null, false if any of them is null
     */
    private boolean validParameters(Object... parameters){
        for(Object param : parameters){
            if(param == null){
                return false;
            }
        }
        return true;
    }
}
