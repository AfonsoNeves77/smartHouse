package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

public class EnergyConsumptionValue implements Value<Integer> {
    private final int primitiveValue;

    /**
     * Constructor for EnergyConsumptionValue
     * @param reading reading value
     * @throws InstantiationException if reading is invalid
     */
    public EnergyConsumptionValue(String reading) throws InstantiationException {
        if(!validateReading(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Method to validate reading
     * @param reading reading value
     * @return true if reading is valid, false otherwise
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
     * Method to get the value
     * @return value
     */
    @Override
    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * Method to get the value as a string
     * @return value as a string
     */
    @Override
    public String getValueAsString() {
        return primitiveValue + "";
    }
}
