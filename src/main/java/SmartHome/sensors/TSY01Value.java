package SmartHome.sensors;

import SmartHome.domain.Value;

public class TSY01Value implements Value
{
    public double _dValue;

    public TSY01Value(int dValue )
    {
        this._dValue = dValue;
    }

    public String toString()
    {
        return this._dValue + "";
    }
}
