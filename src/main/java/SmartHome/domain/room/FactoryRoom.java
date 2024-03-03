package SmartHome.domain.room;

public interface FactoryRoom {
    public Room createRoom(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight);
}
