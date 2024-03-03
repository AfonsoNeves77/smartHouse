package SmartHome.dto;

public class DeviceDTO {
    private String deviceName;
    private String deviceModel;
    private String location;
    private boolean status;


    /**
     * Constructor for Device DTO with all parameters.
     *
     * @param deviceName  Name of the device
     * @param deviceModel Model of the device
     * @param location    Location of the device
     * @param status      Status of the device
     */
    public DeviceDTO(String deviceName, String deviceModel, String location, boolean status) {
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.location = location;
        this.status = status;
    }

    public String getName() {
        return deviceName;
    }

    public String getModel() {
        return deviceModel;
    }

    public String getLocation() {
        return location;
    }

    public boolean getStatus() {
        return status;
    }
}
