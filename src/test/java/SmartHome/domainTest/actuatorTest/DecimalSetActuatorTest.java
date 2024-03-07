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

    /**
     * Setup for the tests
     * @throws InstantiationException if the actuator name is invalid
     */

    @BeforeEach
    void setup() throws InstantiationException {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        decimalSetActuator = new DecimalSetActuator("Decimal", mockSimHardwareAct);
    }

//    ### ISOLATION TESTS ###

    /**
     * Test for the constructor of the actuator
     */

    @Test
    void testDecimalSetActuator_Constructor() {
        //arrange
        String actuatorName = "Decimal";

        //act done in BeforeEach

        //assert
        assertEquals(actuatorName, decimalSetActuator.getName());
    }

    /**
     * Test for getValue method of the actuator
     */

    @Test
    void testDecimalSetActuator_GetValue() {
        //arrange
        double value = 0;
        //act done in BeforeEach
        //assert
        assertEquals(value, decimalSetActuator.getValue());
    }

    /**
     * Test for the constructor of the actuator with null name
     */

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

    /**
     * Test for the constructor of the actuator with empty name
     */

    @Test
    void testDecimalSetActuator_emptyActuatorName() {
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

    /**
     * Test for the setLimits method of the actuator
     */

    @Test
    void testDecimalSetActuator_SetLimits_LowerLimitHigherThanUpperLimit_ThrowsException() {
        //arrange
        String expected = "Upper Limit has to be Higher or Equal than Lower Limit";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            decimalSetActuator.setLimits(20.0, 10.0, 10);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    /**
     * Test for the executeCommand method of the actuator with invalid value
     */

    @Test
    void testExecuteCommand_InvalidValue_ReturnsFalse() {
        decimalSetActuator.setLimits(5.0, 10.0, 10);
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);
        boolean result = decimalSetActuator.executeCommand(15);

        assertFalse(result);
    }

    /**
     * Test for the executeCommand method of the actuator with value when limits are not set
     */

    @Test
    void testExecuteCommand_ValidValueButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeDecimalCommandSim(10)).thenReturn(false);

        boolean result = decimalSetActuator.executeCommand(10);

        assertFalse(result);
    }

    /**
     * Test for the executeCommand method of the actuator with valid value when limits are valid
     */

    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);
        decimalSetActuator.setLimits(15.0, 15.0, 10);
        boolean result = decimalSetActuator.executeCommand(15);

        assertTrue(result);
    }

    /**
     * Test for the executeCommand method of the actuator with valid value, check if value updated
     */

    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ValueIsUpdated() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(true);

        decimalSetActuator.setLimits(10.0, 20.0, 10);
        decimalSetActuator.executeCommand(15);

        double expected = 15;
        double result = decimalSetActuator.getValue();

        assertEquals(expected, result);
    }

    /**
     * Test for the executeCommand method of the actuator when validation of value unsuccessful
     */

    @Test
    void testExecuteCommand_ValidationOfNewValueUnsuccessful() {
        decimalSetActuator.setLimits(10.0, 20.0, 10);
        when(mockSimHardwareAct.executeDecimalCommandSim(15.32)).thenReturn(true);

        boolean result = decimalSetActuator.executeCommand(15.32);

        assertFalse(result);
    }

    /**
     * Test for the executeCommand method of the actuator when value is not within the predefined decimal precision
     */

    @Test
    void testExecuteCommand_ValueIsNotWithinThePredefinedDecimalPrecision_Insuccess() {
        when(mockSimHardwareAct.executeDecimalCommandSim(15.32)).thenReturn(true);

        decimalSetActuator.setLimits(10.0, 20.0, 10);
        boolean result = decimalSetActuator.executeCommand(15.32);

        assertFalse(result);
    }

    /**
     * Test for the executeCommand method of the actuator when value is not updated
     */

    @Test
    void test_ValidCommandAndExecutionFails_ValueIsNotUpdated() {

        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(false);
        decimalSetActuator.setLimits(10.0, 20.0, 10);
        decimalSetActuator.executeCommand(15);

        double expected = 10;
        double result = decimalSetActuator.getValue();

        assertEquals(expected, result);
    }

    /**
     * Test for the executeCommand method of the actuator when execution fails
     */

    @Test
    void test_ValidCommandAndExecutionFails_ReturnFalse() {

        when(mockSimHardwareAct.executeDecimalCommandSim(15)).thenReturn(false);
        decimalSetActuator.setLimits(10.0, 20.0, 10);

        boolean result = decimalSetActuator.executeCommand(15);


        assertFalse(result);
    }
}
