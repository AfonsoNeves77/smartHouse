package SmartHome.dto;

import SmartHome.domain.Room;

public class RoomDTO {
    public final String name;
    public final int floor;
    public final double length;
    public final double width;
    public final double height;

    public RoomDTO( Room room)
    {
        this.name = room.getName();
        this.floor = room.getFloor();
        this.length = room.getLength();
        this.width = room.getWidth();
        this.height = room.getHeight();
    }
}
