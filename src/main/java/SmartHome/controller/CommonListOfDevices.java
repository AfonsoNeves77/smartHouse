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

    /**
     * Constructor for CommonListOfDevices, which takes a House to be initialized
     * @param house House Object linked to Common Class
     */
    public CommonListOfDevices(House house){
        this.house = house;
    }

    /**
     * Gathers all DeviceDTOs in a single List
     * @param room Room object to extract Devices from
     * @return List of DeviceDTO objects representing the devices within the requested room in the house
     */
    public List<DeviceDTO> getListOfDevices(Room room){
        refreshMap(room);
        return truncateList(deviceDTOAndDeviceMap);
    }

    /**
     * Gathers all DeviceDTOs in a single List
     * @param deviceDTOAndDeviceMap Map with Key-Value pairs of [DeviceDTOs - Device]
     * @return List of RoomDTOs
     */
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

    /**
     * Method to update this Class's Map containing Key-Value pairs of [DeviceDTOs ; Device], by requesting
     * the room's list of devices
     * @param room Room object to extract Devices from
     */
    private void refreshMap(Room room){
        List<Device> deviceList = room.getListOfDevices();
        deviceDTOAndDeviceMap = DeviceDTOMapper.getDeviceDTOList(deviceList);
    }


}
