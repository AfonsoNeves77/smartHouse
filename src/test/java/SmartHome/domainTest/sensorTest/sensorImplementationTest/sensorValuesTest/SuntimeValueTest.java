package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.SunTimeValue;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the SunTimeValue class, which represents sun time values
 * using ZonedDateTime.
 */
public class SuntimeValueTest {

    /**
     * Verifies that attempting to create a SunTimeValue with a null ZonedDateTime
     * results in an InstantiationException with the expected error message.
     */
    @Test
    void nullZoneDateTime_ShouldReturnInstantiationException() {
        // Arrange
        ZonedDateTime reading = null;
        String expectedErrorMessage = "Invalid sun time value";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SunTimeValue(reading));
        String resultErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, resultErrorMessage);
    }

    /**
     * Verifies that the getValue method of SunTimeValue returns the ZonedDateTime
     * value passed to the constructor.
     *
     * @throws InstantiationException If there is an issue creating the SunTimeValue instance.
     */
    @Test
    void getValue_ShouldReturnPassedToConstructorValue() throws InstantiationException {
        // Arrange
        ZonedDateTime reading = ZonedDateTime.now();

        // Act
        SunTimeValue sunTimeValue = new SunTimeValue(reading);
        ZonedDateTime result = sunTimeValue.getValue();

        // Assert
        assertEquals(reading, result);
    }

    /**
     * Verifies that the getValueAsString method of SunTimeValue returns the
     * string representation of the ZonedDateTime value passed to the constructor.
     *
     * @throws InstantiationException If there is an issue creating the SunTimeValue instance.
     */
    @Test
    void getValueAsString() throws InstantiationException {
        // Arrange
        ZonedDateTime reading = ZonedDateTime.now();
        String expected = reading.toString();

        // Act
        SunTimeValue sunTimeValue = new SunTimeValue(reading);
        String result = sunTimeValue.getValueAsString();

        // Assert
        assertEquals(expected, result);
    }
}

