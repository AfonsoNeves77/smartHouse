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

    @Test
    void whenDeviceNameIsCalled_thenDeviceNameIsReturned() {
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




