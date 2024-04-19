package smarthome.domain.actuator;

import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

public interface ActuatorFactory {
    Actuator createActuator(ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID, Settings settings);

    Actuator createActuator(ActuatorIDVO actuatorIDVO, ActuatorNameVO actuatorName, ActuatorTypeIDVO actuatorTypeID, DeviceIDVO deviceID, Settings settings);
}
