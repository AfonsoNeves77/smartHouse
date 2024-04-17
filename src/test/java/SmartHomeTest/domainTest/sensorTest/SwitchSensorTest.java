package SmartHomeTest.domainTest.sensorTest;

import smarthome.domain.sensor.SwitchSensor;
import smarthome.domain.sensor.externalservices.SimHardware;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

class SwitchSensorTest {

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullNameVO_ThrowsIllegalArgument() {
        // Arrange
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SwitchSensor(null, deviceID, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullDeviceID_ThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SwitchSensor(sensorName, null, sensorTypeID));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullSensorTypeID_ThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        String expected = "Invalid parameters.";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new SwitchSensor(sensorName, deviceID, null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorName() : VO, then calling toString
     * on the same VO;
     */
    @Test
    void callingGetSensorNameToString_ReturnsNameVoAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Test");

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorName().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getSensorTypeID() : VO, then calling getID
     * again on the same VO to receive its value as String;
     */
    @Test
    void callingGetSensorTypeIDToString_ReturnsSensorTypeIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        when(sensorTypeID.getID()).thenReturn("Test");

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getSensorTypeID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getDeviceID() : VO, then calling getID
     * again on the same VO to receive its value as String;
     */
    @Test
    void callingGetDeviceIDWithgetID_ReturnsDeviceIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.getID()).thenReturn("Test");

        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);


        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);

            // Act
            String result = sensor.getDeviceID().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the encapsulated VO's value is accessbile by calling getID() : VO, then calling getID again
     * on the same VO to receive its value as String;
     */
    @Test
    void callingGetIDToGetSensorIDVOThenCallingGetIDAgainToGetString_ReturnsSensorIDAsString() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context)
                -> when(mock.getID()).thenReturn(expected))) {

            SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);
            // Act
            String result = sensor.getId().getID();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(expected, result);
            assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * This test ensures the constructor throws an Illegal Argument Exception when given a null VO. This test also
     * attempts to create a mocked construction of the sensorID, however as can be observed, the mocked construction
     * does not get to be created as it does not reach that stage.
     */
    @Test
    void givenNullSimHardware_ThrowsIllegalArgument() {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);
        String expected = "Invalid external service";

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            // Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sensor.getReading(null));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            // Assert
            assertEquals(0, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test ensures the method getSunriseTime returns an object of the type ZonedDateTimeDouble, which, when called
     * with getValue and toString, returns the encapsulated value.
     */
    @Test
    void givenValidParameters_ReturnsSwitchValueToString() throws InstantiationException {
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        SimHardware simHardware = mock(SimHardware.class);
        SwitchSensor sensor = new SwitchSensor(sensorName, deviceID, sensorTypeID);

        String reading = "On";
        String expected = "On";

        when(simHardware.getValue()).thenReturn(reading);

        // Act
        String result = sensor.getReading(simHardware).getValue();

        // Assert
        assertEquals(expected, result);
    }
}