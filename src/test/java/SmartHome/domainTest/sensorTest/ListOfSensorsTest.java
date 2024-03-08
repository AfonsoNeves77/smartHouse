package SmartHome.domainTest.sensorTest;

import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import SmartHome.domain.sensor.ListOfSensors;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListOfSensorsTest {

//     ### ISOLATION TESTS ###

    /**
     * Test case for successfully adding a temperature sensor to the list of sensors.
     */
    @Test
    void addTemperatureSensorToDevice_Success_Isolation()  {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);

        when(catalogue.createSensor(sensorName, sensorType, simHardware)).thenReturn(sensorDouble);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertTrue(result);
    }

    /**
     * Test case for failing to add a temperature sensor to the list of sensors due to a duplicated device.
     */
    @Test
    void addTemperatureSensorToDevice_UnableToInstantiateSensor_Isolation()  {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = mock(SensorCatalogue.class);

        when(catalogue.createSensor(sensorName, sensorType,simHardware)).thenReturn(null);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

//     ###  INTEGRATION TESTS ###

    /**
     * Test case for unsuccessfully adding a temperature sensor to the list of sensors due to an empty name.
     * @throws InstantiationException
     */
    @Test
    void addTemperatureSensorToDevice_UnableToInstanciateSensorDueToBlankName_integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");

        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    /**
     * Test case for unsuccessfully adding a temperature sensor to the list of sensors due to a null name.
     * @throws InstantiationException
     */
    @Test
    void addTemperatureSensorToDevice_UnableToInstanciateSensorDueToNullName_integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = null;
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    /**
     * Test case for successfully adding a temperature sensor to the list of sensors.
     * @throws InstantiationException
     */
    @Test
    void addTemperatureSensorToDevice_Success_integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertTrue(result);
    }

    /**
     * Test case for failing to add a temperature sensor to the list of sensors due to a duplicated device.
     * @throws InstantiationException
     */
    @Test
    void addTemperatureSensorToDevice_FailsDueToDuplicatedDevice_integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = new SimHardware();
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void getLog_NoFunctionalities_IsolationTest()  {
        //Arrange

        ListOfSensors listOfSensors = new ListOfSensors();

        //Act
       List<String> result =  listOfSensors.getLog("SensorName");
        //Assert
        assertNull(result);
    }


}
