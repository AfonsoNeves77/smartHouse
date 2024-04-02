package SmartHomeDDDTest.mapperTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.dto.ActuatorTypeDTO;
import SmartHomeDDD.mapper.ActuatorTypeMapper;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeMapperTest {

    @Test
    void domainToDTO() {
        ActuatorType actuator1Double = mock(ActuatorType.class);
        ActuatorType actuator2Double = mock(ActuatorType.class);
        ActuatorType actuator3Double = mock(ActuatorType.class);
        when(actuator1Double.getActuatorType()).thenReturn("actuator1");
        when(actuator2Double.getActuatorType()).thenReturn("actuator2");
        when(actuator3Double.getActuatorType()).thenReturn("actuator3");
        List<ActuatorType> list = new ArrayList<>();
        list.add(actuator1Double);
        list.add(actuator2Double);
        list.add(actuator3Double);
        List<ActuatorTypeDTO> listDTO = ActuatorTypeMapper.domainToDTO(list);
        assertEquals("actuator1", listDTO.get(0).getActuatorTypeID());
        assertEquals("actuator2", listDTO.get(1).getActuatorTypeID());
        assertEquals("actuator3", listDTO.get(2).getActuatorTypeID());


    }

    @Test
    void createActuatorTypeIDVO() {
        ActuatorType actuator1Double = mock(ActuatorType.class);
        ActuatorType actuator2Double = mock(ActuatorType.class);
        ActuatorType actuator3Double = mock(ActuatorType.class);
        when(actuator1Double.getActuatorType()).thenReturn("actuator1");
        when(actuator2Double.getActuatorType()).thenReturn("actuator2");
        when(actuator3Double.getActuatorType()).thenReturn("actuator3");
        String actuator1 = actuator1Double.getActuatorType();
        String actuator2 = actuator2Double.getActuatorType();
        String actuator3 = actuator3Double.getActuatorType();
        ActuatorTypeIDVO actuatorTypeIDVO1 = ActuatorTypeMapper.createActuatorTypeIDVO(actuator1);
        ActuatorTypeIDVO actuatorTypeIDVO2 = ActuatorTypeMapper.createActuatorTypeIDVO(actuator2);
        ActuatorTypeIDVO actuatorTypeIDVO3 = ActuatorTypeMapper.createActuatorTypeIDVO(actuator3);
        assertEquals("actuator1", actuatorTypeIDVO1.getID());
        assertEquals("actuator2", actuatorTypeIDVO2.getID());
        assertEquals("actuator3", actuatorTypeIDVO3.getID());
    }
}