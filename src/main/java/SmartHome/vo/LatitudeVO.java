package SmartHome.vo;

public class LatitudeVO implements ValueObject{

    private double latitudeValue;

    public  LatitudeVO(double latitudeValue) throws InstantiationException {
        if(invalidLatitudeValues(latitudeValue)){
            throw new InstantiationException("Invalid latitude value");
        }
        this.latitudeValue = latitudeValue;
    }

    private boolean invalidLatitudeValues(double latitudeValue){
        return latitudeValue > 90.0 || latitudeValue < -90.0;
    }

    @Override
    public String toString() {
        return latitudeValue + "";

    }
}
