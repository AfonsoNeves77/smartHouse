package SmartHomeDDD.domain.actuator;

import SmartHomeDDD.domain.DomainEntity;

public interface Actuator extends DomainEntity {
    /**
     * This Interface has no methods yet. Each implementation of actuator will implement DomainEntity which will
     * offer the ability to getID. The presence of this Interface is fundamentally rooted in the necessity to persist
     * all actuators within an Actuator repository.
     */
}
