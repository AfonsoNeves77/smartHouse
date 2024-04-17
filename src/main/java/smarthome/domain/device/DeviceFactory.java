package smarthome.domain.device;

import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.roomvo.RoomIDVO;

public interface DeviceFactory {
    Device createDevice(DeviceNameVO deviceName, DeviceModelVO deviceModel, RoomIDVO roomID);
}
