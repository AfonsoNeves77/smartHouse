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
    /**
     * Integration Test to add an actuator to a device.
     * Arrange a house with a room and a device.
     * Add an actuator to a device
     * Assert the actuator was successfully added.
     * @throws InstantiationException If House or Catalogue parameters are invalid
     */
    @Test
    void addActuatorToDeviceBlindRoller_IntegrationTest() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house1,catalogue);

        // first adds a room to the house
        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house1.addRoom(roomName,floor,width,length,height,factoryRoom);

        // adds a device to the room
        Room room = house1.getListOfRooms().get(0);
        String deviceName = "DeviceOne";
        String deviceModel = "Model";
        FactoryDevice factoryDevice1 = new ImplFactoryDevice();
        room.addDevice(deviceName,deviceModel,factoryDevice1);

        // then we arrange the actuator adding parameters
        SimHardwareAct simHardware = new SimHardwareAct();
        String actuatorName = "actuatorName";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        addActuatorCtrl.getListOfDevices(roomName); // this step is necessary to ensure the room object is available (saved
        // on the ctrl encapsulated parameters.

        //Act
        boolean result = addActuatorCtrl.addActuatorToDevice(actuatorName,actuatorType,deviceName,simHardware);
        //Assert
        assertTrue(result);
    }

    /**
     * Integration Test to add an already existing actuator to a device.
     * Arrange a house with a room and a device.
     * Act to add the same actuator to the device.
     * Assert the actuator was not successfully added.
     * @throws InstantiationException If House or Catalogue parameters are invalid
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
     * Integration Test to add an actuator with an invalid name to a device.
     * Arrange a house with a room and a device.
     * Act to add the invalid actuator to a device.
     * Assert the actuator was not successfully added.
     * @throws InstantiationException If House or Catalogue parameters are invalid
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
     * Integration Test to add an actuator with a null name to a device.
     * Arrange a house with a room and a device.
     * Act to add the invalid actuator to a device.
     * Assert the actuator was not successfully added.
     * @throws InstantiationException If House or Catalogue parameters are invalid
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

    /**
     * Successfuly gets a list containing all Rooms in the House.
     * @throws InstantiationException If House or Catalogue parameters are invalid
     */
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

    /**
     * Integration test for retrieving the list of actuator types from the AddActuatorToDeviceCTRL controller.
     * Two scenarios are present:
     * 1. Asserts first type mentioned in the list.
     * 2. Asserts the current size of the list of actuator types.
     * @throws InstantiationException If House or Catalogue parameters are invalid
     */

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

    /**
     Integration Test that aims to get the list of devices in a Room from the AddSensorToDeviceCTRL.
     * A Room is added to the House, then a device is added to the Room. Finally there is an attempt to
     * get the list of devices and then accessing to the second device in the list, checking its name.
     * @throws InstantiationException If any parameters for objects' instantiation are invalid
     */
    @Test
    void getListOfDevices() throws InstantiationException {
        //Arrange
        House house = new House("test House");

        String roomName = "Room 0";
        int floor = 0;
        double width = 4;
        double length = 9;
        double height = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        house.addRoom(roomName,floor,width,length,height,factoryRoom);

        Room room = house.getListOfRooms().get(0);
        String deviceName1 = "Dev1";
        String deviceName2 = "Dev2";
        String deviceModel = "XPTO";
        FactoryDevice factoryDevice = new ImplFactoryDevice();
        room.addDevice(deviceName1, deviceModel, factoryDevice);
        room.addDevice(deviceName2, deviceModel, factoryDevice);

        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");

        AddActuatorToDeviceCtrl addActuatorCtrl = new AddActuatorToDeviceCtrl(house, catalogue);

        //Act
        String result = addActuatorCtrl.getListOfDevices(roomName).get(1).getName();

        //Assert
        assertEquals(deviceName2, result);
    }



}

