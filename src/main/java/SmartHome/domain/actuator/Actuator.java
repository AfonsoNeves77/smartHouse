package SmartHome.domain.actuator;

public interface Actuator {
    String getName();
    boolean executeCommand(String commandType, SimHardwareAct simHardwareAct);
}
