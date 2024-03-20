package SmartHome.voTest;

import SmartHome.vo.LongitudeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LongitudeVOTest {

    @Test
    void whenGivenInvalidMinimumLongitude_thenThrowsIllegalArgumentException(){

        //Arrange
        double minimumValue = -180.1;
        String expected = "Invalid longitude value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->new LongitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGivenInvalidMaximumLongitude_thenThrowsIllegalArgumentException(){

        //Arrange
        double minimumValue = 180.1;
        String expected = "Invalid longitude value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->new LongitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLongitudeAsString_LastValidValue()  {

        //Arrange
        double expected = 180;

        //Act
        LongitudeVO longitudeVO = new LongitudeVO(expected);
        double result = longitudeVO.getLongitude();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLongitudeAsString_FirstValidValue()  {

        //Arrange
        double expected = -180;

        //Act
        LongitudeVO longitudeVO = new LongitudeVO(expected);
        double result = longitudeVO.getLongitude();

        //Assert
        assertEquals(expected,result);
    }

}