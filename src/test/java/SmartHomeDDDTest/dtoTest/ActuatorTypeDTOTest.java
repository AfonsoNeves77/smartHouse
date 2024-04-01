package SmartHomeDDDTest.dtoTest;

import SmartHomeDDD.dto.ActuatorTypeDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ActuatorTypeDTOTest {

    /**
     * Test the constructor of ActuatorTypeDTO with null type
     * Expecting IllegalArgumentException
     */
    @Test
    void givenNullType_ThenThrowException() {
        String type = null;
        assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeDTO(type));
    }

    /**
     * Test the constructor of ActuatorTypeDTO with empty type
     * Expecting IllegalArgumentException
     */
    @Test
    void givenEmptyType_ThenThrowException() {
        String type = "";
        assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeDTO(type));
    }

    /**
     * Test the constructor of ActuatorTypeDTO with valid type
     * Expecting ActuatorTypeDTO object
     * Assert the type of the object is the same as the input
     */
    @Test
    void givenType_ThenReturnActuatorTypeDTO() {
        String type = "type";
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(type);
        assertEquals(type, actuatorTypeDTO.getActuatorTypeID());
    }
}