package SmartHome.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest
{
    @Test
    void NewValidRoom() throws InstantiationException {
        // arrange

        // act
        new Room( "Living Room", 0, 10, 9, 2.5);

        // act
        // currently there is no methods to access the object, hence, for now, there is no way to check it
        // then, for now, if it is created, it is ok
    }

    @Test
    void NewEmptyNameRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "", 0, 10, 9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNullNameRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( null, 0, 10, 9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewZeroLengthRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, 0, 9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNegativeLengthRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, -1, 9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewZeroWidthRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, 10, 0, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNegativeWidthRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, 10, -9, 2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewZeroHeightRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, 10, 9, 0)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void NewNegativeHeightRoom()
    {
        // arrange
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            new Room( "Living Room", 0, 10, 9, -2.5)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void originallyRoomHasNoDevices() throws InstantiationException
    {
        // arrange
        Room room = new Room( "Living Room", 0, 10, 9, 2.5);

        // act

        // assert
        assertEquals(room.getDevices().size(), 0);
    }
    @Test
    void addValidDevice() throws InstantiationException
    {
        // arrange
        Room room = new Room( "Living Room", 0, 10, 9, 2.5);

        // act
        room.addDevice( "device1");

        // assert
        assertEquals( room.getDevices().size(), 1);
    }

    @Test
    void add2ValidDevices() throws InstantiationException
    {
        // arrange
        Room room = new Room( "Living Room", 0, 10, 9, 2.5);

        // act
        room.addDevice( "device1");
        room.addDevice( "device2");

        // assert
        assertEquals( room.getDevices().size(), 2);
    }

    @Test
    void NewEmptyNameDevice() throws InstantiationException
    {
        // arrange
        Room livingRoom = new Room( "Living Room", 0, 10, 9, 2.5);
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            livingRoom.addDevice( "")
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(livingRoom.getDevices().size(), 0);
    }

    @Test
    void NewNullNameDevice() throws InstantiationException
    {
        // arrange
        Room livingRoom = new Room( "Living Room", 0, 10, 9, 2.5);
        String expectedMessage = "Invalid arguments";

        // act + assert
        Exception exception = assertThrows(InstantiationException.class, () ->
            livingRoom.addDevice( null)
        );

        // assert
        String actualMessage = exception.getMessage();

        // act
        assertTrue(actualMessage.contains(expectedMessage));
        assertEquals(livingRoom.getDevices().size(), 0);
    }
}