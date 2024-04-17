package smarthome.service;

import smarthome.domain.room.Room;
import smarthome.domain.vo.roomvo.RoomDimensionsVO;
import smarthome.domain.vo.roomvo.RoomFloorVO;
import smarthome.domain.vo.roomvo.RoomNameVO;

import java.util.List;

public interface RoomService {
    List<Room> findAll();
    boolean addRoom(RoomNameVO roomNameVO, RoomFloorVO roomFloorVO, RoomDimensionsVO roomDimensionsVO);
}
