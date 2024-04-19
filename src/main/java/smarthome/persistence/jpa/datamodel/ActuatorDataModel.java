package smarthome.persistence.jpa.datamodel;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * DataModel class for the Actuator entity.
 * This class is used to persist the Actuator entity in the database.
 * The class is annotated with the JPA annotations to specify the table name and the column names.
 * The class has a constructor that initializes the ActuatorDataModel object with the provided parameters.
 * The class has getter methods to retrieve the values of the ActuatorDataModel object.
 * The class is used by the ActuatorRepository to persist and retrieve the Actuator entity from the database.
 */
@Entity
@Table(name = "actuator")
public class ActuatorDataModel {
    @Id
    private String actuatorID;
    private String actuatorName;
    private String actuatorTypeID;
    private String deviceID;
    private String lowerLimit;
    private String upperLimit;
    private String precision;

    /**
     * Default constructor.
     */
    public ActuatorDataModel() {
    }

    /**
     * Constructor to initialize the ActuatorDataModel object with the provided parameters.
     *
     * @param actuatorID
     * @param actuatorName
     * @param actuatorTypeID
     * @param deviceID
     * @param lowerLimit
     * @param upperLimit
     * @param precision
     * @result The ActuatorDataModel object is created with the provided parameters.
     */
    public ActuatorDataModel(String actuatorID, String actuatorName, String actuatorTypeID, String deviceID, String lowerLimit, String upperLimit, String precision) {
        this.actuatorID = actuatorID;
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceID = deviceID;
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.precision = precision;
    }

    /**
     * Getter method to retrieve the actuator ID.
     *
     * @return The actuator ID.
     */
    public String getActuatorID() {
        return actuatorID;
    }

    /**
     * Getter method to retrieve the actuator name.
     *
     * @return The actuator name.
     */
    public String getActuatorName() {
        return actuatorName;
    }

    /**
     * Getter method to retrieve the actuator type ID.
     *
     * @return The actuator type ID.
     */
    public String getActuatorTypeID() {
        return actuatorTypeID;
    }

    /**
     * Getter method to retrieve the device ID.
     *
     * @return The device ID.
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Getter method to retrieve the lower limit.
     *
     * @return The lower limit.
     */
    public String getLowerLimit() {
        return lowerLimit;
    }

    /**
     * Getter method to retrieve the upper limit.
     *
     * @return The upper limit.
     */
    public String getUpperLimit() {
        return upperLimit;
    }

    /**
     * Getter method to retrieve the precision.
     *
     * @return The precision.
     */
    public String getPrecision() {
        return precision;
    }
}
