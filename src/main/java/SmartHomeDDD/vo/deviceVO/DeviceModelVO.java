package SmartHomeDDD.vo.deviceVO;

import SmartHomeDDD.vo.ValueObject;

public class DeviceModelVO implements ValueObject<String> {

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

    /**
     * Simple getter method
     * @return Encapsulated value
     */
    @Override
    public String getValue() {
        return this.strDeviceModel;
    }
}
