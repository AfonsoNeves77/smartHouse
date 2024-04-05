package SmartHomeDDD.controller;

import SmartHomeDDD.domain.sensor.Sensor;
import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.dto.SensorDTO;
import SmartHomeDDD.dto.SensorTypeDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.mapper.SensorMapper;
import SmartHomeDDD.mapper.SensorTypeMapper;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.services.SensorTypeService;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.List;

public class AddSensorToDeviceCTRL {

    private SensorTypeService sensorTypeService;
    private SensorService sensorService;
    private DeviceService deviceService;


    /**
     * Constructor for AddSensorToDeviceCTRL. It takes a sensorTypeService, sensorService and deviceService as
     * parameters, encapsulating them.
     * @param sensorTypeService SensorTypeService object
     * @param sensorService SensorService object
     * @param deviceService DeviceService object
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public AddSensorToDeviceCTRL(SensorTypeService sensorTypeService, SensorService sensorService, DeviceService deviceService) {
        if(sensorTypeService == null || sensorService == null || deviceService == null){
            throw new IllegalArgumentException("Invalid service");
        }
        this.sensorTypeService = sensorTypeService;
        this.sensorService = sensorService;
        this.deviceService = deviceService;
    }


    /**
     * Obtains a list of sensor types by communicating with the encapsulated sensorTypeService.
     * It then communicates with the SensorTypeMapper to translate the list of sensor types into a list of SensorTypeDTO.
     * @return List <SensorTypeDTO>
     */
    public List<SensorTypeDTO> getListOfSensorTypes(){
        List<SensorType> sensorTypeList = this.sensorTypeService.getListOfSensorTypes();
        return SensorTypeMapper.domainToDTO(sensorTypeList);
    }


    /**
     * Method that adds a sensor to an existing device. It takes a deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * It then communicates with the SensorMapper, DeviceMapper and SensorTypeMapper to create the necessary VO objects.
     * It then communicates with the sensorTypeService and deviceService to verify if the sensor type and device are valid.
     * If they are, it creates a new sensor and saves it.
     * @param deviceDTO DeviceDTO object
     * @param sensorTypeDTO SensorTypeDTO object
     * @param sensorDTO SensorDTO object
     * @return True if the sensor was added successfully, false otherwise.
     */
    public boolean addSensorToDevice(DeviceDTO deviceDTO, SensorTypeDTO sensorTypeDTO, SensorDTO sensorDTO) {
        SensorNameVO sensorNameVO = SensorMapper.createSensorNameVO(sensorDTO);
        SensorTypeIDVO sensorTypeIDVO = SensorTypeMapper.createSensorTypeIDVO(sensorTypeDTO);
        DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);
        Sensor newSensor;
        if (this.sensorTypeService.sensorTypeExists(sensorTypeIDVO) && this.deviceService.isActive(deviceIDVO)) {
            newSensor = sensorService.createSensor(sensorNameVO, deviceIDVO, sensorTypeIDVO);
        } else {
            return false;
        }
        return this.sensorService.saveSensor(newSensor);
    }
}
