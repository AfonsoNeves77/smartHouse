package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.EnergyConsumptionSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class EnergyConsumptionTest {
    /**
     * Test for the constructor of the EnergyConsumptionSensor with a null name
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new EnergyConsumptionSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test for the constructor of the EnergyConsumptionSensor with an empty name
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameIsEmpty(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = " ";
        String expected = "Invalid parameter";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new EnergyConsumptionSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test for the getName method of the EnergyConsumptionSensor
     */
    @Test
    void getName_SuccessfullyReturns(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);

        //Act
        String result = sensor.getName();

        //Assert
        assertEquals(sensorName, result);
    }

    /**
     * Test for the getUnit method of the EnergyConsumptionSensor
     */
    @Test
    void getUnit_SuccessfullyReturns(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Wh";

        //Act
        String result = sensor.getUnit();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor
     * @throws InstantiationException
     */
    @Test
    void getReading_ReturnsValueCorrectly() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "50";

        //Act
        Value<Integer> value = sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an empty reading
     */
    @Test
    void getReading_throwsExceptionIfEmptyReading() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn(" ");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an invalid reading
     */
    @Test
    void getReading_throwsExceptionIfInvalidReadingNumber() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("abc");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with a negative reading
     */
    @Test
    void getReading_throwsExceptionIfReadingIsNegative(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("-50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid reading";

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the EnergyConsumptionSensor
     * @throws InstantiationException
     */
    @Test
    void getLog_ReturnsCorrectLog() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "50";

        //Act
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = sensor.getLog().get(0);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the EnergyConsumptionSensor with multiple readings
     * @throws InstantiationException
     */
    @Test
    void getLog_ReturnsCorrectLogAfterMultipleReadingsDifferentValues() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("50");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "60";

        //Act
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        when(simHardware.getValue("15-12-2020 14:15:45", "16-12-2020 14:15:45")).thenReturn("60");
        sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45");
        String result = sensor.getLog().get(1);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getLog method of the EnergyConsumptionSensor with an empty log
     */
    @Test
    void getLog_ReturnsEmptyLogIfNoReadings() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        int expected = 0;

        //Act
        int result = sensor.getLog().size();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an initial date after the final date
     */
    @Test
    void getReading_throwsExceptionIfInitialDateIsAfterFinalDate() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date: initial date must be before final date and final date must be before current date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("16-12-2020 14:15:45", "15-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an initial date after the current date
     */
    @Test
    void getReading_throwsExceptionIfInitialDateIsAfterCurrentDate() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date: initial date must be before final date and final date must be before current date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("16-12-2021 14:15:45", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with a final date after the current date
     */
    @Test
    void getReading_throwsExceptionIfFinalDateIsAfterCurrentDate() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date: initial date must be before final date and final date must be before current date";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2050 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an invalid initial date format
     */
    @Test
    void getReading_throwsExceptionIfInitialDateFormatIsInvalid() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date format: expected 'dd-MM-yyyy HH:mm:ss'";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45:00", "16-12-2020 14:15:45"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an invalid final date format
     */
    @Test
    void getReading_throwsExceptionIfFinalDateFormatIsInvalid() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date format: expected 'dd-MM-yyyy HH:mm:ss'";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45", "16-12-2020 14:15:45:00"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test for the getReading method of the EnergyConsumptionSensor with an invalid initial and final date format
     */
    @Test
    void getReading_throwsExceptionIfInitialDateAndFinalDateFormatsAreInvalid() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("50.5");
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Invalid date format: expected 'dd-MM-yyyy HH:mm:ss'";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> sensor.getReading("15-12-2020 14:15:45:00", "16-12-2020 14:15:45:00"));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void getType_ReturnsCorrectType() {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        EnergyConsumptionSensor sensor = new EnergyConsumptionSensor(sensorName, simHardware);
        String expected = "Energy Consumption";

        //Act
        String result = sensor.getType();

        //Assert
        assertEquals(expected, result);
    }
}
