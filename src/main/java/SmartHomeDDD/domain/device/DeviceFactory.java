package SmartHomeDDD.domain.device;

import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

public class DeviceFactory {

    /**
     * Creates a Device with the specified parameters, that are all Value-Objects.
     * @param deviceName The name of the device.
     * @param deviceModel The model of the device.
     * @param roomID The ID of the room to which the device belongs.
     * @return  The Device created.
     */
    public Device createDevice(DeviceNameVO deviceName, DeviceModelVO deviceModel, RoomIDVO roomID){
        return new Device(deviceName,deviceModel,roomID);
    }
}
