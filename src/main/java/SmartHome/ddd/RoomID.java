package SmartHome.ddd;

import java.util.UUID;

public class RoomID implements DomainID{

    private UUID identifier;

    public RoomID(UUID identifier){
        if(identifier == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.identifier = identifier;
    }

    @Override
    public String getID() {
        return this.identifier.toString();
    }
}
