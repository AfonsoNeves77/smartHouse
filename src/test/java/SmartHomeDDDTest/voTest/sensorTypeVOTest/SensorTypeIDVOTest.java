package SmartHomeDDDTest.voTest.sensorTypeVOTest;

import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeIDVOTest {

    @Test
    void testValidParameters_returnsSensorTypeIDVO() {
        UUID sensorTypeID = UUID.randomUUID();
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(sensorTypeID);
        assertNotNull(sensorTypeIDVO);
    }

    @Test
    void testNullSensorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeIDVO(null));
    }


    @Test
    void testGetSensorTypeID_returnsSensorTypeID() {
        UUID sensorTypeID = UUID.randomUUID();
        String expected = sensorTypeID.toString();
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(sensorTypeID);
        assertEquals(expected, sensorTypeIDVO.getID());
    }
}