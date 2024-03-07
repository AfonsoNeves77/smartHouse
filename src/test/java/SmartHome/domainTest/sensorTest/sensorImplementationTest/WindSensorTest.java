package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.WindSensor;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class WindSensorTest {
    /**
     * This test ensures the constructor throws an exception if the name inserted is null.
     */
    @Test
    void constructor_throwsExceptionIfNullName(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures the constructor throws an exception if the name inserted is empty.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = " ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new WindSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures the getName method returns the name successfully.
     */
    @Test
    void getName_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new WindSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    /**
     * This test ensures the getName method returns the unit successfully.
     */
    @Test
    void getUnit_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new WindSensor(sensorName, simHardware);
        String expected = "Km/h";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Successfully returns getReading value.
     * 1. Instantiates a simHardware double, placing a stub for the method getValue. Also instantiates the sensor.
     * 2. Utilizes a mocked construction to create a double of a WindSensorValue, and placing a stub on the method getValueAsString.
     * 3. The first test involves using the .constructed() method to save all instances of created WindValue doubles,
     * into a list of WindSensorValue. It then checks the size of that list using .size(), and compares it against the expected size of 1.
     * #Important# It is imperative that we call getReading on sensor before this operation.
     * 4. The second test involves accessing the first value saved on the list created above using get(0) and calling getValueAsString,
     * matching it against the expected string.
     * 5. The third test utilizes the WindValueDouble that is created by calling getReading unto the sensor created at the top
     * and ensuring that when we call getValueAsString unto that value, it returns the expected string.
     * @throws InstantiationException If sensor parameters invalid.
     */
    @Test
    void getReading_ReturnsValueCorrectly_Isolation() throws InstantiationException {
        //Arrange
        // 1.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("40-N");
        String sensorName = "Sensor 1";
        WindSensor sensor = new WindSensor(sensorName, simHardware);

        // 2.
        try (MockedConstruction<WindValue> windValueDouble = mockConstruction(WindValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("40-N");
        })) {

            //Act & Assert

            WindValue value = (WindValue) sensor.getReading();

            // 3.
            List<WindValue> values = windValueDouble.constructed();
            int listOfDoublesSize = values.size();
            assertEquals(1, listOfDoublesSize);

            // 4.
            String expected = "40-N";
            String result = values.get(0).getValueAsString();
            assertEquals(expected,result);

            // 5.
            String mockedWindValue = value.getValueAsString();
            assertEquals(expected, mockedWindValue);
        }
    }

    /**
     * This test ensures the new sensor created automatically creates an accessible log, which will be empty by default.
     * @throws InstantiationException On invalid sensor parameters.
     */
    @Test
    void getLog_SuccessfullyReturnsEmptyList_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("40-N");

        Sensor sensor = new WindSensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected,result);
    }

    //////////////////////////////////////////// Integration///////////////////////////////////////////////////

    /**
     * This test creates a simhardware double in order to place a stub on getValue, with a controlled and valid return.
     * The expectation is that the process occurs successfully. To validate it, the value that gets returned has its name
     * extracted using getValueAsString and compared against the expected name.
     * @throws InstantiationException On invalid parameters
     */
    @Test
    void getReading_ReturnsValueCorrectly_Integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("40-N");

        String sensorName = "Sensor 1";
        WindSensor sensor = new WindSensor(sensorName,simHardware);

        String expected = "40-N";

        //Act
        WindValue value = (WindValue) sensor.getReading();
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}
