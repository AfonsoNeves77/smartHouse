package SmartHome.sensors;

import SmartHome.domain.Catalogue;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class TSY01 implements Sensor
{
    private final SensorType _sensorType;

    public TSY01(Catalogue catalogue) throws InstantiationException
    {
        SensorType sensorType = catalogue.getSensorType("Humidity");
        if( sensorType == null )
            throw new InstantiationException("SensorType with description 'Humidity' does not exist.");
        else
            this._sensorType = sensorType;
    }

    public SensorType getSensorType()
    {
        return this._sensorType;
    }

    public Value getValue()
    {
        Random rand = new Random();
        int nValue = rand.nextInt(100); // valor entre 0 e 100

        return new TSY01Value( nValue );
    }
}
