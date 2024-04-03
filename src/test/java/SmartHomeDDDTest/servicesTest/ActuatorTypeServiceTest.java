package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.domain.actuatorType.ActuatorTypeFactory;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.services.ActuatorTypeService;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorTypeServiceTest {


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null repository.
     */
    @Test
    void whenGivenANullRepository_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(null, actuatorTypeFactoryDouble, "filepath"));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null factory.
     */
    @Test
    void whenGivenANullFactory_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(actuatorTypeRepositoryDouble, null, "filepath"));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null filepath.
     */
    @Test
    void whenGivenANullFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a empty repository.
     */
    @Test
    void whenGivenAnEmptyFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);
        String filepath = " ";;


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, filepath));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a wrong repository.
     */
    @Test
    void whenGivenWrongFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Filepath non existent";
        ActuatorTypeRepository actuatorTypeRepositoryDouble = mock(ActuatorTypeRepository.class);
        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);
        String filepath = "WrongFilePath";;


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeService(actuatorTypeRepositoryDouble, actuatorTypeFactoryDouble, filepath));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     *  Test to check if the ActuatorTypeService returns a list of ActuatorTypes
     *  when getListOfActuatorTypes is called.
     */
    @Test
    void whenGetListOfActuatorTypesCalled_returnsAListOfActuatorTypesOfSize1() throws ConfigurationException {
        // Arrange
        ActuatorType actuatorType = mock(ActuatorType.class);
        ArrayList<ActuatorType> list = new ArrayList<>();
        list.add(actuatorType);
        Iterable<ActuatorType> iterable = () -> list.stream().iterator();

        ActuatorTypeRepository repositoryDouble = mock(ActuatorTypeRepository.class);
        when(repositoryDouble.findAll()).thenReturn(iterable);

        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);

        String filePath = "actuator.properties";

        ActuatorTypeService service = new ActuatorTypeService(repositoryDouble, actuatorTypeFactoryDouble, filePath);

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
    void whenGetListOfActuatorTypesCalled_returnsEmptyListIfRepoContainsZeroEntries() throws ConfigurationException {
        // Arrange
        ArrayList<ActuatorType> list = new ArrayList<>();
        Iterable<ActuatorType> iterable = () -> list.stream().iterator();

        ActuatorTypeRepository repositoryDouble = mock(ActuatorTypeRepository.class);
        when(repositoryDouble.findAll()).thenReturn(iterable);

        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);

        String filePath = "actuator.properties";

        ActuatorTypeService service = new ActuatorTypeService(repositoryDouble, actuatorTypeFactoryDouble, filePath);

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
    void whenActuatorTypeExistsCalled_returnsTrueIfRepoContainsAnActuatorType() throws ConfigurationException {
        // Arrange
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        ActuatorTypeRepository repositoryDouble = mock(ActuatorTypeRepository.class);
        when(repositoryDouble.isPresent(id)).thenReturn(true);


        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);

        String filePath = "actuator.properties";

        ActuatorTypeService service = new ActuatorTypeService(repositoryDouble, actuatorTypeFactoryDouble, filePath);

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
    void whenActuatorTypeExistsCalled_returnsFalseIfRepoDoesNotContainTheActuatorType() throws ConfigurationException {
        // Arrange
        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        ActuatorTypeRepository repositoryDouble = mock(ActuatorTypeRepository.class);
        when(repositoryDouble.isPresent(id)).thenReturn(false);


        ActuatorTypeFactory actuatorTypeFactoryDouble = mock(ActuatorTypeFactory.class);

        String filePath = "actuator.properties";

        ActuatorTypeService service = new ActuatorTypeService(repositoryDouble, actuatorTypeFactoryDouble, filePath);

        // Act
        boolean result = service.actuatorTypeExists(id);

        // Assert
        assertFalse(result);
    }
}
