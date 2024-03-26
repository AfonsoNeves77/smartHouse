package SmartHomeDDD.vo.actuatorType;

import SmartHomeDDD.domain.DomainID;


public class ActuatorTypeIDVO implements DomainID {

    private final String actuatorTypeID;

    /**
     * Constructor for ActuatorTypeID Value Object. It receives an identifier, and creates the object if the identifier
     * is not null or empty;
     * @param actuatorTypeID String identifier for the actuator type;
     */

    public ActuatorTypeIDVO(String actuatorTypeID) {
        if (actuatorTypeID == null || actuatorTypeID.trim().isEmpty()){
            throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        }
        this.actuatorTypeID = actuatorTypeID;
    }

    /**
     * Simple getter method;
     * @return Receives the encapsulated value (which in this case is a string);
     */
    public String getID() {
        return actuatorTypeID;
    }
}