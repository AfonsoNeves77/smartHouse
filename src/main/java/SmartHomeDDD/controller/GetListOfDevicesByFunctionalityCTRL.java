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
import java.util.Map;

public class GetListOfDevicesByFunctionalityCTRL {
    private final SensorService sensorService;
    private final ActuatorService actuatorService;
    private final DeviceService deviceService;

    public GetListOfDevicesByFunctionalityCTRL(SensorService sensorService,ActuatorService actuatorService,DeviceService deviceService){
        this.sensorService = sensorService;
        this.actuatorService = actuatorService;
        this.deviceService = deviceService;
    }

    public LinkedHashMap<String,List<DeviceDTO>> getListOfDevicesByFunctionality(){
        LinkedHashMap<String, List<DeviceIDVO>> sensorMap = getListOfDeviceIDBySensorFunctionality();
        LinkedHashMap<String, List<DeviceIDVO>> sensorAndActuatorMap = getListOfDeviceIDByActuatorFunctionality(sensorMap);
        LinkedHashMap<String, List<Device>> strToDeviceMap = convertDeviceIDToDevice(sensorAndActuatorMap);
        return DeviceMapper.domainToDTO(strToDeviceMap);
    }

    private LinkedHashMap<String, List<DeviceIDVO>> getListOfDeviceIDBySensorFunctionality(){
        return this.sensorService.getListOfDeviceIDsByFunctionality();
    }

    private LinkedHashMap<String, List<DeviceIDVO>> getListOfDeviceIDByActuatorFunctionality(LinkedHashMap<String, List<DeviceIDVO>> previousMap){
        return this.actuatorService.getListOfDeviceIDsByFunctionality(previousMap);
    }

    private LinkedHashMap<String, List<Device>> convertDeviceIDToDevice (LinkedHashMap<String, List<DeviceIDVO>> idVOMap){
        return this.deviceService.getDevices(idVOMap);
    }
}

