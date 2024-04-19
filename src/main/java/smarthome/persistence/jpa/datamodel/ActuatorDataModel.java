package smarthome.persistence.jpa.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smarthome.domain.actuator.Actuator;

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
    @Column(name = "id")
    private String actuatorID;
    @Column(name = "actuator_name")
    private String actuatorName;
    @Column(name = "actuator_type_id")
    private String actuatorTypeID;
    @Column(name = "device_id")
    private String deviceID;
    @Column(name = "lower_limit")
    private String lowerLimit;
    @Column(name = "upper_limit")
    private String upperLimit;
    @Column(name = "precision")
    private String precision;

    /**
     * Default constructor for an ActuatorDataModel.
     */
    public ActuatorDataModel() {
    }

    /**
     * Constructor to initialize the ActuatorDataModel object with the provided parameters.
     *
     * @param actuator Actuator to be converted in a data model
     * @result The ActuatorDataModel object is created with the provided parameters.
     */
    public ActuatorDataModel(Actuator actuator) {
        this.actuatorID = actuator.getId().getID();
        this.actuatorName = actuator.getActuatorName().getValue();
        this.actuatorTypeID = actuator.getActuatorTypeID().getID();
        this.deviceID = actuator.getDeviceID().getID();
        this.lowerLimit = actuator.getLowerLimit();
        this.upperLimit = actuator.getUpperLimit();
        this.precision = actuator.getPrecision();
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
