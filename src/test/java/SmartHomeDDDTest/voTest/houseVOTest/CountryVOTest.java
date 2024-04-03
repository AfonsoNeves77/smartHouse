package SmartHomeDDDTest.voTest.houseVOTest;

import SmartHomeDDD.vo.houseVO.CountryVO;
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
    void testNullParameter_throwsIllegalArgumentException() {
        String country = null;
        assertThrows(IllegalArgumentException.class, () -> new CountryVO(country));
    }

    @Test
    void testEmptyParameter_throwsIllegalArgumentException() {
        String country = "";
        assertThrows(IllegalArgumentException.class, () -> new CountryVO(country));
    }

    /**
     * This test checks if when trying to create a CountryVO object with an invalid country
     * an IllegalArgumentException is thrown
     */

    @Test
    void testInvalidCountry_throwsIllegalArgumentException() {
        String country = "Burkina Faso";
        assertThrows(IllegalArgumentException.class, () -> new CountryVO(country));
    }

    @Test
    void testGetCountry_returnsCountry() throws InstantiationException {
        String country = "USA";
        CountryVO countryVO = new CountryVO(country);
        assertEquals(country, countryVO.getValue());
    }
}