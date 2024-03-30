package SmartHomeDDD.services;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeService {
    private final SensorTypeRepository sensorTypeRepository;

    /**
     * Constructor for SensorTypeService. It takes a sensorTypeRepository as parameter and encapsulates it.
     * @param sensorTypeRepository
     */
    public SensorTypeService(SensorTypeRepository sensorTypeRepository) {
        if (sensorTypeRepository == null){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.sensorTypeRepository = sensorTypeRepository;
    }

    /**
     * Obtains a list of sensor types by communicating with the encapsulated repository. It also translates the receiving
     * iterable into a List for indexed access.
     * @return List <SensorType>
     */
    public List<SensorType> getListOfSensorTypes(){
        Iterable<SensorType> sensorTypes = this.sensorTypeRepository.findAll();
        List<SensorType> finalList = new ArrayList<>();

        for (SensorType type : sensorTypes){
            finalList.add(type);
        }
        return finalList;
    }

    /**
     * Verifies if a specific sensor type id is contaned within the repository
     * @param sensorTypeID SensorTypeIDVO object
     * @return True or false
     */
    public boolean sensorTypeExists (SensorTypeIDVO sensorTypeID){
        return this.sensorTypeRepository.isPresent(sensorTypeID);
    }
}
