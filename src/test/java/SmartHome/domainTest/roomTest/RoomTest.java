package SmartHome.domainTest.roomTest;

import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.device.ListOfDevices;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomTest {

    //Isolation testing can be attained with mockedConstruction



    /**
     * Test01 case to (...)
     */

    @Test
    void getDimensions_UnitTest() throws InstantiationException {
        //Arrange
        String name = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        Room room = new Room(name,floor,roomWidth,roomLength,roomHeight);
        double[] expected = {4.5, 8.5, 2};
        //Act
        double[] result = room.getDimensions();
        //Assert
        assertArrayEquals(expected,result);
    }

    /**
     * Test02
     * Isolation Test to add a device to a room.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Arrange a device name and a device model.
     * Act to add a device to the room.
     * Assert that the device was successfully added to the room.
     */
    @Test
    void addDevice_IsolationTest() throws InstantiationException {
        //Arrange
        String name = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        Room room = new Room(name,floor,roomWidth,roomLength,roomHeight);
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        Device deviceDouble = mock(Device.class);

        ImplFactoryDevice implFactoryDeviceDouble = mock(ImplFactoryDevice.class);
        when(implFactoryDeviceDouble.createDevice(deviceName,deviceModel,name)).thenReturn(deviceDouble);

        //Act
        boolean result = room.addDevice(deviceName,deviceModel,implFactoryDeviceDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Test03
     * Isolation Test to get the list of devices in a room.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Arrange a device name and a device model.
     * Act to get the list of devices in the room.
     * Assert that the list of devices was successfully retrieved and that the device in it is the correct one.
     */
    @Test
    void getListOfDevices_IsolationTest() throws InstantiationException {
        //Arrange
        String name = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        Room room = new Room(name,floor,roomWidth,roomLength,roomHeight);
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";
        String deviceLocation = room.getRoomName();
        Device deviceDouble = mock(Device.class);
        FactoryDevice factoryDeviceDouble = mock(ImplFactoryDevice.class);
        when(factoryDeviceDouble.createDevice(deviceName,deviceModel,deviceLocation)).thenReturn(deviceDouble);
        when(deviceDouble.getDeviceName()).thenReturn(deviceName);
        room.addDevice(deviceName,deviceModel,factoryDeviceDouble);

        //Act
        String result = room.getListOfDevices().get(0).getDeviceName();
        //Assert
        assertEquals(deviceName,result);
    }

    @Test
    void getRoomFunctionalities_TwoFunctionalities_IntegrationTest() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String roomName = "Room Test"; int houseFloor = 1; double roomWidth = 4; double roomLength = 5; double roomHeight = 2;
        Room room = new Room(roomName,houseFloor,roomWidth,roomLength,roomHeight);
        FactoryDevice deviceFactory = new ImplFactoryDevice();
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);

        // 1.

        String deviceName1 = "Hairdryer H1"; String deviceModel1 = "BabyBlissPro";
        String deviceName2 = "Dehumidifier D1"; String deviceModel2 = "Bosch";
        room.addDevice(deviceName1,deviceModel1,deviceFactory);
        room.addDevice(deviceName2,deviceModel2,deviceFactory);

        // 3.
        Device device1 = room.getListOfDevices().get(0);
        Device device2 = room.getListOfDevices().get(1);
        device1.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        device1.addSensor("XKT6","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        device1.addSensor("YMCA8", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue,simHardware);

        device2.addSensor("XKT9","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        device2.addSensor("XVM7","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        device2.addSensor("YMCA7", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue,simHardware);
        // 4.
        int expected = 2;

        //Act
        // 5.
        int result = room.getRoomFunctionalities().size();
        //Assert
        assertEquals(expected,result);
    }

}