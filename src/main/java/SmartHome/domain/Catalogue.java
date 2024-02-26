package SmartHome.domain;

import java.io.File;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;


public class Catalogue
{
    private List<SensorType> _listSensorTypes = new ArrayList<>();
    private List<String> _listStringClassesSensors;

    public Catalogue( Configuration config )
    {
        // access configuration properties
        String [] arrayStringClassesSensors = config.getStringArray("sensor");
        this._listStringClassesSensors = List.of(arrayStringClassesSensors);
    }

    public Catalogue( String filePathname ) throws InstantiationException
    {
        Configurations configs = new Configurations();
        try
        {
            Configuration config = configs.properties(new File(filePathname)); // e.g. filePathname = "config.properties"

            // access configuration properties
            String [] arrayStringClassesSensors = config.getStringArray("sensor");
            this._listStringClassesSensors = List.of(arrayStringClassesSensors);
        }
        catch (ConfigurationException exception)
        {
            // Something went wrong
            throw new InstantiationException("something went wrong in reading the configuration: " + exception.getMessage());
        }
    }

    public SensorType addSensorType(String strDescription, Unit unit) throws InstantiationException
    {
        SensorType _sensor = new SensorType(strDescription, unit);

        this._listSensorTypes.add(_sensor);

        return _sensor;
    }

    public SensorType getSensorType( String strDescription )
    {
        Optional<SensorType> optSensorType = this._listSensorTypes.stream().filter(s -> s.getDescription().equals(strDescription) ).findFirst();

        return optSensorType.orElse(null);
    }

    public List<String> getSensorModels()
    {
        return new ArrayList<>(this._listStringClassesSensors);
    }

    public Sensor getSensor( String strModel )
    {
        Optional<String> optSensorType = this.getSensorModels().stream().filter(s -> s.equals(strModel) ).findFirst();

        if(optSensorType.isPresent())
        {
            try {
                Sensor sensor = (Sensor) Class.forName(strModel).getConstructor(Catalogue.class).newInstance(this);
                return sensor;
            }
            // due to the previous conditions, exception will not throw, but Class.forName... requires the catch
            catch(  ClassNotFoundException |
                    NoSuchMethodException |
                    InstantiationException |
                    IllegalAccessException |
                    InvocationTargetException e )
            {
                return null;
            }
        }
        else
            return null;
    }
}
