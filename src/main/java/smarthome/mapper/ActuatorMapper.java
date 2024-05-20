package smarthome.mapper;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.vo.actuatorvo.*;
import smarthome.mapper.dto.ActuatorDTO;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

import java.util.UUID;

/**
 * Mapper class for Actuator
 * It contains methods to create ActuatorIDVO, ActuatorNameVO, ActuatorTypeIDVO and DeviceIDVO objects from ActuatorDTO objects.
 * It also contains a method to convert a list of domain Actuator objects to a list of ActuatorDTO objects.
 */

public class ActuatorMapper {

    private static final String ERRORMESSAGE = "ActuatorDTO cannot be null.";

    /**
     * Method to create an ActuatorNameVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorNameVO object
     */

    public static ActuatorNameVO createActuatorNameVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            return new ActuatorNameVO(actuatorDTO.getActuatorName());
        }
    }

    public static Settings createSettingsVO(ActuatorDTO actuatorDTO) {
        String lowerLimit = actuatorDTO.getLowerLimit();
        String upperLimit = actuatorDTO.getUpperLimit();
        String precision = actuatorDTO.getPrecision();
        try {
            if (precision == null && lowerLimit != null && upperLimit != null) {
                return new IntegerSettingsVO(lowerLimit, upperLimit);
            }
            if (precision != null && lowerLimit != null && upperLimit != null) {
                return new DecimalSettingsVO(lowerLimit, upperLimit, precision);
            }
        } catch (IllegalArgumentException e) {
            return null;
        }
        return null;
    }

    /**
     * Method to create an ActuatorTypeIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorTypeIDVO object
     */

    public static ActuatorTypeIDVO createActuatorTypeIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            return new ActuatorTypeIDVO(actuatorDTO.getActuatorType());
        }
    }

        /**
     * Method to create a DeviceIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null or if the deviceID attribute is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return DeviceIDVO object
     */

    public static DeviceIDVO createDeviceIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException(ERRORMESSAGE);
        } else {
            if(actuatorDTO.getDeviceID() == null) {
                throw new IllegalArgumentException("Device ID cannot be null");
            }
            UUID deviceID = UUID.fromString(actuatorDTO.getDeviceID());
            return new DeviceIDVO(deviceID);
        }
    }


    /**
     * Method to convert a domain Actuator object to a DTO ActuatorDTO object.
     * It throws an IllegalArgumentException if the Actuator object is null.
     *
     * @param actuator Actuator object
     * @return ActuatorDTO object
     */
    public static ActuatorDTO domainToDTO(Actuator actuator) {
        if (actuator == null) {
            throw new IllegalArgumentException("Invalid actuator, DTO cannot be created");
        }
        return ActuatorDTO.builder()
                .actuatorId(actuator.getId().getID())
                .actuatorName(actuator.getActuatorName().getValue())
                .actuatorType(actuator.getActuatorTypeID().getID())
                .deviceID(actuator.getDeviceID().getID())
                .lowerLimit(actuator.getLowerLimit())
                .upperLimit(actuator.getUpperLimit())
                .precision(actuator.getPrecision())
                .build();
    }

    /**
     * Method to create an ActuatorIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     * @param actuatorId String
     * @return ActuatorIDVO object
     */
    public static ActuatorIDVO createActuatorIDVO(String actuatorId) {
        if(actuatorId == null){
            throw new IllegalArgumentException("ActuatorID cannot be null");
        }
        UUID id = UUID.fromString(actuatorId);
        return new ActuatorIDVO(id);
    }
}