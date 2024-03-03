package SmartHome.domain.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import SmartHome.domain.sensor.sensorImplementation.HumiditySensor;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class HumiditySensorTest {

    /**
     * Attempts to create a sensor with a null sensorName, expecting an IllegalArgumentException.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HumiditySensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Attempts to create a sensor with a blank sensorName, expecting an IllegalArgumentException.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HumiditySensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Successfully returns sensorName.
     */
    @Test
    void getName_SuccessfullyReturns(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new HumiditySensor(sensorName);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    /**
     * Successfully returns unit.
     */
    @Test
    void getUnit_SuccessfullyReturns(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new HumiditySensor(sensorName);
        String expected = "%";
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
        Sensor sensor = new HumiditySensor(sensorName);

        // 2.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("70");

        String expected = "70";
        //Act

        // 3.
        Value<?> value =  sensor.getReading(simHardware);
        String result = value.getValueAsString();
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void getReading_ThrowsExceptionIfInvalidReading_Integration()  {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = " ";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new HumiditySensor(sensorName);

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

        Sensor sensor = new HumiditySensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ThrowsExceptionIfReadingAboveRange_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = "101";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new HumiditySensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ThrowsExceptionIfReadingBelowRange_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = "-1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);

        Sensor sensor = new HumiditySensor(sensorName);

        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
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
        when(simHardware.getValue()).thenReturn("36");

        Sensor sensor = new HumiditySensor(sensorName);

        String expected = "36";
        sensor.getReading(simHardware);
        //Act
        ArrayList<String> log = sensor.getLog();
        String result = log.get(0);
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_SuccessfullyReturnsEmptyListIfNoLogs_Integration() {
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new HumiditySensor(sensorName);
        //Act
        ArrayList<String> expected = sensor.getLog();
        ArrayList<String> result = new ArrayList<>();
        //Assert
        assertEquals(expected,result);
    }
}
