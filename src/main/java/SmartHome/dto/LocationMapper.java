package SmartHome.dto;

import SmartHome.domain.Location;

public class LocationMapper
{
    static public LocationDTO Domain2DTO( Location location )
    {
        return new LocationDTO( location );
    }
}
