package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.services.ActuatorTypeService;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorTypeServiceTest {


    /**
     * Test to check if the ActuatorTypeService
     * constructor throws an IllegalArgumentException
     */
    @Test
    void whenGivenANullRepository_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     *  Test to check if the ActuatorTypeService returns a list of ActuatorTypes
     *  when getListOfActuatorTypes is called.
     */
    @Test
    void whenGetListOfActuatorTypesCalled_returnsAListOfActuatorTypes(){
        // Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ArrayList<ActuatorType> list = new ArrayList<>();
        list.add(actuatorType);
        Iterable<ActuatorType> iterable = () -> list.stream().iterator();

        ActuatorTypeRepository repository = mock(ActuatorTypeRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        ActuatorTypeService service = new ActuatorTypeService(repository);

        int expected = 1;

        // Act
        List<ActuatorType> result = service.getListOfActuatorTypes();
        ActuatorType resultActuatorType = result.get(0);

        // Assert
        assertEquals(expected,result.size());
        assertEquals(actuatorType,resultActuatorType);
    }


    /**
     * Test to check if the ActuatorTypeService returns an empty list
     * when getListOfActuatorTypes is called and the repository contains zero entries.
     */
    @Test
    void whenGetListOfActuatorTypesCalled_returnsEmptyListIfRepoContainsZeroEntries(){
        // Arrange
        ArrayList<ActuatorType> list = new ArrayList<>();
        Iterable<ActuatorType> iterable = () -> list.stream().iterator();

        ActuatorTypeRepository repository = mock(ActuatorTypeRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        ActuatorTypeService service = new ActuatorTypeService(repository);

        int expected = 0;
        List<ActuatorType> expectedList = new ArrayList<>();

        // Act
        List<ActuatorType> resultList = service.getListOfActuatorTypes();

        // Assert
        assertEquals(expected,resultList.size());
        assertEquals(expectedList,resultList);
    }


    /**
     * Test to check if the ActuatorTypeService returns true when the
     * actuatorTypeExists method is called and the repository contains the ActuatorType.
     */
    @Test
    void whenActuatorTypeExistsCalled_returnsTrueIfRepoContainsAnActuatorType(){
        // Arrange
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        ActuatorTypeRepository repository = mock(ActuatorTypeRepository.class);
        when(repository.isPresent(id)).thenReturn(true);

        ActuatorTypeService service = new ActuatorTypeService(repository);

        // Act
        boolean result = service.actuatorTypeExists(id);

        // Assert
        assertTrue(result);
    }

    /**
     * Test to check if the ActuatorTypeService returns false when the
     * actuatorTypeExists method is called and the repository does not contain the ActuatorType.
     */
    @Test
    void whenActuatorTypeExistsCalled_returnsFalseIfRepoDoesNotContainTheActuatorType(){
        // Arrange
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        ActuatorTypeRepository repository = mock(ActuatorTypeRepository.class);
        when(repository.isPresent(id)).thenReturn(false);

        ActuatorTypeService service = new ActuatorTypeService(repository);

        // Act
        boolean result = service.actuatorTypeExists(id);

        // Assert
        assertFalse(result);
    }
}
