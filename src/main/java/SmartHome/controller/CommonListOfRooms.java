package SmartHome.controller;


import SmartHome.domain.House;
import SmartHome.domain.room.Room;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomDTOMapper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class CommonListOfRooms {
    private House house;
    private LinkedHashMap<RoomDTO, Room> roomDTOAndRoomMap = new LinkedHashMap<>();

    public CommonListOfRooms(House house) {
        this.house = house;
    }

    /**
     * Retrieves the list of rooms from the associated House.
     *
     * @return An ArrayList of Room objects representing the rooms within the associated House.
     */
    public List<RoomDTO> getListOfRooms() {
        refreshMap();
        return truncateList(roomDTOAndRoomMap);
    }

    private List<RoomDTO> truncateList(Map<RoomDTO, Room> roomDTOAndRoomMap) {
        return roomDTOAndRoomMap.keySet().stream().toList();
    }

    public Room getRoomByName(String roomName) {
        refreshMap();
        for (Map.Entry<RoomDTO, Room> entry : roomDTOAndRoomMap.entrySet()) {
            if (entry.getKey().getRoomName().equals(roomName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    private void refreshMap() {
        List<Room> roomList = house.getListOfRooms();
        roomDTOAndRoomMap = RoomDTOMapper.getRoomDTOList(roomList);
    }
}
