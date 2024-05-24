package smarthome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.domain.log.Log;
import smarthome.domain.vo.DeltaVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.TimeStampVO;
import smarthome.mapper.DeviceMapper;
import smarthome.mapper.LogMapper;
import smarthome.mapper.dto.LogDTO;
import smarthome.service.LogService;
import smarthome.utils.timeconfig.TimeConfigDTO;
import smarthome.utils.timeconfig.TimeConfigMapper;


import java.util.List;


/**
 * REST controller for managing logs in the Smart Home system.
 * <p>
 * This controller provides endpoints for creating log entries, retrieving log entries within a specific time period,
 * and calculating the maximum temperature difference between indoor and outdoor devices. It interacts with the
 * {@link LogService} to perform the necessary operations and uses various mappers to convert between domain objects
 * and DTOs.
 * </p>
 */
@RestController
@RequestMapping ("/logs")
public class LogCTRLWeb {

    private final LogService logService;


    /**
     * Constructs a new {@code WebLogController} with the specified {@code LogService}.
     *
     * @param logService the service for managing logs
     */
    @Autowired
    public LogCTRLWeb(LogService logService) {
        this.logService = logService;
    }


    /**
     * Finds readings for a specific device within a time period.
     * <p>
     * This endpoint retrieves log entries for a specified device ID within a given time period, as defined
     * by the {@link TimeConfigDTO}. It converts the device ID and timestamps from the DTO to value objects,
     * uses the {@code LogService} to find the logs, and returns the logs as a collection of {@link LogDTO}.
     * </p>
     *
     * @param id the device ID
     * @param timeConfigDTO the time configuration data transfer object
     * @return a {@code ResponseEntity} containing the list of log DTOs and HTTP status
     */
    @GetMapping(params = {"deviceId"})
    public ResponseEntity<CollectionModel<LogDTO>> findReadingsInAPeriod(@RequestParam (value="deviceId") String id, @RequestBody TimeConfigDTO timeConfigDTO) {

        try {
            DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(id);
            TimeStampVO initialTimeStamp = TimeConfigMapper.createInitialTimeStamp(timeConfigDTO);
            TimeStampVO finalTimeStamp = TimeConfigMapper.createFinalTimeStamp(timeConfigDTO);

            List<Log> logs = logService.findReadingsFromDeviceInATimePeriod(deviceIDVO, initialTimeStamp, finalTimeStamp);
            List<LogDTO> logsDTO = LogMapper.domainToDTO(logs);
            // Returns the logs with a status code
            return new ResponseEntity<>(CollectionModel.of(logsDTO), HttpStatus.OK);

        } catch (IllegalArgumentException e) {
            // Return an empty list with a status code
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    /**
     * Gets the maximum temperature difference between indoor and outdoor sensors within a time period.
     * <p>
     * This endpoint calculates the maximum instantaneous temperature difference between an indoor and an outdoor
     * device within a specified time period, as defined by the {@link TimeConfigDTO}. It converts the device IDs
     * and timestamps from the DTO to value objects, uses the {@code LogService} to calculate the temperature difference,
     * and returns the result as a string message.
     * </p>
     *
     * @param outId the outdoor device ID
     * @param inId the indoor device ID
     * @param timeConfigDTO the time configuration data transfer object
     * @return a {@code ResponseEntity} containing the maximum temperature difference message and HTTP status
     */
    @GetMapping(params = {"outdoorId", "indoorId"})
    public ResponseEntity<String> getMaxTempDiff(@RequestParam (value="outdoorId") String outId, @RequestParam (value="indoorId") String inId, @RequestBody TimeConfigDTO timeConfigDTO) {

        try {
            DeviceIDVO outdoorDeviceIDVO = DeviceMapper.createDeviceID(outId);
            DeviceIDVO indoorDeviceIDVO = DeviceMapper.createDeviceID(inId);
            TimeStampVO initialTimeStamp = TimeConfigMapper.createInitialTimeStamp(timeConfigDTO);
            TimeStampVO finalTimeStamp = TimeConfigMapper.createFinalTimeStamp(timeConfigDTO);
            DeltaVO delta = TimeConfigMapper.createDeltaVO(timeConfigDTO);


            String maxTempDiff = logService.getMaxInstantaneousTempDifference(outdoorDeviceIDVO, indoorDeviceIDVO, initialTimeStamp, finalTimeStamp, delta);
            // Returns a message with the Maximum Temperature Difference, plus a status code
            return new ResponseEntity<>(maxTempDiff, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            // Return an error message, plus a status code
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}