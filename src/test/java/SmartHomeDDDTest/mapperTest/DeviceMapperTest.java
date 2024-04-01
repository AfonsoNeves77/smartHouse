package SmartHomeDDDTest.mapperTest;


import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceMapperTest {

    /**
     * Test to verify that, when calling the createDeviceName method, the deviceDTO is not null.
     * First, the method is called with a null deviceDTO.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceNameIsCalled_whenDeviceDTOIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = null;
        String expected = "DeviceDTO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceName(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceName method, the deviceName is not null.
     * First, a deviceDTO is created and the getDeviceName method is called with a null value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceNameIsCalled_whenDeviceNameIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceName()).thenReturn(null);
        String expected = "Invalid device name.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceName(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceName method, the deviceName is not blank.
     * First, a deviceDTO is created and the getDeviceName method is called with a blank value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceNameIsCalled_whenDeviceNameIsBlank_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceName()).thenReturn(" ");
        String expected = "Invalid device name.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceName(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceName method, the deviceName is not empty.
     * First, a deviceDTO is created and the getDeviceName method is called with an empty value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceNameIsCalled_whenDeviceNameIsEmpty_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceName()).thenReturn("");
        String expected = "Invalid device name.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceName(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceName method, the deviceName is returned.
     * First, an expected value is created as string.
     * Then, a deviceDTO is created and the getDeviceName method is stubbed with a value.
     * Afterward, a DeviceNameVO object is mocked and the intended behavior is mimicked.
     * Finally, the createDeviceName method is called and the result compared with the expected value.
     */
    @Test
    void whenCreateDeviceNameIsCalled_thenDeviceNameIsReturned() {
        //Arrange
        String expected = "device1";
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceName()).thenReturn(expected);

        try (MockedConstruction<DeviceNameVO> mocked = mockConstruction(DeviceNameVO.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(expected);
        })) {

            //Act
            DeviceNameVO deviceNameVO = DeviceMapper.createDeviceName(deviceDTO);
            String result = deviceNameVO.getValue();

            List<DeviceNameVO> mockedList = mocked.constructed();

            //Assert
            assertEquals(1, mockedList.size());
            assertEquals(expected, result);
        }
    }

    /**
     * Test to verify that, when calling the createDeviceModel method, the deviceDTO is not null.
     * First, the method is called with a null deviceDTO.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceModelIsCalled_whenDeviceDTOIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = null;
        String expected = "DeviceDTO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceModel(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceModel method, the deviceModel is not null.
     * First, a deviceDTO is created and the getDeviceModel method is called with a null value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceModelIsCalled_whenDeviceModelIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceModel()).thenReturn(null);
        String expected = "Invalid device model.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceModel(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceModel method, the deviceModel is not blank.
     * First, a deviceDTO is created and the getDeviceModel method is called with a blank value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceModelIsCalled_whenDeviceModelIsBlank_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceModel()).thenReturn(" ");
        String expected = "Invalid device model.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceModel(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceModel method, the deviceModel is not empty.
     * First, a deviceDTO is created and the getDeviceModel method is called with an empty value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceModelIsCalled_whenDeviceModelIsEmpty_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceModel()).thenReturn("");
        String expected = "Invalid device model.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceModel(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceModel method, the deviceModel is returned.
     * First, an expected value is created as string.
     * Then, a deviceDTO is created and the getDeviceModel method is stubbed with a value.
     * Afterward, a DeviceModelVO object is mocked and the intended behavior is mimicked.
     * Finally, the createDeviceModel method is called and the result compared with the expected value.
     */
    @Test
    void whenCreateDeviceModelIsCalled_thenDeviceModelIsReturned() {
        //Arrange
        String expected = "XPTO";
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceModel()).thenReturn(expected);

        try (MockedConstruction<DeviceModelVO> mocked = mockConstruction(DeviceModelVO.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(expected);
        })) {

            //Act
            DeviceModelVO deviceModelVO = DeviceMapper.createDeviceModel(deviceDTO);
            String result = deviceModelVO.getValue();

            List<DeviceModelVO> mockedList = mocked.constructed();

            //Assert
            assertEquals(1, mockedList.size());
            assertEquals(expected, result);
        }
    }

    /**
     * Test to verify that, when calling the createDeviceID method, the deviceDTO is not null.
     * First, the method is called with a null deviceDTO.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceIDIsCalled_whenDeviceDTOIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = null;
        String expected = "DeviceDTO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceID(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceID method, the deviceID is not null.
     * First, a deviceDTO is created and the getDeviceID method is called with a null value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceIdIsCalled_whenDeviceIdIsNull_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceID()).thenReturn(null);
        String expected = "Invalid device ID.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceID(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceID method, the deviceID is not blank.
     * First, a deviceDTO is created and the getDeviceID method is called with a blank value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceIdIsCalled_whenDeviceIdIsBlank_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceID()).thenReturn(" ");
        String expected = "Invalid device ID.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceID(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceID method, the deviceID is not empty.
     * First, a deviceDTO is created and the getDeviceID method is called with an empty value.
     * Then, an exception is thrown and retrieved to a string that is compared with the expected string.
     */
    @Test
    void ifCreateDeviceIdIsCalled_whenDeviceIdIsEmpty_thenExceptionIsThrown() {
        //Arrange
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceID()).thenReturn("");
        String expected = "Invalid device ID.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> DeviceMapper.createDeviceID(deviceDTO));
        String result  = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that, when calling the createDeviceID method, the deviceID is returned.
     * First, an expected value is created as string.
     * Then, a deviceDTO is created and the getDeviceID method is stubbed with a value.
     * Afterward, a DeviceIDVO object is mocked and the intended behavior is mimicked.
     * Finally, the createDeviceID method is called and the result compared with the expected value.
     */
    @Test
    void whenCreateDeviceIDIsCalled_thenDeviceIDIsReturned() {
        //Arrange
        String expected = UUID.randomUUID().toString();
        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        when(deviceDTO.getDeviceID()).thenReturn(expected);


        try (MockedConstruction<DeviceIDVO> mocked = mockConstruction(DeviceIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn(expected);
        })) {
            //Act
            DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);
            String result = deviceIDVO.getID();

            List<DeviceIDVO> mockedList = mocked.constructed();

            //Assert
            assertEquals(1, mockedList.size());
            assertEquals(expected, result);

        }
    }

    /**
     * Test to verify that, when calling the domainToDTO method, the deviceDTO list is returned.
     * First, the deviceNameVO, deviceModelVO, deviceIDVO, deviceStatusVO, roomIDVO are mocked.
     * Then, the deviceDouble is mocked and the intended behavior is mimicked.
     * This process is repeated for the deviceDouble2.
     * Afterward, a list of devices is created, the mocked devices added to the list and the expected size defined.
     * Finally, the domainToDTO method is called and the VOs of both doubles compared with the expected mocks.
     *
     */
    @Test
    void whenDomainToDTOIsCalled_thenReturnsDeviceDTOList () {
        //Arrange
        DeviceNameVO deviceNameVODouble = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelVODouble = mock(DeviceModelVO.class);
        DeviceIDVO deviceIDVODouble = mock(DeviceIDVO.class);
        DeviceStatusVO deviceStatusVODouble = mock(DeviceStatusVO.class);
        RoomIDVO roomIDVODouble = mock(RoomIDVO.class);

        Device deviceDouble = mock(Device.class);
        when(deviceDouble.getDeviceName()).thenReturn(deviceNameVODouble);
        when(deviceDouble.getDeviceModel()).thenReturn(deviceModelVODouble);
        when(deviceDouble.getDeviceStatus()).thenReturn(deviceStatusVODouble);
        when(deviceDouble.getId()).thenReturn(deviceIDVODouble);
        when(deviceDouble.getRoomID()).thenReturn(roomIDVODouble);

        DeviceNameVO deviceNameVODouble2 = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelVODouble2 = mock(DeviceModelVO.class);
        DeviceIDVO deviceIDVODouble2 = mock(DeviceIDVO.class);
        DeviceStatusVO deviceStatusVODouble2 = mock(DeviceStatusVO.class);
        RoomIDVO roomIDVODouble2 = mock(RoomIDVO.class);

        Device deviceDouble2 = mock(Device.class);
        when(deviceDouble2.getDeviceName()).thenReturn(deviceNameVODouble2);
        when(deviceDouble2.getDeviceModel()).thenReturn(deviceModelVODouble2);
        when(deviceDouble2.getDeviceStatus()).thenReturn(deviceStatusVODouble2);
        when(deviceDouble2.getId()).thenReturn(deviceIDVODouble2);
        when(deviceDouble2.getRoomID()).thenReturn(roomIDVODouble2);

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(deviceDouble);
        deviceList.add(deviceDouble2);

        int expectedListSize = 2;

        //Act
        List<DeviceDTO> result = DeviceMapper.domainToDTO(deviceList);

        //Assert
        assertEquals(expectedListSize, result.size());
        assertEquals(deviceNameVODouble.getValue(), result.get(0).getDeviceName());
        assertEquals(deviceModelVODouble.getValue(), result.get(0).getDeviceModel());
        assertEquals(deviceStatusVODouble.getValue().toString(), result.get(0).getDeviceStatus());
        assertEquals(deviceIDVODouble2.getID(), result.get(0).getDeviceID());
        assertEquals(roomIDVODouble2.getID(), result.get(0).getRoomID());
    }
}