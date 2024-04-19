package smarthome.domain.log;

import smarthome.domain.sensor.values.SensorValueObject;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.LogIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;

import java.time.LocalDateTime;

public interface LogFactory {
    Log createLog(SensorValueObject<?> reading, SensorIDVO sensorID, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID);

    Log createLog(LogIDVO logID, LocalDateTime time, SensorValueObject<?> reading, SensorIDVO sensorID, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID);

}
