package SmartHome.controller;

import SmartHome.domain.*;
import SmartHome.dto.RoomDTO;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToDeviceControllerTest
{
    @Test
    void NewAddSensorToDeviceController() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        // act
        new AddSensorToDeviceController( house, catalogue );

        // assert
    }

    @Test
    void NewAddSensorToDeviceControllerWithNullHouse()
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
                new AddSensorToDeviceController( null, catalogue )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewAddSensorToDeviceControllerWithNullCatalogue()
    {
        // arrange
        House house = new House();
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
                new AddSensorToDeviceController( house, null )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getNonEmptyRooms() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        house.addRoom( "Living Room", 0, 10, 9, 2.5);
        AddSensorToDeviceController controller = new AddSensorToDeviceController(house, catalogue);

        // act
        List<RoomDTO> roomsDTO = controller.getRooms();

        // assert
        assertEquals(1, roomsDTO.size());
    }

    @Test
    void getEmptyRooms() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        List<RoomDTO> roomsDTO = controller.getRooms();

        // assert
        assertEquals(0, roomsDTO.size());
    }

    @Test
    void getSizeOf2Rooms() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();
        house.addRoom( "Living Room", 0, 10, 9, 2.5);
        house.addRoom( "Bed Room", 1, 4, 3, 2.5);

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        List<RoomDTO> roomsDTO = controller.getRooms();

        // assert
        assertEquals(2, roomsDTO.size());
    }

    @Test
    void getDuplicatedEmptyRooms() throws InstantiationException {

        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        List<RoomDTO> roomsDTO = controller.getRooms();

        // assert
        assertEquals(0, roomsDTO.size());
    }

    @Test
    void getDevicesFromExistingRoomWithoutDevices() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();
        house.addRoom( "Living Room", 0, 10, 9, 2.5);
        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );
        RoomDTO livingRoomDTO = controller.getRooms().get(0);

        // act
        List<Device> devices = controller.getDevicesFromRoom( livingRoomDTO );

        // assert
        assertEquals( devices.size(), 0);
    }

    @Test
    void getDevicesFromExistingRoomWithDevice() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();
        Room livingRoom = house.addRoom( "Living Room", 0, 10, 9, 2.5);
        livingRoom.addDevice("device1");

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );
        RoomDTO livingRoomDTO = controller.getRooms().get(0);

        // act
        List<Device> devices = controller.getDevicesFromRoom( livingRoomDTO );

        // assert
        assertEquals( devices.size(), 1);
    }

    @Test
    void getDevicesFromExistingRoomWith2Devices() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();
        Room livingRoom = house.addRoom( "Living Room", 0, 10, 9, 2.5);
        livingRoom.addDevice("device1");
        livingRoom.addDevice("device2");

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );
        RoomDTO livingRoomDTO = controller.getRooms().get(0);

        // act
        List<Device> devices = controller.getDevicesFromRoom( livingRoomDTO );

        // assert
        assertEquals( devices.size(), 2);
    }

    @Test
    void GetEmptySensorModels() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        List<String> sensorModels = controller.getSensorsModels();

        // assert
        assertEquals( sensorModels.size(), 0);
    }

    @Test
    void GetNonEmptySensorModels() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor", "SmartHome.sensors.GA100K");
        config.addProperty("sensor", "SmartHome.sensors.TSY01");
        Catalogue catalogue = new Catalogue( config );
        House house = new House();

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        List<String> sensorModels = controller.getSensorsModels();

        // assert
        assertEquals( sensorModels.size(), 2);
    }

    @Test
    void AddInexistingSensorModelToDevice() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        House house = new House();
        Room livingRoom = house.addRoom( "Living Room", 0, 10, 9, 2.5);
        Device device = livingRoom.addDevice("device1");

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        Sensor sensor = controller.addSensorToDevice(device, "SmartHome.sensors.GA100K" );

        // assert
        assertNull( sensor );
    }

    @Test
    void AddExistingSensorModelToDevice() throws InstantiationException {
        // arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor", "SmartHome.sensors.GA100K");
        Catalogue catalogue = new Catalogue( config );
        catalogue.addSensorType("Temperature", Unit.Temperature);
        House house = new House();
        Room livingRoom = house.addRoom( "Living Room", 0, 10, 9, 2.5);
        Device device = livingRoom.addDevice("device1");

        AddSensorToDeviceController controller = new AddSensorToDeviceController( house, catalogue );

        // act
        Sensor sensor = controller.addSensorToDevice(device, "SmartHome.sensors.GA100K" );

        // assert
        assertNotNull( sensor );
    }
}