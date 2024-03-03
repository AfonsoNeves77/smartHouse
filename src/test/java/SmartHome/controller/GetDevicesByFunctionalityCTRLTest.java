package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetDevicesByFunctionalityCTRLTest {

    /**
     * Test01 - Returns a HashMap with device functionalities as keys, and an ArrayList of DeviceDTOs as values.
     * 1. Add rooms to the house
     * 2. Add devices to the rooms
     * 3. Add sensors to the devices
     * 4. Expected result when searching for a chosen device name in the HashMap
     * 5. Gets a chosen device's name
     * @throws InstantiationException On house and device creation, if parameters invalid
     */
    @Test
    void listDevicesByFunctionality_checksDeviceName_IntegrationTest() throws InstantiationException {
        //Arrange
        House house = new House("House Test");
        FactoryRoom roomFactory = new FactoryIndoorRoom();
        FactoryDevice deviceFactory = new ImplFactoryDevice();
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);

        // 1.
        String roomName1 = "Kitchen"; String roomName2 = "Guest bedroom";
        int houseFloor = 1; double roomWidth = 4; double roomLength = 5; double roomHeight = 2;
        house.addRoom(roomName1, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);
        house.addRoom(roomName2, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);

        // 2.
        Room room1 = house.getListOfRooms().get(0);
        Room room2 = house.getListOfRooms().get(1);
        String deviceName1 = "Hairdryer H1"; String deviceModel1 = "BabyBlissPro";
        String deviceName2 = "Dehumidifier D1"; String deviceModel2 = "Bosch";
        room1.addDevice(deviceName1,deviceModel1,deviceFactory);
        room2.addDevice(deviceName2,deviceModel2,deviceFactory);

        // 3.
        Device device1 = house.getListOfRooms().get(0).getListOfDevices().get(0);
        Device device2 = house.getListOfRooms().get(1).getListOfDevices().get(0);
        device1.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("XKT6","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("YMCA8", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        device2.addSensor("XKT9","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("XVM7","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("YMCA7", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        // 4.
        GetDevicesByFunctionalityCTRL finalCTRL = new GetDevicesByFunctionalityCTRL(house);
        String expected = "Dehumidifier D1";
        //Act
        // 5.
        String result = finalCTRL.getDevicesByFunctionality().get("SmartHome.domain.sensor.sensorImplementation.TemperatureSensor").get(1).getName();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test02 - Returns a HashMap with device functionalities as keys, and an ArrayList of DeviceDTOs as values.
     * 1. Add rooms to the house
     * 2. Add devices to the rooms
     * 3. Add sensors to the devices
     * 4. Expected result of the size of a chosen list of Devices, associated to a functionality
     * (multiple temperature sensors were added to the same device)
     * 5. Gets the size of the chosen device list
     * @throws InstantiationException On house and device creation, if parameters invalid
     */
    @Test
    void listDevicesByFunctionality_checksFunctionalityListSize_IntegrationTest() throws InstantiationException {
        //Arrange
        House house = new House("House Test");
        FactoryRoom roomFactory = new FactoryIndoorRoom();
        FactoryDevice deviceFactory = new ImplFactoryDevice();
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);

        // 1.
        String roomName1 = "Kitchen"; String roomName2 = "Guest bedroom";
        int houseFloor = 1; double roomWidth = 4; double roomLength = 5; double roomHeight = 2;
        house.addRoom(roomName1, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);
        house.addRoom(roomName2, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);

        // 2.
        Room room1 = house.getListOfRooms().get(0);
        Room room2 = house.getListOfRooms().get(1);
        String deviceName1 = "Hairdryer H1"; String deviceModel1 = "BabyBlissPro";
        String deviceName2 = "Dehumidifier D1"; String deviceModel2 = "Bosch";
        room1.addDevice(deviceName1,deviceModel1,deviceFactory);
        room2.addDevice(deviceName2,deviceModel2,deviceFactory);

        // 3.
        Device device1 = house.getListOfRooms().get(0).getListOfDevices().get(0);
        Device device2 = house.getListOfRooms().get(1).getListOfDevices().get(0);
        device1.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("XKT6","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("YMCA8", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        device2.addSensor("XKT9","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("XVM7","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("YMCA7", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        GetDevicesByFunctionalityCTRL ctrl = new GetDevicesByFunctionalityCTRL(house);

        // 4.
        int expected = 2;
        //Act
        // 5.
        int result = ctrl.getDevicesByFunctionality().get("SmartHome.domain.sensor.sensorImplementation.TemperatureSensor").size();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test03 - Returns a HashMap with device functionalities as keys, and an ArrayList of DeviceDTOs as values.
     * 1. Add rooms to the house
     * 2. Add devices to the rooms
     * 3. Add sensors to the devices
     * 4. Expected result of the room name (device location) of a chosen device
     * 5. Gets the location of the chosen device
     * @throws InstantiationException On house and device creation, if parameters invalid
     */
    @Test
    void listDevicesByFunctionality_GetsLocation_IntegrationTest() throws InstantiationException {
        //Arrange
        House house = new House("House Test");
        FactoryRoom roomFactory = new FactoryIndoorRoom();
        FactoryDevice deviceFactory = new ImplFactoryDevice();
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);

        // 1.
        String roomName1 = "Kitchen"; String roomName2 = "Guest bedroom";
        int houseFloor = 1; double roomWidth = 4; double roomLength = 5; double roomHeight = 2;
        house.addRoom(roomName1, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);
        house.addRoom(roomName2, houseFloor, roomWidth, roomLength, roomHeight, roomFactory);

        // 2.
        Room room1 = house.getListOfRooms().get(0);
        Room room2 = house.getListOfRooms().get(1);
        String deviceName1 = "Hairdryer H1"; String deviceModel1 = "BabyBlissPro";
        String deviceName2 = "Dehumidifier D1"; String deviceModel2 = "Bosch";
        room1.addDevice(deviceName1,deviceModel1,deviceFactory);
        room2.addDevice(deviceName2,deviceModel2,deviceFactory);

        // 3.
        Device device1 = house.getListOfRooms().get(0).getListOfDevices().get(0);
        Device device2 = house.getListOfRooms().get(1).getListOfDevices().get(0);
        device1.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("XKT6","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device1.addSensor("YMCA8", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        device2.addSensor("XKT9","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("XVM7","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device2.addSensor("YMCA7", "SmartHome.domain.sensor.sensorImplementation.HumiditySensor", catalogue);

        GetDevicesByFunctionalityCTRL ctrl = new GetDevicesByFunctionalityCTRL(house);

        // 4.
        String expected = "Guest bedroom";

        //Act
        // 5.
        String result = ctrl.getDevicesByFunctionality().get("SmartHome.domain.sensor.sensorImplementation.HumiditySensor").get(1).getLocation();
        //Assert
        assertEquals(expected,result);
    }
}