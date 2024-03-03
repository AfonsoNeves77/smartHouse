package SmartHome.domain.sensorTest;

import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;
import org.junit.jupiter.api.Test;
import SmartHome.domain.sensor.ListOfSensors;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListOfSensorsTest {

    /**
     * Test case for successfully adding a temperature sensor to the list of sensors.
     */
    @Test
    void addTemperatureSensorToDevice_Success_Isolation()  {
        //Arrange
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = mock(SensorCatalogue.class);
        Sensor sensorDouble = mock(Sensor.class);

        when(catalogue.createSensor(sensorName, sensorType)).thenReturn(sensorDouble);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertTrue(result);
    }

    @Test
    void addTemperatureSensorToDevice_UnableToInstanciateSensor_Isolation()  {
        //Arrange
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = mock(SensorCatalogue.class);

        when(catalogue.createSensor(sensorName, sensorType)).thenReturn(null);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertFalse(result);
    }

    @Test
    void addTemperatureSensorToDevice_UnableToInstanciateSensorDueToBlankName_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor 1";
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");

        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertFalse(result);
    }

    @Test
    void addTemperatureSensorToDevice_UnableToInstanciateSensorDueToNullName_integration() throws InstantiationException {
        //Arrange
        String sensorName = null;
        String sensorType = "HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertFalse(result);
    }

    @Test
    void addTemperatureSensorToDevice_Success_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertTrue(result);
    }

    @Test
    void addTemperatureSensorToDevice_FailsDueToDuplicatedDevice_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Act
        boolean result = listOfSensors.addSensor(sensorName,sensorType,catalogue);
        //Assert
        assertFalse(result);
    }

    //Identified the need to test case this scenario with isolation

    @Test
    void getReading_SuccessCase_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String expected = "36.1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(expected);
        //Act
        Value<?> value = listOfSensors.getReading(sensorName,simHardware);
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }

    //Same
    @Test
    void getReading_throwsExceptionIfReadingInvalid_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String simReading = " ";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                listOfSensors.getReading(sensorName,simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }


    //SAME

    @Test
    void getReading_throwsExceptionIfReadingNull_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String simReading = null;
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                listOfSensors.getReading(sensorName,simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_throwsExceptionIfReadingAboveBounds_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String simReading = "101";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                listOfSensors.getReading(sensorName,simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_throwsExceptionIfReadingBelowBounds_integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.HumiditySensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String simReading = "-1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                listOfSensors.getReading(sensorName,simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_SuccessCase_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        ListOfSensors listOfSensors = new ListOfSensors();
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        listOfSensors.addSensor(sensorName,sensorType,catalogue);

        String expected = "36.1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(expected);
        //Act
        Value<?> value = listOfSensors.getReading(sensorName,simHardware);
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }

}
