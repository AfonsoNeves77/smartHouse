package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.actuator.DecimalSetActuator;
import SmartHome.domain.actuator.SimHardwareAct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DecimalSetActuatorTest {

    private DecimalSetActuator decimalSetActuator;
    private SimHardwareAct mockSimHardwareAct;

    @BeforeEach
    void setup() throws InstantiationException {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        decimalSetActuator = new DecimalSetActuator("Decimal", mockSimHardwareAct);
    }

    @Test
    void testDecimalSetActuator_Constructor() {
        //arrange
        String actuatorName = "Decimal";

        //act done in BeforeEach

        //assert
        assertEquals(actuatorName, decimalSetActuator.getName());
    }
    @Test
    void testDecimalSetActuator_GetState() {
        //arrange
        double value = 0;
        //act done in BeforeEach
        //assert
        assertEquals(value, decimalSetActuator.getValue());
    }

    @Test
    void testDecimalSetActuator_nullActuatorName() {
        //arrange
        String actuatorName = null;
        String expected = "Invalid parameters for Decimal Range Actuator.";

        //act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new DecimalSetActuator(actuatorName, mockSimHardwareAct);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testDecimalSetActuator_emptyName() {
        //arrange
        String actuatorName = " ";
        String expected = "Invalid parameters for Decimal Range Actuator.";

        //act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new DecimalSetActuator(actuatorName, mockSimHardwareAct);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testDecimalSetActuator_SetLimits() {
        //arrange
        String expected = "Upper Limit has to be Higher or Equal than Lower Limit";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            decimalSetActuator.setLimits(20,10);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }


    @Test
    void testExecuteCommand_InvalidValue_ReturnsFalse() {
        decimalSetActuator.setLimits(5,10);
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);
        boolean result = decimalSetActuator.executeCommand(15);

        assertFalse(result);
    }

    @Test
    void testExecuteCommand_ValidValueButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeDecimalCommandSim(10)).thenReturn(false);

        boolean result = decimalSetActuator.executeCommand(10);

        assertFalse(result);
    }
    @Test
    void test_GetName() {
        String expected = "Decimal";
        String result = decimalSetActuator.getName();
        assertEquals(expected, result);
    }
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);
        decimalSetActuator.setLimits(15,15);
        boolean result = decimalSetActuator.executeCommand(15);

        assertTrue(result);
    }
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ValueIsUpdated() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);

        decimalSetActuator.setLimits(10,20);
        decimalSetActuator.executeCommand(15);

        double expected = 15;
        double result = decimalSetActuator.getValue();

        assertEquals(expected, result);
    }

    @Test
    void testExecuteCommand_ValidationOfNewValueUnsuccessful() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);

        boolean result = decimalSetActuator.executeCommand(15);

        assertFalse(result);
    }

    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ValueIsUpdatedAndRoundedTo3DecimalPoints() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15.35289)).thenReturn(true);

        decimalSetActuator.setLimits(10,20);
        decimalSetActuator.executeCommand(15.35289);

        double expected = 15.353;
        double result = decimalSetActuator.getValue();

        assertEquals(expected, result);
    }

    @Test
    void test_ValidCommandAndExecutionFails_ValueIsNotUpdated() {

        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(false);
        decimalSetActuator.setLimits(10,20);
        decimalSetActuator.executeCommand(15);

        double expected = 10;
        double result = decimalSetActuator.getValue();

        assertEquals(expected, result);
    }

    @Test
    void test_ValidCommandAndExecutionFails_ReturnFalse() {

        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(false);
        decimalSetActuator.setLimits(10,20);

        boolean result = decimalSetActuator.executeCommand(15);


        assertFalse(result);
    }
}
