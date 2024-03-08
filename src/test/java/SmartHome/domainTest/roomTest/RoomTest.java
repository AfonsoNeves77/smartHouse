package SmartHome.domainTest.roomTest;


import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.device.ListOfDevices;
import SmartHome.domain.room.Room;
import SmartHome.domain.room.RoomDimensions;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoomTest {

    /////////////////////////////////////////ISOLATION///////////////////////////////////////////////////////
    /**
     * This isolation tests attempts to create a room object without a valid name. Regardless of the object Room Dimensions
     * being a collaborator, this object will not be mocked as the tests is supposed to fail before reaching the object instantiation.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfNameEmpty() {
        // Arrange
        String name = " ";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        String expected = "Please insert valid room name.";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new Room(name, floor, roomWidth, roomLength, roomHeight));
        String result = exception.getMessage();

        // assert
        assertEquals(expected, result);
    }

    /**
     * This isolation tests attempts to create a room object without a valid name. Regardless of the object Room Dimensions
     * being a collaborator, this object will not be mocked as the tests is supposed to fail before reaching the object instantiation.
     */
    @Test
    void constructor_throwsInstantiationExceptionIfNameNull() {
        // Arrange
        String name = null;
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        String expected = "Please insert valid room name.";

        // act
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room(name, floor, roomWidth, roomLength, roomHeight));
        String result = exception.getMessage();

        // assert
        assertEquals(expected, result);
    }

    /**
     * This test ensures the method getDimensions().
     * A MockedConstruction of ListOfDevices and RoomDimensions is made. RoomDimensions behaviour is set.
     * @throws InstantiationException Room dimensions.
     */
    @Test
    void getDimensions_SuccessfullyReturnsDimensions() throws InstantiationException {
        //Arrange
        String name = "Name1";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        double[] expected = {4.5, 8.5, 2};

        try (MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class);
             MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class, (mock, context) -> {
                 double actualRoomWidth = (double) context.arguments().get(0);
                 double actualRoomLength = (double) context.arguments().get(1);
                 double actualRoomHeight = (double) context.arguments().get(2);
                 when(mock.getRoomWidth()).thenReturn(actualRoomWidth);
                 when(mock.getRoomLength()).thenReturn(actualRoomLength);
                 when(mock.getRoomHeight()).thenReturn(actualRoomHeight);
             })) {

            Room room = new Room(name, floor, roomWidth, roomLength, roomHeight);

            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();
            // Act
            double[] result = room.getDimensions();
            // Assert
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            assertArrayEquals(expected, result);
        }
    }

    /**
     * Isolation test to get room name.
     * A MockedConstruction of ListOfDevices and RoomDimensions is made.
     * @throws InstantiationException If room parameters are invalid
     */
    @Test
    void getRoomName() throws InstantiationException {
        // arrange
        String name = "Name1";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        try (MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class);
             MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class)) {

            Room room = new Room(name, floor, roomWidth, roomLength, roomHeight);

            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();

            // act
            String result = room.getRoomName();

            // assert
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            assertEquals(name, result);
        }
    }

    /**
     * Isolation test to get room house floor.
     * A MockedConstruction of ListOfDevices and RoomDimensions is made.
     * @throws InstantiationException If room parameters are invalid
     */
    @Test
    void getHouseFloor() throws InstantiationException {
        // arrange
        String name = "Name1";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;

        try (MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class);
             MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class)) {

            Room room = new Room(name, floor, roomWidth, roomLength, roomHeight);

            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();

            // act
            int result = room.getHouseFloor();

            // assert
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            assertEquals(floor, result);
        }
    }

    /**
     * Isolation Test to add a device to a room.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Arrange a device name and a device model.
     * Act to add a device to the room.
     * Assert that the device was successfully added to the room.
     * @throws InstantiationException If room parameters are invalid
     */
    @Test
    void addDevice_IsolationTest() throws InstantiationException {
        //Arrange
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;

        String deviceName = "Heater";
        String deviceModel = "XPTO3000";

        ImplFactoryDevice implFactoryDeviceDouble = mock(ImplFactoryDevice.class);

        try(MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class,(mock, context)-> {
            when(mock.addDeviceToList(deviceName,deviceModel, roomName, implFactoryDeviceDouble)).thenReturn(true);
        });
            MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class))
        {

            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();

            Room room = new Room(roomName, floor, roomWidth, roomLength, roomHeight);

            //Act
            boolean result = room.addDevice(deviceName, deviceModel, implFactoryDeviceDouble);
            //Assert
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            assertTrue(result);
        }
    }

    /**
     * Isolation Test to get the list of devices in a room.
     * Arrange a room name, a house floor, a room width, a room length and a room height.
     * Act to get the list of devices in the room.
     * Assert that the list of devices was successfully retrieved and that the device in it is the correct one,
     * by requesting its name.
     * @throws InstantiationException If room parameters are invalid
     */
    @Test
    void getListOfDevices_IsolationTest() throws InstantiationException {
        //Arrange
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;

        String deviceName = "Heater";
        Device deviceDouble = mock(Device.class);
        when(deviceDouble.getDeviceName()).thenReturn(deviceName);

        List<Device> deviceList = new ArrayList<>();
        deviceList.add(deviceDouble);

        try(MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class,(mock, context)-> {
            when(mock.getDeviceList()).thenReturn(deviceList);
        });
            MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class,(mock, context)-> {
            })) {
            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();

            Room room = new Room(roomName,floor,roomWidth,roomLength,roomHeight);

            //Act
            String result = room.getListOfDevices().get(0).getDeviceName();
            //Assert
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            assertEquals(deviceName,result);
        }
    }

    /**
     * This test ensures that when a Room is instantiated without any devices, it has no functionalities,
     * and the ListOfDevices and RoomDimension objects are correctly accessed during their construction.
     * A MockedConstruction of ListOfDevices and RoomDimensions is made. ListOfDevices behaviour is set.
     * Two scenarios are present:
     * 1. Asserts that the ListOfDevices and RoomDimensions constructors were called exactly once during the
     * construction of the House.
     * 2. The Room functionalities' map should have size 0, verifying that the Room does not have functionalities when
     * there are no devices.
     * @throws InstantiationException If room parameters are invalid.
     */
    @Test
    void getRoomFunctionalitiesAndDimensions_RoomWithoutDevices_IsolationTest() throws InstantiationException {
        //Arrange
        String roomName = "Room Test";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        List<Device> deviceList = new ArrayList<>();

        int expected = 0;

        try(MockedConstruction<ListOfDevices> listOfDevicesDouble = mockConstruction(ListOfDevices.class,(mock, context)-> {
            when(mock.getDeviceList()).thenReturn(deviceList);
        });
            MockedConstruction<RoomDimensions> roomDimensionsDouble = mockConstruction(RoomDimensions.class)) {

            List<ListOfDevices> deviceLists = listOfDevicesDouble.constructed();
            List<RoomDimensions> roomDimensionsList = roomDimensionsDouble.constructed();

            Room room = new Room(roomName,floor,roomWidth,roomLength,roomHeight);

            //Act
            int result = room.getRoomFunctionalities().size();

            //Assert
            // 1.
            assertEquals(1,deviceLists.size());
            assertEquals(1,roomDimensionsList.size());
            // 2.
            assertEquals(expected,result);
        }
    }


    /////////////////////////////////////////INTEGRATION///////////////////////////////////////////////////////

    /**
     * Integration test to get room functionalities in R
     * @throws InstantiationException if parameters for any object instantiation are invalid
     */
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