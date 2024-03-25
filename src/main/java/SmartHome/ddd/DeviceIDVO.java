package SmartHome.ddd;

import java.util.UUID;

/**
 * Represents a value object for Device ID.
 */
public class DeviceIDVO implements DomainID {

    private final UUID deviceID;

    /**
     * Constructs a DeviceIDVO object with the given identifier (ID).
     *
     * @param deviceID The UUID identifier for the device.
     * @throws IllegalArgumentException If the identifier is null.
     */

    public DeviceIDVO (UUID deviceID){
        if(deviceID == null){
            throw new IllegalArgumentException("Invalid Identifier");
        }
        this.deviceID = deviceID;
    }


    /**
     * Gets the string representation of the device identifier (ID).
     *
     * @return The string representation of the device identifier (ID).
     */

    @Override
    public String getID() {
        return this.deviceID.toString();
    }
}
