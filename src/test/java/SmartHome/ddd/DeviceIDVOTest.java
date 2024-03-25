package SmartHome.ddd;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DeviceIDVOTest {
    /**
     * Test case to verify that the DeviceIDVO constructor throws an IllegalArgumentException
     * when given a null identifier. The result exception description must also match the expected one.
     */
    @Test
    public void givenNullIdentifier_ShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid Identifier";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceIDVO(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);

    }

    /**
     * Test case to verify that the getID method of DeviceIDVO returns the correct ID as a string.
     */
    @Test
    public void getIDShouldReturnCorrectIDAsString(){

        //Arrange
        UUID deviceIdentifier = UUID.randomUUID();
        String expected = deviceIdentifier.toString();

        //Act
        DeviceIDVO deviceID = new DeviceIDVO(deviceIdentifier);
        String result = deviceID.getID();

        //Assert
        assertEquals(expected,result);

    }
}