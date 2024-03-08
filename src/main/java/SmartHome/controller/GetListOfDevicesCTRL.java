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


    /**
     * Controller Constructor for Use Case: To get a list of all devices in a room
     * @param house House in use
     */
    public GetListOfDevicesCTRL(House house){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
    }

    /**
     * Retrieves a manipulated list of RoomDTOs. The DTOs correspond to their related domain Room object
     * @return List containing RoomDTOs
     */
    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    /**
     * This method serves the purpose of obtaining the listOfDevices of a specific room, it is called after the user
     * obtaining the listOfRooms and choosing one.
     * @param roomName Room identification from which is required a list of devices
     * @return List containing DeviceDTOs
     */
    public List<DeviceDTO> getListOfDevices(String roomName){
        Room room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }
}
