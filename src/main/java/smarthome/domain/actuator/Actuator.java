package smarthome.domain.actuator;

import smarthome.domain.AggregateRoot;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

public interface Actuator extends AggregateRoot {
    ActuatorTypeIDVO getActuatorTypeID();
    DeviceIDVO getDeviceID();
    ActuatorNameVO getActuatorName();

    String getLowerLimit();

    String getUpperLimit();

    String getPrecision();
}
