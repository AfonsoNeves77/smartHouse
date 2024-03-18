package SmartHome.vo;

public class RoomFloorVO implements ValueObject {
    private int roomFloor;

    public RoomFloorVO(int roomFloor) {
        this.roomFloor = roomFloor;
    }

    public int getRoomFloor(){
        return this.roomFloor;
    }
}
