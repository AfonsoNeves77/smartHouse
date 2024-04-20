package smarthome.controller;

import smarthome.mapper.dto.ActuatorTypeDTO;
import smarthome.persistence.mem.ActuatorTypeRepositoryMem;
import smarthome.service.ActuatorTypeServiceImpl;
import smarthome.domain.actuatortype.ActuatorTypeFactoryImpl;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GetListOfActuatorTypesCTRLTest {
    @Test
    void whenGetListOfActuatorTypes_thenReturnActuatorTypeDTO() {
        //Arrange
        String expectedActuatorTypeID1 = "SwitchActuator";
        String expectedActuatorTypeID2 = "RollerBlindActuator";
        String expectedActuatorTypeID3 = "DecimalValueActuator";
        String expectedActuatorTypeID4 = "IntegerValueActuator";
        String path = "actuator.properties";
        ActuatorTypeRepositoryMem actuatorTypeRepository = new ActuatorTypeRepositoryMem();
        ActuatorTypeFactoryImpl actuatorTypeFactory = new ActuatorTypeFactoryImpl();
        ActuatorTypeServiceImpl actuatorTypeService = new ActuatorTypeServiceImpl(actuatorTypeRepository, actuatorTypeFactory, path);
        GetListOfActuatorTypesCTRL getListOfActuatorTypesCTRL = new GetListOfActuatorTypesCTRL(actuatorTypeService);

        //Act
        List<ActuatorTypeDTO> result = getListOfActuatorTypesCTRL.getListOfActuatorTypes();

        //Assert
        assertEquals(4, result.size());
        assertEquals(expectedActuatorTypeID1, result.get(0).getActuatorTypeID());
        assertEquals(expectedActuatorTypeID2, result.get(1).getActuatorTypeID());
        assertEquals(expectedActuatorTypeID3, result.get(2).getActuatorTypeID());
        assertEquals(expectedActuatorTypeID4, result.get(3).getActuatorTypeID());
    }

    @Test
    void givenNullActuatorTypeService_whenGetListOfActuatorTypes_thenThrowIllegalArgumentException() {
        //Act & Assert
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new GetListOfActuatorTypesCTRL(null));
        assertEquals("Invalid service", thrown.getMessage());
    }
}
