package smarthome.service;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

import java.util.Optional;

public interface ActuatorService {
    Optional<Actuator> addActuator(ActuatorNameVO actuatorNameVO, ActuatorTypeIDVO actuatorTypeIDVO, DeviceIDVO deviceIDVO, Settings settings);
    Optional<Actuator> getActuatorById (ActuatorIDVO actuatorIDVO);
}
