package smarthome.mapper;

import smarthome.domain.vo.housevo.*;
import smarthome.mapper.dto.LocationDTO;

public class HouseMapper {

    /**
     * Method to convert LocationDTO to LocationVO
     * @param locationDTO LocationDTO object
     * @return LocationVO
     */
    public static LocationVO dtoToDomain(LocationDTO locationDTO) {
        if (!validateLocationDTO(locationDTO)) {
            throw new IllegalArgumentException("LocationDTO is null");
        }
        return new LocationVO(createAddressVO(locationDTO), createGPSLocationVO(locationDTO));
    }

    /**
     * Method to create AddressVO
     * @param locationDTO LocationDTO object
     * @return AddressVO
     */
    private static AddressVO createAddressVO(LocationDTO locationDTO) {
        return new AddressVO(createDoorVO(locationDTO), createStreetVO(locationDTO), createCityVO(locationDTO), createCountryVO(locationDTO), createPostalCodeVO(locationDTO));
    }

    /**
     * Method to create GPSLocationVO
     * @param locationDTO LocationDTO object
     * @return GPSLocationVO
     */
    private static GpsVO createGPSLocationVO(LocationDTO locationDTO) {
        return new GpsVO(createLatitudeVO(locationDTO), createLongitudeVO(locationDTO));
    }

    /**
     * Method to create DoorVO
     * @param locationDTO LocationDTO object
     * @return DoorVO
     */
    private static DoorVO createDoorVO(LocationDTO locationDTO) {
        return new DoorVO(locationDTO.door());
    }

    /**
     * Method to create StreetVO
     * @param locationDTO LocationDTO object
     * @return StreetVO
     */
    private static StreetVO createStreetVO(LocationDTO locationDTO) {
        return new StreetVO(locationDTO.street());
    }

    /**
     * Method to create CityVO
     * @param locationDTO LocationDTO object
     * @return CityVO
     */
    private static CityVO createCityVO(LocationDTO locationDTO) {
        return new CityVO(locationDTO.city());
    }

    /**
     * Method to create CountryVO
     * @param locationDTO LocationDTO object
     * @return CountryVO
     */
    private static CountryVO createCountryVO(LocationDTO locationDTO) {
        return new CountryVO(locationDTO.country());
    }

    /**
     * Method to create PostalCodeVO
     * @param locationDTO LocationDTO object
     * @return PostalCodeVO
     */
    private static PostalCodeVO createPostalCodeVO(LocationDTO locationDTO) {
        return new PostalCodeVO(locationDTO.postalCode());
    }

    /**
     * Method to create LatitudeVO
     * @param locationDTO LocationDTO object
     * @return LatitudeVO
     */
    private static LatitudeVO createLatitudeVO(LocationDTO locationDTO) {
        return new LatitudeVO(locationDTO.latitude());
    }

    /**
     * Method to create LongitudeVO
     * @param locationDTO LocationDTO object
     * @return LongitudeVO
     */
    private static LongitudeVO createLongitudeVO(LocationDTO locationDTO) {
        return new LongitudeVO(locationDTO.longitude());
    }

    /**
     *  Method to validate LocationDTO before converting it to LocationVO.
     * @param locationDTO LocationDTO object
     * @return True or false
     */
    private static boolean validateLocationDTO(LocationDTO locationDTO) {
        return locationDTO != null;
    }
}