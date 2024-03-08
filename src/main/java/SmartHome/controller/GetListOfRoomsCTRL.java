package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class GetListOfRoomsCTRL {
    private final House house;
    private final CommonListOfRooms commonListOfRooms;

    /**
     * Controller Constructor for Use Case: To get a list of existing rooms
     * @param house House in use
     */
    public GetListOfRoomsCTRL(House house){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
    }

    /**
     * This method serves the purpose of delivering a listOfRooms to the user, so he can choose one.
     * @return ListOfRoomDTO
     */
    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }
}
