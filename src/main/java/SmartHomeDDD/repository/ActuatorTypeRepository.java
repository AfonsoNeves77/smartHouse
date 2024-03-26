package SmartHomeDDD.repository;

import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.repository.Repository;
import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

import java.util.HashMap;
import java.util.Map;

public class ActuatorTypeRepository implements Repository<ActuatorTypeIDVO, ActuatorType> {
    private final Map<ActuatorTypeIDVO, ActuatorType> actuatorTypes;

    public ActuatorTypeRepository() {
        this.actuatorTypes = new HashMap<ActuatorTypeIDVO, ActuatorType>();
    }

    @Override
    public boolean save(ActuatorType entity) {
        if (entity == null || entity.getId() == null || actuatorTypes.containsKey(entity.getId())) {
            return false;
        } else {
            actuatorTypes.put(entity.getId(), entity);
            return true;
        }
    }

    @Override
    public Iterable<ActuatorType> findAll() {
        return actuatorTypes.values();
    }

    @Override
    public ActuatorType findById(ActuatorTypeIDVO id) {
        return actuatorTypes.get(id);
    }

    @Override
    public boolean isPresent(ActuatorTypeIDVO id) {
        if (actuatorTypes.containsKey(id)) {
            return true;
        } else {
            return false;
        }
    }
}
