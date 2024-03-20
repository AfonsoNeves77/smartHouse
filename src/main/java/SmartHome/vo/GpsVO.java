package SmartHome.vo;

public class GpsVO implements ValueObject{
    private LatitudeVO latitude;
    private LongitudeVO longitude;

    public GpsVO(LatitudeVO latitude, LongitudeVO longitude){
        if(latitude == null || longitude == null)
            throw new IllegalArgumentException("Latitude and longitude cannot be null.");
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude(){
        return latitude.getLatitude();
    }

    public double getLongitude(){
        return longitude.getLongitude();
    }

}
