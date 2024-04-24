package smarthome.utils.timeconfig;

import java.time.DateTimeException;
import java.time.LocalDateTime;

import static java.lang.Integer.parseInt;

public class TimeConfig {

    private static final String ERROR = "Invalid timestamps";
    private final LocalDateTime iTimeStamp;
    private final LocalDateTime eTimeStamp;
    private int deltaMin;

    /**
     * Constructs a TimeConfigImpl object with the specified parameters.
     * @param iDate     the start date in the format "yyyy-MM-dd"
     * @param eDate     the end date in the format "yyyy-MM-dd"
     * @param iTime     the start time in the format "HH:mm:ss"
     * @param eTime     the end time in the format "HH:mm:ss"
     * @param deltaMin  the time difference in minutes between the start and end times
     * @throws IllegalArgumentException if any of the parameters are invalid or if the start time is after the end time
     */
    public TimeConfig(String iDate, String iTime, String eDate, String eTime, String deltaMin){
        this.iTimeStamp = parseDateTime(iDate,iTime);
        this.eTimeStamp = parseDateTime(eDate,eTime);
        if (areTimeStampsInvalid(iTimeStamp,eTimeStamp) || parseDelta(deltaMin) == null){
            throw new IllegalArgumentException(ERROR);
        }
        this.deltaMin = parseInt(deltaMin);
    }

    /**
     * Constructs a TimeConfigImpl object with the specified parameters.
     * @param iDate the start date in the format "yyyy-MM-dd"
     * @param eDate the end date in the format "yyyy-MM-dd"
     * @param iTime the start time in the format "HH:mm:ss"
     * @param eTime the end time in the format "HH:mm:ss"
     * @throws IllegalArgumentException if any of the parameters are invalid or if the start time is after the end time
     */
    public TimeConfig(String iDate, String iTime, String eDate, String eTime){
        this.iTimeStamp = parseDateTime(iDate,iTime);
        this.eTimeStamp = parseDateTime(eDate,eTime);
        if (areTimeStampsInvalid(iTimeStamp,eTimeStamp)){
            throw new IllegalArgumentException(ERROR);
        }
    }

    /**
     * Parses a given date and time string into a LocalDateTime object.
     * This method combines the provided date and time strings and attempts to parse them into a LocalDateTime object.
     * The date and time strings should adhere to ISO-8601 format (yyyy-MM-dd for date and HH:mm:ss for time).
     * If parsing is successful, a LocalDateTime object representing the combined date and time is returned.
     * If parsing fails due to invalid input or any other DateTimeException, null is returned.
     * @param date The date string to parse. Should be in the format "yyyy-MM-dd".
     * @param time The time string to parse. Should be in the format "HH:mm:ss".
     * @return A LocalDateTime object representing the combined date and time if parsing is successful, otherwise null.
     * @throws DateTimeException if the date or time string is not in the expected format or if the combination
     *                           of date and time results in an invalid LocalDateTime.
     */
    private LocalDateTime parseDateTime(String date, String time){
        try{
            return LocalDateTime.parse(date+"T"+time);
        } catch (DateTimeException e){
            return null;
        }
    }

    /**
     * Checks if the given timestamps are invalid.
     * This method compares the initial and end LocalDateTime objects to determine if they represent valid timestamps.
     * A timestamp is considered invalid if either the initial or end timestamp is null.
     * Additionally, if the end timestamp is not before the current system time, it is also considered invalid.
     * @param initial The initial timestamp to compare. May be null.
     * @param end The end timestamp to compare. May be null.
     * @return true if either the initial or end timestamp is null, or if the end timestamp is not before the current system time; false otherwise.
     */
    private boolean areTimeStampsInvalid(LocalDateTime initial, LocalDateTime end){
        if (initial == null || end == null){
            return true;
        }
        return initial.isAfter(end) || end.isAfter(LocalDateTime.now());
    }

    /**
     * Parses the given string representing a delta value into a non-negative Integer.
     * This method attempts to parse the provided string representing a delta value into an Integer.
     * If the parsing is successful and the parsed delta value is non-negative, the parsed Integer value is returned.
     * If the provided delta string is null or cannot be parsed into an Integer due to a NumberFormatException,
     * or if the parsed delta value is negative, null is returned.
     * @param delta The string representing the delta value to parse.
     * @return A non-negative Integer representing the parsed delta value if parsing is successful, otherwise null.
     */
    private Integer parseDelta(String delta){
        try {
            int parsedDelta = Integer.parseInt(delta);
            return parsedDelta < 0 ? null : parsedDelta;
        } catch (NumberFormatException e){
            return null;
        }
    }

    public LocalDateTime getInitialTimeStamp() {
        return iTimeStamp;
    }

    public LocalDateTime getEndTimeStamp() {
        return eTimeStamp;
    }

    public int getDeltaMin() {
        return deltaMin;
    }
}

