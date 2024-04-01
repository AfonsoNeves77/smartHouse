package SmartHomeDDD.dto;

public class RoomDTO {
    private final String id;
    private final String roomName;
    private final int floor;
    private final double roomHeight;

    private final double roomLength;

    private final double roomWidth;
    private final String houseID;

    /**
     * Constructor for RoomDTO
     * @param id
     * @param roomName
     * @param floor
     * @param roomHeight
     * @param roomLength
     * @param roomWidth
     * @param houseID
     */
    public RoomDTO(String id, String roomName, int floor, double roomHeight, double roomLength, double roomWidth, String houseID) {
        this.id = id;
        this.roomName = roomName;
        this.floor = floor;
        this.roomHeight = roomHeight;
        this.roomLength = roomLength;
        this.roomWidth = roomWidth;
        this.houseID = houseID;
    }

    /**
     * Getter method to retrieve room ID
     * @return room ID in string format
     */
    public String getId() {
        return id;
    }

    /**
     * Getter method to retrieve room name
     * @return room name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Getter method to retrieve floor
     * @return floor in int format
     */
    public int getFloor() {
        return floor;
    }

    /**
     * Getter method to retrieve room height
     * @return room height
     */
    public double getRoomHeight() {
        return roomHeight;
    }

    /**
     * Getter method to retrieve room length
     * @return room length
     */
    public double getRoomLength() {
        return roomLength;
    }

    /**
     * Getter method to retrieve room width
     * @return room width
     */
    public double getRoomWidth() {
        return roomWidth;
    }

    /**
     * Getter method to retrieve house ID
     * @return house ID
     */
    public String getHouseID() {
        return houseID;
    }
}