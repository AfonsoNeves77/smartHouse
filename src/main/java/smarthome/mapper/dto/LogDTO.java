package smarthome.mapper.dto;

/**
 * A data transfer object (DTO) representing a log entry.
 * <p>
 * This record class encapsulates the attributes of a log entry, including its unique identifier,
 * timestamp, reading value, sensor identifier, device identifier, and sensor type identifier.
 * Each attribute is represented by a private final field implicitly declared by the record.
 * <p>
 * Records provide a concise and immutable way to declare classes whose main purpose is to store data.
 * They automatically generate a constructor, accessor methods, a toString() method, and equals() and hashCode() methods,
 * making them suitable for use as data-holding objects.
 */

public record LogDTO(String logID, String localDateTime, String reading, String sensorID, String deviceID,
                     String sensorTypeID) {
}
