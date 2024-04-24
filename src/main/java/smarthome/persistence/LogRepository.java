package smarthome.persistence;

import smarthome.domain.log.Log;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;

import java.time.LocalDateTime;

/**
 * This interface defines the operations that a LogRepository must support.
 * It extends the generic Repository interface with LogIDVO as the ID type and Log as the entity type.
 */
public interface LogRepository extends Repository<LogIDVO, Log>{

    /**
     * Retrieves all logs associated with a specific device within a given time period.
     *
     * @param deviceID the ID of the device
     * @param from     the start of the time period
     * @param to       the end of the time period
     * @return an Iterable of logs that match the given criteria
     */
    Iterable<Log> findByDeviceIDAndTimeBetween(DeviceIDVO deviceID, LocalDateTime from, LocalDateTime to);
    Iterable<Log> getDeviceTemperatureLogs(DeviceIDVO deviceID, String sensorType, LocalDateTime start, LocalDateTime end);
}
