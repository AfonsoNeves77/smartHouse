package smarthome.service;

import org.springframework.stereotype.Service;
import smarthome.domain.house.House;
import smarthome.domain.house.HouseFactory;
import smarthome.domain.vo.housevo.LocationVO;
import smarthome.persistence.HouseRepository;

import java.util.Optional;


/**
 * Service class for House. It contains methods to create a new House, update the location of the House and get the first House ID.
 * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
 */

@Service
public class HouseServiceImpl implements HouseService {

    private final HouseRepository houseRepository;
    private final HouseFactory houseFactory;

    /**
     * Constructor for HouseService class.
     * It throws an IllegalArgumentException if the HouseRepository or HouseFactory objects are null.
     * It initializes the HouseRepository and HouseFactory objects.
     *
     * @param houseRepository HouseRepository object
     * @param houseFactory    HouseFactory object
     */
    public HouseServiceImpl(HouseRepository houseRepository, HouseFactory houseFactory) {
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
     * It calls the save method of the HouseRepository object to save the House object.
     *
     * @param locationVO LocationVO object
     * @return House object
     */
    public House addHouse(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid location");
        }
        House house = houseFactory.createHouse(locationVO);
        houseRepository.save(house);
        return house;
    }

    /**
     * Method to update the location of the House.
     * It throws an IllegalArgumentException if the LocationVO object is null.
     * First, it calls the getFirstHouse method to get the first House object.
     * If the House object is null, it throws an IllegalArgumentException.
     * If the House object is not null, it calls the configureLocation method of the House object to update the location.
     * It calls the update method of the HouseRepository object to update the House object.
     * It returns an Optional object with the updated LocationVO object.
     * It throws an IllegalArgumentException if the update operation is not successful.
     *
     * @param locationVO LocationVO object
     * @return Optional object with the updated LocationVO object or an empty Optional object
     * if the update operation is not successful
     */

    public Optional<LocationVO> updateLocation(LocationVO locationVO) {
        if (locationVO == null) {
            throw new IllegalArgumentException("Invalid location");
        }
        House house = getFirstHouse();
        if (house == null) {
            throw new IllegalArgumentException("House not found");
        } else {
            house.configureLocation(locationVO);
            boolean result = houseRepository.update(house);
            if (result) {
                return Optional.of(house.getLocation());
            } else {
                return Optional.empty();
            }
        }
    }

    /**
     * Method to get the first House object.
     * It calls the getFirstHouse method of the HouseRepository object to get the first House object.
     *
     * @return House object
     */
    private House getFirstHouse() {
        return houseRepository.getFirstHouse();
    }
}