package SmartHomeDDDTest.domainTest.sensorTypeTest;

import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.vo.ValueObject;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

public class sensorTypeTest {

    /**
     * The following tests make sure that the getter methods in the class
     * perform as expected, with isolation.
     */
    @Test
    void whenGetIDCalled_thenSensorTypeIDIsReturned() {
        //Arrange
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        UnitVO unitDouble = mock(UnitVO.class);

        //Act
        SensorType sensorType = new SensorType(sensorTypeDouble, unitDouble);
        DomainID result = sensorType.getId();
        SensorTypeIDVO expected = sensorTypeDouble;

        //Assert
        assertEquals(result, expected);
    }

    @Test
    void whenGetUnitCalled_thenUnitVOIsReturned() {
        //Arrange
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        UnitVO unitDouble = mock(UnitVO.class);

        //Act
        SensorType sensorType = new SensorType(sensorTypeDouble, unitDouble);
        ValueObject<String> result = sensorType.getUnit();
        UnitVO expected = unitDouble;

        //Assert
        assertEquals(result, expected);
    }

    /**
     * The following tests make sure that when there is
     * a null parameter, an IllegalArgumentException is thrown.
     */
    private void assertThrowsWithNullParameter(SensorTypeIDVO sensorTypeIDVO, UnitVO unitVO) {
        //Arrange
        String expected = "Invalid Parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new SensorType(sensorTypeIDVO, unitVO));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    @Test
    void whenNullSensorTypeIDVO_thenThrowsIllegalArgumentException() {

        SensorTypeIDVO sensorTypeDouble = null;
        UnitVO unitDouble = mock(UnitVO.class);
        //Assert
        assertThrowsWithNullParameter(sensorTypeDouble, unitDouble);
    }

    @Test
    void whenNullUnitVO_thenThrowsIllegalArgumentException() {
        SensorTypeIDVO sensorTypeDouble = mock(SensorTypeIDVO.class);
        UnitVO unitDouble = null;
        //Assert
        assertThrowsWithNullParameter(sensorTypeDouble, unitDouble);

    }
}
