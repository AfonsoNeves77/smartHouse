package smarthome.mapper;

import smarthome.domain.log.Log;
import smarthome.mapper.dto.LogDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LogMapper {

    /**
     * Private constructor to prevent instantiation of the LogMapper class.
     * <p>
     * This constructor is created to hide the implicit public constructor, preventing instantiation
     * of the LogMapper class from outside the class itself or its nested classes.
     */
    private LogMapper(){
        // Created to hide implicit public constructor
    }

    /**
     * Converts a domain model Log object to a data transfer object (DTO) LogDTO.
     * <p>
     * This method takes a Log object from the domain model and maps its attributes to a LogDTO object.
     * The LogDTO object represents the same data in a format suitable for transfer between different layers
     * or components of an application. If the provided Log object is null, the method throws an IllegalArgumentException
     * with an appropriate error message. Otherwise, it extracts the attributes of the Log object,
     * including log ID, timestamp, reading value, sensor ID, device ID, and sensor type ID, and uses them
     * to instantiate a new LogDTO object.
     * @param log The Log object from the domain model to be converted to a DTO.
     * @return A LogDTO object representing the data from the input Log object.
     * @throws IllegalArgumentException If the provided Log object is null.
     */
    public static LogDTO domainToDTO (Log log) {
        if (log == null){
            throw new IllegalArgumentException("Invalid parameter");
        }

        LocalDateTime timeValue = log.getTime().getValue();
        String formattedTime = timeValue.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));

        return LogDTO.builder()
                .logID(log.getId().getID())
                .time(formattedTime)
                .reading(log.getReading().getValue().toString())
                .sensorID(log.getSensorID().getID())
                .deviceID(log.getDeviceID().getID())
                .sensorTypeID(log.getSensorTypeID().getID())
                .build();
    }

    /**
     * Converts an iterable collection of domain model Log objects to a list of data transfer objects (DTOs) LogDTO.
     * <p>
     * This method takes an iterable collection of Log objects from the domain model and maps each Log object
     * to a corresponding LogDTO object. The resulting list contains LogDTO objects representing the data from
     * the input Log objects. If the provided iterable collection of Logs is null, the method throws an
     * IllegalArgumentException with an appropriate error message. Otherwise, it iterates over the input iterable
     * collection, applies the domainToDTO() method to each Log object to perform the conversion, and collects
     * the resulting LogDTO objects into a list.
     * @param listOfLogs The iterable collection of Log objects from the domain model to be converted to DTOs.
     * @return A list of LogDTO objects representing the data from the input Log objects.
     * @throws IllegalArgumentException If the provided iterable collection of Logs is null.
     */
    public static List<smarthome.mapper.dto.LogDTO> domainToDTO (List<Log> listOfLogs) {
        if (listOfLogs == null){
            throw new IllegalArgumentException("Invalid parameter");
        }
        List<smarthome.mapper.dto.LogDTO> listOfLogDTO = new ArrayList<>();
        for (Log log : listOfLogs) {
            smarthome.mapper.dto.LogDTO logDTO = LogMapper.domainToDTO(log);
            listOfLogDTO.add(logDTO);
        }
        return listOfLogDTO;
    }
}