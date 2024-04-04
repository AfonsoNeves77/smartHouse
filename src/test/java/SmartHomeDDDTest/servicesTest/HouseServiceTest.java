package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.domain.house.HouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.services.HouseService;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.houseVO.LocationVO;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for HouseService
 */

public class HouseServiceTest {

    /**
     * Test case to check if IllegalArgumentException is thrown when HouseRepository is null
     */

    @Test
    void givenNullHouseRepository_whenHouseServiceIsCreated_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepository = null;
        HouseFactory houseFactory = new HouseFactory();
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseService(houseRepository, houseFactory));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when HouseFactory is null
     */

    @Test
    void givenNullHouseFactory_whenHouseServiceIsCreated_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepository = new HouseRepository();
        HouseFactory houseFactory = null;
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseService(houseRepository, houseFactory));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when LocationVO is null
     */

    @Test
    void givenNullLocationVO_whenCreateNewHouseIsCalled_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
        LocationVO locationVO = null;
        String expectedMessage = "Invalid location";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> houseService.createNewHouse(locationVO));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if House object is created when valid LocationVO is passed
     * It calls the createHouse method of the HouseFactory object to create a new House object.
     */

    @Test
    void givenValidLocationVO_whenCreateNewHouseIsCalled_thenReturnCreatedHouse() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
        LocationVO locationVODouble = mock(LocationVO.class);
        House house = mock(House.class);
        when(houseFactoryDouble.createHouse(locationVODouble)).thenReturn(house);
//        Act
        House actualHouse = houseService.createNewHouse(locationVODouble);
//        Assert
        assertEquals(house, actualHouse);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when LocationVO is null
     * It calls the configureLocation method of the House object to update the location.
     */

    @Test
    void givenNullLocationVO_whenUpdateLocationIsCalled_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
        LocationVO locationVO = null;
        String expectedMessage = "Invalid location";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> houseService.updateLocation(locationVO));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if false is returned when House object is not found
     * It calls the configureLocation method of the House object to update the location.
     */

    @Test
    void givenNoHouse_whenUpdateLocationIsCalled_thenReturnFalse() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
        LocationVO locationVO = mock(LocationVO.class);
//        Act
        boolean result = houseService.updateLocation(locationVO);
//        Assert
        assertFalse(result);
    }

    /**
     * Test case to check if true is returned when House object is found and location is updated
     * It calls the configureLocation method of the House object to update the location.
     */

    @Test
    void givenValidLocationVO_whenUpdateLocationIsCalled_thenReturnTrue() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        House houseDouble = mock(House.class);
        when(houseRepositoryDouble.getFirstHouse()).thenReturn(houseDouble);
        LocationVO locationVO = mock(LocationVO.class);
        when(houseDouble.configureLocation(locationVO)).thenReturn(true);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
//        Act
        boolean result = houseService.updateLocation(locationVO);
//        Assert
        assertTrue(result);
    }

    /**
     * Test case to check if false is returned when House object is found but location is not updated
     * It calls the configureLocation method of the House object to update the location.
     */

    @Test
    void givenValidLocationVO_whenUpdateLocationCalledFailedScenario_thenReturnFalse() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        House houseDouble = mock(House.class);
        when(houseRepositoryDouble.getFirstHouse()).thenReturn(houseDouble);
        LocationVO locationVO = mock(LocationVO.class);
        when(houseDouble.configureLocation(locationVO)).thenReturn(false);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
//        Act
        boolean result = houseService.updateLocation(locationVO);
//        Assert
        assertFalse(result);
    }

    /**
     * Test case to check if House object is returned when getFirstHouse is called
     * It calls the getFirstHouse method of the HouseRepository object to get the first House object.
     */

    @Test
    void givenHouseInRepository_whenGetFirstHouseIDVOIsCalled_thenReturnHouseIDVO() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        HouseIDVO houseIDVODouble = mock(HouseIDVO.class);
        when(houseIDVODouble.getID()).thenReturn("f47ac10b-58cc-4372-a567-0e02b2c3d479");
        when(houseRepositoryDouble.getFirstHouseIDVO()).thenReturn(houseIDVODouble);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
//        Act
        HouseIDVO result = houseService.getFirstHouseIDVO();
//        Assert
        assertEquals(houseIDVODouble, result);
    }

    /**
     * Test case to check if NoSuchElementException is thrown when House object is not found
     * It calls the getFirstHouse method of the HouseRepository object to get the first House object.
     */

    @Test
    void givenHouseNotInRepository_whenGetFirstHouseIDVOIsCalled_thenThrowException() {
//        Arrange
        HouseRepository houseRepositoryDouble = mock(HouseRepository.class);
        HouseFactory houseFactoryDouble = mock(HouseFactory.class);
        when(houseRepositoryDouble.getFirstHouseIDVO()).thenReturn(null);
        HouseService houseService = new HouseService(houseRepositoryDouble, houseFactoryDouble);
        String expectedMessage = "No House ID found";
//        Act
        Exception exception = assertThrows(NoSuchElementException.class, () -> houseService.getFirstHouseIDVO());
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(actualMessage, expectedMessage);
    }
}
