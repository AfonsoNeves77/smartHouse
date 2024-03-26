package SmartHomeDDD.domain.actuatorType;


import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

public class ActuatorTypeFactory {
    /**
     * Create an ActuatorType object
     *
     * @param actuatorTypeIDVO
     * @return ActuatorType
     */
    public ActuatorType createActuatorType(ActuatorTypeIDVO actuatorTypeIDVO) {
        return new ActuatorType(actuatorTypeIDVO);
    }
}
