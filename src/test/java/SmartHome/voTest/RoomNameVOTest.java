package SmartHome.voTest;

import SmartHome.vo.RoomNameVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoomNameVOTest {

    @Test
    void givenNullParameter_returnsException(){
        //Arrange
        String roomName = null;
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomNameVO(roomName);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void givenEmptyParameter_returnsException(){
        //Arrange
        String roomName = "";
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        new RoomNameVO(roomName);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void givenBlankParameter_returnsException(){
        //Arrange
        String roomName = " ";
        String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomNameVO(roomName);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void givenValidParameter_returnsObject(){
        //Arrange
        String roomName = "room";

        //Act
        RoomNameVO roomNameVO = new RoomNameVO(roomName);

        //Assert
        assertNotNull(roomNameVO);
    }

    @Test
    void callingGetDeviceName_returnsDeviceNameString(){
        //Arrange
        String roomName = "room";
        RoomNameVO roomNameVO = new RoomNameVO(roomName);
        String expected = "room";

        //Act
        String result = roomNameVO.getRoomName();

        //Assert
        assertEquals(expected,result);
    }
}
