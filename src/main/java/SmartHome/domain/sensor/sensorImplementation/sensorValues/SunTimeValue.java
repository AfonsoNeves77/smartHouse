package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import java.time.ZonedDateTime;

/**
 * Implementation of the Value interface for representing sun time values.
 * This class stores a ZonedDateTime value representing a specific time,
 * typically the sunrise or sunset time.
 */
public class SunTimeValue implements Value<ZonedDateTime> {

    /**
     * The ZonedDateTime value representing the sun time.
     */
    private final ZonedDateTime sunTimeValue;

    /**
     * Constructs a SunTimeValue with the provided ZonedDateTime.
     *
     * @param sunTimeValue The ZonedDateTime value to be stored.
     * @throws InstantiationException If the provided sunTimeValue is null.
     */
    public SunTimeValue(ZonedDateTime sunTimeValue) throws InstantiationException {
        if (sunTimeValue == null) {
            throw new InstantiationException("Invalid sun time value");
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

    /**
     * Retrieves a string representation of the ZonedDateTime value.
     *
     * @return A string representation of the ZonedDateTime value.
     */

    @Override
    public String getValueAsString() {
        return this.sunTimeValue.toString();
    }

}