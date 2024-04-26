package smarthome.mapper.dto;

/**
 * Represents a Data Transfer Object (DTO) for a room.
 * This DTO is used to transfer room information between different layers of the application.
 */
public record RoomDTO(String id, String roomName, int floor, double roomHeight,
                      double roomLength, double roomWidth, String houseID) {
}