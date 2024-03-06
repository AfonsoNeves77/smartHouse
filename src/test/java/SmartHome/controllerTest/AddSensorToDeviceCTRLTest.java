package SmartHome.controllerTest;

import SmartHome.controller.AddSensorToDeviceCTRL;
import SmartHome.controller.CommonListOfDevices;
import SmartHome.controller.CommonListOfRooms;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AddSensorToDeviceCTRLTest {


    //.............................................................................INTEGRATION TESTS..............................................................................
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

    //.............................................................................ISOLATION TESTS..............................................................................

    /**
     * Unit test for verifying the behavior of the getListOfRooms method
     * in the AddSensorToDeviceCTRL class, ensuring that it returns the
     * correct list of rooms by isolating the behavior of CommonListOfRooms
     * and CommonListOfDevices using mock objects.

     * The test sets up mock objects for House, SensorCatalogue, and RoomDTO.
     * It then constructs an instance of AddSensorToDeviceCTRL with the mock objects,
     * isolates the behavior of CommonListOfRooms and CommonListOfDevices using
     * MockedConstruction, and verifies that calling getListOfRooms returns the
     * expected list of rooms.
     */
    @Test
    void getListOfRooms_isolationTest() {
        // Arrange
        House houseDouble = mock(House.class);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);

        RoomDTO roomDTODouble = mock(RoomDTO.class);
        ArrayList<RoomDTO> arrayListRooms = new ArrayList<>();
        arrayListRooms.add(roomDTODouble);

        int expected = 1;

        // Act + Assert
        try (MockedConstruction<CommonListOfRooms> commonListOfRoomsMockedConstruction = mockConstruction(CommonListOfRooms.class);
             MockedConstruction<CommonListOfDevices> commonListOfDevicesMockedConstruction = mockConstruction(CommonListOfDevices.class)) {

            AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(houseDouble, sensorCatalogueDouble);

            List<CommonListOfRooms> mockedRoomsList = commonListOfRoomsMockedConstruction.constructed();
            CommonListOfRooms commonListOfRoomsMocked = mockedRoomsList.get(0);
            when(commonListOfRoomsMocked.getListOfRooms()).thenReturn(arrayListRooms);

            List<RoomDTO> roomList = addSensorToDeviceCTRL.getListOfRooms();
            int result = roomList.size();

            assertEquals(expected, result);
        }
    }

    /**
     * Unit test for verifying the behavior of the getListOfDevices method
     * in the AddSensorToDeviceCTRL class, ensuring that it returns the
     * correct list of devices in a room by isolating the behavior of
     * CommonListOfRooms and CommonListOfDevices using mock objects.

     * The test sets up mock objects for House, SensorCatalogue, Room, and DeviceDTO.
     * It then constructs an instance of AddSensorToDeviceCTRL with the mock objects,
     * isolates the behavior of CommonListOfRooms and CommonListOfDevices using
     * MockedConstruction, and verifies that calling getListOfDevices returns the
     * expected list of devices in a specified room.
     */
    @Test
    void getListOfDevicesInARoom_IsolationTest() {
        // Arrange
        House houseDouble = mock(House.class);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);

        Room roomDouble = mock(Room.class);

        DeviceDTO deviceDtoDouble = mock(DeviceDTO.class);

        ArrayList<DeviceDTO> arrayListDevice = new ArrayList<>();
        arrayListDevice.add(deviceDtoDouble);

        String roomName = "Bathroom";
        int expected = 1;

        // Act + Assert
        try (MockedConstruction<CommonListOfRooms> commonListOfRoomsMockedConstruction = mockConstruction(CommonListOfRooms.class);
             MockedConstruction<CommonListOfDevices> commonListOfDevicesMockedConstruction = mockConstruction(CommonListOfDevices.class)) {

            AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(houseDouble, sensorCatalogueDouble);

            List<CommonListOfRooms> mockedRoomsList = commonListOfRoomsMockedConstruction.constructed();
            CommonListOfRooms commonListOfRoomsMocked = mockedRoomsList.get(0);
            when(commonListOfRoomsMocked.getRoomByName(roomName)).thenReturn(roomDouble);

            List<CommonListOfDevices> mockedDeviceList = commonListOfDevicesMockedConstruction.constructed();
            CommonListOfDevices commonListOfDevicesMocked = mockedDeviceList.get(0);
            when(commonListOfDevicesMocked.getListOfDevices(roomDouble)).thenReturn(arrayListDevice);

            List<DeviceDTO> deviceList = addSensorToDeviceCTRL.getListOfDevices(roomName);
            int result = deviceList.size();

            assertEquals(expected, result);
        }
    }

}
