package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.InstantPowerConsumptionSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.InstantPowerConsumptionValue;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InstantPowerConsumptionSensorTest {

    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new InstantPowerConsumptionSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new InstantPowerConsumptionSensor(sensorName, simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getName_ReturnsName(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    @Test
    void getUnit_ReturnsUnit(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        String expected = "W";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_ReturnsValueCorrectly() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        String sensorName = "Sensor 1";
        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName,simHardware);

        String expected = "36";

        //Act
        InstantPowerConsumptionValue value = (InstantPowerConsumptionValue) sensor.getReading();
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_throwsExceptionIfEmptyReading() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn(" ");
        String sensorName = "Sensor1";
        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading());
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_throwsExceptionIfInvalidReadingNumber() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("abc");
        String sensorName = "Sensor1";
        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading());
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLog_ReturnsEmptyList() {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getType_ReturnsType(){
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);

        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName,simHardware);

        String expected = "InstantPowerConsumptionSensor";
        //Act
        String result = sensor.getTYPE();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_ReturnsLog() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);

        InstantPowerConsumptionValue value = (InstantPowerConsumptionValue) sensor.getReading();

        String expected = "36";


        //Act
        String result = sensor.getLog().get(0);

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_ReturnsCorrectLogAfterMultipleReadingsDifferentValues() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("30");
        String sensorName = "Sensor1";
        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        String expected = "50";

        //Act
        sensor.getReading();
        when(simHardware.getValue()).thenReturn("50");
        sensor.getReading();
        String result = sensor.getLog().get(1);

        //Assert
        assertEquals(expected, result);
    }
}
