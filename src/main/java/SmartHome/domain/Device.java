package SmartHome.domain;

import java.util.ArrayList;
import java.util.List;

public class Device
{
    private final String _strName;

    private List<Sensor> _sensors = new ArrayList<>();

    public Device( String strName ) throws InstantiationException
    {
        if( !isValidConstructorArguments(strName) )
            throw( new InstantiationException("Invalid arguments"));

        this._strName = strName;
    }

    private boolean isValidConstructorArguments( String strName )
    {
        return strName != null && !strName.isEmpty();

        // implement here the rest of validations
    }

    public Sensor addSensor( String strModel, Catalogue catalogue ) {
        Sensor sensor = catalogue.getSensor( strModel );
        if( sensor == null )
            return null;

        this._sensors.add( sensor );
        return sensor;
    }
}
