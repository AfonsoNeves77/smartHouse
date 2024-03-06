package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class PositionSensor implements Sensor{

    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "%";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
    * Constructor for Position Sensor. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
    * @param sensorName Name of the sensor
    * @param externalServices ExternalServices object that will be used to get the sensor reading.
     */

    public PositionSensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new InstantiationException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    /**
     * @return String containing the name of the sensor.
    */

    @Override
    public String getName(){
        return this.sensorName;
    }

    /**
     * Method to get the reading from the sensor. It gets the reading from the external services and creates a new PositionValue object with it.
     * It then adds the value to the sensor log.
     * @return PositionValue object with the reading from the sensor.
     * @throws InstantiationException
     */

    public Value<Integer> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        PositionValue readingValue = new PositionValue(simValue);

        addValueToLog(readingValue);
        return readingValue;
    }

    /**
     * Converts the log list from Value to String.
     * 1. Creates an empty ArrayList of strings.
     * 2. Iterates through all the Value objects saved into the log. For each Value object found, it converts
     * it to String. It then adds it to the ArrayList created in step 1, returning it after it performs the iteration.
     * @return logList as a list of strings.
     */

    @Override
    public ArrayList<String> getLog(){
        ArrayList<String> logList = new ArrayList<>();
        for(Value<Integer> value : log){
            logList.add(value.getValueAsString());
        }
        return logList;
    }

    /**
     * Method to add a value to the sensor log.
     * @param value Value object to be added to the log.
     */

    private void addValueToLog(Value<Integer> value){
        log.add(value);
    }

    /**
     * @return Returns the sensor's unit. This method is still not being called by any class, but it was created
     * nonetheless as it is likely it will be needed for querying sensors by type.
     */

    public String getUnit(){
        return this.unit;
    }


}
