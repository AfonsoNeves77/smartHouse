package SmartHomeDDDTest.domainTest.deviceTest;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DeviceTest {

    /**
     * This test verifies that the Device constructor throws an IllegalArgumentException with the message "Invalid parameters"
     * when provided with a null DeviceNameVO. A null value is assigned to simulate an invalid device name.
     * The expected exception message ("Invalid parameters") is defined for comparison.
     * Then, an assertThrows statement captures the exception thrown by the constructor with the null DeviceNameVO.
     * The captured exception's message is retrieved and compared with the expected message using assertEquals.
     * This ensures the constructor throws the correct exception with the correct error message.
     */
    @Test
    void whenNullDeviceName_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceNameVO deviceNameDouble = null;
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);
        String expected = "Invalid parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(deviceNameDouble, deviceModelDouble, roomIDDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the Device constructor throws an IllegalArgumentException with the message "Invalid parameters"
     * when provided with a null DeviceModelVO. A null value is assigned to simulate an invalid device model.
     * The expected exception message ("Invalid parameters") is defined for comparison.
     * Then, an assertThrows statement captures the exception thrown by the constructor with the null DeviceModelVO.
     * The captured exception's message is retrieved and compared with the expected message using assertEquals.
     * This ensures the constructor throws the correct exception with the correct error message.
     */
    @Test
    void whenNullDeviceModel_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = null;
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);
        String expected = "Invalid parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(deviceNameDouble, deviceModelDouble, roomIDDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the Device constructor throws an IllegalArgumentException with the message "Invalid parameters"
     * when provided with a null RoomIDVO. A null value is assigned to simulate an invalid room ID.
     * The expected exception message ("Invalid parameters") is defined for comparison.
     * Then, an assertThrows statement captures the exception thrown by the constructor with the null RoomIDVO.
     * The captured exception's message is retrieved and compared with the expected message using assertEquals.
     * This ensures the constructor throws the correct exception with the correct error message.
     */
    @Test
    void whenNullRoomID_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = null;
        String expected = "Invalid parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Device(deviceNameDouble, deviceModelDouble, roomIDDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the getDeviceStatus method returns the correct device status.
     * First, the expected number of DeviceStatusVO objects to be constructed is defined.
     * Then, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The getDeviceStatus method is called on the Device object to retrieve the device status.
     * The DeviceStatusVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceStatusVO object to ensure the correct device status is returned.
     * The number of DeviceStatusVO objects constructed is also compared with the expected number to ensure the method is called.
     */
    @Test
    void whenGetDeviceStatusCalled_thenDeviceStatusIsReturned() {
        //Arrange
        int expectedMockedConstruction = 1;
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            DeviceStatusVO result = device.getDeviceStatus();
            DeviceStatusVO expected = deviceStatusMockedConstruction.constructed().get(0);

            //Assert
            assertEquals(result, expected);
            int resultMockedConstruction = deviceStatusMockedConstruction.constructed().size();
            assertEquals(expectedMockedConstruction, resultMockedConstruction);
        }
    }

    /**
     * This test verifies that the getID method returns the deviceID.
     * First, the expected number of DeviceIDVO objects to be constructed is defined.
     * Then, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The getID method is called on the Device object to retrieve the deviceID.
     * The DeviceIDVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceIDVO object to ensure the correct deviceID is returned.
     * The number of DeviceIDVO objects constructed is also compared with the expected number to ensure the method is called.
     * The DeviceIDVO object is compared with the expected DeviceIDVO object to ensure the correct deviceID is returned.
     */
    @Test
    void whenGetIDCalled_thenDeviceIDIsReturned() {
        //Arrange
        int expectedDeviceIDMockedConstruction = 1;
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try(MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);

            MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class))
            {
                //Act
                Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
                DeviceIDVO result = device.getId();
                DeviceIDVO expected = deviceIDMockedConstruction.constructed().get(0);

                //Assert
                assertEquals(result, expected);
                int resultMockedConstruction = deviceIDMockedConstruction.constructed().size();
                assertEquals(expectedDeviceIDMockedConstruction, resultMockedConstruction);
            }
    }

    /**
     * This test verifies that when a device is instantiated both deviceStatus and deviceID are created.
     * First, the expected number of DeviceIDVO objects to be constructed is defined.
     * Second, the expected number of DeviceStatusVO objects to be constructed is defined.
     * Then, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The number of DeviceIDVO and DeviceStatusVO objects constructed is retrieved and stored in the result variables.
     * The result variables are compared with the expected number of DeviceIDVO and DeviceStatusVO objects to ensure they are created.
     */
    @Test
    void whenDeviceIsInstantiated_thenDeviceStatusAndIdAreInstantiated() {
        //Arrange
        int expectedDeviceIDMockedConstruction = 1;
        int expectedDeviceStatusMockedConstruction = 1;

        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);

             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            int resultMockedConstruction = deviceIDMockedConstruction.constructed().size();
            int result2MockedConstruction = deviceStatusMockedConstruction.constructed().size();

            //Assert
            assertEquals(expectedDeviceIDMockedConstruction, resultMockedConstruction);
            assertEquals(expectedDeviceStatusMockedConstruction, result2MockedConstruction);
        }
    }

    /**
     * This test verifies that the getDeviceStatus method returns the correct device status.
     * First, the expected number of DeviceStatusVO objects to be constructed is defined.
     * Then, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The deactivateDevice method is called on the Device object to deactivate the device.
     * The DeviceStatusVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceStatusVO object to ensure the correct device status is returned.
     * The number of DeviceStatusVO objects constructed is also compared with the expected number to ensure the method is called.
     * The DeviceStatusVO object is compared with the expected DeviceStatusVO object to ensure the correct device status is returned.
     */
    @Test
    void whenGetDeviceStatusCalled_thenDeviceStatusIsReturnedTrue() {
        //Arrange
        int expectedMockedConstruction = 1;
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(true);
        });
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            DeviceStatusVO result = device.getDeviceStatus();


            //Assert
            assertTrue(result.getValue());
            int resultMockedConstruction = deviceStatusMockedConstruction.constructed().size();
            assertEquals(expectedMockedConstruction, resultMockedConstruction);
        }
    }

    /**
     * This test verifies that the deactivateDevice method returns the device status.
     * First, the expected number of DeviceStatusVO objects to be constructed is defined.
     * Then, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The deactivateDevice method is called on the Device object to deactivate the device.
     * The DeviceStatusVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceStatusVO object to ensure the correct device status is returned.
     * The number of DeviceStatusVO objects constructed is also compared with the expected number to ensure the method is called.
     * The DeviceStatusVO object is compared with the expected DeviceStatusVO object to ensure the correct device status is returned.
     */
    @Test
    void whenDeactivateDeviceCalled_thenDeviceStatusIsReturnedFalse() {
        //Arrange
        int expectedMockedConstruction = 2;
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(false);
        });
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            DeviceStatusVO result = device.deactivateDevice();

            //Assert
            assertFalse(result.getValue());
            int resultMockedConstruction = deviceStatusMockedConstruction.constructed().size();
            assertEquals(expectedMockedConstruction, resultMockedConstruction);
        }
    }

    /**
     * This test verifies that the getDeviceName method returns the device name.
     * First, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The getDeviceName method is called on the Device object to retrieve the device name.
     * The DeviceNameVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceNameVO object to ensure the correct device name is returned.
     * The DeviceNameVO object is compared with the expected DeviceNameVO object to ensure the correct device name is returned.
     */
    @Test
    void whenGetDeviceNameIsCalled_thenDeviceNameIsReturned() {
        //Arrange
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            DeviceNameVO result = device.getDeviceName();
            DeviceNameVO expected = deviceNameDouble;

            //Assert
            assertEquals(result, expected);
        }
    }

    /**
     * This test verifies that the getDeviceModel method returns the device model.
     * First, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     * The getDeviceModel method is called on the Device object to retrieve the device model.
     * The DeviceModelVO object constructed is retrieved and stored in the expected variable.
     * The result is compared with the expected DeviceModelVO object to ensure the correct device model is returned.
     * The DeviceModelVO object is compared with the expected DeviceModelVO object to ensure the correct device model is returned.
     *
     */
    @Test
    void whenGetDeviceModelCalled_thenDeviceModelIsReturned() {
        //Arrange
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            DeviceModelVO result = device.getDeviceModel();
            DeviceModelVO expected = deviceModelDouble;

            //Assert
            assertEquals(result, expected);
        }
    }

    /**
     * This test verifies that the getRoomID method returns the room ID.
     * First, the DeviceNameVO, DeviceModelVO, and RoomIDVO are mocked to simulate valid parameters.
     * The Device constructor is called with the mocked parameters to instantiate a Device object.
     *  The getRoomID method is called on the Device object to retrieve the room ID.
     *  The RoomIDVO object constructed is retrieved and stored in the expected variable.
     *  The result is compared with the expected RoomIDVO object to ensure the correct room ID is returned.
     *  The RoomIDVO object is compared with the expected RoomIDVO object to ensure the correct room ID is returned.
     */
    @Test
    void whenGetRoomIDCalled_thenRoomIDIsReturned() {
        //Arrange
        DeviceNameVO deviceNameDouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIDDouble = mock(RoomIDVO.class);

        try (MockedConstruction<DeviceStatusVO> deviceStatusMockedConstruction = mockConstruction(DeviceStatusVO.class);
             MockedConstruction<DeviceIDVO> deviceIDMockedConstruction = mockConstruction(DeviceIDVO.class)) {
            //Act
            Device device = new Device(deviceNameDouble, deviceModelDouble, roomIDDouble);
            RoomIDVO result = device.getRoomID();
            RoomIDVO expected = roomIDDouble;

            //Assert
            assertEquals(result, expected);
        }
    }
}



