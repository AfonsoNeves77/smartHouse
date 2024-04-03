package SmartHomeDDDTest.dtoTest;

import SmartHomeDDD.dto.ActuatorDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ActuatorDTO test class
 */

public class ActuatorDTOTest {

    /**
     * Test to verify that the ActuatorDTO object is created correctly.
     */

    @Test
    void givenCorrectParameters_WhenCreateActuatorDTO_ThenReturnActuatorDTO() {
//        Arrange
        String expectedActuatorID = "frt3-567p-32gf";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
//        Act
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, expectedActuatorName, actuatorType, deviceID);
//        Assert
        assertEquals(expectedActuatorName, actuatorDTO.getActuatorName());
        assertEquals(expectedActuatorID, actuatorDTO.getActuatorID());
        assertEquals(actuatorType, actuatorDTO.getActuatorType());
        assertEquals(deviceID, actuatorDTO.getDeviceID());
    }

    /**
     * Test to verify that the ActuatorDTO object is created correctly without the actuator ID.
     */

    @Test
    void givenCorrectParametersWithoutId_WhenCreateActuatorDTO_ThenReturnActuatorDTO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
//        Act
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorName, actuatorType, deviceID);
//        Assert
        assertEquals(expectedActuatorName, actuatorDTO.getActuatorName());
        assertEquals(actuatorType, actuatorDTO.getActuatorType());
        assertEquals(deviceID, actuatorDTO.getDeviceID());
    }

    /**
     * Test to verify that the actuator name is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetActuatorName_ThenReturnActuatorName() {
//        Arrange
        String actuatorID = "frt3-567p-32gf";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorID, expectedActuatorName, actuatorType, deviceID);
//        Act
        String resultActuatorName = actuatorDTO.getActuatorName();
//        Assert
        assertEquals(expectedActuatorName, resultActuatorName);
    }

    /**
     * Test to verify that the actuator ID is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetActuatorID_ThenReturnActuatorID() {
//        Arrange
        String expectedActuatorID = "frt3-567p-32gf";
        String actuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, actuatorName, actuatorType, deviceID);
//        Act
        String resultActuatorID = actuatorDTO.getActuatorID();
//        Assert
        assertEquals(expectedActuatorID, resultActuatorID);
    }

    /**
     * Test to verify that the actuator type is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetActuatorType_ThenReturnActuatorType() {
//        Arrange
        String actuatorID = "frt3-567p-32gf";
        String actuatorName = "Smart Thermostat";
        String expectedActuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorID, actuatorName, expectedActuatorType, deviceID);
//        Act
        String resultActuatorType = actuatorDTO.getActuatorType();
//        Assert
        assertEquals(expectedActuatorType, resultActuatorType);
    }

    /**
     * Test to verify that the device ID is retrieved correctly.
     */

    @Test
    void givenActuatorDTO_WhenGetDeviceID_ThenReturnDeviceID() {
//        Arrange
        String actuatorID = "frt3-567p-32gf";
        String actuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String expectedDeviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorID, actuatorName, actuatorType, expectedDeviceID);
//        Act
        String resultDeviceID = actuatorDTO.getDeviceID();
//        Assert
        assertEquals(expectedDeviceID, resultDeviceID);
    }
}
