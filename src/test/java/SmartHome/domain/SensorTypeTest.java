package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeTest
{

    @Test
    void NewValidSensorType() throws InstantiationException
    {
        // arrange

        // act
        SensorType sensorType = new SensorType( "Humidity", Unit.Humidity );

        // assert
        assertEquals( sensorType.getDescription(), "Humidity");
        assertEquals( sensorType.getUnit(), Unit.Humidity );
    }

    @Test
    void NewEmptyDescriptionSensorType()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
            new SensorType( "", Unit.Humidity )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNullDescriptionSensorType()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows( InstantiationException.class, () ->
                new SensorType( null, Unit.Humidity )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}