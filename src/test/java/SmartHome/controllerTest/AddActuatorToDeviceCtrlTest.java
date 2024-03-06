package SmartHome.controllerTest;

import SmartHome.controller.AddActuatorToDeviceCtrl;
import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.House;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddActuatorToDeviceCtrlTest {
    @Test
    void addActuatorToDeviceBlindRoller_IntegrationTest() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardwareAct simHardware = new SimHardwareAct();
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

        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1,catalogue);
        String actuatorName = "actuatorName";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        addActuatorCtrl.getListOfDevices(roomName);


        //Act
        boolean result = addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);
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
    void addingActuatorToDeviceIntegrationTest_shouldNotSucceedForDuplicatedSensor() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardwareAct simHardware = new SimHardwareAct();
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

        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1,catalogue);
        String actuatorName = "actuatorName";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        addActuatorCtrl.getListOfDevices(roomName);
        addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);

        //Act
        boolean result = addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);
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
    void addingActuatorToDeviceIntegrationTest_shouldNotSucceedEmptyName() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardwareAct simHardware = new SimHardwareAct();
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

        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1,catalogue);
        String actuatorName = "";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";

        //Act
        addActuatorCtrl.getListOfDevices(roomName);
        boolean result = addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);
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
    void addingActuatorToDeviceIntegrationTest_shouldNotSucceedNullName() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        SimHardwareAct simHardware = new SimHardwareAct();
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

        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1,catalogue);
        String actuatorName = null;
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";

        //Act
        addActuatorCtrl.getListOfDevices(roomName);
        boolean result = addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);
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
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");

        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1, catalogue);

        //Act
        String result = addActuatorCtrl.getListOfRooms().get(0).getRoomName();

        //Assert
        assertEquals(roomName, result);
    }

    @Test
    void getListOfActuatorTypes() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");

        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1, catalogue);
        String expected = "SmartHome.domain.actuator.SwitchActuator";
        //Act
        List<String> listOfActuatorTypes= addActuatorCtrl.getListOfActuatorTypes();
        String result = listOfActuatorTypes.get(0);

        //Assert
        assertEquals(expected,result);
    }
}

