package SmartHome.domainTest.actuatorTest;


import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.actuator.SwitchActuator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SwitchActuatorTest {

    private SwitchActuator switchActuator;
    private SimHardwareAct mockSimHardwareAct;

    /**
     * This method is used to setup the test environment before each test.
     * @throws InstantiationException if an invalid name is provided for the actuator
     */
    @BeforeEach
    void setup() throws InstantiationException {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        switchActuator = new SwitchActuator("Switch", mockSimHardwareAct);
    }

    /**
     * This method is used to test the constructor of the SwitchActuator.
     */
    @Test
    void testSwitchActuator_Constructor() {
        //arrange
        String actuatorName = "Switch";

        //act done in BeforeEach

        //assert
        assertEquals(actuatorName, switchActuator.getName());
    }

    /**
     * This method is used to test the getState method of the SwitchActuator.
     */
    @Test
    void testSwitchActuator_GetState() {
        //arrange
        String state = "Off";

        //act done in BeforeEach
        //assert
        assertEquals(state, switchActuator.getState());
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should throw an exception if the actuatorName is null.
     */
    @Test
    void testSwitchActuator_nullActuatorName() {
        //arrange
        String actuatorName = null;
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new SwitchActuator(actuatorName, mockSimHardwareAct);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should throw an exception if the actuatorName is empty.
     */
    @Test
    void testSwitchActuator_emptyName() {
        //arrange
        String actuatorName = " ";
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new SwitchActuator(actuatorName, mockSimHardwareAct);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should return false if the command.
     */
    @Test
    void testExecuteCommand_InvalidCommand_ReturnsFalse() {

        boolean result = switchActuator.executeCommand("Invalid");

        assertFalse(result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should return false if the execution fails.
     */
    @Test
    void testExecuteCommand_ValidCommandButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(false);

        boolean result = switchActuator.executeCommand("On");

        assertFalse(result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should return true if the execution succeeds.
     */
    @Test
    void test_GetName() {
        String expected = "Switch";
        String result = switchActuator.getName();
        assertEquals(expected, result);
    }
    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should return true if the execution succeeds.
     */
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        boolean result = switchActuator.executeCommand("On");

        assertTrue(result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should update the state if the execution succeeds.
     */
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_StateIsUpdated() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        switchActuator.executeCommand("On");

        String expected = "On";
        String result = switchActuator.getState();

        assertEquals(expected, result);
    }

    /**
     * This method is used to test the executeCommand method of the SwitchActuator. It should not update the state if the execution fails.
     */
    @Test
    void test_ValidCommandAndExecutionFails_StateIsNotUpdated() {

        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(false);
        switchActuator.executeCommand("On");

        String expected = "Off";
        String result = switchActuator.getState();

        assertEquals(expected, result);
    }
}