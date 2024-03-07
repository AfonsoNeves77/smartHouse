package SmartHome.domain.room;

public class FactoryIndoorRoom implements FactoryRoom {

    /**
     * Factory method to create new instances of Room
     * @param roomName Room name
     * @param houseFloor House floor
     * @param roomWidth Room width
     * @param roomLength Room Length
     * @param roomHeight Room Height
     * @return A new Room Object
     * @throws InstantiationException If any given parameter is invalid
     */
    public Room createRoom(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight) throws InstantiationException {
        return new Room(roomName,houseFloor,roomWidth,roomLength,roomHeight);
    }


}
