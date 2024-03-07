package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.EnergyConsumptionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;



public class EnergyConsumptionSensor implements Sensor{
    private String sensorName;
    private final String unit = "Wh";
    private final String Type = "Energy Consumption";
    private SimHardware simHardware;

    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
     * Constructor for EnergyConsumptionSensor
     * @param sensorName name of the sensor
     * @param externalServices external services
     */
    public EnergyConsumptionSensor(String sensorName, ExternalServices externalServices) {
        if(!validateSensorName(sensorName)){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    /**
     * Method to validate sensor name
     * @param sensorName name of the sensor
     */
    private boolean validateSensorName(String sensorName) {
        return sensorName != null && !sensorName.trim().isEmpty();
    }

    /**
     * Method to get the reading
     * @param initialDate initial date
     * @param finalDate final date
     * @return reading value
     * @throws InstantiationException
     */
    public Value<Integer> getReading(String initialDate, String finalDate) throws InstantiationException {
        try {
            LocalDateTime initialDateTime = getInitialDateTime(initialDate);
            LocalDateTime finalDateTime = getFinalDateTime(finalDate);
            if (!validateDate(initialDateTime, finalDateTime)) {
                throw new IllegalArgumentException("Invalid date: initial date must be before final date and final date must be before current date");
            }

            String simValue = this.simHardware.getValue(initialDate,finalDate);
            EnergyConsumptionValue readingValue = new EnergyConsumptionValue(simValue);
            addValueToLog(readingValue);
            return readingValue;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: expected 'dd-MM-yyyy HH:mm:ss'");

        }
    }


    /**
     * Method to get the initial date time
     * @param initialDate initial date
     * @return initial date time
     */
    private LocalDateTime getInitialDateTime(String initialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime initialDateTime = LocalDateTime.parse(initialDate, formatter);
        return initialDateTime;

    }

    /**
     * Method to get the final date time
     * @param finalDate final date
     * @return final date time
     */
    private LocalDateTime getFinalDateTime(String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime finalDateTime = LocalDateTime.parse(finalDate, formatter);
        return finalDateTime;
    }

    /**
     * Method to validate date
     * @param initialDate initial date
     * @param finalDateTime final date time
     * @return
     */
    private boolean validateDate(LocalDateTime initialDate, LocalDateTime finalDateTime) {
        return initialDate != null && finalDateTime != null && initialDate.isBefore(finalDateTime) && finalDateTime.isBefore(LocalDateTime.now());
    }

    /**
     * Method to get the name of the sensor
     * @return name of the sensor
     */
    @Override
    public String getName() {
        return this.sensorName;
    }

    /**
     * Method to get the unit of the sensor
     * @return unit of the sensor
     */
    @Override
    public String getUnit() {
        return this.unit;
    }

    /**
     * Method to get the log
     * @return log
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

    public String getType() {
        return Type;
    }

    /**
     * Method to add value to log
     * @param value value
     */
    private void addValueToLog(Value<Integer> value){
        this.log.add(value);
    }
}
