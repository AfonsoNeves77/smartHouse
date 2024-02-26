package SmartHome.domain;

public interface Sensor
{
    SensorType getSensorType();

    Value getValue();
}
