package SmartHomeDDD.services;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ActuatorService {
    private ActuatorFactory factoryActuator;
    private ActuatorRepository actuatorRepository;

    /**
     * Constructs a new ActuatorService instance.
     *
     * @param factoryActuator   The factory responsible for creating Actuators.
     * @param ActuatorRepository The repository storing and retrieving Actuators.
     * @throws IllegalArgumentException if either factoryActuator or ActuatorRepository is null.
     */

    public ActuatorService(ActuatorFactory factoryActuator, ActuatorRepository ActuatorRepository){
        if(!validParams(factoryActuator,ActuatorRepository)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.factoryActuator = factoryActuator;
        this.actuatorRepository = ActuatorRepository;
    }

    /**
     * Creates a new Actuator with the provided parameters.
     *
     * @param actuatorNameVO The name of the Actuator.
     * @param actuatorTypeIDVO The type of the Actuator.
     * @param deviceIDVO The ID of the device where the Actuator will be located.
     * @return The created Actuator instance, or null if the parameters are invalid.
     */

    public Actuator createActuator(ActuatorNameVO actuatorNameVO, ActuatorTypeIDVO actuatorTypeIDVO, DeviceIDVO deviceIDVO){
        if(validParams(actuatorNameVO, actuatorTypeIDVO,deviceIDVO)){
            return factoryActuator.createActuator(actuatorNameVO,actuatorTypeIDVO,deviceIDVO);
        }
        return null;
    }

    /**
     * Saves the given Actuator to the repository.
     *
     * @param Actuator The Actuator to be saved.
     * @return true if the Actuator was successfully saved, false otherwise.
     */

    public boolean saveActuator(Actuator Actuator){
        if(validParams(Actuator)){
            return this.actuatorRepository.save(Actuator);
        }
        return false;
    }


    /**
     * Checks if all parameters passed to the method are non-null.
     * This method ensures that all parameters provided to it are not null before proceeding with further operations.
     * While this function may not be considered type-safe, it is always used after ensuring type safety
     * by type-checking the variables of the calling method (compiler).
     *
     * @param params The parameters to be checked for nullity.
     * @return true if all parameters are non-null, false otherwise.
     */

    private boolean validParams (Object... params){
        for (Object param : params){
            if (param == null){
                return false;
            }
        }
        return true;
    }

    /**
     * This method communicates with the Actuator Repository and requests a list of actuators.
     * @return List <Actuator>
     */
    public List<Actuator> getListOfActuators(){
        Iterable<Actuator> actuators = this.actuatorRepository.findAll();
        List<Actuator> finalList = new ArrayList<>();

        for (Actuator type : actuators){
            finalList.add(type);
        }
        return finalList;
    }

    /**
     * This method iterates through the list of Actuator to create a map of unique types to List <Devices>
     * 0. Creates a map: string(value of typeVO), List <DeviceIDVO>
     * 1. Requests the ActuatorType and DeviceID;
     * 2. Checks if there is an entry on the map with that type, if not, adding it. Checks if the deviceID is already
     * present in the List <DeviceID> related to that entry, adding it if not.
     * @return Map string, List<DeviceIDVO>
     */
    public LinkedHashMap<String,List<DeviceIDVO>> getListOfDeviceIDsByFunctionality (LinkedHashMap<String,List<DeviceIDVO>> previousMap){
        List<Actuator> actuatorList = getListOfActuators();
        for (Actuator actuator : actuatorList){
            String type = actuator.getActuatorTypeID().getID();
            DeviceIDVO deviceID = actuator.getDeviceID();
            if (previousMap.containsKey(type)){
                List<DeviceIDVO> devicesPerKey = previousMap.get(type);
                if(!devicesPerKey.contains(deviceID)){
                    devicesPerKey.add(deviceID);
                }
            } else {
                List<DeviceIDVO> devicesPerKey = new ArrayList<>();
                devicesPerKey.add(deviceID);
                previousMap.put(type,devicesPerKey);
            }
        }
        return previousMap;
    }
}
