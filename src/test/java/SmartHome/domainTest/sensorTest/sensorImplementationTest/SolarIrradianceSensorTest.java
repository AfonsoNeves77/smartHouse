package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.SolarIrradianceSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SolarIrradianceValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * SolarIrradianceSensorTest class for testing SolarIrradianceSensor
 */
public class SolarIrradianceSensorTest {

    /**
     * SolarIrradianceSensor object to be used in tests
     * SimHardware object to be used in tests
     */

    private SolarIrradianceSensor solarIrradianceSensor;
    private SimHardware simHardwareMock;

    /**
     * Setting up the SolarIrradianceSensor and SimHardware objects
     * @throws InstantiationException if the SolarIrradianceSensor object cannot be created
     */

    @BeforeEach
    void setUp() throws InstantiationException {
        String sensorName = "Solar Irradiance Sensor";
        simHardwareMock = mock(SimHardware.class);
        solarIrradianceSensor = new SolarIrradianceSensor(sensorName, simHardwareMock);
    }

    /**
     * Testing the SolarIrradianceSensor constructor
     * Should throw an exception if the sensor name is null
     */

    @Test
    void sensorConstructor_throwsExceptionIfNameNull() {
//        Arrange
        String sensorName = null;
        String expected = "Invalid parameter";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SolarIrradianceSensor(sensorName, simHardwareMock));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the SolarIrradianceSensor constructor
     * Should throw an exception if the sensor name is empty
     */

    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty() {
//        Arrange
        String sensorName = "   ";
        String expected = "Invalid parameter";
//        Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new SolarIrradianceSensor(sensorName, simHardwareMock));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getName method
     * Successfully returns the sensor name
     */

    @Test
    void getName_SuccessfullyReturns() {
//        Arrange
        String expected = "Solar Irradiance Sensor";
//        Act
        String result = solarIrradianceSensor.getName();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getUnit method
     * Successfully returns the unit of the sensor
     */

    @Test
    void getUnit_SuccessfullyReturns() {
//        Arrange
        String expected = "W/m2";
//        Act
        String result = solarIrradianceSensor.getUnit();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getType method
     * Successfully returns the type of the sensor
     */

    @Test
    void getType_ReturnsCorrectType() {
//        Arrange
        String expected = "SolarIrradiance";
//         Act
        String result = solarIrradianceSensor.getType();
//         Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getReading method
     * Successfully returns the value of the sensor
     */

    @Test
    void getReading_ReturnsValueCorrectly() throws InstantiationException {
//        Arrange
        String returnValue = "30";
        when(simHardwareMock.getValue()).thenReturn(returnValue);
        String expected = "30";
//        Act
        SolarIrradianceValue value = (SolarIrradianceValue) solarIrradianceSensor.getReading();
        String result = value.getValueAsString();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getLog method
     * Successfully returns the log of the sensor
     */

    @Test
    void getLog_ReturnsLogCorrectly() throws InstantiationException {
//        Arrange
        String simulatedValue = "30";
        when(simHardwareMock.getValue()).thenReturn(simulatedValue);
        String expected = "30";
//        Act
        solarIrradianceSensor.getReading();
        String result = solarIrradianceSensor.getLog().get(0);
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getLog method
     * Successfully returns the log of the sensor with multiple readings
     */

    @Test
    void getLog_ReturnsLogCorrectlyMultipleReadings() throws InstantiationException {
//        Arrange
        String simulatedValue = "30";
        when(simHardwareMock.getValue()).thenReturn(simulatedValue);
        String expected = "30";
//        Act
        solarIrradianceSensor.getReading();
        solarIrradianceSensor.getReading();
        String result = solarIrradianceSensor.getLog().get(1);
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Testing the getLog method
     * Successfully returns an empty list if no logs
     */

    @Test
    void getLog_SuccessfullyReturnsEmptyListIfNoLogs() {
//        Arrange
        int expected = 0;
//        Act
        int result = solarIrradianceSensor.getLog().size();
//        Assert
        assertEquals(expected, result);
    }
}
