package smarthome.domain.sensor;

import smarthome.domain.sensor.externalservices.SensorExternalServices;
import smarthome.domain.DomainID;
import smarthome.domain.sensor.values.SolarIrradianceValue;
import smarthome.domain.vo.ValueObject;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;

import java.util.UUID;

public class SolarIrradianceSensor implements Sensor {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for SolarIrradianceSensor Class.
     * Starts by validating input parameter and proceeds with the creation of a unique sensor ID, using the Java's
     * built-in class named java.util.UUID.
     * @param sensorName Sensor name Value Object
     * @param deviceID Device ID Value Object where the sensor is going to be connected to
     * @param sensorTypeID ID Value Object of the sensor type chosen
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
     * Constructor for SolarIrradianceSensor Class.
     * Context: This constructor is used when we want to create a sensor object from a sensorDataModel.
     * It receives a SensorIDVO, SensorNameVO, DeviceIDVO and SensorTypeIDVO retrieved from the database.
     *
     * @param sensorID     the ID of the sensor
     * @param sensorName   the name of the sensor
     * @param deviceID     the ID of the device
     * @param sensorTypeID the type of the sensor
     */
    public SolarIrradianceSensor(SensorIDVO sensorID, SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        this.sensorID = sensorID;
        this.sensorName = sensorName;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;
    }

    /**
     * Requests the sensor to retrieve its reading
     * @return ValueObject<Integer> containing the reading of the sensor
     * @throws IllegalArgumentException If reading hardware is null or reading is invalid (null or wrong number format)
     */
    public ValueObject<Integer> getReading(SensorExternalServices simHardware) {
        if(simHardware == null)
            throw new IllegalArgumentException("Invalid hardware");
        String reading = simHardware.getValue();

        return new SolarIrradianceValue(reading);
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
