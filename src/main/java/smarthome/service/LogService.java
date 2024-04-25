package smarthome.service;

import smarthome.domain.log.Log;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.utils.timeconfig.TimeConfig;

import java.util.List;

/**
 * Service interface for handling log data.
 */
public interface LogService {
    List<Log> findReadingsFromDeviceInATimePeriod(DeviceIDVO deviceIDVO, TimeConfig timeConfig);
    String getMaxInstantaneousTempDifference(DeviceIDVO outdoorDevice, DeviceIDVO indoorDevice, TimeConfig timeConfig);
}
