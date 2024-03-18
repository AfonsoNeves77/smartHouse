package SmartHome.vo;

public class RoomNameVO {

    private String roomName;

    public RoomNameVO(String roomName) {
        if(isDeviceNameNull(roomName) || isDeviceNameEmpty(roomName) || isDeviceNameBlank(roomName)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.roomName = roomName;
    }

    private boolean isDeviceNameNull(String roomName) {
        return roomName == null;
    }

    private boolean isDeviceNameEmpty(String roomName) {
        return roomName.trim().isEmpty();
    }

    private boolean isDeviceNameBlank(String roomName) {
        return roomName.isBlank();
    }

    public String getRoomName() {
        return this.roomName;
    }
}