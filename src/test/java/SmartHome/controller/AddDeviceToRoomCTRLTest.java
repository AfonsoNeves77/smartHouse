package SmartHome.controller;


import SmartHome.domain.House;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddDeviceToRoomCTRLTest {

    /**
     * Test01
     * Test to add a room to a house and retrieve the list of rooms.
     * Arrange a house with a room.
     * Act to get the list of rooms.
     * Assert the room in the list is the same as the one added.
     */
    @Test
    void addDeviceToRoomCTRL_getRoomListSuccess() throws InstantiationException {
        //Arrange
        String houseName = "Test";
        House house = new House(houseName);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3;
        double length = 3;
        double height = 3;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(roomName, floor, width, length, height, factoryRoom);

        FactoryDevice factoryDevice = new ImplFactoryDevice();
        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(house, factoryDevice);
        //Act
        List<RoomDTO> listOfRoomDTO = ctrl.getListOfRooms();
        String resultName = listOfRoomDTO.get(0).getRoomName();
        //Assert
        assertEquals(roomName, resultName);
    }

    /**
     * Test02
     * Test to add a device to a room.
     * Arrange a house with a room and a device.
     * Act to add the device to the room.
     * Assert the device was added to the room.
     */
    @Test
    void addDeviceToRoomCTRL_addDeviceToRoomSuccess() throws InstantiationException {
        //Arrange
        String houseName = "Test";
        House house = new House(houseName);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3;
        double length = 3;
        double height = 3;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        house.addRoom(roomName, floor, width, length, height, factoryRoom);
        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(house, factoryDevice);
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        //Act
        boolean result = ctrl.addDeviceToRoom(deviceName, deviceModel, roomName);

        //Assert
        assertTrue(result);
    }

    @Test
    void addDeviceToRoomCTRL_addRepeatedDeviceToRoom() throws InstantiationException {
        //Arrange
        String houseName = "Test";
        House house = new House(houseName);

        String roomName = "Living Room";
        int floor = 1;
        double width = 3;
        double length = 3;
        double height = 3;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        house.addRoom(roomName, floor, width, length, height, factoryRoom);
        AddDeviceToRoomCTRL ctrl = new AddDeviceToRoomCTRL(house, factoryDevice);
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        //Act
        ctrl.addDeviceToRoom(deviceName, deviceModel, roomName);
        boolean result = ctrl.addDeviceToRoom(deviceName, deviceModel, roomName);

        //Assert
        assertFalse(result);
    }
}
