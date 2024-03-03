package SmartHome.domain.device;

public class ImplFactoryDevice implements FactoryDevice {
    public Device createDevice(String deviceName, String deviceModel, String deviceLocation) {
        return new Device(deviceName, deviceModel, deviceLocation);
    }
}