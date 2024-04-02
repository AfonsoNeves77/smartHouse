package SmartHomeDDDTest.domainTest.actuatorTest;

import SmartHomeDDD.SimHardwareAct;
import SmartHomeDDD.domain.actuator.IntSetRangeActuator;
import SmartHomeDDD.domain.actuator.RollerBlindActuator;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IntSetRangeActuatorTest {

    /**
     * This test is used to check if an IllegalArgumentException is thrown when the actuator name is null.
     * First the actuator name is set to null.
     * Then, all the other parameters are mocked.
     * An expected string is set to compare with the exception message.
     * An exception is expected to be thrown when the IntSetRangeActuator is created.
     * A string is set to be attributed with the exception message.
     * Finally, the expected message is compared with the result message.
     */
    @Test
    void whenActuatorNameNull_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = null;
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new IntSetRangeActuator(actuatorName, actuatorTypeID, deviceIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test is used to check if an IllegalArgumentException is thrown when the actuator type is null.
     * The actuator type is set to null while all the other parameters are mocked.
     * An expected string is set to compare with the exception message.
     * An exception is expected to be thrown when the IntSetRangeActuator is created.
     * A string is set to be attributed with the exception message.
     * Finally, the expected message is compared with the result message.
     */
    @Test
    void whenActuatorTypeNull_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = null;
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new IntSetRangeActuator(actuatorName, actuatorTypeID, deviceIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test is used to check if an IllegalArgumentException is thrown when the device ID is null.
     * The device ID is set to null while all the other parameters are mocked.
     * An expected string is set to compare with the exception message.
     * An exception is expected to be thrown when the IntSetRangeActuator is created.
     * A string is set to be attributed with the exception message.
     * Finally, the expected message is compared with the result message.
     */
    @Test
    void whenDeviceIDNull_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = null;
        String expected = "Parameters cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new IntSetRangeActuator(actuatorName, actuatorTypeID, deviceIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test is used to check if the actuator ID is set when the IntSetRangeActuator is created.
     * The actuator name, type and device ID are mocked.
     * The actuator ID is mocked and set to a random UUID.
     * The IntSetRangeActuator is created.
     * The actuator ID is then retrieved, using the getID method provided by the ActuatorIDVO, which allows to get a string
     * to be compared with the expected value which was defined by an expected string.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void whenAllAttributesAreValid_thenActuatorIsCreated() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        String expected = "XPTO";

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn(expected);
             })) {

            //Act
            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);
            String result = actuator.getId().getID();
            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertNotNull(actuator);
            assertEquals(expected,result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test is used to check that, when the setLimits method is called, if the lower limit is higher than the upper limit an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 10 and the upper limit to 5.
     * An expected string is set to compare with the exception message.
     * An exception is expected to be thrown when the setLimits method is called given both parameters.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifLowerLimitIsGreaterThanUpperLimit_whenSetLimitsCalled_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class))
            {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);
            int lowerLimit = 10;
            int upperLimit = 5;
            String expected = "Upper limit can't be less than or equal to lower limit.";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.setLimits(lowerLimit, upperLimit));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test is used to check that, when the setLimits method is called, if the lower limit is equal to the upper limit an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 10 and the upper limit to 10.
     * An expected string is set to compare with the exception message.
     * An exception is expected to be thrown when the setLimits method is called given both parameters.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifLowerLimitIsEqualToUpperLimit_whenSetLimitsCalled_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);
            int lowerLimit = 10;
            int upperLimit = 10;
            String expected = "Upper limit can't be less than or equal to lower limit.";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.setLimits(lowerLimit, upperLimit));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test is used to check that, when the setLimits method is called, if the lower limit is less than the upper limit the method returns true.
     * The actuator name, type and device ID are mocked, as well as the actuator ID.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The setLimits method is called with both parameters.
     * The method is expected to return true.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the result value is asserted true, and the size of the list checked.
     */
    @Test
    void ifLowerLimitIsLessThanUpperLimit_whenSetLimitsCalled_thenReturnsTrue() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);
            int lowerLimit = 5;
            int upperLimit = 10;

            //Act
            boolean result = actuator.setLimits(lowerLimit, upperLimit);

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertTrue(result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the lower and upper limits are not set, an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower and upper limits are purposely not set and the expected string set to compare with the exception message.
     * An exception is expected to be thrown when the executeCommand method is called.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifLowerOrUpperLimitNull_whenExecuteCommandCalled_throwsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int value = 15;
            String expected = "Limits not set or value out of range";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.executeCommand(simHardwareAct, value));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the value is above the upper limit by one, an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 11 and the expected string set to compare with the exception message.
     * An exception is expected to be thrown when the executeCommand method is called.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifValueAboveUpperLimitByOne_whenExecuteCommandCalled_throwsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            actuator.setLimits(lowerLimit, upperLimit);

            int value = 11;
            String expected = "Limits not set or value out of range";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.executeCommand(simHardwareAct, value));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the value is below the lower limit by one, an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 4 and the expected string set to compare with the exception message.
     * An exception is expected to be thrown when the executeCommand method is called.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifValueBellowLowerLimitByOne_whenExecuteCommandCalled_throwsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            actuator.setLimits(lowerLimit, upperLimit);

            int value = 4;
            String expected = "Limits not set or value out of range";

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.executeCommand(simHardwareAct, value));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the simHardwareAct is null, an IllegalArgumentException is thrown.
     * The actuator name, type and device ID are mocked, as well as the actuator ID.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 7.
     * The simHardwareAct is set to null and the expected string set to compare with the exception message.
     * An exception is expected to be thrown when the executeCommand method is called.
     * A string is set to be attributed with the exception message.
     * Then, a list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifSimHardwareActNull_whenExecuteCommandCalled_throwsIllegalArgumentException() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            actuator.setLimits(lowerLimit, upperLimit);

            SimHardwareAct simHardwareAct = null;
            String expected = "SimHardwareAct cannot be null";

            int value = 7;

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () -> actuator.executeCommand(simHardwareAct, value));
            String result = exception.getMessage();

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, even if the value is within the limits, the method may be set to return false so that this result can be tested.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 7.
     * The simHardwareAct is set to return false when the executeIntegerCommandSim method is called with the value.
     * The executeCommand method is called with the simHardwareAct and the value.
     * The actuator's executeCommand method is called and the result expected to be false.
     * A list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifExecutionResultFalse_whenExecuteCommandCalled_thenReturnsFalse() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            actuator.setLimits(lowerLimit, upperLimit);

            int value = 7;
            when(simHardwareAct.executeIntegerCommandSim(value)).thenReturn(false);

            //Act
            boolean result = actuator.executeCommand(simHardwareAct, value);

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertFalse(result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the value is within the limits, the method returns true.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 7.
     * The simHardwareAct is set to return true when the executeIntegerCommandSim method is called with the value.
     * The executeCommand method is called with the simHardwareAct and the value.
     * A list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifValueIsValid_whenExecutionCommandCalled_thenReturnTrue() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator actuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            actuator.setLimits(lowerLimit, upperLimit);

            int value = 7;
            when(simHardwareAct.executeIntegerCommandSim(value)).thenReturn(true);

            //Act
            boolean result = actuator.executeCommand(simHardwareAct, value);

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertTrue(result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test verifies that when the executeCommand method is called, if the value is equal to the lower limit, the method returns true.
     * The actuator name, type and device ID are mocked, as well as the actuator ID and the simHardwareAct.
     * The IntSetRangeActuator is created.
     * The lower limit is set to 5 and the upper limit to 10.
     * The value is set to 5.
     * The simHardwareAct is set to return true when the executeIntegerCommandSim method is called with the value.
     * The executeCommand method is called with the simHardwareAct and the value.
     * A list of mocked ActuatorIDVO is created to check if the object was constructed.
     * Finally, the expected value is compared with the result value, and the size of the list checked.
     */
    @Test
    void ifValueIsEqualToPresentValueState_whenExecuteCommandCalled_thenReturnsTrue() {
        //Arrange
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorType = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try (MockedConstruction<ActuatorIDVO> actuatorIdMockedConstruction = mockConstruction(ActuatorIDVO.class)) {

            IntSetRangeActuator intSetRangeActuator = new IntSetRangeActuator(actuatorName, actuatorType, deviceID);

            int lowerLimit = 5;
            int upperLimit = 10;
            intSetRangeActuator.setLimits(lowerLimit, upperLimit);

            int value = 5;
            SimHardwareAct simHardwareAct = mock(SimHardwareAct.class);
            when(simHardwareAct.executeIntegerCommandSim(value)).thenReturn(true);

            //Act
            boolean result = intSetRangeActuator.executeCommand(simHardwareAct, value);

            List<ActuatorIDVO> listOfMockedActuatorIDVO = actuatorIdMockedConstruction.constructed();

            //Assert
            assertTrue(result);
            assertEquals(1, listOfMockedActuatorIDVO.size());
        }
    }

    /**
     * This test validates that getActuatorName successfully returns actuatorNameVo object
     */
    @Test
    void getActuatorName_returnsActuatorNameVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        IntSetRangeActuator actuator = new IntSetRangeActuator(name,type,deviceID);
        // Act
        ActuatorNameVO result = actuator.getActuatorName();
        // Assert
        assertEquals(name,result);
    }

    /**
     * This test validates that getActuatorName successfully returns ActuatorTypeIDVO object
     */
    @Test
    void getActuatorTypeID_returnsActuatorTypeIDVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        IntSetRangeActuator actuator = new IntSetRangeActuator(name,type,deviceID);
        // Act
        ActuatorTypeIDVO result = actuator.getActuatorTypeID();
        // Assert
        assertEquals(type,result);
    }

    /**
     * This test validates that getActuatorName successfully returns DeviceIDVO object
     */
    @Test
    void getActuatorTypeID_returnsDeviceIDVO(){
        // Arrange
        ActuatorNameVO name = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO type = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        IntSetRangeActuator actuator = new IntSetRangeActuator(name,type,deviceID);
        // Act
        DeviceIDVO result = actuator.getDeviceID();
        // Assert
        assertEquals(deviceID,result);
    }
}