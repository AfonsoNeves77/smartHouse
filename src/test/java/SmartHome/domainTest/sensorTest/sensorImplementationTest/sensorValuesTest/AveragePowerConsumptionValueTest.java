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
        String value = "-1.57";
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
        String value = "50.5";
        AveragePowerConsumptionValue averagePowerConsumptionValue = new AveragePowerConsumptionValue(value);
        double expected = 50.5;

        //Act
        double result = averagePowerConsumptionValue.getValue();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValueAsString_SuccessfullyConvertsToString() throws InstantiationException {
        //Arrange
        String value = "50.5";
        AveragePowerConsumptionValue averagePowerConsumptionValue = new AveragePowerConsumptionValue(value);
        String expected = "50.5";

        //Act
        String result = averagePowerConsumptionValue.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}