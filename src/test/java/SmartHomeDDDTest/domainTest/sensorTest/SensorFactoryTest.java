package SmartHomeDDDTest.domainTest.sensorTest;

import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.domain.sensor.SunriseSensor;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorFactoryTest {

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenEmptyPath_constructorThrowsIllegalArgument(){
        // Arrange
        String path = " ";
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactory(path));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenNullPath_constructorThrowsIllegalArgument(){
        // Arrange
        String path = null;
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactory(path));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that when given an invalid path. The constructor throws an exception.
     */
    @Test
    void givenInvalidPath_constructorThrowsIllegalArgument(){
        // Arrange
        String path = "Invalid path";
        String expected = "Error reading file";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->
                new SensorFactory(path));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Validates the method createSensor, when all prerequisites are met, successfully returns the specified sensor object.
     */
    @Test
    void givenCorrectVOs_createSensorReturnsASensorObject(){
        // Arrange
        String path = "config.sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn("Sunrise");

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            SunriseSensor sensorExpected = mockedConstruction.constructed().get(0);

            // Assert
            assertEquals(sensorExpected,sensorResult);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null SensorNameVo. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullSensorNameVO_createSensorReturnsNull(){
        // Arrange
        String path = "config.sensor";
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(null,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null DeviceIDVO. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullDeviceIDVO_createSensorReturnsNull(){
        // Arrange
        String path = "config.sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,null,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when given a null SensorTypeIDVO. It also ensures there was no
     * instance of Sunrise Sensor created, as the method returns null before reaching the creation phase.
     */
    @Test
    void givenNullSensorTypeID_createSensorReturnsNull(){
        // Arrange
        String path = "config.sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,null);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when a valid SensorTypeIDVO returns a null when called with getID().
     * It also ensures there was no instance of Sunrise Sensor created, as the method returns null before reaching the
     * creation phase.
     */
    @Test
    void givenSensorTypeIDVO_whenGetIDFromVOreturnsNull_createSensorReturnsNull(){
        // Arrange
        String path = "config.sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn(null);

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }

    /**
     * This test ensures createSensor returns null when a valid SensorTypeIDVO returns an invalid ID when called with getID().
     * It also ensures there was no instance of Sunrise Sensor created, as the method returns null before reaching the
     * creation phase.
     */
    @Test
    void givenSensorTypeIDVO_whenGetIDFromVOreturnsInvalidType_createSensorReturnsNull(){
        // Arrange
        String path = "config.sensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn("Nuclear");

        try(MockedConstruction<SunriseSensor> mockedConstruction = mockConstruction(SunriseSensor.class)) {
            SensorFactory factory = new SensorFactory(path);

            // Act
            SunriseSensor sensorResult = (SunriseSensor) factory.createSensor(sensorName,deviceID,sensorType);
            int numberMockedConstructions = mockedConstruction.constructed().size();

            // Assert
            assertNull(sensorResult);
            assertEquals(0,numberMockedConstructions);
        }
    }
}
