package SmartHome.voTest;

import SmartHome.vo.RoomHeightVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomHeightVOTest {
    @Test
    public void shouldCreateAValidVO_height() {
        //Arrange
        double height = 1.5;
        RoomHeightVO vo_height = new RoomHeightVO(height);

        //Act
        double result = vo_height.getHeight();

        //Assert
        assertEquals(height, result);
    }

    @Test
    public void shouldThrowExceptionVO_heightWithInvalidHeight() {
        //Arrange
        double height = -1.7;
        String expectedMessage = "Invalid height value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomHeightVO(height);
        });
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldCreateValidVO_heightWithZeroHeight() {
        //Arrange
        double height = 0.0;
        RoomHeightVO vo_height = new RoomHeightVO(height);

        //Act
        double result = vo_height.getHeight();

        //Assert
        assertEquals(height, result);
    }

}