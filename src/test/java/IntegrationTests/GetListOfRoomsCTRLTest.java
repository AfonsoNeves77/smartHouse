package IntegrationTests;

import SmartHomeDDD.controller.GetListOfRoomsCTRL;
import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfRoomsCTRLTest {
    private RoomRepository roomRepository;
    private RoomFactory roomFactory;
    private RoomService roomService;
    private GetListOfRoomsCTRL controller;

    @BeforeEach
    void setUp() {
        roomRepository = new RoomRepository();
        roomFactory = new RoomFactory();
        roomService = new RoomService(roomRepository, roomFactory);
        controller = new GetListOfRoomsCTRL(roomService);
    }

    /**
     * Test for the constructor of the GetListOfRoomsCTRL class.
     * When the RoomService object is null, an IllegalArgumentException should be thrown.
     */
    @Test
    void whenRoomServiceIsNull_thenThrowException() {
        //Arrange
        RoomService roomService = null;
        String expected = "RoomService cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new GetListOfRoomsCTRL(roomService));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getListOfRooms method of the GetListOfRoomsCTRL class.
     * When the house has no Rooms, an empty list should be returned.
     */
    @Test
    void whenHouseHasNoRooms_thenReturnEmptyList() {
        // Arrange
        int expected = 0;

        //Act
        List<RoomDTO> list = controller.getListOfRooms();
        int result = list.size();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getListOfRooms method of the GetListOfRoomsCTRL class.
     * When the house has one Rooms, should be able to access and compare attributes from the element in the list.
     */
    @Test
    void whenHouseHasOneRoom_thenReturnListWithOneRoom() {
        // Arrange
        RoomWidthVO roomWidth = new RoomWidthVO(3.5);
        RoomLengthVO roomLength = new RoomLengthVO(3);
        RoomHeightVO roomHeight = new RoomHeightVO(2);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(roomLength, roomWidth, roomHeight);

        RoomNameVO roomName = new RoomNameVO("Office");
        RoomFloorVO roomFloor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        String expected = "Office";

        Room room = roomFactory.createRoom(roomName, roomFloor, roomDimensions, houseID);
        roomRepository.save(room);

        // Act
        List<RoomDTO> list = controller.getListOfRooms();
        String result = list.get(0).getRoomName();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getListOfRooms method of the GetListOfRoomsCTRL class.
     * When the house has multiple Rooms, should be able to access and compare attributes from the elements in the list.
     */
    @Test
    void whenHouseHasMultipleRooms_thenReturnNameOfSecondRoom() {
        // Arrange
        RoomWidthVO roomWidth = new RoomWidthVO(3.5);
        RoomLengthVO roomLength = new RoomLengthVO(3);
        RoomHeightVO roomHeight = new RoomHeightVO(2);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(roomLength, roomWidth, roomHeight);

        RoomNameVO roomName = new RoomNameVO("Kitchen");
        RoomFloorVO roomFloor = new RoomFloorVO(2);
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomName2 = new RoomNameVO("Living Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(1);
        HouseIDVO houseID2 = new HouseIDVO(UUID.randomUUID());

        String expected = "Living Room";

        Room room = roomFactory.createRoom(roomName, roomFloor, roomDimensions, houseID);
        Room room2 = roomFactory.createRoom(roomName2, roomFloor2, roomDimensions, houseID2);
        roomRepository.save(room);
        roomRepository.save(room2);

        // Act
        List<RoomDTO> list = controller.getListOfRooms();
        String result = list.get(1).getRoomName();

        // Assert
        assertEquals(expected, result);
    }
}