package SmartHomeDDD.dto;

public class ActuatorTypeDTO {

    private String actuatorTypeID;

    /**
     * Constructor for ActuatorTypeDTO
     *
     * @param actuatorTypeID as String
     */
    public ActuatorTypeDTO(String actuatorTypeID) {
        if (actuatorTypeID == null || actuatorTypeID.isEmpty())
            throw new IllegalArgumentException("ActuatorType cannot be null or empty");
        this.actuatorTypeID = actuatorTypeID;
    }

    /**
     * Get the actuatorTypeID
     *
     * @return actuatorTypeID as String
     */
    public String getActuatorTypeID() {
        return actuatorTypeID;
    }
}
