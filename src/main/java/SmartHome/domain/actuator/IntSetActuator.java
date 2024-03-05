package SmartHome.domain.actuator;

public class IntSetActuator implements Actuator {

    private String actuatorName;
    private int value;
    private SimHardwareAct simHardwareAct;
    private int upperLimit;
    private int lowerLimit;

    public IntSetActuator(String actuatorName, SimHardwareAct simHardwareAct) {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new IllegalArgumentException("Invalid parameters for Integer Range Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
    }

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct != null;
    }

    public boolean executeCommand(int newValue) {
        if (!validateValue(newValue)) {
            return false;
        }
        boolean executionResult = simHardwareAct.executeIntegerCommandSim(newValue);
        if (executionResult) {
            this.value = newValue;
        }
        return executionResult;
    }

    private boolean validateValue(int value) {
        if (value < lowerLimit || value > upperLimit) {
            return false;
        }
        return true;
    }

    public void setLimits(int lowerLimit, int upperLimit) {
        if (upperLimit <= lowerLimit) {
            throw new IllegalArgumentException("Upper limit can't be less than or equal to lower limit.");
        } else {
            this.lowerLimit = lowerLimit;
            this.upperLimit = upperLimit;
            this.value = lowerLimit;
        }
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return this.actuatorName;
    }
}
