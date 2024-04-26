package smarthome.service;

import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.room.Room;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.roomvo.RoomDimensionsVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;
import smarthome.utils.timeconfig.TimeConfig;

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
        TimeConfig timeConfig = mock(TimeConfig.class);
        LogServiceImpl service = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,timeConfig));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(deviceID,null));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,null));
        String result3 = exception3.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null outdoor device.
     */
    @Test
    void whenOutDoorDeviceIsNull_ThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdInt = mock(DeviceIDVO.class);
        TimeConfig timeConfig = mock(TimeConfig.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(null, deviceIdInt, timeConfig));
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
        TimeConfig timeConfig = mock(TimeConfig.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, null, timeConfig));
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
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null indoor device.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifInDoorDeviceIsNull_ThenThrowIllegalArgumentException(){
        // Arrange
        DeviceIDVO deviceIdOut = mock(DeviceIDVO.class);
        TimeConfig timeConfig = mock(TimeConfig.class);
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogServiceImpl logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);
        String expected = "Invalid Parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                logService.getMaxInstantaneousTempDifference(deviceIdOut, null, timeConfig));
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
        TimeConfig timeConfig = mock(TimeConfig.class);

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
            logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, timeConfig));

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
        TimeConfig timeConfig = mock(TimeConfig.class);

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
            logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, timeConfig));

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
        TimeConfig timeConfig = mock(TimeConfig.class);

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
        when(timeConfig.getInitialTimeStamp()).thenReturn(initialDateTime);

        LocalDateTime finalDateTime = mock(LocalDateTime.class);
        when(timeConfig.getEndTimeStamp()).thenReturn(finalDateTime);

        int delta=5;
        when(timeConfig.getDeltaMin()).thenReturn(delta);

        String sensorType = "TemperatureSensor";

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialDateTime, finalDateTime)).thenReturn(Collections.emptyList());

        String expectedMessage = "There are no records available for the given period";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, timeConfig);

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
        TimeConfig timeConfig = mock(TimeConfig.class);

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
        when(timeConfig.getInitialTimeStamp()).thenReturn(initialDateTime);

        LocalDateTime finalDateTime = mock(LocalDateTime.class);
        when(timeConfig.getEndTimeStamp()).thenReturn(finalDateTime);

        int delta=5;
        when(timeConfig.getDeltaMin()).thenReturn(delta);

        String sensorType = "TemperatureSensor";

        Log log1 = mock(Log.class);
        Log log2 = mock(Log.class);

        logRepository.save(log1);
        logRepository.save(log2);

        List<Log> outdoorLogs = Arrays.asList(log1, log2);

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialDateTime, finalDateTime)).thenReturn(outdoorLogs);

        when(logRepository.getDeviceTemperatureLogs(deviceIdInt, sensorType, initialDateTime, finalDateTime)).thenReturn(Collections.emptyList());

        String expectedMessage = "There are no records available for the given period";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, timeConfig);

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
        TimeConfig timeConfig = mock(TimeConfig.class);

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
        when(timeConfig.getInitialTimeStamp()).thenReturn(initialDateTime);

        LocalDateTime finalDateTime = mock(LocalDateTime.class);
        when(timeConfig.getEndTimeStamp()).thenReturn(finalDateTime);

        int delta=5;
        when(timeConfig.getDeltaMin()).thenReturn(delta);

        String sensorType = "TemperatureSensor";

        Log log1 = mock(Log.class);
        when(log1.getTime()).thenReturn(LocalDateTime.parse("2024-02-25T23:50:00"));
        Log log2 = mock(Log.class);
        when(log2.getTime()).thenReturn(LocalDateTime.parse("2024-02-25T23:50:00"));

        logRepository.save(log1);
        logRepository.save(log2);

        List<Log> outdoorLogs = Arrays.asList(log1, log2);

        Log log3 = mock(Log.class);
        when(log3.getTime()).thenReturn(LocalDateTime.parse("2024-02-25T23:55:00"));
        Log log4 = mock(Log.class);
        when(log4.getTime()).thenReturn(LocalDateTime.parse("2024-02-25T23:45:00"));

        logRepository.save(log3);
        logRepository.save(log4);

        List<Log> indoorLogs = Arrays.asList(log3, log4);

        when(logRepository.getDeviceTemperatureLogs(deviceIdOut, sensorType, initialDateTime, finalDateTime)).thenReturn(outdoorLogs);

        when(logRepository.getDeviceTemperatureLogs(deviceIdInt, sensorType, initialDateTime, finalDateTime)).thenReturn(indoorLogs);

        String expectedMessage = "Readings were found within the provided time span, but with no matches within the delta provided";

        // Act
        String result = logService.getMaxInstantaneousTempDifference(deviceIdOut, deviceIdInt, timeConfig);

        // Assert
        assertEquals(expectedMessage, result);
    }
}
