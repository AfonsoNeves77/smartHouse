package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionValueTest {

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull() throws InstantiationException {
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingEmpty() throws InstantiationException {
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingUnableToParse() throws InstantiationException {
        //Arrange
        String reading = "this will fail";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingAboveBounds() throws InstantiationException {
        //Arrange
        String reading = "101";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingBelowBounds() throws InstantiationException {
        //Arrange
        String reading = "-1";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new PositionValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValue_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String doubleValue = "3";
        int expected = 3;
        //Act
        PositionValue value = new PositionValue(doubleValue);
        //Assert
        assertEquals(expected,value.getValue());
    }

    @Test
    void getValue_SuccessfullyConvertsToString() throws InstantiationException {
        //Arrange
        String doubleValue = "1";
        //Act
        PositionValue value = new PositionValue(doubleValue);
        String result = value.getValueAsString();
        String expected = "1";
        //Assert
        assertEquals(expected,result);
    }
}