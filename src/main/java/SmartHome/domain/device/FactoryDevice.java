package SmartHome.domain.device;

public interface FactoryDevice {
    Device createDevice(String deviceName, String deviceModel, String deviceLocation);
}