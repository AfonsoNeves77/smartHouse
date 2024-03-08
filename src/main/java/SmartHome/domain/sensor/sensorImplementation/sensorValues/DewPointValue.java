package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Double.parseDouble;

public class DewPointValue implements Value<Double> {

    private final double primitiveValue;

    /**
     * Constructor for Dew point value. It receives a reading in string form, validates it, perses into double, and saved it.
     * @param reading Reading
     * @throws InstantiationException
     */
    public DewPointValue(String reading) throws InstantiationException {
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
     * Returns the encapsulated primitive value, maintaining its type. It provides the optional wind direction
     * @return Encapsulated primitive value.
     */
    public String getValueAsString() {
        return primitiveValue + "";
    }

    /**
     * Ensures reading is not null or empty, as it would fail the parsing straight away. It then attempts to parse it into
     * a double.
     * @param reading Reading
     * @return True or false.
     */
    private boolean isReadingValid(String reading){
        try{
            parseDouble(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }
}