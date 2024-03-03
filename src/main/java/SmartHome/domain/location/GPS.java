package SmartHome.domain.location;

public class GPS {
    private double latitude;
    private double longitude;

    /**
     * Constructor for GPS with full parameters. The constructor verifies their validity.
     * @param latitude latitude parameter in decimal degrees (DD)
     * @param longitude longitude parameter in decimal degrees (DD)
     */
    public GPS(double latitude, double longitude){
        if(!areGPSParamsValid(latitude, longitude)){
            throw new IllegalArgumentException("Invalid parameter.");
        }
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private boolean areGPSParamsValid(double latitude, double longitude) {
        return (latitude >= -90 && latitude <= 90 && longitude >= -180 && longitude <= 180);
    }
}
