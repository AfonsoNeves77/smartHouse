package SmartHomeDDD.services;

import SmartHomeDDD.domain.actuatorType.ActuatorType;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Service layer class for managing actuator types within the system. This class provides methods
 * for interacting with the ActuatorTypeRepository to perform operations such as retrieving
 * all actuator types, and checking the existence of an actuator type.
 * It encapsulates the business logic related to actuator types, ensuring that consumers of this
 * service can perform necessary operations without direct access to the underlying data access layer.
 * This abstraction also facilitates easier testing and maintenance.
 * An instance of this class must be initialized with a non-null ActuatorTypeRepository,
 * which it then uses to interact with actuator type data. The service validates the presence of the
 * repository upon construction to prevent null-pointer exceptions during runtime.
 */
public class ActuatorTypeService {

    private final ActuatorTypeRepository actuatorTypeRepository;

    /**
     * Constructor for ActuatorTypeService. Checks if the repository is null.
     * @param actuatorTypeRepository Repository for ActuatorType.
     */
    public ActuatorTypeService(ActuatorTypeRepository actuatorTypeRepository) {
        if (actuatorTypeRepository == null){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.actuatorTypeRepository = actuatorTypeRepository;
    }


    /**
     * Returns a list of all ActuatorTypes in the system.
     * @return List of ActuatorTypes.
     */
    public List<ActuatorType> getListOfActuatorTypes(){
        Iterable<ActuatorType> actuatorTypes = this.actuatorTypeRepository.findAll();
        List<ActuatorType> listOfActuatorTypes = new ArrayList<>();

        for (ActuatorType type : actuatorTypes){
            listOfActuatorTypes.add(type);
        }
        return listOfActuatorTypes;
    }


    /**
     * Checks if an ActuatorType exists through the ActuatorTypeIDVO.
     * @param actuatorTypeIDVO ActuatorTypeIDVO.
     * @return boolean, returns true if the ActuatorType exists.
     */
    public boolean actuatorTypeExists (ActuatorTypeIDVO actuatorTypeIDVO){
        if(this.actuatorTypeRepository.isPresent(actuatorTypeIDVO)){
            return true;
        } else{
            return false;
        }
    }
}
