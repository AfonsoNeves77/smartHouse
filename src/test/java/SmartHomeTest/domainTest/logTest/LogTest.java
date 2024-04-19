package SmartHomeTest.domainTest.logTest;

import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import smarthome.domain.log.Log;
import smarthome.domain.sensor.values.HumidityValue;
import smarthome.domain.sensor.values.PositionValue;
import smarthome.domain.sensor.values.SensorValueObject;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class LogTest {

    /**
     * Tests that creating a Log with any null parameters throws an IllegalArgumentException,
     * ensuring the integrity of the entire Log aggregate.
     * The System Under Test (SUT) in this test is the entire Log aggregate, including its
     * associated ValueObject instances.
     * @throws InstantiationException if there is an error in instantiating test objects.
     */
    @Test
    void whenGivenANullParameter_throwsIllegalArgumentException() throws InstantiationException {
        // Arrange
        SensorValueObject<?> reading = new HumidityValue("70");
        SensorIDVO sensorID = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorTypeID = new SensorTypeIDVO("Humidity");
        LogIDVO logID = new LogIDVO(UUID.randomUUID());
        LocalDateTime time = LocalDateTime.now();
        String expected = "Invalid House Entity parameters.";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Log(null,sensorID,deviceID,sensorTypeID));
        String result1 = exception1.getMessage();
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Log(reading, null, deviceID,sensorTypeID));
        String result2 = exception2.getMessage();
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> new Log(reading, sensorID, null,sensorTypeID));
        String result3 = exception3.getMessage();
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> new Log(reading,sensorID,deviceID,null));
        String result4 = exception4.getMessage();
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> new Log(logID,null,reading,sensorID,deviceID,null));
        String result5 = exception5.getMessage();
        Exception exception6 = assertThrows(IllegalArgumentException.class, () -> new Log(null,time,reading,sensorID,deviceID,sensorTypeID));
        String result6 = exception6.getMessage();
        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
    }

    /**
     * Tests that creating a Log with any null parameters throws an IllegalArgumentException,
     * ensuring the integrity of the Log entity within the aggregate.
     * The System Under Test (SUT) in this test is the Log entity. Mocked ValueObject instances are used for testing.
     */
    @Test
    void whenGivenANullParameter_throwsIllegalArgumentException_SUTEntity() {
        // Arrange
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        LogIDVO logID = mock(LogIDVO.class);
        LocalDateTime time = mock(LocalDateTime.class);
        String expected = "Invalid House Entity parameters.";
        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () -> new Log(null, sensorID, deviceID, sensorTypeID));
        String result1 = exception1.getMessage();
        Exception exception2 = assertThrows(IllegalArgumentException.class, () -> new Log(reading, null, deviceID, sensorTypeID));
        String result2 = exception2.getMessage();
        Exception exception3 = assertThrows(IllegalArgumentException.class, () -> new Log(reading, sensorID, null, sensorTypeID));
        String result3 = exception3.getMessage();
        Exception exception4 = assertThrows(IllegalArgumentException.class, () -> new Log(reading, sensorID, deviceID, null));
        String result4 = exception4.getMessage();
        Exception exception5 = assertThrows(IllegalArgumentException.class, () -> new Log(null,time,reading, sensorID, deviceID, sensorTypeID));
        String result5 = exception5.getMessage();
        Exception exception6 = assertThrows(IllegalArgumentException.class, () -> new Log(logID, null, reading, sensorID, deviceID, sensorTypeID));
        String result6 = exception6.getMessage();
        // Assert
        assertEquals(expected, result1);
        assertEquals(expected, result2);
        assertEquals(expected, result3);
        assertEquals(expected, result4);
        assertEquals(expected, result5);
        assertEquals(expected, result6);
    }

    /**
     * Tests that the Log entity returns the correct SensorIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the SensorIDVO associated with it.
     * @throws InstantiationException if there is an error in instantiating test objects.
     */
    @Test
    void returnsCorrectSensorID() throws InstantiationException {
        // Arrange
        SensorValueObject<?> reading = new HumidityValue("70");
        SensorIDVO sensorID = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorTypeID = new SensorTypeIDVO("Humidity");
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        SensorIDVO result = log.getSensorID();
        // Assert
        assertEquals(sensorID,result);
    }

    /**
     * Tests that the Log entity returns the correct SensorIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the SensorIDVO associated with it. Mocked ValueObject instances
     * are used for testing.
     */
    @Test
    void returnsCorrectSensorID_SUTEntity() {
        // Arrange
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        SensorIDVO result = log.getSensorID();
        // Assert
        assertEquals(sensorID,result);
    }

    /**
     * Tests that the Log entity returns the correct DeviceIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the DeviceIDVO associated with it.
     * @throws InstantiationException if there is an error in instantiating test objects.
     */
    @Test
    void returnsCorrectDeviceID() throws InstantiationException {
        // Arrange
        SensorValueObject<?> reading = new HumidityValue("70");
        SensorIDVO sensorID = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorTypeID = new SensorTypeIDVO("Humidity");
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        DeviceIDVO result = log.getDeviceID();
        // Assert
        assertEquals(deviceID,result);
    }

    /**
     * Tests that the Log entity returns the correct DeviceIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the DeviceIDVO associated with it. Mocked ValueObject instances
     * are used for testing.
     */
    @Test
    void returnsCorrectDeviceID_SUTEntity() {
        // Arrange
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        DeviceIDVO result = log.getDeviceID();
        // Assert
        assertEquals(deviceID,result);
    }

    /**
     * Tests that the Log entity returns the correct SensorTypeIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the SensorTypeIDVO associated with it.
     * @throws InstantiationException if there is an error in instantiating test objects.
     */
    @Test
    void returnsCorrectSensorTypeID() throws InstantiationException {
        // Arrange
        SensorValueObject<?> reading = new HumidityValue("70");
        SensorIDVO sensorID = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorTypeID = new SensorTypeIDVO("Humidity");
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        SensorTypeIDVO result = log.getSensorTypeID();
        // Assert
        assertEquals(sensorTypeID,result);
    }

    /**
     * Tests that the Log entity returns the correct SensorTypeIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the SensorTypeIDVO associated with it. Mocked ValueObject instances
     * are used for testing.
     */
    @Test
    void returnsCorrectSensorTypeID_SUTEntity() {
        // Arrange
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);
        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);
        // Act
        SensorTypeIDVO result = log.getSensorTypeID();
        // Assert
        assertEquals(sensorTypeID,result);
    }

    /**
     * Tests that the Log entity returns the correct LogIDVO.
     * The System Under Test (SUT) in this test is the Log entity, which is expected
     * to correctly return the LogIDVO associated with it. Mocks a UUID construction
     * to ensure consistency in the LogIDVO generation.
     */
    @Test
    void returnsCorrectLogIDVO_SUTEntity(){
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        String expected = "Test";
        int expectedSize = 1;
        try (MockedConstruction<LogIDVO> mockUUID = mockConstruction(LogIDVO.class, (mock, context) ->
                when(mock.toString()).thenReturn(expected))) {

            Log log = new Log(reading,sensorID,deviceID,sensorTypeID);

            //Act
            String result = log.getId().toString();
            int resultSize = mockUUID.constructed().size();

            //Assert
            assertEquals(expected,result);
            assertEquals(expectedSize,resultSize);
        }
    }

    /**
     * This test ensures that Log object returns a proper LocalDateTime object as string, with the timestamp
     * of the time of testing. SUT: Log class + LocalDataTime
     */
    @Test
    void returnsCorrectTime(){
        // Arrange
        SensorValueObject<?> reading = mock(HumidityValue.class);
        SensorIDVO sensorID = mock(SensorIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        Log log = new Log(reading,sensorID,deviceID,sensorTypeID);

        String expected = LocalDateTime.now().toString();

        // Act
        String result = log.getTime().toString();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Tests if the getReading() method returns the correct reading value.
     * Casts the result to a PositionValue object to ensure successful type conversion.
     */
    @Test
    void returnsReading(){

        // Arrange
        SensorValueObject<?> reading = new PositionValue(5);
        SensorIDVO sensorID = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorTypeID = new SensorTypeIDVO("Humidity");
        LogIDVO logID = new LogIDVO(UUID.randomUUID());
        LocalDateTime time = LocalDateTime.now();

        Log log = new Log(logID,time,reading,sensorID,deviceID,sensorTypeID);

        // Act
        PositionValue result = (PositionValue) log.getReading();

        // Assert
        assertEquals(reading,result);
    }
}
