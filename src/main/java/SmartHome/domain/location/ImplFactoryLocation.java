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

    /**
     * Receives address creation parameters and, if valid, instantiates an Address object, returning it.
     * @param doorReference Door reference
     * @param buildingNumber Building number
     * @param streetName Street name
     * @param city City
     * @param country Country
     * @param zipCode Zipcode
     * @return New address object.
     * @throws InstantiationException If parameters null or empty.
     */
    public Address createAddress(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode) throws InstantiationException {
        return new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
    }

    /**
     * Receives gps creation parameters and, if valid, instantiates a new gps object, returning it.
     * @param latitude Latitude
     * @param longitude Longitude
     * @return New gps object.
     */
    public GPS createGPS(double latitude, double longitude) throws InstantiationException {
        return new GPS(latitude, longitude);
    }
}
