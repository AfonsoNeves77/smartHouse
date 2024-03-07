package SmartHome.domain.room;

public class RoomDimensions {
    private double roomWidth;
    private double roomLength;
    private double roomHeight;

    /**
     * RoomDimensions Constructor
     * @param roomWidth  Room width
     * @param roomLength Room Length
     * @param roomHeight Room Height
     * @throws InstantiationException if any room dimension is invalid.
     */
    public RoomDimensions(double roomWidth, double roomLength, double roomHeight) throws InstantiationException {
        if (!areDimensionsValid(roomWidth, roomLength, roomHeight)) {
            throw new InstantiationException("Please insert valid room dimensions.");
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

    /**
     * Verifies if dimensions are valid.
     * @param roomWidth room width must be higher than 0
     * @param roomLength room length must be higher than 0
     * @param roomHeight room height must be at least 0
     * @return True if dimensions are valid.
     */
    private boolean areDimensionsValid(double roomWidth, double roomLength, double roomHeight) {
        if (roomWidth <= 0 || roomLength <= 0 || roomHeight < 0) {
            return false;
        }
        else{
            return true;
        }
    }
}
