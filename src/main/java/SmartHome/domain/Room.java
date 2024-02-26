package SmartHome.domain;

import java.util.List;
import java.util.ArrayList;

public class Room
{
    private final String _strName;
    private final int _nFloor;
    private final double _dLength;
    private final double _dWidth;
    private final double _dHeight;

    private List<Device> _listDevices = new ArrayList<>();

    public Room(String strName, int nFloor, double dLenght, double dWidth, double dHeight) throws InstantiationException
    {
        if( !isValidConstructorArguments(strName, nFloor, dLenght, dWidth, dHeight) )
            throw( new InstantiationException("Invalid arguments"));

        this._strName = strName;
        this._nFloor = nFloor;
        this._dLength = dLenght;
        this._dWidth = dWidth;
        this._dHeight = dHeight;
    }

    private boolean isValidConstructorArguments( String strName, int nFloor, double dLength, double dWidth, double dHeight )
    {
        if( strName==null || strName.isEmpty() )
            return false;
        if( dLength<=0 || dWidth<=0 || dHeight<=0  )
            return false;

        // implement here the rest of validations

        return true;
    }

    public Device addDevice( String strName ) throws InstantiationException
    {
        Device device = new Device( strName );

        this._listDevices.add( device );

        return device;
    }

    public List<Device> getDevices()
    {
        return new ArrayList<>( this._listDevices );
    }

    public String getName()
    {
        return this._strName;
    }

    public double getLength()
    {
        return this._dLength;
    }

    public double getWidth()
    {
        return this._dWidth;
    }

    public double getHeight()
    {
        return this._dHeight;
    }

    public int getFloor()
    {
        return this._nFloor;
    }
}
