package SmartHome.domainTest.roomTest;

import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.ListOfRooms;
import SmartHome.domain.room.Room;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ListOfRoomsTest {

    /**
     * Test01
     * Isolation Test to successfully create a room.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Act to create a room.
     * Assert that the room was successfully created.
     */
    @Test
    void addRoomToList_IsolationTest() {
        //Arrange
        String roomName = "Bathroom";
        int houseFloor = 1;
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = 2;

        FactoryIndoorRoom factRoomDouble = mock(FactoryIndoorRoom.class);
        Room roomDouble = mock(Room.class);
        when(roomDouble.getRoomName()).thenReturn(roomName);
        ListOfRooms roomsList = new ListOfRooms();
        when(factRoomDouble.createRoom(roomName,houseFloor,roomWidth,roomLength,roomHeight))
                .thenReturn(roomDouble);
        //Act
        boolean result = roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,
                roomHeight,factRoomDouble);
        //Assert
        assertTrue(result);
    }


    /**
     * Test03 case to ensure that the addRoomToList method successfully adds a room to the list.
     *
     * @throws InstantiationException if there is an issue with instantiating a room object(invalid parameters).
     */
    @Test
    void addRoomToList_Success(){
        //Arrange
        String roomName = "Living Room";
        int houseFloor = 1;
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = 2;
        ListOfRooms roomsList = new ListOfRooms();
        FactoryIndoorRoom factRoom = new FactoryIndoorRoom();
        //Act
        boolean result = roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,roomHeight,factRoom);

        //Assert
        assertTrue(result);
    }

    /**
     * Test 04
     * Integration Test to ensure that the addRoomToList method does not add a room to the list if it already exists.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Act to add the same room to the list twice.
     * Assert that the room was not added to the list twice.
     */
    @Test
    void addRoomToList_DuplicatedRoom(){
        //Arrange
        String roomName = "Living Room";
        int houseFloor = 1;
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = 2;
        ListOfRooms roomsList = new ListOfRooms();

        FactoryRoom factRoom = new FactoryIndoorRoom();
        //Act
        roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,roomHeight,factRoom);
        boolean result = roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,roomHeight,factRoom);
        //Assert
        assertFalse(result);
    }

    @Test
    void addRoomToList_DuplicatedRoomIsolationTest() {
        //Arrange
        String roomName = "Bathroom";
        int houseFloor = 1;
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = 2;

        FactoryIndoorRoom factRoomDouble = mock(FactoryIndoorRoom.class);
        Room roomDouble = mock(Room.class);
        when(roomDouble.getRoomName()).thenReturn(roomName);
        ListOfRooms roomsList = new ListOfRooms();
        when(factRoomDouble.createRoom(roomName,houseFloor,roomWidth,roomLength,roomHeight))
                .thenReturn(roomDouble);
        //Act
        roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,
                roomHeight,factRoomDouble);
        boolean result = roomsList.addRoomToList(roomName,houseFloor,roomWidth,roomLength,
                roomHeight,factRoomDouble);
        //Assert
        assertFalse(result);
    }



}