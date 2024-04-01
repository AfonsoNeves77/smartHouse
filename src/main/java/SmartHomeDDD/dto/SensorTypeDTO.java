package SmartHomeDDD.dto;

public class SensorTypeDTO {
    private final String sensorTypeID;
    private final String unit;

    /**
     * Constructor for SensorTypeDTO
     *
     * @param sensorTypeID as String
     * @param unit as String
     */
    public SensorTypeDTO(String sensorTypeID, String unit) {
        if (sensorTypeID == null || sensorTypeID.isEmpty())
            throw new IllegalArgumentException("SensorType cannot be null or empty");
        if(unit == null || unit.isEmpty())
            throw new IllegalArgumentException("Unit cannot be null or empty");
        this.sensorTypeID = sensorTypeID;
        this.unit = unit;
    }

    /**
     * Get the sensorTypeID
     *
     * @return sensorTypeID as String
     */
    public String getSensorTypeID() {
        return sensorTypeID;
    }
    /**
     * Get the unit
     *
     * @return unit as String
     */
    public String getUnit() {
        return unit;
    }
}