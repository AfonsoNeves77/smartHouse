package SmartHome.vo;

public class DeviceNameVO implements ValueObject{

    private String deviceName;

    public DeviceNameVO(String deviceName) {
        if(deviceName == null || deviceName.isBlank()) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.deviceName = deviceName;
    }

    public String getDeviceName() {
        return this.deviceName;
    }
}
