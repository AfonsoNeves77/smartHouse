package SmartHome.controllerTest;

import SmartHome.controller.GetListOfDevicesCTRL;
import SmartHome.domain.House;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.dto.DeviceDTO;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GetListOfDevicesCTRLTest {
    /**
     * Test01
     * Test to get a list of devices from a room.
     * Arrange a house with a room and a device.
     * Act to get the list of devices.
     * Assert the device in the list is the same as the one added.
     */
    @Test
    void getListOfDevices_RoomWithOneDevice() throws InstantiationException {
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
        GetListOfDevicesCTRL ctrl = new GetListOfDevicesCTRL(house);

        house.getListOfRooms().get(0).addDevice(deviceName,deviceModel,factoryDevice);

        String expected = "Heater";
        //Act
        DeviceDTO deviceDTO = ctrl.getListOfDevices(roomName).get(0);
        String result = deviceDTO.getName();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test02
     * Test to get a list of devices from a room with no devices.
     * Arrange a house with a room and no devices.
     * Act to get the list of devices.
     * Assert the list is empty.
     */
    @Test
    void getListOfDevices_RoomWithNoDevices() throws InstantiationException {
        //Arrange
        String houseName = "house";
        House house = new House(houseName);

        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(roomName, floor, width, length, height, factoryRoom);

        GetListOfDevicesCTRL ctrl = new GetListOfDevicesCTRL(house);


        //Act
        List<DeviceDTO> result = ctrl.getListOfDevices(roomName);

        //Assert
        assertTrue(result.isEmpty());
    }

    /**
     * Test03
     * Test to get a list of devices from a house with multiple rooms.
     * Arrange a house with multiple rooms and devices.
     * Act to get the list of devices.
     * Assert the device in the list is the same as the one added in the selected room.
     */
    @Test
    void getListOfDevices_HouseWithMultipleRooms() throws InstantiationException {
        //Arrange
        // Creates all the necessary collaborators for the ctrl, and the controller itself
        String houseName = "house";
        House house = new House(houseName);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        GetListOfDevicesCTRL ctrl = new GetListOfDevicesCTRL(house);

        // Adds two rooms to the house
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        house.addRoom(roomName, floor, width, length, height, factoryRoom);

        String roomNameTwo = "Room 1";
        int otherFloor = 0;
        double otherWidth = 4;
        double otherLength = 9;
        double otherHeight = 2;
        house.addRoom(roomNameTwo, otherFloor, otherWidth, otherLength, otherHeight, factoryRoom);

        // Adds two devices, one to each previously added room.
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        String otherDeviceName = "Refrigerator";
        String otherDeviceModel = "XPTO2000";
        house.getListOfRooms().get(0).addDevice(deviceName, deviceModel, factoryDevice);
        house.getListOfRooms().get(1).addDevice(otherDeviceName, otherDeviceModel, factoryDevice);


        String expected = "Heater";
        //Act
        DeviceDTO deviceDTO = ctrl.getListOfDevices(roomName).get(0);
        String result = deviceDTO.getName();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test04
     * Test to get a list of devices from a room with multiple devices.
     * Arrange a house with a room and multiple devices.
     * Act to get the list of devices.
     * Assert the device in the list is the same as the one added in the selected room.
     */
    @Test
    void getListOfDevices_RoomWithMultipleDevices() throws InstantiationException {

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

        String otherDeviceName = "Refrigerator";
        String otherDeviceModel = "XPTO2000";

        FactoryDevice factoryDevice = new ImplFactoryDevice();
        GetListOfDevicesCTRL ctrl = new GetListOfDevicesCTRL(house);

        house.getListOfRooms().get(0).addDevice(deviceName,deviceModel,factoryDevice);
        house.getListOfRooms().get(0).addDevice(otherDeviceName,otherDeviceModel,factoryDevice);

        String expected = "Refrigerator";
        //Act
        DeviceDTO deviceDTO = ctrl.getListOfDevices(roomName).get(1);
        String result = deviceDTO.getName();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test05
     * Test to get a list of devices from a room with multiple devices.
     * Arrange a house with a room and multiple devices.
     * Act to get the list of devices.
     * Assert the device in the list is the same as the one added in the selected room.
     */

    @Test
    void getListOfRooms() throws InstantiationException {

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
        GetListOfDevicesCTRL ctrl = new GetListOfDevicesCTRL(house);

        String expected = "Room 0";
        //Act
        String result = ctrl.getListOfRooms().get(0).getRoomName();

        //Assert
        assertEquals(expected,result);
    }
}