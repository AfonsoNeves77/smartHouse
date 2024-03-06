package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SunTimeValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.time.ZonedDateTime;
import java.util.ArrayList;

/**
 * The SunriseSensor class represents a sensor that calculates sunrise times based on
 * provided date and GPS location. It implements the Sensor interface and maintains
 * a log of sunrise time values.

 * The sensor has a unique name, a type identifier, and a fixed unit for representing
 * sunrise times as ZonedDateTime objects. It utilizes an external SunTimeCalculator
 * for the actual sunrise time calculations.

 * The log keeps track of the historical sunrise time values calculated by the sensor.
 *
 */
public class SunriseSensor implements Sensor {

    private String sensorName;
    private SunTimeCalculator sunTimeCalculator;
    private final String unit = "Zone Date Time";
    private ArrayList<Value<ZonedDateTime>> log = new ArrayList<>();

    private final String type = "Sunrise Sensor";

    /**
     * Constructs a SunriseSensor with the specified sensor name and SunTimeCalculator.
     * Validates the input parameters, throwing InstantiationException if they are invalid.
     *
     * @param sensorName the unique name of the sensor
     * @param sunTimeCalculator the external service for sunrise time calculations
     * @throws InstantiationException if the sensorName or sunTimeCalculator is invalid
     */
    public SunriseSensor(String sensorName, ExternalServices sunTimeCalculator) throws InstantiationException {
        if (!validSensorName(sensorName) || !validSunTimeCalculator(sunTimeCalculator)) {
            throw new InstantiationException("Invalid parameters");
        }
        this.sensorName = sensorName;
        this.sunTimeCalculator = (SunTimeCalculator) sunTimeCalculator;
    }

    private boolean validSensorName(String sensorName) {
        return (sensorName != null && !sensorName.trim().isEmpty());
    }

    private boolean validSunTimeCalculator(ExternalServices sunTimeCalculator) {
        return (sunTimeCalculator != null);
    }

    /**
     * Retrieves the sunrise time based on the specified date and GPS location.
     * Calculates the sunrise time using the SunTimeCalculator and adds the result
     * to the log.
     *
     * @param date the date for which to calculate the sunrise time
     * @param gpsLocation the GPS location for which to calculate the sunrise time
     * @return the calculated sunrise time as a Value<ZonedDateTime>
     * @throws InstantiationException if an error occurs during calculation and an invalid
     * parameters is passed to SunTimeValue constructor
     */
    public Value<ZonedDateTime> getSunriseTime(String date, String gpsLocation) throws InstantiationException {
        ZonedDateTime sunriseTime = this.sunTimeCalculator.computeSunrise(date, gpsLocation);
        SunTimeValue finalValue = new SunTimeValue(sunriseTime);
        addValueToLog(finalValue);
        return finalValue;
    }

    private void addValueToLog(Value<ZonedDateTime> value) {
        this.log.add(value);
    }

    /**
     * Retrieves the type identifier of the sensor.
     *
     * @return the type identifier of the sensor
     */
    public String getType() {
        return type;
    }

    /**
     * Retrieves the name of the sensor.
     *
     * @return the name of the sensor
     */
    @Override
    public String getName() {
        return this.sensorName;
    }

    /**
     * Retrieves the unit used for representing sunrise times.
     *
     * @return the unit for representing sunrise times
     */
    @Override
    public String getUnit() {
        return this.unit;
    }

    /**
     * Retrieves the log of historical sunrise time values as strings.
     * It converts each Value for a String
     *
     * @return the log of historical sunrise time values
     */
    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> tmpLog = new ArrayList<>();
        for (Value<ZonedDateTime> value : this.log) {
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }
}
