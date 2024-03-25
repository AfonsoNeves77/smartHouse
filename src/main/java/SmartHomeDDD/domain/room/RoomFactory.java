package SmartHomeDDD.domain.room;

import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;

public class RoomFactory {

    /**
     * Creates a Room with the specified parameters, that are all Value-Objects.
     * @param roomName The name of the room.
     * @param floor The floor on which the room is located.
     * @param roomDimensions The dimensions of the room.
     * @param houseID The ID of the house to which the room belongs.
     * @return  The Room created.
     */

    public Room createRoom(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID){
        return new Room(roomName, floor, roomDimensions, houseID);
    }
}
