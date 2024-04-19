package smarthome.persistence.jpa.datamodel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import smarthome.domain.log.Log;

/**
 * LogDataModel is a class that mirrors the Log entity within the database.
 * It serves as a means to persist Log objects in the database.
 */

@Entity
@Table(name = "LOG")
public class LogDataModel {
    @Id
    @Column(name = "id")
    private String logID;
    @Column(name = "time")
    private String time;
    @Column(name = "reading")
    private String reading;
    @Column(name = "sensor_id")
    private String sensorID;
    @Column(name = "device_id")
    private String deviceID;
    @Column(name = "sensor_type_id")
    private String sensorTypeID;

    /**
     * Constructor with no arguments. Necessary for JPA.
     */
    public LogDataModel() {
    }

    /**
     * This constructor forms a LogDataModel instance using a Log domain object as input.
     * It extracts the required attributes from the Log object to instantiate the LogDataModel.
     * This is typically used to transform a Log object into a LogDataModel before storing it in the database.
     *
     * @param log The Log instance that serves as the basis for the LogDataModel creation.
     */
    public LogDataModel(Log log) {
        this.logID = log.getId().toString();
        this.time = log.getTime().toString();
        this.reading = log.getReading().toString();
        this.sensorID = log.getSensorID().toString();
        this.deviceID = log.getDeviceID().toString();
        this.sensorTypeID = log.getSensorTypeID().toString();
    }

    /**
     * Getter to obtain the logID attribute.
     */
    public String getLogID() {
        return logID;
    }

    /**
     * Getter to obtain the time attribute.
     */
    public String getTime() {
        return time;
    }

    /**
     * Getter to obtain the reading attribute.
     */
    public String getReading() {
        return reading;
    }

    /**
     * Getter to obtain the sensorID attribute.
     */
    public String getSensorID() {
        return sensorID;
    }

    /**
     * Getter to obtain the deviceID attribute.
     */
    public String getDeviceID() {
        return deviceID;
    }

    /**
     * Getter to obtain the sensorTypeID attribute.
     */
    public String getSensorTypeID() {
        return sensorTypeID;
    }
}