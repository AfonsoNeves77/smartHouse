package SmartHome.vo;

public class RoomDimensionsVO {
    private RoomLengthVO length;
    private RoomWidthVO width;
    private RoomHeightVO height;

    public RoomDimensionsVO(RoomLengthVO length, RoomWidthVO width, RoomHeightVO height) {
        if (!dimensionsAreValid(length, width, height)) {
            throw new IllegalArgumentException("Invalid dimensions");
        }
        this.length = length;
        this.width = width;
        this.height = height;
    }

    private boolean dimensionsAreValid(RoomLengthVO length, RoomWidthVO width, RoomHeightVO height) {
        return length != null && width != null && height != null;
    }

    public double  getRoomLength() {
        return this.length.getLength();

    }

    public double getRoomWidth() {
        return this.width.getRoomWidth();
    }

    public double getRoomHeight() {
        return this.height.getHeight();
    }
}
