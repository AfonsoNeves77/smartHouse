package SmartHomeDDDTest.repositoryTest;

import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HouseRepositoryTest {

    /**
     * Test for HouseRepository
     * Given a valid HouseEntity with a valid ID, when the method to save is called, then House is saved
     */
    @Test
    void givenHouseEntity_whenSave_thenHouseIsSaved(){
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house.getId()).thenReturn(houseID);
        // Act
        boolean result = houseRepository.save(house);
        // Assert
        assertTrue(result);
    }

    /**
     * Test for HouseRepository
     * Given a null HouseEntity, when the method to save is called, then House is not saved
     */
    @Test
    void givenNullHouseEntity_whenSave_thenHouseIsNotSaved(){
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = null;
        // Act
        boolean result = houseRepository.save(house);
        // Assert
        assertFalse(result);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with a null ID, when the method to save is called, then House is not saved
     */
    @Test
    void givenHouseEntityWithNullID_whenSave_thenHouseIsNotSaved(){
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = null;
        when(house.getId()).thenReturn(houseID);
        // Act
        boolean result = houseRepository.save(house);
        // Assert
        assertFalse(result);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with an ID already in the repository, when the method to save is called, then House is not
     * saved, returning false
     */
    @Test
    void givenHouseEntityWithRepeatedID_whenSave_thenHouseIsNotSaved(){
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house.getId()).thenReturn(houseID);
        houseRepository.save(house);
        // Act
        boolean result = houseRepository.save(house);
        // Assert
        assertFalse(result);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with a valid ID, when the method to find all is called, then House is found
     */
    @Test
    void whenHouseIsSaved_thenItShouldAppearInFindAll() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house1 = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house1.getId()).thenReturn(houseID);
        houseRepository.save(house1);
        // Act
        Iterable<House> foundHouses = houseRepository.findAll();
        boolean isHousePresent = StreamSupport.stream(foundHouses.spliterator(), false)
                .anyMatch(house -> house.equals(house1));
        // Assert
        assertTrue(isHousePresent);
    }


    /**
     * Test for HouseRepository
     * Given an empty repository, when the method to find all is called, then an empty iterable is returned
     */
    @Test
    void whenRepositoryIsEmpty_thenFindAllReturnsEmptyIterable() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        long expected = 0;
        // Act
        Iterable<House> foundHouses = houseRepository.findAll();
        long result = StreamSupport.stream(foundHouses.spliterator(), false).count();
        // Assert
        assertFalse(foundHouses.iterator().hasNext());
        assertEquals(expected, result);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with a valid ID, when the method to find by ID is called, then House is found
     */
    @Test
    void givenHouseID_whenFindById_thenHouseIsFound() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house.getId()).thenReturn(houseID);
        houseRepository.save(house);
        // Act
        House foundHouse = houseRepository.findById(houseID);
        // Assert
        assertEquals(house, foundHouse);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with a valid ID but not present in the repository, when the method to find by ID is called,
     * then House is not found
     */
    @Test
    void givenHouseNotPresent_thenReturnNull() {
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house.getId()).thenReturn(houseID);
        // Act
        House foundHouse = houseRepository.findById(houseID);
        //Assert
        assertNull(foundHouse);
    }


    /**
     * Test for HouseRepository
     * Given a HouseEntity with a valid ID, when the method to verify if present is called, then House is present
     */
    @Test
    void givenHouseID_whenIsPresent_thenHouseIsPresent(){
        // Arrange
        HouseRepository houseRepository = new HouseRepository();
        House house = mock(House.class);
        HouseIDVO houseID = mock(HouseIDVO.class);
        when(house.getId()).thenReturn(houseID);
        houseRepository.save(house);
        // Act
        boolean isPresent = houseRepository.isPresent(houseID);
        // Assert
        assertTrue(isPresent);
    }
}