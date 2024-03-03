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
    public void setup() {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        switchActuator = new SwitchActuator("Switch", "Off");
    }

    @Test
    void testSwitchActuator() {
        //arrange
        String actuatorName = "Random Actuator";
        String state = "On";

        //act
        SwitchActuator switchActuator = new SwitchActuator(actuatorName, state);

        //assert
        assertEquals(actuatorName, switchActuator.getName());
    }

    @Test
    void testSwitchActuator_nullActuatorName() {
        //arrange
        String actuatorName = null;
        String state = "On";
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(actuatorName, state);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testSwitchActuator_nullState() {
        //arrange
        String actuatorName = "Random Actuator";
        String state = null;
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(actuatorName, state);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testSwitchActuator_emptyName() {
        //arrange
        String actuatorName = " ";
        String state = "Off";
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(actuatorName, state);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }

    @Test
    void testSwitchActuator_emptyState() {
        //arrange
        String actuatorName = "Random Actuator";
        String state = " ";
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new SwitchActuator(actuatorName, state);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }


    @Test
    public void testExecuteCommand_InvalidCommand_ReturnsFalse() {
        boolean result = switchActuator.executeCommand("Invalid", mockSimHardwareAct);

        assertFalse(result);
    }

    @Test
    public void testExecuteCommand_ValidCommandButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(false);

        boolean result = switchActuator.executeCommand("On", mockSimHardwareAct);

        assertFalse(result);
    }
    @Test
    public void testGetName() {
        String expected = "Switch";
        String result = switchActuator.getName();
        assertEquals(expected, result);
    }
    @Test
    public void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        boolean result = switchActuator.executeCommand("On", mockSimHardwareAct);

        assertTrue(result);
    }
    @Test
    public void testExecuteCommand_ValidCommandAndExecutionSucceeds_StateIsUpdated() {
        when(mockSimHardwareAct.executeCommandSim("On")).thenReturn(true);

        switchActuator.executeCommand("On", mockSimHardwareAct);

        String expected = "On";
        String result = switchActuator.getState();

        assertEquals(expected, result);
    }
}