package SmartHomeDDD.dto;

public class DecimalSettingsDTO {
    private String lowerLimit;
    private String upperLimit;
    private String precision;

    /**
     * Constructor for DecimalSettingsDTO.
     * Use scenario: to receive external information when actuator configurations are received and to enable the
     * conversion of a DecimalSettingsVO to a DTO.
     * @param lowerLimit Required lower limit
     * @param upperLimit Required upper limit
     * @param precision Required precision
     */
    public DecimalSettingsDTO(String lowerLimit, String upperLimit, String precision) {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        this.precision = precision;
    }

    /**
     * Simple getter method to retrieve the lower limit.
     * @return Lower limit in String format.
     */
    public String getLowerLimit() {
        return lowerLimit;
    }

    /**
     * Simple getter method to retrieve the upper limit.
     * @return Upper limit in String format.
     */
    public String getUpperLimit() {
        return upperLimit;
    }

    /**
     * Simple getter method to retrieve the precision.
     * @return Precision in String format.
     */
    public String getPrecision() {
        return precision;
    }
}
