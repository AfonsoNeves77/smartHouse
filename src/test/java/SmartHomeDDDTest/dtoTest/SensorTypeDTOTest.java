package SmartHomeDDDTest.dtoTest;

import SmartHomeDDD.dto.SensorTypeDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SensorTypeDTOTest {

    /**
    * Test the constructor of SensorTypeDTO with null type
    * Expecting IllegalArgumentException
    */
@Test
void givenNullType_ThenThrowException() {
    String type = null;
    String unit = "C";
    assertThrows(IllegalArgumentException.class, () -> new SensorTypeDTO(type, unit));
}

    /**
     * Test the constructor of SensorTypeDTO with empty type
     * Expecting IllegalArgumentException
     */
    @Test
    void givenEmptyType_ThenThrowException() {
        String type = "";
        String unit = "C";
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeDTO(type, unit));
    }

    /**
     * Test the constructor of SensorTypeDTO with null unit
     * Expecting IllegalArgumentException
     */
    @Test
    void givenNullUnit_ThenThrowException() {
        String type = "Temperature";
        String unit = null;
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeDTO(type,unit));
    }

    /**
     * Test the constructor of SensorTypeDTO with empty unit
     * Expecting IllegalArgumentException
     */
    @Test
    void givenEmptyUnit_ThenThrowException() {
        String type = "Temperature";
        String unit = "";
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeDTO(type,unit));
    }

    /**
     * Test the constructor of SensorTypeDTO with valid type
     * Expecting SensorTypeDTO object
     * Assert the type of the object is the same as the input
     */
    @Test
    void createSensorTypeDTO_WhenGetType_ThenShouldReturnTypeAsString() {
        String type = "Temperature";
        String unit = "C";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(type,unit);
        assertEquals(type, sensorTypeDTO.getSensorTypeID());
    }
    /**
     * Test the constructor of SensorTypeDTO with valid unit
     * Expecting SensorTypeDTO object
     * Assert the unit of the object is the same as the input
     */
    @Test
    void createSensorTypeDTO_WhenGetUnit_ThenShouldReturnUnitAsString() {
        String type = "Temperature";
        String unit = "C";
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(type,unit);
        assertEquals(unit, sensorTypeDTO.getUnit());
    }
}