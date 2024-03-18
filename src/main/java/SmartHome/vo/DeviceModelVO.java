package SmartHome.vo;

public class DeviceModelVO implements ValueObject{

    private String strDeviceModel;

    /**
     * DeviceModel Constructor for the corresponding Value Object
     * @param strDeviceModel Device model as string
     * @throws InstantiationException If parameter is null or blank
     */
    public DeviceModelVO(String strDeviceModel) throws InstantiationException {
        if(strDeviceModel == null || strDeviceModel.isBlank()){
            throw new InstantiationException("Device model cannot be null neither blank.");
        }
        this.strDeviceModel = strDeviceModel;
    }

    @Override
    public String toString() {
        return strDeviceModel;
    }
}