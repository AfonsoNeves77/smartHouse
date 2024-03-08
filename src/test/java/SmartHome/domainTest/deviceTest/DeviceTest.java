package SmartHome.domainTest.deviceTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.device.Device;
import SmartHome.domain.sensor.ListOfSensors;
import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
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
     * This test case verifies the behavior when attempting to instantiate a Device with a null name.

     * This test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The mock constructions of ListOfActuators and ListOfSensors are isolated.
     * - The expected error message is "Invalid parameter."
     * - The sizes of the constructed lists (ListOfActuators and ListOfSensors) are both expected to be 0.

     * @throws InstantiationException if the instantiation of Device with a null name fails.
     */

    @Test
    void instantiateDeviceWithNullName_ShouldThrowInstantiationException_IsolationTest() {

        //Arrange
        String deviceName = null;
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        int expectedSize = 0;
        String result = "Invalid parameter";

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Exception exception = assertThrows(InstantiationException.class, () -> new Device(deviceName,deviceModel,deviceLocation));
            String expected = exception.getMessage();

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();


            //Assert
            assertEquals(expected,result);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }

    }

    /**
     * This test case verifies the successful addition of an actuator to a device.

     * This test checks for the following:
     * - An actuator with the specified name and type is successfully added to the device.
     * - Mock constructions of ListOfActuators and ListOfSensors are isolated.
     * - The expected result from addActuator is true.
     * - The sizes of the constructed lists (ListOfActuators and ListOfSensors) are both expected to be 1.

     * Throws InstantiationException if the instantiation of the Device fails.
     */


    @Test
    void addActuatorToDeviceSuccessful_Isolation() throws InstantiationException{

        //Arrange
        String deviceName = "device1";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        String actuatorName = "actuator1";
        String type = "thisWillFailButItsNotRelevant";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class, (mock, context)
                -> {
            when(mock.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble)).thenReturn(true);
        });
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble);

            //Assert
            assertTrue(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }

    }

    @Test
    void addActuatorToDevice_AdditionToListOfActuatorsFailed_Isolation() throws InstantiationException{

        //Arrange
        String deviceName = "device1";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        String actuatorName = "actuator1";
        String type = "thisWillFailButItsNotRelevant";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class, (mock, context)
                -> {
            when(mock.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble)).thenReturn(false);
        });
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble);

            //Assert
            assertFalse(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }
    }

    @Test
    void addActuatorToDevice_ShouldReturnFalseForInactiveDevice_Isolation() throws InstantiationException{

        //Arrange
        String deviceName = "device1";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        String actuatorName = "actuator1";
        String type = "thisWillFailButItsNotRelevant";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        ActuatorCatalogue catalogueDouble = mock(ActuatorCatalogue.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);
            device.deactivateDevice();

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addActuator(actuatorName,type,catalogueDouble,simHardwareActDouble);

            //Assert
            assertFalse(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }

    }


    @Test
    void addSensorToDeviceSuccessful_Isolation() throws InstantiationException{

        //Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName = "device1";
        String sensorType = "Temperature";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class, (mock, context)
                -> {
            when(mock.addSensor(sensorName,sensorType,sensorCatalogue,externalServices)).thenReturn(true);
        });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addSensor(sensorName,sensorType,sensorCatalogue,externalServices);

        //Assert
            assertTrue(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }

    }

    @Test
    void addSensorToDevice_ShouldReturnFalseForInactiveDevice_Isolation()  throws InstantiationException{

        //Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName = "device1";
        String sensorType = "Temperature";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class, (mock, context)
                -> {
            when(mock.addSensor(sensorName,sensorType,sensorCatalogue,externalServices)).thenReturn(false);
        });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);
            device.deactivateDevice();

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addSensor(sensorName,sensorType,sensorCatalogue,externalServices);

            //Assert
            assertFalse(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }

    }

    @Test
    void addSensorToDevice_AdditionToListOfSensorsFailed_Isolation()  throws InstantiationException{

        //Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName = "device1";
        String sensorType = "Temperature";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class, (mock, context)
                -> {
            when(mock.addSensor(sensorName,sensorType,sensorCatalogue,externalServices)).thenReturn(false);
        });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.addSensor(sensorName,sensorType,sensorCatalogue,externalServices);

            //Assert
            assertFalse(result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }

    }

    /**
     * This test case verifies the behavior of deactivating a device.

     * This test checks for the following:
     * - A device with the specified name, model, and location is instantiated.
     * - Mock constructions of ListOfActuators and ListOfSensors are isolated.
     * - Attempting to deactivate the device returns false.
     * - The sizes of the constructed lists (ListOfActuators and ListOfSensors) are both expected to be 1.

     * Throws InstantiationException if the instantiation of the device fails.
     */

    @Test
    void deactivateDevice_IsolationTest() throws InstantiationException {

        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        int expectedSize = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.deactivateDevice();


            //Assert
            assertFalse(result);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceName_IsolationTest() throws InstantiationException {

        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        int expectedSize = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            String result = device.getDeviceName();


            //Assert
            assertEquals(result,deviceName);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceModel_IsolationTest() throws InstantiationException {

        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        int expectedSize = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            String result = device.getDeviceModel();


            //Assert
            assertEquals(result,deviceModel);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceStatus_IsolationTest() throws InstantiationException {

        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";
        int expectedSize = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            boolean result = device.getStatus();


            //Assert
            assertTrue(result);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceFunctionalities_DeviceWithNoFunctionality_IsolationTest() throws InstantiationException {

        //Arrange
        String deviceName = "DeviceOne";
        String deviceModel = "XPTO";
        String deviceLocation = "bedroom";

        int expected = 0;
        int expectedSize = 1;

        //Act

        try (MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class);
             MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();

            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            int result = device.getDeviceFunctionalities().size();


            //Assert
            assertEquals(result,expected);
            assertEquals(expectedSize,sizeOfActuatorsList);
            assertEquals(expectedSize,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceFunctionalities_DeviceWithOneFunctionality_Isolation() throws InstantiationException{

        //Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName = "device1";
        String sensorType = "Temperature";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 1;

        //Act

        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class, (mock, context)
                -> {
            when(mock.addSensor(sensorName,sensorType,sensorCatalogue,externalServices)).thenReturn(true);
        });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName,deviceModel,deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            device.addSensor(sensorName,sensorType,sensorCatalogue,externalServices);
            int result = device.getDeviceFunctionalities().size();

                    //Assert
            assertEquals(expected,result);
            assertEquals(expected,sizeOfActuatorsList);
            assertEquals(expected,sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceFunctionalities_DeviceTwoFunctionalities_Isolation() throws InstantiationException {
        // Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName1 = "device1";
        String sensorType1 = "Temperature";
        String sensorName2 = "device2";
        String sensorType2 = "Pressure";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 2;
        int expectedSize = 1;

        // Act
        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class,
                (mock, context) -> {
                    when(mock.addSensor(sensorName1, sensorType1, sensorCatalogue, externalServices)).thenReturn(true);
                    when(mock.addSensor(sensorName2, sensorType2, sensorCatalogue, externalServices)).thenReturn(true);
                });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName, deviceModel, deviceLocation);

            device.addSensor(sensorName1, sensorType1, sensorCatalogue, externalServices);
            device.addSensor(sensorName2, sensorType2, sensorCatalogue, externalServices);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            int result = device.getDeviceFunctionalities().size();

            // Assert
            assertEquals(expected, result);
            assertEquals(expectedSize, sizeOfActuatorsList);
            assertEquals(expectedSize, sizeOfSensorsList);
        }
    }

    @Test
    void getDeviceFunctionalities_DeviceWithTwoSensorAdditionButOneFunctionality_Isolation() throws InstantiationException {
        // Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName1 = "device1";
        String sensorType1 = "Temperature";
        String sensorName2 = "device2";

        SensorCatalogue sensorCatalogue = mock(SensorCatalogue.class);
        ExternalServices externalServices = mock(ExternalServices.class);
        int expected = 1;

        // Act
        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class,
                (mock, context) -> {
                    when(mock.addSensor(sensorName1, sensorType1, sensorCatalogue, externalServices)).thenReturn(true);
                    when(mock.addSensor(sensorName2, sensorType1, sensorCatalogue, externalServices)).thenReturn(true);
                });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName, deviceModel, deviceLocation);

            device.addSensor(sensorName1, sensorType1, sensorCatalogue, externalServices);
            device.addSensor(sensorName2, sensorType1, sensorCatalogue, externalServices);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            int result = device.getDeviceFunctionalities().size();

            // Assert
            assertEquals(expected, result);
            assertEquals(expected, sizeOfActuatorsList);
            assertEquals(expected, sizeOfSensorsList);
        }
    }


    @Test
    void getLog_IsolationTest() throws InstantiationException {
        // Arrange
        String deviceName = "DeviceName";
        String deviceModel = "Device model";
        String deviceLocation = "Bathroom";
        String sensorName1 = "device1";

        int expected = 2;
        int expectedSize = 1;

        ArrayList<String> simulatedLog = new ArrayList<>();
        simulatedLog.add("LogOne");simulatedLog.add("LogTwo");

        // Act
        try (MockedConstruction<ListOfSensors> listOfSensorsMockedConstruction = mockConstruction(ListOfSensors.class,
                (mock, context) -> {
                    when(mock.getLog(sensorName1)).thenReturn(simulatedLog);
                });
             MockedConstruction<ListOfActuators> listOfActuatorsMockedConstruction = mockConstruction(ListOfActuators.class)) {

            Device device = new Device(deviceName, deviceModel, deviceLocation);

            List<ListOfSensors> listOfMockedListOfSensors = listOfSensorsMockedConstruction.constructed();
            List<ListOfActuators> listOfMockedListOfActuators = listOfActuatorsMockedConstruction.constructed();
            int sizeOfActuatorsList = listOfMockedListOfActuators.size();
            int sizeOfSensorsList = listOfMockedListOfSensors.size();

            int result = device.getLog(sensorName1).size();

            // Assert
            assertEquals(expected, result);
            assertEquals(expectedSize, sizeOfActuatorsList);
            assertEquals(expectedSize, sizeOfSensorsList);
        }
    }


    //..........................................................INTEGRATION TESTS..................................................................................

    @Test
    void addTemperatureSensorToDeviceSuccessful_IntegrationTest() throws InstantiationException {
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
    void addHumiditySensorToDeviceSuccessful_IntegrationTest() throws InstantiationException {
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
    void deactivateDevice() throws InstantiationException {

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
    void getDeviceName() throws InstantiationException {

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
    void getDeviceModel() throws InstantiationException {

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
    void getDeviceLocation() throws InstantiationException {

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
    void getDeviceStatus() throws InstantiationException {

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
    void getDeviceFunctionalities_DeviceWithNoFunctionality() throws InstantiationException {

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
    void getDeviceSensorLog_DeviceWithNoSensors() throws InstantiationException {

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

    /**
     * Test to deactivte a device.
     * Arrange a device with a name, model and location.
     * Act to deactivate the device.
     * Assert that the device is deactivated.
     */
    @Test
    void deactivateDevice_returnsFalseEvenIfDeviceIsAlreadyFalseAsStatus() throws InstantiationException {
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
     * Test to create a device with an invalid model.
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
        String expected = "Invalid parameter";
        //Act
        Exception result = assertThrows(InstantiationException.class, () ->
                new Device(deviceName, deviceModel, deviceLocation));
        //Assert
        assertEquals(expected,result.getMessage());
    }


}
