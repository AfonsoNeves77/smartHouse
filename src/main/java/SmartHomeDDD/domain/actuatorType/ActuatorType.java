package SmartHomeDDD.domain.actuatorType;

import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

public class ActuatorType implements DomainEntity {

    private final ActuatorTypeIDVO actuatorTypeIDVO;


    /**
     * Constructor for ActuatorType
     *
     * @param actuatorTypeIDVO
     * @return ActuatorType
     * @throws IllegalArgumentException
     */
    public ActuatorType(ActuatorTypeIDVO actuatorTypeIDVO) {
        if (actuatorTypeIDVO == null) throw new IllegalArgumentException("ActuatorType cannot be null");
        this.actuatorTypeIDVO = actuatorTypeIDVO;


    }

    /**
     * Get the actuatorType
     *
     * @return ActuatorType as String
     */
    public String getActuatorType() {
        return actuatorTypeIDVO.toString();
    }

    /**
     * Get the actuatorTypeIDVO
     *
     * @return ActuatorTypeIDVO
     */
    @Override
    public ActuatorTypeIDVO getId() {
        return actuatorTypeIDVO;
    }
}
