package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuator.RollerBlindActuator;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.vo.Settings;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorServiceTest {

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
        Settings settings = mock(Settings.class);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);

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
        Settings settings = mock(Settings.class);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);

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
        Settings settings = mock(Settings.class);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);
        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating an Actuator with null Settings returns an Actuator instance.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     * In this test case, the behavior of ActuatorFactory is configured to return a doubled Actuator when the createActuator method is called.
     * The assertion is performed by comparing the returned Actuator object with the expected Actuator.
     */
    @Test
    void createActuator_WhenNullSettings_ShouldReturnCorrectActuator(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);


        Actuator expected = mock(Actuator.class);
        when(actuatorFactory.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings)).thenReturn(expected);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);

        //Assert
        assertEquals(expected,result);
    }


    /**
     * Test case to verify that creating an Actuator with valid parameters returns a non-null Actuator instance.
     * All collaborators of ActuatorService involved in this operation are mocked to ensure proper isolation.
     * In this test case, the behavior of ActuatorFactory is configured to return a doubled Actuator when the createActuator method is called.
     * The assertion is performed by comparing the returned Actuator object with the expected Actuator.
     */
    @Test
    void createActuator_WhenValidParameters_ShouldReturnCorrectActuator(){

        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);


        Actuator expected = mock(Actuator.class);
        when(actuatorFactory.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings)).thenReturn(expected);

        //Act
        Actuator result = actuatorService.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO,settings);

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

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, only returns a map with 2 entries. Entries are related to the unique types.
     */
    @Test
    void getListOfDeviceIDByFunctionality_whenRepositoryHas3devicesButTwoRepeatedTypes_returnHasSizeTwo(){
        // Arrange
        LinkedHashMap<String,List<DeviceIDVO>> entryMap = new LinkedHashMap<>();

        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        ActuatorTypeIDVO type1 = mock(ActuatorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        ActuatorTypeIDVO type2 = mock(ActuatorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        ActuatorTypeIDVO type3 = mock(ActuatorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Actuator actuator1 = mock(SwitchActuator.class);
        when(actuator1.getActuatorTypeID()).thenReturn(type1);
        when(actuator1.getDeviceID()).thenReturn(id1);
        Actuator actuator2 = mock(RollerBlindActuator.class);
        when(actuator2.getActuatorTypeID()).thenReturn(type2);
        when(actuator2.getDeviceID()).thenReturn(id2);
        Actuator actuator3 = mock(SwitchActuator.class);
        when(actuator3.getActuatorTypeID()).thenReturn(type3);
        when(actuator3.getDeviceID()).thenReturn(id3);

        List<Actuator> actuators = new ArrayList<>();
        actuators.add(actuator1);
        actuators.add(actuator2);
        actuators.add(actuator3);

        ActuatorRepository repository = mock(ActuatorRepository.class);
        when(repository.findAll()).thenReturn(actuators);

        ActuatorFactory factory = mock(ActuatorFactory.class);

        ActuatorService service = new ActuatorService(factory,repository);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality(entryMap);

        int expectedSize = 2;
        // Act
        int resultSize = map.size();

        // Assert
        assertEquals(expectedSize,resultSize);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, it returns a map where the first two keys match the expected ones.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_theReturningFirstTwoKeysAreSimilar(){
        // Arrange
        LinkedHashMap<String,List<DeviceIDVO>> entryMap = new LinkedHashMap<>();

        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        ActuatorTypeIDVO type1 = mock(ActuatorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        ActuatorTypeIDVO type2 = mock(ActuatorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        ActuatorTypeIDVO type3 = mock(ActuatorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Actuator actuator1 = mock(SwitchActuator.class);
        when(actuator1.getActuatorTypeID()).thenReturn(type1);
        when(actuator1.getDeviceID()).thenReturn(id1);
        Actuator actuator2 = mock(RollerBlindActuator.class);
        when(actuator2.getActuatorTypeID()).thenReturn(type2);
        when(actuator2.getDeviceID()).thenReturn(id2);
        Actuator actuator3 = mock(SwitchActuator.class);
        when(actuator3.getActuatorTypeID()).thenReturn(type3);
        when(actuator3.getDeviceID()).thenReturn(id3);

        List<Actuator> actuators = new ArrayList<>();
        actuators.add(actuator1);
        actuators.add(actuator2);
        actuators.add(actuator3);

        ActuatorRepository repository = mock(ActuatorRepository.class);
        when(repository.findAll()).thenReturn(actuators);

        ActuatorFactory factory = mock(ActuatorFactory.class);

        ActuatorService service = new ActuatorService(factory,repository);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality(entryMap);

        String expectedFirstKey = "SwitchActuator";
        String expectedSecondKey = "RollerBlindActuator";
        String expectedThirdKey = null;
        // Act
        String resultFirstKey = map.keySet().stream().findFirst().orElse(null);
        String resultSecondKey = map.keySet().stream().skip(1).findFirst().orElse(null);
        String resultThirdKey = map.keySet().stream().skip(2).findFirst().orElse(null);

        // Assert
        assertEquals(expectedFirstKey,resultFirstKey);
        assertEquals(expectedSecondKey,resultSecondKey);
        assertEquals(expectedThirdKey,resultThirdKey);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, it returns a map where the values of first two keys match the expected ones.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_ReturnsListOfExpectedDeviceIDRefs(){
        // Arrange
        LinkedHashMap<String,List<DeviceIDVO>> entryMap = new LinkedHashMap<>();

        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        ActuatorTypeIDVO type1 = mock(ActuatorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        ActuatorTypeIDVO type2 = mock(ActuatorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        ActuatorTypeIDVO type3 = mock(ActuatorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Actuator actuator1 = mock(SwitchActuator.class);
        when(actuator1.getActuatorTypeID()).thenReturn(type1);
        when(actuator1.getDeviceID()).thenReturn(id1);
        Actuator actuator2 = mock(RollerBlindActuator.class);
        when(actuator2.getActuatorTypeID()).thenReturn(type2);
        when(actuator2.getDeviceID()).thenReturn(id2);
        Actuator actuator3 = mock(SwitchActuator.class);
        when(actuator3.getActuatorTypeID()).thenReturn(type3);
        when(actuator3.getDeviceID()).thenReturn(id3);

        List<Actuator> actuators = new ArrayList<>();
        actuators.add(actuator1);
        actuators.add(actuator2);
        actuators.add(actuator3);

        ActuatorRepository repository = mock(ActuatorRepository.class);
        when(repository.findAll()).thenReturn(actuators);

        ActuatorFactory factory = mock(ActuatorFactory.class);

        ActuatorService service = new ActuatorService(factory,repository);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality(entryMap);

        String firstKey = map.keySet().stream().findFirst().orElse(null);
        String secondKey = map.keySet().stream().skip(1).findFirst().orElse(null);

        String firstListIDRefs = "[" + id1.toString() + ", " + id3.toString() + "]";
        String secondListIDRefs = "[" + id2.toString() + "]";

        // Act
        String resultfirstKey = map.get(firstKey).toString();
        String resultsecondKey = map.get(secondKey).toString();

        // Assert
        assertEquals(firstListIDRefs,resultfirstKey);
        assertEquals(secondListIDRefs,resultsecondKey);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types and 2 similar DeviceIDs, it returns a map where the values of first
     * two keys match the expected ones, considering no DeviceIDs should be duplicated within the same key:value pair.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_ReturnsListOfExpectedDeviceIDRefsWithNoDuplicatedIds(){
        // Arrange
        LinkedHashMap<String,List<DeviceIDVO>> entryMap = new LinkedHashMap<>();

        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        ActuatorTypeIDVO type1 = mock(ActuatorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        ActuatorTypeIDVO type2 = mock(ActuatorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        ActuatorTypeIDVO type3 = mock(ActuatorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Actuator actuator1 = mock(SwitchActuator.class);
        when(actuator1.getActuatorTypeID()).thenReturn(type1);
        when(actuator1.getDeviceID()).thenReturn(id1);
        Actuator actuator2 = mock(RollerBlindActuator.class);
        when(actuator2.getActuatorTypeID()).thenReturn(type2);
        when(actuator2.getDeviceID()).thenReturn(id2);
        Actuator actuator3 = mock(SwitchActuator.class);
        when(actuator3.getActuatorTypeID()).thenReturn(type3);
        when(actuator3.getDeviceID()).thenReturn(id3);

        Actuator actuator4 = mock(SwitchActuator.class);
        when(actuator4.getActuatorTypeID()).thenReturn(type3);
        when(actuator4.getDeviceID()).thenReturn(id3);

        List<Actuator> actuators = new ArrayList<>();
        actuators.add(actuator1);
        actuators.add(actuator2);
        actuators.add(actuator3);

        ActuatorRepository repository = mock(ActuatorRepository.class);
        when(repository.findAll()).thenReturn(actuators);

        ActuatorFactory factory = mock(ActuatorFactory.class);

        ActuatorService service = new ActuatorService(factory,repository);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality(entryMap);

        String firstKey = map.keySet().stream().findFirst().orElse(null);
        String secondKey = map.keySet().stream().skip(1).findFirst().orElse(null);

        String firstListIDRefs = "[" + id1.toString() + ", " + id3.toString() + "]";
        String secondListIDRefs = "[" + id2.toString() + "]";

        // Act
        String resultfirstKey = map.get(firstKey).toString();
        String resultsecondKey = map.get(secondKey).toString();

        // Assert
        assertEquals(firstListIDRefs,resultfirstKey);
        assertEquals(secondListIDRefs,resultsecondKey);
    }

}
