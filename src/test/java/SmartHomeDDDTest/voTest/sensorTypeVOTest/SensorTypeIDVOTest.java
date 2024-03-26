package SmartHomeDDDTest.voTest.sensorTypeVOTest;

import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SensorTypeIDVOTest {

    @Test
    void testValidParameters_returnsSensorTypeIDVO() {
        String type = "humidity";
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(type);
        assertNotNull(sensorTypeIDVO);
    }

    @Test
    void testNullSensorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeIDVO(null));
    }

    @Test
    void testEmptySensorTypeID_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new SensorTypeIDVO(" "));
    }


    @Test
    void testGetSensorTypeID_returnsSensorTypeID() {
        String expected = "humidity";
        String type = "humidity";
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(type);;
        assertEquals(expected, sensorTypeIDVO.getID());
    }
}