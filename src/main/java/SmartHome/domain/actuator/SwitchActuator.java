package SmartHome.domain.actuator;

public class SwitchActuator implements Actuator{
    private String actuatorName;
    private String state;
    private SimHardwareAct simHardwareAct;

    public SwitchActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid name for Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
        this.state = "Off";
    }

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    @Override
    public String getName() {
        return this.actuatorName;
    }

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

    private boolean validateCommand(String commandType) {
        return commandType.equalsIgnoreCase("On") || commandType.equalsIgnoreCase("Off");
    }

    public String getState() {
        return state;
    }
}
