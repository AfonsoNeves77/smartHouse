package SmartHome.dto;

import SmartHome.domain.Room;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RoomMapper
{
    static public RoomDTO Domain2DTO( Room room )
    {
        return new RoomDTO( room );
    }

    static public Map<RoomDTO, Room> Domain2DTO(List<Room> rooms)
    {
        Map<RoomDTO, Room> roomsDTOAndRooms = new HashMap<>();

        rooms.forEach( room -> {
            RoomDTO roomDTO = RoomMapper.Domain2DTO(room);
            roomsDTOAndRooms.put( roomDTO, room );
        });

        return roomsDTOAndRooms;
    }
}
