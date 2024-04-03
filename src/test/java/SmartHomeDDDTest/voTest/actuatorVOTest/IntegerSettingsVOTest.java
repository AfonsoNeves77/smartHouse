package SmartHomeDDDTest.voTest.actuatorVOTest;

import SmartHomeDDD.vo.actuatorVO.IntegerSettingsVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IntegerSettingsVOTest {


    /**
     * Test if the lower limit is greater than the upper limit.
     * When the constructor is called with a lower limit greater than the upper limit, an exception is thrown.
     */
    @Test
    void ifLowerLimitIsGreaterThanUpperLimit_whenConstructorCalled_thenReturnsException(){
        //Arrange
        String lowerLimit = "10";
        String upperLimit = "0";
        String expected = "Upper limit can't be less than or equal to lower limit.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntegerSettingsVO(lowerLimit, upperLimit);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Test if the lower limit is equal to the upper limit.
     * When the constructor is called with a lower limit equal to the upper limit, an exception is thrown.
     */
    @Test
    void ifLowerLimitIsEqualToUpperLimit_whenConstructorCalled_thenReturnsIllegalArgumentException() {
        //Arrange
        String lowerLimit = "10";
        String upperLimit = "10";
        String expected = "Upper limit can't be less than or equal to lower limit.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new IntegerSettingsVO(lowerLimit, upperLimit);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests if the object is created if the parameters are valid.
     */
    @Test
    void whenParametersAreValid_thenObjectIsCreated(){
        //Arrange
        String lowerLimit = "0";
        String upperLimit = "10";

        //Act
        IntegerSettingsVO integerConfigurationVO = new IntegerSettingsVO(lowerLimit, upperLimit);

        //Assert
        assertNotNull(integerConfigurationVO);
    }

    /**
     * Test if the object is created if the lower limit is negative.
     */
    @Test
    void whenParametersAreValidWithLowerNegative_thenObjectIsCreated(){
        //Arrange
        String lowerLimit = "-10";
        String upperLimit = "10";

        //Act
        IntegerSettingsVO integerConfigurationVO = new IntegerSettingsVO(lowerLimit, upperLimit);

        //Assert
        assertNotNull(integerConfigurationVO);
    }

    /**
     * Test if the object is created if the both limits are negative.
     */
    @Test
    void whenParametersAreValidWithBothValuesNegative_thenObjectIsCreated(){
        //Arrange
        String lowerLimit = "-10";
        String upperLimit = "-1";

        //Act
        IntegerSettingsVO integerConfigurationVO = new IntegerSettingsVO(lowerLimit, upperLimit);

        //Assert
        assertNotNull(integerConfigurationVO);
    }

    /**
     * Tests if when the getValue method is called, the array is created and the lower limits are saved in index 0.
     */
    @Test
    void whenGetValueIsCalled_thenLowerLimitIsSavedInTheArrayFirstPosition() {
        //Arrange
        String lowerLimit = "0";
        String upperLimit = "10";
        IntegerSettingsVO integerConfigurationVO = new IntegerSettingsVO(lowerLimit,upperLimit);
        Integer[] array = integerConfigurationVO.getValue();
        int expected = 0;

        //Act
        int result = array[0];

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests if when the getValue method is called, the array is created and the upper limits are saved in index 1.
     */
    @Test
    void whenGetValueIsCalled_thenUpperLimitIsSavedInTheArraySecondPosition() {
        //Arrange
        String lowerLimit = "0";
        String upperLimit = "10";
        IntegerSettingsVO integerConfigurationVO = new IntegerSettingsVO(lowerLimit,upperLimit);
        Integer[] array = integerConfigurationVO.getValue();
        int expected = 10;

        //Act
        int result = array[1];

        //Assert
        assertEquals(expected,result);
    }
}