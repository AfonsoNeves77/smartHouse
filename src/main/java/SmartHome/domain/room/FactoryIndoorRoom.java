package SmartHome.domain.room;

public class FactoryIndoorRoom implements FactoryRoom {

    public Room createRoom(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight) {
        return new Room(roomName,houseFloor,roomWidth,roomLength,roomHeight);
    }


}
