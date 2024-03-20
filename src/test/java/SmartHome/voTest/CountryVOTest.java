package SmartHome.voTest;

import SmartHome.vo.CountryVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CountryVOTest {

    @Test
    void testValidParameter_returnsCountryVO() throws InstantiationException {
        String country = "Portugal";
        CountryVO countryVO = new CountryVO(country);
        assertNotNull(countryVO);
    }

    @Test
    void testNullParameter_throwsInstantiationException() {
        String country = null;
        assertThrows(IllegalArgumentException.class, () -> new CountryVO(country));
    }

    @Test
    void testEmptyParameter_throwsInstantiationException() {
        String country = "";
        assertThrows(IllegalArgumentException.class, () -> new CountryVO(country));
    }

    @Test
    void testGetCountry_returnsCountry() throws InstantiationException {
        String country = "Portugal";
        CountryVO countryVO = new CountryVO(country);
        assertEquals(country, countryVO.getCountry());
    }
}