package SmartHome.domain.actuator;

public class SimHardwareAct {

    /**
     * This method is used to execute a command on the simulated hardware.
     * @param commandType The type of command to be executed.
     * @return True if the command was executed successfully, false otherwise.
     */

    public boolean executeCommandSim(String commandType) {
        return true;
    }

    /**
     * This method is used to execute a command on the simulated hardware.
     * @param position The position to be set.
     * @return True if the command was executed successfully, false otherwise.
     */

    public boolean executeIntegerCommandSim(int position) {
        return true;
    }

    /**
     * This method is used to execute a command on the simulated hardware.
     * @param newValue The value to be set.
     * @return True if the command was executed successfully, false otherwise.
     */

    public boolean executeDecimalCommandSim(double newValue){
        return true;
    }
}
