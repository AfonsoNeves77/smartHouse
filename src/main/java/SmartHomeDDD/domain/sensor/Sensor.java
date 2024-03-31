package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

public interface Sensor extends DomainEntity {
    SensorTypeIDVO getSensorTypeID();
    DeviceIDVO getDeviceID();
    SensorNameVO getSensorName();
}
