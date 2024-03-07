package SmartHome.domain.actuator;

public class DecimalSetActuator implements Actuator{

    private String actuatorName;
    private double value;
    private SimHardwareAct simHardwareAct;
    private Double upperLimit;
    private Double lowerLimit;
    private Integer decimalPrecision;

    public DecimalSetActuator(String actuatorName, SimHardwareAct simHardwareAct) throws InstantiationException {
        if (!validateParams(actuatorName, simHardwareAct)) {
            throw new InstantiationException("Invalid parameters for Decimal Range Actuator.");
        }
        this.actuatorName = actuatorName;
        this.simHardwareAct = simHardwareAct;
    }

    private boolean validateParams(String name, SimHardwareAct simHardwareAct) {
        return name != null && !name.trim().isEmpty() && simHardwareAct!=null;
    }

    public boolean executeCommand(double newValue) {
        if(upperLimit == null && lowerLimit == null && decimalPrecision == null){
            return false;
        }
        if (!isValueWithinLimits(newValue) || !hasOneDecimalPlaceOrNone(newValue)){
            return false;
        }
        boolean executionResult = simHardwareAct.executeDecimalCommandSim(newValue);
        if (executionResult) {
            this.value = newValue;
        }
        return executionResult;
    }

    private boolean isValueWithinLimits(double value) {
        if (value < lowerLimit || value > upperLimit){
            return false;
        }
        return true;
    }

    private boolean hasOneDecimalPlaceOrNone(double newValue) {
        double decimalPart = newValue - Math.floor(newValue);
        double decimalPartMultiplied = decimalPart * this.decimalPrecision;
        return decimalPartMultiplied == Math.floor(decimalPartMultiplied);
    }

    @Override
    public String getName() {
        return this.actuatorName;
    }

    public double getValue(){
        return this.value;
    }

    public void setLimits(Double lowerLimit, Double upperLimit,Integer decimalPrecision){
        if (upperLimit<lowerLimit){
            throw new IllegalArgumentException("Upper Limit has to be Higher or Equal than Lower Limit");
        }
        this.upperLimit=upperLimit;
        this.lowerLimit=lowerLimit;
        this.decimalPrecision=decimalPrecision;
        this.value=lowerLimit;
    }
}



