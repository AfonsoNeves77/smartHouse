package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import org.apache.commons.lang3.ObjectUtils;

import static java.lang.Double.parseDouble;

public class InstantPowerConsumptionValue implements Value<Double>{
    private final double primitiveValue;

    public InstantPowerConsumptionValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseDouble(reading);
    }

    private boolean isReadingValid(String reading){
        double valueOfReading;
        try{
            valueOfReading = parseDouble(reading);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        return valueOfReading >= 0;
    }

    @Override
    public Double getValue() {
        return this.primitiveValue;
    }

    @Override
    public String getValueAsString() {
        return this.primitiveValue + "";
    }
}
