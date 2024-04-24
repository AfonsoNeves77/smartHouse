package smarthome.persistence.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    /**
     * This method retrieves all log data from the database that falls within the specified time range and is associated
     * with the specified device and sensor type.
     * It is a query method that is annotated with @Query to specify the JPQL query to be executed.
     * The query retrieves all log data that matches the specified device ID, sensor type, and time range.
     * The method parameters are annotated with @Param to specify the named parameters in the query.
     * The method returns a List of LogDataModel objects that match the query criteria.
     *
     * @param deviceID   The device ID to filter the log data by.
     * @param sensorType The sensor type to filter the log data by.
     * @param start      The start of the time range to filter the log data by.
     * @param end        The end of the time range to filter the log data by.
     * @return A List of LogDataModel objects that match the query criteria.
     */
    @Query("SELECT l FROM LogDataModel l " +
            "WHERE l.deviceID = :deviceID " +
            "AND l.sensorTypeID = :sensorType " +
            "AND l.time BETWEEN :start AND :end")
    List<LogDataModel> findByDeviceIDAndSensorTypeAndTimeBetween(
            @Param("deviceID") String deviceID,
            @Param("sensorType") String sensorType,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );
}