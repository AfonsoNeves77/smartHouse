package SmartHome.domain.actuator;

public class BlindRollerActuator implements Actuator {
    private String actuatorName;
    private SimHardwareAct simHardwareAct;

    /**
     * Constructor for BlindRollerActuator
     * @param actuatorName Name of the actuator
     * @param simHardwareAct
     */
    public BlindRollerActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid name for Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
    }

    /**
     * Validates the parameters for creating a new actuator
     * @param name
     * @param simHardwareAct
     */
    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    /**
     * Returns the name of the actuator
     * @return actuatorName
     */
    public String getName() {
        return this.actuatorName;
    }

    /**
     * Executes the command for the actuator
     * @param position Position of the roller
     * @return boolean According to the result of the execution
     */
    public boolean executeRollerCommand(int position) {
        if (!validateCommand(position)) {
            return false;
        }
        return simHardwareAct.executeIntegerCommandSim(position);
    }

    /**
     * Validates the command for the actuator
     * @param position To be validated
     * @return
     */
    private boolean validateCommand(int position) {
        return position >= 0 && position <= 100;
    }
}
