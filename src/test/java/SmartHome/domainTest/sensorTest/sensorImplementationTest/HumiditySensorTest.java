package SmartHome.domainTest.sensorTest.sensorImplementationTest;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import SmartHome.domain.sensor.sensorImplementation.HumiditySensor;
import org.mockito.MockedConstruction;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;



class HumiditySensorTest {

    @Test
    void sensorConstructor_throwsExceptionIfNameNull() {
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HumiditySensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new HumiditySensor(sensorName, simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getName_SuccessfullyReturns() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new HumiditySensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName, result);
    }

    @Test
    void getUnit_SuccessfullyReturns() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new HumiditySensor(sensorName, simHardware);
        String expected = "%";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReading_ReturnsValueCorrectly_Integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        String sensorName = "Sensor 1";
        HumiditySensor sensor = new HumiditySensor(sensorName, simHardware);

        String expected = "36";

        //Act
        HumidityValue value = (HumidityValue) sensor.getReading();
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getLog_SuccessfullyReturnsEmptyList_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        HumiditySensor sensor = new HumiditySensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getReadingValid_isolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        HumiditySensor humiditySensor = new HumiditySensor(sensorName,simHardware);


        try (MockedConstruction<HumidityValue> humidityValueDouble = mockConstruction(HumidityValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(23);
        })) {


            // act
            Value<Integer> value = humiditySensor.getReading();

            // assert
            List<HumidityValue> values = humidityValueDouble.constructed();

            assertEquals(1, values.size());
            assertEquals(23, humidityValueDouble.constructed().get(0).getValue());
            assertEquals(23, value.getValue());
        }
    }




}
