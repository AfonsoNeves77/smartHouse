package SmartHomeDDDTest.controllerTest;

import SmartHomeDDD.controller.GetListOfDevicesCTRL;
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
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.*;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfDevicesCTRLTest {

    /**
     * Test for the constructor of GetListOfDevicesCTRL.
     * When the RoomService is null, it should throw an IllegalArgumentException.
     * A room service is declared null.
     * Then, a device service is created injected with both device factory and device repository.
     * An expected message is declared.
     * An exception is thrown when the constructor is called.
     * The result is the message of the exception, which should be the same as the expected message.
     */
    @Test
    void whenRoomServiceIsNull_thenThrowsIllegalArgumentException(){
        //Arrange

        RoomService roomService = null;

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetListOfDevicesCTRL(roomService, deviceService);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the constructor of GetListOfDevicesCTRL.
     * When the DeviceService is null, it should throw an IllegalArgumentException.
     * A device service is declared null.
     * Then, a room service is created injected with both room factory and room repository.
     * An expected message is declared.
     * An exception is thrown when the constructor is called.
     * The result is the message of the exception, which should be the same as the expected message.
     */
    @Test
    void whenDeviceServiceIsNull_thenThrowsIllegalArgumentException(){
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        DeviceService deviceService = null;

        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GetListOfDevicesCTRL(roomService, deviceService);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the constructor of GetListOfDevicesCTRL.
     * When the parameters are valid, it should return an object.
     * A room service is created injected with both room factory and room repository.
     * A device service is created injected with both device factory and device repository.
     * The constructor is called with the room service and device service.
     * The result is the object created, which should not be null.
     */
    @Test
    void whenParametersValid_thenReturnsObject(){
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        //Act
        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(roomService, deviceService);

        //Assert
        assertNotNull(getListOfDevicesCTRL);
    }

    /**
     * Test for the method getListOfDevices.
     * When the room does not exist, it should return an empty list.
     * A room service is created, injected with both room factory and room repository.
     * A device service is created, injected with both device factory and device repository.
     * A room DTO is created with a room ID that does not exist in the repository.
     * A GetListOfDevicesCTRL is created with the room service and device service.
     * The method getListOfDevices is called with the room DTO.
     * The result is the list of devices, which should be empty.
     */
    @Test
    void whenRoomDoesNotExist_thenReturnsAnEmptyList() throws InstantiationException {
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        String roomName1 = "room1";
        int roomFloor1 = 1;
        double roomWidth1 = 10.0;
        double roomLength1 = 10.0;
        double roomHeight1 = 10.0;
        String roomID1 = "123e4567-e89b-12d3-a456-426614174001";
        String houseIDVO1 = "123e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(roomID1,roomName1,roomFloor1,roomHeight1,roomLength1,roomWidth1,houseIDVO1);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(roomService, deviceService);

        //Act
        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);

        //Assert
        assertTrue(deviceDTOList.isEmpty());
    }

    /**
     * Test for the method getListOfDevices.
     * When the room has no devices, it should return an empty list.
     * A room service is created, injected with both room factory and room repository.
     * A device service is created, injected with both device factory and device repository.
     * A room is created and saved in the repository.
     * A room DTO is created with the room ID of the room created.
     * A GetListOfDevicesCTRL is created with the room service and device service.
     * The method getListOfDevices is called with the room DTO.
     * The result is the list of devices, which should be empty.
     * @throws InstantiationException
     */
    @Test
    void ifRoomHasNoDevices_whenGetListOfDevicesIsCalled_thenReturnsEmptyList() throws InstantiationException {
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomWidthVO roomWidthVO = new RoomWidthVO(10.0);
        RoomLengthVO roomLengthVO = new RoomLengthVO(10.0);
        RoomHeightVO roomHeightVO = new RoomHeightVO(10.0);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        RoomNameVO roomNameVO = new RoomNameVO("room1");
        RoomFloorVO roomFloorVO = new RoomFloorVO(1);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room = roomFactory.createRoom(roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepository.save(room);


        String roomName = "room1";
        int roomFloor = 1;
        double roomWidth = 10.0;
        double roomLength = 10.0;
        double roomHeight = 10.0;
        String roomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(roomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(roomService, deviceService);

        //Act

        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);

        //Assert
        assertTrue(deviceDTOList.isEmpty());
    }

    /**
     * Test for the method getListOfDevices.
     * When the room has one device, it should return a list with one device.
     * A room service is created, injected with both room factory and room repository.
     * A device service is created, injected with both device factory and device repository.
     * A room is created and saved in the repository.
     * A device is created and saved in the repository.
     * A room DTO is created with the room ID of the room created.
     * A GetListOfDevicesCTRL is created with the room service and device service.
     * The method getListOfDevices is called with the room DTO.
     * The result is the list of devices, which should have one device.
     * @throws InstantiationException
     */
    @Test
    void ifRoomHasOneDevice_whenGetListOfDevicesIsCalled_thenReturnsListOfDevicesDTO() throws InstantiationException {
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomWidthVO roomWidthVO = new RoomWidthVO(10.0);
        RoomLengthVO roomLengthVO = new RoomLengthVO(10.0);
        RoomHeightVO roomHeightVO = new RoomHeightVO(10.0);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        RoomNameVO roomNameVO = new RoomNameVO("room1");
        RoomFloorVO roomFloorVO = new RoomFloorVO(1);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room = roomFactory.createRoom(roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepository.save(room);


        String roomName = "room1";
        int roomFloor = 1;
        double roomWidth = 10.0;
        double roomLength = 10.0;
        double roomHeight = 10.0;
        String roomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(roomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(roomID));

        DeviceNameVO deviceNameVO = new DeviceNameVO("Fridge");
        DeviceModelVO deviceModelVO = new DeviceModelVO("123QWE");

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device = deviceFactory.createDevice(deviceNameVO, deviceModelVO, roomIDVO);
        deviceRepository.save(device);

        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(roomService, deviceService);

        String expected = "Fridge";
        int expectedSize = 1;

        //Act
        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);
        String result = deviceDTOList.get(0).getDeviceName();

        //Assert
        assertEquals(expected, result);
        assertEquals(expectedSize, deviceDTOList.size());
        }

    /**
     * Test for the method getListOfDevices.
     * When the room has two devices, it should return a list with two devices.
     * A room service is created, injected with both room factory and room repository.
     * A device service is created, injected with both device factory and device repository.
     * A room is created and saved in the repository.
     * Two devices are created and saved in the repository.
     * A room DTO is created with the room ID of the room created.
     * A GetListOfDevicesCTRL is created with the room service and device service.
     * The method getListOfDevices is called with the room DTO.
     * The result is the list of devices, which should have two devices.
     * @throws InstantiationException
     */
    @Test
    void ifRoomHasTwoDevices_whenGetListOfDevicesIsCalled_thenReturnsListOfDevicesDTO() throws InstantiationException {
        //Arrange
        RoomFactory roomFactory = new RoomFactory();
        RoomRepository roomRepository = new RoomRepository();
        RoomService roomService = new RoomService(roomRepository, roomFactory);

        RoomWidthVO roomWidthVO = new RoomWidthVO(10.0);
        RoomLengthVO roomLengthVO = new RoomLengthVO(10.0);
        RoomHeightVO roomHeightVO = new RoomHeightVO(10.0);
        RoomDimensionsVO roomDimensionsVO = new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);

        RoomNameVO roomNameVO = new RoomNameVO("quarto");
        RoomFloorVO roomFloorVO = new RoomFloorVO(1);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        Room room = roomFactory.createRoom(roomNameVO, roomFloorVO, roomDimensionsVO, houseID);
        roomRepository.save(room);


        String roomName = "quarto";
        int roomFloor = 1;
        double roomWidth = 10.0;
        double roomLength = 10.0;
        double roomHeight = 10.0;
        String roomID = room.getId().getID();
        String houseIDVO = "124e4567-e89b-12d3-a456-426614174000";
        RoomDTO roomDTO = new RoomDTO(roomID,roomName,roomFloor,roomHeight,roomLength,roomWidth,houseIDVO);

        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(roomID));

        DeviceNameVO deviceNameVO = new DeviceNameVO("Fridge");
        DeviceModelVO deviceModelVO = new DeviceModelVO("123QWE");

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("AirConditioner");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("456QWE");

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device = deviceFactory.createDevice(deviceNameVO, deviceModelVO, roomIDVO);
        Device device2 = deviceFactory.createDevice(deviceNameVO2,deviceModelVO2,roomIDVO);
        deviceRepository.save(device);
        deviceRepository.save(device2);

        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

        GetListOfDevicesCTRL getListOfDevicesCTRL = new GetListOfDevicesCTRL(roomService, deviceService);

        String expected = "Fridge";
        String expected2 = "AirConditioner";
        int expectedSize = 2;

        //Act
        List<DeviceDTO> deviceDTOList = getListOfDevicesCTRL.getListOfDevices(roomDTO);
        String result = deviceDTOList.get(0).getDeviceName();
        String result2 = deviceDTOList.get(1).getDeviceName();

        //Assert
        assertEquals(expected, result);
        assertEquals(expected2, result2);
        assertEquals(expectedSize, deviceDTOList.size());
    }
}