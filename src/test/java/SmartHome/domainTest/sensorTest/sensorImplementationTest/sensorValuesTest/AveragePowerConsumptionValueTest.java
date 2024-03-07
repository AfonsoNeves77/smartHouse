package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.AveragePowerConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AveragePowerConsumptionValueTest {
    /**
     * Test of the constructor of the class AveragePowerConsumptionValue. It should throw an exception if the value is not a number.
     */
    @Test
    void averagePowerConsumptionValueConstructor_throwsExceptionIfValueIsNotANumber(){
        //Arrange
        String value = "Not a number";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new AveragePowerConsumptionValue(value));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test of the constructor of the class AveragePowerConsumptionValue. It should throw an exception if the value is negative.
     */
    @Test
    void averagePowerConsumptionValueConstructor_throwsExceptionIfValueIsNegative(){
        //Arrange
        String value = "-1";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new AveragePowerConsumptionValue(value));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test of the constructor of the class AveragePowerConsumptionValue. It should return successfully if the value is valid.
     */
    @Test
    void averagePowerConsumptionValueConstructor_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String value = "50";
        AveragePowerConsumptionValue averagePowerConsumptionValue = new AveragePowerConsumptionValue(value);
        int expected = 50;

        //Act
        int result = averagePowerConsumptionValue.getValue();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test of the method getValueAsString of the class AveragePowerConsumptionValue. It should return the value as a string.
     */
    @Test
    void getValueAsString_SuccessfullyConvertsToString() throws InstantiationException {
        //Arrange
        String value = "50";
        AveragePowerConsumptionValue averagePowerConsumptionValue = new AveragePowerConsumptionValue(value);
        String expected = "50";

        //Act
        String result = averagePowerConsumptionValue.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}