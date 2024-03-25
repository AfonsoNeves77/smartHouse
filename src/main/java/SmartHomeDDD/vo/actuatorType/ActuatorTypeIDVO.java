package SmartHomeDDD.vo.actuatorType;

import SmartHomeDDD.domain.DomainID;

import java.util.UUID;

public class ActuatorTypeIDVO implements DomainID {

    private final UUID actuatorTypeID;

    public ActuatorTypeIDVO(UUID actuatorTypeID) {
        if (actuatorTypeID == null) throw new IllegalArgumentException("ActuatorTypeID cannot be null");
        this.actuatorTypeID = actuatorTypeID;
    }


    public String getID() {
        return actuatorTypeID.toString();
    }
}