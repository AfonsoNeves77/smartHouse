package SmartHome.domain;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceTest
{
    @Test
    void NewValidDevice() throws InstantiationException
    {
        // arrange

        // act
        new Device( "device1");

        // assert
        // currently there is no methods to access the object, hence, for now, there is no way to check it
        // then, for now, if it is created, it is ok
    }

    @Test
    void NewEmptyNameDevice()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Device( "" )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNullNameDevice()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Device( null )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addValidSensor() throws Exception
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor", "SmartHome.sensors.GA100K");
        Catalogue catalogue = new Catalogue( config );
        catalogue.addSensorType("Temperature", Unit.Temperature);

        Device device = new Device( "device1");

        // act
        Sensor sensor = device.addSensor( "SmartHome.sensors.GA100K", catalogue );

        // assert
        assertEquals( sensor.getSensorType().getDescription(), "Temperature" );
        // how to check if sensor was added to the device?
    }

    @Test
    void addInvalidSensor() throws Exception
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        Device device = new Device( "device1");

        // act
        Sensor sensor = device.addSensor( "123", catalogue );

        // assert
        assertNull( sensor );
    }
}