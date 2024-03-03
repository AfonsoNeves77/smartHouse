package SmartHome.domain.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.TemperatureSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TemperatureSensorTest {
    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getName_SuccessfullyReturns(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    @Test
    void getUnit_SuccessfullyReturns(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName);
        String expected = "C";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * 1. Instantiates a new sensor
     * 2. Crates a SimHardware double, in order to mock its behaviour when .getValue is called. Even though
     * this is an integration test, the SimHardware must be mocked as it simulates a connection with a physical
     * sensor/API.
     * 3. Saves the Value resulting from calling the method getReading unto the pre-defined sensor.
     * 4. Converts the Value unto string and verifies that it matches the value mocked by calling getValue on simHardware
     * @throws InstantiationException If invalid sensor Name.
     */
    @Test
    void getReading_ReturnsValueCorrectly_Integration() throws InstantiationException {
        //Arrange

        // 1.
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName);

        // 2.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36.1");

        String expected = "36.1";
        //Act

        // 3.
        Value<?> value = sensor.getReading(simHardware);
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ThrowsExceptionIfInvalidReading_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = " ";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new TemperatureSensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ThrowsExceptionIfNullReading_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = null;
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new TemperatureSensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ThrowsExceptionIfReadingInvalidButNotNullOrEmpty_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = "I will fail";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new TemperatureSensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void getLog_Successfully_Integration() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36.1");

        Sensor sensor = new TemperatureSensor(sensorName);

        String expected = "36.1";
        sensor.getReading(simHardware);
        //Act
        ArrayList<String> log = sensor.getLog();
        String result = log.get(0);
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_SuccessfullyReturnsEmptyListIfNoLogs_Integration(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName);
        //Act
        ArrayList<String> expected = sensor.getLog();
        ArrayList<String> result = new ArrayList<>();
        //Assert
        assertEquals(expected,result);
    }
}
