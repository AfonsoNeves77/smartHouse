package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

public class PositionValue implements Value<Integer>{
    private final int primitiveValue;

    public PositionValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    public Integer getValue() {
        return this.primitiveValue;
    }

    public String getValueAsString(){
        return primitiveValue + "";
    }

    private boolean isReadingValid(String reading) {
        int valueOfReading;
        try {
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0 && valueOfReading <= 100;
    }
}
