package SmartHome.domain.sensor.sensorImplementation.sensorValues;

public class PositionValue implements Value<Integer>{
    private final int primitiveValue;

    public PositionValue(int reading) throws InstantiationException {
        if(reading < 0 || reading > 100){
            throw new InstantiationException("Invalid reading");
        }
        this.primitiveValue = reading;
    }

    public Integer getValue() {
        return this.primitiveValue;
    }

    public String getValueAsString(){
        return primitiveValue + "";
    }
}
