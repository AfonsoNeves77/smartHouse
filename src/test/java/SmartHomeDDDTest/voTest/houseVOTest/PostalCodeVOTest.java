package SmartHomeDDDTest.voTest.houseVOTest;

import SmartHomeDDD.vo.houseVO.PostalCodeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeVOTest {

    @Test
    void testValidParameter_returnsPostalCodeVO() throws InstantiationException {
        String postalCode = "PT-1234-567";
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);
        assertNotNull(postalCodeVO);
    }

    @Test
    void testNullParameter_throwsIllegalArgumentException() {
        String postalCode = null;
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeVO(postalCode));
    }

    @Test
    void testEmptyParameter_throwsIllegalArgumentException() {
        String postalCode = "";
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeVO(postalCode));
    }

    /**
     * This test checks if when trying to create a PostalCodeVO object with an invalid prefix
     * an IllegalArgumentException is thrown
     */

    @Test
    void testInvalidPostalCode_throwsIllegalArgumentException() {
        String postalCode = "UK-1234-567";
        assertThrows(IllegalArgumentException.class, () -> new PostalCodeVO(postalCode));
    }

    @Test
    void testGetPostalCode_returnsPostalCode() throws InstantiationException {
        String postalCode = "PT-1234-567";
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);
        assertEquals(postalCode, postalCodeVO.getValue());
    }
}