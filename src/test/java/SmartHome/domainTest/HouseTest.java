package SmartHome.domainTest;

import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.location.Address;
import SmartHome.domain.location.FactoryLocation;
import SmartHome.domain.location.GPS;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseTest {
    @Test
    void houseConstructor_throwsIllegalArgumentExceptionIfNameEmpty(){
        //Arrange
        String houseName = " ";
        String expected = "Please insert a valid house name.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new House(houseName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void houseConstructor_throwsIllegalArgumentExceptionIfNameNull(){
        //Arrange
        String houseName = null;
        String expected = "Please insert a valid house name.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new House (houseName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void configureLocation_Success( ) throws InstantiationException{
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 0;
        FactoryLocation factoryLocationDouble = mock(FactoryLocation.class);
        Address address = new Address(doorReference,buildingNumber,streetName,city,country,zipCode);
        GPS gps = new GPS(latitude, longitude);
        when(factoryLocationDouble.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode)).thenReturn(address);
        when(factoryLocationDouble.createGPS(latitude, longitude)).thenReturn(gps);
        //Act
        boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocationDouble);
        //Assert
        assertTrue(result);
    }
    @Test
    void configureLocation_FalseDueToFailAddress() throws InstantiationException {
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String doorReference = " ";
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
        //Act
        boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocationDouble);
        //Assert
        assertFalse(result);
    }
    @Test
    void configureLocation_FalseDueToFailGPS() throws InstantiationException {
        //Arrange
        String houseName = "House";
        House myHouse = new House(houseName);
        String doorReference = " ";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 200;
        FactoryLocation factoryLocationDouble = mock(FactoryLocation.class);
        Address addressDouble = mock(Address.class);
        when(factoryLocationDouble.createGPS(latitude, longitude)).thenThrow(InstantiationException.class);
        when(factoryLocationDouble.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode)).thenReturn(addressDouble);
        //Act
        boolean result = myHouse.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocationDouble);
        //Assert
        assertFalse(result);
    }

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
