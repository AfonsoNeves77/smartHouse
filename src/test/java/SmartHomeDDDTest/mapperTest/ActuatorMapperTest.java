package SmartHomeDDDTest.mapperTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.dto.ActuatorDTO;
import SmartHomeDDD.mapper.ActuatorMapper;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * ActuatorMapper test class
 */

public class ActuatorMapperTest {

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create an ActuatorIDVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateActuatorIDVO_ThenThrowException() {
//        Arrange
        ActuatorDTO actuatorDTO = null;
        String expectedMessage = "Invalid DTO, ActuatorDTO cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createActuatorIDVO(actuatorDTO));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create an ActuatorNameVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateActuatorNameVO_ThenThrowException() {
//        Arrange
        ActuatorDTO actuatorDTO = null;
        String expectedMessage = "Invalid DTO, ActuatorDTO cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createActuatorNameVO(actuatorDTO));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create an ActuatorTypeIDVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateActuatorTypeIDVO_ThenThrowException() {
//        Arrange
        ActuatorDTO actuatorDTO = null;
        String expectedMessage = "Invalid DTO, ActuatorDTO cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createActuatorTypeIDVO(actuatorDTO));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an exception is thrown when the ActuatorDTO object is null.
     * Should throw an IllegalArgumentException with the message "Invalid DTO, ActuatorDTO cannot be null" since it fails to create a DeviceIDVO object.
     */

    @Test
    void givenNullActuatorDTO_WhenCreateDeviceIDVO_ThenThrowException() {
//        Arrange
        ActuatorDTO actuatorDTO = null;
        String expectedMessage = "Invalid DTO, ActuatorDTO cannot be null";
//        Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ActuatorMapper.createDeviceIDVO(actuatorDTO));
//        Assert
        String resultingMessage = exception.getMessage();
        assertEquals(expectedMessage, resultingMessage);
    }

    /**
     * Test to verify that an ActuatorIDVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateActuatorIDVO_ThenReturnActuatorIDVO() {
//        Arrange
        String expectedActuatorID = "123e4567-e89b-12d3-a456-426651110000";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, expectedActuatorName, actuatorType, deviceID);
//        Act
        ActuatorIDVO actuatorIDVO = ActuatorMapper.createActuatorIDVO(actuatorDTO);
//        Assert
        assertEquals(expectedActuatorID, actuatorIDVO.getID());
    }

    /**
     * Test to verify that an ActuatorNameVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateActuatorNameVO_ThenReturnActuatorNameVO() {
//        Arrange
        String expectedActuatorID = "123e4567-e89b-12d3-a456-426651110000";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, expectedActuatorName, actuatorType, deviceID);
//        Act
        ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTO);
//        Assert
        assertEquals(expectedActuatorName, actuatorNameVO.getValue());
    }

    /**
     * Test to verify that an ActuatorTypeIDVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateActuatorTypeIDVO_ThenReturnActuatorTypeIDVO() {
//        Arrange
        String expectedActuatorID = "123e4567-e89b-12d3-a456-426651110000";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "frt3-567p-32za";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, expectedActuatorName, actuatorType, deviceID);
//        Act
        ActuatorTypeIDVO actuatorTypeIDVO = ActuatorMapper.createActuatorTypeIDVO(actuatorDTO);
//        Assert
        assertEquals(actuatorType, actuatorTypeIDVO.getID());
    }

    /**
     * Test to verify that a DeviceIDVO object is created correctly.
     */

    @Test
    void givenActuatorDTO_WhenCreateDeviceIDVO_ThenReturnDeviceIDVO() {
//        Arrange
        String expectedActuatorID = "123e4567-e89b-12d3-a456-426651110000";
        String expectedActuatorName = "Smart Thermostat";
        String actuatorType = "Thermostat";
        String deviceID = "123e4567-e89b-12d3-a456-111111111111";
        ActuatorDTO actuatorDTO = new ActuatorDTO(expectedActuatorID, expectedActuatorName, actuatorType, deviceID);
//        Act
        DeviceIDVO deviceIDVO = ActuatorMapper.createDeviceIDVO(actuatorDTO);
//        Assert
        assertEquals(deviceID, deviceIDVO.getID());
    }

    /**
     * Test to verify if the domainToDTO method returns a list of ActuatorDTO objects.
     * Should return a list of ActuatorDTO objects with the same size as the list of Actuator objects.
     * The test makes use of mock objects to simulate the Actuator objects in order to facilitate testing.
     */

    @Test
    void givenActuatorList_WhenDomainToDTOIsCalled_ThenShouldReturnActuatorDTOListSizeEqualsOne() {
//        Arrange
        ActuatorIDVO actuatorIDVODouble = mock(ActuatorIDVO.class);
        ActuatorNameVO actuatorNameVODouble = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVODouble = mock(DeviceIDVO.class);

        Actuator actuatorTestDouble = mock(SwitchActuator.class);
        when(actuatorTestDouble.getId()).thenReturn(actuatorIDVODouble);
        when(actuatorTestDouble.getActuatorName()).thenReturn(actuatorNameVODouble);
        when(actuatorTestDouble.getActuatorTypeID()).thenReturn(actuatorTypeIDVODouble);
        when(actuatorTestDouble.getDeviceID()).thenReturn(deviceIDVODouble);

        List<Actuator> actuatorList = new ArrayList<>();
        actuatorList.add(actuatorTestDouble);
        int expectedListSize = 1;
//        Act
        List<ActuatorDTO> actuatorDTOList = ActuatorMapper.domainToDTO(actuatorList);
//        Assert
        assertEquals(expectedListSize, actuatorDTOList.size());
    }

    /**
     * Test to verify if the domainToDTO method returns a list of ActuatorDTO objects.
     * Should return a list of ActuatorDTO objects with the same attributes as the Actuator object that is contained in the list.
     * The test makes use of mock objects to simulate the Actuator objects in order to facilitate testing.
     */

    @Test
    void givenActuatorList_WhenDomainToDTOIsCalled_ThenShouldReturnActuatorDTOList() {
//        Arrange
        String actuatorID = "123e4567-e89b-12d3-a456-426651110000";
        ActuatorIDVO actuatorIDVODouble = mock(ActuatorIDVO.class);
        when(actuatorIDVODouble.getID()).thenReturn(actuatorID);

        String actuatorName = "Smart Thermostat";
        ActuatorNameVO actuatorNameVODouble = mock(ActuatorNameVO.class);
        when(actuatorNameVODouble.getValue()).thenReturn(actuatorName);

        String actuatorType = "Thermostat";
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);
        when(actuatorTypeIDVODouble.getID()).thenReturn(actuatorType);

        String deviceID = "123e4567-e89b-12d3-a456-111111111111";
        DeviceIDVO deviceIDVODouble = mock(DeviceIDVO.class);
        when(deviceIDVODouble.getID()).thenReturn(deviceID);

        Actuator actuatorTestDouble = mock(SwitchActuator.class);
        when(actuatorTestDouble.getId()).thenReturn(actuatorIDVODouble);
        when(actuatorTestDouble.getActuatorName()).thenReturn(actuatorNameVODouble);
        when(actuatorTestDouble.getActuatorTypeID()).thenReturn(actuatorTypeIDVODouble);
        when(actuatorTestDouble.getDeviceID()).thenReturn(deviceIDVODouble);

        List<Actuator> actuatorList = new ArrayList<>();
        actuatorList.add(actuatorTestDouble);
//        Act
        List<ActuatorDTO> actuatorDTOList = ActuatorMapper.domainToDTO(actuatorList);
//        Assert
        assertEquals(actuatorDTOList.get(0).getActuatorID(), actuatorID);
        assertEquals(actuatorDTOList.get(0).getActuatorName(), actuatorName);
        assertEquals(actuatorDTOList.get(0).getActuatorType(), actuatorType);
        assertEquals(actuatorDTOList.get(0).getDeviceID(), deviceID);
    }

    /**
     * Verifies when a valid DTO is received (not null), ActuatorIDVO is created, and when getID() is called on it,
     * the expected actuator ID in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and ActuatorIDVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * ActuatorIDVO Class, in order to condition its behaviour when a ActuatorIDVO is created.
     * It also verifies the number of times the ActuatorIDVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateActuatorIDVO_ThenReturnActuatorIDVO() {
//        Arrange
        String expectedActuatorID = "123e4567-e89b-12d3-a456-426651110000";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getActuatorID()).thenReturn(expectedActuatorID);

        try (MockedConstruction<ActuatorIDVO> mockedActuatorIDVO = mockConstruction(ActuatorIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123e4567-e89b-12d3-a456-426651110000");
        })) {
//            Act
            ActuatorIDVO actuatorIDVO = ActuatorMapper.createActuatorIDVO(actuatorDTODouble);
//            Assert
            List<ActuatorIDVO> listOfMockedActuatorIDVO = mockedActuatorIDVO.constructed();
            assertEquals(expectedListSize, listOfMockedActuatorIDVO.size());
            assertEquals(expectedActuatorID, actuatorIDVO.getID());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), ActuatorNameVO is created, and when getValue() is called on it,
     * the expected actuator name in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and ActuatorNameVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * ActuatorNameVO Class, in order to condition its behaviour when a ActuatorNameVO is created.
     * It also verifies the number of times the ActuatorNameVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateActuatorNameVO_ThenReturnActuatorNameVO() {
//        Arrange
        String expectedActuatorName = "Smart Thermostat";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getActuatorName()).thenReturn(expectedActuatorName);

        try (MockedConstruction<ActuatorNameVO> mockedActuatorNameVO = mockConstruction(ActuatorNameVO.class, (mock, context) -> {
            when(mock.getValue()).thenReturn("Smart Thermostat");
        })) {
//            Act
            ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTODouble);
//            Assert
            List<ActuatorNameVO> listOfMockedActuatorNameVO = mockedActuatorNameVO.constructed();
            assertEquals(expectedListSize, listOfMockedActuatorNameVO.size());
            assertEquals(expectedActuatorName, actuatorNameVO.getValue());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), ActuatorTypeIDVO is created, and when getID() is called on it,
     * the expected actuator type in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and ActuatorTypeIDVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * ActuatorTypeIDVO Class, in order to condition its behaviour when a ActuatorTypeIDVO is created.
     * It also verifies the number of times the ActuatorTypeIDVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateActuatorTypeIDVO_ThenReturnActuatorTypeIDVO() {
//        Arrange
        String actuatorType = "Thermostat";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getActuatorType()).thenReturn(actuatorType);

        try (MockedConstruction<ActuatorTypeIDVO> mockedActuatorTypeIDVO = mockConstruction(ActuatorTypeIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn("Thermostat");
        })) {
//            Act
            ActuatorTypeIDVO actuatorTypeIDVO = ActuatorMapper.createActuatorTypeIDVO(actuatorDTODouble);
//            Assert
            List<ActuatorTypeIDVO> listOfMockedActuatorTypeIDVO = mockedActuatorTypeIDVO.constructed();
            assertEquals(expectedListSize, listOfMockedActuatorTypeIDVO.size());
            assertEquals(actuatorType, actuatorTypeIDVO.getID());
        }
    }

    /**
     * Verifies when a valid DTO is received (not null), DeviceIDVO is created, and when getID() is called on it,
     * the expected device ID in a string format is retrieved.
     * ActuatorMapper Class has two collaborators in this scenario, ActuatorDTO and DeviceIDVO classes. To isolate
     * the class under test (ActuatorMapper), a double of the ActuatorDTO class is made as well as a MockedConstruction of the
     * DeviceIDVO Class, in order to condition its behaviour when a DeviceIDVO is created.
     * It also verifies the number of times the DeviceIDVO constructor is called.
     */

    @Test
    void givenActuatorDTOMock_WhenCreateDeviceIDVO_ThenReturnDeviceIDVO() {
//        Arrange
        String deviceID = "123e4567-e89b-12d3-a456-111111111111";
        int expectedListSize = 1;
        ActuatorDTO actuatorDTODouble = mock(ActuatorDTO.class);
        when(actuatorDTODouble.getDeviceID()).thenReturn(deviceID);

        try (MockedConstruction<DeviceIDVO> mockedDeviceIDVO = mockConstruction(DeviceIDVO.class, (mock, context) -> {
            when(mock.getID()).thenReturn("123e4567-e89b-12d3-a456-111111111111");
        })) {
//            Act
            DeviceIDVO deviceIDVO = ActuatorMapper.createDeviceIDVO(actuatorDTODouble);
//            Assert
            List<DeviceIDVO> listOfMockedDeviceIDVO = mockedDeviceIDVO.constructed();
            assertEquals(expectedListSize, listOfMockedDeviceIDVO.size());
            assertEquals(deviceID, deviceIDVO.getID());
        }
    }
}