package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.HumidityValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;

import java.util.ArrayList;
import static java.lang.Integer.parseInt;

public class HumiditySensor implements Sensor {
    private String sensorName;
    private final String unit = "%";
    private final ArrayList<Value<Integer>> log = new ArrayList<>();

    /**
     * Constructor for Humidity Sensor. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
     * @param sensorName Name of the sensor
     */
    public HumiditySensor(String sensorName) {
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
    }

    /**
     * @return String containing the name of the sensor.
     */
    @Override
    public String getName(){
        return this.sensorName;
    }

    /**
     * This method receives a connection to a physical sensor. Upon receiving its reading, it checks its validity and encapsulates
     * it into a value object of the type TemperatureValue. It then saves a copy of the value object in the log and returns another copy.
     * 1. Calls getValue on the simHardware object, receiving a reading as string. If the receiving string is null or empty, it throws an illegal
     * argument exception.
     * 2. Converts the string value received above into the specific primitive that is used by this sensor. If it is unable to parse, it will return a
     * NumberFormatException. E.g. If the receiving string is "This will fail", the parse will not work, thus stopping the process.
     * 3. After obtaining a primitive value, it then attempts to encapsulate it into the respective SensorValue. If this fails
     * it will throw an InstantiationException.
     * 4. After successfully receiving a string reading, converting it into a primitive, and encapsulating it into a value,
     * it saves a copy in the log, and returning another copy.
     * @param simHardware Simulates the connection to a piece of hardware/external api that physically measures the reading
     * @return Returns a temperature value. It uses a generic Value object while specifying the wrapper class of the primitive in scope.
     */

    //Onde tratar InstantiationException?? Onde tratar NumberFormatException?
    public Value<Integer> getReading(SimHardware simHardware) throws InstantiationException {

        // 1.
        String simValue = simHardware.getValue();
        if (simValue == null || simValue.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid reading");
        }

        // 2.
        int value;
        try {
            value = parseInt(simValue);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid reading");
        }

        // 3.
        HumidityValue readingValue = new HumidityValue(value);

        // 4.
        addValueToLog(readingValue);
        return readingValue;
    }

    public HumidityValue defineHumidityValue(int value) throws InstantiationException {
        return new HumidityValue(value);
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