package smarthome.controller;

import org.junit.jupiter.api.Test;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.sensorvalues.TemperatureValue;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.housevo.HouseIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.roomvo.*;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.mapper.dto.DeviceDTO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;
import smarthome.service.LogService;
import smarthome.service.LogServiceImpl;
import smarthome.utils.timeconfig.TimeConfigDTO;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GetMaxInstantaneousTemperatureDifferenceCTRLTest {


    /**
     * Test for the constructor of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the service is null it throws an IllegalArgumentException.
     */
    @Test
    void whenConstructorGetsNullService_throwsInstantiationException() {
        //Arrange
        String expected = "Invalid service";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new GetMaxInstantaneousTemperatureDifferenceCTRL(null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test for the constructor of GetMaxInstantaneousTemperatureDifferenceCTRL
     * when the service is valid and the controller is successfully instantiated.
     */
    @Test
    void whenConstructorGetsValidParam_thenControllerIsInstantiated() {
        //Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        // Act
        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        // Assert
        assertNotNull(ctrl);
    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the outdoor device DTO is null it returns an error message.
     */
    @Test
    void whenOutdoorDeviceDTOIsNull_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        String deviceID2 = UUID.randomUUID().toString();
        String deviceName2 = "TempDevice2";
        String deviceModel2 = "XP";
        String deviceStatus2 = "On";
        String roomID2 = UUID.randomUUID().toString();

        DeviceDTO deviceInDTO = new DeviceDTO(deviceID2, deviceName2, deviceModel2, deviceStatus2, roomID2);

        String startDate = "2024-04-25";
        String endDate = "2024-04-25";
        String startTime = "20:00:00";
        String endTime = "22:00:00";
        String delta = "5";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, endDate, startTime, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "DeviceDTO cannot be null.";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(null, deviceInDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the indoor device DTO is null it returns an error message.
     */
    @Test
    void whenIndoorDeviceDTOIsNull_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        String deviceID2 = UUID.randomUUID().toString();
        String deviceName2 = "TempDevice2";
        String deviceModel2 = "XP";
        String deviceStatus2 = "On";
        String roomID2 = UUID.randomUUID().toString();

        DeviceDTO deviceOutDTO = new DeviceDTO(deviceID2, deviceName2, deviceModel2, deviceStatus2, roomID2);

        String startDate = "2024-04-25";
        String endDate = "2024-04-25";
        String startTime = "20:00:00";
        String endTime = "22:00:00";
        String delta = "5";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, endDate, startTime, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "DeviceDTO cannot be null.";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, null, timeConfigDTO);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the time configuration DTO is null it returns an error message.
     */
    @Test
    void whenTimeConfigDTOIsNull_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        String deviceID1 = UUID.randomUUID().toString();
        String deviceName1 = "TempDevice1";
        String deviceModel1 = "XPTO";
        String deviceStatus1 = "On";
        String roomID1 = UUID.randomUUID().toString();

        String deviceID2 = UUID.randomUUID().toString();
        String deviceName2 = "TempDevice2";
        String deviceModel2 = "XP";
        String deviceStatus2 = "On";
        String roomID2 = UUID.randomUUID().toString();

        DeviceDTO deviceInDTO = new DeviceDTO(deviceID1, deviceName1, deviceModel1, deviceStatus1, roomID1);
        DeviceDTO deviceOutDTO = new DeviceDTO(deviceID2, deviceName2, deviceModel2, deviceStatus2, roomID2);


        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "Invalid TimeConfigDTO";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, null);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the time configuration DTO has null parameters it returns an error message.
     */
    @Test
    void whenGivenNullParamsToTimeConfigDTO_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        String deviceID1 = UUID.randomUUID().toString();
        String deviceName1 = "TempDevice1";
        String deviceModel1 = "XPTO";
        String deviceStatus1 = "On";
        String roomID1 = UUID.randomUUID().toString();

        String deviceID2 = UUID.randomUUID().toString();
        String deviceName2 = "TempDevice2";
        String deviceModel2 = "XP";
        String deviceStatus2 = "On";
        String roomID2 = UUID.randomUUID().toString();

        DeviceDTO deviceInDTO = new DeviceDTO(deviceID1, deviceName1, deviceModel1, deviceStatus1, roomID1);
        DeviceDTO deviceOutDTO = new DeviceDTO(deviceID2, deviceName2, deviceModel2, deviceStatus2, roomID2);

        String startDate = "2024-04-24";
        String endDate = "2024-04-25";
        String startTime = "20:00:00";
        String endTime = "22:00:00";
        String delta = "5";

        TimeConfigDTO timeConfigDTO1 = new TimeConfigDTO(null, endDate, startTime, endTime, delta);
        TimeConfigDTO timeConfigDTO2 = new TimeConfigDTO(startDate, null, startTime, endTime, delta);
        TimeConfigDTO timeConfigDTO3 = new TimeConfigDTO(startDate, endDate, null, endTime, delta);
        TimeConfigDTO timeConfigDTO4 = new TimeConfigDTO(startDate, endDate, startTime, null, delta);
        TimeConfigDTO timeConfigDTO5 = new TimeConfigDTO(startDate, endDate, startTime, endTime, null);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "Invalid timestamps";

        // Act
        String result1 = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, timeConfigDTO1);
        String result2 = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, timeConfigDTO2);
        String result3 = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, timeConfigDTO3);
        String result4 = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, timeConfigDTO4);
        String result5 = ctrl.getMaxInstantaneousTemperature(deviceOutDTO, deviceInDTO, timeConfigDTO5);

        // Assert
        assertEquals(expected, result1);
        assertEquals(expected, result2);
        assertEquals(expected, result3);
        assertEquals(expected, result4);
        assertEquals(expected, result5);
    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the outside device is inside the house (=has height greater than zero) it returns an error message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_IfOutsideDeviceIsInside_ReturnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(2.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1, roomWidth1, roomHeight1);
        Room room1 = new Room(roomName1, roomFloor1, dimensions1, houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(1.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);
        Room room2 = new Room(roomName2, roomFloor2, dimensions2, houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device(deviceName1, deviceModel1, roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device(deviceName2, deviceModel2, roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-04-24";
        String endDate = "2024-04-24";
        String startTime = "20:00";
        String endTime = "22:00";
        String delta = "5";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "Invalid Device Location";

        // Act
        String result1 = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result1);

    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the inside device is outside the house (=has height equal to zero) it returns an error message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifInsideDeviceIsOutside_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1, roomWidth1, roomHeight1);
        Room room1 = new Room(roomName1, roomFloor1, dimensions1, houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);
        Room room2 = new Room(roomName2, roomFloor2, dimensions2, houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device(deviceName1, deviceModel1, roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device(deviceName2, deviceModel2, roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-04-24";
        String endDate = "2024-04-24";
        String startTime = "20:00";
        String endTime = "22:00";
        String delta = "5";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "Invalid Device Location";

        // Act
        String result1 = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result1);

    }


    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the outside device's logs are not found within the time period given it returns an error message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifNoOutsideLogsAreFoundWithinTimePeriodGiven_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1, roomWidth1, roomHeight1);
        Room room1 = new Room(roomName1, roomFloor1, dimensions1, houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(1.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);
        Room room2 = new Room(roomName2, roomFloor2, dimensions2, houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device(deviceName1, deviceModel1, roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device(deviceName2, deviceModel2, roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-04-24";
        String endDate = "2024-04-25";
        String startTime = "22:00";
        String endTime = "04:00";
        String delta = "3";

        DeviceIDVO outDeviceIDVO = outDevice.getId();

        LocalDateTime initialDateTime = LocalDateTime.parse(startDate+"T"+startTime);
        LocalDateTime finalDateTime = LocalDateTime.parse(endDate+"T"+endTime);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(Collections.emptyList());

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "There are no records available for the given period";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result);

    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When the inside device's logs are not found within the time period given it returns an error message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifNoInsideLogsAreFoundWithinTimePeriodGiven_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1, roomWidth1, roomHeight1);
        Room room1 = new Room(roomName1, roomFloor1, dimensions1, houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(1.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);
        Room room2 = new Room(roomName2, roomFloor2, dimensions2, houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device(deviceName1, deviceModel1, roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device(deviceName2, deviceModel2, roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-04-24";
        String endDate = "2024-04-25";
        String startTime = "22:00";
        String endTime = "04:00";
        String delta = "3";

        DeviceIDVO outDeviceIDVO = outDevice.getId();
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        LocalDateTime initialDateTime = LocalDateTime.parse(startDate+"T"+startTime);
        LocalDateTime finalDateTime = LocalDateTime.parse(endDate+"T"+endTime);

        SensorIDVO outdoorSensorID = new SensorIDVO(UUID.randomUUID());

        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");

        Log outLog1Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:55:50"), new TemperatureValue("23"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog2Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:59:59"), new TemperatureValue("23"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog3Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:00:00"), new TemperatureValue("23"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog4Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:03:50"), new TemperatureValue("23"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog5Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:35:50"), new TemperatureValue("23"), outdoorSensorID, outDeviceID, sensorType);

        List<Log> outdoorLogs = Arrays.asList(outLog1Dev1, outLog2Dev1, outLog3Dev1, outLog4Dev1, outLog5Dev1);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(outdoorLogs);
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(Collections.emptyList());

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "There are no records available for the given period";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result);

    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When both device's logs are found within the time period given but the delta span does not include
     * readings it returns an error message.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifMatchingInstantLogsAreFoundWithinTimePeriodGivenButDeltaSpanDoesNotAllowForMatchingReadings_returnsErrorMessage() {
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1, roomWidth1, roomHeight1);
        Room room1 = new Room(roomName1, roomFloor1, dimensions1, houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(1.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2, roomWidth2, roomHeight2);
        Room room2 = new Room(roomName2, roomFloor2, dimensions2, houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device(deviceName1, deviceModel1, roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device(deviceName2, deviceModel2, roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-03-23";
        String endDate = "2024-03-27";
        String startTime = "22:00";
        String endTime = "23:50";
        String delta = "3";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        DeviceIDVO outDeviceIDVO = outDevice.getId();
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        LocalDateTime initialDateTime = LocalDateTime.parse(startDate + "T" + startTime);
        LocalDateTime finalDateTime = LocalDateTime.parse(endDate + "T" + endTime);

        SensorIDVO outdoorSensorID = new SensorIDVO(UUID.randomUUID());
        SensorIDVO insideSensorID = new SensorIDVO(UUID.randomUUID());

        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");

        Log outLog1Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:50:00"), new TemperatureValue("13"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog2Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:10:00"), new TemperatureValue("13"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog3Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:20:00"), new TemperatureValue("18"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog4Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:40:00"), new TemperatureValue("17"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog5Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T01:10:00"), new TemperatureValue("15"), outdoorSensorID, outDeviceID, sensorType);

        Log inLog1Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:00:00"), new TemperatureValue("23"), insideSensorID, inDeviceID, sensorType);
        Log inLog2Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:30:00"), new TemperatureValue("21"), insideSensorID, inDeviceID, sensorType);
        Log inLog3Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:00:00"), new TemperatureValue("22"), insideSensorID, inDeviceID, sensorType);
        Log inLog4Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:30:00"), new TemperatureValue("25"), insideSensorID, inDeviceID, sensorType);
        Log inLog5Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T01:00:00"), new TemperatureValue("20"), insideSensorID, inDeviceID, sensorType);


        List<Log> outdoorLogs = Arrays.asList(outLog1Dev1, outLog2Dev1, outLog3Dev1, outLog4Dev1, outLog5Dev1);
        List<Log> indoorLogs = Arrays.asList(inLog1Dev1, inLog2Dev1, inLog3Dev1, inLog4Dev1, inLog5Dev1);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(outdoorLogs);
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(indoorLogs);


        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "Readings were found within the provided time span, but with no matches within the delta provided";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result);

    }

    /**
     * Test for getMaxInstantaneousTemperature method of GetMaxInstantaneousTemperatureDifferenceCTRL.
     * When both device's logs are found within the time period given and the delta span allows for matching readings,
     * it returns the maximum instantaneous temperature difference and the corresponding timestamp.
     */
    @Test
    void whenGetMaxInstantaneousTempDifferenceIsCalled_ifMatchingInstantLogsAreFoundWithinTimePeriodGiven_returnsSuccessMessage(){
        // Arrange
        LogRepository logRepository = mock(LogRepository.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        RoomRepository roomRepository = mock(RoomRepository.class);
        LogService logService = new LogServiceImpl(logRepository, deviceRepository, roomRepository);

        HouseIDVO houseID1 = new HouseIDVO(UUID.randomUUID());
        RoomNameVO roomName1 = new RoomNameVO("Outside");
        RoomFloorVO roomFloor1 = new RoomFloorVO(2);
        RoomLengthVO roomLength1 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth1 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight1 = new RoomHeightVO(0.0);
        RoomDimensionsVO dimensions1 = new RoomDimensionsVO(roomLength1,roomWidth1,roomHeight1);
        Room room1 = new Room(roomName1,roomFloor1,dimensions1,houseID1);
        RoomIDVO roomID1 = room1.getId();

        RoomNameVO roomName2 = new RoomNameVO("Room");
        RoomFloorVO roomFloor2 = new RoomFloorVO(2);
        RoomLengthVO roomLength2 = new RoomLengthVO(2.2);
        RoomWidthVO roomWidth2 = new RoomWidthVO(5.0);
        RoomHeightVO roomHeight2 = new RoomHeightVO(1.0);
        RoomDimensionsVO dimensions2 = new RoomDimensionsVO(roomLength2,roomWidth2,roomHeight2);
        Room room2 = new Room(roomName2,roomFloor2,dimensions2,houseID1);
        RoomIDVO roomID2 = room2.getId();


        DeviceNameVO deviceName1 = new DeviceNameVO("Outside Device");
        DeviceModelVO deviceModel1 = new DeviceModelVO("XPTO");
        Device outDevice = new Device (deviceName1,deviceModel1,roomID1);
        DeviceIDVO outDeviceID = outDevice.getId();

        String outDeviceDTOID1 = outDeviceID.getID();
        String deviceNameDTO1 = "Outside Device";
        String deviceModelDTO1 = "XPTO";
        String deviceStatusDTO1 = "On";
        String outRoomDTOID = roomID1.getID();


        DeviceNameVO deviceName2 = new DeviceNameVO("Inside Device");
        DeviceModelVO deviceModel2 = new DeviceModelVO("XP");
        Device inDevice = new Device (deviceName2,deviceModel2,roomID2);
        DeviceIDVO inDeviceID = inDevice.getId();

        String intDeviceDTOID2 = inDeviceID.getID();
        String deviceNameDTO2 = "Inside Device";
        String deviceModelDTO2 = "XP";
        String deviceStatusDTO2 = "On";
        String intRoomDTOID = roomID2.getID();

        when(deviceRepository.findById(outDeviceID)).thenReturn(outDevice);
        when(deviceRepository.findById(inDeviceID)).thenReturn(inDevice);

        when(roomRepository.findById(roomID1)).thenReturn(room1);
        when(roomRepository.findById(roomID2)).thenReturn(room2);

        DeviceDTO outDeviceDTO = new DeviceDTO(outDeviceDTOID1, deviceNameDTO1, deviceModelDTO1, deviceStatusDTO1, outRoomDTOID);
        DeviceDTO intDeviceDTO = new DeviceDTO(intDeviceDTOID2, deviceNameDTO2, deviceModelDTO2, deviceStatusDTO2, intRoomDTOID);

        String startDate = "2024-03-23";
        String endDate = "2024-03-27";
        String startTime = "22:00";
        String endTime = "23:50";
        String delta = "0";

        TimeConfigDTO timeConfigDTO = new TimeConfigDTO(startDate, startTime, endDate, endTime, delta);

        DeviceIDVO outDeviceIDVO = outDevice.getId();
        DeviceIDVO inDeviceIDVO = inDevice.getId();

        LocalDateTime initialDateTime = LocalDateTime.parse(startDate+"T"+startTime);
        LocalDateTime finalDateTime = LocalDateTime.parse(endDate+"T"+endTime);

        SensorIDVO outdoorSensorID = new SensorIDVO(UUID.randomUUID());
        SensorIDVO insideSensorID = new SensorIDVO(UUID.randomUUID());

        SensorTypeIDVO sensorType = new SensorTypeIDVO("TemperatureSensor");

        Log outLog1Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:50:00"), new TemperatureValue("13"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog2Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:00:00"), new TemperatureValue("13"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog3Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:05:00"), new TemperatureValue("18"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog4Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:10:00"), new TemperatureValue("17"), outdoorSensorID, outDeviceID, sensorType);
        Log outLog5Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:15:00"), new TemperatureValue("15"), outdoorSensorID, outDeviceID, sensorType);

        Log inLog1Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:00:00"), new TemperatureValue("23"), insideSensorID, inDeviceID, sensorType);
        Log inLog2Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-25T23:30:00"), new TemperatureValue("21"), insideSensorID, inDeviceID, sensorType);
        Log inLog3Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:02:01"), new TemperatureValue("22"), insideSensorID, inDeviceID, sensorType);
        Log inLog4Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:08:01"), new TemperatureValue("25"), insideSensorID, inDeviceID, sensorType);
        Log inLog5Dev1 = new Log(new LogIDVO(UUID.randomUUID()), LocalDateTime.parse("2024-03-26T00:19:00"), new TemperatureValue("20"), insideSensorID, inDeviceID, sensorType);


        List<Log> outdoorLogs = Arrays.asList(outLog1Dev1, outLog2Dev1, outLog3Dev1, outLog4Dev1, outLog5Dev1);
        List<Log> indoorLogs = Arrays.asList(inLog1Dev1, inLog2Dev1, inLog3Dev1, inLog4Dev1, inLog5Dev1);

        when(logRepository.getDeviceTemperatureLogs(outDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(outdoorLogs);
        when(logRepository.getDeviceTemperatureLogs(inDeviceIDVO, "TemperatureSensor", initialDateTime, finalDateTime)).thenReturn(indoorLogs);


        GetMaxInstantaneousTemperatureDifferenceCTRL ctrl = new GetMaxInstantaneousTemperatureDifferenceCTRL(logService);

        String expected = "The Maximum Temperature Difference within the selected Period was of 9.0 Cº which happened at 2024-03-26T00:02:01";

        // Act
        String result = ctrl.getMaxInstantaneousTemperature(outDeviceDTO, intDeviceDTO, timeConfigDTO);

        // Assert
        assertEquals(expected, result);

    }
}