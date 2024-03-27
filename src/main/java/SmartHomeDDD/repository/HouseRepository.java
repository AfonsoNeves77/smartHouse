package SmartHomeDDD.repository;

import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.vo.houseVO.HouseIDVO;

import java.util.HashMap;
import java.util.Map;

public class HouseRepository implements Repository<HouseIDVO,House>{

    private final Map<HouseIDVO,House> houseIDAndHouseMap = new HashMap<>();


    /**
     * Saves a HouseEntity unto the repository
     * @return False if entity is null or ID is null or already present, True otherwise.
     */
    @Override
    public boolean save(House entity) {
        if(entity == null || entity.getId() == null || isPresent(entity.getId())){
            return false;
        }
        houseIDAndHouseMap.put(entity.getId(),entity);
        return true;
    }


    /**
     * Finds all HouseEntities saved unto the repository;
     * @return Iterable.
     */
    @Override
    public Iterable<House> findAll() {
        return houseIDAndHouseMap.values();
    }


    /**
     * Finds HouseEntity by IDVO
     * @param id IDVO
     * @return HouseEntity matching the inserted IDVO if present, null otherwise.
     */
    @Override
    public House findById(HouseIDVO id) {
        if(isPresent(id)){
            return houseIDAndHouseMap.get(id);
        } else{
            return null;
        }
    }


    /**
     * Verifies if a HouseEntity is present in the repository, queries by ID;
     * @param id IDVO
     * @return True if present, False otherwise.
     */
    @Override
    public boolean isPresent(HouseIDVO id) {
        return houseIDAndHouseMap.containsKey(id);
    }
}
