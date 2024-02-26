package SmartHome.domain;

import java.util.ArrayList;
import java.util.List;

public class House
{
    private Location _location = null;
    private List<Room> _rooms = new ArrayList<>();


    public House( ) { }

    public Location defineLocation( String street, String postalCode) throws InstantiationException
    {
        this._location = new Location( street, postalCode);

        return this._location;
    }

    public Room addRoom(String strName, int nFloor, double dLength, double dWidth, double dHeight) throws InstantiationException
    {
        Room room = new Room(strName, nFloor, dLength, dWidth, dHeight);

        this._rooms.add( room );

        return room;
    }

    public List<Room> getRooms()
    {
        return new ArrayList<>( _rooms );
    }
}
