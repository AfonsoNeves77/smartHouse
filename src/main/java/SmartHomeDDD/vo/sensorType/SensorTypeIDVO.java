package SmartHomeDDD.vo.sensorType;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

public class SensorTypeIDVO implements DomainID {

    private final UUID sensorTypeID;

    /**
     * Constructor for SensorTypeID Value Object. It receives a UUID identifier, and creates the object if the identifier
     * is not null;
     * @param sensorTypeID UUID identifier;
     */
    public SensorTypeIDVO(UUID sensorTypeID) {
        if (sensorTypeID == null) throw new IllegalArgumentException("SensorTypeID cannot be null");
        this.sensorTypeID = sensorTypeID;
    }

    /**
     * Simple getter method;
     * @return Receives the encapsulated value as string;
     */
    public String getID() {
        return sensorTypeID.toString();
    }
}
