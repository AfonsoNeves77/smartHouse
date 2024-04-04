package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.dto.ActuatorTypeDTO;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

import java.util.ArrayList;
import java.util.List;

public class ActuatorTypeMapper {

    private static List<ActuatorTypeDTO> actuatorTypeDTOList = new ArrayList<ActuatorTypeDTO>();


    /**
     * Maps a list of ActuatorType to a list of ActuatorTypeDTO
     *
     * @param actuatorTypeList list of ActuatorType
     * @return list of ActuatorTypeDTO
     */
    public static List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypeList) {
        for (ActuatorType actuatorType : actuatorTypeList) {
            actuatorTypeDTOList.add(new ActuatorTypeDTO(actuatorType.getId().getID()));
        }
        return actuatorTypeDTOList;
    }

    /**
     * Creates an ActuatorTypeIDVO from an ActuatorTypeDTO
     *
     * @param actuatorTypeDTO ActuatorTypeDTO
     * @return ActuatorTypeIDVO
     * @throws IllegalArgumentException if ActuatorTypeDTO is null
     */
    public static ActuatorTypeIDVO createActuatorTypeIDVO(ActuatorTypeDTO actuatorTypeDTO) {
        if (actuatorTypeDTO == null) {
            throw new IllegalArgumentException("ActuatorTypeDTO cannot be null");
        }
        String actuatorType = actuatorTypeDTO.getActuatorTypeID();
        return new ActuatorTypeIDVO(actuatorType);
    }

    public static ActuatorTypeIDVO createActuatorTypeIDVOFromString(String actuatorType){
        return new ActuatorTypeIDVO(actuatorType);
    }
}
