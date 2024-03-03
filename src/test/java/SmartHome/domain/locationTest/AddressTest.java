package SmartHome.domain.locationTest;

import org.junit.jupiter.api.Test;
import SmartHome.domain.location.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddressTest {

    /**
     * Test 01
     * Test to create an address with an emtpy door reference.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfDoorRefEmpty() {
        //Arrange
        // 1.
        String doorReference = " ";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 02
     * Test to create an address with a null door reference.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfDoorRefNull() {
        //Arrange
        // 1.
        String doorReference = null;
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Test to create an address with an empty building number.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfBuildingNumberEmpty() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = " ";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
    /**
     * Test 03
     * Test to create an address with a null building number.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfBuildingNumberNull() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = null;
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
    /**
     * Test 03
     * Test to create an address with an empty street name.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfStreetNameEmpty() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = " ";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
    /**
     * Test 03
     * Test to create an address with a null street name.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfStreetNameNull() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = null;
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
    /**
     * Test 03
     * Test to create an address with an empty city.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfCityEmpty() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = " ";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
    /**
     * Test 03
     * Test to create an address with a null city.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfCityNull() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = null;
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Test to create an address with an empty country.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */@Test
    void constructor_throwsExceptionIfCountryEmpty() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = " ";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Test to create an address with a null country.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */

    @Test
    void constructor_throwsExceptionIfCountryNull() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = null;
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Test to create an address with an empty zip cody.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with invalid parameters.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfZipCodeEmpty() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = " ";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 03
     * Test to create an address with a null zip code.
     * Arrange an address with a door reference, building number, street name, city, country and zip code.
     * Act to create an address with null parameters.
     * Assert that an exception is thrown.
     */

    @Test
    void constructor_throwsExceptionIfZipCodeNull() {
        //Arrange
        // 1.
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = null;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Address(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
}
