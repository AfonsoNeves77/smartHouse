package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

public class DewPointSensor implements Sensor {

    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "C";
    private final ArrayList<Value<Double>> log = new ArrayList<>();

    public DewPointSensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new InstantiationException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    @Override
    public String getName() {
        return this.sensorName;
    }

    public Value<Double> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        DewPointValue readingValue = new DewPointValue(simValue);

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
