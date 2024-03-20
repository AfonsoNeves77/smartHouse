package SmartHome.ddd;

import java.util.UUID;

/**
 * Represents a value object for Room ID.
 */
public class RoomIDVO implements DomainID {

    private UUID identifier;

    /**
     * Constructs a RoomIDVO object with the given identifier.
     *
     * @param identifier The UUID identifier for the room.
     * @throws IllegalArgumentException If the identifier is null.
     */
    public RoomIDVO(UUID identifier){
        if(identifier == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }

    /**
     * Gets the string representation of the room identifier.
     *
     * @return The string representation of the room identifier.
     */
    @Override
    public String getID() {
        return this.identifier.toString();
    }
}
