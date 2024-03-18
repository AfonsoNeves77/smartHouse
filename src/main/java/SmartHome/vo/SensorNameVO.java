package SmartHome.vo;

public class SensorNameVO implements ValueObject{

    private String sensorName;

    public SensorNameVO(String sensorName) {
        if(sensorName == null || sensorName.isBlank()) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.sensorName = sensorName;
    }

    public String getSensorName() {
        return this.sensorName;
    }
}
