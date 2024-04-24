package smarthome.utils.timeconfig;

public class TimeConfigAssembler {

    private TimeConfigAssembler(){
        // Private constructor to prevent instantiation of this utility class
    }

    /**
     * Creates a new TimeConfigImpl object based on the provided TimeConfigDTO.
     * <p>
     * This method creates a TimeConfigImpl object using the parameters provided
     * in the TimeConfigDTO. If the TimeConfigDTO is null or any of its fields
     * (iDate, eDate, iTime, eTime) are null, an IllegalArgumentException is thrown.
     * If the deltaMin field of the TimeConfigDTO is not null and not empty, it is
     * passed to the TimeConfigImpl constructor to create a TimeConfigImpl object
     * with the specified deltaMin value. Otherwise, a TimeConfigImpl object is created
     * without the deltaMin parameter.
     *
     * @param timeConfigDTO the TimeConfigDTO object containing the configuration details
     * @return a new TimeConfigImpl object initialized with the parameters from the provided TimeConfigDTO
     * @throws IllegalArgumentException if the provided TimeConfigDTO is null or if any of its parameters are null
     */
    public static TimeConfig createTimeConfig(TimeConfigDTO timeConfigDTO){
        if (timeConfigDTO == null){
            throw new IllegalArgumentException("Invalid DTO");
        }
        String iDate = timeConfigDTO.iDate;
        String eDate = timeConfigDTO.eDate;
        String iTime = timeConfigDTO.iTime;
        String eTime = timeConfigDTO.eTime;
        String deltaMin = timeConfigDTO.deltaMin;

        return deltaMin != null ?
                new TimeConfig(iDate, eDate, iTime, eTime, deltaMin) :
                new TimeConfig(iDate, eDate, iTime, eTime);
    }
}
