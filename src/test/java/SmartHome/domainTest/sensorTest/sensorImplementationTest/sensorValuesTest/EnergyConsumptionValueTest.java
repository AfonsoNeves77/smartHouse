package SmartHome.domainTest.sensorTest.sensorImplementationTest.sensorValuesTest;

import SmartHome.domain.sensor.sensorImplementation.EnergyConsumptionSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.EnergyConsumptionValue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EnergyConsumptionValueTest {
    @Test
    void averagePowerConsumptionValueConstructor_throwsExceptionIfValueIsNotANumber(){
        //Arrange
        String value = "Not a number";
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(value));
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
        Exception exception = assertThrows(InstantiationException.class, () -> new EnergyConsumptionValue(value));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void averagePowerConsumptionValueConstructor_returnsSuccessfully() throws InstantiationException {
        //Arrange
        String value = "50";
        EnergyConsumptionValue energyPowerConsumptionValue = new EnergyConsumptionValue(value);
        double expected = 50;

        //Act
        double result = energyPowerConsumptionValue.getValue();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getValueAsString_SuccessfullyConvertsToString() throws InstantiationException {
        //Arrange
        String value = "50";
        EnergyConsumptionValue averagePowerConsumptionValue = new EnergyConsumptionValue(value);
        String expected = "50";

        //Act
        String result = averagePowerConsumptionValue.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}
