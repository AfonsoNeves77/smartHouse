package SmartHome.domain.sensor.externalServices;

public class SimHardware implements ExternalServices{
    public String getValue() {
        return "Sample";
    }
    public String getValue(String initialDate, String finalDate) {
        return "Sample";
    }
}
