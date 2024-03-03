package SmartHome.domain.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionValueTest {

    @Test
    void constructorThrowsExceptionIfValueAboveRange() {
        // Arrange
        int intValue = 101;
        String expected = "Invalid reading";
        // Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new PositionValue(intValue));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void constructorThrowsExceptionIfValueBelowRange() {
        // Arrange
        int intValue = -1;
        String expected = "Invalid reading";
        // Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new PositionValue(intValue));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected, result);
    }

    @Test
    void constructorSuccess() throws InstantiationException {
        // Arrange
        int intValue = 1;
        // Act
        PositionValue value = new PositionValue(intValue);
        // Assert
        assertEquals(intValue, value.getValue());
    }

    @Test
    void constructorSuccessLowerLimitEqualsZero() throws InstantiationException {
        // Arrange
        int intValue = 0;
        // Act
        PositionValue value = new PositionValue(intValue);
        // Assert
        assertEquals(intValue, value.getValue());
    }

    @Test
    void constructorSuccessUpperLimitEqualsHundred() throws InstantiationException {
        // Arrange
        int intValue = 100;
        // Act
        PositionValue value = new PositionValue(intValue);
        // Assert
        assertEquals(intValue, value.getValue());
    }

    @Test
    void getValueReturnsSuccessfuly() throws InstantiationException {
        // Arrange
        int intValue = 1;
        PositionValue value = new PositionValue(intValue);
        // Act
        int result = value.getValue();
        // Assert
        assertEquals(intValue, result);
    }

    @Test
    void getValueSuccessfullyConvertsToString() throws InstantiationException {
        // Arrange
        int intValue = 1;
        PositionValue value = new PositionValue(intValue);
        String expected = "1";
        // Act
        String result = value.getValueAsString();
        // Assert
        assertEquals(expected, result);
    }

}