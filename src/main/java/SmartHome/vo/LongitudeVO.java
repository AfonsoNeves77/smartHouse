package SmartHome.vo;

public class LongitudeVO implements ValueObject{

    private double longitudeValue;

    public LongitudeVO(double longitudeValue)  {
        if(invalidLongitudeValues(longitudeValue)){
            throw new IllegalArgumentException("Invalid longitude value");
        }
        this.longitudeValue = longitudeValue;
    }

    private boolean invalidLongitudeValues(double longitudeValue){
        return longitudeValue > 180.0 || longitudeValue < -180.0;
    }

    @Override
    public String toString() {
        return longitudeValue + "";

    }
}
