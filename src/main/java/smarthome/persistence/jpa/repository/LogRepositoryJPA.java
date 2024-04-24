package smarthome.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactory;
import smarthome.domain.sensor.sensorvalues.SensorValueFactory;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.mapper.assembler.LogAssembler;
import smarthome.persistence.LogRepository;
import smarthome.persistence.jpa.datamodel.LogDataModel;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LogRepositoryJPA implements LogRepository {
    private final LogFactory logFactory;
    private final SensorValueFactory sensorValueFactory;
    private final EntityManagerFactory entityManagerFactory;

    /**
     * Constructor for LogRepositoryJPA.
     *
     * @param logFactory the factory used to create Log domain objects
     * @param sensorValueFactory the factory used to create SensorValue domain objects
     * @param entityManagerFactory the factory used to create EntityManager instances for database interactions
     */
    public LogRepositoryJPA(LogFactory logFactory, SensorValueFactory sensorValueFactory, EntityManagerFactory entityManagerFactory) {
        this.logFactory = logFactory;
        this.sensorValueFactory = sensorValueFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Saves a log to the database.
     *
     * @param log the log to be saved
     * @return true if the log was saved successfully, false otherwise
     * @throws IllegalArgumentException if the log is null
     */
    @Override
    public boolean save(Log log) {
        if (log == null) {
            throw new IllegalArgumentException("Log cannot be null");
        }
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            EntityTransaction tx = em.getTransaction();
            tx.begin();
            LogDataModel logDataModel = new LogDataModel(log);
            em.persist(logDataModel);
            tx.commit();
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Checks if a log with the given ID is present in the database.
     *
     * @param id the ID of the log
     * @return true if the log is present, false otherwise
     * @throws IllegalArgumentException if the id is null
     */
    @Override
    public boolean isPresent(LogIDVO id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Optional<LogDataModel> logDataModel = getDataModelFromId(em, id);
            return logDataModel.isPresent();
        } catch (RuntimeException e) {
            return false;
        }
    }

    /**
     * Retrieves all logs from the database.
     *
     * @return an Iterable of all logs
     */
    @Override
    public Iterable<Log> findAll() {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Query query = em.createQuery("SELECT r FROM LogDataModel r");
            List<LogDataModel> listOfLogs = query.getResultList();
            return LogAssembler.toDomain(logFactory, sensorValueFactory, listOfLogs);
        } catch (RuntimeException e) {
            // Log the exception or rethrow it as a custom exception
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a log from the database by its ID.
     *
     * @param logIDVO the ID of the log to be retrieved
     * @return the retrieved log, or null if no log with the given ID was found
     * @throws IllegalArgumentException if the logIDVO is null
     */
    @Override
    public Log findById(LogIDVO logIDVO) {
        if (logIDVO == null) {
            throw new IllegalArgumentException("LogIDVO cannot be null");
        }
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Optional<LogDataModel> logDataModel = getDataModelFromId(em, logIDVO);
            return logDataModel.map(dataModel ->
                    LogAssembler.toDomain(logFactory, sensorValueFactory, dataModel)).orElse(null);
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * Retrieves a log from the database by its ID.
     *
     * @param em the EntityManager instance used to interact with the database
     * @param logIDVO the ID of the log to be retrieved
     * @return the retrieved log, or null if no log with the given ID was found
     */
    private Optional<LogDataModel> getDataModelFromId(EntityManager em, LogIDVO logIDVO) {
        return Optional.ofNullable(em.find(LogDataModel.class, logIDVO.getID()));
    }

    /**
     * Retrieves all logs associated with a specific device within a given time period.
     *
     * @param deviceID the ID of the device
     * @param from     the start of the time period
     * @param to       the end of the time period
     * @return an Iterable of logs that match the given criteria
     * @throws IllegalArgumentException if any of the parameters are null
     */
    @Override
    public Iterable<Log> findByDeviceIDAndTimeBetween(DeviceIDVO deviceID, LocalDateTime from, LocalDateTime to) {
        if (deviceID == null || from == null || to == null) {
            throw new IllegalArgumentException("DeviceIDVO, from and to cannot be null");
        }
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Query query = em.createQuery("SELECT r FROM LogDataModel r WHERE r.deviceID = :deviceID AND r.time >= :from AND r.time <= :to");
            query.setParameter("deviceID", deviceID.getID());
            query.setParameter("from", from);
            query.setParameter("to", to);
            List<LogDataModel> listOfLogs = query.getResultList();
            return LogAssembler.toDomain(logFactory, sensorValueFactory, listOfLogs);
        } catch (RuntimeException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves all Log objects from the database that fall within the specified time range and are associated with
     * the specified device and sensor type.
     * This method queries the database for LogDataModel objects that match the specified device ID, sensor type, and
     * time range. It uses the setParameter() method to set the named parameters in the query.
     * It then converts the LogDataModel objects to Log objects using LogAssembler and returns the result.
     * If a RuntimeException occurs, it returns an empty list.
     * If any of the input parameters are null, it throws an IllegalArgumentException.
     *
     * @param deviceID   The device ID to filter the log data by.
     * @param sensorType The sensor type to filter the log data by.
     * @param start      The start of the time range to filter the log data by.
     * @param end        The end of the time range to filter the log data by.
     * @return An Iterable of Log objects that match the query criteria.
     */

    public Iterable<Log> getDeviceTemperatureLogs(DeviceIDVO deviceID, String sensorType, LocalDateTime start, LocalDateTime end) {
        if (deviceID == null || sensorType == null || start == null || end == null) {
            throw new IllegalArgumentException("Invalid parameters.");
        }

        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Query query = em.createQuery("SELECT l FROM LogDataModel l WHERE l.deviceID = :deviceID AND l.sensorTypeID = :sensorTypeID AND l.time BETWEEN :start AND :end");
            query.setParameter("deviceID", deviceID.getID());
            query.setParameter("sensorTypeID", sensorType);
            query.setParameter("start", start);
            query.setParameter("end", end);
            List<LogDataModel> listOfLogs = query.getResultList();
            return LogAssembler.toDomain(logFactory, sensorValueFactory, listOfLogs);
        } catch (RuntimeException e) {
            return Collections.emptyList();
        }
    }
}