package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.InstantPowerConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class InstantPowerConsumptionValueTest {

    @Test
    void constructor_throwsInstantiationExceptionIfReadingNull(){
        //Arrange
        String reading = null;
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new InstantPowerConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingEmpty() {
        //Arrange
        String reading = " ";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new InstantPowerConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_throwsInstantiationExceptionIfReadingBelowBound(){
        //Arrange
        String reading = "-1.1";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new InstantPowerConsumptionValue(reading));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_SuccessfulParseIfStringInt() throws InstantiationException {
        //Arrange
        String reading = "1";
        double expected = 1.0;

        //Act
        InstantPowerConsumptionValue value = new InstantPowerConsumptionValue(reading);
        double result = value.getValue();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void constructor_SuccessIfReadingLowerBound() throws InstantiationException {
        //Arrange
        String reading = "0.0";
        double expected = 0.0;

        //Act
        InstantPowerConsumptionValue value = new InstantPowerConsumptionValue(reading);
        double result = value.getValue();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValue_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String reading = "2323351.123412";
        double expected = 2323351.123412;

        //Act
        InstantPowerConsumptionValue value = new InstantPowerConsumptionValue(reading);
        double result = value.getValue();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValueAsString_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String reading = "1.0";
        InstantPowerConsumptionValue value = new InstantPowerConsumptionValue(reading);
        String expected = "1.0";
        //Act
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
}
