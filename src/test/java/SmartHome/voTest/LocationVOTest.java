package SmartHome.voTest;

import SmartHome.vo.AddressVO;
import SmartHome.vo.GpsVO;
import SmartHome.vo.LocationVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LocationVOTest {

    @Test
    void whenConstructorCalled_thenNewLocationInstantiated(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);

        //Act

        LocationVO locationVO = new LocationVO(addressVO,gpsVO);

        //Assert
        assertNotNull(locationVO);
    }

    @Test
    void whenAddressIsNull_thenThrowException(){
        //Arrange
        AddressVO addressVO = null;
        GpsVO gpsVO = mock(GpsVO.class);
        String expected = "Invalid parameter.";

        //Act

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LocationVO(addressVO,gpsVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGpsIsNull_thenThrowException(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = null;
        String expected = "Invalid parameter.";

        //Act

        Exception exception = assertThrows(IllegalArgumentException.class, () -> new LocationVO(addressVO,gpsVO));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetDoorCalled_thenReturnDoor(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(addressVO.getDoor()).thenReturn("1904");
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "1904";

        //Act
        String result = locationVO.getDoor();

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void whenGetStreetCalled_thenReturnStreet(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(addressVO.getStreet()).thenReturn("Praça da avenida da rua");
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "Praça da avenida da rua";

        //Act
        String result = locationVO.getStreet();

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void whenGetCityCalled_thenReturnCity(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(addressVO.getCity()).thenReturn("Polis");
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "Polis";

        //Act
        String result = locationVO.getCity();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetCountryCalled_thenReturnCountry(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(addressVO.getCountry()).thenReturn("Haiti");
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "Haiti";

        //Act
        String result = locationVO.getCountry();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetPostalCodeCalled_thenReturnPostalCode(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(addressVO.getPostalCode()).thenReturn("1848-212");
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "1848-212";

        //Act
        String result = locationVO.getPostalCode();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetLatitudeCalled_thenReturnLatitude(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(gpsVO.getLatitude()).thenReturn( 34.5);
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        double expected = 34.5;

        //Act
        double result = locationVO.getLatitude();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetLongitudeCalled_thenReturnLongitude(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        GpsVO gpsVO = mock(GpsVO.class);
        when(gpsVO.getLongitude()).thenReturn( 134.5);
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        double expected = 134.5;

        //Act
        double result = locationVO.getLongitude();

        //Assert
        assertEquals(expected,result);
    }

    @Test
    void whenGetLocationAsString_returnLocation(){
        //Arrange
        AddressVO addressVO = mock(AddressVO.class);
        when(addressVO.getDoor()).thenReturn("1904");
        when(addressVO.getStreet()).thenReturn("Praça da avenida da rua");
        when(addressVO.getCity()).thenReturn("Polis");
        when(addressVO.getCountry()).thenReturn("Haiti");
        when(addressVO.getPostalCode()).thenReturn("1848-212");
        GpsVO gpsVO = mock(GpsVO.class);
        when(gpsVO.getLatitude()).thenReturn( 34.5);
        when(gpsVO.getLongitude()).thenReturn( 134.5);
        LocationVO locationVO = new LocationVO(addressVO,gpsVO);
        String expected = "Location: Address - Praça da avenida da rua, 1904. 1848-212, Polis, Haiti. GPS - latitude: 34.5, longitude: 134.5";

        //Act
        String result = locationVO.toString();

        //Assert
        assertEquals(expected,result);
    }
}
