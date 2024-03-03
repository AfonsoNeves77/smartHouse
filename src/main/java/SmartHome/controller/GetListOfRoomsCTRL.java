package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class GetListOfRoomsCTRL {
    private final House house;
    private final CommonListOfRooms commonListOfRooms;

    public GetListOfRoomsCTRL(House house){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
    }

    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }
}
