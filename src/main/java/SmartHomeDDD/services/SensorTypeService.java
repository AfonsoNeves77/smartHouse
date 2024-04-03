package SmartHomeDDD.services;

import SmartHomeDDD.domain.sensorType.SensorType;
import SmartHomeDDD.domain.sensorType.SensorTypeFactory;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.util.ArrayList;
import java.util.List;

public class SensorTypeService {
    private final SensorTypeRepository sensorTypeRepository;
    private final SensorTypeFactory sensorTypeFactory;

    /**
     * Constructor for SensorTypeService. It takes a sensorTypeRepository and factory as parameters, encapsulating them.
     * It also has a method named populate repository that dynamically instantiates and saves all different instances
     * of sensor type unto the sensortype repository.
     * @param sensorTypeRepository SensorTypeRepository object
     * @param sensorTypeFactory SensorTypeFactory object
     */
    public SensorTypeService(SensorTypeRepository sensorTypeRepository, SensorTypeFactory sensorTypeFactory, String path) {
        if (areParamsNull(sensorTypeFactory,sensorTypeRepository,path)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        try{
            this.sensorTypeRepository = sensorTypeRepository;
            this.sensorTypeFactory = sensorTypeFactory;
            populateRepository(path);
        } catch (NullPointerException | ConfigurationException e){
            throw new IllegalArgumentException("Invalid path");
        }
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

    /**
     * Validates if parameters are null
     * @param params Any object parameter
     * @return True or false
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * Receives a path, receives a list of types as string, utilizing the strings to create SensortypeIDVO and UnitVO
     * objects. It then creates a SensorType object with the created VOs using the factory, then saves the new sensortype
     * object unto the repository.
     * @param path Path to the configuration file
     * @throws ConfigurationException Is path is invalid
     */
    private void populateRepository(String path) throws ConfigurationException {
        List<String> listOfTypes = getTypesFromConfig(path);

        for (String string : listOfTypes){
            String[] splitString = splitStrings(string);
            SensorTypeIDVO type = new SensorTypeIDVO(splitString[0]);
            UnitVO unit = new UnitVO(splitString[1]);
            SensorType sensorType = this.sensorTypeFactory.createSensorType(type,unit);
            this.sensorTypeRepository.save(sensorType);
        }
    }

    /**
     * Creates a new configuration object and extracts a List of Strings equivalent to the value of the key provided.
     * @param path Configuration path
     * @return List of Strings
     * @throws ConfigurationException If path invalid
     */
    private List<String> getTypesFromConfig (String path) throws ConfigurationException {
        Configurations config = new Configurations();
        Configuration configuration = config.properties(path);
        String[] arrayOfTypes = configuration.getStringArray("unit");
        return List.of(arrayOfTypes);
    }

    /**
     * Splits a string using a delimiter.
     * @param stringToSplit String to split
     * @return Array of strings
     */
    private String[] splitStrings (String stringToSplit){
        return stringToSplit.split("\\|");
    }
}
