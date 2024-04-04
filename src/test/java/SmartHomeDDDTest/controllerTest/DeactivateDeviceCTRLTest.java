package SmartHomeDDDTest.controllerTest;

import SmartHomeDDD.controller.DeactivateDeviceCTRL;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class DeactivateDeviceCTRLTest {

    /**
     * Test case to verify that when a null DeviceService is provided to the constructor,
     * an IllegalArgumentException with "Invalid service" message is thrown.
     */

    @Test
    void whenNullDeviceService_ConstructorShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid service";
        DeviceService deviceService = null;
        //Act + Assert

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeactivateDeviceCTRL(deviceService));
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
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceDTO deviceDTO = null;
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        //Act
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

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
        String deviceID = null; // Null device ID
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with a null device ID
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomID);

        // Creating necessary dependencies for testing
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        // Act: Invoking deactivateDevice method with a DeviceDTO having a null device ID
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }

    /**
     * Test case to verify that when an empty device ID is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenEmptyDeviceDTOID_ShouldReturnFalse() {
        // Arrange
        String deviceID = ""; // Empty device ID
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with an empty device ID
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomID);

        // Creating necessary dependencies for testing
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        // Act: Invoking deactivateDevice method with a DeviceDTO having an empty device ID
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }
    /**
     * Test case to verify that when a device ID containing only blank spaces is provided in the DeviceDTO to deactivateDevice method,
     * it returns false.
     */
    @Test
    void deactivateDevice_WhenEmptyWithBlankSpacesDeviceDTOID_ShouldReturnFalse() {
        // Arrange
        String deviceID = "    "; // Device ID with blank spaces
        String deviceName = "Smart Oven";
        String deviceModel = "Bosch electronics 77-kk";
        String deviceStatus = "true";
        String roomID = "JL-II-99";

        // Creating a DeviceDTO with a device ID containing only blank spaces
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomID);

        // Creating necessary dependencies for testing
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        // Act: Invoking deactivateDevice method with a DeviceDTO having a device ID containing only blank spaces
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
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

        // Creating necessary dependencies for testing
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

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

        // Creating necessary dependencies for testing
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

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
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        // Saving the device and deactivating it
        deviceRepository.save(device);
        device.deactivateDevice();

        // Creating a DeviceDTO representing the deactivated device
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus,idRoomString);

        // Act: Invoking deactivateDevice method with a DeviceDTO representing an already deactivated device
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is false as expected
        assertFalse(result);
    }

    /**
     * Test case to verify that when an activated device exists and is provided in the DeviceDTO to deactivateDevice method,
     * it returns true after successful deactivation.
     */
    @Test
    void deactivateDevice_WhenDeviceExistsAndActivated_ShouldReturnTrue() {
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
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // Creating DeactivateDeviceCTRL instance
        DeactivateDeviceCTRL deactivateDeviceCTRL = new DeactivateDeviceCTRL(deviceService);

        // Saving the device
        deviceRepository.save(device);

        // Creating a DeviceDTO representing the activated device
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus,idRoomString);

        // Act: Invoking deactivateDevice method with a DeviceDTO representing an activated device
        boolean result = deactivateDeviceCTRL.deactivateDevice(deviceDTO);

        // Assert: Verifying that the result is true as expected
        assertTrue(result);
    }
}
