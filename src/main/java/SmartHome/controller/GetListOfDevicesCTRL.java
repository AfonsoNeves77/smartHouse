package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class GetListOfDevicesCTRL {
    private House house;
    private CommonListOfRooms commonListOfRooms;
    private CommonListOfDevices commonListOfDevices;


    public GetListOfDevicesCTRL(House house){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
    }

    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    public List<DeviceDTO> getListOfDevices(String roomName){
        Room room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }
}
