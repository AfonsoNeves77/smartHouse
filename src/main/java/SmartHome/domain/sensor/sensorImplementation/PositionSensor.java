package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PositionSensor implements Sensor{

    private String sensorName;
    private final String unit = "%";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    public PositionSensor(String sensorName) {
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
    }

    @Override
    public String getName(){
        return this.sensorName;
    }

    public Value<Integer> getReading(SimHardware simHardware) throws InstantiationException {
        String simValue = simHardware.getValue();
        if (simValue == null || simValue.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid reading");
        }
        int value = parseInt(simHardware.getValue());
        PositionValue readingValue = new PositionValue(value);
        addValueToLog(readingValue);
        return readingValue;
    }

    @Override
    public ArrayList<String> getLog(){
        ArrayList<String> logList = new ArrayList<>();
        for(Value<Integer> value : log){
            logList.add(value.getValueAsString());
        }
        return logList;
    }

    private void addValueToLog(Value<Integer> value){
        log.add(value);
    }

    public String getUnit(){
        return this.unit;
    }


}
