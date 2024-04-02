package SmartHomeDDD.domain.actuator;

import SmartHomeDDD.SimHardwareAct;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import java.util.UUID;

public class IntSetRangeActuator implements DomainEntity, Actuator {
    private ActuatorNameVO actuatorName;
    private final ActuatorTypeIDVO actuatorType;
    private final DeviceIDVO deviceID;
    private final ActuatorIDVO actuatorID;
    private Integer lowerLimit;
    private Integer upperLimit;
    private int value;

    /**
     * Constructs a new IntSetRangeActuator object with the specified actuator name, type, and device ID.
     * Validates the provided parameters to ensure they are not null.
     * Assigns a unique actuator ID using a randomly generated UUID.
     */
    public IntSetRangeActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorType, DeviceIDVO deviceID){
        if(areParamsNull(actuatorName, actuatorType, deviceID)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        this.actuatorName = actuatorName;
        this.actuatorType = actuatorType;
        this.deviceID = deviceID;
        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
    }

    /**
     * This method sets the lower and upper limits for the actuator.
     * The lower limit must be less than the upper limit, otherwise throws an IllegalArgumentException.
     * The value of the actuator is then set to the lower limit by default.
     * @param lowerLimit the lower limit for the actuator
     * @param upperLimit the upper limit for the actuator
     * @return true if the limits are successfully set, false otherwise
     */
    public boolean setLimits(int lowerLimit, int upperLimit){
        if(lowerLimit >= upperLimit){
            throw new IllegalArgumentException("Upper limit can't be less than or equal to lower limit.");
        }
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.value = lowerLimit;
        return true;
    }

    /**
     * This method executes the command on the actuator.
     * To ensure the upper and lower limits have been already set, hence not null, the method throws an IllegalArgumentException if either are null.
     * However, being both null by default when the actuator is created, verifying if the upperLimit is null is in this case redundant.
     * It was chosen to keep the verification for consistency and to avoid potential future changes that could make the upperLimit null.
     * It then verifies if the provided value is within the limits, by use of a private method, and throws an IllegalArgumentException if not.
     * Furthermore, it verifies if the simHardwareAct is null and throws an IllegalArgumentException if so.
     * The simHardwareAct is then used to execute the command with the provided value and the value of the actuator is set, altering the object's state to its last definition.
     * The method returns true if the command is successfully executed, false otherwise.
     * @param simHardwareAct the hardware actuator to execute the command on
     * @param value the value to set the actuator to
     * @return true if the command is successfully executed, false otherwise
     */
    public boolean executeCommand(SimHardwareAct simHardwareAct, int value) {
        if (lowerLimit == null || upperLimit == null || !validateValue(value)) {
            throw new IllegalArgumentException("Limits not set or value out of range");
        } else {
            if (simHardwareAct == null) {
                throw new IllegalArgumentException("SimHardwareAct cannot be null");
            }
            boolean executionResult = simHardwareAct.executeIntegerCommandSim(value);
            if (executionResult) {
                this.value = value;
            }
            return executionResult;
        }
    }

    /**
     * This method verifies if a given value is within the range of the pre-established limits for the present state of the actuator.
     * @return true if the value is within the limits, false otherwise
     */
    private boolean validateValue(int value){
        if(value < lowerLimit || value > upperLimit){
            return false;
        }
        return true;
    }

    /**
     * This method gets the actuator's ID.
     * @return the actuator ID object (ActuatorIDVO)
     */
    @Override
    public ActuatorIDVO getId() {
        return this.actuatorID;
    }

    /**
     * This method verifies if a given set of parameters are null.
     * @return true if any of the parameters are null, false otherwise
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * Simple getter method.
     * @return ActuatorTypeIDVO object
     */
    @Override
    public ActuatorTypeIDVO getActuatorTypeID() {
        return this.actuatorType;
    }

    /**
     * Simple getter method.
     * @return DeviceIDVO object
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceID;
    }

    /**
     * Simple getter method.
     * @return ActuatorName object
     */
    @Override
    public ActuatorNameVO getActuatorName() {
        return this.actuatorName;
    }
}
