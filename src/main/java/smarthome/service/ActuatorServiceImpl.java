package smarthome.service;

import org.springframework.stereotype.Service;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.device.Device;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.persistence.ActuatorRepository;
import smarthome.persistence.ActuatorTypeRepository;
import smarthome.persistence.DeviceRepository;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;

import java.util.Optional;

@Service
public class ActuatorServiceImpl implements ActuatorService{
    private final DeviceRepository deviceRepository;
    private final ActuatorTypeRepository actuatorTypeRepository;
    private final ActuatorFactory actuatorFactory;
    private final ActuatorRepository actuatorRepository;

    /**
     * Constructs an instance of V1ActuatorService with the provided dependencies.
     * @param actuatorFactory   The factory responsible for creating Actuators.
     * @param actuatorRepository The repository storing and retrieving Actuators.
     * @throws IllegalArgumentException if either factoryActuator or ActuatorRepository is null.
     */

    public ActuatorServiceImpl(DeviceRepository deviceRepository, ActuatorTypeRepository actuatorTypeRepository,
                               ActuatorFactory actuatorFactory, ActuatorRepository actuatorRepository){

        if(areParamsNull(deviceRepository, actuatorTypeRepository, actuatorFactory, actuatorRepository)){
            throw new IllegalArgumentException("Parameters cannot be null");
        }

        this.deviceRepository = deviceRepository;
        this.actuatorTypeRepository = actuatorTypeRepository;
        this.actuatorFactory = actuatorFactory;
        this.actuatorRepository = actuatorRepository;
    }

    /**
     * Adds a new Actuator to the system.
     * This method creates a new Actuator with the provided parameters and saves it to the repository.
     * Before the Actuator is created, the method validates the parameters and checks if the associated Device is active
     * and if the Actuator type is present in the system.
     * The method interacts with the ActuatorFactory to create a new Actuator instance. This is done by calling the
     * createActuator method of the ActuatorFactory with the specified parameters.
     * The method also interacts with the ActuatorRepository to save the newly created Actuator. This is done by
     * calling the save method of the ActuatorRepository with the new Actuator as the parameter.
     * If the save operation is successful (returns true), the newActuator is wrapped in an Optional and returned.
     * If the save operation is not successful (returns false), an empty Optional is returned.
     * @param actuatorNameVO The name of the Actuator to be added. This parameter cannot be null.
     * @param actuatorTypeIDVO The type ID of the Actuator to be added. This parameter cannot be null and the Actuator
     *                         type must be present in the system.
     * @param deviceIDVO The ID of the Device to which the Actuator belongs. This parameter cannot be null and the
     *                   Device must be active.
     * @param settings The settings for the Actuator.
     * @return If the save operation is successful (returns true), the newActuator is wrapped in an Optional and
     * returned. If the save operation is not successful (returns false), an empty Optional is returned.
     * @throws IllegalArgumentException if any of the parameters are null, if the Device is not active, or if the
     * Actuator type is not present.
     */
    public Optional<Actuator> addActuator(ActuatorNameVO actuatorNameVO, ActuatorTypeIDVO actuatorTypeIDVO,
                                          DeviceIDVO deviceIDVO, Settings settings){

        if (areParamsNull(actuatorNameVO,actuatorTypeIDVO,deviceIDVO)) {
            throw new IllegalArgumentException("Parameters cannot be null");
        }
        if (!isDeviceActive(deviceIDVO)) {
            throw new IllegalArgumentException("Device is not active");
        }
        if (!isActuatorTypePresent(actuatorTypeIDVO)) {
            throw new IllegalArgumentException("Actuator type is not present");
        }

        Actuator newActuator = this.actuatorFactory.createActuator(actuatorNameVO,actuatorTypeIDVO,
                                                                    deviceIDVO,settings);
        if (this.actuatorRepository.save(newActuator)){
            return Optional.of(newActuator);
        }
        return Optional.empty();
    }

    /**
     * Checks the status of the Device with the provided DeviceIDVO to determine if it is active.
     * This method queries the DeviceRepository to retrieve the Device based on the given ID.
     * If the Device is found and marked as active, it returns true, indicating the Device is active.
     * If the Device is not found or is not marked as active, it returns false.
     * @param deviceIDVO The Value Object representing the ID of the Device to check.
     * @return true if the Device is active, false otherwise or if the Device is not found.
     */
    private boolean isDeviceActive(DeviceIDVO deviceIDVO){
        try{
            Device device = deviceRepository.findById(deviceIDVO);
            return device.isActive();
        } catch (NullPointerException e){
            return false;
        }
    }

    /**
     * Checks if an ActuatorType with the provided ActuatorTypeIDVO is present in the system.
     * This method queries the ActuatorTypeRepository to determine the presence of the ActuatorType.
     * @param actuatorTypeIDVO The Value Object representing the ID of the ActuatorType to check.
     * @return true if the ActuatorType is present in the system, false otherwise. It returns false if the ActuatorType
     * is not found or if the repository is unable to provide the information.
     */
    private boolean isActuatorTypePresent(ActuatorTypeIDVO actuatorTypeIDVO){
        return actuatorTypeRepository.isPresent(actuatorTypeIDVO);
    }

    /**
     * This method retrieves an Actuator from the repository based on the provided ActuatorIDVO.
     * It first checks if the ActuatorIDVO is null and throws an IllegalArgumentException if it is.
     * It then queries the ActuatorRepository to retrieve the Actuator based on the provided IDVO.
     * If the Actuator is found, it is wrapped in an Optional and returned. If the Actuator is not found, an empty
     * Optional is returned.
     * @param actuatorIDVO The Value Object representing the ID of the Actuator to retrieve.
     * @return An Optional containing the Actuator if it is found, or an empty Optional if it is not found.
     */

    public Optional<Actuator> getActuatorById(ActuatorIDVO actuatorIDVO){
        if (actuatorIDVO == null){
            throw new IllegalArgumentException("ActuatorIDVO cannot be null");
        }
        if (actuatorRepository.isPresent(actuatorIDVO)){
            return Optional.of(actuatorRepository.findById(actuatorIDVO));
        }
        return Optional.empty();
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
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
