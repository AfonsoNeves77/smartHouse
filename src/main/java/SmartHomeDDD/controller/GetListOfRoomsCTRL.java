package SmartHomeDDD.controller;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.mapper.RoomMapper;
import SmartHomeDDD.services.RoomService;

import java.util.List;

public class GetListOfRoomsCTRL {


    private final RoomService roomService;

    /**
     * Constructor for the GetListOfRoomsCTRL class.
     * @param roomService RoomService object.
     */
    public GetListOfRoomsCTRL(RoomService roomService) {
        if (!validateRoomService(roomService)) {
            throw new IllegalArgumentException("RoomService cannot be null.");
        }
        this.roomService = roomService;
    }

    /**
     * Method to validate the RoomService object.
     * @param roomService RoomService object.
     * @return boolean
     */
    private boolean validateRoomService(RoomService roomService) {
        return roomService != null;
    }

    /**
     * Method to get the list of rooms.
     * @return List of RoomDTO objects.
     */
    public List<RoomDTO>getListOfRooms() {
        List<Room> rooms = roomService.findAll();
        return RoomMapper.domainToDTO(rooms);
    }
}