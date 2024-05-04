package smarthome.service;

import smarthome.domain.log.Log;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.DeltaVO;
import smarthome.domain.vo.logvo.TimeStampVO;

import java.util.List;

/**
 * Service interface for handling log data.
 */
public interface LogService {
    List<Log> findReadingsFromDeviceInATimePeriod(DeviceIDVO deviceID, TimeStampVO initialTimeStamp, TimeStampVO finalTimeStamp);
    String getMaxInstantaneousTempDifference(DeviceIDVO outdoorDevice, DeviceIDVO indoorDevice, TimeStampVO initialTimeStamp, TimeStampVO finalTimeStamp, DeltaVO deltaMin);
}
