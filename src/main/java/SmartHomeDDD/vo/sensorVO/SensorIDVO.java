package SmartHomeDDD.vo.sensorVO;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

public class SensorIDVO implements DomainID {
    private final UUID identifier;

    /**
     * Constructor for SensorID Value Object. It receives a UUID identifier, and creates the object if the identifier
     * is not null;
     * @param identifier Unique identifier for the Sensor
     * @throws IllegalArgumentException If identifier is null
     */
    public SensorIDVO(UUID identifier){
        if(identifier == null)
            throw new IllegalArgumentException("Invalid Identifier");
        this.identifier = identifier;
    }


    /**
     * Implements method from DomainID Interface
     * @return A String format of the Sensor identifier
     */
    @Override
    public String getID() {
        return identifier.toString();
    }
}
