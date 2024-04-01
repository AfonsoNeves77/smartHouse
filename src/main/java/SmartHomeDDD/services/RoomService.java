package SmartHomeDDD.services;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;

import java.util.ArrayList;
import java.util.List;

public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomFactory roomFactory;

    /**
     * Constructor for RoomService. It takes a RoomRepository and a RoomFactory as parameters and encapsulates them.
     * If any of the parameters are null, it throws an IllegalArgumentException.
     * @param roomRepository
     * @param roomFactory
     */
    public RoomService(RoomRepository roomRepository, RoomFactory roomFactory) {
        if (areParamsNull(roomRepository,roomFactory)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.roomRepository = roomRepository;
        this.roomFactory = roomFactory;
    }

    /**
     * This method receives RoomNameVO, RoomFloorVO, RoomDimensionsVO and HouseIDVO objects, ensures they are not null,
     * passes them on to the encapsulated RoomFactory and propagates the return received from RoomFactory.
     * @param roomNameVO
     * @param roomFloorVO
     * @param roomDimensionsVO
     * @param houseIDVO
     * @return
     */
    public Room createRoom(RoomNameVO roomNameVO, RoomFloorVO roomFloorVO, RoomDimensionsVO roomDimensionsVO, HouseIDVO houseIDVO){
        if (areParamsNull(roomNameVO)){
            return null;
        }
        return roomFactory.createRoom(roomNameVO,roomFloorVO,roomDimensionsVO,houseIDVO);
    }

    /**
     * This method receives a Room object, ensures it is not null, passes it on to the Room Repository in order to save.
     * Propagates the return received from the Room Repository.
     * @param room
     * @return
     */
    public boolean saveRoom(Room room){
        if (areParamsNull(room)){
            return false;
        }
        return roomRepository.save(room);
    }

    /**
     * This method retrieves all rooms from the Room Repository and translates the received iterable into a List for indexed access.
     * @return
     */
    public List<Room> findAll(){
        Iterable<Room> rooms = this.roomRepository.findAll();
        List<Room> finalList = new ArrayList<>();

        for (Room room : rooms){
            finalList.add(room);
        }
        return finalList;
    }

    /**
     * This method receives a RoomIDVO object and verifies if it is contained within the Room Repository.
     * If the Room Repository contains the RoomIDVO object, it returns true, otherwise it returns false.
     * @param roomIDVO
     * @return
     */
    public boolean isPresent(RoomIDVO roomIDVO){
        return this.roomRepository.isPresent(roomIDVO);
    }

    /**
     * This method receives object parameters and verifies they are null.
     * @param params
     * @return
     */
    private boolean areParamsNull (Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
