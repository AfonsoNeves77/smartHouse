package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.sensor.ListOfSensors;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListOfActuatorsTest {
    @Test
    void addActuatorToDevice_Success_Isolation()  {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);
        Actuator actuatorDouble = mock(Actuator.class);

        when(catalogue.createActuator(actuatorName, actuatorType, simHardware)).thenReturn(actuatorDouble);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertTrue(result);
    }

    @Test
    void addActuatorToDevice_UnableToInstantiateSensor_Isolation()  {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);

        when(catalogue.createActuator(actuatorName, actuatorType,simHardware)).thenReturn(null);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }
    @Test
    void addActuatorToDevice_FalseDueToDuplicatedActuator_Isolation()  {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = mock(ActuatorCatalogue.class);
        Actuator actuatorDouble = mock(Actuator.class);

        when(catalogue.createActuator(actuatorName, actuatorType, simHardware)).thenReturn(actuatorDouble);
        when(actuatorDouble.getName()).thenReturn(actuatorName);
        listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    ///////////////////////////////////////////////////INTEGRATION/////////////////////////////////////////////////////////

    @Test
    void addActuatorToDevice_UnableToInstantiateSensorDueToBlankName_integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");

        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void addActuatorToDevice_UnableToInstantiateSensorDueToNullName_integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator 1";
        String actuatorType = "XPTO";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }

    @Test
    void addTemperatureSensorToDevice_Success_integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator1";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertTrue(result);
    }

    @Test
    void addActuatorToDevice_FailsDueToDuplicatedDevice_integration() throws InstantiationException {
        //Arrange
        SimHardwareAct simHardware = mock(SimHardwareAct.class);
        String actuatorName = "Actuator1";
        String actuatorType = "SmartHome.domain.actuator.BlindRollerActuator";
        ListOfActuators listOfActuators = new ListOfActuators();
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Act
        boolean result = listOfActuators.addActuator(actuatorName,actuatorType,catalogue,simHardware);
        //Assert
        assertFalse(result);
    }
}


