package smarthome.persistence;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;

public interface ActuatorRepository extends Repository<ActuatorIDVO, Actuator>{
}
