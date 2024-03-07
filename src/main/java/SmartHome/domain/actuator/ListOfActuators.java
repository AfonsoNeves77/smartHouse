package SmartHome.domain.actuator;

import SmartHome.domain.ActuatorCatalogue;

import java.util.ArrayList;

public class ListOfActuators {

    /**
     * Represents a collection of actuators in a device.
     */
    private ArrayList<Actuator> listOfActuators;

    /**
     * Constructs an empty list of actuators.
     */
    public ListOfActuators() {
        listOfActuators = new ArrayList<>();
    }

    /**
     * Adds a new actuator to the list.
     *
     * @param actuatorName The name of the actuator to be added.
     * @param actuatorType The type of the actuator to be added.
     * @param actuatorCatalogue The class where the actuator is created.
     * @param simHardwareAct  Simulates a conexion to and outside source.
     * @return true on successful addition, false if instantiation error and if the sensor already exists.
     */

    public boolean addActuator(String actuatorName, String actuatorType, ActuatorCatalogue actuatorCatalogue, SimHardwareAct simHardwareAct) {
        if(!isActuatorInList(actuatorName)){
            Actuator newActuator = actuatorCatalogue.createActuator(actuatorName,actuatorType,simHardwareAct);
            if(newActuator != null){
                listOfActuators.add(newActuator);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }

    /**
     * Checks if an actuator with the given name already exists in the list.
     *
     * @param actuatorName The name of the actuator to check.
     * @return True if the actuator exists, false otherwise.
     */

    private boolean isActuatorInList(String actuatorName){
        if(!listOfActuators.isEmpty()){
            return findActuator(actuatorName) !=null;
        }
        return false;
    }

    /**
     * Returns an actuator if is present in the listOfActuators.
     *
     * @param actuatorName The name of the actuator to check.
     * @return a singleActuator if successfull, null otherwise.
     */
    private Actuator findActuator(String actuatorName){
        for(Actuator singleActuator : listOfActuators) {
            if (actuatorName.equalsIgnoreCase(singleActuator.getName())) {
                return singleActuator;
            }
        }
        return null;
    }
}


