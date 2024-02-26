package SmartHome.sensors;

import SmartHome.domain.Catalogue;
import SmartHome.domain.SensorType;
import SmartHome.domain.Unit;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GA100KTest
{
    @Test
    void NewValidGA100K() throws InstantiationException
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        SensorType sensorType = catalogue.addSensorType( "Temperature", Unit.Temperature );
        // act
        GA100K ga100k = new GA100K( catalogue );

        // assert
        assertEquals( ga100k.getSensorType(), sensorType );
        int value = Integer.parseInt( ga100k.getValue().toString() );
        assertTrue( value >= -70 && value <= 70 );
    }

    @Test
    void NewInexistentSensorTypeForGA100K()
    {
        // arrange
        Configuration config = new PropertyListConfiguration();
        Catalogue catalogue = new Catalogue( config );
        //SensorType sensorType = catalogue.addSensorType( "Temperature", Unit.Temperature );
        String expectedMessage = "SensorType with description 'Temperature' does not exist.";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new GA100K( catalogue )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}