package SmartHome.repository;

import SmartHome.ddd.DomainID;
import SmartHome.ddd.DomainEntity;

public interface Repository{

    /**
     * Saves a domainEntity unto the repository implementation
     * @param domainEntity Any Domain Entity that is being handled by an implementation of a repository
     * @return True or False
     */
    boolean save(DomainEntity domainEntity);

    /**
     * Finds all entities saved unto the repository;
     * @return Iterable.
     */
    Iterable<DomainEntity> findAll();

    /**
     * Finds Entity by IDVO
     * @param id IDVO
     * @return Entity matching the inserted IDVO
     */
    DomainEntity findById(DomainID id);

    /**
     * Verifies if an entity is present in the repository, queries by ID;
     * @param id IDVO
     * @return True or False
     */
    boolean isPresent(DomainID id);
}
