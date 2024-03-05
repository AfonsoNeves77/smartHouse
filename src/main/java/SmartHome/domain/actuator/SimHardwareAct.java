package SmartHome.domain.actuator;

public class SimHardwareAct {
    public boolean executeCommandSim(String commandType) {
        return true;
    }

    public boolean executeIntegerCommandSim(int position) {
        return true;
    }
    public boolean executeDecimalCommandSim(double newValue){
        return true;
    }
}
