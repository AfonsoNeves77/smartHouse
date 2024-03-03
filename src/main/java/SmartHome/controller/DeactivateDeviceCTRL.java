package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

import java.util.List;


public class DeactivateDeviceCTRL {
    private final House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private Room room;

    /**
     * Constructor for Toggle device controller.
     * @param house House in scope.
     */
    public DeactivateDeviceCTRL(House house) {
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
    }

    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    public List<DeviceDTO> getListOfDevices(String roomName){
       this.room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }

    public boolean deactivateDevice(String deviceName){
        Device device = commonListOfDevices.getDeviceByName(deviceName,room);
        return device.deactivateDevice();
    }
}
