package SmartHomeDDD.services;

import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.domain.house.HouseFactory;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.houseVO.LocationVO;

import java.util.NoSuchElementException;

/**
 * Service class for House. It contains methods to create a new House, update the location of the House and get the first House ID.
 * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
 */

public class HouseService {

    private HouseRepository houseRepository;
    private HouseFactory houseFactory;

    /**
     * Constructor for HouseService class.
     * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
     * It initializes the HouseRepository and HouseFactory objects.
     * @param houseRepository HouseRepository object
     * @param houseFactory HouseFactory object
     */

    public HouseService(HouseRepository houseRepository, HouseFactory houseFactory) {
        if (houseRepository == null || houseFactory == null) {
            throw new IllegalArgumentException("Invalid parameters");
        } else {
            this.houseRepository = houseRepository;
            this.houseFactory = houseFactory;
        }
    }

    /**
     * Method to create a new House object.
     * It throws an IllegalArgumentException if the LocationVO object is null.
     * It calls the createHouse method of the HouseFactory object to create a new House object.
     * @param locationVO LocationVO object
     * @return House object
     */

    public House createNewHouse(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid location");
        } else {
            House house = houseFactory.createHouse(locationVO);
            houseRepository.save(house);
            return house;
        }
    }

    /**
     * Method to update the location of the House.
     * It throws an IllegalArgumentException if the LocationVO object is null.
     * It calls the configureLocation method of the House object to update the location.
     * @param locationVO LocationVO object
     * @return boolean
     */

    public boolean updateLocation(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid location");
        }
        House house = getFirstHouse();
        if (house == null) {
            return false;
        } else {
            return house.configureLocation(locationVO);
        }
    }

    /**
     * Method to get the first House object.
     * It calls the getFirstHouse method of the HouseRepository object to get the first House object.
     * @return House object
     */

    private House getFirstHouse() {
        return houseRepository.getFirstHouse();
    }

    /**
     * Method to get the first House ID.
     * It calls the getFirstHouseIDVO method of the HouseRepository object to get the first House ID.
     * @return HouseIDVO object
     */

    public HouseIDVO getFirstHouseIDVO() {
        HouseIDVO houseIDVO = houseRepository.getFirstHouseIDVO();
        if (houseIDVO == null) {
            throw new NoSuchElementException("No House ID found");
        } else {
            return houseIDVO;
        }
    }
}