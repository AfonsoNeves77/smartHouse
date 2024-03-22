package SmartHome.vo;

public class GpsVO implements ValueObject{
    private final LatitudeVO latitude;
    private final LongitudeVO longitude;

    /**
     * GPS Constructor for the corresponding Value Object composed by a Latitude and Longitude Value Objects
     * @param latitude Latitude Value Object
     * @param longitude Longitude Value Object
     */
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
