package IntegrationTests;

import SmartHomeDDD.controller.ConfigureLocationCTRL;
import SmartHomeDDD.domain.house.HouseFactory;
import SmartHomeDDD.dto.LocationDTO;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.services.HouseService;
import SmartHomeDDD.vo.houseVO.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ConfigureLocationCTRL class
 */

public class ConfigureLocationCTRLTest {

    private HouseRepository houseRepository;
    private HouseFactory houseFactory;
    private HouseService houseService;
    private String door, street, city, country, postalCode;
    private double latitude, longitude;
    private LocationDTO locationDTO;

    /**
     * Method that initializes the house repository, house factory, house service, door, street, city, country,
     * postal code, latitude, longitude and location data transfer object
     */

    @BeforeEach
    void setUp() {
        houseRepository = new HouseRepository();
        houseFactory = new HouseFactory();
        houseService = new HouseService(houseRepository, houseFactory);
        door = "1";
        street = "Street";
        city = "City";
        country = "Portugal";
        postalCode = "PT-1234-567";
        latitude = 77.777;
        longitude = -89.999;
        locationDTO = new LocationDTO(door, street, city, country, postalCode, latitude, longitude);
    }

    /**
     * Method that tests the creation of a ConfigureLocationCTRL object
     * The test checks if the object is created successfully
     */

    @Test
    void givenNullHouseService_whenCreateConfigureLocationCTRL_thenThrowIllegalArgumentException() {
//        Arrange
        houseService = null;
//        Act + Assert
        assertThrows(IllegalArgumentException.class, () -> new ConfigureLocationCTRL(houseService));
    }

    /**
     * Method that tests the update of the location of the house
     * The test checks if the location data transfer object is null and returns false
     */

    @Test
    void givenNullLocationDTO_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        locationDTO = null;
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(locationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house
     * The test checks if the location data transfer object is valid and returns true
     * First, it creates all the necessary value objects to create a location value object
     * Then, it creates a location value object and a house by calling the createNewHouse method on house service
     * Finally, it creates a ConfigureLocationCTRL object and calls the updateLocation method with the location data transfer object
     */

    @Test
    void givenValidLocationDTO_whenUpdateLocation_thenReturnTrue() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";

        DoorVO door = new DoorVO(doorNumber);
        StreetVO street = new StreetVO(streetName);
        CityVO city = new CityVO(cityName);
        CountryVO country = new CountryVO(countryName);
        PostalCodeVO postalCode = new PostalCodeVO(postalCodeName);
        AddressVO addressVO = new AddressVO(door, street, city, country, postalCode);

        double latitudeValue = 77.777;
        double longitudeValue = -89.999;

        LatitudeVO latitude = new LatitudeVO(latitudeValue);
        LongitudeVO longitude = new LongitudeVO(longitudeValue);
        GpsVO gpsVO = new GpsVO(latitude, longitude);

        LocationVO locationVO = new LocationVO(addressVO, gpsVO);

        houseService.createNewHouse(locationVO);

        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(locationDTO);
//        Assert
        assertTrue(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null door number and returns false
     */

    @Test
    void givenNullDoorNumber_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = null;
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null street name and returns false
     */

    @Test
    void givenNullStreetName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = null;
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null city name and returns false
     */

    @Test
    void givenNullCityName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = null;
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null country name and returns false
     */

    @Test
    void givenNullCountryName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = null;
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has a null postal code name and returns false
     */

    @Test
    void givenNullPostalCodeName_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = null;
        double latitudeValue = 77.777;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has an invalid latitude value and returns false
     */

    @Test
    void givenInvalidLatitudeValue_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = -100.54321;
        double longitudeValue = -89.999;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when one of the values passed to the LocationDTO is invalid
     * The test checks if the location data transfer object has an invalid longitude value and returns false
     */

    @Test
    void givenInvalidLongitudeValue_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = "2";
        String streetName = "Street2";
        String cityName = "City2";
        String countryName = "France";
        String postalCodeName = "FR-1234-567";
        double latitudeValue = 77.777;
        double longitudeValue = -200.54321;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }

    /**
     * Method that tests the update of the location of the house when all the values passed to the LocationDTO are invalid
     * The test checks if the location data transfer object has all null or invalid values and returns false
     */

    @Test
    void givenLocationDTOWithAllNullOrInvalidValues_whenUpdateLocation_thenReturnFalse() {
//        Arrange
        String doorNumber = null;
        String streetName = null;
        String cityName = null;
        String countryName = null;
        String postalCodeName = null;
        double latitudeValue = -100.54321;
        double longitudeValue = -200.54321;
        LocationDTO secondLocationDTO = new LocationDTO(doorNumber, streetName, cityName, countryName, postalCodeName, latitudeValue, longitudeValue);
        ConfigureLocationCTRL configureLocationCTRL = new ConfigureLocationCTRL(houseService);
//        Act
        boolean result = configureLocationCTRL.updateLocation(secondLocationDTO);
//        Assert
        assertFalse(result);
    }
}
