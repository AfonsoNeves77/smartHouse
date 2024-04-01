package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensor.sensorValues.AveragePowerConsumptionValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class AveragePowerConsumptionSensor implements Sensor, DomainEntity {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for the average power consumption sensor. It creates a new sensor with a random ID.
     * It throws an exception if any of the parameters is null.
     *
     * @param sensorName   the name of the sensor
     * @param deviceID     the ID of the device
     * @param sensorTypeID the type of the sensor
     */

    public AveragePowerConsumptionSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName, deviceID, sensorTypeID)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }

    /**
     * Method to validate if the received parameters are null. It receives a SensorNameVO, SensorTypeIDVO and DeviceIDVO
     * and returns a boolean.
     *
     * @param sensorName   Sensor name
     * @param sensorTypeID SensorType ID
     * @param deviceID     Device ID
     * @return boolean
     */

    private boolean areParamsNull(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        return sensorName == null || deviceID == null || sensorTypeID == null;
    }

    /**
     * Obtains the average power consumption reading. It receives an initial date, a final date and a SimHardware external
     * service (validating it against null). Returns an AveragePowerConsumptionValue object as ValueObject<Integer>
     *
     * @param initialDate Initial date
     * @param finalDate   Final date
     * @param simHardware SimHardware external service
     * @return AveragePowerConsumptionValue object
     */

    public ValueObject<Integer> getReading(String initialDate, String finalDate, SimHardware simHardware) {
        if (!validateDate(initialDate, finalDate)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (simHardware == null) {
            throw new IllegalArgumentException("Invalid external service");
        }
        String simValue = simHardware.getValue(initialDate, finalDate);
        return new AveragePowerConsumptionValue(simValue);
    }

    /**
     * Validates the date. It receives an initial date and a final date and returns a boolean.
     *
     * @param initialDate Initial date
     * @param finalDate   Final date
     * @return boolean
     */

    private boolean validateDate(String initialDate, String finalDate) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            LocalDateTime.parse(initialDate, formatter);
            LocalDateTime.parse(finalDate, formatter);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public SensorNameVO getSensorName() {
        return sensorName;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for SensorTypeID;
     */
    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeID;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for DeviceID;
     */

    @Override
    public DeviceIDVO getDeviceID() {
        return deviceID;
    }

    /**
     * Simple getter method
     * It's an override from DomainEntity interface
     *
     * @return The encapsulated VO for SensorID;
     */

    @Override
    public DomainID getId() {
        return this.sensorID;
    }
}
