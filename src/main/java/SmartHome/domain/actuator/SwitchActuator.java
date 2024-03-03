package SmartHome.domain.actuator;

public class SwitchActuator implements Actuator{
    private String actuatorName;
    private String state;

    public SwitchActuator(String actuatorName, String state) {
        if (!validateName(actuatorName) || !validateName(state)) {
            throw new IllegalArgumentException("Invalid name for Actuator.");
        }
        this.actuatorName = actuatorName;
        this.state = state;
    }

    private boolean validateName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    @Override
    public String getName() {
        return this.actuatorName;
    }

    public boolean executeCommand(String commandType, SimHardwareAct simHardwareAct) {
        if (!validateCommand(commandType)) {
            return false;
        }
        boolean executionResult = simHardwareAct.executeCommandSim(commandType);
        if (executionResult) {
            this.state = commandType;
        }
        return executionResult;
    }

    private boolean validateCommand(String commandType) {
        return commandType.equalsIgnoreCase("On") || commandType.equalsIgnoreCase("Off");
    }

    public String getState() {
        return state;
    }
}
