package smarthome.mapper;

import org.junit.jupiter.api.Test;
import smarthome.domain.log.Log;
import smarthome.domain.sensor.sensorvalues.HumidityValue;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.mapper.dto.LogDTO;
import smarthome.domain.vo.logvo.TimeStampVO;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogMapperTest {

    /**
     * Tests the scenario when a null Log or null iterable of Logs is provided to the domainToDTO method, expecting an
     * IllegalArgumentException to be thrown.
     * <p>
     * This test method verifies that calling the domainToDTO method of the LogMapper class with a null Log object or
     * null iterable of Logs results in an IllegalArgumentException being thrown, as expected. It arranges the expected
     * error message. Then, it invokes the domainToDTO method with a null parameter within an assertThrows block to
     * capture the exception. Finally, it verifies that the thrown exception is of type IllegalArgumentException and
     * that its message matches the expected one.
     */
    @Test
    void whenProvidedLogIsNull_throwIllegalArgumentException(){
        // Arrange
        String expected = "Invalid parameter";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()
                -> LogMapper.domainToDTO((Log)null));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> LogMapper.domainToDTO((List<Log>) null));
        String result2 = exception2.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
    }

    /**
     * Tests the scenario when valid parameters are provided to the constructor, expecting the creation of a LogDTO
     * object with accessible attributes.
     * <p>
     * This test method verifies that when valid parameters are provided to the constructor of the LogDTO class, a
     * LogDTO object is created successfully and its attributes are accessible. It arranges the necessary mock objects
     * representing valid parameters for the constructor, including mock LogIDVO, LocalDateTime, HumidityValue,
     * SensorIDVO, DeviceIDVO, and SensorTypeIDVO objects. It also creates a mock Log object using these mock objects.
     * The domainToDTO method of the LogMapper class is then invoked with the mock Log object to create a LogDTO object.
     * Finally, the method retrieves each attribute of the LogDTO object using getter methods and asserts that the
     * retrieved attribute values match the expected values based on the mock objects provided.
     */
    @Test
    void whenProvidedValidParameters_constructorCreatesObjectAndAttributesAreAccessible(){
        // Arrange
        String logID = UUID.randomUUID().toString();
        LogIDVO logIDVO = mock(LogIDVO.class);
        when(logIDVO.getID()).thenReturn(logID);

        // Due to technical testing reasons, the result is truncated to minutes, due to technical reasons
        String time = LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.MINUTES).toString();
        TimeStampVO localDateTime = mock(TimeStampVO.class);
        when(localDateTime.getValue()).thenReturn(LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.MINUTES));

        String reading = "60";
        HumidityValue readingObj= mock(HumidityValue.class);
        when(readingObj.getValue()).thenReturn(60);

        String sensorID = UUID.randomUUID().toString();
        SensorIDVO sensorIDVO = mock(SensorIDVO.class);
        when(sensorIDVO.getID()).thenReturn(sensorID);

        String deviceID = UUID.randomUUID().toString();
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        when(deviceIDVO.getID()).thenReturn(deviceID);

        String sensorTypeID = UUID.randomUUID().toString();
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeIDVO.getID()).thenReturn(sensorTypeID);


        // Ideally, the log should be mocked, unable to do so due to mockito limitations when stubbing SensorValueObject
        Log log = new Log(logIDVO,localDateTime,readingObj,sensorIDVO,deviceIDVO,sensorTypeIDVO);

        LogDTO logDTO = LogMapper.domainToDTO(log);

        // Act
        String resultLogID = logDTO.logID();
        // Due to technical testing reasons, the result is truncated to minutes, due to technical reasons
        String dateTime = logDTO.localDateTime();
        String resultTime = dateTime.substring(0, dateTime.lastIndexOf(":"));
        String resultReading = logDTO.reading();
        String resultSensorID = logDTO.sensorID();
        String resultDeviceID = logDTO.deviceID();
        String resultSensorTypeID = logDTO.sensorTypeID();

        // Assert
        assertEquals(logID,resultLogID);
        assertEquals(time,resultTime);
        assertEquals(reading,resultReading);
        assertEquals(sensorID,resultSensorID);
        assertEquals(deviceID,resultDeviceID);
        assertEquals(sensorTypeID,resultSensorTypeID);
    }

    /**
     * Tests the scenario when two valid Logs are provided to the constructor, expecting the creation of a list of
     * LogDTO objects with accessible attributes.
     * <p>
     * This test method verifies that when two valid Logs are provided to the constructor of the LogDTO class, a list
     * of LogDTO objects is created successfully and their attributes are accessible. It arranges the necessary mock
     * objects representing valid parameters for the constructor, including mock LogIDVO, LocalDateTime, HumidityValue,
     * SensorIDVO, DeviceIDVO, and SensorTypeIDVO objects. Two mock Logs are created using these mock objects, and a
     * list of Logs is constructed. The domainToDTO method of the LogMapper class is then invoked with the list of Logs
     * to create an iterable collection of LogDTO objects. The LogDTO objects are then converted to a list for easier
     * access. Finally, the method retrieves each attribute of the LogDTO objects using getter methods and asserts that
     * the retrieved attribute values match the expected values based on the mock objects provided.
     */
    @Test
    void whenProvidedTwoLogs_constructorCreatesListAndAttributesAreAccessible(){
        // Arrange
        String logID1 = UUID.randomUUID().toString();
        LogIDVO logIDVO1 = mock(LogIDVO.class);
        when(logIDVO1.getID()).thenReturn(logID1);

        String logID2 = UUID.randomUUID().toString();
        LogIDVO logIDVO2 = mock(LogIDVO.class);
        when(logIDVO2.getID()).thenReturn(logID2);

        String time = LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.MINUTES).toString();
        TimeStampVO localDateTime = mock(TimeStampVO.class);
        when(localDateTime.getValue()).thenReturn(LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.MINUTES));

        String reading = "60";
        HumidityValue readingObj= mock(HumidityValue.class);
        when(readingObj.getValue()).thenReturn(60);

        String sensorID = UUID.randomUUID().toString();
        SensorIDVO sensorIDVO = mock(SensorIDVO.class);
        when(sensorIDVO.getID()).thenReturn(sensorID);

        String deviceID = UUID.randomUUID().toString();
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        when(deviceIDVO.getID()).thenReturn(deviceID);

        String sensorTypeID = UUID.randomUUID().toString();
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeIDVO.getID()).thenReturn(sensorTypeID);

        // Ideally, the log should be mocked, unable to do so due to mockito limitations when stubbing SensorValueObject
        Log log1 = new Log(logIDVO1,localDateTime,readingObj,sensorIDVO,deviceIDVO,sensorTypeIDVO);
        Log log2 = new Log(logIDVO2,localDateTime,readingObj,sensorIDVO,deviceIDVO,sensorTypeIDVO);

        List<Log> logList = new ArrayList<>();
        logList.add(log1);
        logList.add(log2);

        Iterable<LogDTO> iterableLogDTO = LogMapper.domainToDTO(logList);

        List<LogDTO> logDTOList = new ArrayList<>();
        iterableLogDTO.forEach(logDTOList::add);

        LogDTO resultLog1 = logDTOList.get(0);
        LogDTO resultLog2 = logDTOList.get(1);

        // Act
        String resultLogID1 = resultLog1.logID();
        // Due to technical testing reasons, the result is truncated to minutes, due to technical reasons
        String dateTime1 = resultLog1.localDateTime();
        String resultTime1 = dateTime1.substring(0, dateTime1.lastIndexOf(":"));
        String resultReading1 = resultLog1.reading();
        String resultSensorID1 = resultLog1.sensorID();
        String resultDeviceID1 = resultLog1.deviceID();
        String resultSensorTypeID1 = resultLog1.sensorTypeID();

        String resultLogID2 = resultLog2.logID();
        // Due to technical testing reasons, the result is truncated to minutes, due to technical reasons
        String dateTime2 = resultLog2.localDateTime();
        String resultTime2 = dateTime2.substring(0, dateTime1.lastIndexOf(":"));
        String resultReading2 = resultLog2.reading();
        String resultSensorID2 = resultLog2.sensorID();
        String resultDeviceID2 = resultLog2.deviceID();
        String resultSensorTypeID2 = resultLog2.sensorTypeID();

        // Assert
        assertEquals(logID1,resultLogID1);
        assertEquals(time,resultTime1);
        assertEquals(reading,resultReading1);
        assertEquals(sensorID,resultSensorID1);
        assertEquals(deviceID,resultDeviceID1);
        assertEquals(sensorTypeID,resultSensorTypeID1);

        assertEquals(logID2,resultLogID2);
        assertEquals(time,resultTime2);
        assertEquals(reading,resultReading2);
        assertEquals(sensorID,resultSensorID2);
        assertEquals(deviceID,resultDeviceID2);
        assertEquals(sensorTypeID,resultSensorTypeID2);
    }
}
