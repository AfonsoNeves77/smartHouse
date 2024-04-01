package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.dto.SensorTypeDTO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeMapper {

    /**
     * Converts a list of SensorType objects to a list of SensorTypeDTO objects by calling the convertSensorTypeToDTO
     * private method.
     * @param sensorTypeList  List of SensorType objects.
     * @return  List of SensorTypeDTO objects.
     */
    public static List<SensorTypeDTO> domainToDTO(List<SensorType> sensorTypeList){
        List<SensorTypeDTO> sensorTypeDTOList = new ArrayList<>();
        for(SensorType sensorType : sensorTypeList){
            SensorTypeDTO sensorTypeDTO = convertSensorTypeToDTO(sensorType);
            sensorTypeDTOList.add(sensorTypeDTO);
        };
        return sensorTypeDTOList;
    }

    /**
     * Creates a SensorTypeIDVO object from a SensorTypeDTO object by retrieving the sensorTypeID.
     * @param sensorTypeDTO  SensorTypeDTO object.
     * @return  SensorTypeIDVO object.
     */
    public static SensorTypeIDVO createSensorTypeIDVO(SensorTypeDTO sensorTypeDTO){
        return new SensorTypeIDVO(sensorTypeDTO.getSensorTypeID());
    }


    /**
     * Converts a SensorType object to a SensorTypeDTO object.
     * @param sensorType  SensorType object.
     * @return  SensorTypeDTO object.
     */
    private static SensorTypeDTO convertSensorTypeToDTO(SensorType sensorType){
        String sensorTypeID = sensorType.getId().getID();
        String unit = sensorType.getUnit().getUnit();
        return new SensorTypeDTO(sensorTypeID, unit);
    }
}
