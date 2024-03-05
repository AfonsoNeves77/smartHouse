package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import java.time.ZonedDateTime;

public class SunTimeValue implements Value<ZonedDateTime> {

    private final ZonedDateTime sunTimeValue;

    public SunTimeValue(ZonedDateTime sunTimeValue) throws InstantiationException {
        if(sunTimeValue == null){
            throw new InstantiationException("Invalid sun time value");
        }
        this.sunTimeValue = sunTimeValue;
    }

    @Override
    public ZonedDateTime getValue() {
        return this.sunTimeValue;
    }

    @Override
    public String getValueAsString() {
        return this.sunTimeValue.toString();
    }
}
