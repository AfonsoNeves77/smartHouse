package SmartHome.domain.sensor.sensorImplementation.sensorValues;

public class DewPointValue implements Value<Double> {

    private final double primitiveValue;

    public DewPointValue(double reading) {
        this.primitiveValue = reading;
    }


    public Double getValue() {
        return this.primitiveValue;
    }


    public String getValueAsString() {
        return primitiveValue + "";
    }
}