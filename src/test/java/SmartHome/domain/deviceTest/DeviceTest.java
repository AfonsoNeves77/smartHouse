package SmartHome.domain.deviceTest;

import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DeviceTest {
    /**
     * Test 01
     * Unit test to deactivte a device.
     * Arrange a device with a name, model and location.
     * Act to deactivate the device.
     * Assert that the device is deactivated.
     */
    @Test
    void deactivateDevice_returnsFalseEvenIfDeviceIsAlreadyFalseAsStatus() {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "XPTO3000";
        String deviceLocation = "Room";
        Device newDevice = new Device(deviceName, deviceModel, deviceLocation);
        //Act
        boolean result = newDevice.deactivateDevice();
        //Assert
        assertFalse(result);
    }

    /**
     * Test 02
     * Unit test to create a device with an invalid model.
     * Arrange a device with a name, model and location.
     * Act to create a device with an invalid model.
     * Assert that an exception is thrown.
     */
    @Test
    void createDevice_InvalidDeviceModel() {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = " ";
        String deviceLocation = "Room";
        String expected = "Invalid parameter.";
        //Act
        Exception result = assertThrows(IllegalArgumentException.class, () ->
               new Device(deviceName, deviceModel, deviceLocation));
        //Assert
        assertEquals(expected,result.getMessage());
    }


    @Test
    void addSensorToDeviceSuccessful_IsolationTest(){
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "Temperature";
        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType)).thenReturn(sensorDouble);
        //Act
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble);
        //Assert
        assertTrue(result);
    }

    @Test
    void addSensorToDeviceDuplicatedSensor_IsolationTest(){
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";

        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "Temperature";

        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);

        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType)).thenReturn(sensorDouble);
        when(sensorDouble.getName()).thenReturn(nameSensor);

        //Act
        device.addSensor(nameSensor,sensorType,catalogueDouble);
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble);
        //Assert
        assertFalse(result);
    }

    @Test
    void addSensorToDeviceInvalidSensorParameters_IsolationTest(){
        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "";
        String sensorType = "Temperature";
        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType)).thenReturn(null);
        when(sensorDouble.getName()).thenReturn(nameSensor);
        //Act
        device.addSensor(nameSensor,sensorType,catalogueDouble);
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble);
        //Assert
        assertFalse(result);
    }


    @Test
    void addTemperatureSensorToDeviceSuccessful() throws InstantiationException {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";

        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor","SmartHome.domain.sensor.sensorImplementation.TemperatureSensor");

        //Act
        SensorCatalogue catalogue = new SensorCatalogue(config);
        boolean result = device.addSensor(nameSensor,sensorType,catalogue);

        //Assert

        assertTrue(result);
    }

    @Test
    void addHumiditySensorToDeviceSuccessful() throws InstantiationException {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";

        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor","SmartHome.domain.sensor.sensorImplementation.HumiditySensor");

        //Act
        SensorCatalogue catalogue = new SensorCatalogue(config);
        boolean result = device.addSensor(nameSensor,sensorType,catalogue);

        //Assert

        assertTrue(result);

    }

    @Test
    void addDuplicatedSensorToDevice() throws InstantiationException {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "pt.ipp.isep.dei.project.domain.sensor.sensorImplementation.HumiditySensor";

        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor","pt.ipp.isep.dei.project.domain.sensor.sensorImplementation.HumiditySensor");

        //Act
        SensorCatalogue catalogue = new SensorCatalogue(config);
        device.addSensor(nameSensor,sensorType,catalogue);
        boolean result = device.addSensor(nameSensor,sensorType,catalogue);

        //Assert
        assertFalse(result);

    }

    @Test
    void addSensorToDevice_DeviceWithTwoSensors() throws InstantiationException {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "Sensor One";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        String nameSensorTwo = "Sensor Two";

        Configuration config = new PropertyListConfiguration();
        config.addProperty("sensor","SmartHome.domain.sensor.sensorImplementation.HumiditySensor");
        config.addProperty("sensor","SmartHome.domain.sensor.sensorImplementation.HumiditySensor");

        //Act
        SensorCatalogue catalogue = new SensorCatalogue(config);
        device.addSensor(nameSensor,sensorType,catalogue);
        boolean result = device.addSensor(nameSensorTwo,sensorType,catalogue);

        //Assert
        assertTrue(result);

    }

    @Test
    void deactivateDevice(){

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        //Act
        boolean result = device.deactivateDevice();
        //Assert
        assertFalse(result);
    }

    @Test
    void getDeviceName(){

        //Arrange
        String expected = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(expected,deviceModel,deviceLocation);
        //Act
        String result = device.getDeviceName();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getDeviceModel(){

        //Arrange
        String deviceName = "Heater";
        String expected = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,expected,deviceLocation);
        //Act
        String result = device.getDeviceModel();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getDeviceLocation(){

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String expected = "Room";
        Device device = new Device(deviceName,deviceModel,expected);
        //Act
        String result = device.getDeviceLocation();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getDeviceStatus(){

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        //Act
        boolean result = device.getStatus();
        //Assert
        assertTrue(result);
    }

    @Test
    void getDeviceFunctionalities_DeviceWithNoFunctionality(){

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        //Act
        List<String> result = device.getDeviceFunctionalities();
        //Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void getDeviceFunctionalities_DeviceWithOneFunctionality() throws InstantiationException {

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String sensorName = "Sensor 1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        device.addSensor(sensorName,sensorType,catalogue);
        int expected = 1;
        //Act
        List<String> listOfDeviceFunctionality = device.getDeviceFunctionalities();
        int result = listOfDeviceFunctionality.size();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getDeviceFunctionalities_DeviceWithTwoFunctionalities() throws InstantiationException {

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String sensorName = "Sensor 1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        String sensorNameTwo = "Sensor Two";
        String sensorTypeTwo = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        device.addSensor(sensorName,sensorType,catalogue);
        device.addSensor(sensorNameTwo,sensorTypeTwo,catalogue);
        int expected = 2;
        //Act
        List<String> listOfDeviceFunctionality = device.getDeviceFunctionalities();
        int result = listOfDeviceFunctionality.size();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void getDeviceSensorLog_DeviceWithNoSensors()  {

        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        int expected = 0;
        //Act
        List<String> listOfFunctionalities = device.getDeviceFunctionalities();
        int result = listOfFunctionalities.size();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void getDeviceFunctionalities_DeviceWithTwoSensorsButOneFunctionality() throws InstantiationException {
        //Arrange
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room X";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);
        //Same functionality is added
        device.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        device.addSensor("XKT6"," SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("SmartHome.domain.sensor.sensorImplementation.TemperatureSensor");
        //Act
        List<String> result = device.getDeviceFunctionalities();
        //Assert
        assertEquals(expected, result);
    }

}
