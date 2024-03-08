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

    /**
     * Constructor for CommonListOfRooms, which takes a House to be initialized
     * @param house House Object linked to Common Class
     */
    public CommonListOfRooms(House house) {
        this.house = house;
    }

    /**
     * Retrieves the list of rooms from the associated House.
     * @return List of RoomDTO objects representing the rooms within the associated House.
     */
    public List<RoomDTO> getListOfRooms() {
        refreshMap();
        return truncateList(roomDTOAndRoomMap);
    }

    /**
     * Gathers all RoomDTOs in a single List
     * @param roomDTOAndRoomMap Map with Key-Value pairs of [RoomDTOs - Room]
     * @return List of RoomDTOs
     */
    private List<RoomDTO> truncateList(Map<RoomDTO, Room> roomDTOAndRoomMap) {
        return roomDTOAndRoomMap.keySet().stream().toList();
    }

    /**
     * After refreshing this Class roomDTOAndRoomMap, it searches for a Room by its name
     * @param roomName Room identifier
     * @return The Room object in case it is found, null if not present in the map
     */
    public Room getRoomByName(String roomName) {
        refreshMap();
        for (Map.Entry<RoomDTO, Room> entry : roomDTOAndRoomMap.entrySet()) {
            if (entry.getKey().getRoomName().equals(roomName)) {
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * Method to update this Class's Map containing Key-Value pairs of [RoomDTOs - Room], by requesting
     * the house's list of rooms
     */
    private void refreshMap() {
        List<Room> roomList = house.getListOfRooms();
        roomDTOAndRoomMap = RoomDTOMapper.getRoomDTOList(roomList);
    }
}
