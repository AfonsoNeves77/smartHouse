package SmartHomeDDDTest.controller;

import SmartHomeDDD.controller.AddDeviceToRoomCTRL;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.*;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * NOTE: RoomDTO and DeviceDTO attributes were not accessed in the controller tests, since all Mappers ensure they cannot
 * be invalid and used as so. Exceptions would be thrown and caught when appropriate.
 */
class AddDeviceToRoomCTRLTest {
    /**
     * This test ensures that given correct DTOs, the method addDeviceToRoom() successfully adds a device, returning true.
     */
    @Test
    void givenValidData_WhenAddDeviceToRoom_ThenShouldReturnTrue(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        roomService.saveRoom(room);

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertTrue(result);
    }

    /**
     * This test verifies that the method addDeviceToRoom() throws an IllegalArgumentException if CTRL is injected with
     * an invalid RoomService (null).
     */
    @Test
    void givenNullRoomService_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges null Room Service
        RoomService roomService = null;

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        String expected = "Invalid services provided";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AddDeviceToRoomCTRL(roomService, deviceService);
        });
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the method addDeviceToRoom() throws an IllegalArgumentException if CTRL is injected with
     * an invalid DeviceService (null).
     */
    @Test
    void givenNullDeviceService_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        // arranges null Device Service
        DeviceService deviceService = null;

        String expected = "Invalid services provided";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new AddDeviceToRoomCTRL(roomService, deviceService);
        });
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test ensures that the method addDeviceToRoom() returns false if RoomDTO is null.
     */
    @Test
    void givenNullRoomDTO_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        roomService.saveRoom(room);

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        // arranges null RoomDTO
        RoomDTO roomDTO = null;

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that the method addDeviceToRoom() returns false if DeviceDTO is null.
     */
    @Test
    void givenNullDeviceDTO_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room Service, creates Room, saves room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        roomService.saveRoom(room);

        // arranges Device service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges null DeviceDTO
        DeviceDTO deviceDTO = null;

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that the addDeviceToRoom() method returns false if the information within the RoomDTO does not result
     * in a RoomIDVO object. When giving a wrong UUID format, an IllegalArgumentException is thrown.
     */
    @Test
    void givenRoomDTOWithInvalidIdFormat_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        // arranges Device service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        // arranges RoomDTO. House ID must be obtained from the previously instantiated HouseIDVO object.
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO("I will fail","Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that the addDeviceToRoom() method returns false if the information within the RoomDTO does not result
     * in a RoomIDVO object that corresponds to a key in the repository (does not exist).
     */
    @Test
    void givenRoomDTOWithAnIdNotPresentInRoomRepository_WhenAddDeviceToRoom_ThenShouldReturnFalse(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        // arranges RoomDTO. House ID must be obtained from the previously instantiated HouseIDVO object.
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO("d1c6bfb3-8d1e-4e5c-a4fd-6b1dfc7dc8f9","Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName, deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        // Assert
        assertFalse(result);
    }


    /**
     * This test ensures the method addDeviceToRoom() returns true even if the Device to be added has a similar name and model.
     * This indicates that a brand new DeviceIDVO is being created on Device instantiation. The same DeviceDTO is introduced twice
     * in the system and added to the same Room.
     */
    @Test
    void whenAddingDevicesWithSameNameAndModel_ThenShouldReturnTrue(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        roomService.saveRoom(room);

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName,deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService,deviceService);

        ctrl.addDeviceToRoom(roomDTO,deviceDTO);

        // Act
        boolean result = ctrl.addDeviceToRoom(roomDTO,deviceDTO);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that when the addDeviceToRoom() method is utilized, the new device can be accessed through the repository,
     * and its name is the same as the defined name, meaning the Device was actually saved.
     */
    @Test
    void givenValidData_DeviceIsAdded_WhenFindByRoomIDFollowedByGetDeviceName_ExpectedDeviceNameIsReturned(){
        // Arrange
        // arranges Room Service, creates Room, saves Room to repository
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomNameVO name = new RoomNameVO("Office");
        RoomFloorVO floor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());
        RoomWidthVO width = new RoomWidthVO(3.5);
        RoomLengthVO length = new RoomLengthVO(3);
        RoomHeightVO height = new RoomHeightVO(2);
        RoomDimensionsVO dimensions = new RoomDimensionsVO(length, width, height);
        Room room = new Room(name, floor, dimensions, houseID);

        roomService.saveRoom(room);

        // arranges Device Service
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        // arranges RoomDTO. String IDs must be obtained from the previously instantiated Room and HouseIDVO objects.
        String roomId = room.getId().getID();
        String houseId = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomId,"Office",2,3.5,3,2,houseId);

        // arranges DeviceDTO
        String deviceName = "Top Load Washing Machine";
        String deviceModel = "Samsung WA50R5400AW";
        DeviceDTO deviceDTO = new DeviceDTO(deviceName,deviceModel);

        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(roomService, deviceService);

        ctrl.addDeviceToRoom(roomDTO, deviceDTO);

        RoomIDVO roomIdVO = room.getId();

        List<Device> resultList = deviceRepository.findByRoomID(roomIdVO);
        int expectedSize = 1;

        // Act
        String resultName = resultList.get(0).getDeviceName().getValue();
        int listSize = resultList.size();

        // Assert
        assertEquals(deviceName,resultName);
        assertEquals(expectedSize, listSize);
    }
}