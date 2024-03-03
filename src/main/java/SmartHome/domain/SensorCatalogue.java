package SmartHome.domain;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import SmartHome.domain.sensor.sensorImplementation.Sensor;


import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SensorCatalogue {
    private List<String> listOfInstantiableSensors;


    public SensorCatalogue(Configuration configuration) throws InstantiationException {
        if(!areConstructorParametersValid(configuration)){
            throw new InstantiationException("Invalid parameters");
        }
        String[] arrayInstantiableSensors = configuration.getStringArray("sensor");
        this.listOfInstantiableSensors = List.of(arrayInstantiableSensors);
    }

    public SensorCatalogue(String filePath) throws InstantiationException {
        if(!areConstructorParametersValid(filePath)){
            throw new InstantiationException("Invalid parameters");
        }
        try {
            Configurations configs = new Configurations();
            Configuration configuration = configs.properties(new File(filePath));

            String[] arrayInstantiableSensors = configuration.getStringArray("sensor");
            this.listOfInstantiableSensors = List.of(arrayInstantiableSensors);
        }catch (ConfigurationException e){
            throw new InstantiationException("Error reading file");
        }
    }

    public List<String> getListOfInstantiableSensors() {
        return new ArrayList<>(this.listOfInstantiableSensors);
    }

    public Sensor createSensor(String sensorName, String sensorType) {
        Optional<String> optionalSensor = this.listOfInstantiableSensors.stream().filter(s -> s.equalsIgnoreCase(sensorType)).findFirst();
        if(optionalSensor.isPresent()){
            try{
            Class<?> classObj = Class.forName(sensorType);
            Constructor<?> constructor = classObj.getConstructor(String.class);
            Sensor sensor = (Sensor) constructor.newInstance(sensorName);
            return sensor;
            }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                return null;
            }
        } else {
            return null;
        }
    }

    private boolean areConstructorParametersValid(Configuration configuration){
        return configuration != null;
    }

    private boolean areConstructorParametersValid(String filePath){
        return filePath != null && !filePath.isEmpty() && !filePath.isBlank();
    }

}
