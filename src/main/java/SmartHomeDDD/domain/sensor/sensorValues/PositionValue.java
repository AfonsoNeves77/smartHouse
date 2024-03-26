package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHomeDDD.vo.ValueObject;

/**
 * Implementation of the Value interface for representing position values.
 * This class stores an integer value representing a position that has to be between
 * 0 and 100.
 */
public class PositionValue implements ValueObject<Integer> {

    private final int positionValue;

    /**
     * Constructs a PositionValue object with the provided integer value.
     * @param positionValue The integer value to be stored.
     * @throws IllegalArgumentException If the provided positionValue is
     * not between 0 and 100.
     */
    public PositionValue(int positionValue) {
        if (!isReadingValid(positionValue)) {
            throw new IllegalArgumentException("Invalid Position Value, has to be between 0 and 100");
        }
        this.positionValue = positionValue;
    }

    /**
     * Validates the position value so that it is between 0 and 100.
     */
    private boolean isReadingValid(int positionValue) {
        if (positionValue < 0 || positionValue > 100) return false;
        return true;
    }

    /**
     * Retrieves the integer value stored in this PositionValue.
     * @return  The integer value representing the position.
     */
    @Override
    public Integer getValue() {
        return this.positionValue;
    }
}
