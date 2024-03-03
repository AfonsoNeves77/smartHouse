package SmartHome.controllerTest;

import SmartHome.controller.CommonListOfRooms;
import SmartHome.domain.House;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class CommonListOfRoomsTest {

    /**
     * Test01
     * Test to get a list of rooms from a house.
     * Arrange a house with a room.
     * Act to get the list of rooms.
     * Assert the room in the list is the same as the one added.
     */
    @Test
    void getListOfRooms() throws InstantiationException{

        //Arrange
        String houseName = "house";
        House house = new House(houseName);

        String name = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(name,floor,width,length,height,factoryRoom);


        //Act

        CommonListOfRooms commonListOfRooms = new CommonListOfRooms(house);
        List<RoomDTO> listOfRoomDTO = commonListOfRooms.getListOfRooms();
        RoomDTO firstRoomInList = listOfRoomDTO.get(0);
        String roomDTOName = firstRoomInList.getRoomName();

        //Assert
        assertEquals(roomDTOName,name);
    }

    /**
     * Test02
     * Test to get a room from a roomDTO.
     * Arrange a house with a room.
     * Act to get the room from the roomDTO.
     * Assert the room is the same as the one added.
     */
    @Test
    void getRoomFromRoomDTO() throws InstantiationException {

        //Arrange
        String houseName = "house";
        House house = new House(houseName);

        String name = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(name,floor,width,length,height,factoryRoom);


        //Act

        CommonListOfRooms commonListOfRooms = new CommonListOfRooms(house);
        List<RoomDTO> listOfRoomDTO = commonListOfRooms.getListOfRooms();

        Room returnedRoom = commonListOfRooms.getRoomByName(name);
        String returnedRoomName = returnedRoom.getRoomName();

        //Assert
        assertEquals(returnedRoomName,name);
    }

    /**
     * Test03
     * Isolation Test to get an empty list of rooms from a house.
     * Arrange a house with an empty list of rooms.
     * Act to get the list of rooms.
     * Assert the list is empty.
     */
    @Test
    void getListOfRoomsEmptyList_IsolationTest(){

        //Arrange
        House houseDouble = mock(House.class);
        List<Room> listOfRooms = new ArrayList<>();
        when(houseDouble.getListOfRooms()).thenReturn(listOfRooms);

        //Act
        CommonListOfRooms commonListOfRooms = new CommonListOfRooms(houseDouble);

        boolean result = commonListOfRooms.getListOfRooms().isEmpty();

        //Assert
        assertTrue(result);

    }

    /**
     * Test04
     * Isolation Test to get a list of rooms from a house with one room.
     * Arrange a house with a room.
     * Act to get the list of rooms.
     * Assert the room in the list is the same as the one added.
     */
    @Test
    void getListOfRoomsListWithOneRoom_IsolationTest(){

        //Arrange
        House houseDouble = mock(House.class);
        List<Room> listOfRooms = new ArrayList<>();
        String name = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        Room room = new Room(name,floor,width,length,height);
        listOfRooms.add(room);
        when(houseDouble.getListOfRooms()).thenReturn(listOfRooms);

        //Act
        CommonListOfRooms commonListOfRooms = new CommonListOfRooms(houseDouble);

       RoomDTO roomDTO = commonListOfRooms.getListOfRooms().get(0);
       String result = roomDTO.getRoomName();

        //Assert
        assertEquals(result,name);

    }
}
