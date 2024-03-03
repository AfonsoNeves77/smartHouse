package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.domain.location.FactoryLocation;

public class ConfigureLocationCTRL {
    private final House house;
    private final FactoryLocation factoryLocation;

    /**
     * Constructor for the controller. It takes a house as parameter.
     */
    public ConfigureLocationCTRL(House house, FactoryLocation factoryLocation) {
        this.factoryLocation = factoryLocation;
        this.house = house;
    }

    public boolean configureLocation(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode, double latitude, double longitude, FactoryLocation factoryLocation) {
        return house.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocation);
    }

}
