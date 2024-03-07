package SmartHome.domainTest.deviceTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.BlindRollerActuator;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.device.Device;
import SmartHome.domain.device.ListOfDevices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
        SimHardware simHardware = mock(SimHardware.class);
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "Temperature";
        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType,simHardware)).thenReturn(sensorDouble);
        //Act
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble,simHardware);
        //Assert
        assertTrue(result);
    }

    @Test
    void addSensorToDeviceDuplicatedSensor_IsolationTest(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";

        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "NameSensor";
        String sensorType = "Temperature";

        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);

        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType,simHardware)).thenReturn(sensorDouble);
        when(sensorDouble.getName()).thenReturn(nameSensor);

        //Act
        device.addSensor(nameSensor,sensorType,catalogueDouble,simHardware);
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble,simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void addSensorToDeviceInvalidSensorParameters_IsolationTest(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String deviceName = "DeviceOne";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String nameSensor = "";
        String sensorType = "Temperature";
        SensorCatalogue catalogueDouble = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);
        when(catalogueDouble.createSensor(nameSensor,sensorType,simHardware)).thenReturn(null);
        when(sensorDouble.getName()).thenReturn(nameSensor);
        //Act
        device.addSensor(nameSensor,sensorType,catalogueDouble,simHardware);
        boolean result = device.addSensor(nameSensor,sensorType,catalogueDouble,simHardware);
        //Assert
        assertFalse(result);
    }
    @Test
    void addActuatorToDeviceSuccessful_Isolation(){
        String deviceName = "device1";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        String actuatorName = "actuator1";
        String type = "thisWillFailButItsNotRelevant";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {
            Device device = new Device(deviceName, deviceModel, deviceLocation);
            List<ListOfActuators> listOfActuatorsMockedList = listOfActuatorsMockedConstruction.constructed();
            ListOfActuators listOfActuatorsDouble = listOfActuatorsMockedList.get(0);
            when(listOfActuatorsDouble.addActuator(actuatorName, type, catalogueDouble, simHardwareActDouble)).thenReturn(true);

            //Act
            boolean result = device.addActuator(actuatorName, type, catalogueDouble, simHardwareActDouble);
            //Assert
            assertTrue(result);
        }
    }
    @Test
    void addActuatorToDevice_False_Isolation(){
        String deviceName = "device1";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        String actuatorName = "actuator1";
        String type = "thisWillFailButItsNotRelevant";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {
            Device device = new Device(deviceName, deviceModel, deviceLocation);
            List<ListOfActuators> listOfActuatorsMockedList = listOfActuatorsMockedConstruction.constructed();
            ListOfActuators listOfActuatorsDouble = listOfActuatorsMockedList.get(0);
            when(listOfActuatorsDouble.addActuator(actuatorName, type, catalogueDouble, simHardwareActDouble)).thenReturn(false);

            //Act
            boolean result = device.addActuator(actuatorName, type, catalogueDouble, simHardwareActDouble);
            //Assert
            assertFalse(result);
        }
    }

//    @Test
//    void addActuatorToDeviceSuccessful_Isolation(){
//        String deviceName = "device1";
//        String deviceModel = "XPTO";
//        String deviceLocation = "bedroom";
//        Device device = new Device(deviceName,deviceModel,deviceLocation);
//        String actuatorName = "actuator1";
//        String type = "thisWillFailButItsNotRelevant";
//        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
//        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
//        try (MockedConstruction<ListOfActuators> listOfActuators = mockConstruction(ListOfActuators.class, (mock, context)
//                -> {
//            when(mock.addActuator(actuatorName, type, catalogueDouble, simHardwareActDouble)).thenReturn(true);
//        })) {
//        }
//        //Act
//        boolean result = device.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble);
//        //Assert
//        assertTrue(result);
//    }
    @Test
    void addActuatorToDeviceDuplicatedActuator_IsolationTest(){
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String deviceName = "Device1";
        String deviceModel = "K899";
        String deviceLocation = "Room";

        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String actuatorName = "Actuator1";
        String actuatorType = "BlindRoller";

        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);

        BlindRollerActuator blindRollerActuatorDouble = mock(BlindRollerActuator.class);
        when(catalogueDouble.createActuator(actuatorName,actuatorType,simHardware)).thenReturn(blindRollerActuatorDouble);
        when(blindRollerActuatorDouble.getName()).thenReturn(actuatorName);

        //Act
        device.addActuator(actuatorName,actuatorType,catalogueDouble,simHardware);
        boolean result = device.addActuator(actuatorName,actuatorType,catalogueDouble,simHardware);
        //Assert
        assertFalse(result);
    }
    @Test
    void addActuatorToDeviceInvalidActuatorParameters_IsolationTest(){
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String deviceName = "DeviceOne";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String actuatorName = "";
        String actuatorType = "BlindRoller";
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
        BlindRollerActuator actuatorDouble = mock(BlindRollerActuator.class);
        when(catalogueDouble.createActuator(actuatorName,actuatorType,simHardware)).thenReturn(null);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        //Act
        device.addActuator(actuatorName,actuatorType,catalogueDouble,simHardware);
        boolean result = device.addActuator(actuatorName,actuatorType,catalogueDouble,simHardware);
        //Assert
        assertFalse(result);
    }
//    @Test
////    void addActuatorToDevice_ReturnsFalseDueToUnableToInstantiate_Isolation(){
////        String deviceName = "device1";
////        String deviceModel = "XPTO";
////        String deviceLocation = "bedroom";
////        Device device = new Device(deviceName,deviceModel,deviceLocation);
////        String actuatorName = "actuator1";
////        String type = "thisWillFailButItsNotRelevant";
////        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
////        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
////        when(catalogueDouble.createActuator(actuatorName, type,simHardwareActDouble)).thenThrow(InstantiationException.class);
////        //Act
////        boolean result = device.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble);
////        //Assert
////        assertFalse(result);
////    }


    @Test
    void addTemperatureSensorToDeviceSuccessful() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
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
        boolean result = device.addSensor(nameSensor,sensorType,catalogue,simHardware);

        //Assert
        assertTrue(result);
    }

    @Test
    void addHumiditySensorToDeviceSuccessful() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
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
        boolean result = device.addSensor(nameSensor,sensorType,catalogue,simHardware);

        //Assert

        assertTrue(result);

    }

    @Test
    void addDuplicatedSensorToDevice() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
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
        device.addSensor(nameSensor,sensorType,catalogue,simHardware);
        boolean result = device.addSensor(nameSensor,sensorType,catalogue,simHardware);

        //Assert
        assertFalse(result);

    }

    @Test
    void addSensorToDevice_DeviceWithTwoSensors() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
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
        device.addSensor(nameSensor,sensorType,catalogue,simHardware);
        boolean result = device.addSensor(nameSensorTwo,sensorType,catalogue,simHardware);

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
        SimHardware simHardware = new SimHardware();
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String sensorName = "Sensor 1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        device.addSensor(sensorName,sensorType,catalogue,simHardware);
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
        SimHardware simHardware = new SimHardware();
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String sensorName = "Sensor 1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        String sensorNameTwo = "Sensor Two";
        String sensorTypeTwo = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        device.addSensor(sensorName,sensorType,catalogue,simHardware);
        device.addSensor(sensorNameTwo,sensorTypeTwo,catalogue,simHardware);
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
        SimHardware simHardware = new SimHardware();
        String deviceName = "Heater";
        String deviceModel = "K899";
        String deviceLocation = "Room X";
        Device device = new Device(deviceName,deviceModel,deviceLocation);
        String fileName = "config.properties";
        SensorCatalogue catalogue = new SensorCatalogue(fileName);
        //Same functionality is added
        device.addSensor("XKT2", "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        device.addSensor("XKT6"," SmartHome.domain.sensor.sensorImplementation.TemperatureSensor", catalogue,simHardware);
        ArrayList<String> expected = new ArrayList<>();
        expected.add("SmartHome.domain.sensor.sensorImplementation.TemperatureSensor");
        //Act
        List<String> result = device.getDeviceFunctionalities();
        //Assert
        assertEquals(expected, result);
    }

}
