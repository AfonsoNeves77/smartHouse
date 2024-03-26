package SmartHomeDDD.vo.sensorType;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

public class SensorTypeIDVO implements DomainID {

    private final String sensorTypeID;

    /**
     * Constructor for SensorTypeID Value Object. It receives an identifier, and creates the object if the identifier
     * is not null or empty;
     * @param sensorTypeID String identifier for the sensor type;
     */
    public SensorTypeIDVO(String sensorTypeID) {
        if (sensorTypeID == null || sensorTypeID.trim().isEmpty()){
            throw new IllegalArgumentException("SensorTypeID cannot be null");
        } else {
            this.sensorTypeID = sensorTypeID;
        }
    }

    /**
     * Simple getter method;
     * @return Receives the encapsulated value (which in this case is a string);
     */
    public String getID() {
        return sensorTypeID;
    }
}
