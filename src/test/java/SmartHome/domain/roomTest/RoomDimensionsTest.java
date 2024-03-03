package SmartHome.domain.roomTest;

import SmartHome.domain.room.RoomDimensions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoomDimensionsTest {

    /**
     * Test01 case to verify that attempting to create RoomDimensions with negative width results in an InstantiationException.
     */
    @Test
    void invalidDimensions_NegativeWidth() {
        //Arrange
        double roomWidth = -3;
        double roomLength = 1;
        double roomHeight = 2;
        String expectedMessage = "Please insert valid room dimensions.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomDimensions(roomWidth, roomLength, roomHeight);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test02 case to verify that attempting to create RoomDimensions with zero width results in an InstantiationException.
     */
    @Test
    void invalidDimensions_ZeroWidth() {
        //Arrange
        double roomWidth = 0;
        double roomLength = 1;
        double roomHeight = 2;
        String expectedMessage = "Please insert valid room dimensions.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomDimensions(roomWidth, roomLength, roomHeight);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test03 case to verify that attempting to create RoomDimensions with negative length results in an InstantiationException.
     */
    @Test
    void invalidDimensions_NegativeLength() {
        //Arrange
        double roomWidth = 3;
        double roomLength = -1;
        double roomHeight = 2;
        String expectedMessage = "Please insert valid room dimensions.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomDimensions(roomWidth, roomLength, roomHeight);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test04 case to verify that attempting to create RoomDimensions with zero length results in an InstantiationException.
     */
    @Test
    void invalidDimensions_ZeroLength() {
        //Arrange
        double roomWidth = 3;
        double roomLength = 0;
        double roomHeight = 2;
        String expectedMessage = "Please insert valid room dimensions.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomDimensions(roomWidth, roomLength, roomHeight);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test05 case to verify that attempting to create RoomDimensions with negative height results in an InstantiationException.
     */
    @Test
    void invalidDimensions_NegativeHeight() {
        //Arrange
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = -2;
        String expectedMessage = "Please insert valid room dimensions.";
        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RoomDimensions(roomWidth, roomLength, roomHeight);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expectedMessage, result);
    }

    /**
     * Test06 case to verify that creating RoomDimensions with zero height is successful.
     * Compares the roomHeight of the room with the one extracted.
     *
     * @throws InstantiationException if there is an issue with instantiating the RoomDimensions.
     */
    @Test
    void validDimensions_ZeroHeight() {
        //Arrange
        double roomWidth = 3;
        double roomLength = 2;
        double roomHeight = 0;
        double expected = 0;
        //Act
        RoomDimensions dimensions = new RoomDimensions(roomWidth, roomLength, roomHeight);
        double result = dimensions.getRoomHeight();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test07 case to verify that creating RoomDimensions is successful, and width is correctly set.
     * Compares the roomWidth of the room with the one extracted.
     *
     * @throws InstantiationException if there is an issue with instantiating the RoomDimensions.
     */
    @Test
    void createDimensions_SuccessByWidth() {
        //Arrange
        double roomWidth = 3.5;
        double roomLength = 1;
        double roomHeight = 2;
        RoomDimensions dimensions = new RoomDimensions(roomWidth, roomLength, roomHeight);
        double expected = 3.5;
        //Act
        double result = dimensions.getRoomWidth();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test08 case to verify that creating RoomDimensions is successful, and length is correctly set.
     * Compares the roomLength of the room with the one extracted.
     *
     * @throws InstantiationException if there is an issue with instantiating the RoomDimensions.
     */
    @Test
    void createDimensions_SuccessByLength()  {
        //Arrange
        double roomWidth = 3.5;
        double roomLength = 1;
        double roomHeight = 2;
        RoomDimensions dimensions = new RoomDimensions(roomWidth, roomLength, roomHeight);
        double expected = 1;
        //Act
        double result = dimensions.getRoomLength();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test09 case to verify that creating RoomDimensions is successful, and height is correctly set.
     * Compares the roomLength of the room with the one extracted.
     *
     * @throws InstantiationException if there is an issue with instantiating the RoomDimensions.
     */
    @Test
    void createDimensions_SuccessByHeight()  {
        //Arrange
        double roomWidth = 3.5;
        double roomLength = 1;
        double roomHeight = 2;
        RoomDimensions dimensions = new RoomDimensions(roomWidth, roomLength, roomHeight);
        double expected = 2;
        //Act
        double result = dimensions.getRoomHeight();
        //Assert
        assertEquals(expected, result);
    }
}