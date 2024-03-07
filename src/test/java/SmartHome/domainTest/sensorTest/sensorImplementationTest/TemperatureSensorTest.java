package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.sensorImplementation.DewPointSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.TemperatureSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.TemperatureValue;
import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TemperatureSensorTest {

//     ### ISOLATION TESTS ###

    /**
     * Tests the constructor of the TemperatureSensor class with a null name.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameNull() {
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new TemperatureSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the constructor of the TemperatureSensor class with an empty name.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new TemperatureSensor(sensorName, simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests the getName method of the TemperatureSensor class.
     *
     * @throws InstantiationException
     */
    @Test
    void getName_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName, result);
    }

    /**
     * Tests the getUnit method of the TemperatureSensor class.
     *
     * @throws InstantiationException
     */
    @Test
    void getUnit_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new TemperatureSensor(sensorName, simHardware);
        String expected = "C";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Successfully returns getReading value.
     * 1. Instantiates a simHardware double, placing a stub for the method getValue. Also instantiates the sensor.
     * 2. Utilizes a mocked construction to create a double of a TemperatureValue, and placing a stub on the method getValueAsString.
     * 3. The first test involves using the .constructed() method to save all instances of created TemperatureValue doubles,
     * into a list of TemperatureValue. It then checks the size of that list using .size(), and compares it against the expected size of 1.
     * #Important# It is imperative that we call getReading on sensor before this operation.
     * 4. The second test involves accessing the first value saved on the list created above using get(0) and calling getValueAsString,
     * matching it against the expected string.
     * 5. The third test utilizes the temperatureValueDouble that is created by calling getReading unto the sensor created at the top
     * and ensuring that when we call getValueAsString unto that value, it returns the expected string.
     *
     * @throws InstantiationException If sensor parameters invalid.
     */
    @Test
    void getReading_ReturnsValueCorrectly_Isolation() throws InstantiationException {
        //Arrange
        // 1.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36.1");
        String sensorName = "Sensor 1";
        TemperatureSensor sensor = new TemperatureSensor(sensorName, simHardware);

        // 2.
        try (MockedConstruction<TemperatureValue> temperatureValueDouble = mockConstruction(TemperatureValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("36.1");
        })) {

            //Act & Assert

            TemperatureValue value = (TemperatureValue) sensor.getReading();

            // 3.
            List<TemperatureValue> values = temperatureValueDouble.constructed();
            int listOfDoublesSize = values.size();
            assertEquals(1, listOfDoublesSize);

            // 4.
            String expected = "36.1";
            String result = values.get(0).getValueAsString();
            assertEquals(expected, result);

            // 5.
            String mockedTemperatureValue = value.getValueAsString();
            assertEquals(expected, mockedTemperatureValue);
        }
    }


    /**
     * Tests the getLog method of the TemperatureSensor class.
     *
     * @throws InstantiationException
     */
    @Test
    void getLog_SuccessfullyReturnsEmptyList_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36.1");

        Sensor sensor = new TemperatureSensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected, result);
    }

//     ### INTEGRATION TESTS ###

    /**
     * Tests the getLog method of the TemperatureSensor class.
     *
     * @throws InstantiationException
     */
    @Test
    void getReading_ReturnsValueCorrectly_Integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36.1");

        String sensorName = "Sensor 1";
        TemperatureSensor sensor = new TemperatureSensor(sensorName, simHardware);

        String expected = "36.1";

        //Act
        TemperatureValue value = (TemperatureValue) sensor.getReading();
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected, result);
    }
}
