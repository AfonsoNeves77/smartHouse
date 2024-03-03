package SmartHome.controllerTest;

import SmartHome.controller.CommonListOfDevices;
import SmartHome.domain.House;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonListOfDevicesTest {

    /**
     * Tests the getListOfDevices method in the CommonDeviceListCTRL class when a device is added to a room.
     * 1. Adds the room to the House
     * 2. Adds device to room
     * 3. Starts the controller
     * Asserts if the name of the device is the same as the one from the ArrayList
     * @throws InstantiationException If an error occurs during the instantiation of objects.
     */
    @Test
    void getListOfDevices() throws InstantiationException {

        //Arrange
        String houseName = "myHouse";
        String roomName = "Room 1";
        int roomFloor = 2;
        double width = 1.9;
        double length = 2;
        double height = 4;
        House house = new House(houseName);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        //1.
        house.addRoom(roomName,roomFloor,width,length,height,factoryRoom);
        Room room = house.getListOfRooms().get(0);
        String deviceName = "Device 1";
        String model = "XPTO";

        //Act
        //2.
        room.addDevice(deviceName,model,factoryDevice);

        //3.
        CommonListOfDevices commonListOfDevices = new CommonListOfDevices(house);
        String result = commonListOfDevices.getListOfDevices(room).get(0).getName();
        //Assert
        //4.
        assertEquals(result,deviceName);

    }

    @Test
    void getDeviceByName() throws InstantiationException {

        //Arrange
        String houseName = "myHouse";
        String roomName = "Room 1";
        int roomFloor = 2;
        double width = 1.9;
        double length = 2;
        double height = 4;
        House house = new House(houseName);
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        //1.
        house.addRoom(roomName,roomFloor,width,length,height,factoryRoom);
        Room room = house.getListOfRooms().get(0);
        String deviceName = "Device 1";
        String model = "XPTO";

        //Act
        //2.
        room.addDevice(deviceName,model,factoryDevice);

        //3.
        CommonListOfDevices commonListOfDevices = new CommonListOfDevices(house);
        Device resultDevice = commonListOfDevices.getDeviceByName(deviceName,room);
        String result = resultDevice.getDeviceName();
        //Assert
        //4.
        assertEquals(result,deviceName);

    }


}
