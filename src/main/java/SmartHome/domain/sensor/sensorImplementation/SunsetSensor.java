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
    private final String unit = "Zoned Date Time";
    private final String type = "Sunset Sensor";
    private ArrayList<Value<ZonedDateTime>> log = new ArrayList<>();

    /**
     * Constructor for SunsetSensor Objects.
     * @param sensorName Sensor name
     * @param sunTimeCalculator External Service in use
     * @throws InstantiationException If sensor name is null or empty, or External Service is null.
     */
    public SunsetSensor(String sensorName, ExternalServices sunTimeCalculator) throws InstantiationException {
        if(!validSensorName(sensorName) || !validSunTimeCalculator(sunTimeCalculator)){
            throw new InstantiationException("Invalid parameters.");
        }
        this.sensorName = sensorName;
        this.sunTimeCalculator = (SunTimeCalculator) sunTimeCalculator;
    }

    /**
     * Validates if sensor name is valid (not empty or null).
     * @param sensorName Sensor name
     * @return True if name is valid.
     */
    private boolean validSensorName(String sensorName){
        return (sensorName != null && !sensorName.trim().isEmpty());
    }

    /**
     * Validates if service provided is valid (not null).
     * @param sunTimeCalculator service
     * @return True if service is valid.
     */
    private boolean validSunTimeCalculator(ExternalServices sunTimeCalculator) {
        return (sunTimeCalculator != null);
    }

    /**
     *
     * @param date Date as String. Accepted format: YYYY-MM-DD
     * @param gpsLocation Decimal location coordinates. Accepted format: (latitude : longitude)
     * @return Sunset time in ZonedDateTime Value format.
     * @throws InstantiationException If retrieved value from computeSunsetTime() method is null (operation fails).
     */
    public Value<ZonedDateTime> getSunsetTime(String date, String gpsLocation) throws InstantiationException {
        ZonedDateTime sunsetTime = this.sunTimeCalculator.computeSunsetTime(date, gpsLocation);
        SunTimeValue finalValue = new SunTimeValue(sunsetTime);
        addValueToLog(finalValue);
        return finalValue;
    }


    /**
     * Adds operation result to log, in case operation succeeds.
     * @param value Result to be added.
     */
    private void addValueToLog(Value<ZonedDateTime> value){
        this.log.add(value);
    }

    /**
     * @return Retrieves the name of the sensor.
     */
    @Override
    public String getName() {
        return this.sensorName;
    }

    /**
     * @return Retrieves the unit defined for the sensor.
     */
    @Override
    public String getUnit() {
        return this.unit;
    }

    /**
     * @return Retrieves the sensor log, but data is first converted to String.
     */
    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> tmpLog = new ArrayList<>();
        for (Value<ZonedDateTime> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }

    /**
     * @return Retrieves the type of the sensor.
     */
    public String getType(){
        return this.type;
    }
}
