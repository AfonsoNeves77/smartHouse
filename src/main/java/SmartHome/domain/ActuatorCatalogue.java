package SmartHome.domain;

import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.SimHardwareAct;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActuatorCatalogue {

    private List<String> listOfInstantiableActuators;

    public ActuatorCatalogue(Configuration configuration) throws InstantiationException {
        if(!areConstructorParametersValid(configuration)){
            throw new InstantiationException("Invalid parameters");
        }
        String[] arrayInstantiableActuators = configuration.getStringArray("actuator");
        this.listOfInstantiableActuators = List.of(arrayInstantiableActuators);
    }

    public ActuatorCatalogue(String filePath) throws InstantiationException {
        if(!areConstructorParametersValid(filePath)){
            throw new InstantiationException("Invalid parameters");
        }
        try {
            Configurations configs = new Configurations();
            Configuration configuration = configs.properties(new File(filePath));

            String[] arrayInstantiableActuators = configuration.getStringArray("actuator");
            this.listOfInstantiableActuators = List.of(arrayInstantiableActuators);
        }catch (ConfigurationException e){
            throw new InstantiationException("Error reading file");
        }
    }

    private boolean areConstructorParametersValid(Configuration configuration){
        return configuration != null;
    }

    private boolean areConstructorParametersValid(String filePath){
        return filePath != null && !filePath.isEmpty() && !filePath.isBlank();
    }

    public List<String> getListOfInstantiableActuators() {
        return new ArrayList<>(this.listOfInstantiableActuators);
    }

    public Actuator createActuator(String actuatorName, String actuatorType, SimHardwareAct simHardwareAct) {
        Optional<String> optionalActuator = this.listOfInstantiableActuators.stream().filter(s -> s.equalsIgnoreCase(actuatorType)).findFirst();
        if(optionalActuator.isPresent()){
            try{
                Class<?> classObj = Class.forName(actuatorType);
                Constructor<?> constructor = classObj.getConstructor(String.class, SimHardwareAct.class);
                Actuator actuator = (Actuator) constructor.newInstance(actuatorName, simHardwareAct);
                return actuator;
            }catch (ClassNotFoundException | NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e){
                return null;
            }
        } else {
            return null;
        }
    }
}
