package SmartHome.domainTest.locationTest;
import SmartHome.domain.location.ImplFactoryLocation;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ImplfactoryLocationTest {
    @Test
    void createAddress_throwsExceptionIfDoorRefEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = " ";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress (doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfDoorRefNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = null;
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfBuildingNumberEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = " ";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfBuildingNumberNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = null;
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfStreetNameEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = " ";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfStreetNameNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = null;
        String city = "Porto";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfCityEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = " ";
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfCityNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = null;
        String country = "Portugal";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfCountryEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = " ";
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfCountryNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = null;
        String zipCode = "4570-222";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfZipCodeEmpty() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = " ";
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createAddress_throwsExceptionIfZipCodeNull() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        String doorReference = "2";
        String buildingNumber = "1234";
        String streetName = "Rua da Alegria";
        String city = "Porto";
        String country = "Portugal";
        String zipCode = null;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createAddress(doorReference, buildingNumber, streetName, city, country, zipCode);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createGPS_throwsExceptionIfLongitudeAbove() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        double longitude = 181;
        double latitude = 0;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createGPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createGPS_throwsExceptionIfLongitudeBelow() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        double longitude = -181;
        double latitude = 0;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createGPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createGPS_throwsExceptionIfLatitudeBelow(){
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        double longitude = 0;
        double latitude = -91;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createGPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    @Test
    void createGPS_throwsExceptionIfLatitudeAbove() {
        //Arrange
        // 1.
        ImplFactoryLocation factory = new ImplFactoryLocation();
        double longitude = 0;
        double latitude = 91;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createGPS (latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
}


