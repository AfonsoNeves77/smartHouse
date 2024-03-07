package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.actuator.IntSetActuator;
import SmartHome.domain.actuator.SimHardwareAct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This class is used to test the IntSetActuator class.
 */

public class IntSetActuatorTest {

//    ### INTEGRATION TESTS ###

    /**
     * This method is used to test the IntSetActuator constructor.
     * @throws InstantiationException if the actuator name is null or empty or the simHardwareAct object is null
     */

    @Test
    void testIntSetActuator_Constructor() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
//        Act
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
//        Assert
        assertEquals(actuatorName, intSetActuator.getName());
    }

    /**
     * This method is used to test the IntSetActuator constructor with a null name.
     * @throws InstantiationException if the actuator name is null or empty or the simHardwareAct object is null
     */

    @Test
    void testIntSetActuator_nullName() {
//        Arrange
        String actuatorName = null;
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        String expected = "Invalid parameters for Integer Range Actuator.";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new IntSetActuator(actuatorName, simHardwareActMock);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * This method is used to test the IntSetActuator constructor with an empty name.
     * @throws InstantiationException if the actuator name is null or empty or the simHardwareAct object is null
     */

    @Test
    void testIntSetActuator_emptyName() {
//        Arrange
        String actuatorName = " ";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        String expected = "Invalid parameters for Integer Range Actuator.";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new IntSetActuator(actuatorName, simHardwareActMock);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * This method is used to test the executeCommand method when the limits are not set.
     * @throws InstantiationException if the actuator name is null or empty or the simHardwareAct object is null
     */
    @Test
    void testIntSetActuator_LimitsNotSet() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 5;
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertFalse(result);
    }

    /**
     * This method is used to test the setLimits method when the limits are inverted, meaning the upper limit is less than the lower limit.
     * @throws IllegalArgumentException if the upper limit is less than or equal to the lower limit
     */

    @Test
    void testIntSetActuator_setLimitsInverted() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int lowerLimit = 15;
        int upperLimit = 5;
        String expectedMessage = "Upper limit can't be less than or equal to lower limit.";
//        Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            intSetActuator.setLimits(lowerLimit, upperLimit);
        });
        String resultMessage = exception.getMessage();
//        Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * This method is used to test the setLimits method when the limits are equal.
     * @throws IllegalArgumentException if the upper limit is less than or equal to the lower limit
     */

    @Test
    void testIntSetActuator_setLimitsToEqualValues() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int lowerLimit = 15;
        int upperLimit = 15;
        String expectedMessage = "Upper limit can't be less than or equal to lower limit.";
//        Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            intSetActuator.setLimits(lowerLimit, upperLimit);
        });
        String resultMessage = exception.getMessage();
//        Assert
        assertEquals(expectedMessage, resultMessage);
    }

    /**
     * This test is used to check if the value is updated when the executeCommand method is called.
     */

    @Test
    void testIntSetActuator_ReturnFalseIfValueNotUpdated() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 15;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(false);
        int lowerLimit = 5;
        int upperLimit = 30;
        intSetActuator.setLimits(lowerLimit, upperLimit);
        int expected = 5;
//        Act
        intSetActuator.executeCommand(value);
        int result = intSetActuator.getValue();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * This test is used to test the executeCommand method when the value is invalid.
     */

    @Test
    void testIntSetActuator_testExecuteCommand_SetInvalidValue() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 15;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(true);
        int lowerLimit = 5;
        int upperLimit = 10;
        intSetActuator.setLimits(lowerLimit, upperLimit);
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertFalse(result);
    }

    /**
     * This test is used to test the executeCommand method when the value is equal to the lower limit.
     */

    @Test
    void testIntSetActuator_SetValueEqualToLowerLimit() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 5;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(true);
        int lowerLimit = 5;
        int upperLimit = 30;
        intSetActuator.setLimits(lowerLimit, upperLimit);
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertTrue(result);
    }

    /**
     * This test is used to test the executeCommand method when the value is equal to the upper limit.
     */

    @Test
    void testIntSetActuator_SetValueEqualToUpperLimit() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 30;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(true);
        int lowerLimit = 5;
        int upperLimit = 30;
        intSetActuator.setLimits(lowerLimit, upperLimit);
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertTrue(result);
    }

    /**
     * This test is used to test the executeCommand method when the value is within the limits.
     */

    @Test
    void testIntSetActuator_testExecuteCommand_Success() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 15;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(true);
        int lowerLimit = 5;
        int upperLimit = 30;
        intSetActuator.setLimits(lowerLimit, upperLimit);
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertTrue(result);
    }

    /**
     * This test is used to test the executeCommand method when the value is not set.
     */

    @Test
    void testIntSetActuator_testExecuteCommand_ReturnFalse() throws InstantiationException {
//        Arrange
        String actuatorName = "IntSetActuator";
        SimHardwareAct simHardwareActMock = mock(SimHardwareAct.class);
        IntSetActuator intSetActuator = new IntSetActuator(actuatorName, simHardwareActMock);
        int value = 15;
        when(simHardwareActMock.executeIntegerCommandSim(value)).thenReturn(false);
        int lowerLimit = 5;
        int upperLimit = 30;
        intSetActuator.setLimits(lowerLimit, upperLimit);
//        Act
        boolean result = intSetActuator.executeCommand(value);
//        Assert
        assertFalse(result);
    }
}
