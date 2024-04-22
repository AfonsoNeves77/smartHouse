package smarthome.persistence.jpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactory;
import smarthome.domain.sensor.sensorvalues.SensorValueFactory;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.mapper.assembler.LogAssembler;
import smarthome.persistence.LogRepository;
import smarthome.persistence.Repository;
import smarthome.persistence.jpa.datamodel.LogDataModel;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * The LogRepositoryJPA class is an implementation of the LogRepository interface.
 * This class is responsible for performing Create, Read, Update, and Delete (CRUD) operations on the Log entity in the
 * database, in this specific repository only Create and Read operations are made. It leverages the Java Persistence
 * API (JPA) for database interactions.
 */

public class LogRepositoryJPA implements LogRepository {
    private final LogFactory logFactory;
    private final SensorValueFactory sensorValueFactory;
    private final EntityManagerFactory entityManagerFactory;

    /**
     * This is the constructor for the LogRepositoryJPA class.
     * It requires a LogFactory and an EntityManagerFactory as input parameters.
     *
     * @param logFactory This is utilized for the creation of Log instances.
     * @param entityManagerFactory This is used to generate EntityManager instances that facilitate interaction with
     * the database.
     */

    public LogRepositoryJPA(LogFactory logFactory, SensorValueFactory sensorValueFactory,
                            EntityManagerFactory entityManagerFactory) {
        this.logFactory = logFactory;
        this.sensorValueFactory = sensorValueFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    /**
     * Saves a Log object to the database.
     * This method implements the save() method from the Repository interface.
     * It first validates the Log object, then initiates a transaction to persist the Log object in the database.
     * If the operation is successful, it returns true. If a RuntimeException occurs, it rolls back the transaction and
     * returns false.
     *
     * @param log The Log object to be saved.
     * @return true if the Log object was saved successfully, false otherwise.
     */
    @Override
    public boolean save(Log log) {
        if (objectIsNull(log)) {
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
     * Checks if a Log object exists in the database using its LogIDVO.
     * This method implements the isPresent() method from the Repository interface.
     * It validates the LogIDVO, retrieves the corresponding LogDataModel from the database, and returns true if it
     * exists.
     * If a RuntimeException occurs or the LogDataModel does not exist, it returns false.
     *
     * @param id The LogIDVO of the Log object to check.
     * @return true if the Log object exists in the database, false otherwise.
     */

    @Override
    public boolean isPresent(LogIDVO id) {
        if (objectIsNull(id)) {
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
     * Retrieves all Log objects from the database.
     * This method implements the findAll() method from the Repository interface.
     * It creates a Query to fetch all LogDataModel objects, converts them to Log objects using LogAssembler, and
     * returns the result.
     * If a RuntimeException occurs, it returns an empty list.
     *
     * @return An Iterable of Log objects.
     */

    @Override
    public Iterable<Log> findAll() {

        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Query query = em.createQuery("SELECT r FROM LogDataModel r");
            List<LogDataModel> listOfLogs = query.getResultList();
            return LogAssembler.toDomain(logFactory, sensorValueFactory, listOfLogs);
        } catch (RuntimeException e) {
            return Collections.emptyList();
        }
    }

    /**
     * Retrieves a Log object from the database using its LogIDVO.
     * This method implements the findById() method from the Repository interface.
     * It validates the LogIDVO, fetches the corresponding LogDataModel from the database, and converts it to a Log
     * object. If a RuntimeException occurs or the LogDataModel does not exist, it returns null.
     *
     * @param logIDVO The LogIDVO of the Log object to retrieve.
     * @return The retrieved Log object, or null if not found.
     */

    @Override
    public Log findById(LogIDVO logIDVO) {
        if (objectIsNull(logIDVO)) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            Optional<LogDataModel> logDataModel = getDataModelFromId(em, logIDVO);
            return logDataModel.map(dataModel ->
                    LogAssembler.toDomain(logFactory, sensorValueFactory,dataModel)).orElse(null);
        } catch (RuntimeException e) {
            return null;
        }
    }

    /**
     * Retrieves a LogDataModel from the database using its LogIDVO.
     * This method validates the EntityManager and LogIDVO, then fetches the LogDataModel from the database.
     * If the LogDataModel exists, it returns an Optional containing it, otherwise it returns an empty Optional.
     *
     * @param em The EntityManager for database interaction.
     * @param logIDVO The LogIDVO of the LogDataModel to fetch.
     * @return An Optional containing the LogDataModel if found, or an empty Optional otherwise.
     */

    private Optional<LogDataModel> getDataModelFromId(EntityManager em, LogIDVO logIDVO) {
        return Optional.ofNullable(em.find(LogDataModel.class, logIDVO.getID()));
    }

    /**
     * Method to check if an object is null. It returns true if the object is null, and false otherwise.
     *
     * @param object The object to check.
     * @return A boolean value indicating whether the object is null.
     */
    private boolean objectIsNull(Object object) {
        return object == null;
    }
}
