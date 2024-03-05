package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class DewPointValue implements Value<Double> {

    private final double primitiveValue;

    public DewPointValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseDouble(reading);
    }

    public Double getValue() {
        return this.primitiveValue;
    }

    public String getValueAsString() {
        return primitiveValue + "";
    }

    private boolean isReadingValid(String reading){
        try{
            parseDouble(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}