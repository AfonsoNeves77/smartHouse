package SmartHome.domainTest;

import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.location.Address;
import SmartHome.domain.location.FactoryLocation;
import SmartHome.domain.location.GPS;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.ListOfRooms;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.ListOfSensors;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mockConstruction;

class HouseTest {

    //////////////////////////////////////ISOLATION///////////////////////////////////////////////////////////
    /**
     * Test to ensure House cannot be instantiated with an empty name
     */
    @Test
    void houseConstructor_throwsInstantiationExceptionIfNameEmpty(){
        //Arrange
        String houseName = " ";
        String expected = "Please insert a valid house name.";

        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class)){
            //Act
            Exception exception = assertThrows(InstantiationException.class, () ->
                    new House(houseName));
            String result = exception.getMessage();
            //Assert
            assertEquals(expected,result);

            // Just as a curiosity, the mocked construction is created even if the house fails to instantiate.
            List<ListOfRooms> listOfMockedListOfRooms = listOfRoomsMockedConstruction.constructed();
            assertEquals(1,listOfMockedListOfRooms.size());
        }
    }

    /**
     * Test to ensure House cannot be instantiated with a null name
     */
    @Test
    void houseConstructor_throwsInstantiationExceptionIfNameNull(){
        //Arrange
        String houseName = null;
        String expected = "Please insert a valid house name.";
        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class)){
            //Act
            Exception exception = assertThrows(InstantiationException.class, () ->
                new House(houseName));
            String result = exception.getMessage();
            //Assert
            assertEquals(expected,result);

            // Just as a curiosity, the mocked construction is created even if the house fails to instantiate.
            List<ListOfRooms> listOfMockedListOfRooms = listOfRoomsMockedConstruction.constructed();
            assertEquals(1,listOfMockedListOfRooms.size());
        }
    }


    /**
     * Test to configure House Location by mocking all of its collaborators and dependencies involved.
     * Successful configuration.
     * @throws InstantiationException If invalid House name
     */
    @Test
    void configureLocation_Success( ) throws InstantiationException{
        //Arrange
        String houseName = "House";
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 0;

        FactoryLocation factoryLocationDouble = mock(FactoryLocation.class);
        Address addressDouble = mock(Address.class);
        GPS gpsDouble = mock(GPS.class);
        when(factoryLocationDouble.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode)).thenReturn(addressDouble);
        when(factoryLocationDouble.createGPS(latitude, longitude)).thenReturn(gpsDouble);

        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class)) {

            House myHouse = new House(houseName);
            List<ListOfRooms> listOfMockedListOfRooms = listOfRoomsMockedConstruction.constructed();

            //Act
            boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode, latitude, longitude, factoryLocationDouble);

            //Assert
            assertTrue(result);
            assertEquals(1,listOfMockedListOfRooms.size());
        }
    }

    /**
     * Test to configure House Location by mocking all of its collaborators and dependencies involved.
     * Address instantiation is not possible, so configuration is unsuccessful.
     * @throws InstantiationException If invalid House name
     */
    @Test
    void configureLocation_FalseDueToInvalidGPS() throws InstantiationException {
        //Arrange
        String houseName = "House";
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 0;

        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class)) {

            FactoryLocation factoryLocationDouble = mock(FactoryLocation.class);
            Address addressDouble = mock(Address.class);

            when(factoryLocationDouble.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode)).thenReturn(addressDouble);
            when(factoryLocationDouble.createGPS(latitude, longitude)).thenThrow(InstantiationException.class);

            House myHouse = new House(houseName);
            List<ListOfRooms> listOfMockedListOfRooms = listOfRoomsMockedConstruction.constructed();

            //Act
            boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode, latitude, longitude, factoryLocationDouble);

            //Assert
            assertFalse(result);
            assertEquals(1,listOfMockedListOfRooms.size());
        }
    }

    /**
     * Test to configure House Location by mocking all of its collaborators and dependencies involved.
     * GPS instantiation is not possible, so configuration is unsuccessful.
     * @throws InstantiationException If invalid House name
     */
    @Test
    void configureLocation_FalseDueToInvalidAddress() throws InstantiationException {
        //Arrange
        String houseName = "House";
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 0;

        FactoryLocation factoryLocationDouble = mock(FactoryLocation.class);
        GPS gpsDouble = mock(GPS.class);
        when(factoryLocationDouble.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode)).thenThrow(InstantiationException.class);
        when(factoryLocationDouble.createGPS(latitude, longitude)).thenReturn(gpsDouble);

        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class)) {

            House myHouse = new House(houseName);
            List<ListOfRooms> listOfMockedListOfRooms = listOfRoomsMockedConstruction.constructed();

            //Act
            boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode, latitude, longitude, factoryLocationDouble);

            //Assert
            assertFalse(result);
            assertEquals(1,listOfMockedListOfRooms.size());
        }
    }

    /**
     * This test adds a room to the house's list of rooms object. The challenge in performing an isolation test is the
     * fact that the ListOfRooms object is created on House instantiation.
     * 1. Arranges the room parameters and factory room double.
     * 2. This step creates a mocked construction of the object ListOfRooms. In order for this double to be used by the
     * house, when the House is instantiated, the instantiation of House must be performed after this mocked construction.
     * When this happens, the house, when instantiated, will use the double for ListOfRooms, instead of creating a new one.
     * 3. After the house is instantiated, the mocked construction will automatically instantiate a listOfRoomsDouble. But
     * now, to ensure we can create a stub for the ListOfRooms object's addRoom, we must access it. In order to access the
     * object ListOfRoomsDouble, we must use the expression .constructed(), which will list all instances of the ListOfRooms
     * double. To access the double that was created by instantiating the house, we must use get(0) and save that double into
     * a variable (As opposite to a normal creation of a mock, the use of mocked constructions does not give us direct access
     * to the created object). After saving the double into a variable, we then create a stub of its behaviour, for this
     * test we want that method to return true.
     * 4. Finally, considering the house is instantiated and has a double ListOfRooms object. We finally call addRoom,
     * resulting into success.
     * 5. Asserts that the ListOfRooms constructor was called exactly once upon House construction. Then asserts that
     * Room is successfully added.
     * @throws InstantiationException If House/Room parameters invalid.
     */
    @Test
    void addRoom_Success_Isolation() throws InstantiationException {
        //Arrange
        // 1.
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        FactoryRoom factoryRoomDouble = mock(FactoryRoom.class);

        // 2.
        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class,(mock,context) -> {
            when(mock.addRoomToList(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoomDouble)).thenReturn(true);
        })) {

        // 3.
            House house = new House("House1");
            List<ListOfRooms> mockedListOfRoomsList = listOfRoomsMockedConstruction.constructed();

        // 4
            // Act
            boolean result = house.addRoom(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoomDouble);
        // 5
            // Assert
            // 5.1
            assertEquals(1,mockedListOfRoomsList.size());
            // 5.2
            assertTrue(result);
        }
    }

    /**
     * Utilizing the same testing strategy above, it returns false due to being unable to add a room.
     * @throws InstantiationException If house name invalid
     */
    @Test
    void addRoom_UnableToAddReturningFalse_Isolation() throws InstantiationException {
        //Arrange
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        FactoryRoom factoryRoomDouble = mock(FactoryRoom.class);

        try (MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class, (mock, context)
                -> {
            when(mock.addRoomToList(roomName,floor,roomWidth,roomLength,roomHeight,factoryRoomDouble)).thenReturn(false);
        })) {

            House house = new House("House1");
            List<ListOfRooms> mockedListOfRoomsList = listOfRoomsMockedConstruction.constructed();

            // Act
            boolean result = house.addRoom(roomName,floor,roomWidth,roomLength,roomHeight,factoryRoomDouble);

            //Assert
            assertFalse(result);
            assertEquals(1,mockedListOfRoomsList.size());
        }
    }


    /**
     * This test ensures that when a House is instantiated without any rooms, its functionalities are empty,
     * and the ListOfRooms object is correctly accessed during its construction.
     * A MockedConstruction of ListOfRooms is made and then its behaviour is set once getRoomList() method is called.
     * Two scenarios are present:
     * 1. Asserts that the ListOfRooms constructor was called exactly once during the construction of the House.
     * 2. The house functionalities' map should have size 0, verifying that the house functionalities are empty when
     * there are no rooms.
     * @throws InstantiationException If house name is invalid.
     */
    @Test
    void getHouseFunctionalities_HouseWithoutRooms_IsolationTest() throws InstantiationException {
        //Arrange
        String houseName = "House Test";
        List<Room> roomList = new ArrayList<>();
        int expected = 0;

        try(MockedConstruction<ListOfRooms> listOfRoomsDouble = mockConstruction(ListOfRooms.class,(mock, context)-> {
            when(mock.getRoomList()).thenReturn(roomList);
            })) {

            List<ListOfRooms> roomLists = listOfRoomsDouble.constructed();

            House house = new House(houseName);

            //Act
            int result = house.getHouseFunctionalities().size();

            //Assert
            // 1.
            assertEquals(1,roomLists.size());
            // 2.
            assertEquals(expected,result);
        }
    }

    /**
     * This test validates that the method is able to return an empty list. It also checks how many doubles were created.
     * @throws InstantiationException On invalid house parameters
     */
    @Test
    void getListOfRooms_SuccessfullyDeliversEmptyList() throws InstantiationException{
        //Arrange
        String houseName = "House Test";
        List<Room> roomList = new ArrayList<>();

        try(MockedConstruction<ListOfRooms> listOfRoomsMockedConstruction = mockConstruction(ListOfRooms.class,(mock, context)-> {
            when(mock.getRoomList()).thenReturn(roomList);
        })) {

            List<ListOfRooms> listOfRoomsMockedConstructionList = listOfRoomsMockedConstruction.constructed();

            House house = new House(houseName);

            List<Room> expected = new ArrayList<>();
            //Act
            List<Room> result = house.getListOfRooms();

            //Assert
            // 1.
            assertEquals(expected,result);
            assertEquals(1,listOfRoomsMockedConstructionList.size());
        }
    }

    //////////////////////////////////////INTEGRATION///////////////////////////////////////////////////////////


    /**
     * Successfully adds a Room to the House.
     * @throws InstantiationException If House name is invalid.
     */
    @Test
    void addRoom_Success() throws InstantiationException {
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        //Act
        boolean result = myHouse.addRoom(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoom);
        //Assert
        assertTrue(result);
    }

    /**
     * Test that aims to check that it is not possible to add a second Room with the same name (Room identifier).
     * @throws InstantiationException If House name is invalid.
     */
    @Test
    void addRoom_RoomAlreadyExists() throws InstantiationException {
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = 4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        myHouse.addRoom(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoom);
        //Act
        boolean result = myHouse.addRoom(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoom);
        //Assert
        assertFalse(result);
    }

    /**
     * Test that aims to check that it is not possible to add a Room with invalid dimensions (in this case, room width).
     * @throws InstantiationException If House name is invalid.
     */
    @Test
    void addRoom_InvalidRoomWidth() throws InstantiationException {
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String roomName = "Kitchen";
        int floor = 0;
        double roomWidth = -4.5;
        double roomLength = 8.5;
        double roomHeight = 2;
        FactoryRoom factoryRoom = new FactoryIndoorRoom();
        //Act
        boolean result = myHouse.addRoom(roomName, floor, roomWidth, roomLength, roomHeight, factoryRoom);
        //Assert
        assertFalse(result);
    }

    /**
     * Tests that aims to assert that House has 2 types of functionalities, even tough that 2 or more sensors of the
     * same type were added.
     * @throws InstantiationException If House name is invalid.
     */
    @Test
    void getHouseFunctionalities_HouseWithSeveralFunctionalities() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
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
        int result = house.getHouseFunctionalities().size();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that aims to assert that House has no functionalities, since it has no sensors.
     * @throws InstantiationException If House name is invalid.
     */
    @Test
    void getHouseFunctionalities_HouseWithNoFunctionalities() throws InstantiationException {
        //Arrange
        House house = new House("House Test");
        FactoryRoom roomFactory = new FactoryIndoorRoom();
        FactoryDevice deviceFactory = new ImplFactoryDevice();
        String fileName = "config.properties";

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

        // 4.
        int expected = 0;
        //Act
        // 5.
        int result = house.getHouseFunctionalities().size();
        //Assert
        assertEquals(expected,result);
    }
}
