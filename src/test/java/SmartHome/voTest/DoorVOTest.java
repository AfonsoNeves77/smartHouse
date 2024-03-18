package SmartHome.voTest;

import SmartHome.domain.actuator.BlindRollerActuator;
import SmartHome.vo.DoorVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DoorVOTest {
    @Test
    void givenNullParameter_returnsException(){
        // Arrange
        String door = null;
        String expected = "Invalid Paramaters";
        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new DoorVO(door);
        });
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    @Test
    void givenEmptyParameter_returnsException(){
        // Arrange
        String door = " ";
        String expected = "Invalid Paramaters";
        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new DoorVO(door);
        });
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    @Test
    void callingGetDoor_returnsDoorString(){
        // Arrange
        String door = "4A";
        String expected = "4A";
        DoorVO doorVO = new DoorVO(door);
        // Act
        String result = doorVO.getValue();
        // Assert
        assertEquals(expected,result);
    }
}
