package SmartHomeDDD.domain.room;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;

import java.util.UUID;

/**
 * Represents a room in a house.

 * A room is characterized by its name, floor, dimensions, and the ID of the house it belongs to.

 * The constructor of this class validates the parameters and throws an IllegalArgumentException
 * if any of them are null. Additionally, the constructor instantiates a RoomIDVO object
 * for the room's unique identifier.
 */
public class Room implements DomainEntity {

    private final RoomIDVO roomID;
    private RoomNameVO roomName;
    private final RoomFloorVO floor;
    private RoomDimensionsVO roomDimensions;
    private final HouseIDVO houseID;

    /**
     * Constructs a room with the specified parameters.
     *
     * @param roomName The name of the room.
     * @param floor The floor on which the room is located.
     * @param roomDimensions The dimensions of the room.
     * @param houseID The ID of the house to which the room belongs.
     * @throws IllegalArgumentException If any of the parameters are null.
     */

      /*
    RoomIDVO instantiation may throw an exception (Illegal Argument) if it receives null in it´s constructor.
    In case UUID.randomUUID() returns something null an exception would be thrown. In this case that exception is propagated
    and caught in the method that invokes room creation in the RoomFactory.
     */

    public Room(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID) {
        if (!allParametersAreValid(roomName, floor, roomDimensions, houseID)) {
            throw new IllegalArgumentException("Invalid Parameters");
        }
        this.roomName = roomName;
        this.floor = floor;
        this.roomDimensions = roomDimensions;
        this.houseID = houseID;
        this.roomID = new RoomIDVO(UUID.randomUUID());
    }

    private boolean allParametersAreValid(RoomNameVO roomName, RoomFloorVO floor, RoomDimensionsVO roomDimensions, HouseIDVO houseID) {
        return roomName != null && floor != null && roomDimensions != null && houseID != null;
    }

    /**
     * Gets the name of the room.
     *
     * @return The name of the room.
     */
    public RoomNameVO getRoomName() {
        return roomName;
    }

    /**
     * Gets the floor on which the room is located.
     *
     * @return The floor of the room.
     */
    public RoomFloorVO getFloor() {
        return floor;
    }

    /**
     * Gets the dimensions of the room.
     *
     * @return The dimensions of the room.
     */
    public RoomDimensionsVO getRoomDimensions() {
        return roomDimensions;
    }

    /**
     * Gets the ID of the house to which the room belongs.
     *
     * @return The ID of the house.
     */
    public HouseIDVO getHouseID() {
        return houseID;
    }

    /**
     *
     * @return Rooms´s identity, it´s ID;
     */

    @Override
    public DomainID getId() {
        return this.roomID;
    }
}

