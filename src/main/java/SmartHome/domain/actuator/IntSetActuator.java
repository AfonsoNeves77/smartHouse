package SmartHome.domain.actuator;

/**
 * This class represents an actuator that can be used to set an integer value on a simulated hardware.
 * It implements the Actuator interface.
 */

public class IntSetActuator implements Actuator {

    /**
     * actuatorName String to store the name of the sensor
     * value int to store the value of the sensor
     * simHardwareAct SimHardwareAct to store the SimHardwareAct object
     * upperLimit int to store the upper limit of the actuator
     * lowerLimit int to store the lower limit of the actuator
     */

    private String actuatorName;
    private int value;
    private SimHardwareAct simHardwareAct;
    private Integer upperLimit;
    private Integer lowerLimit;

    /**
     * IntSetActuator constructor
     * @param actuatorName String to store the name of the actuator
     * @param simHardwareAct SimHardwareAct to store the SimHardwareAct object
     * @throws InstantiationException if the actuator name is null or empty or the simHardwareAct object is null
     */

    public IntSetActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid parameters for Integer Range Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
    }

    /**
     * This method is used to validate the parameters of the constructor
     * @param name String to store the name of the actuator
     * @param simHardwareAct SimHardwareAct to store the SimHardwareAct object
     * @return True if the parameters are valid, false otherwise
     */

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct != null;
    }

    /**
     * This method is used to execute a command on the actuator
     * @param newValue The value to be set
     * @return True if the command was executed successfully, false otherwise
     */

    public boolean executeCommand(int newValue) {
        if (lowerLimit == null && upperLimit == null) {
            return false;
        } else {
            if (!validateValue(newValue)) {
                return false;
            }
            boolean executionResult = simHardwareAct.executeIntegerCommandSim(newValue);
            if (executionResult) {
                this.value = newValue;
            }
            return executionResult;
        }
    }

    /**
     * This method is used to validate the value to be set
     * @param value The value to be set
     * @return True if the value is valid, false otherwise
     */

    private boolean validateValue(int value) {
        if (value < lowerLimit || value > upperLimit) {
            return false;
        }
        return true;
    }

    /**
     * This method is used to set the limits of the actuator
     * @param lowerLimit The lower limit of the actuator
     * @param upperLimit The upper limit of the actuator
     * @throws IllegalArgumentException if the upper limit is less than or equal to the lower limit
     */

    public void setLimits(int lowerLimit, int upperLimit) {
        if (upperLimit <= lowerLimit) {
            throw new IllegalArgumentException("Upper limit can't be less than or equal to lower limit.");
        } else {
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            this.value = lowerLimit;
        }
    }

    /**
     * This method is used to get the value of the actuator
     * @return The value of the actuator
     */

    public int getValue() {
        return value;
    }

    /**
     * This method is used to get the name of the actuator
     * @return The name of the actuator
     */

    public String getName() {
        return this.actuatorName;
    }
}
