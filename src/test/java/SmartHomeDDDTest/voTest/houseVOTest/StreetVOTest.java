package SmartHomeDDDTest.voTest.houseVOTest;

import SmartHomeDDD.vo.houseVO.StreetVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StreetVOTest {

    @Test
    void givenValidStreet_whenCreatingStreetVO_thenCreateStreetVO() {
//         Arrange
        String street = "Test";
//        Act
        StreetVO streetVO = new StreetVO(street);
        String result = streetVO.getValue();
//        Assert
        assertEquals(street, result);
    }

    @Test
    void givenNullStreet_whenCreatingStreetVO_thenThrowException() {
//         Arrange
        String street = null;
        String expected = "Invalid parameters.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StreetVO(street);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    @Test
    void givenBlankStreet_whenCreatingStreetVO_thenThrowException() {
//         Arrange
        String street = " ";
        String expected = "Invalid parameters.";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new StreetVO(street);
        });
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }
}
