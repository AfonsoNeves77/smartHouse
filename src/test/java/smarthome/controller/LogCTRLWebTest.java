package smarthome.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.sensorvalues.SensorValueObject;
import smarthome.domain.sensor.sensorvalues.TemperatureValue;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.housevo.HouseIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.logvo.TimeStampVO;
import smarthome.domain.vo.roomvo.*;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.mapper.dto.LogDTO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for WebLogController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class LogCTRLWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LogRepository logRepository;

    @MockBean
    private DeviceRepository deviceRepository;

    @MockBean
    private RoomRepository roomRepository;


    /**
     * Tests the {@code findReadingsInAPeriod} method with valid parameters.
     * <p>
     * This test verifies that when valid parameters are provided, the method successfully returns
     * a collection of log DTOs.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenValidParams_findReadingsInAPeriodSuccessfullyReturnsCollectionOfLogsDTO() throws Exception {
        //Arrange

        // Create two logs
        SensorIDVO sensorID1 = new SensorIDVO(UUID.randomUUID());
        SensorIDVO sensorID2 = new SensorIDVO(UUID.randomUUID());
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");
        LogIDVO logID1 = new LogIDVO(UUID.randomUUID());
        LogIDVO logID2 = new LogIDVO(UUID.randomUUID());
        TimeStampVO time1 = new TimeStampVO(LocalDateTime.now().minusHours(2).truncatedTo(ChronoUnit.SECONDS));
        TimeStampVO time2 = new TimeStampVO(LocalDateTime.now().minusHours(1).truncatedTo(ChronoUnit.SECONDS));
        SensorValueObject<Double> reading1 = new TemperatureValue("23");
        SensorValueObject<Double> reading2 = new TemperatureValue("25");
        Log log1 = new Log(logID1, time1, reading1, sensorID1, deviceID, sensorType);
        Log log2 = new Log(logID2, time2, reading2, sensorID2, deviceID, sensorType);

        // Create the Json body
        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusHours(3).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.findByDeviceIDAndTimeBetween(deviceID, initialSearch, finalSearch)).thenReturn(List.of(log1, log2));

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, initialTime, endDate, endTime
        );

        // Create the resulting DTOs
        LogDTO logDTO1 = LogDTO.builder()
                .logID(logID1.getID())
                .time(time1.getValue().toString())
                .reading(reading1.getValue().toString())
                .sensorID(sensorID1.getID())
                .deviceID(deviceID.getID())
                .sensorTypeID(sensorType.getID())
                .build();

        LogDTO logDTO2 = LogDTO.builder()
                .logID(logID2.getID())
                .time(time2.getValue().toString())
                .reading(reading2.getValue().toString())
                .sensorID(sensorID2.getID())
                .deviceID(deviceID.getID())
                .sensorTypeID(sensorType.getID())
                .build();



        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.logDTOList[0].logID").value(logDTO1.getLogID()))
                .andExpect(jsonPath("$._embedded.logDTOList[0].time").value(logDTO1.getTime()))
                .andExpect(jsonPath("$._embedded.logDTOList[0].reading").value(logDTO1.getReading()))
                .andExpect(jsonPath("$._embedded.logDTOList[0].sensorID").value(logDTO1.getSensorID()))
                .andExpect(jsonPath("$._embedded.logDTOList[0].deviceID").value(logDTO1.getDeviceID()))
                .andExpect(jsonPath("$._embedded.logDTOList[0].sensorTypeID").value(logDTO1.getSensorTypeID()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].logID").value(logDTO2.getLogID()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].time").value(logDTO2.getTime()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].reading").value(logDTO2.getReading()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].sensorID").value(logDTO2.getSensorID()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].deviceID").value(logDTO2.getDeviceID()))
                .andExpect(jsonPath("$._embedded.logDTOList[1].sensorTypeID").value(logDTO2.getSensorTypeID()));
    }


    /**
     * Tests the {@code findReadingsInAPeriod} method with an invalid device ID.
     * <p>
     * This test verifies that when an invalid device ID is provided, the method returns
     * a {@code BadRequest} status.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenInvalidDeviceID_findReadingsInAPeriodReturnsBadRequestStatus() throws Exception {
        //Arrange

        // Create the Json body
        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusHours(3).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, initialTime, endDate, endTime
        );

        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", "I AM INVALID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isBadRequest());
    }


    /**
     * Tests the {@code findReadingsInAPeriod} method with invalid timestamps.
     * <p>
     * This test verifies that when invalid timestamps are provided, the method returns
     * a {@code BadRequest} status.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenInvalidTimeStamps_findReadingsInAPeriodReturnsBadRequestStatus() throws Exception {
        //Arrange
        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());

        // Create the Json body
        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusHours(3).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        String initialTimeFail = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        String timeConfigJson1 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                null, initialTime, endDate, endTime
        );

        String timeConfigJson2 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, null, endDate, endTime
        );

        String timeConfigJson3 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, initialTime, null, endTime
        );

        String timeConfigJson4 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, initialTime, endDate, null
        );

        String timeConfigJson5 = String.format(
                "{\"initialTimeFail\":\"%s\",\"initialDate\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialTimeFail, initialDate, endDate, endTime
        );


        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson1))
                .andExpect(status().isBadRequest());

        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson2))
                .andExpect(status().isBadRequest());

        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson3))
                .andExpect(status().isBadRequest());

        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson4))
                .andExpect(status().isBadRequest());

        //Act & Assert
        mockMvc.perform(get("/logs")
                        .param("deviceId", deviceID.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson5))
                .andExpect(status().isBadRequest());

    }


    /**
     * Tests the {@code findReadingsInAPeriod} method when the device ID is not found.
     * <p>
     * This test verifies that when the specified device ID is not found, the method returns
     * an empty collection of logs.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void givenDeviceIdNotFound_findReadingsInAPeriodReturnsAnEmptyCollection() throws Exception {
        //Arrange

        DeviceIDVO deviceID = new DeviceIDVO(UUID.randomUUID());

        // Create the Json body
        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusHours(3).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.findByDeviceIDAndTimeBetween(deviceID, initialSearch, finalSearch)).thenReturn(new ArrayList<>());

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\"}",
                initialDate, initialTime, endDate, endTime
        );

        //Act & Assert
        mockMvc.perform(get("/logs")
                .param("deviceId", deviceID.getID())
                .contentType(MediaType.APPLICATION_JSON)
                .content(timeConfigJson))
                .andExpect(status().isOk())
                .andExpect(content().json("{}"));
    }



    /**
     * Tests the {@code getMaxTempDiff} method with correct parameters.
     * <p>
     * This test verifies that when correct parameters are provided, the method successfully returns
     * the maximum temperature difference between indoor and outdoor sensors.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void whenGivenCorrectParams_thenGetMaxTempDiffMethodReturnsSuccessMessage() throws Exception {
        // Arrange

        // Arranging two rooms and roomRepository
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomNameVO = new RoomNameVO("Balcony");
        RoomFloorVO floor = new RoomFloorVO(1);
        RoomLengthVO length = new RoomLengthVO(10);
        RoomWidthVO width = new RoomWidthVO(10);
        RoomHeightVO height = new RoomHeightVO(0);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(length, width, height);
        Room outRoom = new Room(roomNameVO, floor, roomDimensions, houseID);
        RoomIDVO outRoomIDVO = outRoom.getId();

        RoomNameVO roomNameVO2 = new RoomNameVO("BedRoom");
        RoomFloorVO floor2 = new RoomFloorVO(1);
        RoomLengthVO length2 = new RoomLengthVO(10);
        RoomWidthVO width2 = new RoomWidthVO(10);
        RoomHeightVO height2 = new RoomHeightVO(1);
        RoomDimensionsVO roomDimensions2 = new RoomDimensionsVO(length2, width2, height2);
        Room inRoom = new Room(roomNameVO2, floor2, roomDimensions2, houseID);
        RoomIDVO inRoomIDVO = inRoom.getId();

        when(roomRepository.findById(outRoomIDVO)).thenReturn(outRoom);
        when(roomRepository.findById(inRoomIDVO)).thenReturn(inRoom);

        // Setting up two devices and deviceRepository
        DeviceNameVO deviceNameVO = new DeviceNameVO("OutdoorDevice");
        DeviceModelVO deviceModelVO = new DeviceModelVO("OutdoorModel");
        Device outDevice = new Device(deviceNameVO, deviceModelVO, outRoomIDVO);
        DeviceIDVO outDeviceIDVO = outDevice.getId();

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("IndoorDevice");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("IndoorModel");
        Device inDevice = new Device(deviceNameVO2, deviceModelVO2, inRoomIDVO);
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        when(deviceRepository.findById(outDeviceIDVO)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceIDVO)).thenReturn(inDevice);

        // Create two logs, one for the outside device, another for the inside device
        // Create log repository and stub the behaviour to return iterable containing said logs
        SensorIDVO sensorID1 = new SensorIDVO(UUID.randomUUID());
        SensorIDVO sensorID2 = new SensorIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");
        LogIDVO logID1 = new LogIDVO(UUID.randomUUID());
        LogIDVO logID2 = new LogIDVO(UUID.randomUUID());
        TimeStampVO time1 = new TimeStampVO(LocalDateTime.now().minusMinutes(2).truncatedTo(ChronoUnit.SECONDS));
        TimeStampVO time2 = new TimeStampVO(LocalDateTime.now().minusMinutes(1).truncatedTo(ChronoUnit.SECONDS));
        String time2Str = time2.getValue().toString();
        SensorValueObject<Double> outReadingVO = new TemperatureValue("23");
        SensorValueObject<Double> inReadingVO = new TemperatureValue("25");

        Log log1 = new Log(logID1, time1, outReadingVO, sensorID1, outDeviceIDVO, sensorType);
        Iterable<Log> outLog = List.of(log1);
        Log log2 = new Log(logID2, time2, inReadingVO, sensorID2, inDeviceIDVO, sensorType);
        Iterable<Log> inLog = List.of(log2);

        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusMinutes(30).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String deltaMin = "5";

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(outLog);
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(inLog);

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, endDate, endTime, deltaMin
        );

        // Act & Assert
        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isOk())
                .andExpect(content().string("The Maximum Temperature Difference within the selected Period was of 2.0 Cº which happened at "+ time2Str));
    }


    /**
     * Tests the {@code getMaxTempDiff} method when no logs are found within the delta provided.
     * <p>
     * This test verifies that when no logs are found within the specified delta, the method returns
     * an appropriate message.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void whenNoLogsFoundWithinDeltaProvided_thenGetMaxTempDiffMethodReturnsAppropriateMessage() throws Exception {
        // Arrange

        // Arranging two rooms and roomRepository
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomNameVO = new RoomNameVO("Balcony");
        RoomFloorVO floor = new RoomFloorVO(1);
        RoomLengthVO length = new RoomLengthVO(10);
        RoomWidthVO width = new RoomWidthVO(10);
        RoomHeightVO height = new RoomHeightVO(0);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(length, width, height);
        Room outRoom = new Room(roomNameVO, floor, roomDimensions, houseID);
        RoomIDVO outRoomIDVO = outRoom.getId();

        RoomNameVO roomNameVO2 = new RoomNameVO("BedRoom");
        RoomFloorVO floor2 = new RoomFloorVO(1);
        RoomLengthVO length2 = new RoomLengthVO(10);
        RoomWidthVO width2 = new RoomWidthVO(10);
        RoomHeightVO height2 = new RoomHeightVO(1);
        RoomDimensionsVO roomDimensions2 = new RoomDimensionsVO(length2, width2, height2);
        Room inRoom = new Room(roomNameVO2, floor2, roomDimensions2, houseID);
        RoomIDVO inRoomIDVO = inRoom.getId();

        when(roomRepository.findById(outRoomIDVO)).thenReturn(outRoom);
        when(roomRepository.findById(inRoomIDVO)).thenReturn(inRoom);

        // Setting up two devices and deviceRepository
        DeviceNameVO deviceNameVO = new DeviceNameVO("OutdoorDevice");
        DeviceModelVO deviceModelVO = new DeviceModelVO("OutdoorModel");
        Device outDevice = new Device(deviceNameVO, deviceModelVO, outRoomIDVO);
        DeviceIDVO outDeviceIDVO = outDevice.getId();

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("IndoorDevice");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("IndoorModel");
        Device inDevice = new Device(deviceNameVO2, deviceModelVO2, inRoomIDVO);
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        when(deviceRepository.findById(outDeviceIDVO)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceIDVO)).thenReturn(inDevice);

        // Create two logs, one for the outside device, another for the inside device
        // Create log repository and stub the behaviour to return iterable containing said logs
        SensorIDVO sensorID1 = new SensorIDVO(UUID.randomUUID());
        SensorIDVO sensorID2 = new SensorIDVO(UUID.randomUUID());
        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");
        LogIDVO logID1 = new LogIDVO(UUID.randomUUID());
        LogIDVO logID2 = new LogIDVO(UUID.randomUUID());
        TimeStampVO time1 = new TimeStampVO(LocalDateTime.now().minusMinutes(5).truncatedTo(ChronoUnit.SECONDS));
        TimeStampVO time2 = new TimeStampVO(LocalDateTime.now().minusMinutes(1).truncatedTo(ChronoUnit.SECONDS));
        SensorValueObject<Double> outReadingVO = new TemperatureValue("23");
        SensorValueObject<Double> inReadingVO = new TemperatureValue("25");

        Log log1 = new Log(logID1, time1, outReadingVO, sensorID1, outDeviceIDVO, sensorType);
        Iterable<Log> outLog = List.of(log1);
        Log log2 = new Log(logID2, time2, inReadingVO, sensorID2, inDeviceIDVO, sensorType);
        Iterable<Log> inLog = List.of(log2);

        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusMinutes(30).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String deltaMin = "1";

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(outLog);
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(inLog);

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, endDate, endTime, deltaMin
        );

        // Act & Assert
        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isOk())
                .andExpect(content().string("Readings were found within the provided time span, but with no matches within the delta provided"));
    }


    /**
     * Tests the {@code getMaxTempDiff} method when no logs are present.
     * <p>
     * This test verifies that when no logs are present, the method returns
     * an appropriate message.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void whenNoLogsPresent_thenGetMaxTempDiffMethodReturnsAppropriateMessage() throws Exception {
        // Arrange

        // Arranging two rooms and roomRepository
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomNameVO = new RoomNameVO("Balcony");
        RoomFloorVO floor = new RoomFloorVO(1);
        RoomLengthVO length = new RoomLengthVO(10);
        RoomWidthVO width = new RoomWidthVO(10);
        RoomHeightVO height = new RoomHeightVO(0);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(length, width, height);
        Room outRoom = new Room(roomNameVO, floor, roomDimensions, houseID);
        RoomIDVO outRoomIDVO = outRoom.getId();

        RoomNameVO roomNameVO2 = new RoomNameVO("BedRoom");
        RoomFloorVO floor2 = new RoomFloorVO(1);
        RoomLengthVO length2 = new RoomLengthVO(10);
        RoomWidthVO width2 = new RoomWidthVO(10);
        RoomHeightVO height2 = new RoomHeightVO(1);
        RoomDimensionsVO roomDimensions2 = new RoomDimensionsVO(length2, width2, height2);
        Room inRoom = new Room(roomNameVO2, floor2, roomDimensions2, houseID);
        RoomIDVO inRoomIDVO = inRoom.getId();

        when(roomRepository.findById(outRoomIDVO)).thenReturn(outRoom);
        when(roomRepository.findById(inRoomIDVO)).thenReturn(inRoom);

        // Setting up two devices and deviceRepository
        DeviceNameVO deviceNameVO = new DeviceNameVO("OutdoorDevice");
        DeviceModelVO deviceModelVO = new DeviceModelVO("OutdoorModel");
        Device outDevice = new Device(deviceNameVO, deviceModelVO, outRoomIDVO);
        DeviceIDVO outDeviceIDVO = outDevice.getId();

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("IndoorDevice");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("IndoorModel");
        Device inDevice = new Device(deviceNameVO2, deviceModelVO2, inRoomIDVO);
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        when(deviceRepository.findById(outDeviceIDVO)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceIDVO)).thenReturn(inDevice);

        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusMinutes(30).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String deltaMin = "5";

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(new ArrayList<>());
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(new ArrayList<>());

        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, endDate, endTime, deltaMin
        );

        // Act & Assert
        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isOk())
                .andExpect(content().string("There are no records available for the given period"));
    }


    /**
     * Tests the {@code getMaxTempDiff} method with invalid device IDs.
     * <p>
     * This test verifies that when invalid device IDs are provided, the method returns
     * a {@code BadRequest} status. It arranges two rooms and devices, constructs an
     * invalid request JSON body, and performs a GET request to the "/logs" endpoint with
     * the invalid device IDs.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void whenGivenInvalidDeviceIDs_ReturnsBadRequestStatus() throws Exception {
        // Arrange

        // Arranging two rooms and roomRepository
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomNameVO = new RoomNameVO("Balcony");
        RoomFloorVO floor = new RoomFloorVO(1);
        RoomLengthVO length = new RoomLengthVO(10);
        RoomWidthVO width = new RoomWidthVO(10);
        RoomHeightVO height = new RoomHeightVO(0);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(length, width, height);
        Room outRoom = new Room(roomNameVO, floor, roomDimensions, houseID);
        RoomIDVO outRoomIDVO = outRoom.getId();

        RoomNameVO roomNameVO2 = new RoomNameVO("BedRoom");
        RoomFloorVO floor2 = new RoomFloorVO(1);
        RoomLengthVO length2 = new RoomLengthVO(10);
        RoomWidthVO width2 = new RoomWidthVO(10);
        RoomHeightVO height2 = new RoomHeightVO(1);
        RoomDimensionsVO roomDimensions2 = new RoomDimensionsVO(length2, width2, height2);
        Room inRoom = new Room(roomNameVO2, floor2, roomDimensions2, houseID);
        RoomIDVO inRoomIDVO = inRoom.getId();

        // Setting up two devices and deviceRepository
        DeviceNameVO deviceNameVO = new DeviceNameVO("OutdoorDevice");
        DeviceModelVO deviceModelVO = new DeviceModelVO("OutdoorModel");
        Device outDevice = new Device(deviceNameVO, deviceModelVO, outRoomIDVO);
        DeviceIDVO outDeviceIDVO = outDevice.getId();

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("IndoorDevice");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("IndoorModel");
        Device inDevice = new Device(deviceNameVO2, deviceModelVO2, inRoomIDVO);
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusMinutes(30).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String deltaMin = "5";


        String timeConfigJson = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, endDate, endTime, deltaMin
        );

        // Act & Assert
        mockMvc.perform(get("/logs")
                        .param("outdoorId", "I AM INVALID")
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", "I AM INVALID")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson))
                .andExpect(status().isBadRequest());
    }


    /**
     * Tests the {@code getMaxTempDiff} method with invalid timestamps.
     * <p>
     * This test verifies that when invalid timestamps are provided, the method returns
     * a {@code BadRequest} status. It arranges two rooms and devices, constructs various
     * invalid request JSON bodies with missing or null timestamp values, and performs
     * GET requests to the "/logs" endpoint with the invalid timestamps.
     * </p>
     *
     * @throws Exception if an error occurs during the test
     */
    @Test
    void whenGivenInvalidTimeStamps_ReturnsBadRequestStatus() throws Exception {
        // Arrange

        // Arranging two rooms and roomRepository
        HouseIDVO houseID = new HouseIDVO(UUID.randomUUID());

        RoomNameVO roomNameVO = new RoomNameVO("Balcony");
        RoomFloorVO floor = new RoomFloorVO(1);
        RoomLengthVO length = new RoomLengthVO(10);
        RoomWidthVO width = new RoomWidthVO(10);
        RoomHeightVO height = new RoomHeightVO(0);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(length, width, height);
        Room outRoom = new Room(roomNameVO, floor, roomDimensions, houseID);
        RoomIDVO outRoomIDVO = outRoom.getId();

        RoomNameVO roomNameVO2 = new RoomNameVO("BedRoom");
        RoomFloorVO floor2 = new RoomFloorVO(1);
        RoomLengthVO length2 = new RoomLengthVO(10);
        RoomWidthVO width2 = new RoomWidthVO(10);
        RoomHeightVO height2 = new RoomHeightVO(1);
        RoomDimensionsVO roomDimensions2 = new RoomDimensionsVO(length2, width2, height2);
        Room inRoom = new Room(roomNameVO2, floor2, roomDimensions2, houseID);
        RoomIDVO inRoomIDVO = inRoom.getId();

        when(roomRepository.findById(outRoomIDVO)).thenReturn(outRoom);
        when(roomRepository.findById(inRoomIDVO)).thenReturn(inRoom);

        // Setting up two devices and deviceRepository
        DeviceNameVO deviceNameVO = new DeviceNameVO("OutdoorDevice");
        DeviceModelVO deviceModelVO = new DeviceModelVO("OutdoorModel");
        Device outDevice = new Device(deviceNameVO, deviceModelVO, outRoomIDVO);
        DeviceIDVO outDeviceIDVO = outDevice.getId();

        DeviceNameVO deviceNameVO2 = new DeviceNameVO("IndoorDevice");
        DeviceModelVO deviceModelVO2 = new DeviceModelVO("IndoorModel");
        Device inDevice = new Device(deviceNameVO2, deviceModelVO2, inRoomIDVO);
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        when(deviceRepository.findById(outDeviceIDVO)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceIDVO)).thenReturn(inDevice);

        String initialDate = LocalDate.now().toString();
        String initialTime = LocalTime.now().minusMinutes(30).truncatedTo(ChronoUnit.SECONDS).toString();
        String endDate = LocalDate.now().toString();
        String endTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).toString();
        String deltaMin = "5";

        TimeStampVO initialSearch = new TimeStampVO(initialDate, initialTime);
        TimeStampVO finalSearch = new TimeStampVO(endDate, endTime);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(new ArrayList<>());
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialSearch, finalSearch))
                .thenReturn(new ArrayList<>());

        String timeConfigJson1 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                null, initialTime, endDate, endTime, deltaMin
        );

        String timeConfigJson2 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, null, endDate, endTime, deltaMin
        );

        String timeConfigJson3 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, null, endTime, deltaMin
        );

        String timeConfigJson4 = String.format(
                "{\"initialDate\":\"%s\",\"initialTime\":\"%s\",\"endDate\":\"%s\",\"endTime\":\"%s\",\"deltaMin\":%s}",
                initialDate, initialTime, endDate, null, deltaMin
        );

        // Act & Assert
        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson1))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson2))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson3))
                .andExpect(status().isBadRequest());

        mockMvc.perform(get("/logs")
                        .param("outdoorId", outDeviceIDVO.getID())
                        .param("indoorId", inDeviceIDVO.getID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(timeConfigJson4))
                .andExpect(status().isBadRequest());

    }


}