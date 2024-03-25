package SmartHome.ddd;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SensorIDVOTest {
    /**
     * Verifies that an IllegalArgumentException is thrown when a null identifier is given.
     * The exception message thrown should be "Invalid Identifier".
     */
    @Test
    void whenIdentifierIsNull_ThenThrowsIllegalArgumentException(){
        //Assert
        UUID identifier = null;
        String expected = "Invalid Identifier";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           new SensorIDVO(identifier);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that the getID method returns the correct sensor identifier in a string format.
     * The single collaborator is the UUID library, so it was not mocked.
     */
    @Test
    void givenValidIdentifier_whenGetID_ThenReturnSensorIDAsString(){
        //Assert
        UUID identifier = UUID.randomUUID();
        String expected = identifier.toString();

        SensorIDVO sensorID = new SensorIDVO(identifier);

        //Act
        String result = sensorID.getID();

        //Assert
        assertEquals(expected, result);
    }

}
