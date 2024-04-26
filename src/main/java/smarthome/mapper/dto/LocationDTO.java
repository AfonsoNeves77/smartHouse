package smarthome.mapper.dto;

/**
 * Represents a Data Transfer Object (DTO) for a location.
 * This DTO is used to transfer location information between different layers of the application.
 */
public record LocationDTO(String door, String street, String city, String country, String postalCode,
                          double latitude, double longitude) {
}