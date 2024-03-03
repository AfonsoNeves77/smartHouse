package SmartHome.domainTest.deviceTest;

import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ImplFactoryDevice;
import SmartHome.domain.device.ListOfDevices;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class
ListOfDevicesTest {

    /**
     * Test 01
     * Unit test to add a device to a list.
     * Arrange a list, a device name, device model and device location.
     * Act to add a device to the list.
     * Assert that the device is successfully added to the list.
     */
    @Test
    void addDeviceToList_ReturnTrueWhenSuccessful() {
        //Arrange
        String name = "Device 1";
        String deviceModel = "Heater";
        String location = "WC";
        ListOfDevices list = new ListOfDevices();
        FactoryDevice factoryDeviceDouble = mock(ImplFactoryDevice.class);
        Device deviceDouble = mock(Device.class);
        when(factoryDeviceDouble.createDevice(name, deviceModel, location)).thenReturn(deviceDouble);

        //Act
        boolean result = list.addDeviceToList(name, deviceModel, location, factoryDeviceDouble);

        //Assert
        assertTrue(result);
    }

    /**
     * Test 02
     * Isolation test to add a device with invalid parameters to a list.
     * Arrange a list, a device name, device model and device location.
     * Act to add a device to the list with invalid parameters.
     * Assert that the device is not added to the list.
     */
    @Test
    void addDeviceToList_ReturnFalseWhenDeviceParamsInvalid() {
        //Arrange
        String name = "Device 1";
        String deviceModel = "Heater";
        String location = "WC";
        ListOfDevices list = new ListOfDevices();
        FactoryDevice factoryDeviceDouble = mock(ImplFactoryDevice.class);
        Device deviceDouble = mock(Device.class);
        when(factoryDeviceDouble.createDevice(name, deviceModel, location)).thenThrow(IllegalArgumentException.class);

        //Act
        boolean result = list.addDeviceToList(name, deviceModel, location, factoryDeviceDouble);

        //Assert
        assertFalse(result);
    }

    /**
     * Test 03
     * Isolation test to add an already existing device to a list.
     * Arrange a list, a device name, device model and device location.
     * Act to add a device to the list, then add the same device again.
     * Assert that the device is not added to the list.
     */
    @Test
    void addDeviceToList_ReturnFalseDueToDuplicatedDevice() {
        //Arrange
        ListOfDevices list = new ListOfDevices();

        String name = "Device 1";
        String deviceModel = "Heater";
        String location = "WC";

        //Mocks factoryDevice and method createDevice, which returns a mocked device.
        FactoryDevice factoryDeviceDouble = mock(ImplFactoryDevice.class);
        Device firstDeviceDouble = mock(Device.class);
        when(factoryDeviceDouble.createDevice(name, deviceModel, location)).thenReturn(firstDeviceDouble);

        //Ensures the mocked device returns 'Device 1' when getDeviceName is called.
        when(firstDeviceDouble.getDeviceName()).thenReturn(name);

        //Act
        /*Adds a mocked device to the list, then attempts to add a second one. The method fails when
        it confirms if there is another device with the same name on the list
         */
        list.addDeviceToList(name, deviceModel, location, factoryDeviceDouble);
        boolean result = list.addDeviceToList(name, deviceModel, location, factoryDeviceDouble);

        //Assert
        assertFalse(result);
    }
}
