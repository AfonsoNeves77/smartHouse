package SmartHome.voTest;

import SmartHome.vo.PostalCodeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PostalCodeVOTest {

    @Test
    void testValidParameter_returnsPostalCodeVO() throws InstantiationException {
        String postalCode = "1234-567";
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);
        assertNotNull(postalCodeVO);
    }

    @Test
    void testNullParameter_throwsInstantiationException() {
        String postalCode = null;
        assertThrows(InstantiationException.class, () -> new PostalCodeVO(postalCode));
    }

    @Test
    void testEmptyParameter_throwsInstantiationException() {
        String postalCode = "";
        assertThrows(InstantiationException.class, () -> new PostalCodeVO(postalCode));
    }

    @Test
    void testGetPostalCode_returnsPostalCode() throws InstantiationException {
        String postalCode = "Portugal";
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);
        assertEquals(postalCode, postalCodeVO.getCountry());
    }
}