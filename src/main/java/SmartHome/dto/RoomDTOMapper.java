package SmartHome.dto;

import SmartHome.domain.room.Room;

import java.util.LinkedHashMap;
import java.util.List;

public class RoomDTOMapper {

    /**
     * Receives a Room object and converts it to a Room DTO object
     *
     * @param room Room object
     * @return Room DTO object
     */
    public static RoomDTO domainToDTO(Room room) {
        double[] roomDimensions = room.getDimensions();
        return new RoomDTO(room.getRoomName(), room.getHouseFloor(),
                roomDimensions[0], roomDimensions[1], roomDimensions[2]);
    }

    /**
     * Converts list of rooms into a list of room DTOs.
     *
     * @param listOfRooms List with Room objects
     * @return List with Room DTOs
     */
    public static LinkedHashMap<RoomDTO, Room> getRoomDTOList(List<Room> listOfRooms) {
        LinkedHashMap<RoomDTO, Room> roomDTOAndRoomMap = new LinkedHashMap<>();
        listOfRooms.forEach(room ->
                {
                    RoomDTO roomDTO = RoomDTOMapper.domainToDTO(room);
                    roomDTOAndRoomMap.put(roomDTO, room);
                }
        );
        return roomDTOAndRoomMap;
    }
}
