package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

public class InstantPowerConsumptionValue implements Value<Integer>{
    private final int primitiveValue;

    public InstantPowerConsumptionValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    private boolean isReadingValid(String reading){
        double valueOfReading;
        try{
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        return valueOfReading >= 0;
    }

    @Override
    public Integer getValue() {
        return this.primitiveValue;
    }

    @Override
    public String getValueAsString() {
        return this.primitiveValue + "";
    }
}
