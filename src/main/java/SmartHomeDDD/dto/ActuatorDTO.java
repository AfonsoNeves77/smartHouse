package SmartHomeDDD.dto;

/**
 * Data Transfer Object that contains the actuator ID, name, type and device ID.
 */

public class ActuatorDTO {

    private String actuatorID;
    private String actuatorName;
    private String actuatorType;
    private String deviceID;

    /**
     * Constructor for ActuatorDTO. It initializes the actuator ID, name, type and device ID.
     *
     * @param actuatorName actuator name
     * @param actuatorType actuator type
     * @param deviceID     device ID in string format
     */

    public ActuatorDTO(String actuatorID, String actuatorName, String actuatorType, String deviceID) {
        this.actuatorID = actuatorID;
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
    }

    /**
     * Constructor for ActuatorDTO. It initializes the actuator name, type and device ID.
     * Used to create a new actuator. Does not include the actuator ID because it is generated automatically.
     *
     * @param actuatorName actuator name
     * @param actuatorType actuator type
     * @param deviceID     device ID in string format
     */

    public ActuatorDTO(String actuatorName, String actuatorType, String deviceID) {
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
    }

    /**
     * Method to get the actuator name.
     *
     * @return The name of the actuator.
     */

    public String getActuatorName() {
        return actuatorName;
    }

    /**
     * Method to get the actuator ID.
     *
     * @return The ID of the actuator.
     */

    public String getActuatorID() {
        return actuatorID;
    }

    /**
     * Method to get the actuator type.
     *
     * @return The type of the actuator.
     */

    public String getActuatorType() {
        return actuatorType;
    }

    /**
     * Method to get the device ID.
     *
     * @return The ID of the device.
     */

    public String getDeviceID() {
        return deviceID;
    }
}
