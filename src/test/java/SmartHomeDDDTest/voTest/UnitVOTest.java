package SmartHomeDDDTest.voTest;

import SmartHomeDDD.vo.UnitVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitVOTest {
    /**
     * Test if the UnitVO is created with valid parameters
     * Should return a UnitVO
     */
    @Test
    void testValidParameters_ShouldReturnUnitVO() {
        // Given
        String unit = "Celsius";
        // When
        UnitVO unitVO = new UnitVO(unit);
        // Then
        assertNotNull(unitVO);
    }

    /**
     * Test if the UnitVO is created with null unit
     * Should throw IllegalArgumentException
     */
    @Test
    void testNullUnit_ShouldThrowIllegalArgumentException() {
        // Given
        String unit = null;
        // When
        // Then
        assertThrows(IllegalArgumentException.class, () -> new UnitVO(unit));
    }

    /**
     * Test if the UnitVO is created with empty unit
     * Should throw IllegalArgumentException
     */
    @Test
    void testEmptyUnit_ShouldReturnUnitVO() {
        // Given
        String unit = "";
        // When
        UnitVO unitVO = new UnitVO(unit);
        // Then
        assertEquals(unit, unitVO.getUnit());
    }


}