package SmartHomeDDDTest.domainTest.sensorTest;

import SmartHomeDDD.domain.sensor.SunsetSensor;
import SmartHomeDDD.domain.sensor.externalServices.SunTimeCalculator;
import SmartHomeDDD.domain.sensor.sensorValues.SunTimeValue;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Test class for SunsetSensor
 */

public class SunsetSensorTest {

    /**
     * This test ensures the constructor throws an IllegalArgumentException when given a null SensorNameVO.
     */

    @Test
    void givenNullName_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorNameVO sensorName = null;
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";
//            Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SunsetSensor(sensorName, deviceIDDouble, sensorTypeDouble));
//            Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * This test ensures the constructor throws an IllegalArgumentException when given a null SensorTypeIDVO.
     */

    @Test
    void givenNullSensorType_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorType = null;
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        String expected = "Parameters cannot be null";
//            Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SunsetSensor(sensorNameDouble, deviceIDDouble, sensorType));
//            Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * This test ensures the constructor throws an IllegalArgumentException when given a null DeviceIDVO.
     */

    @Test
    void givenNullDeviceID_whenCreatingSensor_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceID = null;
        String expected = "Parameters cannot be null";
//            Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SunsetSensor(sensorNameDouble, deviceID, sensorTypeDouble));
//            Assert
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     * This test ensures that the method getSunsetTime throws an IllegalArgumentException when given a null SunTimeCalculator.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenNullSunTimeCalculator_whenGettingSunsetTime_thenThrowException() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        SunTimeCalculator sunTimeCalculator = null;
        String date = "2021-06-01";
        String gpsLocation = "40.7128 : 74.0060";
        String expected = "Invalid external service";
        int expectedListSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            SunsetSensor sunsetSensor = new SunsetSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
//            Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    sunsetSensor.getSunsetTime(date, gpsLocation, sunTimeCalculator));
//            Assert
            String result = exception.getMessage();
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();

            assertEquals(expected, result);
            assertEquals(expectedListSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getSunsetTime returns the correct value when given a valid SunTimeCalculator.
     * It also ensures that the method creates a new instance of SunTimeValue.
     */

    @Test
    void givenValidSunTimeCalculator_whenGettingSunsetTime_thenReturnSunTimeValue() {
//        Arrange
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        SunTimeCalculator sunTimeCalculator = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
        int expectedSize = 1;

        String date = "2021-06-01";
        String gpsLocation = "40.7128 : 74.0060";

        ZonedDateTime zonedDateTimeDouble = mock(ZonedDateTime.class);
        when(zonedDateTimeDouble.toString()).thenReturn("2021-06-01T20:00:00Z");

        String expected = "2021-06-01T20:00:00Z";

        try (MockedConstruction<SunTimeValue> sunTimeValueMock = mockConstruction(SunTimeValue.class, (mock, context) -> {
            when(mock.getValue()).thenReturn(zonedDateTimeDouble);
        })) {
//            Act
            String result = sunsetSensor.getSunsetTime(date, gpsLocation, sunTimeCalculator).getValue().toString();
//            Assert
            List<SunTimeValue> createdInstances = sunTimeValueMock.constructed();
            assertEquals(expected, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getSensorName returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetSensorName_whenGettingSensorName_thenReturnSensorNameAsString() {
//        Arrange
        String name = "SunsetSensor";
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.getValue()).thenReturn(name);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            SunsetSensor sunsetSensor = new SunsetSensor(sensorName, deviceIDDouble, sensorTypeDouble);
//            Act
            String result = sunsetSensor.getSensorName().getValue();
//            Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(name, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getSensorTypeID returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetSensorTypeID_whenGettingSensorTypeID_thenReturnSensorTypeIDAsString() {
//        Arrange
        String sensorTypeID = "SunsetSensor";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.getID()).thenReturn(sensorTypeID);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            SunsetSensor sunsetSensor = new SunsetSensor(sensorNameDouble, deviceIDDouble, sensorType);
//            Act
            String result = sunsetSensor.getSensorTypeID().getID();
//            Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(sensorTypeID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getDeviceID returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetDeviceID_whenGettingDeviceID_thenReturnDeviceIDAsString() {
//        Arrange
        String deviceID = "SunsetSensor";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        when(deviceIDVO.getID()).thenReturn(deviceID);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class)) {
            SunsetSensor sunsetSensor = new SunsetSensor(sensorNameDouble, deviceIDVO, sensorTypeDouble);
//            Act
            String result = sunsetSensor.getDeviceID().getID();
//            Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(deviceID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }

    /**
     * This test ensures that the method getID returns the correct value.
     * It also ensures that the method creates a new instance of SensorIDVO.
     */

    @Test
    void givenGetID_whenGettingID_thenReturnSensorIDAsString() {
//        Arrange
        String sensorID = "123";
        SensorNameVO sensorNameDouble = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        DeviceIDVO deviceIDDouble = mock(DeviceIDVO.class);
        int expectedSize = 1;

        try (MockedConstruction<SensorIDVO> sensorIDVOMock = mockConstruction(SensorIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn(sensorID);
        })) {
            SunsetSensor sunsetSensor = new SunsetSensor(sensorNameDouble, deviceIDDouble, sensorTypeDouble);
//            Act
            String result = sunsetSensor.getId().getID();
//            Assert
            List<SensorIDVO> createdInstances = sensorIDVOMock.constructed();
            assertEquals(sensorID, result);
            assertEquals(expectedSize, createdInstances.size());
        }
    }
}