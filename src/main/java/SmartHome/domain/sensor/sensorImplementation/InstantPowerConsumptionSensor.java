package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.InstantPowerConsumptionValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.util.ArrayList;



public class InstantPowerConsumptionSensor implements Sensor {
    private String sensorName;
    private SimHardware simHardware;
    private final String unit = "W";
    private final String type = "InstantPowerConsumptionSensor";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
     * Constructor for Instant Power Consumption. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
     * @param sensorName Name of the sensor
     * @param externalServices External services to be used by the sensor cast to SimHardware
     */
    public InstantPowerConsumptionSensor(String sensorName, ExternalServices externalServices){
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
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
     * @return String containing the unit of the sensor.
     */
    @Override
    public String getUnit() {
        return this.unit;
    }

    /**
     * @return String containing the type of the sensor.
     */
    public String getTYPE() {
        return type;
    }

    /**
     * Communicates with simHardware in order to receive a reading, it then converts it to value,
     * all the validations are done on value creation. It then adds that value object to the sensor's
     * log and returns a copy of that value.
     * @return Returns the reading obtained, validated and converted to Value.
     * @throws InstantiationException If unable to create value.
     */
    public Value<Integer> getReading() throws InstantiationException {
        String simValue = this.simHardware.getValue();

        InstantPowerConsumptionValue readingValue = new InstantPowerConsumptionValue(simValue);

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
    public ArrayList<String> getLog() {
        //1.
        ArrayList<String> tmpLog = new ArrayList<>();
        //2.
        for (Value<Integer> value : this.log){
            tmpLog.add(value.getValueAsString());
        }
        return tmpLog;
    }

    /**
     * Receives a value generic of a specific wrapper class.
     * @param value Value to be inserted.
     */
    private void addValueToLog(Value<Integer> value) {
      this.log.add(value);
    }
}
