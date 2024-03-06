package SmartHome.controllerTest;
import SmartHome.controller.ConfigureLocationCTRL;
import SmartHome.domain.House;
import SmartHome.domain.location.FactoryLocation;
import SmartHome.domain.location.ImplFactoryLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConfigureLocationCTRLTest {

    /**
     * Integration Test to configure a location.
     * Arrange the atributes of a location.
     * Act to create and configure the location.
     * Assert the location was successfully configured.
     */
    @Test
    void configureLocation_SuccessfullyCreatesAddressAndGpsObjects() throws InstantiationException {
        //Arrange
        String houseName = "myHouse";
        House house = new House(houseName);
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        double longitude = 0;
        double latitude = 0;
        FactoryLocation factoryLocation = new ImplFactoryLocation();
        ConfigureLocationCTRL ctrl = new ConfigureLocationCTRL(house);
        //Act
        boolean result = ctrl.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocation);
        //Assert
        assertTrue(result);
    }

    /**
     * Integration Test to configure a location.
     * Arrange the atributes of a location.
     * Act to create and configure the location.
     * Assert the location was successfully configured.
     */
    @Test
    void configureLocation_ReturnsFalseWhenInvalidParameters() throws InstantiationException {
        //Arrange
        String houseName = "myHouse";
        House house = new House(houseName);
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = null;
        double longitude = 0;
        double latitude = 0;
        FactoryLocation factoryLocation = new ImplFactoryLocation();
        ConfigureLocationCTRL ctrl = new ConfigureLocationCTRL(house);
        //Act
        boolean result = ctrl.configureLocation(doorReference, buildingNumber, streetName, city, country, zipCode,latitude, longitude,factoryLocation);
        //Assert
        assertFalse(result);
    }
}