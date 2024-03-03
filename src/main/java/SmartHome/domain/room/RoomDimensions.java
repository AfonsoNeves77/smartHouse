package SmartHome.domain.room;

public class RoomDimensions {
    private double roomWidth;
    private double roomLength;
    private double roomHeight;

    //Create constructor with only width and length parameters as well??

    public RoomDimensions() {
    }

    /**
     * RoomDimensions Constructor
     *
     * @param roomWidth  Room width
     * @param roomLength Room Length
     * @param roomHeight Room Height
     */
    public RoomDimensions(double roomWidth, double roomLength, double roomHeight) {
        if (!areDimensionsValid(roomWidth, roomLength, roomHeight)) {
            throw new IllegalArgumentException("Please insert valid room dimensions.");
        }
        this.roomWidth = roomWidth;
        this.roomLength = roomLength;
        this.roomHeight = roomHeight;
    }


    /**
     * @return Room width
     */
    public double getRoomWidth() {
        return roomWidth;
    }

    /**
     * @return Room Length
     */
    public double getRoomLength() {
        return roomLength;
    }

    /**
     * @return Room Height
     */
    public double getRoomHeight() {
        return roomHeight;
    }

    private boolean areDimensionsValid(double roomWidth, double roomLength, double roomHeight) {
        if (roomWidth <= 0 || roomLength <= 0 || roomHeight < 0) {
            return false;
        }
        else{
            return true;
        }
    }
}
