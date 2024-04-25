package smarthome.utils.timeconfig;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TimeConfigAssemblerTest {

    /**
     * Tests the scenario when the DTO is null, expecting an IllegalArgumentException to be thrown.
     * This test method verifies that calling the createTimeConfig method of TimeConfigAssembler with a null DTO
     * results in an IllegalArgumentException being thrown, as expected.
     * It arranges the expected error message.
     * Then, it invokes the createTimeConfig method with a null parameter within an assertThrows block to capture the exception.
     * Finally, it verifies that the thrown exception is of type IllegalArgumentException and that its message matches the expected one.
     */
    @Test
    void whenDTONull_throwsIllegalArgumentException(){
        // Arrange
        String expected = "Invalid DTO";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                TimeConfigAssembler.createTimeConfig(null));
        String result = exception.getMessage();
        // Assert
        assertEquals(expected,result);
    }

    /**
     * Tests the scenario when given full parameters, calls appropriate constructor, and the object has accessible attributes.
     * This test method verifies that a TimeConfig object created using a TimeConfigDTO with initial and end dates, times, and delta
     * correctly initializes its attributes and makes them accessible through getter methods.
     * It arranges the necessary input parameters including initial date, end date, initial time, end time, and delta in minutes.
     * Then, it creates a TimeConfigDTO object using these parameters and constructs a TimeConfig object using a factory method
     * (TimeConfigAssembler.createTimeConfig) with the DTO.
     * After that, it retrieves the initial timestamp, end timestamp, and delta value from the created TimeConfig object using getter methods.
     * Finally, it asserts that the retrieved values match the expected values based on the input parameters.
     */
    @Test
    void whenGivenFullParameters_callsAppropriateConstructor_objectHasAccessibleAttributes(){
        // Arrange
        String iDate = "2024-04-23";
        String iTime = "18:00";
        String eDate = "2024-04-23";
        String eTime = "23:00";
        String deltaMin = "60";

        String expectedIStamp = iDate + "T" + iTime;
        String expectedEStamp = eDate + "T" + eTime;
        int expected = 60;

        TimeConfigDTO dto = new TimeConfigDTO(iDate,iTime,eDate,eTime,deltaMin);

        TimeConfig config = TimeConfigAssembler.createTimeConfig(dto);

        // Act
        LocalDateTime iStamp = config.getInitialTimeStamp();
        LocalDateTime eStamp = config.getEndTimeStamp();
        int delta = config.getDeltaMin();

        // Assert
        assertEquals(expectedIStamp,iStamp.toString());
        assertEquals(expectedEStamp,eStamp.toString());
        assertEquals(expected,delta);
    }

    /**
     * Tests the scenario when given parameters excluding delta, calls appropriate constructor, and the object has accessible attributes.
     * This test method verifies that a TimeConfig object created using a TimeConfigDTO with initial and end dates and times
     * (excluding delta) correctly initializes its attributes and makes them accessible through getter methods.
     * It arranges the necessary input parameters including initial date, end date, initial time, and end time.
     * Then, it creates a TimeConfigDTO object using these parameters and constructs a TimeConfig object using a factory method
     * (TimeConfigAssembler.createTimeConfig) with the DTO.
     * After that, it retrieves the initial and end timestamps from the created TimeConfig object using getter methods.
     * Finally, it asserts that the retrieved timestamps match the expected values based on the input parameters.
     */
    @Test
    void whenGivenParametersExcludingDelta_callsAppropriateConstructor_objectHasAccessibleAttributes(){
        // Arrange
        String iDate = "2024-04-23";
        String eDate = "2024-04-23";
        String iTime = "18:00";
        String eTime = "23:00";

        String expectedIStamp = iDate + "T" + iTime;
        String expectedEStamp = eDate + "T" + eTime;

        TimeConfigDTO dto = new TimeConfigDTO(iDate,iTime,eDate,eTime);

        TimeConfig config = TimeConfigAssembler.createTimeConfig(dto);

        // Act
        LocalDateTime iStamp = config.getInitialTimeStamp();
        LocalDateTime eStamp = config.getEndTimeStamp();

        // Assert
        assertEquals(expectedIStamp,iStamp.toString());
        assertEquals(expectedEStamp,eStamp.toString());
    }
}
