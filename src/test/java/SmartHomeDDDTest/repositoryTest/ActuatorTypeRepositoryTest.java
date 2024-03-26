package SmartHomeDDDTest.repositoryTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeRepositoryTest {

    /**
     * Test to check if the actuator type is saved given a null ActuatorType
     * The result should be false
     */
    @Test
    public void whenActuatorTypeIsNull_thenTypeIsNotSaved() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type = null;

        //Act
        boolean result = actuatorTypeRepository.save(type);

        //Assert
        assertFalse(result);
    }

    /**
     * Test to check if the actuator type is saved given a null ActuatorTypeID
     * The result should be false
     */
    @Test
    public void whenActuatorTypeIDIsNull_thenTypeNotSaved() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type = mock(ActuatorType.class);
        when(type.getId()).thenReturn(null);

        //Act
        boolean result = actuatorTypeRepository.save(type);

        //Assert
        assertFalse(result);
    }

    /**
     * Test to check if the actuator type is saved given a valid ActuatorType
     * The result should be true
     */
    @Test
    public void whenTypeIsValid_thenTypeIsSaved() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type = mock(ActuatorType.class);
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        when(type.getId()).thenReturn(id);

        //Act
        boolean result = actuatorTypeRepository.save(type);

        //Assert
        assertTrue(result);
    }

    /**
     * Test to check if the actuator type is saved given a valid ActuatorType
     * Should be able to find the type by its ID
     */
    @Test
    public void whenTypeIsSaved_thenIsFoundByTypeId() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type = mock(ActuatorType.class);
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        when(type.getId()).thenReturn(id);
        actuatorTypeRepository.save(type);
        ActuatorType expected = type;

        //Act
        ActuatorType result = actuatorTypeRepository.findById(id);

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test to check if the actuator type is saved given a valid ActuatorType and then found in findAll
     * Should return a list of all the types saved
     */
    @Test
    public void whenTypesAreSaved_thenFoundInFindAll() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type1 = mock(ActuatorType.class);
        ActuatorType type2 = mock(ActuatorType.class);
        ActuatorType type3 = mock(ActuatorType.class);
        ActuatorTypeIDVO type1ID = mock(ActuatorTypeIDVO.class);
        when(type1.getId()).thenReturn(type1ID);
        ActuatorTypeIDVO type2ID = mock(ActuatorTypeIDVO.class);
        when(type2.getId()).thenReturn(type2ID);
        ActuatorTypeIDVO type3ID = mock(ActuatorTypeIDVO.class);
        when(type3.getId()).thenReturn(type3ID);
        actuatorTypeRepository.save(type1);
        actuatorTypeRepository.save(type2);
        actuatorTypeRepository.save(type3);

        //Act
        Iterable<ActuatorType> iterable = actuatorTypeRepository.findAll();
        boolean isType1Present = StreamSupport.stream(iterable.spliterator(), false)
                .anyMatch(type -> type.equals(type1));
        boolean isType2Present = StreamSupport.stream(iterable.spliterator(), false)
                .anyMatch(type -> type.equals(type2));
        boolean isType3Present = StreamSupport.stream(iterable.spliterator(), false)
                .anyMatch(type -> type.equals(type3));

        //Assert
        assertTrue(isType1Present);
        assertTrue(isType2Present);
        assertTrue(isType3Present);
    }

    /**
     * Test to check if the actuator type is saved given a valid ActuatorType and then found in isPresent
     * Should return true if the type is present
     */
    @Test
    public void whenTypesAreSaved_thenFoundInRepository() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type1 = mock(ActuatorType.class);
        ActuatorType type2 = mock(ActuatorType.class);
        ActuatorType type3 = mock(ActuatorType.class);
        ActuatorTypeIDVO type1ID = mock(ActuatorTypeIDVO.class);
        when(type1.getId()).thenReturn(type1ID);
        ActuatorTypeIDVO type2ID = mock(ActuatorTypeIDVO.class);
        when(type2.getId()).thenReturn(type2ID);
        ActuatorTypeIDVO type3ID = mock(ActuatorTypeIDVO.class);
        when(type3.getId()).thenReturn(type3ID);
        actuatorTypeRepository.save(type1);
        actuatorTypeRepository.save(type2);
        actuatorTypeRepository.save(type3);

        //Act
        boolean isType3Present = actuatorTypeRepository.isPresent(type3ID);
        boolean isType1Present = actuatorTypeRepository.isPresent(type1ID);
        boolean isType2Present = actuatorTypeRepository.isPresent(type2ID);


        //Assert
        assertTrue(isType1Present);
        assertTrue(isType2Present);
        assertTrue(isType3Present);
    }

    /**
     * Test to check if a not saved actuator type is not found in findAll
     * Should return false if the type is not present
     */
    @Test
    public void whenTypeIsNotSaved_thenNotFoundWithFindAll() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type1 = mock(ActuatorType.class);
        ActuatorType type2 = mock(ActuatorType.class);
        ActuatorType type3 = mock(ActuatorType.class);
        ActuatorTypeIDVO typeID = mock(ActuatorTypeIDVO.class);
        when(type3.getId()).thenReturn(typeID);
        actuatorTypeRepository.save(type1);
        actuatorTypeRepository.save(type2);

        //Act
        Iterable<ActuatorType> iterable = actuatorTypeRepository.findAll();
        boolean isType3Present = StreamSupport.stream(iterable.spliterator(), false)
                .anyMatch(type -> type.equals(type3));

        //Assert
        assertFalse(isType3Present);
    }

    /**
     * Test to check if a not saved actuator type is not found by its ID
     * Should return a null object
     */
    @Test
    public void whenTypeIsNotPresent_thenNotFoundById() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type1 = mock(ActuatorType.class);
        ActuatorType type2 = mock(ActuatorType.class);
        ActuatorType type3 = mock(ActuatorType.class);
        ActuatorTypeIDVO type3ID = mock(ActuatorTypeIDVO.class);
        when(type3.getId()).thenReturn(type3ID);
        actuatorTypeRepository.save(type1);
        actuatorTypeRepository.save(type2);

        //Act
        ActuatorType result = actuatorTypeRepository.findById(type3ID);

        //Assert
        assertNull(result);
    }

    /**
     * Test to check if a not saved actuator type is not found in isPresent
     * Should return a null object
     */
    @Test
    public void whenTypeIDIsNotPresent_thenNotFound() {
        //Arrange
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorType type1 = mock(ActuatorType.class);
        ActuatorType type2 = mock(ActuatorType.class);
        actuatorTypeRepository.save(type1);
        actuatorTypeRepository.save(type2);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);

        //Act
        ActuatorType result = actuatorTypeRepository.findById(actuatorTypeID);

        //Assert
        assertNull(result);
    }
}