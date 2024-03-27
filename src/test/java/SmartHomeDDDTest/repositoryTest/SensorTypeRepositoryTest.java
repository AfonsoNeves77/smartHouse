package SmartHomeDDDTest.repositoryTest;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.apache.commons.collections4.IterableUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SensorTypeRepositoryTest {

    /**
     * This test verifies that the saving process of a valid SensorType must return true. By valid it´s
     * understood a not null SensorType, a not null SensorTypeID and a non-repeated SensorType.
     * All the collaborators that interview in this process are doubled ensuring isolation.
     */
    @Test
    void saveWithValidSensorType_ShouldReturnTrue(){
        //Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        boolean result = sensorTypeRepository.save(sensorTypeDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify the behavior of the save method in SensorTypeRepository
     * when attempting to save a null SensorType.
     * This test verifies that the saving process of a null SensorType must return false.
     */

    @Test
    void saveNullSensorType_ShouldReturnFalse(){
        //Arrange
        SensorType sensorTypeDouble = null;

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        boolean result = sensorTypeRepository.save(sensorTypeDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify the behavior of the save method in SensorTypeRepository
     * when attempting to save a SensorType with a null SensorTypeID.
     * This test verifies that the saving process a SensorType with a null SensorTypeID must return false.
     */
    @Test
    void saveWhenSensorTypeIDIsNull_ShouldReturnFalse(){
        //Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = null;
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        boolean result = sensorTypeRepository.save(sensorTypeDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify the behavior of the save method in SensorTypeRepository
     * when attempting to save a repeated SensorType.
     * This test verifies that the saving process a repeated SensorType return false.
     */
    @Test
    void saveRepeatedSensorType_ShouldReturnFalse(){
        //Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);
        boolean result = sensorTypeRepository.save(sensorTypeDouble);
        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify the behavior of the save method in SensorTypeRepository
     * when attempting to save a SensorType in a repository that already has one record.
     * This test verifies that the saving process the second valid SensorType return true.
     */

    @Test
    void saveValidSensorTypeWithOneExistingRecord_ShouldReturnTrue(){
        //Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        SensorTypeIDVO sensorTypeIDVOTwo = mock(SensorTypeIDVO.class);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);

        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVOTwo);

        boolean result = sensorTypeRepository.save(sensorTypeDouble);
        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify the behavior of the findAll method in SensorTypeRepository
     * when the repository is empty.
     * This test uses an Apache library that provides utility methods and decorators for Iterable instances.
     * It is invoked findAll() in an empty repository and the resulting iterable must be asserted empty.
     */

    @Test
    void findAll_whenRepositoryIsEmpty_ReturnedIterableShouldBeEmpty(){
        //Arrange
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();

        //Act
        Iterable<SensorType> sensorTypeIterable = sensorTypeRepository.findAll();
        boolean result = IterableUtils.isEmpty(sensorTypeIterable);

        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify the behavior of the findAll method in SensorTypeRepository
     * when the repository has one record.
     * This test uses an Apache library that provides utility methods and decorators for Iterable instances.
     * It is invoked findAll() in repository class and the resulting iterable must be asserted with one record.
     */

    @Test
    void findAll_whenRepositoryHasOneRecord_ReturnedIterableShouldHaveOneRecord(){
        //Arrange
        int expected = 1;
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);
        Iterable<SensorType> sensorTypeIterable = sensorTypeRepository.findAll();

        //Assert
        int result = IterableUtils.size(sensorTypeIterable);
        assertEquals(expected,result);
    }

    /**
     * Test case to verify the behavior of the findAll method in SensorTypeRepository
     * when the repository has two records.
     * This test uses an Apache library that provides utility methods and decorators for Iterable instances.
     * It is invoked findAll() in repository class and the resulting iterable must be asserted with two record.
     */
    @Test
    void findAll_whenRepositoryHasTwoRecords_ReturnedIterableShouldHaveTwoRecords(){
        //Arrange
        int expected = 2;
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        SensorTypeIDVO sensorTypeIDVOTwo = mock(SensorTypeIDVO.class);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);

        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVOTwo);
        sensorTypeRepository.save(sensorTypeDouble);

        Iterable<SensorType> sensorTypeIterable = sensorTypeRepository.findAll();
        //Assert
        int result = IterableUtils.size(sensorTypeIterable);
        assertEquals(expected,result);
    }

    /**
     * Test case to verify the behavior of the findById method in SensorTypeRepository
     * when the repository is empty.
     * This test asserts that null is returned when findByID() is invoked in an empty repository.
     */

    @Test
    void findById_WhenEmptyRepository_ShouldReturnNull(){

        //Arrange
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);

        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorType result = sensorTypeRepository.findById(sensorTypeIDVO);

        //Assert
        assertNull(result);

    }

    /**
     * Test case to verify the behavior of the findById method in SensorTypeRepository
     * when the requested SensorType does not exist in the repository.
     * This test asserts that null is returned when findByID() invoked with a SensorType that does not exist in the repository.
     */

    @Test
    void findById_WhenNonExistingSensorType_ShouldReturnNull(){

        //Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        SensorTypeIDVO isPresentSensorTypeID = mock(SensorTypeIDVO.class);


        //Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);
        SensorType result = sensorTypeRepository.findById(isPresentSensorTypeID);

        //Assert
        assertNull(result);

    }

    /**
     * Test case to verify the behavior of the isPresent method in SensorTypeRepository
     * when the requested SensorType exists in the repository.
     * Is present should return true when an existing Sensor Type is searched.
     */
    @Test
    void isPresent_WhenExistingSensorType_ShouldReturnCorrectSensorType() {
        // Arrange
        SensorType expected = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(expected.getId()).thenReturn(sensorTypeIDVO);

        // Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(expected);
        SensorType result = sensorTypeRepository.findById(sensorTypeIDVO);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test case to verify the behavior of the isPresent method in SensorTypeRepository
     * in an empty repository
     * Is present should return false when the repository is empty.
     */
    @Test
    void isPresent_WhenEmptyRepository_ShouldReturnFalse() {
        // Arrange
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);

        // Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        boolean result = sensorTypeRepository.isPresent(sensorTypeIDVO);

        // Assert
        assertFalse(result);
    }

    /**
     * Test case to verify the behavior of the isPresent method in SensorTypeRepository
     * when the requested SensorType does not exist in the repository.
     * Is present should return false when a non-existing Sensor Type is searched.
     */
    @Test
    void isPresent_WhenNonExistingSensorType_ShouldReturnFalse() {
        // Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        SensorTypeIDVO isPresentSensorTypeID = mock(SensorTypeIDVO.class);

        // Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);
        boolean result = sensorTypeRepository.isPresent(isPresentSensorTypeID);

        // Assert
        assertFalse(result);
    }

    /**
     * Test case to verify the behavior of the isPresent method in SensorTypeRepository
     * when the requested SensorType exists in the repository.
     * Is present should return true when an already existing Sensor Type is searched.
     */
    @Test
    void isPresent_WhenExistingSensorType_ShouldReturnTrue() {
        // Arrange
        SensorType sensorTypeDouble = mock(SensorType.class);
        SensorTypeIDVO sensorTypeIDVO = mock(SensorTypeIDVO.class);
        when(sensorTypeDouble.getId()).thenReturn(sensorTypeIDVO);

        // Act
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        sensorTypeRepository.save(sensorTypeDouble);
        boolean result = sensorTypeRepository.isPresent(sensorTypeIDVO);

        // Assert
        assertTrue(result);
    }

}