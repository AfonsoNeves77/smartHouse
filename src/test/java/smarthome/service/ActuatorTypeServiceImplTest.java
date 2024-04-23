package smarthome.service;

import smarthome.domain.actuatortype.ActuatorTypeFactoryImpl;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.persistence.mem.ActuatorTypeRepositoryMem;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeServiceImplTest {


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given a null repository.
     */
    @Test
    void whenGivenANullRepository_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(null, v1ActuatorTypeFactoryDouble, "filepath"));
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
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, null, "filepath"));
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
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);


        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Test to check if the ActuatorTypeService constructor
     * throws an IllegalArgumentException when given an empty repository.
     */
    @Test
    void whenGivenAnEmptyFilepath_throwsIllegalArgument(){
        // Arrange
        String expected = "Invalid repository";
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        String filepath = " ";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, filepath));
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
        ActuatorTypeRepositoryMem memActuatorTypeRepositoryDouble = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl v1ActuatorTypeFactoryDouble = mock(ActuatorTypeFactoryImpl.class);
        String filepath = "WrongFilePath";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new ActuatorTypeServiceImpl(memActuatorTypeRepositoryDouble, v1ActuatorTypeFactoryDouble, filepath));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


    /**
     * Tests that the 'actuatorTypeExists' method returns true when the specified actuator type ID
     * is present in the repository.
     * This test ensures that the service layer correctly identifies existing actuator types
     * using the ActuatorTypeRepositoryMem 'isPresent' method.
     * Creating a 'mock' of ActuatorTypeRepositoryMem to simulate the presence of an actuator type ID.
     * Setting up the ActuatorTypeServiceImpl with mocked dependencies to handle the repository interaction.
     * Mocking the ActuatorTypeIDVO to use as a parameter for the actuatorTypeExists method.
     * The test asserts that the method returns true, confirming the presence of the actuator type.
     */
    @Test
    void whenActuatorTypeIsPresent_returnsTrue() {
        // Arrange
        ActuatorTypeRepositoryMem repository = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl factory = mock(ActuatorTypeFactoryImpl.class);
        ActuatorTypeServiceImpl service = new ActuatorTypeServiceImpl(repository, factory, "actuator.properties");

        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        when(repository.isPresent(id)).thenReturn(true);

        // Act
        boolean result = service.actuatorTypeExists(id);

        // Assert
        assertTrue(result);
    }


    /**
     * Tests that the 'actuatorTypeExists' method returns false when the specified actuator type ID is not
     * present in the repository.
     * This test checks the service layer's capability to correctly report the absence of actuator types,
     * relying on the ActuatorTypeRepositoryMem 'isPresent' method's behavior.
     * Creating a 'mock' of ActuatorTypeRepositoryMem to simulate the absence of an actuator type ID.
     * Setting up the ActuatorTypeServiceImpl with mocked dependencies to manage repository interactions.
     * Mocking the ActuatorTypeIDVO to serve as a parameter for the actuatorTypeExists method.
     * The test asserts that the method returns false, confirming the absence of the actuator type.
     */
    @Test
    void whenActuatorTypeIsNotPresent_returnsFalse() {
        // Arrange
        ActuatorTypeRepositoryMem repository = mock(ActuatorTypeRepositoryMem.class);
        ActuatorTypeFactoryImpl factory = mock(ActuatorTypeFactoryImpl.class);
        ActuatorTypeServiceImpl service = new ActuatorTypeServiceImpl(repository, factory, "actuator.properties");

        ActuatorTypeIDVO id = mock(ActuatorTypeIDVO.class);
        when(repository.isPresent(id)).thenReturn(false);

        // Act
        boolean result = service.actuatorTypeExists(id);

        // Assert
        assertFalse(result);
    }
}
