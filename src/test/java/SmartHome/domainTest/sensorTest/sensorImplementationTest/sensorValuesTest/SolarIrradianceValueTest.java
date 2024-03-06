package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.SolarIrradianceValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * SolarIrradianceValueTest class for testing SolarIrradianceValue
 */

public class SolarIrradianceValueTest {

    /**
     * Testing the getValue method
     * Successfully returns value
     * @throws InstantiationException if the SolarIrradianceValue object cannot be created
     */

    @Test
    void getValue_SuccessCase() throws InstantiationException {
//        Arrange
        String reading = "10";
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(reading);
        int expected = 10;
//        Act
        int result = solarIrradianceValue.getValue();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getValue method when reading is out of range
     */

    @Test
    void getValue_ReadingOutOfRange() {
//        Arrange
        String reading = "-10";
        String expected = "Invalid reading";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SolarIrradianceValue(reading));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getValue method when reading equals zero
     * @throws InstantiationException if the SolarIrradianceValue object cannot be created
     */

    @Test
    void getValue_ReadingEqualsZero() throws InstantiationException {
//        Arrange
        String reading = "0";
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(reading);
        int expected = 0;
//        Act
        int result = solarIrradianceValue.getValue();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getValueAsString method
     * Successfully returns value as a string
     * @throws InstantiationException if the SolarIrradianceValue object cannot be created
     */

    @Test
    void getValueAsString_SuccessCase() throws InstantiationException {
//        Arrange
        String reading = "10";
        SolarIrradianceValue solarIrradianceValue = new SolarIrradianceValue(reading);
        String expected = "10";
//        Act
        String result = solarIrradianceValue.getValueAsString();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getValueAsString method when reading is invalid
     */

    @Test
    void getValue_InvalidReading() {
//        Arrange
        String reading = " ";
        String expected = "Invalid reading";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SolarIrradianceValue(reading));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getValue method when reading is null
     */

    @Test
    void getValue_NullReading() {
//        Arrange
        String reading = null;
        String expected = "Invalid reading";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SolarIrradianceValue(reading));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }
}