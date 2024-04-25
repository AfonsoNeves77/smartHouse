package smarthome.service;

import smarthome.domain.log.Log;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.persistence.LogRepository;
import smarthome.utils.timeconfig.TimeConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;

    /**
     * Constructor for LogServiceImpl.
     * @param logRepository the repository used for data access
     * @throws IllegalArgumentException if the logRepository is null
     */
    public LogServiceImpl(LogRepository logRepository) {
        if (areParamsNull(logRepository)) {
            throw new IllegalArgumentException("LogRepository cannot be null.");
        }
        this.logRepository = logRepository;
    }

    /**
     * Retrieves all logs associated with a specific device within a given time period.
     * @param deviceID the ID of the device
     * @param time     the time configuration object
     * @return an Iterable of logs that match the given criteria
     * @throws IllegalArgumentException if any of the parameters are null
     */
    @Override
    public List<Log> findReadingsFromDeviceInATimePeriod(DeviceIDVO deviceID, TimeConfig time) {
        if (areParamsNull(deviceID, time)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        LocalDateTime from = time.getInitialTimeStamp();
        LocalDateTime to = time.getEndTimeStamp();

        try {
            Iterable<Log> iterable = logRepository.findByDeviceIDAndTimeBetween(deviceID, from, to);
            return convertToList(iterable);
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Checks if any of the provided parameters are null.
     * <p>
     * This method takes a variable number of parameters and iterates over them to determine if any of them are null.
     * If any parameter is found to be null, the method returns true; otherwise, it returns false. This method is useful
     * for checking multiple parameters for nullability in a concise way.
     * @param params The parameters to be checked for null.
     * @return true if any of the parameters are null, false otherwise.
     */
    private boolean areParamsNull(Object... params){
        for(Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * Converts an Iterable of Log objects into an ArrayList.
     * @param logIterable The Iterable containing Log objects to be converted.
     * @return An ArrayList containing the Log objects from the Iterable.
     */
    private List<Log> convertToList (Iterable<Log> logIterable){
        List<Log> listLogs = new ArrayList<>();
        for (Log log : logIterable){
            listLogs.add(log);
        }
        return listLogs;
    }

}
