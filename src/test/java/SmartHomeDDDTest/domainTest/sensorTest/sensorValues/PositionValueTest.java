package SmartHomeDDDTest.domainTest.sensorTest.sensorValues;

import SmartHomeDDD.domain.sensor.sensorValues.PositionValue;
import SmartHomeDDD.domain.sensor.sensorValues.SwitchValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PositionValueTest {

    /**
     * Given a reading below 0, the constructor throws IllegalArgumentException.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfReadingIsBelow0() {
        //Arrange
        int reading = -1;
        String expected = "Invalid Position Value, has to be between 0 and 100";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     *  Given a reading above 100, the constructor throws IllegalArgumentException.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfReadingIsAbove100() {
        //Arrange
        int reading = 101;
        String expected = "Invalid Position Value, has to be between 0 and 100";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Given a reading within 0 and 100, the constructor creates a PositionValue object and the value
     * is successfully retrieved through the getValue method.
     */
    @Test
    void getValue_returnsSuccessfullyWhenReadingIsWithin0And100() {
        //Arrange
        int reading = 20;
        int expected = 20;
        //Act
        PositionValue value = new PositionValue(reading);
        //Assert
        assertEquals(expected, value.getValue());
    }
}
