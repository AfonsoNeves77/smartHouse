package SmartHome.domain.actuator;

public class BlindRollerActuator {
    private String actuatorName;
    private SimHardwareAct simHardwareAct;

    public BlindRollerActuator(String actuatorName, SimHardwareAct simHardwareAct) {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new IllegalArgumentException("Invalid name for Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
    }

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    public String getName() {
        return this.actuatorName;
    }

    public boolean executeRollerCommand(int position) {
        if (!validateCommand(position)) {
            return false;
        }
        return simHardwareAct.executeRollerCommandSim(position);
    }

    private boolean validateCommand(int position) {
        return position >= 0 && position <= 100;
    }
}
