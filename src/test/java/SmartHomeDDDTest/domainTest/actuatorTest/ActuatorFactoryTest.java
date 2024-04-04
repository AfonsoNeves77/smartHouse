package SmartHomeDDDTest.domainTest.actuatorTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.vo.Settings;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.actuatorVO.DecimalSettingsVO;
import SmartHomeDDD.vo.actuatorVO.IntegerSettingsVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActuatorFactoryTest {

    /*
    SYSTEM UNDER TEST: FACTORY + ACTUATOR IMPLEMENTATION
    A double of all the other collaborators is done (essentially the required value objects to create the actuator).
     */

    /**
     * Success Scenario: Switch Actuator is created.
     * Upon request, returns its ActuatorTypeIDVO.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void createSwitchActuator_WhenGetActuatorTypeID_ThenShouldReturnExpectedIDVO() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("SwitchActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = null;

        Actuator switchActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Act
        ActuatorTypeIDVO result = switchActuator.getActuatorTypeID();

        //Assert
        assertEquals(actuatorTypeID, result);
    }

    /**
     * Success Scenario: Roller Blind Actuator is created.
     * Upon request, returns its ActuatorTypeIDVO.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void createRollerBlindActuator_WhenGetActuatorTypeID_ThenShouldReturnExpectedIDVO() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("RollerBlindActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = null;

        Actuator rollerBlindActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Act
        ActuatorTypeIDVO result = rollerBlindActuator.getActuatorTypeID();

        //Assert
        assertEquals(actuatorTypeID, result);
    }

    /**
     * Success Scenario: Decimal Value Actuator is created.
     * Upon request, returns its ActuatorTypeIDVO.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void createDecimalValueActuator_WhenGetActuatorTypeID_ThenShouldReturnExpectedIDVO() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("DecimalValueActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        DecimalSettingsVO settings = mock(DecimalSettingsVO.class);

        Actuator decimalActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Act
        ActuatorTypeIDVO result = decimalActuator.getActuatorTypeID();

        //Assert
        assertEquals(actuatorTypeID, result);
    }

//    /**
//     * Success Scenario: Integer Value Actuator is created.
//     * Upon request, returns its ActuatorTypeIDVO.
//     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
//     */
//    @Test
//    void createIntegerValueActuator_WhenGetActuatorTypeID_ThenShouldReturnExpectedIDVO() throws ConfigurationException {
//        //Arrange
//        ActuatorFactory factory = new ActuatorFactory();
//        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
//        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
//        when(actuatorTypeID.getID()).thenReturn("IntegerValueActuator");
//        DeviceIDVO deviceID = mock(DeviceIDVO.class);
//        IntegerSettingsVO settings = mock(IntegerSettingsVO.class);
//
//        Actuator integerActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);
//
//        //Act
//        ActuatorTypeIDVO result = integerActuator.getActuatorTypeID();
//
//        //Assert
//        assertEquals(actuatorTypeID, result);
//    }


    /**
     * Verifies that when wrong settings are given to create a Decimal Value Actuator, the actuator is not created. There is
     * an attempt to create the Decimal Value Actuator, but when the constructor attempts to cast the settings to a proper
     * DecimalValueVO object, a ClassCastException is thrown by the Actuator constructor. This exception is caught in the factory method and
     * a null is returned.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenWrongSettingsToADecimalValueActuator_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("DecimalValueActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        IntegerSettingsVO settings = mock(IntegerSettingsVO.class);

        //Act
        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(result);
    }

//    /**
//     * Verifies that when wrong settings are given to create an Integer Value Actuator, the actuator is not created. There is
//     * an attempt to create the Integer Value Actuator, but when the constructor attempts to cast the settings to a proper
//     * IntegerValueVO object, a ClassCastException is thrown by the Actuator constructor. This exception is caught in the
//     * factory method and a null is returned.
//     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
//     */
//    @Test
//    void givenWrongSettingsToAnIntegerValueActuator_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
//        //Arrange
//        ActuatorFactory factory = new ActuatorFactory();
//        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
//        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
//        when(actuatorTypeID.getID()).thenReturn("IntegerValueActuator");
//        DeviceIDVO deviceID = mock(DeviceIDVO.class);
//        IntegerSettingsVO settings = mock(IntegerSettingsVO.class);
//
//        //Act
//        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);
//
//        //Assert
//        assertNull(result);
//    }


    //ISOLATION TESTS

    /**
     * Verifies if a null ActuatorName VO is given, an IllegalArgumentException is thrown.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void whenNullActuatorName_ThenThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = null;
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies if a null ActuatorTypeID VO is given, an IllegalArgumentException is thrown.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void whenNullActuatorTypeID_ThenThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = null;
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies if a null DeviceID VO is given, an IllegalArgumentException is thrown.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void whenNullDeviceID_ThenThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceID = null;
        Settings settings = mock(Settings.class);
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies that actuator type is not mentioned in the accessed file containing actuator details and paths.
     * The Actuator is not created.
     * Since its instantiation is not even initialized, no MockedConstruction is required for this test scenario.
     * Even if valid parameters for Actuator creation are given, the entire operation fails, due to the above-mentioned.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenValidParameters_FileDoesNotContainActuatorType_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("xptoActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        //Act
        Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(newActuator);

    }

    /**
     * Verifies if actuator type is mentioned in the accessed file containing actuator details and paths. Although actuator type is
     * mentioned, it has no path associated.
     * Since its instantiation is not even initialized, so no MockedConstruction is required for this test scenario.
     * Even if valid parameters for Actuator creation are given, the entire operation fails, due to the above-mentioned.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenValidParameters_FileDoesNotContainActuatorTypePath_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange

        ActuatorFactory factory = new ActuatorFactory();

        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("HydraulicActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        //Act
        Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(newActuator);
    }

    /**
     * Verifies if actuator type is mentioned in the accessed file. Although it has a key and a corresponding path, there
     * is not found a match to its Class, may be due to incorrect path or Class does not exist. The Actuator is not created.
     * Since its instantiation is not even initialized, so no MockedConstruction is required for this test scenario.
     * Even if valid parameters for Actuator creation are given, the entire operation fails, due to the above-mentioned.
     * A double of all injected dependencies is made in order to isolate the ActuatorFactory Class.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenValidParameters_FileContainsActuatorTypePathButHasNoMatch_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange

        ActuatorFactory factory = new ActuatorFactory();

        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("NuclearActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        //Act
        Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(newActuator);
    }

    /**
     * Verifies that when a null Settings object is given, a DecimalValueActuator is not created, since a proper
     * constructor is not found.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenNullSettings_WhenCreateDecimalValueActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("DecimalValueActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings decimalSettings = null;

        //Act
        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, decimalSettings);

        //Assert
        assertNull(result);
    }

//    /**
//     * Verifies that when a null Settings object is given, an Integer ValueActuator is not created, since a proper
//     constructor is not found.
//     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
//     */
//    @Test
//    void givenNullSettings_WhenCreateIntegerValueActuator_ThenShouldReturnNull() throws ConfigurationException {
//        //Arrange
//        ActuatorFactory factory = new ActuatorFactory();
//        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
//        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
//        when(actuatorTypeID.getID()).thenReturn("IntegerValueActuator");
//        DeviceIDVO deviceID = mock(DeviceIDVO.class);
//        Settings integerSettings = null;
//
//        //Act
//        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, integerSettings);
//
//        //Assert
//        assertNull(result);
//    }

    /**
     * Verifies that when Settings are given to create an actuator that does not require such parameters, the actuator
     * is not created, since a proper constructor is not found. This test attempts to create a Switch Actuator.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenNonNullSettingsToASwitchActuator_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("SwitchActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        //Act
        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(result);
    }

    /**
     * Verifies that when Settings are given to create an actuator that does not require such parameters, the actuator
     * is not created, since a proper constructor is not found. This test attempts to create a Roller Blind Actuator.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenNonNullSettingsToARollerBlindActuator_WhenCreateActuator_ThenShouldReturnNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("RollerBlindActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        //Act
        Actuator result = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Assert
        assertNull(result);
    }

    /**
     * Success case scenario: Verifies a Switch Actuator is created. To isolate the Class under test, ActuatorFactory,
     * from the SwitchActuator Class, a MockedConstruction is made. A double of all injected dependencies is also made,
     * to fully isolate the ActuatorFactory Class. Settings is passed as null, since it is not required for the type of
     * actuator under test.
     * It is verified that precisely one MockedConstruction is created, and it has the same reference as the result of
     * the 'Act' operation.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void givenValidParameters_WhenCreateActuator_ThenMockObjectShouldBeCreated() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();

        ActuatorNameVO actuatorName = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeID = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeID.getID()).thenReturn("SwitchActuator");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        Settings settings = null;

        int expectedSize = 1;

        try(MockedConstruction<SwitchActuator> actuatorDouble = mockConstruction(SwitchActuator.class)) {

            //Act
            Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

            //Assert
            List<SwitchActuator> actuators = actuatorDouble.constructed();
            assertEquals(expectedSize, actuators.size());

            assertEquals(actuators.get(0), newActuator);
        }
    }


    //INTEGRATION TEST

    /**
     * Test scenario to ensure proper collaboration between all classes.
     * An Actuator is created successfully, since the method getId() is properly accessed and returns a non-empty ID.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void createActuator_SuccessfulCase_WhenGetId_IdIsNotNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        String name = "Test Actuator";
        ActuatorNameVO actuatorName = new ActuatorNameVO(name);
        String type = "SwitchActuator";
        ActuatorTypeIDVO actuatorTypeID = new ActuatorTypeIDVO(type);
        UUID devID = UUID.randomUUID();
        DeviceIDVO deviceID = new DeviceIDVO(devID);
        Settings actuatorSettings = null;

        Actuator switchActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID, actuatorSettings);

        //Act
        ActuatorIDVO actuatorId = (ActuatorIDVO) switchActuator.getId();

        //Arrange
        assertNotNull(actuatorId);
    }
}
