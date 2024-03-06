package SmartHome.domain.sensor.externalServices;

import org.shredzone.commons.suncalc.SunTimes;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

public class SunTimeCalculator implements ExternalServices {

    public ZonedDateTime computeSunsetTime(String date, String gpsCoordinates){
        SunTimes sunsetTime;
        try{
            sunsetTime = compute(date,gpsCoordinates);
            return sunsetTime.getSet();
        } catch (IllegalArgumentException e){
            return null;
        }
    }

    private SunTimes compute(String date, String gpsCoordinates){
        double[] coordinatesArray = convertCoordinates(gpsCoordinates);
        if(coordinatesArray.length == 0){
            throw new IllegalArgumentException("Invalid Coordinates");
        }
        double latitude = coordinatesArray[0];
        double longitude = coordinatesArray[1];
        try{
            LocalDate localDate = parseLocalDate(date);
            Date toComputeDate = convertToDate(localDate);
            return SunTimes.compute()
                    .on(toComputeDate)
                    .at(latitude, longitude)
                    .execute();
        } catch (NullPointerException | IllegalArgumentException | DateTimeException exception){
            throw new IllegalArgumentException("Operation Failed");
        }
    }

    private double[] convertCoordinates(String gpsCoordinates){
        double latitude;
        double longitude;

        try {
            String[] parts = gpsCoordinates.split(":");
            latitude = Double.parseDouble(parts[0]);
            longitude = Double.parseDouble(parts[1]);

        }catch (NumberFormatException | PatternSyntaxException | NullPointerException exception){
            return new double[0];
        }
        return new double[] {latitude,longitude};
    }

    private  LocalDate parseLocalDate(String dateString) {
        return LocalDate.parse(dateString);
    }

    private Date convertToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
