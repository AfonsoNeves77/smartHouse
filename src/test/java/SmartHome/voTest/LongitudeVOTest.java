package SmartHome.voTest;

import SmartHome.vo.LongitudeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongitudeVOTest {

    @Test
    void whenGivenInvalidMinimumLongitude_thenThrowsInstantiationException(){

        //Arrange
        double minimumValue = -180.1;
        String expected = "Invalid longitude value";

        //Act
        Exception exception = assertThrows(InstantiationException.class,() ->new LongitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGivenInvalidMaximumLongitude_thenThrowsInstantiationException(){

        //Arrange
        double minimumValue = 180.1;
        String expected = "Invalid longitude value";

        //Act
        Exception exception = assertThrows(InstantiationException.class,() ->new LongitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLongitudeAsString_LastValidValue() throws InstantiationException {

        //Arrange
        double maximumValue = 180;
        String expected = "180.0";

        //Act
        LongitudeVO longitudeVO = new LongitudeVO(maximumValue);
        String result = longitudeVO.toString();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLongitudeAsString_FirstValidValue() throws InstantiationException {

        //Arrange
        double minimumValue = -180;
        String expected = "-180.0";

        //Act
        LongitudeVO longitudeVO = new LongitudeVO(minimumValue);
        String result = longitudeVO.toString();

        //Assert
        assertEquals(expected,result);
    }

}