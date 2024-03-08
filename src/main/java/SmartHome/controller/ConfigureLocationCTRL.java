package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.domain.location.FactoryLocation;

public class ConfigureLocationCTRL {
    private final House house;

    /**
     * Controller Constructor for Use Case: To configure the location of the house
     * @param house House in use
     */
    public ConfigureLocationCTRL(House house) {
        this.house = house;
    }

    /**
     * Calls configure location on house, passing the address and gps creation parameters. It receives a boolean to indicate success.
     * @param doorReference Door reference
     * @param buildingNumber Building number
     * @param streetName Street name
     * @param city City
     * @param country Country
     * @param zipCode Zipcode
     * @param latitude Latitude
     * @param longitude Longitude
     * @param factoryLocation Factory for location objects
     * @return True or false
     */
    public boolean configureLocation(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode, double latitude, double longitude, FactoryLocation factoryLocation) {
        return house.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocation);
    }

}
