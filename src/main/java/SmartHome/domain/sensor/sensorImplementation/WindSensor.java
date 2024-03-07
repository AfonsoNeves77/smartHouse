package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

public class WindSensor implements Sensor {
    private String sensorName;
    private SimHardware simHardware;
    private final String type = "WindSensor";
    private final String unit = "Km/h";
    private final ArrayList<Value<Double>> log = new ArrayList<>();

    /**
     * Constructor for Wind Sensor. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
     * @param sensorName Name of the sensor
     */
    public WindSensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
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
    public String getName() {
        return this.sensorName;
    }

    /**
     * Communicates with simHardware in order to receive a reading, it then converts it to value,
     * all the validations are done on value creation. It then adds that value object to the sensor's
     * log and returns a copy of that value.
     * @return Returns the reading obtained, validated and converted to Value.
     * @throws InstantiationException If unable to create value.
     */
    public Value<?> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        WindValue readingValue = new WindValue(simValue);

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
        for (Value<Double> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }

    /**
     * Receives a value generic of a specific wrapper class.
     * @param value Value to be inserted.
     */
    private void addValueToLog (Value<Double> value){
        this.log.add(value);
    }

    /**
     * @return Returns the sensor's unit. This method is still not being called by any class, but it was created
     * nonetheless as it is likely it will be needed for querying sensors by type.
     */
    public String getUnit(){
        return this.unit;
    }
    public String getType(){
        return this.type;
    }
}