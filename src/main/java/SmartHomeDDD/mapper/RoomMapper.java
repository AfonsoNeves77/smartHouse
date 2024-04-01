package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Mapper class that converts Room objects to RoomDTO objects and vice versa.
 * It provides utility functions for mapping between Room domain objects and RoomDTO data transfer objects.
 * This class facilitates the conversion of complex domain objects into simpler, flattened
 * data structures suitable for transmission over the network or for use in interfaces
 * where complex domain logic is not required.
 * Each public static method in this class corresponds to a specific component of a Room, allowing for
 * targeted conversion of individual attributes between the Room and RoomDTO representations.
 */
public class RoomMapper {

    /**
     * Retrieves the room name from the RoomDTO object and creates a RoomNameVO object.
     * @param roomDTO  RoomDTO object that contains the room name.
     * @return  RoomNameVO value-object.
     */
    public static RoomNameVO createRoomNameVO(RoomDTO roomDTO){
        return new RoomNameVO(roomDTO.getRoomName());
    }


    /**
     * Retrieves the room dimensions from the RoomDTO object and creates a RoomDimensionsVO object.
     * @param roomDTO  RoomDTO object that contains the room dimensions.
     * @return  RoomDimensionsVO value-object.
     */
    public static RoomDimensionsVO createRoomDimensionsVO(RoomDTO roomDTO) {
        RoomHeightVO roomHeightVO = new RoomHeightVO(roomDTO.getRoomHeight());
        RoomLengthVO roomLengthVO = new RoomLengthVO(roomDTO.getRoomLength());
        RoomWidthVO roomWidthVO = new RoomWidthVO(roomDTO.getRoomWidth());
        return new RoomDimensionsVO(roomLengthVO, roomWidthVO, roomHeightVO);
    }


    /**
     * Retrieves the room floor from the RoomDTO object and creates a RoomFloorVO object.
     * @param roomDTO  RoomDTO object that contains the room floor.
     * @return  RoomFloorVO value-object.
     */
    public static RoomFloorVO createRoomFloorVO(RoomDTO roomDTO) {
        return new RoomFloorVO(roomDTO.getFloor());
    }


    /**
     * Retrieves the room ID from the RoomDTO object and creates a RoomIDVO object.
     * @param roomDTO  RoomDTO object that contains the room ID.
     * @return  RoomIDVO value-object.
     */
    public static RoomIDVO createRoomIDVO(RoomDTO roomDTO){
        // Converts String from the DTO into a UUID
        UUID roomID = UUID.fromString(roomDTO.getId());
        return new RoomIDVO(roomID);
    }


    /**
     * Retrieves the house ID from the RoomDTO object and creates a HouseIDVO object.
     * @param roomDTO  RoomDTO object that contains the house ID.
     * @return  HouseIDVO value-object.
     */
    public static HouseIDVO createHouseIDVO(RoomDTO roomDTO){
        // Converts String from the DTO into a UUID
        UUID houseID = UUID.fromString(roomDTO.getHouseID());
        return new HouseIDVO(houseID);
    }

    /**
     * Converts any number of Room objects into a list of RoomDTO objects
     * by using the private method convertRoomToDTO in order to convert each Room object.
     * @param listOfRooms<Room>  Room objects to be converted.
     * @return  List of RoomDTO objects.
     */
    public static List<RoomDTO> domainToDTO (List<Room> listOfRooms){
        List<RoomDTO> roomDTOList = new ArrayList<>();
        for (Room room : listOfRooms){
            RoomDTO roomDTO = convertRoomToDTO(room);
            roomDTOList.add(roomDTO);
        }
        return roomDTOList;
    }


    /**
     * Private method that converts a Room object into a RoomDTO object.
     * @param room  Room object to be converted.
     * @return  RoomDTO object.
     */
    private static RoomDTO convertRoomToDTO (Room room){
        int roomFloor = room.getFloor().getValue();
        double roomHeight = room.getRoomDimensions().getRoomHeight();
        double roomLength = room.getRoomDimensions().getRoomLength();
        double roomWidth = room.getRoomDimensions().getRoomWidth();
        String roomName = room.getRoomName().getValue();
        String roomID = room.getId().getID();
        String houseID = room.getHouseID().getID();
        RoomDTO roomDTO = new RoomDTO(roomID, roomName, roomFloor, roomHeight, roomLength, roomWidth, houseID);
        return roomDTO;
    }
}
