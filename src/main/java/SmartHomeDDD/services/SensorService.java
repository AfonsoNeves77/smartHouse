package SmartHomeDDD.services;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.sensor.Sensor;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SensorService {
    private final SensorRepository sensorRepository;
    private final SensorFactory sensorFactory;

    /**
     * Constructor for SensorService, it receives a SensorRepository and SensorFactory objects, encapsulating them if in a valid state
     * @param sensorRepository Sensor Repository Object
     * @param sensorFactory Sensor Factory Object
     */
    public SensorService(SensorRepository sensorRepository, SensorFactory sensorFactory) {
        if (areParamsNull(sensorRepository,sensorFactory)){
            throw new IllegalArgumentException("Invalid repository");
        }
        this.sensorRepository = sensorRepository;
        this.sensorFactory = sensorFactory;
    }

    /**
     * Receives SensorNameVO, DeviceIDVO and SensorTypeIDVO objects, ensures they are not null, passes them on to the
     * encapsulated Sensor Factory and propagates the return received from Sensor Factory.
     * @param sensorName SensorNameVO object
     * @param deviceIDVO DeviceIDVO object
     * @param sensorTypeIDVO SensorTypeIDVO object
     * @return Sensor object
     */
    public Sensor createSensor(SensorNameVO sensorName, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO){
        if (areParamsNull(sensorName,deviceIDVO,sensorTypeIDVO)){ //Optional validation. This may be discarded as factory checks for null
            return null;
        }
        return sensorFactory.createSensor(sensorName,deviceIDVO,sensorTypeIDVO);
    }

    /**
     * Receives a Sensor object, ensures it is not null, passes it on to the Sensor Repository in order to save. Propagates
     * the return received from the Sensor Repository
     * @param sensor Sensor object
     * @return True or false
     */
    public boolean saveSensor(Sensor sensor){
        if (areParamsNull(sensor)){ //Optional validation. This may be discarded as repository checks for null
            return false;
        }
        return sensorRepository.save(sensor);
    }

    /**
     * Receives object parameters and verifies they are null
     * @param params Any object parameter.
     * @return True or false
     */
    private boolean areParamsNull (Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * This method communicates with the Sensor Repository and requests a list of actuators.
     * @return List <Actuator>
     */
    public List<Sensor> getListOfSensors(){
        Iterable<Sensor> sensors = this.sensorRepository.findAll();
        List<Sensor> finalList = new ArrayList<>();

        for (Sensor type : sensors){
            finalList.add(type);
        }
        return finalList;
    }

    /**
     * This method iterates through the list of Sensors to create a map of unique types to List <Devices>
     * 0. Creates a map: string(value of typeVO), List <DeviceIDVO>
     * 1. Requests the SensorType and DeviceID;
     * 2. Checks if there is an entry on the map with that type, if not, adding it. Checks if the deviceID is already
     * present in the List <DeviceID> related to that entry, adding it if not.
     * @return Map string, List<DeviceIDVO>
     */
    public Map<String,List<DeviceIDVO>> getListOfDeviceIDsByFunctionality (){
        List<Sensor> sensorList = getListOfSensors();
        LinkedHashMap<String,List<DeviceIDVO>> filteredMap = new LinkedHashMap<>();
        for (Sensor sensor : sensorList){
            String type = sensor.getSensorTypeID().getID();
            DeviceIDVO deviceID = sensor.getDeviceID();
            if (filteredMap.containsKey(type)){
                List<DeviceIDVO> devicesPerKey = filteredMap.get(type);
                if(!devicesPerKey.contains(deviceID)){
                    devicesPerKey.add(deviceID);
                }
            } else {
                List<DeviceIDVO> devicesPerKey = new ArrayList<>();
                devicesPerKey.add(deviceID);
                filteredMap.put(type,devicesPerKey);
            }
        }
        return filteredMap;
    }
}
