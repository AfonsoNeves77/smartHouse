package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.DewPointValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

public class DewPointSensor implements Sensor {

    private String sensorName;
    private SimHardware simHardware;
    private final String UNIT = "C";
    private final String TYPE = "DewPointSensor";
    private final ArrayList<Value<Double>> log = new ArrayList<>();

    /**
     * Constructor for DewPointSensor. The instantiation ensures the name is valid, throwing InstantiationExpection otherwise.
     * @param sensorName Name of the sensor
     * @param externalServices external services that will be used to get the sensor reading.
     */
    public DewPointSensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
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
     * @return String containing the Type of the sensor.
     */
    public String getTYPE() { return this.TYPE; }

    /**
     * Method to get the reading from the sensor. It gets the reading from the simHardware and creates a new DewPointValue object with it.
     * It then adds the value to the sensor log.
     * @return DewPointValue object with the reading from the sensor.
     * @throws InstantiationException
     */

    public Value<Double> getReading() throws InstantiationException {

        String simValue = this.simHardware.getValue();

        DewPointValue readingValue = new DewPointValue(simValue);

        addValueToLog(readingValue);
        return readingValue;
    }

    /**
     * Converts the log list from Value to String.
     * 1. Creates an empty ArrayList of strings.
     * 2. Iterates through all the Value objects saved into the log. For each Value object found, it converts
     * it to String. It then adds it to the ArrayList created in step 1, returning it after it performs the iteration.
     * @return depLogo as a list of strings.
     */
    @Override
    public ArrayList<String> getLog() {
        ArrayList<String> dpLog = new ArrayList<>();
        // 2.
        for (Value<Double> value : this.log){
            dpLog.add(value.getValueAsString());
        }
        return dpLog;
    }

    /**
     * Method to add a value to the sensor log.
     * @param value Value object to be added to the log.
     */
    private void addValueToLog (Value<Double> value){
        this.log.add(value);
    }

    /**
     * @return Returns the sensor's unit. This method is still not being called by any class, but it was created
     * nonetheless as it is likely it will be needed for querying sensors by type.
     */
    public String getUnit() {
        return this.UNIT;
    }






}
