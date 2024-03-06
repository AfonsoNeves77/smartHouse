package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

public class PositionValue implements Value<Integer>{
    private final int primitiveValue;

    /**
     * Constructor for Position value.
     * It validates the reading and stores it as an integer.
     * @param reading Position value as String.
     * @throws InstantiationException On invalid parameters.
     */

    public PositionValue(String reading) throws InstantiationException {
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

    /**
     * This method checks if the input reading is valid. It must be a number between 0 and 100.
     * @param reading Position value as String.
     * @return True if the reading is valid, false otherwise.
     */

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
