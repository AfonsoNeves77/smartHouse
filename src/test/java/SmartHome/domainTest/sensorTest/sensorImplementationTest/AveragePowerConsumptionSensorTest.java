package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.AveragePowerConsumptionSensor;
import SmartHome.domain.sensor.sensorImplementation.DewPointSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.AveragePowerConsumptionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AveragePowerConsumptionSensorTest {

//    ### ISOLATION TESTS ###

    /**
     * Test for the constructor of the AveragePowerConsumptionSensor class. It should throw an InstantiationException if the name is null.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameNull() {
        //Arrange
        SimHardware simHardwareInteger = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new AveragePowerConsumptionSensor(sensorName, simHardwareInteger));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the constructor of the AveragePowerConsumptionSensor class. It should throw an InstantiationException if the name is empty.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameIsEmpty() {
        //Arrange
        SimHardware simHardwareInteger = mock(SimHardware.class);
        String sensorName = " ";
        String expected = "Invalid parameter";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new AveragePowerConsumptionSensor(sensorName, simHardwareInteger));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getName method of the AveragePowerConsumptionSensor class. It should return the name of the sensor.
     */
    @Test
    void getName_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);

        //Act
        String result = sensor.getName();

        //Assert
        assertEquals(sensorName, result);
    }

    /**
     * Test for the getUnit method of the AveragePowerConsumptionSensor class. It should return the unit of the sensor.
     */
    @Test
    void getUnit_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "W";

        //Act
        String result = sensor.getUnit();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an InstantiationException if the reading is empty.
     */
    @Test
    void getReading_throwsExceptionIfEmptyReading() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn(" ");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an InstantiationException if the reading is not a number.
     */
    @Test
    void getReading_throwsExceptionIfInvalidReadingNumber() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("abc");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an InstantiationException if the reading is negative.
     */
    @Test
    void getReading_throwsExceptionIfReadingIsNegative() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("-50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }


    /**
     * Test for the getType method of the AveragePowerConsumptionSensor class. It should return the type of the sensor.
     */
    @Test
    void getType_ReturnsCorrectType() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Average Power Consumption Sensor";

        //Act
        String result = sensor.getType();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the AveragePowerConsumptionSensor class. It should return the log of the sensor after multiple readings with the same value.
     */
    @Test
    void getLog_ReturnsEmptyLogIfNoReadings() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        int expected = 0;

        //Act
        int result = sensor.getLog().size();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the initial date is after the final date.
     */
    @Test
    void getReading_throwsExceptionIfInitialDateIsAfterFinalDate() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("16-12-2020 14:15:45", "15-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the initial date is after the current date.
     */
    @Test
    void getReading_throwsExceptionIfInitialDateIsAfterCurrentDate() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("16-12-2021 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the final date is after the current date.
     */
    @Test
    void getReading_throwsExceptionIfFinalDateIsAfterCurrentDate() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2050 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the initial date format is invalid.
     */
    @Test
    void getReading_throwsExceptionIfInitialDateFormatIsInvalid() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45:00", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the final date format is invalid.
     */
    @Test
    void getReading_throwsExceptionIfFinalDateFormatIsInvalid() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45:00"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should throw an IllegalArgumentException if the initial date and final date formats are invalid.
     */
    @Test
    void getReading_throwsExceptionIfInitialDateAndFinalDateFormatsAreInvalid() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45:00", "16-12-2020 14:15:45:00"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Successfully returns getReading value.
     * 1. Instantiates a simHardware double, placing a stub for the method getValue. Also instantiates the sensor.
     * 2. Utilizes a mocked construction to create a double of a AveragePowerConsumptionValue, and placing a stub on the method getValueAsString.
     * 3. The first test involves using the .constructed() method to save all instances of created AveragePowerConsumptionValue doubles,
     * into a list of AveragePowerConsumptionValue. It then checks the size of that list using .size(), and compares it against the expected size of 1.
     * #Important# It is imperative that we call getReading on sensor before this operation.
     * 4. The second test involves accessing the first value saved on the list created above using get(0) and calling getValueAsString,
     * matching it against the expected string.
     * 5. The third test utilizes the averagePowerConsumptionValueDouble that is created by calling getReading unto the sensor created at the top
     * and ensuring that when we call getValueAsString unto that value, it returns the expected string.
     *
     * @throws InstantiationException If sensor parameters invalid.
     */
    @Test
    void getReading_ReturnsValueCorrectly_Isolation() throws InstantiationException {
        //Arrange
        // 1.
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor 1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);

        // 2.
        try (MockedConstruction<AveragePowerConsumptionValue> averagePowerConsumptionValueDouble = mockConstruction(AveragePowerConsumptionValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("50");
        })) {

            //Act & Assert

            AveragePowerConsumptionValue value = (AveragePowerConsumptionValue) sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");

            // 3.
            List<AveragePowerConsumptionValue> values = averagePowerConsumptionValueDouble.constructed();
            int listOfDoublesSize = values.size();
            assertEquals(1, listOfDoublesSize);

            // 4.
            String expected = "50";
            String result = values.get(0).getValueAsString();
            assertEquals(expected, result);

            // 5.
            String mockedAveragePowerConsumptionValue = value.getValueAsString();
            assertEquals(expected, mockedAveragePowerConsumptionValue);
        }
    }

//    ### INTEGRATION TESTS ###

    /**
     * Test for the getReading method of the AveragePowerConsumptionSensor class. It should return the value of the sensor.
     */
    @Test
    void getReading_ReturnsValueCorrectly() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "50";

        //Act
        Value<Integer> value = sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the AveragePowerConsumptionSensor class. It should return the log of the sensor.
     */
    @Test
    void getLog_ReturnsCorrectLog() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "50";

        //Act
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = sensor.getLog().get(0);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the AveragePowerConsumptionSensor class. It should return the log of the sensor after multiple readings with different values.
     */
    @Test
    void getLog_ReturnsCorrectLogAfterMultipleReadingsDifferentValues() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        AveragePowerConsumptionSensor sensor = new AveragePowerConsumptionSensor(sensorName, simHardware);
        String expected = "60";

        //Act
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("60");
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = sensor.getLog().get(1);

        //Assert
        assertEquals(expected, result);
    }
}