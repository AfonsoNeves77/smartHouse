package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.actuator.SimHardwareAct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListOfActuatorsTest {

    ///////////////////////////////////////////////////ISOLATION TEST/////////////////////////////////////////////////////////

    /**
     * This test case verifies the behavior of adding an actuator to ListOfActuators successfully.

     * This test checks for the following:
     * - An actuator with the specified name and type is successfully added to ListOfActuators.
     * - Mock constructions of SimHardwareAct and ActuatorCatalogue are isolated.
     * - The expected result from addActuator is true.
     */
    @Test
    void addActuatorToDevice_Success_Isolation() {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);

        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);
        Actuator actuatorDouble = mock(Actuator.class);

        when(catalogue.createActuator(actuatorName, actuatorType, simHardware)).thenReturn(actuatorDouble);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertTrue(result);
    }

    /**
     * This test case verifies the behavior when unable to instantiate an actuator in ListOfActuators.

     * This test checks for the following:
     * - An actuator with the specified name and type is unable to be instantiated in ListOfActuators.
     * - Mock constructions of SimHardwareAct and ActuatorCatalogue are isolated.
     * - The expected result from addActuator is false.
     */

    @Test
    void addActuatorToDevice_UnableToInstantiateSensor_Isolation() {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);

        when(catalogue.createActuator(actuatorName, actuatorType, simHardware)).thenReturn(null);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertFalse(result);
    }

    /**
     * This test case verifies the behavior when attempting to add a duplicated actuator to ListOfActuators.

     * This test checks for the following:
     * - An attempt to add an actuator with a duplicated name is unsuccessful in ListOfActuators.
     * - Mock constructions of SimHardwareAct and ActuatorCatalogue are isolated.
     * - The expected result from addActuator is false.
     */

    @Test
    void addActuatorToDevice_FalseDueToDuplicatedActuator_Isolation() {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);
        Actuator actuatorDouble = mock(Actuator.class);

        when(catalogue.createActuator(actuatorName, actuatorType, simHardware)).thenReturn(actuatorDouble);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertFalse(result);
    }

    ///////////////////////////////////////////////////INTEGRATION/////////////////////////////////////////////////////////

    @Test
    void addActuatorToDevice_UnableToInstantiateSensorDueToBlankName_Integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");

        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void addActuatorToDevice_UnableToInstantiateSensorDueToNullName_Integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void addTemperatureSensorToDevice_Success_Integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator1";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertTrue(result);
    }

    @Test
    void addActuatorToDevice_FailsDueToDuplicatedDevice_Integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator1";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName, actuatorType, catalogue, simHardware);
        //Assert
        assertFalse(result);
    }
}


