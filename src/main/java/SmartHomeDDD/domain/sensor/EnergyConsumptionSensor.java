package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.domain.sensor.sensorValues.EnergyConsumptionValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.UUID;

public class EnergyConsumptionSensor implements Sensor {
    private SensorNameVO name;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorTypeID;
    private final SensorIDVO energyConsumptionSensorID;

    /**
     * Constructor for EnergyConsumptionSensor
     * @param name
     * @param deviceID
     * @param sensorTypeID
     */
    public EnergyConsumptionSensor(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (!validateParameters(name, deviceID, sensorTypeID)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }

        this.energyConsumptionSensorID = new SensorIDVO(UUID.randomUUID());
        this.name = name;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;
    }

    /**
     * Method to validate the parameters
     * @param name
     * @param deviceID
     * @param sensorTypeID
     * @return boolean
     */
    private boolean validateParameters(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        return name != null && deviceID != null && sensorTypeID != null;
    }


    @Override
    public SensorIDVO getId() {
        return this.energyConsumptionSensorID;
    }

    @Override
    public SensorNameVO getSensorName() {
        return this.name;
    }

    @Override
    public SensorTypeIDVO getSensorTypeID() {
        return this.sensorTypeID;
    }

    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceID;
    }

    /**
     * Method to get the reading
     * @param initialDate
     * @param finalDate
     * @param simHardware
     * @return ValueObject<Integer>
     * @throws InstantiationException
     */
    public ValueObject<Integer> getReading(String initialDate, String finalDate, SimHardware simHardware) throws InstantiationException {
        if (!validateDates(initialDate, finalDate)) {
            throw new IllegalArgumentException("Invalid date");
        }
        if (simHardware == null) {
            throw new IllegalArgumentException("Invalid external service");
        }
        String simValue = simHardware.getValue(initialDate, finalDate);
        return new EnergyConsumptionValue(simValue);
    }

    /**
     * Parses the initial date string into LocalDateTime object.
     *
     * @param initialDate Initial date string
     * @return LocalDateTime object representing the initial date
     */
    private LocalDateTime getInitialDateTime(String initialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime initialDateTime = LocalDateTime.parse(initialDate, formatter);
        return initialDateTime;

    }

    /**
     * Parses the final date string into LocalDateTime object.
     *
     * @param finalDate Final date string
     * @return LocalDateTime object representing the final date
     */
    private LocalDateTime getFinalDateTime(String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime finalDateTime = LocalDateTime.parse(finalDate, formatter);
        return finalDateTime;
    }

    /**
     * Validates the date format and checks if the initial date is before the final date and both are before the current time.
     *
     * @param initialDate Initial date string
     * @param finalDate   Final date string
     * @return true if the date format is valid and initial date is before final date and both are before the current time, false otherwise
     */
    private boolean validateDates(String initialDate, String finalDate) {
        LocalDateTime initialDateTime;
        LocalDateTime finalDateTime;
        try{
            initialDateTime = getInitialDateTime(initialDate);
            finalDateTime = getFinalDateTime(finalDate);
        } catch(DateTimeParseException e) {
            return false;
        }
        return initialDate != null && finalDateTime != null && initialDateTime.isBefore(finalDateTime) && finalDateTime.isBefore(LocalDateTime.now());
    }
}