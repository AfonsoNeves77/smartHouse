package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


class GetListOfRoomsCTRLTest {

    /**
     * This test verifies the behavior of the getListOfRooms method in the GetListOfRoomsCTRL class when a single room is added to the house.
     * The test follows these steps:
     * 1. A room is added to the House.
     * 2. The controller is started.
     * 3. The test asserts if the name of the room returned by the controller is the same as the one added to the House.
     *
     * @throws InstantiationException If an error occurs during the instantiation of objects.
     */
    @Test
    void getListOfRoomsCTRLTest_HouseWithOneRoom() throws InstantiationException {

        //Arrange
        String houseName = "my House";
        House myHouse = new House(houseName);

        String name = "Bathroom";
        int floor = 2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;
        GetListOfRoomsCTRL controller = new GetListOfRoomsCTRL(myHouse);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        //1.
        myHouse.addRoom(name, floor, roomWidth, roomLength, roomHeight, factoryRoom);

        //Act
        //2.
        List<RoomDTO> result = controller.getListOfRooms();
        String nameOfRoom = result.get(0).getRoomName();

        //Assert
        //3.
        assertEquals(name,nameOfRoom);

    }

    /**
     * This test verifies the behavior of the getListOfRooms method in the GetListOfRoomsCTRL class when multiple rooms are added to the house.
     * The test follows these steps:
     * 1. Multiple rooms are added to the House.
     * 2. The controller is started.
     * 3. The test asserts if the name of the second room returned by the controller is the same as the second one added to the House.
     *
     * @throws InstantiationException If an error occurs during the instantiation of objects.
     */
    @Test
    void getListOfRoomsCTRLTest_HouseWithMultipleRooms() throws InstantiationException {

        //Arrange
        String houseName = "my House";
        House myHouse = new House(houseName);

        String name = "Bathroom";
        int floor = 2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;

        String name1 = "Kitchen";
        int floor1 = 5;
        double roomWidth1 = 7.5;
        double roomLength1 = 4.5;
        double roomHeight1 = 3.5;

        String name2 = "Office";
        int floor2 = 2;
        double roomWidth2 = 3.5;
        double roomLength2 = 5;
        double roomHeight2 = 4;

        GetListOfRoomsCTRL controller = new GetListOfRoomsCTRL(myHouse);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        //1.
        myHouse.addRoom(name, floor, roomWidth, roomLength, roomHeight, factoryRoom);
        myHouse.addRoom(name1, floor1, roomWidth1, roomLength1, roomHeight1, factoryRoom);
        myHouse.addRoom(name2, floor2, roomWidth2, roomLength2, roomHeight2, factoryRoom);

        //Act
        //2.
        List<RoomDTO> result = controller.getListOfRooms();
        String nameOfRoom = result.get(1).getRoomName();

        //Assert
        //3.
        assertEquals(name1,nameOfRoom);

    }

    /**
     * This test verifies the behavior of the getListOfRooms method in the GetListOfRoomsCTRL class when no rooms are added to the house.
     * The test follows these steps:
     * 1. The controller is started.
     * 2. The test asserts that the list of rooms returned by the controller is empty.
     *
     * @throws InstantiationException If an error occurs during the instantiation of objects.
     */
    @Test
    void getListOfRoomsCTRLTest_HouseWithNoRooms() throws InstantiationException {
        //Arrange
        String houseName = "my House";
        House myHouse = new House(houseName);
        GetListOfRoomsCTRL controller = new GetListOfRoomsCTRL(myHouse);

        //Act
        //1.
        List<RoomDTO> result = controller.getListOfRooms();

        //2.
        //Assert
        assertTrue(result.isEmpty());
    }
}
