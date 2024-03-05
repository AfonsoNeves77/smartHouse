package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Double.parseDouble;

/**
 * Implementation of value for the Temperature Sensor.
 */
public class TemperatureValue implements Value<Double> {
    private final double primitiveValue;

    /**
     * Constructor for Temperature value.
     * @param reading Value received by an external source, in line for conversion.
     */
    public TemperatureValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseDouble(reading);
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type.
     * @return Encapsulated primitive value.
     */
    public Double getValue() {
        return this.primitiveValue;
    }

    /**
     * Returns the primitive value after conversion to String format.
     * @return Encapsulated primitive value as String.
     */
    public String getValueAsString(){
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
