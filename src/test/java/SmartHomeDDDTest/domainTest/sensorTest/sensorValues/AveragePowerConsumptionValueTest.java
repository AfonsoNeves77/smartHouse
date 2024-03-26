package SmartHomeDDDTest.domainTest.sensorTest.sensorValues;

import SmartHomeDDD.domain.sensor.sensorValues.AveragePowerConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for the AveragePowerConsumptionValue value object
 */

public class AveragePowerConsumptionValueTest {

    /**
     * Test to validate the reading of the average power consumption value
     * It should throw an exception if the reading is not a number
     */

    @Test
    void givenValueIsNotANumber_whenAveragePowerConsumptionValueConstructorCalled_thenThrowException() {
//        Arrange
        String reading = "Not a number";
        String expected = "Invalid reading";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AveragePowerConsumptionValue(reading));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to validate the reading of the average power consumption value
     * It should throw an exception if the reading is negative
     */

    @Test
    void givenNegativeValue_whenAveragePowerConsumptionValueConstructorCalled_thenThrowException() {
//        Arrange
        String reading = "-1";
        String expected = "Invalid reading";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AveragePowerConsumptionValue(reading));
//        Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * Test to validate the reading of the average power consumption value
     * It should return the value if the reading is valid
     */

    @Test
    void givenValidValue_whenAveragePowerConsumptionValueConstructorCalled_thenReturnSuccessfully() {
//        Arrange
        String reading = "100";
        AveragePowerConsumptionValue averagePowerConsumptionValue = new AveragePowerConsumptionValue(reading);
        int expected = 100;
//        Act
        int result = averagePowerConsumptionValue.getValue();
//        Assert
        assertEquals(expected, result);
    }
}
