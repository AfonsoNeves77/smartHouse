package SmartHome.vo;

public class RoomLengthVO implements ValueObject{
    private final double length;

    public RoomLengthVO(double length) {
        if (!validateLength(length)) {
            throw new IllegalArgumentException("Invalid length value");
        }
        this.length = length;
    }
    private boolean validateLength(double length) {
        return length > 0;
    }

    public double getLength() {
        return this.length;
    }

}
