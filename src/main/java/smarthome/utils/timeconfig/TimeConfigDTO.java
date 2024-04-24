package smarthome.utils.timeconfig;

public class TimeConfigDTO {
    public final String iDate;
    public final String iTime;
    public final String eDate;
    public final String eTime;
    public String deltaMin;

    /**
     * Constructs a TimeConfigDTO object with the specified parameters.
     *
     * @param iDate     the start date in the format "yyyy-MM-dd"
     * @param iTime     the start time in the format "HH:mm:ss"
     * @param eDate     the end date in the format "yyyy-MM-dd"
     * @param eTime     the end time in the format "HH:mm:ss"
     * @param deltaMin  the time difference in minutes between the start and end times
     */
    public TimeConfigDTO(String iDate, String iTime, String eDate, String eTime, String deltaMin){
        this.iDate = iDate;
        this.iTime = iTime;
        this.eDate = eDate;
        this.eTime = eTime;
        this.deltaMin = deltaMin;
    }

    /**
     * Constructs a TimeConfigDTO object with the specified parameters.
     *
     * @param iDate the start date in the format "yyyy-MM-dd"
     * @param iTime the start time in the format "HH:mm:ss"
     * @param eDate the end date in the format "yyyy-MM-dd"
     * @param eTime the end time in the format "HH:mm:ss"
     */
    public TimeConfigDTO(String iDate, String iTime, String eDate, String eTime){
        this.iDate = iDate;
        this.iTime = iTime;
        this.eDate = eDate;
        this.eTime = eTime;
    }
}
