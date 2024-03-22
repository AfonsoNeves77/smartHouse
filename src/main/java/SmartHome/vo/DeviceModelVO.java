package SmartHome.vo;

public class DeviceModelVO implements ValueObject{

    private final String strDeviceModel;

    /**
     * DeviceModel Constructor for the corresponding Value Object
     * @param strDeviceModel Device model as string
     * @throws InstantiationException If parameter is null or blank
     */
    public DeviceModelVO(String strDeviceModel) {
        if(strDeviceModel == null || strDeviceModel.isBlank()){
            throw new IllegalArgumentException("Device model cannot be null neither blank.");
        }
        this.strDeviceModel = strDeviceModel;
    }

    @Override
    public String toString() {
        return strDeviceModel;
    }
}
