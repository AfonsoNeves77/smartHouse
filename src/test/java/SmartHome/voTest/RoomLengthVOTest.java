package SmartHome.voTest;

import SmartHome.vo.RoomLengthVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomLengthVOTest {
    /**
     * Test case to verify that the RoomLengthVO constructor throws an IllegalArgumentException
     * when given a negative length. The result exception description must also match the expected one.
     */
    @Test
    public void shouldCreateAValidVO_length() {
        //Arrange
        double length = 1.0;
        RoomLengthVO vo_length = new RoomLengthVO(length);

        //Act
        double result = vo_length.getLength();

        //Assert
        assertEquals(length, result);
    }

    /**
     * Test case to verify that the RoomLengthVO constructor throws an IllegalArgumentException
     * when given a negative length. The result exception description must also match the expected one.
     */
    @Test
    public void shouldThrowExceptionVO_lengthWithInvalidLength() {
        //Arrange
        double length = -1.0;
        String expectedMessage = "Invalid length value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomLengthVO(length);
        });
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    /**
     * Test case to verify that the RoomLengthVO constructor throws an IllegalArgumentException
     * when given a zero length. The result exception description must also match the expected one.
     */
    @Test
    public void shouldThrowExceptionVO_lengthWithZeroLength() {
        //Arrange
        double length = 0.0;
        String expectedMessage = "Invalid length value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomLengthVO(length);
        });
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

}