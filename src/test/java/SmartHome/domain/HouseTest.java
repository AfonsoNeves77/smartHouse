package SmartHome.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseTest
{
    @Test
    void defineValidLocation() throws InstantiationException
    {
        // arrange
        House house = new House();

        String strStreet = "Rua de S. Tomé, s/n";
        String strPostalCode = "4200 Porto";

        // act
        Location location = house.defineLocation( strStreet, strPostalCode );

        // assert
        assertEquals(location.getStreet(), strStreet);
        assertEquals(location.getPostalCode(), strPostalCode);

        // how to check location set in house?
    }

    @Test
    void defineInvalidStreetLocation()
    {
        // arrange
        House house = new House();

        String strStreet = "";
        String strPostalCode = "4200 Porto";
        String expectedMessage = "Invalid Street or Postal Code";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            house.defineLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void defineInvalidPostalCodeLocation()
    {
        // arrange
        House house = new House();

        String strStreet = "Rua de S. Tomé, s/n";
        String strPostalCode = null;
        String expectedMessage = "Invalid Street or Postal Code";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            house.defineLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void defineInvalidStreetAndPostalCodeLocation()
    {
        // arrange
        House house = new House();

        String strStreet = null;
        String strPostalCode = null;
        String expectedMessage = "Invalid Street or Postal Code";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            house.defineLocation( strStreet, strPostalCode )
        );

        // assert
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void originallyHouseHasNoRooms()
    {

        // arrange
        House house = new House();

        // act
        List<Room> listRooms = house.getRooms();

        // assert
        assertEquals(listRooms.size(), 0);
    }

    @Test
    void addValidRoom() throws InstantiationException {

        // arrange
        House house = new House();

        // act
        Room livingRoom = house.addRoom( "Living Room", 0, 10, 9, 2.5);

        // assert
        assertEquals(house.getRooms().size(), 1);
        assertEquals( livingRoom.getDevices().size(), 0);
    }

    @Test
    void add2ValidRooms() throws InstantiationException
    {
        // arrange
        House house = new House();

        // act
        house.addRoom( "Living Room1", 0, 10, 9, 2.5);
        house.addRoom( "Living Room2", 0, 10, 9, 2.5);

        // assert
        assertEquals(house.getRooms().size(), 2);
    }
    @Test
    void addInvalidRoom() {
        // arrange
        House house = new House();
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            house.addRoom( null, 0, 10, 9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // assert
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(house.getRooms().size(), 0);
    }
}