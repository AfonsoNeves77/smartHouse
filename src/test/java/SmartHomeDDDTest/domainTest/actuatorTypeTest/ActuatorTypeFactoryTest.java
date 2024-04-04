package SmartHomeDDDTest.domainTest.actuatorTypeTest;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.domain.actuatorType.ActuatorTypeFactory;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ActuatorTypeFactoryTest {

    /*
    SYSTEM UNDER TEST: FACTORY + ACTUATORTYPE IMPLEMENTATION
    A double of all the other collaborators is done (which are the required value objects to create the actuatorType).
     */

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
        ActuatorTypeIDVO resultActuatorTypeIDVO = actuatorType.getId();
        assertEquals(actuatorTypeIDVODouble, resultActuatorTypeIDVO);
    }

    /**
     * Test if the ActuatorTypeFactory can create an ActuatorType object given a ActuatorTypeIDVO
     *
     * @result Throws IllegalArgumentException with the message "ActuatorType cannot be null" and the object is not created
     */
    @Test
    void createActuatorType_whenNullActuatorType_ShouldPropagateIllegalArgumentException() {
        // Arrange
        String expected = "ActuatorType cannot be null";
        ActuatorTypeIDVO actuatorTypeIDVODouble = null;
        ActuatorTypeFactory factoryActuatorType = new ActuatorTypeFactory();

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> factoryActuatorType.createActuatorType(actuatorTypeIDVODouble));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }
}