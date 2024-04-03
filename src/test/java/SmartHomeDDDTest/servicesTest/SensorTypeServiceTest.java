package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.domain.sensorType.SensorTypeFactory;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.services.SensorTypeService;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SensorTypeServiceTest {
    /**
     * This test ensures that, when given a null path, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenANullPath_ConstructorThrowsIllegalArgument(){
        // Arrange
        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String expected = "Invalid parameters";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeService(repository,factory,null));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given an invalid path, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenAnInvalidPath_ConstructorThrowsIllegalArgument(){
        // Arrange
        String path = "I will fail";
        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String expected = "Invalid path";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeService(repository,factory,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null repository, the constructor throws and IllegalArgumentException
     */
    @Test
    void whenGivenAnInvalidRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        String expected = "Invalid parameters";
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String path = "sensor.properties";
        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorTypeService(null,factory,path));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that getListOfSensorTypes method successfully returns a List of SensorTypes, the size is similar
     * to the iterable returned by the repository, and the objects on the first entry of the list and the iterable are the
     * same
     */
    @Test
    void whenGetListOfSensorTypesCalled_SuccessfullyReturnsAListOfSensorTypes(){
        // Arrange
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String path = "sensor.properties";
        SensorType sensorType = mock(SensorType.class);
        ArrayList<SensorType> list = new ArrayList<>();
        list.add(sensorType);
        Iterable<SensorType> iterable = () -> list.stream().iterator(); //This line lets us convert an ArrayList to an interable

        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        SensorTypeService service = new SensorTypeService(repository,factory,path);

        int expected = 1;

        // Act
        List<SensorType> result = service.getListOfSensorTypes();
        SensorType resultSensorType = result.get(0);

        // Assert
        assertEquals(expected,result.size());
        assertEquals(sensorType,resultSensorType);
    }

    /**
     * This test ensures that getListOfSensorTypes, when communicating with an empty repo, returns an empty List.
     */
    @Test
    void whenGetListOfSensorTypesCalled_IfRepoContainsNoEntries_returnsEmptyList(){
        // Arrange
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String path = "sensor.properties";
        ArrayList<SensorType> list = new ArrayList<>();
        Iterable<SensorType> iterable = () -> list.stream().iterator(); //This line lets us convert an ArrayList to an interable

        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        SensorTypeService service = new SensorTypeService(repository,factory,path);

        int expected = 0;
        List<SensorType> expectedList = new ArrayList<>();

        // Act
        List<SensorType> resultList = service.getListOfSensorTypes();

        // Assert
        assertEquals(expected,resultList.size());
        assertEquals(expectedList,resultList);
    }

    /**
     * This test ensures that method sensorTypeExists, when repo has the same id, returns true.
     */
    @Test
    void whenSensorTypeExistsCalled_ifRepoContainsTheObject_returnsTrue(){
        // Arrange
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String path = "sensor.properties";
        SensorTypeIDVO id = mock(SensorTypeIDVO.class);
        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        when(repository.isPresent(id)).thenReturn(true);

        SensorTypeService service = new SensorTypeService(repository,factory,path);

        // Act
        boolean result = service.sensorTypeExists(id);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that method sensorTypeExists, when repo does not have the same id, returns false.
     */
    @Test
    void whenSensorTypeExistsCalled_ifRepoDoesNotContainTheObject_returnsFalse() {
        // Arrange
        SensorTypeFactory factory = mock(SensorTypeFactory.class);
        String path = "sensor.properties";
        SensorTypeIDVO id = mock(SensorTypeIDVO.class);
        SensorTypeRepository repository = mock(SensorTypeRepository.class);
        when(repository.isPresent(id)).thenReturn(false);

        SensorTypeService service = new SensorTypeService(repository, factory, path);

        // Act
        boolean result = service.sensorTypeExists(id);

        // Assert
        assertFalse(result);
    }
}
