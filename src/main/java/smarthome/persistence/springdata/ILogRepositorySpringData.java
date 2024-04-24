package smarthome.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import smarthome.persistence.jpa.datamodel.LogDataModel;

import java.time.LocalDateTime;
import java.util.List;

public interface ILogRepositorySpringData extends JpaRepository<LogDataModel, String> {
    /**
     * Finds all logs by device ID and time range.
     *
     * @param deviceID the device ID
     * @param from     the start time
     * @param to       the end time
     * @return a list of logs that match the device ID and time range
     */

    List<LogDataModel> findByDeviceIDAndTimeBetween(String deviceID, LocalDateTime from, LocalDateTime to);
}