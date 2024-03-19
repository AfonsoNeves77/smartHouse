package SmartHome.voTest;

import SmartHome.vo.DeviceModelVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeviceModelVOTest {

    @Test
    void whenNullDeviceModel_thenThrowsInstantiationException(){
        //Arrange
        String deviceModel = null;
        String expected = "Device model cannot be null neither blank.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            new DeviceModelVO(deviceModel);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void whenBlankDeviceModel_thenThrowsInstantiationException(){
        //Arrange
        String deviceModel = " ";
        String expected = "Device model cannot be null neither blank.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            new DeviceModelVO(deviceModel);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void whenEmptyDeviceModel_thenThrowsInstantiationException(){
        //Arrange
        String deviceModel = "";
        String expected = "Device model cannot be null neither blank.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() -> {
            new DeviceModelVO(deviceModel);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void whenToString_ShouldReturnDeviceModelAsString() {
        //Arrange
        String deviceModel = "Model HKT";
        DeviceModelVO modelVO = new DeviceModelVO(deviceModel);

        //Act
        String result = modelVO.toString();

        //Assert
        assertEquals(deviceModel, result);
    }

}