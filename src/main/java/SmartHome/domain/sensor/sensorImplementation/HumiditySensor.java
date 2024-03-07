package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class HumiditySensor implements Sensor {
    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "%";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
     * Constructor for Humidity Sensor. The instantiation ensures the name is valid, throwing InstantiationException otherwise.
     * @param sensorName Name of the sensor
     */
    public HumiditySensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
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

    public Value<Integer> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        HumidityValue readingValue = new HumidityValue(simValue);

        addValueToLog(readingValue);
        return readingValue;
    }


    /**
     * Converts the log list from Value to String.
     * 1. Creates an empty ArrayList of strings.
     * 2. Iterates through all the Value objects saved into the log. For each Value object found, it converts
     * it to string, and adds it to the ArrayList created in step 1. Returning it after it performs the iteration.
     * @return Log as a list of strings.
     */
    @Override
    public ArrayList<String> getLog(){
        // 1.
        ArrayList<String> tmpLog = new ArrayList<>();
        // 2.
        for (Value<Integer> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }

    /**
     * Receives a value generic of a specific wrapper class.
     * @param value Value to be inserted.
     */
    private void addValueToLog(Value<Integer> value){
        this.log.add(value);
    }

    /**
     * @return Returns the sensor's unit. This method is still not being called by any class, but it was created
     * nonetheless as it is likely it will be needed for querying sensors by type.
     */
    // This method is still not being called by any class, but it was created nonetheless as it is likely it will be needed for querying sensors by type
    public String getUnit(){
        return this.unit;
    }
}