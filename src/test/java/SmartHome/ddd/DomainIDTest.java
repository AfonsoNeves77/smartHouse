package SmartHome.ddd;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DomainIDTest {

    @Test
    public void givenNullIdentifier_ShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid Identifier";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RoomID(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);

    }

    @Test
    public void getIDShouldReturnCorrectIDAsString(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();
        String expected = roomIdentifier.toString();

        //Act
        RoomID roomID = new RoomID(roomIdentifier);
        String result = roomID.getID();

        //Assert
        assertEquals(expected,result);

    }



}
