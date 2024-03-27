package SmartHomeDDDTest.domainTest.sensorTest.sensorValues;

import SmartHomeDDD.domain.sensor.sensorValues.TemperatureValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TemperatureValueTest {

    /**
     * Test if the constructor throws an exception when the reading is null.
     */
    @Test
    void whenReadingIsNull_thenThrowsInstantiationException(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new TemperatureValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test if the constructor throws an exception when the reading is empty.
     */
    @Test
    void whenReadingIsEmpty_thenThrowsInstantiationException(){
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new TemperatureValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test if the constructor throws an exception when the reading is in invalid format.
     */
    @Test
    void whenReadingIsInInvalidFormat_thenThrowsInstantiationException(){
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new TemperatureValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests if the constructor returns the correct value when the reading is positive.
     */
    @Test
    void whenReadingIsPositive_thenItIsReturned() throws InstantiationException {
        //Arrange
        String doubleValue = "3.0";
        Double expected = 3.0;

        //Act
        TemperatureValue value = new TemperatureValue(doubleValue);

        //Assert
        assertEquals(expected,value.getValue());
    }

    /**
     *Tests if the constructor returns the correct value when the reading is negative.
     */
    @Test
    void whenReadingIsNegative_thenItIsReturned() throws InstantiationException {
        //Arrange
        String doubleValue = "-3.0";
        Double expected = -3.0;

        //Act
        TemperatureValue value = new TemperatureValue(doubleValue);

        //Assert
        assertEquals(expected,value.getValue());
    }
}