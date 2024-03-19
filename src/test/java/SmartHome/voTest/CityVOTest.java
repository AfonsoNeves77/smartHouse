package SmartHome.voTest;

import SmartHome.vo.CityVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CityVOTest {

    @Test
    void givenValidCity_whenCreatingCityVO_thenCreateCityVO() {
//         Arrange
        String city = "Test";
//        Act
        CityVO cityVO = new CityVO(city);
        String result = cityVO.getCity();
//        Assert
        assertEquals(city, result);
    }

    @Test
    void givenNullCity_whenCreatingCityVO_thenThrowException() {
//         Arrange
        String city = null;
        String expected = "Invalid parameters.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CityVO(city);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    @Test
    void givenBlankCity_whenCreatingCityVO_thenThrowException() {
//         Arrange
        String city = " ";
        String expected = "Invalid parameters.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new CityVO(city);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }
}
