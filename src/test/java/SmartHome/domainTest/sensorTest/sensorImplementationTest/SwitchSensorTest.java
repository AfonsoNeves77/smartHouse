package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.HumiditySensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.SwitchSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SwitchValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SwitchSensorTest {

    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SwitchSensor(sensorName, simHardwareDouble));
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
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SwitchSensor(sensorName, simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getName_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new SwitchSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    @Test
    void getUnit_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new SwitchSensor(sensorName, simHardware);
        String expected = "State";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getLog_SuccessfullyReturnsEmptyList_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("On");

        SwitchSensor sensor = new SwitchSensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getReading_SuccessTest_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("On");

        SwitchSensor sensor = new SwitchSensor(sensorName, simHardware);

        String reading = "On";

        //Act
        Value<String> result = sensor.getReading();

        //Assert
        assertEquals(reading,result.getValue());
    }

    /**
     * Successfully returns getReading value.
     * 1. Instantiates a simHardware double, placing a stub for the method getValue. Also instantiates the sensor.
     * 2. Utilizes a mocked construction to create a double of a HumidityValue, and placing a stub on the method getValueAsString.
     * 3. The first test involves using the .constructed() method to save all instances of created HumidityValue doubles,
     * into a list of HumidityValue. It then checks the size of that list using .size(), and compares it against the expected size of 1.
     * #Important# It is imperative that we call getReading on sensor before this operation.
     * 4. The second test involves accessing the first value saved on the list created above using get(0) and calling getValueAsString,
     * matching it against the expected string.
     * 5. The third test utilizes the humidityValueDouble that is created by calling getReading unto the sensor created at the top
     * and ensuring that when we call getValueAsString unto that value, it returns the expected string.
     * @throws InstantiationException If sensor parameters invalid.
     */
    @Test
    void getReading_ReturnsValueCorrectly_Isolation() throws InstantiationException {
        //Arrange
        // 1.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("On");
        String sensorName = "Sensor 1";
        SwitchSensor sensor = new SwitchSensor(sensorName, simHardware);

        // 2.
        try (MockedConstruction<SwitchValue> switchValueDouble = mockConstruction(SwitchValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("On");
        })) {

            //Act & Assert

            SwitchValue value = (SwitchValue) sensor.getReading();

            // 3.
            List<SwitchValue> values = switchValueDouble.constructed();
            int listOfDoublesSize = values.size();
            assertEquals(1, listOfDoublesSize);

            // 4.
            String expected = "On";
            String result = values.get(0).getValueAsString();
            assertEquals(expected,result);

            // 5.
            String mockedSwitchValue = value.getValueAsString();
            assertEquals(expected, mockedSwitchValue);
        }
    }

}
