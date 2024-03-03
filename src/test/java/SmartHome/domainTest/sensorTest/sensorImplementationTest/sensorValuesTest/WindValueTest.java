package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WindValueTest {
    @Test
    void constructor_throwsExceptionIfWindSpeedNegative(){
        //Arrange
        int windspeed = -1;
        String windDirection = "N";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(windspeed,windDirection));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsExceptionIfDirectionInvalid(){
        //Arrange
        int windspeed = 59;
        String windDirection = "I will fail";
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindValue(windspeed,windDirection));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValue_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        int windspeed = 50;
        String windDirection = "N";
        WindValue windValue = new WindValue(windspeed,windDirection);
        int expected = 50;
        //Act
        int result = windValue.getValue();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getWindDirection_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        int windspeed = 50;
        String windDirection = "N";
        WindValue windValue = new WindValue(windspeed,windDirection);
        String expected = "N";
        //Act
        String result = windValue.getWindDirection();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValueAsString_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        int windspeed = 50;
        String windDirection = "N";
        WindValue windValue = new WindValue(windspeed,windDirection);
        String expected = "50-N";
        //Act
        String result = windValue.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
}
