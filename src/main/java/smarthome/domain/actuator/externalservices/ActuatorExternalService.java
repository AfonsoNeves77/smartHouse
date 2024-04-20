package smarthome.domain.actuator.externalservices;

public interface ActuatorExternalService {
    public boolean executeCommandSim();
    public boolean executeIntegerCommandSim(int position);
    boolean executeDecimalCommand(double value);
}
