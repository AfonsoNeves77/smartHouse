package SmartHomeDDD.mapper;

import SmartHomeDDD.dto.LocationDTO;
import SmartHomeDDD.vo.houseVO.*;

public class HouseMapper {

    /**
     * Method to convert LocationDTO to LocationVO
     * @param locationDTO
     * @return LocationVO
     */
    public static LocationVO dtoToDomain(LocationDTO locationDTO) {
        return new LocationVO(createAddressVO(locationDTO), createGPSLocationVO(locationDTO));
    }

    /**
     * Method to create AddressVO
     * @param locationDTO
     * @return AddressVO
     */
    private static AddressVO createAddressVO(LocationDTO locationDTO) {
        return new AddressVO(createDoorVO(locationDTO), createStreetVO(locationDTO), createCityVO(locationDTO), createCountryVO(locationDTO), createPostalCodeVO(locationDTO));
    }

    /**
     * Method to create GPSLocationVO
     * @param locationDTO
     * @return GPSLocationVO
     */
    private static GpsVO createGPSLocationVO(LocationDTO locationDTO) {
        return new GpsVO(createLatitudeVO(locationDTO), createLongitudeVO(locationDTO));
    }

    /**
     * Method to create DoorVO
     * @param locationDTO
     * @return DoorVO
     */
    private static DoorVO createDoorVO(LocationDTO locationDTO) {
        return new DoorVO(locationDTO.getDoor());
    }

    /**
     * Method to create StreetVO
     * @param locationDTO
     * @return StreetVO
     */
    private static StreetVO createStreetVO(LocationDTO locationDTO) {
        return new StreetVO(locationDTO.getStreet());
    }

    /**
     * Method to create CityVO
     * @param locationDTO
     * @return CityVO
     */
    private static CityVO createCityVO(LocationDTO locationDTO) {
        return new CityVO(locationDTO.getCity());
    }

    /**
     * Method to create CountryVO
     * @param locationDTO
     * @return CountryVO
     */
    private static CountryVO createCountryVO(LocationDTO locationDTO) {
        return new CountryVO(locationDTO.getCountry());
    }

    /**
     * Method to create PostalCodeVO
     * @param locationDTO
     * @return PostalCodeVO
     */
    private static PostalCodeVO createPostalCodeVO(LocationDTO locationDTO) {
        return new PostalCodeVO(locationDTO.getPostalCode());
    }

    /**
     * Method to create LatitudeVO
     * @param locationDTO
     * @return LatitudeVO
     */
    private static LatitudeVO createLatitudeVO(LocationDTO locationDTO) {
        return new LatitudeVO(locationDTO.getLatitude());
    }

    /**
     * Method to create LongitudeVO
     * @param locationDTO
     * @return LongitudeVO
     */
    private static LongitudeVO createLongitudeVO(LocationDTO locationDTO) {
        return new LongitudeVO(locationDTO.getLongitude());
    }
}