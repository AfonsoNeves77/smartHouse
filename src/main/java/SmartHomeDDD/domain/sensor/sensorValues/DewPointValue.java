package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHomeDDD.vo.ValueObject;

import static java.lang.Double.parseDouble;

public class DewPointValue implements ValueObject<Double> {
    private final double dewPointValue;
    /**
     * Constructor for Dew point value. It receives a dewPointValue in string form and validates it.
     * @param dewPointValue dewPointValue
     * @throws IllegalArgumentException
     */
    public DewPointValue(String dewPointValue) {
        if (!isReadingValid(dewPointValue)) {
            throw new IllegalArgumentException("Invalid DewPoint reading");
        }
        this.dewPointValue = parseDouble(dewPointValue);
    }
    /**
     * A simple getter method of Value.
     * @return Encapsulated DewPointvalue.
     */

    public Double getValue() {
        return this.dewPointValue;
    }

    /**
     * Ensures dewPointValue is not null or empty, as it would fail the parsing straight away. It then attempts to parse it into
     * a double.
     * @param dewPointValue dewPointValue
     * @return True or false.
     */

    private boolean isReadingValid(String dewPointValue) {
        try {
            parseDouble(dewPointValue);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}