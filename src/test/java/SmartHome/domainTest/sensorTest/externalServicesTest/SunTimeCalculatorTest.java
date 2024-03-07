package SmartHome.domainTest.sensorTest.externalServicesTest;

import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import org.junit.jupiter.api.Test;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class SunTimeCalculatorTest {
    /**
     * This test verifies that the computeSunsetTime() method returns the expected sunset time,
     * for a given date and coordinates.
     */
    @Test
    void getSunsetTime_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunsetTime(date, coordinates);
        String result = resultZoneDateTime.toString();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that if an invalid date format is provided, the method returns null
     * instead of attempting to compute the sunset time.
     */
    @Test
    void getSunsetTimeInvalidFormatDate_ShouldReturnNull_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "11/09/2026";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunsetTime(date, coordinates);
        //Assert
        assertNull(resultZoneDateTime);
    }

    /**
     * This test ensures that if invalid coordinates are provided, the method returns null
     * instead of attempting to compute the sunset time.
     */
    @Test
    void getSunsetTimeInvalidCoordinates_ShouldReturnNull_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024/09/11";
        String coordinates = "41.1579 : -198.6291";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunsetTime(date, coordinates);
        //Assert
        assertNull(resultZoneDateTime);
    }

    /**
     * Similar to the first test, this test checks that the computeSunrise() method returns the expected sunrise
     * time in the specified format for a given date and coordinates.
     */
    @Test
    void getSunriseTime_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-06T07:00:19Z[Europe/Lisbon]";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunrise(date, coordinates);
        String result = resultZoneDateTime.toString();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * This test verifies that if an invalid date format is provided to computeSunrise(), it returns null
     * as expected, indicating an error.
     */
    @Test
    void getSunriseTimeInvalidFormatDate_ShouldReturnNull_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "11/09/2026";
        String coordinates = "41.1579 : -8.6291";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(resultZoneDateTime);
    }

    /**
     * This test ensures that if invalid coordinates are provided, the method returns null
     * instead of attempting to compute the sunrise time.
     */
    @Test
    void getSunriseTimeInvalidCoordinates_ShouldReturnNull_IntegrationTest()  {
        //Arrange
        SunTimeCalculator sunTimeCalculator = new SunTimeCalculator();
        String date = "2024/09/11";
        String coordinates = "41.1579 : -198.6291";
        //Act
        ZonedDateTime resultZoneDateTime = sunTimeCalculator.computeSunrise(date, coordinates);
        //Assert
        assertNull(resultZoneDateTime);
    }
}