package smarthome.service;

import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.sensorvalues.SensorValueObject;
import smarthome.domain.sensor.sensorvalues.TemperatureValue;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.roomvo.RoomDimensionsVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;
import smarthome.domain.vo.DeltaVO;
import smarthome.domain.vo.logvo.TimeStampVO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LogServiceImplTest {
    /**
     * Test to verify that IllegalArgumentException is thrown when given null parameters.
     */
    @Test
    void whenGivenNullRepository_construtorThrowsIllegalArgumentException(){
        // Arrange
        String expected = "Repository cannot be null.";
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()
                -> new LogServiceImpl(null, deviceRepository, roomRepository));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> new LogServiceImpl(logRepository, null, roomRepository));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, ()
                -> new LogServiceImpl(logRepository, deviceRepository, null));
        String result3 = exception3.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null parameters.
     */
    @Test
    void whenGivenNullParameters_throwsIllegalArgumentException(){
        // Arrange
        String expected = "Invalid parameters";

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        LogServiceImpl service = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,initialTime,finalTime));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(deviceID,null, finalTime));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,null, null));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(deviceID,initialTime, null));
        String result4 = exception2.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null outdoor device.
     */
    @Test
    void whenOutDoorDeviceIsNull_ThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(null, deviceIdInt, initialTime, finalTime, delta));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null indoor device.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_IfInDoorDeviceIsNull_ThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, null, initialTime, finalTime, delta));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to verify that IllegalArgumentException is thrown when given null time configuration.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_IfTimeConfigIsNull_ThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, null, finalTime, delta));
        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, null, delta));
        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, finalTime, null));
        String result = exception.getMessage();
        String result2 = exception.getMessage();
        String result3 = exception.getMessage();

        // Assert
        assertEquals(expected,result);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null indoor device.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifInDoorDeviceIsNull_ThenThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, null, initialTime, finalTime, delta));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when the outdoor device is indoor.
     * The test mocks the device repository and room repository to return a room with a height of 1.0, which indicates
     * that the device is indoor.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_IfOutDoorDeviceIsIndoor_ThenThrowIllegalArgumentException() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(1.0);

        String expectedMessage = "Invalid Device Location";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, finalTime, delta));

        String result = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when the indoor device is outdoor.
     * The test mocks the device repository and room repository to return a room with a height of 0.0, which indicates
     * that the device is outdoor.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_IfInDoorDeviceIsOutdoor_ThenThrowIllegalArgumentException() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(0.0);

        Device deviceIn = mock(Device.class);
        when(deviceRepository.findById(deviceIdInt)).thenReturn(deviceIn);

        RoomIDVO roomIdIn = mock(RoomIDVO.class);
        when(deviceIn.getRoomID()).thenReturn(roomIdIn);

        Room roomIn = mock(Room.class);
        when(roomRepository.findById(roomIdIn)).thenReturn(roomIn);

        RoomDimensionsVO roomDimensionsIn = mock(RoomDimensionsVO.class);
        when(roomIn.getRoomDimensions()).thenReturn(roomDimensionsIn);
        when(roomIn.getRoomDimensions().getRoomHeight()).thenReturn(0.0);


        String expectedMessage = "Invalid Device Location";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, finalTime, delta));

        String result = exception.getMessage();

        // Assert
        assertEquals(expectedMessage, result);
    }


    /**
     * Test to verify that returns an error message when there are no records available for the given period in
     * the outdoor device's log.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifOutDoorDeviceLogEmpty_ThenReturnsStringMessage() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(0.0);

        Device deviceIn = mock(Device.class);
        when(deviceRepository.findById(deviceIdInt)).thenReturn(deviceIn);

        RoomIDVO roomIdIn = mock(RoomIDVO.class);
        when(deviceIn.getRoomID()).thenReturn(roomIdIn);

        Room roomIn = mock(Room.class);
        when(roomRepository.findById(roomIdIn)).thenReturn(roomIn);

        RoomDimensionsVO roomDimensionsIn = mock(RoomDimensionsVO.class);
        when(roomIn.getRoomDimensions()).thenReturn(roomDimensionsIn);
        when(roomIn.getRoomDimensions().getRoomHeight()).thenReturn(1.0);

        LocalDateTime initialDateTime = mock(LocalDateTime.class);
        when(initialTime.getValue()).thenReturn(initialDateTime);

        LocalDateTime finalDateTime = mock(LocalDateTime.class);
        when(finalTime.getValue()).thenReturn(finalDateTime);

        int deltaMin=5;
        when(delta.getValue()).thenReturn(deltaMin);

        String sensorType = "TemperatureSensor";

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialTime, finalTime)).thenReturn(Collections.emptyList());

        String expectedMessage = "There are no records available for the given period";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, finalTime, delta);

        // Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test to verify that returns an error message when there are no records available for the given period in
     * the indoor device's log.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifInDoorDeviceLogEmpty_ThenReturnsStringMessage() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(0.0);

        Device deviceIn = mock(Device.class);
        when(deviceRepository.findById(deviceIdInt)).thenReturn(deviceIn);

        RoomIDVO roomIdIn = mock(RoomIDVO.class);
        when(deviceIn.getRoomID()).thenReturn(roomIdIn);

        Room roomIn = mock(Room.class);
        when(roomRepository.findById(roomIdIn)).thenReturn(roomIn);

        RoomDimensionsVO roomDimensionsIn = mock(RoomDimensionsVO.class);
        when(roomIn.getRoomDimensions()).thenReturn(roomDimensionsIn);
        when(roomIn.getRoomDimensions().getRoomHeight()).thenReturn(1.0);

        LocalDateTime initialDateTime = mock(LocalDateTime.class);
        when(initialTime.getValue()).thenReturn(initialDateTime);

        LocalDateTime finalDateTime = mock(LocalDateTime.class);
        when(finalTime.getValue()).thenReturn(finalDateTime);

        int deltaMin=5;
        when(delta.getValue()).thenReturn(deltaMin);

        String sensorType = "TemperatureSensor";

        Log log1 = mock(Log.class);
        Log log2 = mock(Log.class);

        logRepository.save(log1);
        logRepository.save(log2);

        List<Log> outdoorLogs = Arrays.asList(log1, log2);

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialTime, finalTime)).thenReturn(outdoorLogs);

        when(logRepository.getDeviceTemperatureLogs(deviceIdInt, sensorType, initialTime, finalTime)).thenReturn(Collections.emptyList());

        String expectedMessage = "There are no records available for the given period";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialTime, finalTime, delta);

        // Assert
        assertEquals(expectedMessage, result);
    }


    /**
     * Test to verify that returns an error message when there are no records available for the given period.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifLogEntriesAreOutSideTheTimeWindowProvided_ThenReturnsStringMessage() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(0.0);

        Device deviceIn = mock(Device.class);
        when(deviceRepository.findById(deviceIdInt)).thenReturn(deviceIn);

        RoomIDVO roomIdIn = mock(RoomIDVO.class);
        when(deviceIn.getRoomID()).thenReturn(roomIdIn);

        Room roomIn = mock(Room.class);
        when(roomRepository.findById(roomIdIn)).thenReturn(roomIn);

        RoomDimensionsVO roomDimensionsIn = mock(RoomDimensionsVO.class);
        when(roomIn.getRoomDimensions()).thenReturn(roomDimensionsIn);
        when(roomIn.getRoomDimensions().getRoomHeight()).thenReturn(1.0);

        LocalDateTime t1 = LocalDateTime.parse("2024-02-25T21:50:00");
        TimeStampVO time1 = mock(TimeStampVO.class);
        when(time1.getValue()).thenReturn(t1);

        LocalDateTime t2 = LocalDateTime.parse("2024-02-25T22:50:00");
        TimeStampVO time2 = mock(TimeStampVO.class);
        when(time2.getValue()).thenReturn(t2);

        int deltaMin=5;
        when(delta.getValue()).thenReturn(deltaMin);

        String sensorType = "TemperatureSensor";

        Log log1 = mock(Log.class);
        when(log1.getTime()).thenReturn(time1);
        Log log2 = mock(Log.class);
        when(log2.getTime()).thenReturn(time2);

        logRepository.save(log1);
        logRepository.save(log2);

        List<Log> outdoorLogs = Arrays.asList(log1, log2);

        LocalDateTime t3 = LocalDateTime.parse("2024-02-25T22:00:00");
        TimeStampVO time3 = mock(TimeStampVO.class);
        when(time3.getValue()).thenReturn(t3);

        LocalDateTime t4 = LocalDateTime.parse("2024-02-25T23:00:00");
        TimeStampVO time4 = mock(TimeStampVO.class);
        when(time4.getValue()).thenReturn(t4);

        Log log3 = mock(Log.class);
        when(log3.getTime()).thenReturn(time3);
        Log log4 = mock(Log.class);
        when(log4.getTime()).thenReturn(time4);

        logRepository.save(log3);
        logRepository.save(log4);

        List<Log> indoorLogs = Arrays.asList(log3, log4);

        LocalDateTime userInitTime = LocalDateTime.parse("2024-01-25T20:45:00");
        TimeStampVO userInitialTime = mock(TimeStampVO.class);
        when(userInitialTime.getValue()).thenReturn(userInitTime);

        LocalDateTime userFinTime = LocalDateTime.parse("2024-05-01T23:45:00");
        TimeStampVO userFinalTime = mock(TimeStampVO.class);
        when(userFinalTime.getValue()).thenReturn(userFinTime);

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, userInitialTime, userFinalTime)).thenReturn(outdoorLogs);

        when(logRepository.getDeviceTemperatureLogs(deviceIdInt, sensorType, userInitialTime, userFinalTime)).thenReturn(indoorLogs);


        String expectedMessage = "Readings were found within the provided time span, but with no matches within the delta provided";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, userInitialTime, userFinalTime, delta);

        // Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Success test case for getMaxInstantaneousTempDifference method. Tests that when all there are logs available
     * within the time frame provided, and the delta allows for a match, the method returns the correct message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifLogEntriesAreInSideTheTimeWindowProvided_ThenReturnsStringMessage() {
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeStampVO initialTime = mock(TimeStampVO.class);
        TimeStampVO finalTime = mock(TimeStampVO.class);
        DeltaVO delta = mock(DeltaVO.class);

        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);

        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        Device deviceOut = mock(Device.class);
        when(deviceRepository.findById(deviceIdOut)).thenReturn(deviceOut);

        RoomIDVO roomIdOut = mock(RoomIDVO.class);
        when(deviceOut.getRoomID()).thenReturn(roomIdOut);

        Room roomOut = mock(Room.class);
        when(roomRepository.findById(roomIdOut)).thenReturn(roomOut);

        RoomDimensionsVO roomDimensionsOut = mock(RoomDimensionsVO.class);
        when(roomOut.getRoomDimensions()).thenReturn(roomDimensionsOut);
        when(roomOut.getRoomDimensions().getRoomHeight()).thenReturn(0.0);

        Device deviceIn = mock(Device.class);
        when(deviceRepository.findById(deviceIdInt)).thenReturn(deviceIn);

        RoomIDVO roomIdIn = mock(RoomIDVO.class);
        when(deviceIn.getRoomID()).thenReturn(roomIdIn);

        Room roomIn = mock(Room.class);
        when(roomRepository.findById(roomIdIn)).thenReturn(roomIn);

        RoomDimensionsVO roomDimensionsIn = mock(RoomDimensionsVO.class);
        when(roomIn.getRoomDimensions()).thenReturn(roomDimensionsIn);
        when(roomIn.getRoomDimensions().getRoomHeight()).thenReturn(1.0);

        TimeStampVO initialDateTime = mock(TimeStampVO.class);

        LocalDateTime initialT = LocalDateTime.parse("2024-01-25T23:50:00");

        when(initialDateTime.getValue()).thenReturn(initialT);


        TimeStampVO finalDateTime = mock(TimeStampVO.class);

        LocalDateTime finalT = LocalDateTime.parse("2024-04-25T23:50:00");

        when(finalDateTime.getValue()).thenReturn(finalT);


        int deltaMin=6;
        when(delta.getValue()).thenReturn(deltaMin);

        String sensorType = "TemperatureSensor";

        Log log1 = mock(Log.class);
        TimeStampVO time1 = mock(TimeStampVO.class);
        when(log1.getTime()).thenReturn(time1);
        when(log1.getTime().getValue()).thenReturn(LocalDateTime.parse("2024-02-25T23:50:00"));
        SensorValueObject s3Value = mock(TemperatureValue.class);
        when(s3Value.getValue()).thenReturn(5.0);
        when(log1.getReading()).thenReturn(s3Value);

        Log log2 = mock(Log.class);
        TimeStampVO time2 = mock(TimeStampVO.class);
        when(log2.getTime()).thenReturn(time2);
        when(log2.getTime().getValue()).thenReturn(LocalDateTime.parse("2024-02-25T23:50:00"));
        SensorValueObject s4Value = mock(TemperatureValue.class);
        when(s4Value.getValue()).thenReturn(20.0);
        when(log2.getReading()).thenReturn(s4Value);

        logRepository.save(log1);
        logRepository.save(log2);

        List<Log> outdoorLogs = Arrays.asList(log1, log2);


        Log log3 = mock(Log.class);
        SensorValueObject sValue = mock(TemperatureValue.class);
        TimeStampVO time3 = mock(TimeStampVO.class);
        when(log3.getTime()).thenReturn(time3);
        when(log3.getTime().getValue()).thenReturn(LocalDateTime.parse("2024-02-25T23:55:00"));
        when(s4Value.getValue()).thenReturn(0.0);
        when(log3.getReading()).thenReturn(sValue);

        Log log4 = mock(Log.class);
        TimeStampVO time4 = mock(TimeStampVO.class);
        when(log4.getTime()).thenReturn(time4);
        when(log4.getTime().getValue()).thenReturn(LocalDateTime.parse("2024-02-25T23:45:00"));
        SensorValueObject s2Value = mock(TemperatureValue.class);
        when(s4Value.getValue()).thenReturn(10.0);
        when(log4.getReading()).thenReturn(s2Value);

        logRepository.save(log3);
        logRepository.save(log4);

        List<Log> indoorLogs = Arrays.asList(log3, log4);

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialDateTime, finalDateTime)).thenReturn(outdoorLogs);

        when(logRepository.getDeviceTemperatureLogs(deviceIdInt, sensorType, initialDateTime, finalDateTime)).thenReturn(indoorLogs);

        String expectedMessage = "The Maximum Temperature Difference within the selected Period was of 10.0 Cº which happened at 2024-02-25T23:55";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, initialDateTime, finalDateTime, delta);

        // Assert
        assertEquals(expectedMessage, result);
    }
}
