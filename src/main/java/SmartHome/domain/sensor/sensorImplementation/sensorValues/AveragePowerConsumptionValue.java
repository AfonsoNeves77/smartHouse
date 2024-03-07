package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;


public class AveragePowerConsumptionValue implements Value<Integer> {
    private final int primitiveValue;

    /**
     * Constructs an AveragePowerConsumptionValue object from a string representation of the reading.
     *
     * @param reading String representation of the reading
     * @throws InstantiationException if the reading is invalid
     */
    public AveragePowerConsumptionValue(String reading) throws InstantiationException {
        if(!validateReading(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Validates the reading.
     *
     * @param reading String representation of the reading
     * @return true if the reading is a valid integer value, false otherwise
     */
    private boolean validateReading(String reading) {
        double valueOfReading;
        try {
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0;
    }

    /**
     * Retrieves the integer value of the reading.
     *
     * @return Integer value of the reading
     */
    @Override
    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * Retrieves the string representation of the reading.
     *
     * @return String representation of the reading
     */
    @Override
    public String getValueAsString() {
        return primitiveValue + "";
    }
}
