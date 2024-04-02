package SmartHomeDDDTest.domainTest.actuatorTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ActuatorFactoryTest {

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
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
           factory.createActuator(actuatorName, actuatorTypeID, deviceID);
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
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createActuator(actuatorName, actuatorTypeID, deviceID);
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
        String expected = "Invalid actuator parameters";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            factory.createActuator(actuatorName, actuatorTypeID, deviceID);
        });
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * Verifies if actuator type is not mentioned in the accessed file containing actuator details and paths,
     * the Actuator is not created.
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
        when(actuatorTypeID.getID()).thenReturn("xpto");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        //Act
        Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID);

        //Assert
        assertNull(newActuator);

    }

    /**
     * Verifies if actuator type is mentioned in the accessed file containing actuator details and paths, but
     * its path is incorrect, the Actuator is not created.
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
        when(actuatorTypeID.getID()).thenReturn("Hydraulic");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        //Act
        Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID);

        //Assert
        assertNull(newActuator);
    }

    /**
     * Success case scenario: Verifies a Switch Actuator is created. To isolate the Class under test, ActuatorFactory,
     * from the SwitchActuator Class, a MockedConstruction is made. A double of all injected dependencies is also made,
     * to fully isolate the ActuatorFactory Class.
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

        int expectedSize = 1;

        try(MockedConstruction<SwitchActuator> actuatorDouble = mockConstruction(SwitchActuator.class)) {

            //Act
            Actuator newActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID);

            //Assert
            List<SwitchActuator> actuators = actuatorDouble.constructed();
            assertEquals(expectedSize, actuators.size());

            assertEquals(actuators.get(0), newActuator);
        }
    }

    //IntegrationTest

    /**
     * Test scenario to ensure proper collaboration between all classes.
     * An Actuator is created successfully, since the method getId() is properly accessed and returns a non-empty ID.
     * @throws ConfigurationException If file path used is invalid (it is encapsulated in the ActuatorFactory Class)
     */
    @Test
    void createActuator_SuccessfulCase_WhenGetId_IdIsNotNull() throws ConfigurationException {
        //Arrange
        ActuatorFactory factory = new ActuatorFactory();
        String name = "My actuator";
        ActuatorNameVO actuatorName = new ActuatorNameVO(name);
        String type = "SwitchActuator";
        ActuatorTypeIDVO actuatorTypeID = new ActuatorTypeIDVO(type);
        UUID devID = UUID.randomUUID();
        DeviceIDVO deviceID = new DeviceIDVO(devID);

        Actuator switchActuator = factory.createActuator(actuatorName, actuatorTypeID, deviceID);

        //Act
        ActuatorIDVO actuatorId = (ActuatorIDVO) switchActuator.getId();

        //Arrange
        assertNotNull(actuatorId);
    }

}
