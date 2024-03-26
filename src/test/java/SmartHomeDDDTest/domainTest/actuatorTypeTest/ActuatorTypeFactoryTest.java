package SmartHomeDDDTest.domainTest.actuatorTypeTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.domain.actuatorType.ActuatorTypeFactory;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorTypeFactoryTest {

    /**
     * Test if the ActuatorTypeFactory can create an ActuatorType object given a ActuatorTypeIDVO
     *
     * @result An ActuatorType object is created
     */
    @Test
    void givenActuatorTypeIDVO_whenCreatingActuatorType_thenReturnActuatorType() {
        // Arrange
        ActuatorTypeIDVO actuatorTypeIDVODouble = mock(ActuatorTypeIDVO.class);
        ActuatorTypeFactory factoryActuatorType = new ActuatorTypeFactory();

        // Act
        ActuatorType actuatorType = factoryActuatorType.createActuatorType(actuatorTypeIDVODouble);

        // Assert
        assertNotNull(actuatorType);
    }
}