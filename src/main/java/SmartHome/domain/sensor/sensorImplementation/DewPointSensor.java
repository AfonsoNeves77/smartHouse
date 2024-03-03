package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;

import java.util.ArrayList;

import static java.lang.Double.parseDouble;

public class DewPointSensor implements Sensor {

    private String sensorName;

    private final String unit = "C";

    private final ArrayList<Value<Double>> log = new ArrayList<>();

    public DewPointSensor(String sensorName){
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
    }
    @Override
    public String getName() {
        return this.sensorName;
    }
    public Value<Double> getReading(SimHardware simHardware)  {

        String simValue = simHardware.getValue();
        if (simValue == null || simValue.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid reading");
        }

        double value;
        try {
            value = parseDouble(simHardware.getValue());
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid reading");
        }

        DewPointValue readingValue = new DewPointValue(value);

        //4.
        addValueToLog(readingValue);
        return readingValue;
    }
    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> dpLog = new ArrayList<>();
        // 2.
        for (Value<Double> value : this.log){
            dpLog.add(value.getValueAsString());
        }
        return dpLog;
    }

    private void addValueToLog (Value<Double> value){
        this.log.add(value);
    }

    public String getUnit() {
        return this.unit;
    }






}
