package SmartHomeDDD.vo.houseVO;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

/**
 * Represents a value object for House ID.
 */
public class HouseIDVO implements DomainID {

    private final UUID identifier;

    /**
     * Constructs a HouseIDVO object with the given identifier.
     *
     * @param identifier The UUID identifier for the house.
     * @throws IllegalArgumentException If the identifier is null.
     */
    public HouseIDVO(UUID identifier){
        if(identifier == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }

    /**
     * Gets the string representation of the house identifier.
     *
     * @return The string representation of the house identifier.
     */
    @Override
    public String getID() {
        return this.identifier.toString();
    }
}
