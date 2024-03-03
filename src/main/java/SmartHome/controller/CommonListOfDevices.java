package SmartHome.controller;


import SmartHome.domain.House;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceDTOMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CommonListOfDevices {
    private House house;

    private LinkedHashMap<DeviceDTO, Device> deviceDTOAndDeviceMap = new LinkedHashMap<>();

    public CommonListOfDevices(House house){
        this.house = house;
    }

    public List<DeviceDTO> getListOfDevices(Room room){
        refreshMap(room);
        return truncateList(deviceDTOAndDeviceMap);
    }

    private List<DeviceDTO> truncateList(Map<DeviceDTO, Device> deviceDTOAndDeviceMap){
        return deviceDTOAndDeviceMap.keySet().stream().toList();
    }

    public Device getDeviceByName(String deviceName,Room room) {
        refreshMap(room);
        for(Map.Entry<DeviceDTO, Device> entry : deviceDTOAndDeviceMap.entrySet()){
            if(entry.getKey().getName().equals(deviceName)){
                return entry.getValue();
            }
        }
        return null;
    }

    private void refreshMap(Room room){
        List<Device> deviceList = room.getListOfDevices();
        deviceDTOAndDeviceMap = DeviceDTOMapper.getDeviceDTOList(deviceList);
    }


}
