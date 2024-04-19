package smarthome.domain.vo.logvo;

import smarthome.domain.DomainID;

import java.util.UUID;

public class LogIDVO implements DomainID {

    private final UUID identifier;

    /**
     * Constructs a LogIDVO object with the given identifier.
     *
     * @param identifier The UUID identifier for the log.
     * @throws IllegalArgumentException If the identifier is null.
     */
    public LogIDVO(UUID identifier){
        if(identifier == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }

    /**
     * Gets the string representation of the log identifier.
     * @return The string representation of the log identifier.
     */
    @Override
    public String getID() {
        return this.identifier.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LogIDVO logIDVO)) return false;
        return identifier.equals(logIDVO.identifier);
    }

    @Override
    public int hashCode() {
        return identifier.hashCode();
    }
}
