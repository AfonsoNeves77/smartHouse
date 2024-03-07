package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WindValueTest {

    /**
     * Ensures the constructor throws exception when reading is null
     */
    @Test
    void constructor_throwsExceptionIfReadingNull(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception when reading is empty
     */
    @Test
    void constructor_throwsExceptionIfReadingEmpty(){
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception when reading does not have a delimiter.
     */
    @Test
    void constructor_throwsExceptionIfReadingWithoutDelimiter(){
        //Arrange
        String reading = "1N";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception if the windspeed is negative.
     */
    @Test
    void constructor_throwsExceptionIfWindSpeedNegative(){
        //Arrange
        String reading = "-1-N";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception if there is no windspeed (No string at the left side of the delimiter).
     */
    @Test
    void constructor_throwsExceptionIfNoWindSpeed(){
        //Arrange
        String reading = "-N";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception if there is no windspeed (No string at the right side of the delimiter).
     */
    @Test
    void constructor_throwsExceptionIfNoWindDirection(){
        //Arrange
        String reading = "1-";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception if there is no windspeed or wind direction (No string at the right
     * or left sides of the delimiter).
     */
    @Test
    void constructor_throwsExceptionIfNoWindSpeedOrDirection(){
        //Arrange
        String reading = "-";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures the constructor throws exception if direction is invalid.
     */
    @Test
    void constructor_throwsExceptionIfDirectionInvalid(){
        //Arrange
        String reading = "1-D";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test ensure getValue successfully returns the integer encapsulated.
     */
    @Test
    void getValue_SuccessfullyReturnsWindSpeed() throws InstantiationException {
        //Arrange
        String reading = "1-N";
        WindValue value = new WindValue(reading);
        int expected = 1;
        //Act
        int result = value.getValue();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test ensure getWindDirection successfully returns the String encapsulated.
     */
    @Test
    void getWindDirection_SuccessfullyReturnsWindDirection() throws InstantiationException {
        //Arrange
        String reading = "1-N";
        WindValue value = new WindValue(reading);
        String expected = "N";
        //Act
        String result = value.getWindDirection();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Ensures get valueAsString successfully returns a concatenation of windSpeed + delimiter + windDirection
     * @throws InstantiationException If invalid value parameters
     */
    @Test
    void getValueAsString_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        String reading = "1-N";
        WindValue value = new WindValue(reading);
        String expected = "1-N";
        //Act
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
}
