package SmartHome.vo;

public class RoomNameVO implements ValueObject{

    private String roomName;

    public RoomNameVO(String roomName) {
        if(!areParametersValid(roomName)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.roomName = roomName;
    }

    private boolean areParametersValid(String roomName) {
        if(roomName == null || roomName.trim().isEmpty() || roomName.isBlank()) {
            return false;
        }
        return true;
    }

    public String getRoomName() {
        return this.roomName;
    }
}
