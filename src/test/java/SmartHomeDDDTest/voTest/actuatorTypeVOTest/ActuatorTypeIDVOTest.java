package SmartHomeDDDTest.voTest.actuatorTypeVOTest;

import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ActuatorTypeIDVOTest {
    @Test
    void testValidParameters_returnsActuatorTypeIDVO() {
    UUID actuatorTypeID = UUID.randomUUID();
    ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
    assertNotNull(actuatorTypeIDVO);
}

    @Test
    void testNullActuatorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new ActuatorTypeIDVO(null));
    }


    @Test
    void testGetActuatorTypeID_returnsActuatorTypeID() {
        UUID actuatorTypeID = UUID.randomUUID();
        String expected = actuatorTypeID.toString();
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        assertEquals(expected, actuatorTypeIDVO.getID());
    }
}