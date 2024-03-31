package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensor.externalServices.SunTimeCalculator;
import SmartHomeDDD.domain.sensor.sensorValues.SunTimeValue;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;

import java.time.ZonedDateTime;
import java.util.UUID;

public class SunriseSensor implements Sensor, DomainEntity {

    private final SensorIDVO sensorID;
    private SensorNameVO sensorName;
    private final SensorTypeIDVO sensorTypeID;
    private final DeviceIDVO deviceID;

    /**
     * Constructor for SunriseSensor, it validates the receiving VOs and creates a new SensorIDVO utilizing the UUID
     * library. Received VOs and SensorID are encapsulated within the object.
     * @param sensorName Sensor name
     * @param deviceID Device ID
     * @param sensorTypeID SensorType ID
     */
    public SunriseSensor(SensorNameVO sensorName, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (areParamsNull(sensorName,deviceID,sensorTypeID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.sensorName = sensorName;
        this.deviceID = deviceID;
        this.sensorTypeID = sensorTypeID;
        this.sensorID = new SensorIDVO(UUID.randomUUID());
    }

    /**
     * Obtains the sunrise time. It receives a data, gps location and SunTimeCalculator external service (validating it
     * against null). Returns a SunTimeValue object as ValueObject<ZoneDateTime>
     * @param date Date
     * @param gpsLocation Geolocation
     * @param sunTimeCalculator SuntimeCalculator external service
     * @return SunTimeValue object
     */
    public ValueObject<ZonedDateTime> getSunriseTime(String date, String gpsLocation, SunTimeCalculator sunTimeCalculator) {
        if (areParamsNull(sunTimeCalculator)){
            throw new IllegalArgumentException("Invalid external service");
        }
        ZonedDateTime sunriseTime = sunTimeCalculator.computeSunrise(date, gpsLocation);
        return new SunTimeValue(sunriseTime);
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for Name;
     */
    @Override
    public SensorNameVO getSensorName(){
        return this.sensorName;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for SensorTypeID;
     */
    @Override
    public SensorTypeIDVO getSensorTypeID(){
        return this.sensorTypeID;
    }

    /**
     * Simple getter method
     * @return The encapsulated VO for DeviceID;
     */
    @Override
    public DeviceIDVO getDeviceID(){
        return this.deviceID;
    }

    /**
     * Simple getter method, implemented from EntityDomain interface
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public DomainID getId() {
        return this.sensorID;
    }

    /**
     * Validates any number of object params against null
     * @param params Any object param
     * @return True or false
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}