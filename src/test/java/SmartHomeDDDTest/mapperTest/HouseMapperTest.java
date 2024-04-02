package SmartHomeDDDTest.mapperTest;

import SmartHomeDDD.dto.LocationDTO;
import SmartHomeDDD.vo.houseVO.LocationVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static SmartHomeDDD.mapper.HouseMapper.dtoToDomain;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseMapperTest {

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct door information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectDoorInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 1.0);
        String expected = "25A";

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getDoor()).thenReturn("25A");
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            String result = locationVO.getDoor();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test checks if when trying to convert a null LocationDTO object to a LocationVO object
     * an IllegalArgumentException is thrown
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldThrowIllegalArgumentException() {
        //Arrange
        LocationDTO locationDTO = null;
        String expected = "LocationDTO is null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            dtoToDomain(locationDTO);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct street information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectStreetInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 1.0);
        String expected = "Rua da Alegria";

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getStreet()).thenReturn("Rua da Alegria");
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            String result = locationVO.getStreet();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(1, listOfMockedSensorIDVO.size());
        assertEquals(expected, result);
        }
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct city information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectCityInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 1.0);
        String expected = "Porto";

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getCity()).thenReturn("Porto");
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            String result = locationVO.getCity();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct country information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectCountryInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 1.0);
        String expected = "Portugal";

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getCountry()).thenReturn("Portugal");
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            String result = locationVO.getCountry();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct postal code information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectPostalCodeInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 1.0);
        String expected = "4444-222";

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getPostalCode()).thenReturn("4444-222");
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            String result = locationVO.getPostalCode();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct latitude information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectLatitudeInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 5.0);
        double expected = 1.0;

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getLatitude()).thenReturn(1.0);
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            double result = locationVO.getLatitude();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * This test is used to check if after using the dtoToDomain method to get a domain object
     * it is returning the correct longitude information
     */
    @Test
    public void whenDtoToDomainIsCalled_ShouldGetCorrectLongitudeInformation() {
        //Arrange
        LocationDTO locationDTO = new LocationDTO("25A", "Rua da Alegria", "Porto", "Portugal", "4444-222", 1.0, 5.0);
        double expected = 5.0;

        //Act
        try (MockedConstruction<LocationVO> mockedConstruction = mockConstruction(LocationVO.class, (mock, context) -> {
            when(mock.getLongitude()).thenReturn(5.0);
        })) {
            LocationVO locationVO = dtoToDomain(locationDTO);

            double result = locationVO.getLongitude();
            List<LocationVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }
}