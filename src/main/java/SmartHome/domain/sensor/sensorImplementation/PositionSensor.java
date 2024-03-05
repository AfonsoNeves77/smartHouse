package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PositionSensor implements Sensor{

    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "%";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    public PositionSensor(String sensorName, ExternalServices externalServices) {
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    @Override
    public String getName(){
        return this.sensorName;
    }

    public Value<Integer> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        PositionValue readingValue = new PositionValue(simValue);

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
