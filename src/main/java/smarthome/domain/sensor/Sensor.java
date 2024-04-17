package smarthome.domain.sensor;

import smarthome.domain.DomainEntity;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;

public interface Sensor extends DomainEntity {
    SensorTypeIDVO getSensorTypeID();
    DeviceIDVO getDeviceID();
    SensorNameVO getSensorName();
}
