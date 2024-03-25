package SmartHomeDDDTest.voTest.roomVOTest;


import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class RoomIDVOTest {

    /**
     * Test case to verify that the RoomIDVO constructor throws an IllegalArgumentException
     * when given a null identifier. The result exception description must also match the expected one.
     */
    @Test
    public void givenNullIdentifier_ShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid Identifier";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new RoomIDVO(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);

    }

    /**
     * Test case to verify that the getID method of RoomIDVO returns the correct ID as a string.
     */
    @Test
    public void getIDShouldReturnCorrectIDAsString(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();
        String expected = roomIdentifier.toString();

        //Act
        RoomIDVO roomID = new RoomIDVO(roomIdentifier);
        String result = roomID.getID();

        //Assert
        assertEquals(expected,result);

    }



}
