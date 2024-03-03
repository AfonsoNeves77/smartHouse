package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class DeactivateDeviceCTRLTest {
    /**
     * Test01
     * Test to get a list of rooms from a house.
     * Arrange a house with a room.
     * Act to get the list of rooms.
     * Assert the room in the list is the same as the one added.
     */
    @Test
    void deactivateDevice_getListOfRooms() throws InstantiationException {
        //Arrange
        String houseName = "my House";
        House myHouse = new House(houseName);

        String name = "Bathroom";
        int floor = 2;
        double roomWidth = 3.5;
        double roomLength = 3;
        double roomHeight = 2;
        DeactivateDeviceCTRL controller = new DeactivateDeviceCTRL(myHouse);
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
     * Test02
     * Test to get a list of devices from a room.
     * Arrange a house with a room and a device.
     * Act to get the list of devices.
     * Assert the device in the list is the same as the one added.
     */
    @Test
    void deactivateDevice_getListOfDevices() throws InstantiationException {
        //Arrange
        String houseName = "house";
        House house = new House(houseName);

        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(roomName,floor,width,length,height,factoryRoom);

        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        FactoryDevice factoryDevice = new ImplFactoryDevice();
        DeactivateDeviceCTRL ctrl = new DeactivateDeviceCTRL(house);
        house.getListOfRooms().get(0).addDevice(deviceName,deviceModel,factoryDevice);

        String expected = "Heater";
        //Act
        DeviceDTO deviceDTO = ctrl.getListOfDevices(roomName).get(0);
        String result = deviceDTO.getName();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test03
     * Test to deactivate a device.
     * Arrange a house with a room and a device.
     * Act to deactivate the device.
     * Assert the device was successfully deactivated.
     */
    @Test
    void deactivateDevice_successfulyChangedStatusFromTrueToFalse() throws InstantiationException {
        //Arrange
        String houseName = "house";
        House house = new House(houseName);

        //Adds a room to the house
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(roomName,floor,width,length,height,factoryRoom);

        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        FactoryDevice factoryDevice = new ImplFactoryDevice();
        DeactivateDeviceCTRL ctrl = new DeactivateDeviceCTRL(house);

        Room room = house.getListOfRooms().get(0);
        room.addDevice(deviceName,deviceModel,factoryDevice);
        ctrl.getListOfDevices(roomName);

        //Act
        boolean result = ctrl.deactivateDevice(deviceName);
        //Assert
        assertFalse(result);
    }
}
