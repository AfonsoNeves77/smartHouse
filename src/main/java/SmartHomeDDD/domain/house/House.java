package SmartHomeDDD.domain.house;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.houseVO.LocationVO;

import java.util.UUID;

public class House  implements DomainEntity {
    private final HouseIDVO houseID;
    private LocationVO location;

    /**
     * Constructor for HouseEntity.
     * @param location The location of the house.
     */
    public House(LocationVO location) {
        if (!validateLocation(location)) {
            throw new IllegalArgumentException("Invalid House Entity parameters.");
        }
        this.houseID = new HouseIDVO(UUID.randomUUID());
        this.location = location;
    }

    /**
     * Configures the location of the house.
     * @param location The location of the house.
     * @return True if the location is valid and was configured, false otherwise.
     */
    public boolean configureLocation(LocationVO location) {
        if (!validateLocation(location)) {
            return false;
        }
        this.location = location;
        return true;
    }

    /**
     * Validates the location of the house.
     * @param location The location of the house.
     * @return True if the location is valid, false otherwise.
     */
    private boolean validateLocation(LocationVO location) {
        return location != null;
    }

    public LocationVO getLocation() {
        return this.location;
    }

    @Override
    public HouseIDVO getId() {
        return this.houseID;
    }
}