package smarthome.controller;

import smarthome.domain.device.Device;
import smarthome.domain.device.DeviceFactoryImpl;
import smarthome.mapper.dto.DeviceDTO;
import smarthome.persistence.mem.DeviceRepositoryMem;
import smarthome.persistence.mem.RoomRepositoryMem;
import smarthome.service.DeviceServiceImpl;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeactivateDeviceCTRLTest {

    /**
     * Test case to verify that when a null DeviceService is provided to the constructor,
     * an IllegalArgumentException with "Invalid service" message is thrown.
     */

    @Test
    void whenNullDeviceService_ConstructorShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid service";
        //Act + Assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceCTRL(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify that when a null DeviceDTO is provided to deactivateDevice method,
     * it returns false.
     */

    @Test
    void deactivateDevice_WhenNullDeviceDTO_ShouldReturnFalse(){

        //Arrange
        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        //Act
        boolean result = deactivateDeviceCTRL.deactivateDevice(null);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that when a null device ID is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenNullDeviceDTOID_ShouldReturnFalse() {
        // Arrange
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with a null device ID
        DeviceDTO deviceDTO = new DeviceDTO(null, deviceName, deviceModel, deviceStatus, roomID);


        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Act: Invoking deactivateDevice method with a DeviceDTO having a null device ID
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }

    /**
     * Test case to verify that when an invalid device ID is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenInvalidDeviceID_ShouldReturnFalse() {
        // Arrange
        String deviceID1 = ""; // Empty device ID
        String deviceID2 = "    "; // Device ID with blank spaces
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with an empty device ID
        DeviceDTO deviceDTO1 = new DeviceDTO(deviceID1, deviceName, deviceModel, deviceStatus, roomID);
        DeviceDTO deviceDTO2 = new DeviceDTO(deviceID2, deviceName, deviceModel, deviceStatus, roomID);
        DeviceDTO deviceDTO3 = new DeviceDTO(null, deviceName, deviceModel, deviceStatus, roomID);


        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Act: Invoking deactivateDevice method with a DeviceDTO having an empty device ID
        boolean result1 = deactivateDeviceCTRL.deactivateDevice(deviceDTO1);
        boolean result2 = deactivateDeviceCTRL.deactivateDevice(deviceDTO2);
        boolean result3 = deactivateDeviceCTRL.deactivateDevice(deviceDTO3);

        // Assert: Verifying that the result is false as expected
        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }


    /**
     * Test case to verify that when a non-convertible UUID string is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenNonConvertibleUUIDtoString_ShouldReturnFalse() {
        // Arrange
        String deviceID = "ak-ll-99-hhhh-k"; // Non-convertible UUID string
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with a non-convertible UUID string as device ID
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomID);


        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Act: Invoking deactivateDevice method with a DeviceDTO having a non-convertible UUID string as device ID
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }

    /**
     * Test case to verify that when a non-existing device is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenNonExistingDevice_ShouldReturnFalse() {
        // Arrange
        String deviceID = UUID.randomUUID().toString(); // Generating a random UUID as the device ID
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with the generated random UUID as device ID
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomID);


        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Act: Invoking deactivateDevice method with a DeviceDTO representing a non-existing device
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }

    /**
     * Test case to verify that when a device is already deactivated and provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenDeviceIsAlreadyDeactivated_ShouldReturnFalse() {
        // Arrange
        String deviceName = "Smart Oven";
        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);

        String deviceModel = "Bosch electronics 77-kk";
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceName);

        String deviceStatus = "true";

        UUID idRoom = UUID.randomUUID();
        String idRoomString = idRoom.toString();
        RoomIDVO roomIDVO = new RoomIDVO(idRoom);

        // Creating a new device
        Device device = new Device(deviceNameVO,deviceModelVO,roomIDVO);
        String deviceID = device.getId().getID();

        // Creating necessary dependencies for testing
        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Saving the device and deactivating it
        deviceRepositoryMem.save(device);
        device.deactivateDevice();

        // Creating a DeviceDTO representing the deactivated device
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus,idRoomString);

        // Act: Invoking deactivateDevice method with a DeviceDTO representing an already deactivated device
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        //Query the device for its status assuring that the created device didn't change it's state
        boolean state = device.getDeviceStatus().getValue();

        // Assert: Verifying that the result is true as expected
        assertFalse(result);
        assertFalse(state);
    }

    /**
     * Test case to verify that when an activated device exists and is provided in the DeviceDTO to deactivateDevice method,
     * it returns False after successful deactivation.
     */
    @Test
    void deactivateDevice_WhenDeviceExistsAndActivated_ShouldReturnFalse() {
        // Arrange
        String deviceName = "Smart Oven";
        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);

        String deviceModel = "Bosch electronics 77-kk";
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceName);

        String deviceStatus = "true";

        UUID idRoom = UUID.randomUUID();
        String idRoomString = idRoom.toString();
        RoomIDVO roomIDVO = new RoomIDVO(idRoom);

        // Creating a new device
        Device device = new Device(deviceNameVO,deviceModelVO,roomIDVO);
        String deviceID = device.getId().getID();

        // Creating necessary dependencies for testing
        RoomRepositoryMem roomRepository = new RoomRepositoryMem();
        DeviceFactoryImpl deviceFactoryImpl = new DeviceFactoryImpl();
        DeviceRepositoryMem deviceRepositoryMem = new DeviceRepositoryMem();
        DeviceServiceImpl deviceServiceImpl = new DeviceServiceImpl(roomRepository, deviceFactoryImpl, deviceRepositoryMem);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceServiceImpl);

        // Saving the device
        deviceRepositoryMem.save(device);

        // Creating a DeviceDTO representing the activated device
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus,idRoomString);

        // Act: Invoking deactivateDevice method with a DeviceDTO representing an activated device
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);
        //Query the device for its status assuring that the created device actually changed it's state
        boolean state = device.getDeviceStatus().getValue();

        // Assert: Verifying that the result is False as expected
        assertFalse(result);
        assertFalse(state);
    }
}
