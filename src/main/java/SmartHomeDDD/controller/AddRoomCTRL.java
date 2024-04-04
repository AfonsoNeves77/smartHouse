package SmartHomeDDD.controller;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.mapper.RoomMapper;
import SmartHomeDDD.services.HouseService;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;


/**
 * The AddRoomCTRL class is responsible for controlling the process of adding a new room
 * to a house in the SmartHome Domain-Driven Design application. It coordinates between the service
 * layer and the client requests, handling the conversion of data transfer objects (DTOs) to
 * value objects (VOs) and orchestrating the addition of rooms to the house.
 */
public class AddRoomCTRL {

   private HouseService houseService;

   private RoomService roomService;

    /**
     * Constructs a new AddRoomCTRL instance with specified services.
     *
     * @param houseService the service for managing houses
     * @param roomService the service for managing rooms
     */
   public AddRoomCTRL(HouseService houseService, RoomService roomService) {
       this.roomService = roomService;
       this.houseService = houseService;
   }

    /**
     * Adds a new room to the first (and only ath this point) house by using the provided RoomDTO.
     * This method performs the conversion of a RoomDTO to RoomNameVO,
     * RoomFloorVO, and RoomDimensionsVO using RoomMapper. Then,
     * it retrieves the first house's ID and requests the RoomService to create
     * and save the new room in the repository.
     *
     * @param roomDTO the data transfer object containing room details
     * @return true if the room was successfully added; false otherwise.
     * catches InstantiationException if the RoomMapper fails to create the VOs;
     */
    public boolean addRoom(RoomDTO roomDTO) {
        try {
                // First, relies on the RoomMapper to convert DTO into the needed VO's
            RoomNameVO roomName = RoomMapper.createRoomNameVO(roomDTO);
            RoomFloorVO roomFloor = RoomMapper.createRoomFloorVO(roomDTO);
            RoomDimensionsVO roomDimensions = RoomMapper.createRoomDimensionsVO(roomDTO);
                // Then calls the House Service in order to get the First House ID VO
            HouseIDVO houseID = houseService.getFirstHouseIDVO();
                // To create a room, calls the Room Service to do so
            Room room = roomService.createRoom(roomName, roomFloor, roomDimensions, houseID);
                // Lastly, tells the Room Service to save the created room in the Repository
            return roomService.saveRoom(room);
                // If any of the above steps fail, the method will return false
        } catch (InstantiationException e) {
            return false;
        }
    }
}
