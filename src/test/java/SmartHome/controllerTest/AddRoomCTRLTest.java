package SmartHome.controllerTest;

import SmartHome.controller.AddRoomCTRL;
import SmartHome.domain.House;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AddRoomCTRLTest {

    /**
     * Test01
     * Isolation Test to add a room to a house and retrieve the list of rooms.
     * Arrange a house double with a room double and direct the behavior of the house double.
     * Act to get the list of rooms.
     * Assert the room was successfully added.
     */
    @Test
    void addRoomCTRL_IsolationTest() {
        //Arrange
        String roomName = "Bathroom";
        int houseFloor = -2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;
        House houseDouble = mock(House.class);
        FactoryRoom factoryRoomDouble = mock(FactoryRoom.class);
        RoomDTO roomDtoDouble = mock(RoomDTO.class);
        when(roomDtoDouble.getRoomName()).thenReturn(roomName);
        when(roomDtoDouble.getHouseFloor()).thenReturn(houseFloor);
        when(roomDtoDouble.getRoomWidth()).thenReturn(roomWidth);
        when(roomDtoDouble.getRoomLength()).thenReturn(roomLength);
        when(roomDtoDouble.getRoomHeight()).thenReturn(roomHeight);
        AddRoomCTRL controller = new AddRoomCTRL(houseDouble, factoryRoomDouble);
        when(houseDouble.addRoom(roomName,houseFloor,roomWidth,roomLength,roomHeight, factoryRoomDouble)).thenReturn(true);
        //Act
        boolean result = controller.addRoom(roomDtoDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Test02
     * Integration Test to add a room to a house.
     * Arrange a house with a room.
     * Act to add a room to the house.
     * Assert the room was successfully added.
     */
    @Test
    void addRoomCTRL_Success_IntegrationTest() throws InstantiationException {
        //Arrange
        String houseName = "House Test";
        String roomName = "Bathroom";
        int floor = -2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;
        House house = new House(houseName);
        RoomDTO roomDTO = new RoomDTO(roomName,floor,roomWidth,roomLength,roomHeight);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        AddRoomCTRL controller = new AddRoomCTRL(house,factoryRoom);
        //Act
        boolean result = controller.addRoom(roomDTO);
        //Assert
        assertTrue(result);
    }

    /**
     * Test03
     * Integration Test to add an already existing room to a house.
     * Arrange a house with a room.
     * Act to add the same room to the house.
     * Assert the room was not added.
     */
    @Test
    void addRoomCTRL_DuplicatedRoom_IntegrationTest() throws InstantiationException {
        //Arrange
        String houseName = "House Test";
        String roomName = "Bathroom";
        int floor = -2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;
        House house = new House(houseName);
        RoomDTO roomDTO = new RoomDTO(roomName,floor,roomWidth,roomLength,roomHeight);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        AddRoomCTRL controller = new AddRoomCTRL(house,factoryRoom);
        house.addRoom(roomName,floor,roomWidth,roomLength,roomHeight,factoryRoom);
        //Act
        boolean result = controller.addRoom(roomDTO);
        //Assert
        assertFalse(result);
    }

    /**
     * Test04
     * Integration Test to add a room with invalid width to a house.
     * Arrange a house with a room with invalid width.
     * Act to add the room to the house.
     * Assert the room was not added.
     */
    @Test
    void addRoomCTRL_InvalidWidth_IntegrationTest() throws InstantiationException {
        //Arrange
        String houseName = "House Test";
        String roomName = "Bathroom";
        int floor = -2;
        double roomWidth = -3.5;
        double roomLength = 3;
        double roomHeight = 2;
        House myHouse = new House(houseName);
        RoomDTO roomDTO = new RoomDTO(roomName,floor,roomWidth,roomLength,roomHeight);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        AddRoomCTRL controller = new AddRoomCTRL(myHouse,factoryRoom);
        //Act
        boolean result = controller.addRoom(roomDTO);
        //Assert
        assertFalse(result);
    }
}