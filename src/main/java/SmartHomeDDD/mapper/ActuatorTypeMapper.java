package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.dto.ActuatorTypeDTO;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

import java.util.ArrayList;
import java.util.List;

public class ActuatorTypeMapper {

    private static List<ActuatorTypeDTO> actuatorTypeDTOList = new ArrayList<ActuatorTypeDTO>();


    public static List<ActuatorTypeDTO> domainToDTO(List<ActuatorType> actuatorTypeList) {
        for (ActuatorType actuatorType : actuatorTypeList) {
            actuatorTypeDTOList.add(new ActuatorTypeDTO(actuatorType.getActuatorType()));
        }
        return actuatorTypeDTOList;
    }

    public static ActuatorTypeIDVO createActuatorTypeIDVO(String actuatorType) {
        return new ActuatorTypeIDVO(actuatorType);
    }
}
