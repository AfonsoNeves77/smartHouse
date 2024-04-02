package SmartHomeDDD.domain.actuator;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

public interface Actuator extends DomainEntity {
    ActuatorTypeIDVO getActuatorTypeID();
    DeviceIDVO getDeviceID();
    ActuatorNameVO getActuatorName();
}
