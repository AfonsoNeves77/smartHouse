package smarthome.service;


import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.RoomRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceServiceImplTest {


    //DEVICE SERVICE IMPL TESTS

    /**
     * Test to verify that the DeviceServiceImpl constructor throws an IllegalArgumentException
     * when the DeviceFactory parameter is null. The test arranges a mock RoomRepository and DeviceRepository,
     * and then asserts that an IllegalArgumentException is thrown when the DeviceServiceImpl constructor
     * is called with a null DeviceFactory. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenNullDeviceFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceRepository deviceRepository = mock(smarthome.persistence.DeviceRepository.class);
        String expected = "Invalid parameters";

        //Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceServiceImpl(roomRepository, null, deviceRepository));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the DeviceServiceImpl constructor throws an IllegalArgumentException
     * when the RoomRepository parameter is null. The test arranges a mock DeviceFactory and DeviceRepository,
     * and then asserts that an IllegalArgumentException is thrown when the DeviceServiceImpl constructor
     * is called with a null RoomRepository. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenNullRoomRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        String expected = "Invalid parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceServiceImpl(null, deviceFactory, deviceRepository));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the DeviceServiceImpl constructor throws an IllegalArgumentException
     * when the DeviceRepository parameter is null. The test arranges a mock RoomRepository and DeviceFactory,
     * and then asserts that an IllegalArgumentException is thrown when the DeviceServiceImpl constructor
     * is called with a null DeviceRepository. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenNullDeviceRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceServiceImpl(roomRepository, deviceFactory, null));
        String result = exception.getMessage();

        //Assert
        assertEquals("Invalid parameters", result);
    }

    /**
     * Test to verify that the DeviceServiceImpl constructor correctly instantiates the DeviceService
     * when all parameters are valid. The test arranges mock RoomRepository, DeviceFactory, and DeviceRepository,
     * and then asserts that a DeviceServiceImpl object is successfully created when the constructor is called with these valid parameters.
     */
    @Test
    void whenValidParameters_thenDeviceServiceInstantiated() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Act
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);

        //Assert
        assertNotNull(deviceService);
    }

    // ADD DEVICE METHOD TESTS

    /**
     * Test to verify that the addDevice method throws an IllegalArgumentException
     * when the DeviceNameVO parameter is null. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceModelVO, and RoomIDVO. It then asserts that an IllegalArgumentException is thrown when the addDevice method
     * is called with a null DeviceNameVO. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenDeviceNameIsInvalid_ThenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceModelVO deviceModelIDVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        String expected = "DeviceNameVO, DeviceModelVO and RoomIDVO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(null, deviceModelIDVO, roomIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addDevice method throws an IllegalArgumentException
     * when the DeviceModelVO parameter is null. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceNameVO, and RoomIDVO. It then asserts that an IllegalArgumentException is thrown when the addDevice method
     * is called with a null DeviceModelVO. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenDeviceModelIsInvalid_ThenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceNameVO deviceName = mock(DeviceNameVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        String expected = "DeviceNameVO, DeviceModelVO and RoomIDVO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(deviceName, null, roomIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addDevice method throws an IllegalArgumentException
     * when the RoomIDVO parameter is null. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceNameVO, and DeviceModelVO. It then asserts that an IllegalArgumentException is thrown when the addDevice method
     * is called with a null RoomIDVO. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenRoomIDIsInvalid_ThenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceModelVO deviceModelIDVO = mock(DeviceModelVO.class);
        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        String expected = "DeviceNameVO, DeviceModelVO and RoomIDVO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(deviceNameVO, deviceModelIDVO, null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addDevice method throws an IllegalArgumentException
     * when the RoomIDVO does not exist in the RoomRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceNameVO, DeviceModelVO, and RoomIDVO. It then sets the behavior of the RoomRepository to return false when isPresent is called.
     * It then asserts that an IllegalArgumentException is thrown when the addDevice method is called. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenRoomIsNotPresent_ThenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelIDVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        String expected = "Room with ID: " + roomIDVO + " is not present.";

        when(roomRepository.isPresent(roomIDVO)).thenReturn(false);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceService.addDevice(deviceNameVO, deviceModelIDVO, roomIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the addDevice method returns an empty Optional
     * when the Device is not saved in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceNameVO, DeviceModelVO, and RoomIDVO. It then sets the behavior of the RoomRepository to return true when isPresent is called,
     * and the behavior of the DeviceRepository to return false when save is called. It then asserts that the result of the addDevice method is an empty Optional.
     */
    @Test
    void whenDeviceIsNotSaved_thenReturnsEmptyOptional() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelIDVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        when(roomRepository.isPresent(roomIDVO)).thenReturn(true);
        when(deviceRepository.save(deviceFactory.createDevice(deviceNameVO, deviceModelIDVO, roomIDVO))).thenReturn(false);

        //Act
        Optional<Device> result = deviceService.addDevice(deviceNameVO, deviceModelIDVO, roomIDVO);

        //Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test to verify that the addDevice method returns an Optional of Device
     * when the Device is saved in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceNameVO, DeviceModelVO, RoomIDVO, and Device. It then sets the behavior of the RoomRepository to return true when isPresent is called,
     * the behavior of the DeviceFactoryImpl to return the mock Device when createDevice is called, and the behavior of the DeviceRepository to return true when save is called.
     * It then asserts that the result of the addDevice method is an Optional containing the mock Device.
     */
    @Test
    void whenDeviceIsSaved_thenReturnsOptionalOfDevice() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        smarthome.persistence.DeviceRepository deviceRepository = mock(smarthome.persistence.DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelIDVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        Device device = mock(Device.class);

        when(deviceFactory.createDevice(deviceNameVO, deviceModelIDVO, roomIDVO)).thenReturn(device);
        when(roomRepository.isPresent(roomIDVO)).thenReturn(true);
        when(deviceRepository.save(device)).thenReturn(true);

        //Act
        Optional<Device> result = deviceService.addDevice(deviceNameVO, deviceModelIDVO, roomIDVO);

        //Assert
        assertTrue(result.isPresent());
    }

    // DEACTIVATE DEVICE METHOD TESTS

    /**
     * Test to verify that the deactivateDevice method throws an IllegalArgumentException
     * when the DeviceIDVO parameter is null. The test arranges mock RoomRepository, DeviceFactoryImpl, and DeviceRepository.
     * It then asserts that an IllegalArgumentException is thrown when the deactivateDevice method
     * is called with a null DeviceIDVO. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenDeviceIDVOIsNull_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        String expected = "DeviceIDVO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> deviceService.deactivateDevice(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the deactivateDevice method throws an IllegalArgumentException
     * when the DeviceIDVO does not exist in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * and DeviceIDVO. It then sets the behavior of the DeviceRepository to return false when isPresent is called.
     * It then asserts that an IllegalArgumentException is thrown when the deactivateDevice method is called. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenDeviceIsNotPresent_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        String expected = "Device with ID: " + deviceIDVO + " is not present.";

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(false);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> deviceService.deactivateDevice(deviceIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the deactivateDevice method throws an IllegalArgumentException
     * when the Device is not active. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceIDVO, and Device. It then sets the behavior of the DeviceRepository to return true when isPresent is called,
     * the behavior of the DeviceRepository to return the mock Device when findById is called, and the behavior of the Device to return false when isActive is called.
     * It then asserts that an IllegalArgumentException is thrown when the deactivateDevice method is called. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenDeviceIsNotActive_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        String expected = "Device with ID: " + deviceIDVO + " is already deactivated.";

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(false);


        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceService.deactivateDevice(deviceIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the deactivateDevice method returns an empty Optional
     * when the Device is not deactivated. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceIDVO, and Device. It then sets the behavior of the DeviceRepository to return true when isPresent is called,
     * the behavior of the DeviceRepository to return the mock Device when findById is called, the behavior of the Device to return true when isActive is called,
     * the behavior of the Device to return false when deactivateDevice is called, and the behavior of the DeviceRepository to return true when update is called.
     * It then asserts that the result of the deactivateDevice method is an empty Optional.
     */

    @Test
    void whenDeviceIsNotDeactivated_thenReturnsEmptyOptional() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(device.deactivateDevice()).thenReturn(false);
        when(deviceRepository.update(device)).thenReturn(true);

        //Act
        Optional<Device> result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test to verify that the deactivateDevice method returns an empty Optional
     * when the Device is not updated in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceIDVO, and Device. It then sets the behavior of the DeviceRepository to return true when isPresent is called,
     * the behavior of the DeviceRepository to return the mock Device when findById is called, the behavior of the Device to return true when isActive is called,
     * the behavior of the Device to return true when deactivateDevice is called, and the behavior of the DeviceRepository to return false when update is called.
     * It then asserts that the result of the deactivateDevice method is an empty Optional.
     */

    @Test
    void whenDeviceIsNotUpdated_thenReturnsEmptyOptional() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(device.deactivateDevice()).thenReturn(true);
        when(deviceRepository.update(device)).thenReturn(false);

        //Act
        Optional<Device> result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test to verify that the deactivateDevice method returns an Optional of Device
     * when the Device is correctly deactivated and updated in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * DeviceIDVO, and Device. It then sets the behavior of the DeviceRepository to return true when isPresent is called,
     * the behavior of the DeviceRepository to return the mock Device when findById is called, the behavior of the Device to return true when isActive is called,
     * the behavior of the Device to return true when deactivateDevice is called, and the behavior of the DeviceRepository to return true when update is called.
     * It then asserts that the result of the deactivateDevice method is an Optional containing the mock Device.
     */

    @Test
    void whenDeviceIsCorrectlyDeactivated_thenReturnsOptionalOfDevice() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(device.deactivateDevice()).thenReturn(true);
        when(deviceRepository.update(device)).thenReturn(true);

        //Act
        Optional<Device> result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertTrue(result.isPresent());
    }
    //GETLISTOFDEVICESINAROOM METHOD TESTS

    /**
     * Test to verify that the getListOfDevicesInARoom method throws an IllegalArgumentException
     * when the RoomIDVO parameter is null. The test arranges mock RoomRepository, DeviceFactoryImpl, and DeviceRepository.
     * It then asserts that an IllegalArgumentException is thrown when the getListOfDevicesInARoom method
     * is called with a null RoomIDVO. The test also verifies that the exception message matches the expected message.
     */

    @Test
    void whenRoomIDVOIsNull_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        String expected = "RoomIDVO cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> deviceService.getListOfDevicesInARoom(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getListOfDevicesInARoom method throws an IllegalArgumentException
     * when the RoomIDVO does not exist in the DeviceRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * and RoomIDVO. It then sets the behavior of the DeviceRepository to return an empty list when findByRoomID is called.
     * It then asserts that an IllegalArgumentException is thrown when the getListOfDevicesInARoom method is called. The test also verifies that the exception message matches the expected message.
     */
    @Test
    void whenRoomIsNotPresent_thenThrowsIllegalArgumentException() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        String expected = "Room with ID: " + roomIDVO + " is not present.";

        when(deviceRepository.findByRoomID(roomIDVO)).thenReturn(Collections.emptyList());

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> deviceService.getListOfDevicesInARoom(roomIDVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that the getListOfDevicesInARoom method returns a list of Devices
     * when the RoomIDVO exists in the RoomRepository. The test arranges mock RoomRepository, DeviceFactoryImpl, DeviceRepository,
     * RoomIDVO, and a list of Devices. It then sets the behavior of the RoomRepository to return true when isPresent is called,
     * and the behavior of the DeviceRepository to return the list of Devices when findByRoomID is called.
     * It then asserts that the result of the getListOfDevicesInARoom method is the list of Devices.
     */
    @Test
    void whenGetListOfDevices_thenReturnListOfDevices() {
        //Arrange
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceFactoryImpl deviceFactory = mock(DeviceFactoryImpl.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceServiceImpl deviceService = new DeviceServiceImpl(roomRepository, deviceFactory, deviceRepository);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        List<Device> devices = Collections.singletonList(mock(Device.class));

        when(roomRepository.isPresent(roomIDVO)).thenReturn(true);
        when(deviceRepository.findByRoomID(roomIDVO)).thenReturn(devices);

        //Act
        List<Device> result = deviceService.getListOfDevicesInARoom(roomIDVO);

        //Assert
        assertEquals(devices, result);
    }
}