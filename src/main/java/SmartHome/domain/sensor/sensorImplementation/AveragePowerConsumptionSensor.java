package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.AveragePowerConsumptionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class AveragePowerConsumptionSensor implements Sensor{
    private String sensorName;
    private final String UNIT = "W";
    private final String TYPE = "Average Power Consumption Sensor";
    private SimHardware simHardware;

    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
     * Constructor for AveragePowerConsumptionSensor. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
     * @param sensorName Name of the sensor
     */
    public AveragePowerConsumptionSensor(String sensorName, ExternalServices externalServices) {
        if(!validateSensorName(sensorName)){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    /**
     * Validates the sensor name.
     *
     * @param sensorName Name of the sensor
     * @return true if the sensor name is not null or empty, false otherwise
     */
    private boolean validateSensorName(String sensorName) {
        return sensorName != null && !sensorName.trim().isEmpty();
    }

    /**
     * Retrieves a reading from the sensor for the specified time period.
     *
     * @param initialDate Initial date of the time period in "dd-MM-yyyy HH:mm:ss" format
     * @param finalDate   Final date of the time period in "dd-MM-yyyy HH:mm:ss" format
     * @return AveragePowerConsumptionValue object representing the reading
     * @throws IllegalArgumentException if the date format is invalid
     */
    public Value<Integer> getReading(String initialDate, String finalDate) throws InstantiationException {
        if(!validateDate(initialDate, finalDate)){
            throw new IllegalArgumentException("Invalid date");
        }
        String simValue = this.simHardware.getValue(initialDate,finalDate);
            AveragePowerConsumptionValue readingValue = new AveragePowerConsumptionValue(simValue);
            addValueToLog(readingValue);
            return readingValue;
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
    private boolean validateDate(String initialDate, String finalDate) {
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

    /**
     * Retrieves the name of the sensor.
     *
     * @return Name of the sensor
     */
    @Override
    public String getName() {
            return this.sensorName;
    }

    /**
     * Retrieves the unit of the sensor.
     *
     * @return Unit of the sensor
     */
    @Override
    public String getUnit() {
        return this.UNIT;
    }

    /**
     * Retrieves the log of readings from the sensor.
     * Each reading is converted to a string representation.
     * 1. Creates an empty ArrayList of strings.
     * 2. Iterates through all the Value objects saved into the log. For each Value object found, it converts
     * it to string, and adds it to the ArrayList created in step 1. Returning it after it performs the iteration.
     * @return ArrayList of strings representing the log of readings
     */
    @Override
    public ArrayList<String> getLog() {
        // 1.
        ArrayList<String> tmpLog = new ArrayList<>();
        // 2.
        for (Value<Integer> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }

    /**
     * Retrieves the type of the sensor.
     *
     * @return Type of the sensor
     */
    public String getType() {
        return this.TYPE;
    }

    /**
     * Adds a value to the log.
     *
     * @param value Value to be added to the log
     */
    private void addValueToLog(Value<Integer> value){
        this.log.add(value);
    }
}


