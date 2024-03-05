package SmartHome.domain.actuator;

import SmartHome.domain.ActuatorCatalogue;

import java.util.ArrayList;

public class ListOfActuators {private ArrayList<Actuator> listOfActuators;

    public ListOfActuators() {
        listOfActuators = new ArrayList<>();
    }

    public boolean addActuator(String actuatorName, String actuatorType, ActuatorCatalogue catalogue, SimHardwareAct simHardwareAct) {
        if(!isActuatorInList(actuatorName)){
            Actuator newActuator = catalogue.createActuator(actuatorName,actuatorType,simHardwareAct);
            if(newActuator != null){
                listOfActuators.add(newActuator);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    private boolean isActuatorInList(String actuatorName){
        if(!listOfActuators.isEmpty()){
            return findActuator(actuatorName) !=null;
        }
        return false;
    }
    private Actuator findActuator(String actuatorName){
        for(Actuator singleActuator : listOfActuators) {
            if (actuatorName.equalsIgnoreCase(singleActuator.getName())) {
                return singleActuator;
            }
        }
        return null;
    }
}


