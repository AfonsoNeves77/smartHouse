package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.actuator.BlindRollerActuator;
import SmartHome.domain.actuator.SimHardwareAct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BlindRollerActuatorTest {
    private BlindRollerActuator blindRollerActuator;
    private SimHardwareAct mockSimHardwareAct;

    @BeforeEach
    void setup() throws InstantiationException {
        mockSimHardwareAct = mock(SimHardwareAct.class);
        blindRollerActuator = new BlindRollerActuator("Blind Roller", mockSimHardwareAct);
    }

    @Test
    void testSwitchActuator_Constructor() {
        //arrange
        String actuatorName = "Blind Roller";

        //act done in BeforeEach

        //assert
        assertEquals(actuatorName, blindRollerActuator.getName());
    }

    @Test
    void testSwitchActuator_nullActuatorName() {
        //arrange
        String actuatorName = null;
        String expected = "Invalid name for Actuator.";

        //act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new BlindRollerActuator(actuatorName, mockSimHardwareAct);
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
            new BlindRollerActuator(actuatorName, mockSimHardwareAct);
        });
        String result = exception.getMessage();

        //assert
        assertEquals(expected, result);
    }



    @Test
    void testExecuteCommand_BorderUpperPosition_ReturnsTrue() {

        when(mockSimHardwareAct.executeIntegerCommandSim(100)).thenReturn(true);
        boolean result = blindRollerActuator.executeRollerCommand(100);

        assertTrue(result);
    }
    @Test
    void testExecuteCommand_BorderLowerPosition_ReturnsTrue() {

        when(mockSimHardwareAct.executeIntegerCommandSim(0)).thenReturn(true);
        boolean result = blindRollerActuator.executeRollerCommand(0);

        assertTrue(result);
    }
    @Test
    void testExecuteCommand_InvalidPositionUpper_ReturnsFalse() {

        boolean result = blindRollerActuator.executeRollerCommand(101);

        assertFalse(result);
    }

    @Test
    void testExecuteCommand_InvalidPositionLower_ReturnsFalse() {

        boolean result = blindRollerActuator.executeRollerCommand(-1);

        assertFalse(result);
    }

    @Test
    void testExecuteCommand_ValidCommandButExecutionFails_ReturnsFalse() {
        when(mockSimHardwareAct.executeIntegerCommandSim(40)).thenReturn(false);

        boolean result = blindRollerActuator.executeRollerCommand(40);

        assertFalse(result);
    }
    @Test
    void test_GetName() {
        String expected = "Blind Roller";
        String result = blindRollerActuator.getName();
        assertEquals(expected, result);
    }
    @Test
    void testExecuteCommand_ValidCommandAndExecutionSucceeds_ReturnsTrue() {
        when(mockSimHardwareAct.executeIntegerCommandSim(40)).thenReturn(true);

        boolean result = blindRollerActuator.executeRollerCommand(40);

        assertTrue(result);
    }
}