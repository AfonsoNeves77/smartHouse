package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DewPointValueTest {

    /**
     * Test of the constructor of the class DewPointSensor. It should throw an exception if the value is null.
     */

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
    /**
     * Test of the constructor of the class DewPointSensor. It should throw an exception if the value is empty.
     */
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
    /**
     * Test of the constructor of the class DewPointSensor. It should throw an exception if the value is not a number.
     */
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

    /**
     * Test of the getValue of the class DewPointValue. It should return successfully if the value is valid.
     */

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
    /**
     * Test of the method getValueAsString of the class DewPointValue. It should return the value as a string.
     */
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
