package SmartHome.domainTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.actuator.SwitchActuator;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ActuatorCatalogueTest {

    /**
     * Unit test to verify the behavior when attempting to create an ActuatorCatalogue with a null Configuration object.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_NullConfigurationObject() {
        // Arrange
        Configuration configuration = null;
        String expectedErrorMessage = "Invalid parameters";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(configuration));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior when attempting to create an ActuatorCatalogue with an empty file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_EmptyFilePath() {
        // Arrange
        String filePath = "";
        String expectedErrorMessage = "Invalid parameters";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior when attempting to create an ActuatorCatalogue with a null file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_NullFilePath() {
        // Arrange
        String filePath = null;
        String expectedErrorMessage = "Invalid parameters";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior when attempting to create an ActuatorCatalogue with a blank file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_BlankFilePath() {
        // Arrange
        String filePath = "      ";
        String expectedErrorMessage = "Invalid parameters";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior when attempting to create an ActuatorCatalogue with an invalid file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Error reading file."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_InvalidFilePath() {
        // Arrange
        String filePath = "cbdhwbdcvjvjv";
        String expectedErrorMessage = "Error reading file";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior of getting the list of actuator types from an ActuatorCatalogue initialized with a valid configuration file.

     * The test checks for the following:
     * - The ActuatorCatalogue is created successfully with a valid configuration file.
     * - The list of instantiable actuator types is retrieved, and the first element is checked against the expected value.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void getListOfActuatorTypes() throws InstantiationException {
        // Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        String expectedActuatorType = "SmartHome.domain.actuator.SwitchActuator";

        // Act
        List<String> listOfActuatorTypes = catalogue.getListOfInstantiableActuators();
        String actualActuatorType = listOfActuatorTypes.get(0);

        // Assert
        assertEquals(expectedActuatorType, actualActuatorType);
    }

    /**
     * Unit test to verify the behavior of getting the list of actuator types from an ActuatorCatalogue initialized with a Configuration object.

     * The test checks for the following:
     * - A Configuration object is created with actuator types.
     * - An ActuatorCatalogue is created with the Configuration object.
     * - The list of instantiable actuator types is retrieved, and the first element is checked against the expected value.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void getListOfActuatorTypes_UsingConfigurationObject() throws InstantiationException {
        // Arrange
        Configuration configuration = new PropertyListConfiguration();
        configuration.addProperty("actuator", "SmartHome.domain.actuator.SwitchActuator");
        ActuatorCatalogue catalogue = new ActuatorCatalogue(configuration);
        String expectedActuatorType = "SmartHome.domain.actuator.SwitchActuator";

        // Act
        List<String> listOfActuatorTypes = catalogue.getListOfInstantiableActuators();
        String actualActuatorType = listOfActuatorTypes.get(0);

        // Assert
        assertEquals(expectedActuatorType, actualActuatorType);
    }

    /**
     * Unit test to verify the behavior when attempting to create an actuator with an invalid type.

     * The test checks for the following:
     * - The ActuatorCatalogue is created successfully with a valid configuration file.
     * - An actuator is attempted to be created with an invalid type.
     * - The created actuator is expected to be null.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */

    @Test
    void createActuatorTest_UnsuccessfulCreation_InvalidActuatorType() throws InstantiationException {
        //Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        String actuatorName = "SwitchActuator";
        String actuatorType = "SmartHome.domain.actuator.NOXActuator";
        //Act
        Actuator actuatorNew = catalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);
        //Assert
        assertNull(actuatorNew);
    }
    /**
     * Unit test to verify the behavior when attempting to create an actuator with a null name.
     *
     * The test checks for the following:
     * - The ActuatorCatalogue is created successfully with a valid configuration file.
     * - An actuator is attempted to be created with a null name.
     * - The created actuator is expected to be null.
     *
     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void createActuatorTest_UnsuccessfulCreation_NullActuatorName() throws InstantiationException {
        // Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        String actuatorName = null;
        String actuatorType = "SmartHome.domain.actuator.SwitchActuator";

        // Act
        Actuator actuatorNew = catalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);

        // Assert
        assertNull(actuatorNew);
    }

    /**
     * Unit test to isolate the creation of a SwitchActuator.
     *
     * The test checks for the following:
     * - A SwitchActuator is created successfully.
     * - The actuator's name is verified against the expected name.
     * - The ActuatorCatalogue is created successfully with a valid configuration file.
     *
     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void creatingSwitchActuator_IsolationTest() throws InstantiationException {
        // Arrange
        String actuatorName = "actuator";
        String actuatorType = "SmartHome.domain.actuator.SwitchActuator";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);

        ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue("config.properties");

        try(MockedConstruction<SwitchActuator> actuatorDouble = mockConstruction(SwitchActuator.class, (mock, context) -> {
            String actualName = (String) context.arguments().get(0);
            when(mock.getName()).thenReturn(actualName);
        })) {


            // Act
            Actuator newActuator = actuatorCatalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);

            // Assert
            List<SwitchActuator> actuators = actuatorDouble.constructed();
            assertEquals(1, actuators.size());
            assertEquals(actuatorName, actuatorDouble.constructed().get(0).getName());
            assertEquals(actuatorName, newActuator.getName());
        }
    }

    /**
     * Unit test to isolate the scenario where creating a sensor throws an InstantiationException.

     * The test checks for the following:
     * - A SwitchActuator is attempted to be created, but a simulated InstantiationException is thrown.
     * - The created actuator is expected to be null.
     * - The ActuatorCatalogue is created successfully with a valid configuration file.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void creatingSensorThatThrowsInstantiationException_IsolationTest() throws InstantiationException {
        // Arrange
        String actuatorName = "actuator";
        String actuatorType = "SmartHome.domain.actuator.SwitchActuator";
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);

        try (MockedConstruction<SwitchActuator> actuatorDouble = mockConstruction(SwitchActuator.class, (mock, context) -> {
            throw new InstantiationException("Simulated InstantiationException");
        })) {
            ActuatorCatalogue actuatorCatalogue = new ActuatorCatalogue("config.properties");

            // Act
            Actuator actuator = actuatorCatalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);

            // Assert
            assertNull(actuator);
            List<SwitchActuator> actuators = actuatorDouble.constructed();
            assertEquals(0, actuators.size());
        }
    }



}
