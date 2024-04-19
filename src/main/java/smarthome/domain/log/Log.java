package smarthome.domain.log;

import smarthome.domain.DomainEntity;
import smarthome.domain.DomainID;
import smarthome.domain.sensor.values.SensorValueObject;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;

import java.time.LocalDateTime;
import java.util.UUID;

public class Log implements DomainEntity {
    private final LogIDVO logID;
    private final LocalDateTime time;
    private final SensorValueObject<?> reading;
    private final SensorIDVO sensorID;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorTypeID;

    /**
     * Constructs a new Log object with the provided parameters. Usually created right after a reading is obtained.
     * @param reading the sensor value object representing the reading
     * @param sensorID the sensor ID object representing the ID of the sensor
     * @param deviceID the device ID object representing the ID of the device
     * @param sensorTypeID the sensor type ID object representing the ID of the sensor type
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Log(SensorValueObject<?> reading, SensorIDVO sensorID, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID){
        if (areParamsNull(reading,sensorID,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Invalid House Entity parameters.");
        }

        this.logID = new LogIDVO(UUID.randomUUID());
        this.time = LocalDateTime.now();
        this.reading = reading;
        this.sensorID = sensorID;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
    }

    /**
     * Constructs a new Log object with the provided parameters. Usually created after a query to the persistence.
     * @param logID the log ID object representing the ID of the log
     * @param time timestamp of the reading
     * @param reading the sensor value object representing the reading
     * @param sensorID the sensor ID object representing the ID of the sensor
     * @param deviceID the device ID object representing the ID of the device
     * @param sensorTypeID the sensor type ID object representing the ID of the sensor type
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Log(LogIDVO logID, LocalDateTime time, SensorValueObject<?> reading, SensorIDVO sensorID, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID){
        if (areParamsNull(logID,time,reading,sensorID,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Invalid House Entity parameters.");
        }

        this.logID = logID;
        this.time = time;
        this.reading = reading;
        this.sensorID = sensorID;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
    }

    /**
     * Retrieves the unique identifier for this Log entry.
     * @return The LogIDVO representing the unique identifier for this log entry.
     */
    @Override
    public DomainID getId() {
        return logID;
    }

    /**
     * Retrieves the reading value of the sensor.
     * @return The ValueObject representing the reading value of the sensor.
     */
    public SensorValueObject<?> getReading() {
        return reading;
    }

    /**
     * Retrieves the unique identifier for the sensor.
     * @return The SensorIDVO representing the unique identifier for the sensor.
     */
    public SensorIDVO getSensorID() {
        return sensorID;
    }

    /**
     * Retrieves the unique identifier for the device associated with this log.
     * @return The DeviceIDVO representing the unique identifier for the device.
     */
    public DeviceIDVO getDeviceID() {
        return deviceID;
    }

    /**
     * Retrieves the unique identifier for the sensor type.
     * @return The SensorTypeIDVO representing the unique identifier for the sensor type.
     */
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Returns the timestamp representing the time stored in this object.
     * @return the timestamp representing the time stored in this object.
     */
    public LocalDateTime getTime(){
        return this.time;
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