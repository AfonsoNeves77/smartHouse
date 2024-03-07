package SmartHome.domain.actuator;

public class DecimalSetActuator implements Actuator{

    private String actuatorName;
    private double value;
    private SimHardwareAct simHardwareAct;
    private Double upperLimit;
    private Double lowerLimit;
    private Integer decimalPrecision;


    /**
     * Contructor for a Decimal Set Actuator that sets a decimal value in the range defined by lower limit, upper
     * limit and with a predefined precision. The example of an A/C Unit was taken into account, in which the user
     * defines a value with a certain precision (eg.: 21.5 Cº - precision of 10 decimal points) within certain
     * limits (eg. an A/C Unit cannot be set to lower than 15Cº or higher than 32Cº)
     * @param actuatorName represents the name of the actuator
     * @param simHardwareAct represents a simulation of the hardware upon which this actuator will have an effect
     * @throws InstantiationException represents an exception for the times when the parameters are null
     */
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

    /**
     * This method executes the command from the user (a value in this case) by sending it to a simulated
     * hardware, but first confirms that the value is within the defined limits, and also that the value is within
     * the predifined precision, and that all limits and precision are not null (=were already initialized).
     * @param newValue represents the value that will be defined by the user for the actuator to execute
     * @return boolean return confirms that the execution was correctly made
     */
    public boolean executeCommand(double newValue) {
        if(upperLimit == null && lowerLimit == null && decimalPrecision == null){
            return false;
        }
        if (!isValueWithinLimits(newValue) || !hasTheCorrectDecimalPrecision(newValue)){
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


    private boolean hasTheCorrectDecimalPrecision(double newValue) {
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

    /**
     * This method defines the limits and precision upon which the user value can be set to. It also verifies for the
     * fact that the upper limit has to be higher or the same compared to the lower limit.
     * @param lowerLimit defines the lower limit that the user input can go to.
     * @param upperLimit defines the upper limit that the user input can go to.
     * @param decimalPrecision defines the precision (the decimal points) allowed for the user value input
     */
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



