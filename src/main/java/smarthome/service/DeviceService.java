package smarthome.service;

import smarthome.domain.device.Device;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.roomvo.RoomIDVO;

import java.util.List;

public interface DeviceService {
    boolean addDevice(DeviceNameVO deviceNameVO, DeviceModelVO deviceModelVO, RoomIDVO roomIDVO);
    boolean deactivateDevice(DeviceIDVO deviceIDVO);
    List<Device> getListOfDevicesInARoom(RoomIDVO roomIDVO);
}
