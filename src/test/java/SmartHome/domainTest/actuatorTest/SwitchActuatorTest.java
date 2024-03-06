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

    @BeforeEach
    void setup() throws InstantiationException {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        switchActuator = new SwitchActuator("Switch", mockSimHardwareAct);
    }

    @Test
    void testSwitchActuator_Constructor() {
        //arrange
        String actuatorName = "Switch";

        //act done in BeforeEach

        //assert
        assertEquals(actuatorName, switchActuator.getName());
    }
    @Test
    void testSwitchActuator_GetState() {
        //arrange
        String state = "Off";

        //act done in BeforeEach
        //assert
        assertEquals(state, switchActuator.getState());
    }

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


    @Test
    void testExecuteCommand_InvalidCommand_ReturnsFalse() {

        boolean result = switchActuator.executeCommand("Invalid");

        assertFalse(result);
    }

    @Test
    void testExecuteCommand_ValidCommandButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(false);

        boolean result = switchActuator.executeCommand("On");

        assertFalse(result);
    }
    @Test
    void test_GetName() {
        String expected = "Switch";
        String result = switchActuator.getName();
        assertEquals(expected, result);
    }
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        boolean result = switchActuator.executeCommand("On");

        assertTrue(result);
    }
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_StateIsUpdated() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        switchActuator.executeCommand("On");

        String expected = "On";
        String result = switchActuator.getState();

        assertEquals(expected, result);
    }
    @Test
    void test_ValidCommandAndExecutionFails_StateIsNotUpdated() {

        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(false);
        switchActuator.executeCommand("On");

        String expected = "Off";
        String result = switchActuator.getState();

        assertEquals(expected, result);
    }
}