package smarthome.domain.actuator;

import smarthome.domain.DomainEntity;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

public interface Actuator extends DomainEntity {
    ActuatorTypeIDVO getActuatorTypeID();
    DeviceIDVO getDeviceID();
    ActuatorNameVO getActuatorName();
}
