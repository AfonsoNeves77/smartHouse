package SmartHome.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import SmartHome.domain.House;
import SmartHome.dto.LocationDTO;

class DefineHouseLocationControllerTest {

    @Test
    void defineValidHouseLocation() throws InstantiationException
    {
        // arrange
        House house = new House();

        String strStreet = "Rua de S. Tomé, s/n";
        String strPostalCode = "4200 Porto";

        DefineHouseLocationController controller = new DefineHouseLocationController( house );

        // act
        LocationDTO locationDTO = controller.defineHouseLocation( strStreet, strPostalCode );

        // assert
        assertEquals(locationDTO.street, strStreet);
        assertEquals(locationDTO.postalCode, strPostalCode);
    }

    @Test
    void defineEmptyStreetHouseLocation()
    {
        // arrange
        House house = new House();

        String strStreet = "";
        String strPostalCode = "4200 Porto";
        String expectedMessage = "Invalid Street or Postal Code";

        DefineHouseLocationController controller = new DefineHouseLocationController( house );

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            controller.defineHouseLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void defineEmptyPostalCodeHouseLocation()
    {
        // arrange
        House house = new House();

        String strStreet = "Rua de S. Tomé, s/n";
        String strPostalCode = "";

        String expectedMessage = "Invalid Street or Postal Code";

        DefineHouseLocationController controller = new DefineHouseLocationController( house );

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            controller.defineHouseLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void defineNullStreetAndPostalCodeHouseLocation()
    {
        // arrange
        House house = new House();

        String strStreet = null;
        String strPostalCode = null;

        String expectedMessage = "Invalid Street or Postal Code";

        DefineHouseLocationController controller = new DefineHouseLocationController( house );

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            controller.defineHouseLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}