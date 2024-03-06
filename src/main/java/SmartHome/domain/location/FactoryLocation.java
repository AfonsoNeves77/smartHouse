package SmartHome.domain.location;

public interface FactoryLocation {
    Address createAddress(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode) throws InstantiationException;

    GPS createGPS(double latitude, double longitude) throws InstantiationException;
}
