package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.sensorImplementation.PositionSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PositionSensorTest {
    @Test
    void positionSensorConstructor_success() {
        //Arrange
        String sensorName = "Sensor1";
        //Act
        Sensor sensor = new PositionSensor(sensorName);
        //Assert
        assertEquals(sensorName, sensor.getName());
    }

    @Test
    void positionSensorConstructor_throwsExceptionIfNameNull() {
        //Arrange
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new PositionSensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void positionSensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        String sensorName = " ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new PositionSensor(sensorName));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getName_success() {
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new PositionSensor(sensorName);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName, result);
    }

    @Test
    void getUnit_success() {
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "%";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_returnsValueCorrectly() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50");
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "50";
        //Act
        Value<?> value = sensor.getReading(simHardware);
        //Assert
        assertEquals(expected, value.getValueAsString());
    }

    @Test
    void getReading_throwsExceptionIfInvalidReading() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = " ";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_throwsExceptionIfNullReading() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = null;
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_throwsExceptionIfReadingAboveRange() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = "101";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_throwsExceptionIfReadingBelowRange() {
        //Arrange
        String sensorName = "Sensor1";
        String simReading = "-1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(simReading);
        Sensor sensor = new PositionSensor(sensorName);
        String expected = "Invalid reading";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading(simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLog_returnsLogCorrectly() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50");
        Sensor sensor = new PositionSensor(sensorName);
        sensor.getReading(simHardware);
        String expected = "50";
        //Act
        String result = sensor.getLog().get(0);
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLog_returnsEmptyListIfNoLogs(){
        //Arrange
        String sensorName = "Sensor1";
        Sensor sensor = new PositionSensor(sensorName);
        ArrayList<String> expected = new ArrayList<>();
        //Act
        ArrayList<String> result = sensor.getLog();
        //Assert
        assertEquals(expected, result);
    }

}