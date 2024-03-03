package SmartHome.dto;

import SmartHome.domain.device.Device;

import java.util.*;

public class DeviceDTOMapper {
    public static DeviceDTO domainToDTO(Device device) {
        return new DeviceDTO(device.getDeviceName(),
                device.getDeviceModel(),
                device.getDeviceLocation(),
                device.getStatus());
    }

    public static ArrayList<DeviceDTO> domainToDTO(List<Device> deviceList) {
        ArrayList<DeviceDTO> newList = new ArrayList<>();
        for(Device device : deviceList){
            DeviceDTO dto = domainToDTO(device);
            newList.add(dto);
        }
        return newList;
    }

    public static LinkedHashMap<DeviceDTO, Device> getDeviceDTOList(List<Device> listOfDevices) {
        LinkedHashMap<DeviceDTO, Device> deviceDTOAndDeviceMap = new LinkedHashMap<>();
        listOfDevices.forEach(device ->
                {
                    DeviceDTO deviceDTO = DeviceDTOMapper.domainToDTO(device);
                    deviceDTOAndDeviceMap.put(deviceDTO, device);
                }
        );
        return deviceDTOAndDeviceMap;
    }

    public static Map<String,ArrayList<DeviceDTO>> domainMapToDTO(Map<String,ArrayList<Device>> map){
        Map<String,ArrayList<DeviceDTO>> dtoMap = new HashMap<>();

        for(Map.Entry<String,ArrayList<Device>> entry : map.entrySet()){
            ArrayList<DeviceDTO> dtoList = domainToDTO(entry.getValue());
            dtoMap.put(entry.getKey(),dtoList);
        }
        return dtoMap;
    }
}
