package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SunTimeValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import java.time.ZonedDateTime;
import java.util.ArrayList;

public class SunsetSensor implements Sensor {
    private String sensorName;
    private SunTimeCalculator sunTimeCalculator;
    private final String unit = "Zone Date Time";
    private ArrayList<Value<ZonedDateTime>> log = new ArrayList<>();

    public SunsetSensor(String sensorName, ExternalServices sunTimeCalculator) throws InstantiationException {
        if(!validSensorName(sensorName) || !validSunTimeCalculator(sunTimeCalculator)){
            throw new InstantiationException("Invalid parameters.");
        }
        this.sensorName = sensorName;
        this.sunTimeCalculator = (SunTimeCalculator) sunTimeCalculator;
    }

    private boolean validSensorName(String sensorName){
        return (sensorName != null && !sensorName.trim().isEmpty());
    }

    private boolean validSunTimeCalculator(ExternalServices sunTimeCalculator) {
        return (sunTimeCalculator != null);
    }

    public Value<ZonedDateTime> getSunsetTime(String date, String gpsLocation) throws InstantiationException {
        ZonedDateTime sunsetTime = this.sunTimeCalculator.computeSunsetTime(date, gpsLocation);
        SunTimeValue finalValue = new SunTimeValue(sunsetTime);
        addValueToLog(finalValue);
        return finalValue;
    }


    private void addValueToLog(Value<ZonedDateTime> value){
        this.log.add(value);
    }

    @Override
    public String getName() {
        return this.sensorName;
    }



    //Remove this from the Sensor interface??
    @Override
    public String getUnit() {
        return this.unit;
    }

    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> tmpLog = new ArrayList<>();
        for (Value<ZonedDateTime> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }
}
