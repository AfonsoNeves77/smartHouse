package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

/**
 * Implementation of value for the Humidity Sensor.
 */
public class HumidityValue implements Value<Integer> {
    private final int primitiveValue;

    /**
     * Constructor for humidity value. It can only be instantiated if the receiving parameter is within
     * the specified interval.
     * @param reading Value received by an external source, in line for conversion.
     * @throws InstantiationException If parameters out of bounds.
     */
    public HumidityValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type.
     * @return Encapsulated primitive value.
     */
    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * Returns the primitive value after conversion to String format.
     * @return Encapsulated primitive value as String.
     */
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
