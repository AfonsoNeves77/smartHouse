package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.InstantPowerConsumptionSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.WindSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.InstantPowerConsumptionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class InstantPowerConsumptionSensorTest {

    /**
     * This test ensures the constructor throws an exception if the name inserted is null.
     */
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

    /**
     * This test ensures the constructor throws an exception if the name inserted is empty.
     */
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

    /**
     * This test ensures the getName method returns the name successfully.
     */
    @Test
    void getName_SuccessfullyReturns(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    /**
     * This test ensures the getName method returns the unit successfully.
     */
    @Test
    void getUnit_SuccessfullyReturns(){
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

    /**
     * Successfully returns getReading value.
     * 1. Instantiates a simHardware double, placing a stub for the method getValue. Also instantiates the sensor.
     * 2. Utilizes a mocked construction to create a double of a InstantPowerConsumptionValue, and placing a stub on the method getValueAsString.
     * 3. The first test involves using the .constructed() method to save all instances of created InstantPowerConsumptionValue doubles,
     * into a list of InstantPowerConsumptionValue. It then checks the size of that list using .size(), and compares it against the expected size of 1.
     * #Important# It is imperative that we call getReading on sensor before this operation.
     * 4. The second test involves accessing the first value saved on the list created above using get(0) and calling getValueAsString,
     * matching it against the expected string.
     * 5. The third test utilizes the InstantPowerConsumptionValue that is created by calling getReading unto the sensor created at the top
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
        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);

        // 2.
        try (MockedConstruction<InstantPowerConsumptionValue> instPowConsDouble = mockConstruction(InstantPowerConsumptionValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("40");
        })) {

            //Act & Assert

            InstantPowerConsumptionValue value = (InstantPowerConsumptionValue) sensor.getReading();

            // 3.
            List<InstantPowerConsumptionValue> values = instPowConsDouble.constructed();
            int listOfDoublesSize = values.size();
            assertEquals(1, listOfDoublesSize);

            // 4.
            String expected = "40";
            String result = values.get(0).getValueAsString();
            assertEquals(expected,result);

            // 5.
            String mockedInstPowConsValue = value.getValueAsString();
            assertEquals(expected, mockedInstPowConsValue);
        }
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

    /**
     * This test ensures the getReading method throws an exception if the reading is empty. Creates a simhardware double
     * in order to place a stub on getValue which is an empty String. The expectation is that the process throws an
     * Instantiation Exception, returning a String stating "Invalid reading". To validate the process, an Exception is
     * thrown and its message is extracted using getMessage and compared against the expected message.
     * @throws InstantiationException On invalid parameters
     *
     */
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

    /**
     * This test ensures the getReading method throws an exception if there is an invalid reading number. Creates a
     * simhardware double in order to place a stub on getValue which is an invalid reading number. The expectation
     * is that the process throws an Instantiation Exception, returning a String stating "Invalid reading". To validate
     * the process, an Exception is thrown and its message is extracted using getMessage and compared against the
     * expected message.
     * @throws InstantiationException On invalid parameters
     *
     */
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

    /**
     * This test ensures the getLog method returns an empty list. Creates a simhardware double in order to place a stub
     * on getValue with a controlled and valid return. The expectation is that the process occurs successfully. To
     * validate it, the log is extracted using getLog and compared against the expected empty list.
     */
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

    /**
     * This test ensures the getLog method returns the log. Creates a simhardware double in order to place a stub on
     * getValue with a controlled and valid return. The expectation is that the process occurs successfully. To validate
     * it, the log is extracted using getLog and compared against the expected log.
     */
    @Test
    void getLog_ReturnsLog() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);

        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);

        when(simHardware.getValue()).thenReturn("36");
        sensor.getReading();

        String expected = "36";


        //Act
        String result = sensor.getLog().get(0);

        //Assert
        assertEquals(expected,result);
    }
    /**
     * This test ensures the getLog method returns the log after multiple readings. Creates a simhardware double in
     * order to place a stub on getValue with a controlled and valid return. It places a second stub on getValue with
     * the expectation is that the process occurs successfully. A String is created with the expected log and,
     * o validate it, the log is extracted using getLog on the second position - index 1 - and compared against
     * the expected log.
     */
    @Test
    void getLog_ReturnsCorrectLogAfterMultipleReadingsDifferentValues() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);

        InstantPowerConsumptionSensor sensor = new InstantPowerConsumptionSensor(sensorName, simHardware);

        when(simHardware.getValue()).thenReturn("30");
        sensor.getReading();
        when(simHardware.getValue()).thenReturn("50");
        sensor.getReading();

        String expected = "50";

        //Act

        String result = sensor.getLog().get(1);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test ensures the getType method returns the type successfully. It creates a simhardware double in order to
     * instantiate a sensor of InstantPowerConsumptionSensor type. It then creates the expected type String and recurs
     * to getType to extract the result. The expected and result Strings are then compared.
     */
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
}
