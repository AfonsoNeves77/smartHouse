package SmartHomeDDD.vo.actuatorVO;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

/**
 * Represents a value object for Actuator ID.
 */

public class ActuatorIDVO implements DomainID {

    /**
     * The UUID identifier for the actuator.
     */

    private final UUID identifier;

    /**
     * Constructs an ActuatorIDVO object with the given identifier.
     *
     * @param identifier The UUID identifier for the actuator.
     * @throws IllegalArgumentException If the identifier is null.
     */

    public ActuatorIDVO(UUID identifier) {
        if (identifier == null) {
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }

    /**
     * Gets the string representation of the actuator identifier.
     *
     * @return The string representation of the actuator identifier.
     */

    @Override
    public String getID() {
        return this.identifier.toString();
    }
}
