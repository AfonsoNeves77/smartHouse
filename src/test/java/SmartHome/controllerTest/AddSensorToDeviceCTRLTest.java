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
import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
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
     *
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
     *
     * Integration Test to add an already existing sensor to a device (same name).
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
     * Integration test for adding a sensor to a device when the device is deactivated.

     * This test case ensures that attempting to add a sensor to a deactivated device
     * should not succeed. It covers the integration of various components including
     * house configuration, room creation, device activation/deactivation, sensor catalog,
     * and the controller responsible for adding a sensor to a device.

     * The test performs the following steps:
     *   - Creates a test house with a room and a deactivated device.
     *   - Attempts to add a sensor to the deactivated device using the controller.
     *   - Verifies that the addition of the sensor to the deactivated device does not succeed.

     * Note: The test assumes that devices are initialized as active by default and must be
     * deactivated explicitly before attempting to add a sensor.
     *
     * @throws InstantiationException if there is an issue with instantiation during the test setup.
     */

    @Test
    void addingSensorToDeviceIntegrationTest_shouldNotSucceedForDeactivatedDevice() throws InstantiationException {
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

        //Must deactivate device before attempting to add a sensor because devices are for default initialized as active!
        Device selectedDevice = room.getListOfDevices().get(0);
        selectedDevice.deactivateDevice();

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
    void addingSensorToDeviceIntegrationTest_shouldNotSucceedInadequateExternalService() throws InstantiationException {
        //Arrange
        House house1 = new House("testHouse");
        ExternalServices invalidService = new SunTimeCalculator();
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
        boolean result = addSensorCtrl.addSensorToDevice(sensorName,sensorType,deviceName,invalidService);
        //Assert
        assertFalse(result);
    }

    /**
     * Integration test for retrieving the list of rooms from the AddSensorToDeviceCTRL controller.

     * This test case ensures that the controller returns the expected list of rooms
     * from the house configuration. It covers the integration of house setup, room creation,
     * and the controller responsible for managing sensors.

     * The test performs the following steps:
     *   - Creates a test house with a specific room.
     *   - Initializes the AddSensorToDeviceCTRL controller with the test house.
     *   - Retrieves the list of rooms from the controller.
     *   - Verifies that the retrieved room name matches the expected room name.
     *
     * @throws InstantiationException if there is an issue with instantiation during the test setup.
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
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");

        AddSensorToDeviceCTRL addSensorCtrl = new AddSensorToDeviceCTRL(house1, catalogue);

        //Act
        String result = addSensorCtrl.getListOfRooms().get(0).getRoomName();

        //Assert
        assertEquals(roomName, result);
    }

    /**
     * Integration test for retrieving the list of sensor types from the AddSensorToDeviceCTRL controller.

     * This test case ensures that the controller returns the expected list of sensor types
     * from the sensor catalog. It covers the integration of house setup, sensor catalog initialization,
     * and the controller responsible for managing sensors.

     * The test performs the following steps:
     *   - Creates a test house without specific rooms.
     *   - Initializes the AddSensorToDeviceCTRL controller with the test house and a sensor catalog.
     *   - Retrieves the list of sensor types from the controller.
     *   - Verifies that the retrieved sensor type matches the expected sensor type.
     *
     * @throws InstantiationException if there is an issue with instantiation during the test setup.
     */
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
        try (MockedConstruction<CommonListOfRooms> commonListOfRoomsMockedConstruction = mockConstruction(CommonListOfRooms.class,(mock,context)
            -> {
                    when(mock.getListOfRooms()).thenReturn(arrayListRooms);
                }
        ); MockedConstruction<CommonListOfDevices> commonListOfDevicesMockedConstruction = mockConstruction(CommonListOfDevices.class)) {

            AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(houseDouble, sensorCatalogueDouble);
            List<RoomDTO> roomList = addSensorToDeviceCTRL.getListOfRooms();
            int roomListSize = roomList.size();

            List<CommonListOfRooms> mockedRoomsList = commonListOfRoomsMockedConstruction.constructed();
            int numberOfConstructedObjects = mockedRoomsList.size();

            assertEquals(roomListSize,expected);
            assertEquals(numberOfConstructedObjects,expected);
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
    void getListOfRoomsDevicesInARoom_isolationTest() {
        // Arrange
        House houseDouble = mock(House.class);
        SensorCatalogue sensorCatalogueDouble = mock(SensorCatalogue.class);
        String roomName = "Bathroom";
        Room roomDouble = mock(Room.class);

        DeviceDTO deviceDTO = mock(DeviceDTO.class);
        ArrayList<DeviceDTO> listOfDevicesDto = new ArrayList<>();
        listOfDevicesDto.add(deviceDTO);


        int expected = 1;

        // Act
        try (MockedConstruction<CommonListOfRooms> commonListOfRoomsMockedConstruction = mockConstruction(CommonListOfRooms.class,(mock,context)
                        -> {
                    when(mock.getRoomByName(roomName)).thenReturn(roomDouble);
                }
        ); MockedConstruction<CommonListOfDevices> commonListOfDevicesMockedConstruction = mockConstruction(CommonListOfDevices.class,(mock,context)
             ->{
                    when(mock.getListOfDevices(roomDouble)).thenReturn(listOfDevicesDto);
                }
        )) {

            AddSensorToDeviceCTRL addSensorToDeviceCTRL = new AddSensorToDeviceCTRL(houseDouble, sensorCatalogueDouble);

            List<DeviceDTO> listOfDevices = addSensorToDeviceCTRL.getListOfDevices(roomName);
            int result = listOfDevices.size();

            //Assert

            assertEquals(result,expected);
        }
    }


}
