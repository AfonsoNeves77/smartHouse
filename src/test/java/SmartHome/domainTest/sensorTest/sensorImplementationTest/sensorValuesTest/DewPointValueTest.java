package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DewPointValueTest {
    @Test
    void getValue_returnsSuccessfuly(){
        //Arrange
        double doubleValue = 3;
        //Act
        DewPointValue value = new DewPointValue(doubleValue);
        //Assert
        assertEquals(doubleValue,value.getValue());
    }

    @Test
    void getValue_SuccessfulyConvertsToString(){
        //Arrange
        double doubleValue = 1.1;
        //Act
        DewPointValue value = new DewPointValue(doubleValue);
        String result = value.getValueAsString();
        String expected = "1.1";
        //Assert
        assertEquals(expected,result);
    }
}
