package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.util.ArrayList;


public class InstantPowerConsumptionSensor implements Sensor {

    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "W";
    private final ArrayList<Value<Double>> log = new ArrayList<>();

    public InstantPowerConsumptionSensor(String sensorName, ExternalServices externalServices){
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
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
        //1.
        ArrayList<String> tmpLog = new ArrayList<>();
        //2.
        for (Value<Double> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }
}
