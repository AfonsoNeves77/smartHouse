package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHomeDDD.vo.ValueObject;

import static java.lang.Integer.parseInt;

public class EnergyConsumptionValue implements ValueObject<Integer> {
    private final int primitiveValue;

    /**
     * Constructor for EnergyConsumptionValue
     * @param reading
     * @throws InstantiationException
     */
    public EnergyConsumptionValue(String reading) throws InstantiationException {
        if(!validateReading(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Method to validate the reading
     * @param reading
     * @return boolean
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

    public Integer getValue() {
        return this.primitiveValue;
    }

    public String getValueAsString() {
        return primitiveValue + "";
    }
}