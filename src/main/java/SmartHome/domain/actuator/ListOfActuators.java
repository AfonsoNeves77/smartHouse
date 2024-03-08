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
     * Adds a new actuator to the list. First it verifies by name if an actuator is already present on the encapsulated list
     * After that it requests that the catalogue creates an actuator providing name, type and a simulated connection.
     * The catalogue then verifies if the type inputted is present on the permitted actuator type list. If the catalogue
     * succeeds in creating the actuator, that new copy is added to the encapsulated list. This method return true
     * if all these operations are done successfully
     *
     * @param actuatorName The name of the actuator to be added.
     * @param actuatorType The type of the actuator to be added.
     * @param actuatorCatalogue The class where the actuator is created.
     * @param simHardwareAct  Simulates a connection to and outside source.
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
     * This method iterates through the encapsulated list and checks the name of each actuator. Through this process, if it finds
     * that one of the actuators has the same name as the inserted one, it returns true.
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
     * Iterates through the encapsulated list and checks if any actuator have the same name as the inserted name. If it finds it,
     * it returns the actuator object, otherwise it returns null.
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


