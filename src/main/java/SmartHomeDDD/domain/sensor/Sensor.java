package SmartHomeDDD.domain.sensor;

import SmartHomeDDD.domain.DomainEntity;

public interface Sensor extends DomainEntity {
    /**
     * This Interface has no methods yet. Each implementation of sensor will implement DomainEntity which will
     * offer the ability to getID. The presence of this Interface is fundamentally rooted in the necessity to persist
     * all sensors within a Sensor repository.
     */
}
