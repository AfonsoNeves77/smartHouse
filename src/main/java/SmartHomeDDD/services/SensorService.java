package SmartHomeDDD.services;

import SmartHomeDDD.domain.sensor.Sensor;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

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
}
