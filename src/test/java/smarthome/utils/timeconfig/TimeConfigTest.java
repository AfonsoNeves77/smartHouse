package smarthome.utils.timeconfig;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class TimeConfigTest {
    /**
     * Unit test to verify that IllegalArgumentException is thrown when any parameter is null.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when any of the parameters
     * (iDate, eDate, iTime, eTime, deltaMin) passed to the TimeConfig constructor is null. It tests
     * each parameter individually by passing null for one parameter at a time and verifying that
     * IllegalArgumentException is thrown with the expected error message.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenNullParameters_throwsIllegalArgumentException(){
        // Arrange
        String iDate = "2024-04-23";
        String iTime = "16:00";
        String eDate = "2024-04-23";
        String eTime = "18:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(null,eDate,iTime,eTime,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,null,iTime,eTime,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,eDate,null,eTime,deltaMin));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,eDate,iTime,null,deltaMin));
        String result4 = exception4.getMessage();

        Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,eDate,iTime,eTime,null));
        String result5 = exception5.getMessage();

        Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(null,eDate,iTime,eTime));
        String result6 = exception6.getMessage();

        Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,null,iTime,eTime));
        String result7 = exception7.getMessage();

        Exception exception8 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,eDate,null,eTime));
        String result8 = exception8.getMessage();

        Exception exception9 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,eDate,iTime,null));
        String result9 = exception9.getMessage();


        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
        assertEquals(expected,result7);
        assertEquals(expected,result8);
        assertEquals(expected,result9);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the start date (iDate) is invalid.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the start date (iDate)
     * passed to the TimeConfig constructor is invalid. It tests various invalid formats for iDate,
     * such as "23-24-5", "Invalid", "  ", and an empty string. It verifies that IllegalArgumentException
     * is thrown with the expected error message in each case.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenInvalidIDate_throwsIllegalArgumentException(){
        // Arrange
        String iDate1 = "23-24-5";
        String iDate2 = "Invalid";
        String iDate3 = "  ";
        String iDate4 = "";

        String iTime = "16:00";
        String eDate = "2024-04-23";
        String eTime = "18:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate1,iTime,eDate,eTime,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate2,iTime,eDate,eTime,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate3,iTime,eDate,eTime,deltaMin));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate4,iTime,eDate,eTime,deltaMin));
        String result4 = exception4.getMessage();

        Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate1,iTime,eDate,eTime));
        String result5 = exception5.getMessage();

        Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate2,iTime,eDate,eTime));
        String result6 = exception6.getMessage();

        Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate3,iTime,eDate,eTime));
        String result7 = exception7.getMessage();

        Exception exception8 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate4,iTime,eDate,eTime));
        String result8 = exception8.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
        assertEquals(expected,result7);
        assertEquals(expected,result8);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the end date (eDate) is invalid.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the end date (eDate)
     * passed to the TimeConfig constructor is invalid. It tests various invalid formats for eDate,
     * such as "23-24-5", "Invalid", "  ", and an empty string. It verifies that IllegalArgumentException
     * is thrown with the expected error message in each case.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenInvalidEDate_throwsIllegalArgumentException(){
        // Arrange
        String eDate1 = "23-24-5";
        String eDate2 = "Invalid";
        String eDate3 = "  ";
        String eDate4 = "";

        String iDate = "2024-04-23";
        String iTime = "16:00";
        String eTime = "18:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate1,eTime,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate2,eTime,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate3,eTime,deltaMin));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate4,eTime,deltaMin));
        String result4 = exception4.getMessage();

        Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate1,eTime));
        String result5 = exception5.getMessage();

        Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate2,eTime));
        String result6 = exception6.getMessage();

        Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate3,eTime));
        String result7 = exception7.getMessage();

        Exception exception8 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate4,eTime));
        String result8 = exception8.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
        assertEquals(expected,result7);
        assertEquals(expected,result8);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the start time (iTime) is invalid.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the start time (iTime)
     * passed to the TimeConfig constructor is invalid. It tests various invalid formats for iTime,
     * such as "25:00" (invalid hour), "-1" (negative value), " " (blank string), and an empty string.
     * It verifies that IllegalArgumentException is thrown with the expected error message in each case.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenInvalidITime_throwsIllegalArgumentException(){
        // Arrange
        String iDate = "2024-04-23";
        String eDate = "2024-04-23";

        String iTime1 = "25:00";
        String iTime2 = "-1";
        String iTime3 = " ";
        String iTime4 = "";

        String eTime = "18:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime1,eDate,eTime,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime2,eDate,eTime,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime3,eDate,eTime,deltaMin));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime4,eDate,eTime,deltaMin));
        String result4 = exception4.getMessage();

        Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime1,eDate,eTime));
        String result5 = exception5.getMessage();

        Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime2,eDate,eTime));
        String result6 = exception6.getMessage();

        Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime3,eDate,eTime));
        String result7 = exception7.getMessage();

        Exception exception8 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime4,eDate,eTime));
        String result8 = exception8.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
        assertEquals(expected,result7);
        assertEquals(expected,result8);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the end time (eTime) is invalid.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the end time (eTime)
     * passed to the TimeConfig constructor is invalid. It tests various invalid formats for eTime,
     * such as "25:00" (invalid hour), "-1" (negative value), " " (blank string), and an empty string.
     * It verifies that IllegalArgumentException is thrown with the expected error message in each case.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenInvalidETime_throwsIllegalArgumentException(){
        // Arrange
        String iDate = "2024-04-23";
        String eDate = "2024-04-23";
        String iTime = "18:00";

        String eTime1 = "25:00";
        String eTime2 = "-1";
        String eTime3 = " ";
        String eTime4 = "";

        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime1,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime2,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime3,deltaMin));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime4,deltaMin));
        String result4 = exception4.getMessage();

        Exception exception5 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime1,deltaMin));
        String result5 = exception5.getMessage();

        Exception exception6 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime2,deltaMin));
        String result6 = exception6.getMessage();

        Exception exception7 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime3,deltaMin));
        String result7 = exception7.getMessage();

        Exception exception8 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime4,deltaMin));
        String result8 = exception8.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
        assertEquals(expected,result5);
        assertEquals(expected,result6);
        assertEquals(expected,result7);
        assertEquals(expected,result8);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the start timestamps are after the end timestamps.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the start timestamps (iDate, iTime)
     * are after the end timestamps (eDate, eTime) passed to the TimeConfig constructor. It tests two scenarios:
     * 1. The start date (iDate) is after the end date (eDate).
     * 2. The start time (iTime) is after the end time (eTime) on the same date.
     * It verifies that IllegalArgumentException is thrown with the expected error message in each case.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenIStampsAboveEStamps_throwsIllegalArgumentException(){
        // Arrange
        String iDate1 = "2024-05-23";
        String iTime1 = "18:00";
        String eDate1 = "2024-04-23";
        String eTime1 = "19:00";

        String iDate2 = "2024-05-23";
        String iTime2 = "18:00";
        String eDate2 = "2024-04-23";
        String eTime2 = "17:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate1,iTime1,eDate1,eTime1,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate2,iTime2,eDate2,eTime2,deltaMin));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate1,iTime1,eDate1,eTime1));
        String result3 = exception3.getMessage();

        Exception exception4 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate2,iTime2,eDate2,eTime2));
        String result4 = exception4.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
        assertEquals(expected,result4);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the deltaMin parameter is negative.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the deltaMin parameter
     * passed to the TimeConfig constructor is negative. It tests the scenario where deltaMin is "-60".
     * It verifies that IllegalArgumentException is thrown with the expected error message.
     */
    @Test
    void whenGivenInvalidDeltaMin_throwsIllegalArgumentException(){
        // Arrange
        String iDate = "2024-03-23";
        String iTime = "18:00";
        String eDate = "2024-03-23";
        String eTime = "19:00";
        String deltaMin1 = "-60";
        String deltaMin2 = "Error";
        String deltaMin3 = " ";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime,deltaMin1));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime,deltaMin2));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime,deltaMin3));
        String result3 = exception3.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
    }

    /**
     * Unit test to verify that IllegalArgumentException is thrown when the time frame extends beyond the current local time.
     * <p>
     * This test method verifies that an IllegalArgumentException is thrown when the time frame (iDate, iTime, eDate, eTime)
     * passed to the TimeConfig constructor extends beyond the current local time. It tests the scenario where the end date (eDate)
     * and end time (eTime) are both after the current local time. It verifies that IllegalArgumentException is thrown
     * with the expected error message.
     * Both constructors are being tested.
     */
    @Test
    void whenGivenTimeFrameAboveLocalTime_throwsIllegalArgumentException(){
        // Arrange
        String iDate = "2023-04-23";
        String iTime = "18:00";
        String eDate = "2025-05-23";
        String eTime = "19:00";
        String deltaMin = "60";

        String expected = "Invalid timestamps";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime,deltaMin));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, () ->
                new TimeConfig(iDate,iTime,eDate,eTime));
        String result2 = exception2.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
    }

    /**
     * Unit test to verify that a TimeConfig object is created successfully and its attributes are accessible with valid parameters.
     * <p>
     * This test method verifies that a TimeConfig object is created successfully when valid parameters (iDate, eDate, iTime, eTime, deltaMin)
     * are provided to the constructor. It then checks that the attributes of the created object (iTimeStamp, eTimeStamp, deltaMinute)
     * are accessible and have the expected values. The test ensures that the iTimeStamp and eTimeStamp are correctly parsed
     * from the provided date and time strings, and that deltaMinute has the correct value.
     */
    @Test
    void whenGivenValidParameters_objectIsCreatedAndAttributesAreAccessible(){
        // Arrange
        String iDate = "2024-04-23";
        String iTime = "16:00";
        String eDate = "2024-04-23";
        String eTime = "18:00";
        String deltaMin = "0";

        LocalDateTime iExpected = LocalDateTime.parse("2024-04-23T16:00");
        LocalDateTime eExpected = LocalDateTime.parse("2024-04-23T18:00");

        TimeConfig config = new TimeConfig(iDate,iTime,eDate,eTime,deltaMin);

        // Act
        LocalDateTime iTimeStamp = config.getInitialTimeStamp();
        LocalDateTime eTimeStamp = config.getEndTimeStamp();
        int deltaMinute = config.getDeltaMin();

        // Assert
        assertEquals(iExpected,iTimeStamp);
        assertEquals(eExpected,eTimeStamp);
        assertEquals(0,deltaMinute);
    }

    /**
     * Unit test to verify that a TimeConfig object is created successfully and its attributes are accessible with valid parameters, excluding deltaMin.
     * <p>
     * This test method verifies that a TimeConfig object is created successfully when valid parameters (iDate, eDate, iTime, eTime)
     * are provided to the constructor, excluding deltaMin. It then checks that the attributes of the created object (iTimeStamp, eTimeStamp, deltaMinute)
     * are accessible and have the expected values. The test ensures that the iTimeStamp and eTimeStamp are correctly parsed
     * from the provided date and time strings, and that deltaMinute has the default value of 0.
     */
    @Test
    void whenGivenValidParametersExcludingDelta_objectIsCreatedAndAttributesAreAccessible(){
        // Arrange
        String iDate = "2024-04-23";
        String iTime = "16:00";
        String eDate = "2024-04-23";
        String eTime = "18:00";

        LocalDateTime iExpected = LocalDateTime.parse("2024-04-23T16:00");
        LocalDateTime eExpected = LocalDateTime.parse("2024-04-23T18:00");

        TimeConfig config = new TimeConfig(iDate,iTime,eDate,eTime);

        // Act
        LocalDateTime iTimeStamp = config.getInitialTimeStamp();
        LocalDateTime eTimeStamp = config.getEndTimeStamp();
        int deltaMinute = config.getDeltaMin();

        // Assert
        assertEquals(iExpected,iTimeStamp);
        assertEquals(eExpected,eTimeStamp);
        assertEquals(0,deltaMinute);
    }
}
