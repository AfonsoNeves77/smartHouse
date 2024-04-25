package smarthome.service;

import smarthome.domain.log.Log;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.utils.timeconfig.TimeConfig;

import java.util.List;

public interface LogService {
    List<Log> findReadingsFromDeviceInATimePeriod(DeviceIDVO deviceIDVO, TimeConfig timeConfig);
}
