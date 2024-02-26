package SmartHome.sensors;

import SmartHome.domain.Value;

public class GA100KValue implements Value
{
    public int _nValue;

    public GA100KValue(int nValue )
    {
        this._nValue = nValue;
    }

    public String toString()
    {
        return this._nValue + "";
    }
}
