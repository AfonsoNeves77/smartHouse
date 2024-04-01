package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ActuatorServiceTest {

    /**
     * The following tests verify that if any of the parameters are null, ActuatorService instantiation should throw an Illegal Argument Exception with
     * the  message "Invalid Parameters".
     * Regarding test syntax it´s used a reusable assertion method assertThrowsIllegalArgumentExceptionWithNullParameter()
     * for the similar nature of the following tests.
     * @param factoryActuator that creates new Actuator
     * @param actuatorRepository responsible for saving, accessing and retrieving Actuator instances
     */


    private void assertThrowsIllegalArgumentExceptionWithNullParameter(ActuatorFactory factoryActuator, ActuatorRepository actuatorRepository) {
        //Arrange
        String expected = "Invalid parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorService(factoryActuator,actuatorRepository));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if ActuatorFactory parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullActuatorFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = null;
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(actuatorFactory,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorRepository parameter is null then ActuatorService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullActuatorRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = null;

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(actuatorFactory,actuatorRepository);
    }

    /**
     * Test case to verify that creating an Actuator with a null ActuatorNameVO parameter returns null.
     * All ActuatorService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     */

    @Test
    void createActuator_WhenNullActuatorNameVO_ShouldReturnNull(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = null;
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating an Actuator with a null ActuatorTypeIDVO parameter returns null.
     * All ActuatorService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     */

    @Test
    void createActuator_WhenNullActuatorTypeIDVO_ShouldReturnNull(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = null;
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating an Actuator with a null DeviceIDVO parameter returns null.
     * All ActuatorService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     */

    @Test
    void createActuator_WhenNullDeviceIDVO_ShouldReturnNull(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = null;

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating an Actuator with valid parameters returns a non-null Actuator instance.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     * In this test case, the behavior of ActuatorFactory is configured to return a doubled Actuator when the createActuator method is called.
     * The assertion is performed by comparing the returned Actuator object with the expected Actuator.
     */
    @Test
    void createActuator_ShouldReturnCorrectActuator(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);

        Actuator expected = mock(Actuator.class);
        when(actuatorFactory.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO)).thenReturn(expected);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify that saving a null Actuator returns false.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     */

    @Test
    void saveActuator_WhenNullActuator_ShouldReturnFalse(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        Actuator actuator = null;

        //Act
        boolean result = actuatorService.saveActuator(actuator);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that saving an Actuator in the repository, which results in an unsuccessful save operation, returns false.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     * In this test, the ActuatorRepository is doubled, and its behavior is configured to return false when the saving operation is called.
     */

    @Test
    void saveActuator_WhenUnsuccessfulSaveInRepository_ShouldReturnFalse(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        Actuator actuator = mock(Actuator.class);
        when(actuatorRepository.save(actuator)).thenReturn(false);

        //Act
        boolean result = actuatorService.saveActuator(actuator);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that saving an Actuator in the repository, which results in a successful save operation, returns true.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     * In this test, the ActuatorRepository is doubled, and its behavior is configured to return true when the saving operation is called.
     */

    @Test
    void saveActuator_WhenSuccessfulSaveInRepository_ShouldReturnTrue(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        Actuator actuator = mock(Actuator.class);
        when(actuatorRepository.save(actuator)).thenReturn(true);

        //Act
        boolean result = actuatorService.saveActuator(actuator);

        //Assert
        assertTrue(result);
    }


}
