package SmartHome.domain.locationTest;
import SmartHome.domain.location.GPS;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GPSTest {

    /**
     * Test 01
     * Unit test to create a GPS location with an invalid longitude.
     * Arrange a factory, a latitude and a longitude.
     * Act to create a GPS location with an invalid longitude.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfLongitudeAbove() {
        //Arrange
        // 1.
        double longitude = 181;
        double latitude = 0;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }


    /**
     * Test 01
     * Unit test to create a GPS location with an invalid longitude.
     * Arrange a factory, a latitude and a longitude.
     * Act to create a GPS location with an invalid longitude.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfLongitudeBelow() {
        //Arrange
        // 1.
        double longitude = -181;
        double latitude = 0;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 01
     * Unit test to create a GPS location with an invalid latitude.
     * Arrange a factory, a latitude and a latitude.
     * Act to create a GPS location with an invalid latitude.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfLatitudeBelow(){
        //Arrange
        // 1.
        double longitude = 0;
        double latitude = -91;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GPS(latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test 01
     * Unit test to create a GPS location with an invalid latitude.
     * Arrange a factory, a latitude and a latitude.
     * Act to create a GPS location with an invalid latitude.
     * Assert that an exception is thrown.
     */
    @Test
    void constructor_throwsExceptionIfLatitudeAbove() {
        //Arrange
        // 1.
        double longitude = 0;
        double latitude = 91;
        String expectedMessage = "Invalid parameter.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GPS (latitude, longitude);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }
}
