package SmartHome.vo;

public class DeviceStatusVO implements ValueObject {

    private boolean deviceStatus;

    public DeviceStatusVO (boolean deviceStatus) {
        this.deviceStatus = deviceStatus;
    }

    public boolean getDeviceStatus() {
        return deviceStatus;
    }

}
