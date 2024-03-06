package SmartHome.domain.sensor.sensorImplementation.sensorValues;

public class SwitchValue implements Value<String>{
    private final String primitiveValue;

    /**
     * Constructor for switch value. It can only be instantiated if the receiving parameter is within
     * the specified interval.
     * @param reading Value received by an external source, in line for conversion.
     * @throws InstantiationException If parameters out of bounds.
     */
    public SwitchValue(String reading) throws InstantiationException {
        if(!isReadingValid(reading)){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = reading;
    }

    /**
     * Returns the encapsulated primitive value, maintaining its type.
     * @return Encapsulated primitive value.
     */
    public String getValue() {
        return this.primitiveValue;
    }

    /**
     * Returns the primitive value after conversion to String format.
     * @return Encapsulated primitive value as String.
     */
    public String getValueAsString(){
        return primitiveValue;
    }

    private boolean isReadingValid(String reading) {
        if (reading == null) return false;
        return reading.trim().equalsIgnoreCase("On") || reading.trim().equalsIgnoreCase("Off");
    }
}
