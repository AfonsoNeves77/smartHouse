package smarthome.domain.actuatortype;


import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;

public class ActuatorTypeFactoryImpl implements ActuatorTypeFactory{
    /**
     * Create an ActuatorType object
     *
     * @param actuatorTypeIDVO ActuatorTypeIDVO object
     * @return ActuatorType
     */
    public ActuatorType createActuatorType(ActuatorTypeIDVO actuatorTypeIDVO) {
        return new ActuatorType(actuatorTypeIDVO);
    }
}
