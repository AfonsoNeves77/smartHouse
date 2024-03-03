package SmartHome.domain.location;

public class ImplFactoryLocation implements FactoryLocation {

    private String doorReference;
    private String buildingNumber;
    private String streetName;
    private String city;
    private String country;
    private String zipCode;
    private double latitude;
    private double longitude;

    public Address createAddress(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode) {
        return new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
    }
    
    public GPS createGPS(double latitude, double longitude) {
        return new GPS(latitude, longitude);
    }
}
