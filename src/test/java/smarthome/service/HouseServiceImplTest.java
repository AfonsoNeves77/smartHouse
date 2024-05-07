package smarthome.service;

import org.junit.jupiter.api.Test;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactory;
import smarthome.domain.house.HouseFactoryImpl;
import smarthome.domain.vo.housevo.LocationVO;
import smarthome.persistence.HouseRepository;
import smarthome.persistence.mem.HouseRepositoryMem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Test class for HouseService
 */

class HouseServiceImplTest {

    /**
     * Test case to check if IllegalArgumentException is thrown when HouseRepository is null
     */

    @Test
    void givenNullHouseRepository_whenHouseServiceIsCreated_thenThrowIllegalArgumentException() {
//        Arrange
        HouseFactoryImpl v1HouseFactory = new HouseFactoryImpl();
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(null, v1HouseFactory));
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
        HouseRepositoryMem memHouseRepository = new HouseRepositoryMem();
        String expectedMessage = "Invalid parameters";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseServiceImpl(memHouseRepository, null));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when LocationVO is null on addHouse method
     */

    @Test
    void givenValidHouseRepositoryAndHouseFactory_whenAddHouseIsCalledWithInvalidLocation_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepository = mock(HouseRepository.class);
        HouseFactory houseFactory = mock(HouseFactory.class);
        HouseService houseService = new HouseServiceImpl(houseRepository, houseFactory);
        String expectedMessage = "Invalid location";
        LocationVO locationVO = null;
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> houseService.addHouse(locationVO));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if IllegalArgumentException is thrown when LocationVO is null on updateLocation
     */

    @Test
    void givenValidHouseRepositoryAndHouseFactory_whenUpdateLocationIsCalledWithInvalidLocation_thenThrowIllegalArgumentException() {
//        Arrange
        HouseRepository houseRepository = mock(HouseRepository.class);
        HouseFactory houseFactory = mock(HouseFactory.class);
        HouseService houseService = new HouseServiceImpl(houseRepository, houseFactory);
        String expectedMessage = "Invalid location";
        LocationVO locationVO = null;
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> houseService.updateLocation(locationVO));
//        Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test case to check if null House object is returned when HouseFactory returns null
     */

    @Test
    void givenValidHouseRepositoryAndHouseFactory_whenAddHouseIsCalledHouseCreatedAndSavedWithNullHouse_thenReturnNull() {
//        Arrange
        LocationVO locationVO = mock(LocationVO.class);

        HouseFactory houseFactory = mock(HouseFactory.class);
        when(houseFactory.createHouse(locationVO)).thenReturn(null);

        HouseRepository houseRepository = mock(HouseRepository.class);
        when(houseRepository.save(any(House.class))).thenReturn(true);

        HouseService houseService = new HouseServiceImpl(houseRepository, houseFactory);
//        Act
        House returnedHouse = houseService.addHouse(locationVO);
//        Assert
        assertNull(returnedHouse);
    }

    /**
     * Test case to check if House object is returned when HouseFactory returns a valid House object
     */

    @Test
    void givenValidHouseRepositoryAndHouseFactory_whenAddHouseIsCalledHouseCreatedAndSaved_thenReturnHouse() {
//        Arrange
        LocationVO locationVO = mock(LocationVO.class);
        House house = mock(House.class);

        HouseFactory houseFactory = mock(HouseFactory.class);
        when(houseFactory.createHouse(locationVO)).thenReturn(house);

        HouseRepository houseRepository = mock(HouseRepository.class);
        when(houseRepository.save(any(House.class))).thenReturn(true);

        HouseService houseService = new HouseServiceImpl(houseRepository, houseFactory);
//        Act
        House returnedHouse = houseService.addHouse(locationVO);
//        Assert
        assertEquals(house, returnedHouse);
    }
}