package SmartHome.voTest;

import SmartHome.vo.DeviceStatusVO;
import SmartHome.vo.RoomFloorVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DeviceStatusVOTest {

    @Test
    void givenFalseStatus_ShouldReturnFalse(){
        //Arrange
        boolean status = false;
        //Act
        DeviceStatusVO statusVO = new DeviceStatusVO(status);
        boolean result = statusVO.getDeviceStatus();
        //Assert
        assertEquals(status, result);
    }

    @Test
    void givenTrueStatus_ShouldReturnTrue(){
        //Arrange
        boolean status = true;
        //Act
        DeviceStatusVO statusVO = new DeviceStatusVO(status);
        boolean result = statusVO.getDeviceStatus();
        //Assert
        assertEquals(status, result);
    }
    @Test
    void givenTrueStatus_ShouldGetTrue(){
        //Arrange
        boolean status = true;
        boolean expected = true;
        DeviceStatusVO statusVO = new DeviceStatusVO(status);
        // Act
        boolean result = statusVO.getDeviceStatus();
        // Assert
        assertEquals(expected,result);
    }
    @Test
    void givenFalseStatus_ShouldGetFalse(){
        //Arrange
        boolean status = true;
        boolean expected = true;
        DeviceStatusVO statusVO = new DeviceStatusVO(status);
        // Act
        boolean result = statusVO.getDeviceStatus();
        // Assert
        assertEquals(expected,result);
    }
}
