package SmartHome.voTest;

import SmartHome.vo.LatitudeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LatitudeVOTest {

    @Test
    void whenInvalidMinimumLatitude_thenThrowsInstantiationException(){

        //Arrange
        double minimumValue = -90.1;
        String expected = "Invalid latitude value";

        //Act
        Exception exception = assertThrows(InstantiationException.class,() ->new LatitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenInvalidMaximumLatitude_thenThrowsInstantiationException(){

        //Arrange
        double minimumValue = 90.1;
        String expected = "Invalid latitude value";

        //Act
        Exception exception = assertThrows(InstantiationException.class,() ->new LatitudeVO(minimumValue));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void toStringShouldReturnCorrectLatitudeAsString_LastValidValue() throws InstantiationException {

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
    void toStringShouldReturnCorrectLatitudeAsString_FirstValidValue() throws InstantiationException {

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