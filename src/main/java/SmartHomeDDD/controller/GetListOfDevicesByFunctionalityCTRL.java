package SmartHomeDDD.controller;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import java.util.LinkedHashMap;
import java.util.List;

public class GetListOfDevicesByFunctionalityCTRL {
    private final SensorService sensorService;
    private final ActuatorService actuatorService;
    private final DeviceService deviceService;

    /**
     * Constructor for GetListOfDevicesByFunctionality controller. It receives a sensor, actuator and device services,
     * encapsulating them.
     * @param sensorService Sensor Service
     * @param actuatorService Actuator Service
     * @param deviceService Device Service
     */
    public GetListOfDevicesByFunctionalityCTRL(SensorService sensorService,ActuatorService actuatorService,DeviceService deviceService){
        this.sensorService = sensorService;
        this.actuatorService = actuatorService;
        this.deviceService = deviceService;
    }

    /**
     * This method retrieves a hashmap pertaining to Devices organized by their functionalities. The hashmap will utilize
     * the types of sensors and actuators as keys represented in string format. Initially, it establishes communication
     * with the sensor service to obtain a mapping of type/DeviceIDVO, associating each DeviceID
     * with the sensors linked to it. Subsequently, a similar procedure is followed with the actuators. The resultant
     * mapping is then transmitted to the device service for the replacement of DeviceIDVO objects with Device objects.
     * Following this, the method invokes 'domainToDto' utilizing the 'DeviceMapper' to substitute all occurrences of
     * Device with DeviceDTO.
     * @return LinkedHashMap<String,List<DeviceDTO>>
     */
    public LinkedHashMap<String,List<DeviceDTO>> getListOfDevicesByFunctionality(){
        LinkedHashMap<String, List<DeviceIDVO>> sensorMap = getListOfDeviceIDBySensorFunctionality();
        LinkedHashMap<String, List<DeviceIDVO>> sensorAndActuatorMap = getListOfDeviceIDByActuatorFunctionality(sensorMap);
        LinkedHashMap<String, List<Device>> strToDeviceMap = convertDeviceIDToDevice(sensorAndActuatorMap);
        return DeviceMapper.domainToDTO(strToDeviceMap);
    }

    /**
     * This method returns a linked hashmap containing string (related to sensor type) with the value List<DeviceIDVO>
     * @return LinkedHashMap<String,List<DeviceIDVO>>
     */
    private LinkedHashMap<String, List<DeviceIDVO>> getListOfDeviceIDBySensorFunctionality(){
        return this.sensorService.getListOfDeviceIDsByFunctionality();
    }

    /**
     * This method receives a linked hashmap, and continues populating it with types of actuators vs matching DeviceIDVOs.
     * @return LinkedHashMap<String,List<DeviceIDVO>>
     */
    private LinkedHashMap<String, List<DeviceIDVO>> getListOfDeviceIDByActuatorFunctionality(LinkedHashMap<String, List<DeviceIDVO>> previousMap){
        return this.actuatorService.getListOfDeviceIDsByFunctionality(previousMap);
    }

    /**
     * This method receives a linked hashmap and converts all instances of DeviceIDVO into Device objects.
     * @return LinkedHashMap<String,List<Device>>
     */
    private LinkedHashMap<String, List<Device>> convertDeviceIDToDevice (LinkedHashMap<String, List<DeviceIDVO>> idVOMap){
        return this.deviceService.getDevices(idVOMap);
    }
}

