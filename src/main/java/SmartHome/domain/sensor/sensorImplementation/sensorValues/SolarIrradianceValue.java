package SmartHome.domain.sensor.sensorImplementation.sensorValues;

import static java.lang.Integer.parseInt;

/**
 * SolarIrradianceValue class for SolarIrradianceValue
 * Implements Value<Integer>
 * @see Value
 */

public class SolarIrradianceValue implements Value<Integer> {

    /**
     * primitiveValue int to store the value of the sensor
     */

    private final int primitiveValue;

    /**
     * SolarIrradianceValue constructor
     * @param reading String to store the reading of the sensor
     * @throws InstantiationException if the reading is invalid
     */

    public SolarIrradianceValue(String reading) throws InstantiationException {
        if (!isReadingValid(reading)) {
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = parseInt(reading);
    }

    /**
     * isReadingValid method to check if the reading is valid
     * @param reading String to store the reading of the sensor
     * @return boolean true if the reading is valid, false otherwise
     */

    private boolean isReadingValid(String reading) {
        int valueOfReading;
        try {
            valueOfReading = parseInt(reading);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        return valueOfReading >= 0;
    }

    /**
     * getValue method to get the value of the sensor
     * @return Integer containing the value of the sensor
     */

    public Integer getValue() {
        return this.primitiveValue;
    }

    /**
     * getValueAsString method to get the value of the sensor as a string
     * @return String containing the value of the sensor
     */

    public String getValueAsString() {
        return this.primitiveValue + "";
    }
}
