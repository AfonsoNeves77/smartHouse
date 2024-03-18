package SmartHome.voTest;

import SmartHome.vo.HeightVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeightVOTest {
    @Test
    public void shouldCreateAValidVO_height() {
        //Arrange
        double height = 1.5;
        HeightVO vo_height = new HeightVO(height);

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
            new HeightVO(height);
        });
        String actualMessage = exception.getMessage();

        //Assert
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldCreateValidVO_heightWithZeroHeight() {
        //Arrange
        double height = 0.0;
        HeightVO vo_height = new HeightVO(height);

        //Act
        double result = vo_height.getHeight();

        //Assert
        assertEquals(height, result);
    }

}