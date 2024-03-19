package SmartHome.voTest;

import SmartHome.vo.RoomLengthVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomLengthVOTest {
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