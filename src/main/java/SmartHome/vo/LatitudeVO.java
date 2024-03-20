package SmartHome.vo;

public class LatitudeVO implements ValueObject{

    private double latitudeValue;

    public  LatitudeVO(double latitudeValue) {
        if(invalidLatitudeValues(latitudeValue)){
            throw new IllegalArgumentException("Invalid latitude value");
        }
        this.latitudeValue = latitudeValue;
    }

    private boolean invalidLatitudeValues(double latitudeValue){
        return latitudeValue > 90.0 || latitudeValue < -90.0;
    }

    public double getLatitude() {
        return this.latitudeValue;

    }
}
