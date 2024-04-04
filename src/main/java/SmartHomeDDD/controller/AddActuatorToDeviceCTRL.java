package SmartHomeDDD.controller;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.dto.ActuatorDTO;
import SmartHomeDDD.dto.ActuatorTypeDTO;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.mapper.ActuatorMapper;
import SmartHomeDDD.mapper.ActuatorTypeMapper;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.services.ActuatorTypeService;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.vo.Settings;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import java.util.List;

import static java.util.Objects.isNull;

public class AddActuatorToDeviceCTRL {

    private ActuatorService actuatorService;
    private ActuatorTypeService actuatorTypeService;
    private DeviceService deviceService;

    /**
     * Constructor for AddActuatorToDeviceCTRL
     *
     * @param actuatorService
     * @param actuatorTypeService
     * @param deviceService
     */
    public AddActuatorToDeviceCTRL(ActuatorService actuatorService, ActuatorTypeService actuatorTypeService, DeviceService deviceService) {
        if (isNull(actuatorService) || isNull(actuatorTypeService) || isNull(deviceService)) {
            throw new IllegalArgumentException("Invalid service");
        }
        this.actuatorService = actuatorService;
        this.actuatorTypeService = actuatorTypeService;
        this.deviceService = deviceService;
    }

    /**
     * Get a list of actuator types
     *
     * @return List of ActuatorTypeDTO
     */
    public List<ActuatorTypeDTO> getListOfActuatorTypes() {
        List<ActuatorType> actuatorTypes = this.actuatorTypeService.getListOfActuatorTypes();
        List<ActuatorTypeDTO> actuatorTypeDTOList = ActuatorTypeMapper.domainToDTO(actuatorTypes);
        return actuatorTypeDTOList;
    }

    /**
     * Add an actuator to a device
     *
     * @param actuatorDTO
     * @param actuatorTypeDTO
     * @param deviceDTO
     * @return boolean depending on the success of the operation
     */
    public boolean addActuatorToDevice(ActuatorDTO actuatorDTO, ActuatorTypeDTO actuatorTypeDTO, DeviceDTO deviceDTO) {
        ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTO);
        ActuatorTypeIDVO actuatorTypeIDVO = ActuatorTypeMapper.createActuatorTypeIDVO(actuatorTypeDTO);
        DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);
        Settings settings = ActuatorMapper.createSettingsVO(actuatorDTO);
        Actuator actuator;
        if (this.actuatorTypeService.actuatorTypeExists(actuatorTypeIDVO) && this.deviceService.isActive(deviceIDVO)) {
            actuator = this.actuatorService.createActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings);
        } else {
            return false;
        }
        return this.actuatorService.saveActuator(actuator);
    }
}
