package SmartHome.vo;

import java.util.Objects;

public class AddressVO implements ValueObject {

    private final DoorVO doorVO;
    private final StreetVO streetVO;
    private final CityVO cityVO;
    private final CountryVO countryVO;
    private final PostalCodeVO postalCodeVO;

    public AddressVO(DoorVO doorVO, StreetVO streetVO, CityVO cityVO, CountryVO countryVO, PostalCodeVO postalCodeVO) {
        if (!validArgs(doorVO, streetVO, cityVO, countryVO, postalCodeVO))
            throw new IllegalArgumentException("Invalid arguments.");
        this.doorVO=doorVO;
        this.streetVO=streetVO;
        this.cityVO=cityVO;
        this.countryVO=countryVO;
        this.postalCodeVO=postalCodeVO;
    }

    public String getDoor() {
        return doorVO.getDoor();
    }

    public String getStreet(){
        return streetVO.getStreet();
    }
    public String getCity(){
        return cityVO.getCity();
    }

    public String getCountry(){
        return countryVO.getCountry();
    }

    public String getPostalCode(){
        return postalCodeVO.getPostalCode();
    }

    private boolean validArgs(DoorVO doorVO, StreetVO streetVO, CityVO cityVO, CountryVO countryVO, PostalCodeVO postalCodeVO) {
        if (doorVO == null || streetVO == null || cityVO == null || countryVO == null || postalCodeVO == null) {
            return false;
        } else {
            return true;
        }
    }
}

