package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

public class InstantPowerConsumptionValue implements Value<Integer>{
    private final int primitiveValue;

    /**
     * Constructor for Instant Power Consumption Value. The instantiation ensures the reading is valid,
     * throwing InstantiationException otherwise.
     * @param reading String containing the reading to be converted to value.
     * @throws InstantiationException If unable to create value.
     */
    public InstantPowerConsumptionValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * Validates the reading. Ensures the reading is not null or empty, that it is parseable, and when
     * parsed, the instant power consumption value (in watts) is above 0.
     * @param reading Reading to validate
     * @return True or False
     */
    private boolean isReadingValid(String reading){
        double valueOfReading;
        try{
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e){
            return false;
        }
        return valueOfReading >= 0;
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type. Instant power consumption (in watts) is the
     * primary value stored unto this value, so in order to utilize the Value interface, only this first int will be
     * returned by getValue.
     * @return Encapsulated primitive value.
     */
    @Override
    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * Returns the primitive value after conversion to String format.
     * @return Encapsulated primitive value as String.
     */
    @Override
    public String getValueAsString() {
        return this.primitiveValue + "";
    }
}
