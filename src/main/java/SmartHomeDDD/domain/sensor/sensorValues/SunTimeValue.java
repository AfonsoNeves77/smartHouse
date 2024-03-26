package SmartHomeDDD.domain.sensor.sensorValues;

import SmartHomeDDD.vo.ValueObject;

import java.time.ZonedDateTime;

/**
 * Implementation of the Value interface for representing sun time values.
 * This class stores a ZonedDateTime value representing a specific time,
 * typically the sunrise or sunset time.
 */
public class SunTimeValue implements ValueObject<ZonedDateTime> {

    /**
     * The ZonedDateTime value representing the sun time.
     */
    private final ZonedDateTime sunTimeValue;

    /**
     * Constructs a SunTimeValue with the provided ZonedDateTime.
     *
     * @param sunTimeValue The ZonedDateTime value to be stored.
     * @throws IllegalArgumentException If the provided sunTimeValue is null.
     */
    public SunTimeValue(ZonedDateTime sunTimeValue) {
        if (sunTimeValue == null) {
            throw new IllegalArgumentException("Invalid sun time value");
        }
        this.sunTimeValue = sunTimeValue;
    }

    /**
     * Retrieves the ZonedDateTime value stored in this SunTimeValue.
     *
     * @return The ZonedDateTime value representing the sun time.
     */
    @Override
    public ZonedDateTime getValue() {
        return this.sunTimeValue;
    }
}