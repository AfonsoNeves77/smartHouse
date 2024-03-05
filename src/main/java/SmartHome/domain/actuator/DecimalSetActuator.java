package SmartHome.domain.actuator;

public class DecimalSetActuator implements Actuator{

    private String actuatorName;
    private double value;
    private SimHardwareAct simHardwareAct;
    private double upperLimit;
    private double lowerLimit;
    private final int precision = 3;

    public DecimalSetActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid parameters for Decimal Range Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
        this.value=0;
    }

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    public boolean executeCommand(double newValue) {
        if (!validateValue(newValue)){
            return false;
        }
        boolean executionResult = simHardwareAct.executeDecimalCommandSim(newValue);
        if (executionResult) {
            // Adjusts the value to the predefined precision
            this.value = Math.round(newValue * Math.pow(10, precision)) / Math.pow(10, precision);
        }
        return executionResult;
    }

    private boolean validateValue(double value) {
        if (value < lowerLimit || value > upperLimit){
            return false;
        }
        return true;
    }

    @Override
    public String getName() {
        return this.actuatorName;
    }

    public double getValue(){
        return this.value;
    }

    public void setLimits(double lowerLimit, double upperLimit){
        if (upperLimit<lowerLimit){
            throw new IllegalArgumentException("Upper Limit has to be Higher or Equal than Lower Limit");
        }
        this.upperLimit=upperLimit;
        this.lowerLimit=lowerLimit;
        this.value=lowerLimit;
    }
}



