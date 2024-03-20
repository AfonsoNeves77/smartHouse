package SmartHome.voTest;

import SmartHome.vo.GpsVO;
import SmartHome.vo.LatitudeVO;
import SmartHome.vo.LongitudeVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GpsVOTest {

    @Test
    void givenValidCoordinates_whenGetLatitude_ThenReturnLatitudeValue(){
        //Arrange
        LatitudeVO doubleLatitude = mock(LatitudeVO.class);
        LongitudeVO doubleLongitude = mock(LongitudeVO.class);
        when(doubleLatitude.getLatitude()).thenReturn(75.7);

        GpsVO newGPS = new GpsVO(doubleLatitude, doubleLongitude);
        double expected = 75.7;

        //Act
        double result = newGPS.getLatitude();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void givenValidCoordinates_whenGetLongitude_ThenReturnLongitudeValue(){
        //Arrange
        LatitudeVO doubleLatitude = mock(LatitudeVO.class);
        LongitudeVO doubleLongitude = mock(LongitudeVO.class);
        when(doubleLongitude.getLongitude()).thenReturn(155.3);

        GpsVO newGPS = new GpsVO(doubleLatitude, doubleLongitude);
        double expected = 155.3;

        //Act
        double result = newGPS.getLongitude();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void whenNullLatitude_ThenThrowsIllegalArgumentException(){
        //Arrange
        LongitudeVO doubleLongitude = mock(LongitudeVO.class);
        String expected = "Latitude and longitude cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GpsVO(null, doubleLongitude);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    @Test
    void whenNullLongitude_ThenThrowsIllegalArgumentException(){
        //Arrangeq
        LatitudeVO doubleLatitude = mock(LatitudeVO.class);
        String expected = "Latitude and longitude cannot be null.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new GpsVO(doubleLatitude, null);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

}