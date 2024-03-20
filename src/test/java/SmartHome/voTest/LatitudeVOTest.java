package SmartHome.voTest;

import SmartHome.vo.LatitudeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatitudeVOTest {

    @Test
    void whenInvalidMinimumLatitude_thenThrowsIllegalArgumentException(){

        //Arrange
        double minimumValue = -90.1;
        String expected = "Invalid latitude value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->new LatitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenInvalidMaximumLatitude_thenThrowsIllegalArgumentException(){

        //Arrange
        double minimumValue = 90.1;
        String expected = "Invalid latitude value";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class,() ->new LatitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLatitudeAsString_LastValidValue()  {

        //Arrange
        double maximumValue = 90.0;
        String expected = "90.0";

        //Act
        LatitudeVO latitudeVO = new LatitudeVO(maximumValue);
        String result = latitudeVO.toString();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLatitudeAsString_FirstValidValue()  {

        //Arrange
        double minimumValue = -90.0;
        String expected = "-90.0";

        //Act
        LatitudeVO latitudeVO = new LatitudeVO(minimumValue);
        String result = latitudeVO.toString();

        //Assert
        assertEquals(expected,result);
    }

}