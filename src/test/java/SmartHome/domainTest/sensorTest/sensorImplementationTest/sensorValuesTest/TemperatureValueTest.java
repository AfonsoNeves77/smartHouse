package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.TemperatureValue;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class TemperatureValueTest {
    @Test
    void getValue_returnsSuccessfuly(){
        //Arrange
        double doubleValue = 1.1;
        TemperatureValue value = new TemperatureValue(doubleValue);
        //Act
        double result = value.getValue();
        //Assert
        assertEquals(doubleValue,result);
    }

    @Test
    void getValueAsString_SuccessfulyConvertsToString(){
        //Arrange
        double doubleValue = 1.1;
        TemperatureValue value = new TemperatureValue(doubleValue);
        String expected = "1.1";
        //Act
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
}
