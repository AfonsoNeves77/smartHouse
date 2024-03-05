package SmartHome.domain.sensor;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

public class ListOfSensors {

    /**
     * Represents a collection of sensors in a device.
     */

    private ArrayList<Sensor> listOfSensors;

    /**
     * Constructs an empty list of sensors.
     */

    public ListOfSensors(){
        listOfSensors = new ArrayList<>();
    }

    /**
     * Adds a new sensor to the list.
     *
     * @param sensorName The name of the sensor to be added.
     * @param sensorType The type of the sensor to be added.
     * @return 0 on successful addition, 1 if instantiation error, 2 if the sensor already exists.
     */

    public boolean addSensor(String sensorName, String sensorType, SensorCatalogue catalogue, SimHardware simHardware) {
        if (!isSensorInList(sensorName)) {
            Sensor newSensor = catalogue.createSensor(sensorName, sensorType, simHardware);
            if(newSensor != null){
                this.listOfSensors.add(newSensor);
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

    public ArrayList<String> getLog(String sensorName){
        Sensor sensor = findSensor (sensorName);
        if (sensor != null){
            return sensor.getLog();
        }
        return null;
    }

    private Sensor findSensor (String sensorName){
        for (Sensor singleSensor : listOfSensors){
            if (sensorName.equalsIgnoreCase(singleSensor.getName())){
                return singleSensor;
            }
        }
        return null;
    }

    /**
     * Checks if a sensor with the given name already exists in the list.
     *
     * @param sensorName The name of the sensor to check.
     * @return True if the sensor exists, false otherwise.
     */

    private boolean isSensorInList(String sensorName){
        if(!listOfSensors.isEmpty()){
            return findSensor(sensorName) != null;
        }
        return false;
    }
}
