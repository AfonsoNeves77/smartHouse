package SmartHomeDDDTest.voTest.actuatorVOTest;

import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Represents a test class for ActuatorIDVO.
 */

class ActuatorIDVOTest {

    /**
     * Test case to verify that the ActuatorIDVO constructor throws an IllegalArgumentException
     * when given a null identifier. The result exception description must match the expected one.
     */

    @Test
    void givenNullIdentifier_whenCreatingActuatorIDVO_thenThrowsIllegalArgumentException() {
//        Arrange
        String expected = "Invalid Identifier";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorIDVO(null));
        String result = exception.getMessage();
//        Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify that the getID method of ActuatorIDVO returns the correct ID as a string.
     */

    @Test
    void givenValidIdentifier_whenCreatingActuatorIDVO_thenReturnsCorrectIDAsString() {
//        Arrange
        UUID actuatorIdentifier = UUID.randomUUID();
        String expected = actuatorIdentifier.toString();
//        Act
        ActuatorIDVO actuatorID = new ActuatorIDVO(actuatorIdentifier);
        String result = actuatorID.getID();
//        Assert
        assertEquals(expected, result);
    }
}