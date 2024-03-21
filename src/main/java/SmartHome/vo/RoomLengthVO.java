package SmartHome.vo;

public class RoomLengthVO implements ValueObject{
    private final double length;

    /**
     * Constructs a RoomLengthVO object with the given length.
     *
     * @param length The length of the room.
     * @throws IllegalArgumentException If the length is invalid.
     */
    public RoomLengthVO(double length) {
        if (!validateLength(length)) {
            throw new IllegalArgumentException("Invalid length value");
        }
        this.length = length;
    }

    /**
     * Validates the length of the room.
     *
     * @param length The length of the room.
     * @return True if the length is valid, false otherwise.
     */
    private boolean validateLength(double length) {
        return length > 0;
    }

    public double getLength() {
        return this.length;
    }
}
