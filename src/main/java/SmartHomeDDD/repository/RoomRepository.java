package SmartHomeDDD.repository;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.houseVO.HouseIDVO;


import java.util.*;
public class RoomRepository implements Repository<RoomIDVO, Room>{

    private static final Map<RoomIDVO, Room> DATA = new HashMap<>();

    /**
     * Saves a room to the repository.
     * @param room
     * @return true if the room was saved successfully, false otherwise.
     */
    @Override
    public boolean save(Room room) {
        if(room == null || room.getId() == null || isPresent(room.getId())) {
            return false;
        }
        DATA.put(room.getId(), room);
        return true;
    }

    /**
     * Finds all rooms in the repository.
     * @return an iterable of all rooms in the repository.
     */
    @Override
    public Iterable<Room> findAll() {
        return DATA.values();
    }

    /**
     * Finds a room by its ID.
     * @param roomID
     * @return the room with the given ID, or null if it does not exist.
     */
    @Override
    public Room findById(RoomIDVO roomID) {
        if(!isPresent(roomID)) {
            return null;
        } else {
            return DATA.get(roomID);
        }
    }

    /**
     * Verifies if a room is present in the repository, searching by its ID.
     * @param roomID
     * @return true if the room is present, false otherwise.
     */
    @Override
    public boolean isPresent(RoomIDVO roomID) {
        return DATA.containsKey(roomID);
    }

    /**
     * Finds all rooms in a house given a house ID.
     * @param houseID
     * @return list of rooms in the house.
     */
    public List<Room> findByHouseID(HouseIDVO houseID) {
        return DATA.values().stream().filter(room -> houseID.equals(room.getHouseID())).toList();
    }
}

