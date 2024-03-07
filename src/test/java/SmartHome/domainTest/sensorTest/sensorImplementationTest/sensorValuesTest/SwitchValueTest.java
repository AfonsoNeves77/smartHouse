package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.SwitchValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SwitchValueTest {

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull() throws InstantiationException {
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SwitchValue(reading));
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
        Exception exception = assertThrows(InstantiationException.class, () -> new SwitchValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNotOnNorOffNorNull() throws InstantiationException {
        //Arrange
        String reading = "this will fail because it is not On or Off";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SwitchValue(reading));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void getValue_returnsSuccessfullyWhenReadingIsOn() throws InstantiationException {
        //Arrange
        String reading = "On";
        String expected = "On";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected,value.getValue());
    }

    @Test
    void getValue_returnsSuccessfullyWhenReadingIsOff() throws InstantiationException {
        //Arrange
        String reading = "Off";
        String expected = "Off";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected,value.getValue());
    }

    @Test
    void getValueAsString_returnsSuccessfullyWhenReadingIsOff() throws InstantiationException {
        //Arrange
        String reading = "Off";
        String expected = "Off";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected,value.getValueAsString());
    }

    @Test
    void getValueAsString_returnsSuccessfullyWhenReadingIsOn() throws InstantiationException {
        //Arrange
        String reading = "On";
        String expected = "On";
        //Act
        SwitchValue value = new SwitchValue(reading);
        //Assert
        assertEquals(expected,value.getValueAsString());
    }

}
