package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class HumidityValueTest {
    @Test
    void getValue_throwsExceptionIfValueAboveRange(){
        //Arrange
        int intValue = 101;
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new HumidityValue(intValue));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getValue_throwsExceptionIfValueBelowRange(){
        //Arrange
        int intValue = 101;
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new HumidityValue(intValue));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getValue_returnsSuccessfuly() throws InstantiationException {
        //Arrange
        int intValue = 1;
        HumidityValue value = new HumidityValue(intValue);
        //Act
        int result = value.getValue();
        //Assert
        assertEquals(intValue,result);
    }

    @Test
    void getValueAsString_SuccessfulyConvertsToString() throws InstantiationException {
        //Arrange
        int intValue = 1;
        HumidityValue value = new HumidityValue(intValue);
        String expected = "1";
        //Act
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
}
