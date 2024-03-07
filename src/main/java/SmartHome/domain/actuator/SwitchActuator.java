package SmartHome.domain.actuator;

public class SwitchActuator implements Actuator{
    private String actuatorName;
    private String state;
    private SimHardwareAct simHardwareAct;

    /**
     * Constructs a SwitchActuator object with the specified name and simulated hardware.
     *
     * @param actuatorName   the name of the actuator
     * @param simHardwareAct the simulated hardware for executing commands
     * @throws InstantiationException if an invalid name is provided for the actuator
     */
    public SwitchActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid name for Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
        this.state = "Off";
    }

    /**
     * Validates the parameters for the SwitchActuator.
     *
     * @param name            the name of the actuator
     * @param simHardwareAct  the simulated hardware for executing commands
     * @return true if the parameters are valid, false otherwise
     */
    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    /**
     * Gets the name of the actuator.
     *
     * @return the name of the actuator
     */
    @Override
    public String getName() {
        return this.actuatorName;
    }

    /**
     * Executes the specified command on the actuator.
     *
     * @param commandType the type of command to be executed
     * @return true if the command was executed successfully, false otherwise
     */
    public boolean executeCommand(String commandType) {
        if (!validateCommand(commandType)) {
            return false;
        }
        boolean executionResult = simHardwareAct.executeCommandSim(commandType);
        if (executionResult) {
            this.state = commandType;
        }
        return executionResult;
    }

    /**
     * Validates the specified command.
     *
     * @param commandType the type of command to be executed
     * @return true if the command is valid, false otherwise
     */
    private boolean validateCommand(String commandType) {
        return commandType.equalsIgnoreCase("On") || commandType.equalsIgnoreCase("Off");
    }

    /**
     * Gets the state of the actuator.
     *
     * @return the state of the actuator
     */
    public String getState() {
        return state;
    }
}
