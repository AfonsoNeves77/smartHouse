package smarthome.persistence.springdata;

import org.springframework.dao.DataAccessException;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactory;
import smarthome.domain.sensor.sensorvalues.SensorValueFactory;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.mapper.assembler.LogAssembler;
import smarthome.persistence.LogRepository;
import smarthome.persistence.jpa.datamodel.LogDataModel;

import java.util.Optional;

/**
 * The LogRepositorySpringData class is an implementation of the LogRepository interface using Spring Data JPA.
 * It provides methods to interact with the persistence layer for managing logs.
 * <p>
 * This class handles saving, finding, and checking the existence of logs in the database.
 * It utilizes Spring Data JPA repositories for database operations.
 */
public class LogRepositorySpringData implements LogRepository {
    private final ILogRepositorySpringData iLogRepositorySpringData;
    private final LogFactory logFactory;
    private final SensorValueFactory sensorValueFactory;

    /**
     * Constructs a new LogRepositorySpringData instance.
     *
     * @param iLogRepositorySpringData the Spring Data JPA repository interface
     * @param logFactory               the factory for creating Log objects
     * @param sensorValueFactory       the factory for creating SensorValue objects
     */
    public LogRepositorySpringData(ILogRepositorySpringData iLogRepositorySpringData, LogFactory logFactory, SensorValueFactory sensorValueFactory) {
        this.iLogRepositorySpringData = iLogRepositorySpringData;
        this.logFactory = logFactory;
        this.sensorValueFactory = sensorValueFactory;

    }

    /**
     * Saves a Log object to the database.
     *
     * @param log the Log object to be saved
     * @return true if the log is saved successfully, false otherwise
     * @throws IllegalArgumentException if the provided log is null
     */
    @Override
    public boolean save(Log log) {
        if (log == null) {
            throw new IllegalArgumentException("Log cannot be null");
        }
        LogDataModel logdataModel = new LogDataModel(log);

        try {
            this.iLogRepositorySpringData.save(logdataModel);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    /**
     * Finds a Log object by its ID.
     *
     * @param logIDVO the ID of the log to be found
     * @return the found Log object, or null if not found
     * @throws IllegalArgumentException if the provided LogIDVO is null
     */
    @Override
    public Log findById(LogIDVO logIDVO) {
        if (logIDVO == null) {
            throw new IllegalArgumentException("LogIDVO cannot be null");
        }
        String id = logIDVO.getID();
        try {
            Optional<LogDataModel> logDataModelOptional = this.iLogRepositorySpringData.findById(id);

            if (logDataModelOptional.isPresent()) {
                LogDataModel logDataModel = logDataModelOptional.get();
                return LogAssembler.toDomain(this.logFactory, this.sensorValueFactory, logDataModel);
            } else {
                return null;
            }
        } catch (DataAccessException e) {
            return null;
        }
    }


    /**
     * Checks if a Log with the given ID exists in the database.
     *
     * @param id the ID of the log to be checked
     * @return true if the log exists, false otherwise
     * @throws IllegalArgumentException if the provided LogIDVO is null
     */
    @Override
    public boolean isPresent(LogIDVO id) {
        if (id == null) {
            throw new IllegalArgumentException("LogIDVO cannot be null");
        }
        return findById(id) != null;
    }

    /**
     * Retrieves all Log objects from the database.
     *
     * @return an Iterable containing all Log objects, or null if an error occurs
     */
    @Override
    public Iterable<Log> findAll() {
        try {
            Iterable<LogDataModel> logDataModelIterable = this.iLogRepositorySpringData.findAll();
            return LogAssembler.toDomain(this.logFactory, this.sensorValueFactory, logDataModelIterable);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
