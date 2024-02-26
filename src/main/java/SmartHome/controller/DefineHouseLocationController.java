package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.Location;

import SmartHome.dto.LocationDTO;
import SmartHome.dto.LocationMapper;

public class DefineHouseLocationController {

    private final House _house;

    public DefineHouseLocationController( House house ) {
        _house = house;
    }

    public LocationDTO defineHouseLocation( String strStreet, String strPostalCode ) throws InstantiationException {
        Location location = _house.defineLocation(strStreet, strPostalCode);

        LocationDTO locationDTO = LocationMapper.Domain2DTO( location );
        return locationDTO;
    }
}
