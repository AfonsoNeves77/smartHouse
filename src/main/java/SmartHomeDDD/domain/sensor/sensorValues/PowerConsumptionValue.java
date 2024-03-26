package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHomeDDD.vo.ValueObject;

public class PowerConsumptionValue implements ValueObject<Integer> {

    /**
     * The integer value representing the power consumption.
     */
    private final int powerConsumptionValue;

    /**
     * Constructor for PowerConsumptionValue class.
     * @param powerConsumptionValue int value to be stored. Must be greater than or equal to 0.
     * @throws IllegalArgumentException if powerConsumptionValue is negative.
     */
    public PowerConsumptionValue(int powerConsumptionValue) {
        if (powerConsumptionValue < 0) {
            throw new IllegalArgumentException("Invalid power consumption value");
        }
        this.powerConsumptionValue = powerConsumptionValue;
    }

    /**
     * Retrieves the integer value stored in this PowerConsumptionValue.
     * @return The integer value representing the power consumption.
     */
    @Override
    public Integer getValue() {
        return this.powerConsumptionValue;
    }
}
