package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DewPointValueTest {

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingEmpty(){
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingUnableToParse(){
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new DewPointValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValue_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String doubleValue = "3.0";
        double expected = 3.0;
        //Act
        DewPointValue value = new DewPointValue(doubleValue);
        //Assert
        assertEquals(expected,value.getValue());
    }

    @Test
    void getValue_SuccessfullyConvertsToString() throws InstantiationException {
        //Arrange
        String doubleValue = "1.1";
        //Act
        DewPointValue value = new DewPointValue(doubleValue);
        String result = value.getValueAsString();
        String expected = "1.1";
        //Assert
        assertEquals(expected,result);
    }
}
