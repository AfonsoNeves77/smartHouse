package SmartHome.controllerTest;

import SmartHome.controller.AddSensorToDeviceCTRL;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AddSensorToDeviceCTRLTest {
    /**
     * Test01
     * Integration Test to add a sensor to a device.
     * Arrange a house with a room and a device.
     * Act to add a sensor to the device.
     * Assert the sensor was successfully added.
     */
    @Test
    void addSensorToDeviceTemperatureSensor_IntegrationTest() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardware simHardware = new SimHardware();
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);

        Room room = house1.getListOfRooms().get(0);
        String deviceName = "DeviceOne";
        String deviceModel = "Model";
        FactoryDevice factoryDevice1 = new ImplFactoryDevice();

        room.addDevice(deviceName,deviceModel,factoryDevice1);

        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1,catalogue);
        String sensorName = "sensorName";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        addSensorCtrl.getListOfDevices(roomName);


        //Act
        boolean result = addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,simHardware);
        //Assert
        assertTrue(result);
    }
    /**
     * Test02
     * Integration Test to add an already existing sensor to a device.
     * Arrange a house with a room and a device.
     * Act to add the same sensor to the device.
     * Assert the sensor was not successfully added.
     */
    @Test
    void addingSensorToDeviceIntegrationTest_shouldNotSucceedForDuplicatedSensor() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardware simHardware = new SimHardware();
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);

        Room room = house1.getListOfRooms().get(0);

        String deviceName = "DeviceOne";
        String deviceModel = "Model";
        FactoryDevice factoryDevice1 = new ImplFactoryDevice();

        room.addDevice(deviceName,deviceModel,factoryDevice1);

        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1,catalogue);
        String sensorName = "sensorName";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        addSensorCtrl.getListOfDevices(roomName);
        addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,simHardware);

        //Act
        boolean result = addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,simHardware);
        //Assert
        assertFalse(result);
    }

    /**
     * Test03
     * Integration Test to add a sensor with an invalid name to a device.
     * Arrange a house with a room and a device.
     * Act to add the invalid sensor to a device.
     * Assert the sensor was not successfully added.
     */
    @Test
    void addingSensorToDeviceIntegrationTest_shouldNotSucceedEmptyName() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardware simHardware = new SimHardware();
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);

        Room room = house1.getListOfRooms().get(0);
        String deviceName = "DeviceOne";
        String deviceModel = "Model";
        FactoryDevice factoryDevice1 = new ImplFactoryDevice();

        room.addDevice(deviceName,deviceModel,factoryDevice1);

        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1,catalogue);
        String sensorName = "";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";

        //Act
        addSensorCtrl.getListOfDevices(roomName);
        boolean result = addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,simHardware);
        //Assert
        assertFalse(result);
    }

    /**
     * Test04
     * Integration Test to add a sensor with a null name to a device.
     * Arrange a house with a room and a device.
     * Act to add the invalid sensor to a device.
     * Assert the sensor was not successfully added.
     */
    @Test
    void addingSensorToDeviceIntegrationTest_shouldNotSucceedNullName() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardware simHardware = new SimHardware();
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);

        Room room = house1.getListOfRooms().get(0);
        String deviceName = "DeviceOne";
        String deviceModel = "Model";
        FactoryDevice factoryDevice1 = new ImplFactoryDevice();

        room.addDevice(deviceName,deviceModel,factoryDevice1);

        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1,catalogue);
        String sensorName = null;
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";

        //Act
        addSensorCtrl.getListOfDevices(roomName);
        boolean result = addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void getListOfRooms() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");

        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1, catalogue);

        //Act
        String result = addSensorCtrl.getListOfRooms().get(0).getRoomName();

        //Assert
        assertEquals(roomName, result);
    }

    @Test
    void getListOfSensorTypes() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");

        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1, catalogue);
        String expected = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        //Act
        List<String> listOfSensorTypes= addSensorCtrl.getListOfSensorTypes();
        String result = listOfSensorTypes.get(0);

        //Assert
        assertEquals(expected,result);
    }
}
