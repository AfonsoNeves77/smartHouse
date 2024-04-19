package smarthome.domain.actuator;

import smarthome.domain.DomainID;
import smarthome.domain.actuator.externalservice.ActuatorExternalService;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.actuatorvo.DecimalSettingsVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

import java.util.UUID;

public class DecimalValueActuator implements Actuator {
    private final ActuatorIDVO actuatorID;
    private ActuatorNameVO actuatorName;
    private final ActuatorTypeIDVO actuatorTypeID;
    private final DeviceIDVO deviceIDVO;
    private final DecimalSettingsVO decimalSettings;


    /**
     * Constructor for the DecimalValueActuator.
     * @param actuatorName ActuatorName Value Object
     * @param actuatorTypeID ActuatorTypeID Value Object
     * @param deviceID DeviceID Value Object
     * @param settings Settings Value Object
     * @throws IllegalArgumentException If any of the parameters is invalid (null).
     */
    public DecimalValueActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID, Settings settings)  {
        if (!validParameters(actuatorName, actuatorTypeID, deviceID, settings)) {
            throw new IllegalArgumentException("Invalid actuator parameters");
        }
        try{
            this.decimalSettings = (DecimalSettingsVO) settings;
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Invalid settings type");
        }

        this.actuatorID = new ActuatorIDVO(UUID.randomUUID());
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceID;
    }

    /**
     * Constructs a new DecimalValueActuator object with the specified actuatorID, actuator name, type, device ID and the settings interface.
     * the input parameters were extracted from a DataModel of an existing actuator. Since the DataModel is created from an existing actuator,
     * it is considered that all the parameters are valid, since they have been validated before persisting the actuator.
     * Tries to cast the settings interface to an DecimalSettingsVO object.
     */
    public DecimalValueActuator(ActuatorIDVO actuatorId, ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID, Settings settings) {

        this.actuatorID = actuatorId;
        this.actuatorName = actuatorName;
        this.actuatorTypeID = actuatorTypeID;
        this.deviceIDVO = deviceID;
        this.decimalSettings = (DecimalSettingsVO) settings;
    }
    /**
     * Verifies if all the parameters for the actuator's instantiation are valid (not null).
     * @param parameters ActuatorNameVO, ActuatorTypeIDVO, DeviceIDVO and DecimalSettingsVO
     * @return True if all parameters are valid (not null), false otherwise.
     */
    private boolean validParameters(Object... parameters) {
        for(Object parameter : parameters){
            if(parameter == null)
                return false;
        }
        return true;
    }

    /**
     * Receives a double value to be set. The command is executed recurring to a connection to a simulation hardware.
     * The operation result can vary, depending on the scenario:
     * Hardware is invalid;
     * Value required to set is not within the actuator limits;
     * Value has a higher precision than allowed actuator precision (input value is rounded by the system);
     * Value is successfully set;
     * Value is not set due to some error with the hardware.
     * @param hardware Hardware simulation to use
     * @param value Value passed in the command to execute the operation
     * @return The result of the operation execution, as a message.
     */
    public String executeCommand(ActuatorExternalService hardware, double value) {
        if(hardware == null)
            return "Invalid hardware, could not execute command";

        if (!isValueWithinLimits(value))
            return "Value out of actuator limits, could not execute command";

        double precision = decimalSettings.getValue()[2];
        if(!validValuePrecision(value, precision)) {
            double newValue = round(value, countDecimalPlaces(precision));
            if(hardware.executeDecimalCommand(newValue))
                return "Value was rounded and set to " + newValue;
        }

        if(hardware.executeDecimalCommand(value))
            return "Value was set";
        return "Error: Value was not set";
    }

    /**
     * Verifies if the value passed for the executeCommand() method is valid (within actuator limits).
     * @param value Value passed in the command
     * @return True if value is within limits.
     */
    private boolean isValueWithinLimits(double value) {
        double lowerLim = decimalSettings.getValue()[0];
        double upperLim = decimalSettings.getValue()[1];
        return (value >= lowerLim && value <= upperLim);
    }

    /**
     * Rounds a double value for a certain number of decimal places.
     * @param value Value to be rounded
     * @param decimalPlaces Number of decimal places desired
     * @return The rounded number with the required amount of decimal places.
     */
    private static double round(double value, int decimalPlaces) {
        double scale = Math.pow(10, decimalPlaces);
        return Math.round(value * scale) / scale;
    }


    /**
     * Verifies if a given value respects the actuator precision.
     * @param value Value passed in the command
     * @param precision Precision defined for the actuator
     * @return True if the value precision granularity is valid, false otherwise (value precision is higher than the
     * actuator's defined precision)
     */
    private boolean validValuePrecision(double value, double precision) {
        return (countDecimalPlaces(value) <= countDecimalPlaces(precision));
    }

    /**
     * Calculates the number of decimal places of a double value.
     * In case the value introduced does not have decimal places, it implicitly has one decimal place (e.g. value
     * introduced: 5, it is interpreted as 5.0).
     * @param value Value to be analysed
     * @return The number of decimal places of the double value
     */
    private int countDecimalPlaces(double value) {
        String numberAsString = Double.toString(Math.abs(value));
        int decimalSeparatorIndex = numberAsString.indexOf('.');
        return numberAsString.length() - decimalSeparatorIndex - 1;
    }

    /**
     * Simple getter method to retrieve the Actuator ID.
     * @return The ActuatorIDVO of the actuator.
     */
    @Override
    public DomainID getId () {
        return this.actuatorID;
    }

    /**
     * Simple getter method to retrieve the Actuator Type ID.
     * @return The ActuatorTypeIDVO of the actuator.
     */
    @Override
    public ActuatorTypeIDVO getActuatorTypeID() {
        return this.actuatorTypeID;
    }

    /**
     * Simple getter method to retrieve the Device ID where the actuator is linked.
     * @return The DeviceIDVO where the actuator is connected to.
     */
    @Override
    public DeviceIDVO getDeviceID() {
        return this.deviceIDVO;
    }

    /**
     * Simple getter method to retrieve the actuator name.
     * @return The ActuatorNameVO of the actuator.
     */
    @Override
    public ActuatorNameVO getActuatorName() {
        return this.actuatorName;
    }

    /**
     * Method to retrieve the settings of the actuator.
     *
     * @return The DecimalSettingsVO of the actuator.
     */
    public DecimalSettingsVO getDecimalSettings() {
        return this.decimalSettings;
    }

    /**
     * Method to retrieve the lower limit of the actuator as String.
     *
     * @return The lower limit of the actuator as String.
     */
    public String getLowerLimit() {
        return decimalSettings.getValue()[0].toString();
    }

    /**
     * Method to retrieve the upper limit of the actuator as String.
     *
     * @return The upper limit of the actuator as String.
     */
    public String getUpperLimit() {
        return decimalSettings.getValue()[1].toString();
    }

    /**
     * Method to retrieve the precision of the actuator as String.
     *
     * @return The precision of the actuator as String.
     */
    public String getPrecision() {
        return decimalSettings.getValue()[2].toString();}
}



