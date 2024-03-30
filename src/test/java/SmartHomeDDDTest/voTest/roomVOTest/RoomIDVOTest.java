package SmartHomeDDDTest.voTest.roomVOTest;


import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


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

    /**
     * Test case to verify that the equals method of RoomIDVO returns true when comparing the same RoomIDVO object.
     */
    @Test
    public void givenSameRoomIDVO_ShouldReturnTrue(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();

        //Act
        RoomIDVO roomID = new RoomIDVO(roomIdentifier);

        //Assert
        assertTrue(roomID.equals(roomID));

    }

    /**
     * Test case to verify that the equals method of RoomIDVO returns false when comparing a RoomIDVO object with a null object.
     */
    @Test
    public void givenRoomIDVOAndNullObject_ShouldReturnFalse(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();

        //Act
        RoomIDVO roomID = new RoomIDVO(roomIdentifier);

        //Assert
        assertFalse(roomID.equals(null));

    }

    /**
     * Test case to verify that the equals method of RoomIDVO returns true when comparing two RoomIDVO objects with the same identifier.
     */
    @Test
    public void givenTwoRoomIDVOWithSameIdentifier_ShouldReturnTrue(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();

        //Act
        RoomIDVO roomID1 = new RoomIDVO(roomIdentifier);
        RoomIDVO roomID2 = new RoomIDVO(roomIdentifier);

        //Assert
        assertTrue(roomID1.equals(roomID2));

    }

    /**
     * Test case to verify that the equals method of RoomIDVO returns false when comparing two RoomIDVO objects with different identifiers.
     */
    @Test
    public void givenTwoRoomIDVOWithDifferentIdentifier_ShouldReturnFalse(){

        //Arrange
        UUID roomIdentifier1 = UUID.randomUUID();
        UUID roomIdentifier2 = UUID.randomUUID();

        //Act
        RoomIDVO roomID1 = new RoomIDVO(roomIdentifier1);
        RoomIDVO roomID2 = new RoomIDVO(roomIdentifier2);

        //Assert
        assertFalse(roomID1.equals(roomID2));

    }

    /**
     * Test case to verify that the equals method of RoomIDVO returns false when comparing a RoomIDVO object with a different object.
     */
    @Test
public void givenRoomIDVOAndDifferentObject_ShouldReturnFalse(){

        //Arrange
        UUID roomIdentifier = UUID.randomUUID();

        //Act
        RoomIDVO roomID = new RoomIDVO(roomIdentifier);
        Object object = new Object();

        //Assert
        assertFalse(roomID.equals(object));
    }

    /**
     * Test case to verify that the hashCode method of RoomIDVO returns the correct hash code.
     */
    @Test
    public void givenTwoRoomIDVOWithSameIdentifier_ShouldReturnSameHashCode() {
        // Arrange
        UUID roomIdentifier = UUID.randomUUID();

        // Act
        RoomIDVO roomID1 = new RoomIDVO(roomIdentifier);
        RoomIDVO roomID2 = new RoomIDVO(roomIdentifier);

        // Assert
        assertEquals(roomID1.hashCode(), roomID2.hashCode());
    }

    /**
     * Test case to verify that the hashCode method of RoomIDVO returns different hash codes for two RoomIDVO objects with different identifiers.
     */
    @Test
    public void givenTwoRoomIDVOWithDifferentIdentifier_ShouldReturnDifferentHashCode() {
        // Arrange
        UUID roomIdentifier1 = UUID.randomUUID();
        UUID roomIdentifier2 = UUID.randomUUID();

        // Act
        RoomIDVO roomID1 = new RoomIDVO(roomIdentifier1);
        RoomIDVO roomID2 = new RoomIDVO(roomIdentifier2);

        // Assert
        assertNotEquals(roomID1.hashCode(), roomID2.hashCode());
    }
}