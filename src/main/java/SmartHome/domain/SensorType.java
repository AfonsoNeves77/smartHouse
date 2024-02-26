package SmartHome.domain;

public class SensorType
{
    private final String _strDescription;
    private final Unit _unit;

    public SensorType( String strDescription, Unit unit ) throws InstantiationException
    {
        if( !isValidConstructorArguments(strDescription) )
            throw( new InstantiationException("Invalid arguments"));

        this._strDescription = strDescription;
        this._unit = unit;
    }

    private boolean isValidConstructorArguments( String strDescription )
    {
        return strDescription != null && !strDescription.isEmpty();

        // implement here the rest of validations
    }

    public String getDescription() {
        return _strDescription;
    }

    public Unit getUnit() {
        return _unit;
    }
}
