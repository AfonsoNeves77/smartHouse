package SmartHomeDDDTest.domainTest.actuatorTypeTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.domain.actuatorType.ActuatorTypeFactory;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorTypeTest {

    /**
     * Test if the ActuatorType can create an ActuatorType object given a ActuatorTypeIDVO
     *
     * @result An ActuatorType object is created
     */
    @Test
    void givenValidActuatorTypeIDVO_whenCreatingActuatorType_thenReturnActuatorType() {
        // Arrange
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);

        // Act
        ActuatorType actuatorType = new ActuatorType(actuatorTypeIDVODouble);
        // Assert
        assertNotNull(actuatorType);

    }

    /**
     * Test if the ActuatorType throws an IllegalArgumentException when creating an ActuatorType object given a null ActuatorTypeIDVO
     *
     * @result An IllegalArgumentException is thrown
     */
    @Test
    void givenNullActuatorTypeIDVO_whenCreatingActuatorType_thenThrowIllegalArgumentException() {
        // Arrange
        ActuatorTypeIDVO actuatorTypeIDVODouble = null;

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            new ActuatorType(actuatorTypeIDVODouble);
        });
    }

    /**
     * Test if the ActuatorType gets the ActuatorType string
     *
     * @result The ActuatorType string is returned
     */
    @Test
    void givenActuatorTypeIDVO_whenGettingActuatorType_thenReturnActuatorType() {
        // Arrange
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);
        ActuatorType actuatorType = new ActuatorType(actuatorTypeIDVODouble);
        when(actuatorTypeIDVODouble.toString()).thenReturn("ActuatorType");

        // Act
        String actuatorTypeString = actuatorType.getId().toString();
        String expected = "ActuatorType";

        // Assert
        assertEquals(expected, actuatorTypeString);
    }

    /**
     * Test if the ActuatorType gets the ID
     *
     * @result The ActuatorTypeIDVO is returned
     */
    @Test
    void givenActuatorTypeIDVO_whenGettingID_thenReturnActuatorTypeIDVO() {
        // Arrange
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);
        ActuatorType actuatorType = new ActuatorType(actuatorTypeIDVODouble);

        // Act
        ActuatorTypeIDVO actuatorTypeIDVO = actuatorType.getId();

        // Assert
        assertEquals(actuatorTypeIDVODouble, actuatorTypeIDVO);
    }
}