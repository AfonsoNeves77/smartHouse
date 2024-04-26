package smarthome.mapper.dto;

/**
 * Represents a Data Transfer Object (DTO) for a sensor type.
 * This DTO is used to transfer sensor type information between different layers of the application.
 */
public record SensorTypeDTO(String sensorTypeID, String unit) {
}