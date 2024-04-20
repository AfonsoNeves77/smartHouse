package smarthome.domain.actuator;

import smarthome.domain.actuator.externalservices.ActuatorExternalService;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

import java.util.UUID;


/**
 * Represents a switch actuator that switches a
 load ON(true)/OFF(false).
 * This class implements both the DomainEntity and Actuator interfaces.
 */
public class SwitchActuator implements Actuator {

    private final ActuatorIDVO actuatorID;
    private ActuatorNameVO actuatorName;
    private final ActuatorTypeIDVO actuatorTypeID;
    private final DeviceIDVO deviceIDVO;

    /**
     * Constructs a new SwitchActuator with the provided actuatorName, actuatorTypeID, and deviceIDVO.
     * Throws an IllegalArgumentException if any of the provided parameters is null.
     * Internally it generates it´s ID using a UUID and injecting that identifier in a ActuatorIDVO object.
     *
     * @param actuatorName   The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO     The ID of the device associated with the actuator.
     * @throws IllegalArgumentException If any of the provided parameters is null.
     */
    public SwitchActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        if (!parametersAreValid(actuatorName, actuatorTypeID, deviceIDVO)) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceIDVO;
    }

    /**
     * Constructs a new SwitchActuator object with the specified actuatorID, actuator name, type, device ID and the settings interface.
     * the input parameters were extracted from a DataModel of an existing actuator. Since the DataModel is created from an existing actuator,
     * it is considered that all the parameters are valid, since they have been validated before persisting the actuator.
     */
    public SwitchActuator(ActuatorIDVO actuatorID, ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        this.actuatorID = actuatorID;
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceIDVO;
    }

    /**
     * Checks if the provided actuator parameters are valid.
     *
     * @param actuatorName   The name of the actuator.
     * @param actuatorTypeID The type ID of the actuator.
     * @param deviceIDVO     The ID of the device associated with the actuator.
     * @return true if all parameters are not null, false otherwise.
     */
    private boolean parametersAreValid(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceIDVO) {
        return actuatorName != null && actuatorTypeID != null && deviceIDVO != null;
    }

    /**
     * Switches the load using the provided SimHardwareAct.
     * SimHardwareAct is an abstraction that represents the external entity or "piece" of hardware this SwitchActuator is going
     * to act upon.
     * This function calls a method in SimHardwareAct class that executes the load switching command, this operation returns a boolean
     * considering if the command was (or not) successfully executed. switchLoad() will return the output of that function.
     *
     * @param simHardwareAct The SimHardwareAct to use for executing the command.
     * @return true if the command was executed successfully, false otherwise.
     */
    public boolean switchLoad(ActuatorExternalService simHardwareAct) {
        return simHardwareAct.executeCommandSim();
    }

    /**
     * Retrieves the ID of the actuator.
     *
     * @return The ActuatorIDVO representing the ID of the actuator.
     */
    @Override
    public ActuatorIDVO getId() {
        return this.actuatorID;
    }

    /**
     * Simple getter method.
     * @return ActuatorTypeIDVO object
     */
    @Override
    public ActuatorTypeIDVO getActuatorTypeID() {
        return this.actuatorTypeID;
    }

    /**
     * Simple getter method.
     * @return DeviceIDVO object
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceIDVO;
    }

    /**
     * Simple getter method.
     * @return ActuatorName object
     */
    @Override
    public ActuatorNameVO getActuatorName() {
        return this.actuatorName;
    }

    /**
     * Method to retrieve the lower limit of the actuator as String.
     *
     * @return null since the SwitchActuator does not have a lower limit.
     */
    public String getLowerLimit() {
        return null;
    }

    /**
     * Method to retrieve the upper limit of the actuator as String.
     *
     * @return null since the SwitchActuator does not have an upper limit.
     */
    public String getUpperLimit() {
        return null;
    }

    /**
     * Method to retrieve the precision of the actuator as String.
     *
     * @return null since the SwitchActuator does not have a precision.
     */
    public String getPrecision() {
        return null;
    }
}

