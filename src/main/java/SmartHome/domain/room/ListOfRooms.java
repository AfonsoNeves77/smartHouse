package SmartHome.domain.room;

import java.util.ArrayList;
import java.util.List;

public class ListOfRooms {
    private ArrayList<Room> roomList;

    /**
     * ListOfRooms Constructor
     */
    public ListOfRooms(){
        this.roomList = new ArrayList<>();
    }



    /**
     * Adds a room, including its dimensions (width, length, height)
     * @param roomName Room name
     * @param houseFloor Room house floor
     * @param roomWidth Room width
     * @param roomLength Room length
     * @param roomHeight Room height
     * @return Code 0: Room successfully added;
     * Code 1: Impossible to instantiate a new Room;
     * Code 2: Room already exists in the house (duplication).
     */
    public boolean addRoomToList(String roomName, int houseFloor, double roomWidth,
                                 double roomLength, double roomHeight, FactoryRoom factoryRoom) {
        if(isRoomInList(roomName)) {
            return false;
        }
        try{
            Room newRoom = factoryRoom.createRoom(roomName,houseFloor,roomWidth,roomLength,roomHeight);
            roomList.add(newRoom);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Verifies if Room is already in the list by evaluating existing room names
     * @param roomName Room name
     * @return True if Room already exists in the list
     */
    private boolean isRoomInList(String roomName){
        if(!roomList.isEmpty()){
            for (Room singleRoom : roomList){
                if(roomName.equalsIgnoreCase(singleRoom.getRoomName())){
                    return true;
                }
            }
        }
        return false;
    }



    /**
     * @return Room list with Room objects
     */
    public List<Room> getRoomList() {
        return new ArrayList<>(roomList);
    }
}
