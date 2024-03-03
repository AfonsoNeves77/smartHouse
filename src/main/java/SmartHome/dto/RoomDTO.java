package SmartHome.dto;

public class RoomDTO {
    private String roomName;
    private int houseFloor;
    private double roomWidth;
    private double roomLength;
    private double roomHeight;

    /**
     * Constructs a new RoomDTO with the specified parameters.
     *
     * @param roomName   the name of the room.
     * @param houseFloor the floor of the house where the room is located.
     * @param roomWidth  the width of the room.
     * @param roomLength the length of the room.
     * @param roomHeight the height of the room.
     */

    public RoomDTO(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight){
        this.roomName = roomName;
        this.houseFloor = houseFloor;
        this.roomWidth = roomWidth;
        this.roomLength = roomLength;
        this.roomHeight = roomHeight;
    }

    /**
     * Returns the name of the room.
     *
     * @return the name of the room.
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Returns the floor of the house where the room is located.
     *
     * @return the floor of the house.
     */
    public int getHouseFloor() {
        return houseFloor;
    }

    /**
     * Returns the width of the room.
     *
     * @return the width of the room.
     */
    public double getRoomWidth() {
        return roomWidth;
    }

    /**
     * Returns the length of the room.
     *
     * @return the length of the room.
     */
    public double getRoomLength() {
        return roomLength;
    }

    /**
     * Returns the height of the room.
     *
     * @return the height of the room.
     */
    public double getRoomHeight() {
        return roomHeight;
    }
}
