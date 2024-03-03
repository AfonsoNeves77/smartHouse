package SmartHome.domain.deviceTest;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.ImplFactoryDevice;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class ImplFactoryDeviceTest {

    /**
     * Test 01
     * Unit test to create a device.
     * Arrange a factory, device name, device model and device location.
     * Act to create a device.
     * Assert that the device is created.
     */
    @Test
    void createDevice_success(){
        //Arrange
        ImplFactoryDevice factory = new ImplFactoryDevice();
        String deviceName = "OTPX";
        String deviceModel = "XPTO";
        String deviceLocation = "Sala1";
        //Act
        Device device = factory.createDevice(deviceName,deviceModel,deviceLocation);
        //Assert
        assertNotNull(device);

    }

    /**
     * Test 02
     * Unit test to create a device with an invalid name.
     * Arrange a factory, device name, device model and device location.
     * Act to create a device with an invalid name.
     * Assert that an exception is thrown.
     */
    @Test
    void createDevice_throwsExceptionIfDeviceNameEmpty(){
        //Arrange
        ImplFactoryDevice factory = new ImplFactoryDevice();
        String deviceName = " ";
        String deviceModel = "XPTO";
        String deviceLocation = "Sala1";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            factory.createDevice(deviceName,deviceModel,deviceLocation));
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Unit test to create a device with a null name.
     * Arrange a factory, device name, device model and device location.
     * Act to create a device with a null name.
     * Assert that an exception is thrown.
     */
    @Test
    void createDevice_throwsExceptionIfDeviceNameNull() {
        //Arrange
        ImplFactoryDevice factory = new ImplFactoryDevice();
        String deviceName = null;
        String deviceModel = "XPTO";
        String deviceLocation = "Sala1";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            factory.createDevice(deviceName,deviceModel,deviceLocation));
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
}
