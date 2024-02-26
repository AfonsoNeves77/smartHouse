package SmartHome.sensors;

import SmartHome.domain.Catalogue;
import SmartHome.domain.Sensor;
import SmartHome.domain.SensorType;
import SmartHome.domain.Value;

import java.util.Random;

public class GA100K implements Sensor
{
    private final SensorType _sensorType;

    public GA100K(Catalogue catalogue) throws InstantiationException
    {
        // this is a sensor of temperature
        SensorType sensorType = catalogue.getSensorType("Temperature");
        if( sensorType == null )
            throw new InstantiationException("SensorType with description 'Temperature' does not exist.");
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
        int nValue = rand.nextInt(140) - 70; // valor entre -70 e 70

        return new GA100KValue( nValue );
    }
}
