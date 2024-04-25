package smarthome.mapper;

import smarthome.domain.log.Log;
import smarthome.mapper.dto.LogDTO;

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
        String logID = log.getId().getID();
        String localDateTime = log.getTime().toString();
        String reading = log.getReading().getValue().toString();
        String sensorID = log.getSensorID().getID();
        String deviceID = log.getDeviceID().getID();
        String sensorTypeID = log.getSensorTypeID().getID();

        return new LogDTO(logID, localDateTime, reading, sensorID, deviceID, sensorTypeID);
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
    public static List<LogDTO> domainToDTO (List<Log> listOfLogs) {
        if (listOfLogs == null){
            throw new IllegalArgumentException("Invalid parameter");
        }
        List<LogDTO> listOfLogDTO = new ArrayList<>();
        for (Log log : listOfLogs) {
            LogDTO logDTO = LogMapper.domainToDTO(log);
            listOfLogDTO.add(logDTO);
        }
        return listOfLogDTO;
    }
}