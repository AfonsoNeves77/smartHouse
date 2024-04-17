package smarthome.persistence;

import smarthome.domain.device.Device;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.roomvo.RoomIDVO;

import java.util.List;

public interface DeviceRepository extends Repository<DeviceIDVO, Device>{
    List<Device> findByRoomID(RoomIDVO roomID);
}
