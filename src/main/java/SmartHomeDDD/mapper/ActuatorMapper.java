package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.dto.ActuatorDTO;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Mapper class for Actuator
 * It contains methods to create ActuatorIDVO, ActuatorNameVO, ActuatorTypeIDVO and DeviceIDVO objects from ActuatorDTO objects.
 * It also contains a method to convert a list of domain Actuator objects to a list of ActuatorDTO objects.
 */

public class ActuatorMapper {

    /**
     * Method to create an ActuatorIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorIDVO object
     */

    public static ActuatorIDVO createActuatorIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException("Invalid DTO, ActuatorDTO cannot be null");
        } else {
            UUID actuatorID = UUID.fromString(actuatorDTO.getActuatorID());
            return new ActuatorIDVO(actuatorID);
        }
    }

    /**
     * Method to create an ActuatorNameVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return ActuatorNameVO object
     */

    public static ActuatorNameVO createActuatorNameVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException("Invalid DTO, ActuatorDTO cannot be null");
        } else {
            return new ActuatorNameVO(actuatorDTO.getActuatorName());
        }
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
            throw new IllegalArgumentException("Invalid DTO, ActuatorDTO cannot be null");
        } else {
            return new ActuatorTypeIDVO(actuatorDTO.getActuatorType());
        }
    }

    /**
     * Method to create a DeviceIDVO object from an ActuatorDTO object.
     * It throws an IllegalArgumentException if the ActuatorDTO object is null.
     *
     * @param actuatorDTO ActuatorDTO object
     * @return DeviceIDVO object
     */

    public static DeviceIDVO createDeviceIDVO(ActuatorDTO actuatorDTO) {
        if (actuatorDTO == null) {
            throw new IllegalArgumentException("Invalid DTO, ActuatorDTO cannot be null");
        } else {
            UUID deviceID = UUID.fromString(actuatorDTO.getDeviceID());
            return new DeviceIDVO(deviceID);
        }
    }

    /**
     * Method that converts a list of domain Actuator objects to a list of ActuatorDTO objects.
     *
     * @param actuators List of Actuator objects
     * @return List of ActuatorDTO objects
     */

    public static List<ActuatorDTO> domainToDTO(List<Actuator> actuators) {
        List<ActuatorDTO> actuatorDTOList = new ArrayList<>();
        for (Actuator actuator : actuators) {
            ActuatorDTO actuatorDTO = convertActuatorFromDomainToDTO(actuator);
            actuatorDTOList.add(actuatorDTO);
        }
        return actuatorDTOList;
    }

    /**
     * Method that converts a domain Actuator object to an ActuatorDTO object.
     *
     * @param actuator Actuator object
     * @return ActuatorDTO object
     */

    private static ActuatorDTO convertActuatorFromDomainToDTO(Actuator actuator) {
        String actuatorID = actuator.getId().getID();
        String actuatorName = actuator.getActuatorName().getValue();
        String actuatorType = actuator.getActuatorTypeID().getID();
        String deviceID = actuator.getDeviceID().getID();
        return new ActuatorDTO(actuatorID, actuatorName, actuatorType, deviceID);
    }
}