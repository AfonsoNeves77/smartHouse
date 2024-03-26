package SmartHomeDDDTest.domainTest.sensorTypeTest;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.domain.sensorType.SensorTypeFactory;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

class SensorTypeFactoryTest {

    /**
     * Test that verifies if the SensorTypeFactory creates a SensorType Entity with the given sensorTypeIDVO and unitVO.
     */
    @Test
    void givenSensorTypeFactory_whenCreateSensorType_thenSensorTypeIsCreated() {
        // Arrange
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeIDVO sensorTypeIDVODouble = mock(SensorTypeIDVO.class);
        UnitVO unitVODouble = mock(UnitVO.class);
        try(MockedConstruction<SensorType> sensorTypeMockedConstruction = mockConstruction(SensorType.class)) {
            // Act
            SensorType sensorType = sensorTypeFactory.createSensorType(sensorTypeIDVODouble, unitVODouble);
            // Assert
            List<SensorType> sensorTypeList = sensorTypeMockedConstruction.constructed();
            SensorType sensorTypeResult = sensorTypeList.get(0);
            assertEquals(sensorType, sensorTypeResult);
        }
    }

    /**
     * Test that verifies if the SensorTypeFactory returns null when the sensorTypeIDVO is null.
     */
    @Test
    void givenSensorTypeFactory_whenCreateSensorTypeWithNullSensorTypeIDVO_thenSensorTypeIsNotCreated() {
        // Arrange
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeIDVO sensorTypeIDVODouble = null;
        UnitVO unitVODouble = mock(UnitVO.class);
        // Act
        SensorType sensorType = sensorTypeFactory.createSensorType(sensorTypeIDVODouble, unitVODouble);
        // Assert
        assertNull(sensorType);
    }

    /**
     * Test that verifies if the SensorTypeFactory returns null when the unitVO is null.
     */
    @Test
    void givenSensorTypeFactory_whenCreateSensorTypeWithNullUnitVO_thenSensorTypeIsNotCreated() {
        // Arrange
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeIDVO sensorTypeIDVODouble = mock(SensorTypeIDVO.class);
        UnitVO unitVODouble = null;
        // Act
        SensorType sensorType = sensorTypeFactory.createSensorType(sensorTypeIDVODouble, unitVODouble);
        // Assert
        assertNull(sensorType);
    }
}