package SmartHome.domainTest;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.actuator.SwitchActuator;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ActuatorCatalogueTest {

    @Test
    void invalidCreationCatalogue_NullConfigurationObject(){

        //Arrange
        Configuration configuration = null;
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(configuration));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

//    @Test
//    void validCreationCatalogueUsingConfigurationObject() throws InstantiationException {
//
//        //Arrange
//        Configuration configuration = new PropertyListConfiguration();
//        //Act
//        ActuatorCatalogue catalogue = new ActuatorCatalogue(configuration);
//        //Assert
//        assertNotNull(catalogue);
//    }


    @Test
    void invalidCreationCatalogue_EmptyFilePath(){

        //Arrange
        String filePath = "";
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }


    @Test
    void invalidCreationCatalogue_NullFilePath(){

        //Arrange
        String filePath = null;
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void invalidCreationCatalogue_BlancFilePath(){

        //Arrange
        String filePath = "      ";
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void invalidCreationCatalogue_InvalidFilePath(){

        //Arrange
        String filePath = "cbdhwbdcvjvjv";
        String expected = "Error reading file";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new ActuatorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getListOfActuatorTypes() throws InstantiationException {
        //Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        String expected = "SmartHome.domain.actuator.SwitchActuator";
        //Act
        List<String> listOfSensorTypes= catalogue.getListOfInstantiableActuators();
        String result = listOfSensorTypes.get(0);
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void createActuatorTest_SuccessfulCreation() throws InstantiationException {
        //Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        String actuatorName = "SwitchActuator";
        String actuatorType = "SmartHome.domain.actuator.SwitchActuator";
        //Act
        Actuator actuatorNew = catalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);
        //Assert
        assertEquals(SwitchActuator.class, actuatorNew.getClass());
        // Assertion method awaits validation. We are not sure we are actually creating a Switch Actuator.
//        assertNotNull(actuatorNew);
    }
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
    @Test
    void createActuatorTest_UnsuccessfulCreation_NullActuatorName() throws InstantiationException {
        //Arrange
        ActuatorCatalogue catalogue = new ActuatorCatalogue("config.properties");
        SimHardwareAct simHardwareActDouble = mock(SimHardwareAct.class);
        String actuatorName = null;
        String actuatorType = "SmartHome.domain.actuator.SwitchActuator";
        //Act
        Actuator actuatorNew = catalogue.createActuator(actuatorName, actuatorType, simHardwareActDouble);
        //Assert
        assertNull(actuatorNew);
    }

}
