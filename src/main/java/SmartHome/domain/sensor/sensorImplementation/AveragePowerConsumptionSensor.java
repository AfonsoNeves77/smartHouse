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
    private final String unit = "W";
    private SimHardware simHardware;

    private final ArrayList<Value<Double>> log = new ArrayList<>();

    public AveragePowerConsumptionSensor(String sensorName, ExternalServices externalServices) {
        if(!validateSensorName(sensorName)){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    private boolean validateSensorName(String sensorName) {
        return sensorName != null && !sensorName.trim().isEmpty();
    }

    public Value<Double> getReading(String initialDate, String finalDate) throws InstantiationException {
        try {
            LocalDateTime initialDateTime = getInitialDateTime(initialDate);
            LocalDateTime finalDateTime = getFinalDateTime(finalDate);
            if (!validateDate(initialDateTime, finalDateTime)) {
                throw new IllegalArgumentException("Invalid date: initial date must be before final date and final date must be before current date");
            }

            String simValue = this.simHardware.getValue(initialDate,finalDate);
            AveragePowerConsumptionValue readingValue = new AveragePowerConsumptionValue(simValue);
            addValueToLog(readingValue);
            return readingValue;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: expected 'dd-MM-yyyy HH:mm:ss'");

        }
    }


    private LocalDateTime getInitialDateTime(String initialDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime initialDateTime = LocalDateTime.parse(initialDate, formatter);
        return initialDateTime;

    }
    private LocalDateTime getFinalDateTime(String finalDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        LocalDateTime finalDateTime = LocalDateTime.parse(finalDate, formatter);
        return finalDateTime;
    }

    private boolean validateDate(LocalDateTime initialDate, LocalDateTime finalDateTime) {
        return initialDate != null && finalDateTime != null && initialDate.isBefore(finalDateTime) && finalDateTime.isBefore(LocalDateTime.now());
    }

    @Override
    public String getName() {
            return this.sensorName;
    }

    @Override
    public String getUnit() {
        return this.unit;
    }

    @Override
    public ArrayList<String> getLog() {
        // 1.
        ArrayList<String> tmpLog = new ArrayList<>();
        // 2.
        for (Value<Double> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }


    private void addValueToLog(Value<Double> value){
        this.log.add(value);
    }
}


