package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.AveragePowerConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AveragePowerConsumptionValueTest {

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