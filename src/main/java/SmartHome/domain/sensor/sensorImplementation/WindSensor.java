package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.WindValue;
import SmartHome.domain.sensor.simHardware.SimHardware;

import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class WindSensor implements Sensor {
    private String sensorName;
    private final String unit = "Km/h";
    private final ArrayList<Value<Double>> log = new ArrayList<>();

    /**
     * Constructor for Wind Sensor. The instantiation ensures the name is valid, throwing IllegalArgumentException otherwise.
     * @param sensorName Name of the sensor
     */
    public WindSensor(String sensorName){
        if(sensorName == null || sensorName.trim().isEmpty()){
            throw new IllegalArgumentException("Invalid parameter");
        }
        this.sensorName = sensorName;
    }

    /**
     * @return String containing the name of the sensor.
     */
    @Override
    public String getName() {
        return this.sensorName;
    }


    /**
     * This method receives a connection to a physical sensor. Upon receiving its reading, it checks its validity and encapsulates
     * it into a value object of the type Value. It then saves a copy of the value object in the log and returns another copy.
     * 1. Calls getValue on the simHardware object, receiving a reading as string.
     * 2. Calls the private method is reading valid that checks if simValue string is null or empty, if it can be split, if it has a delimiter and if the resulting
     * two strings are valid.
     * 3. If the above validations pass, it then attempts to encapsulate it into the respective SensorValue. If this fails
     * it will throw an InstantiationException.
     * 4. After successfully receiving a string reading, converting it into a primitive, and encapsulating it into a value,
     * it saves a copy in the log, and returning another copy.
     * @param simHardware Simulates the connection to a piece of hardware/external api that physically measures the reading
     * @return Returns a temperature value. It uses a generic Value object while specifying the wrapper class of the primitive in scope.
     */
    public Value<?> getReading(SimHardware simHardware) throws InstantiationException {

        String errorMessage = "Invalid reading";
        // 1.
        String simValue = simHardware.getValue();

        // 2.
        if (!isReadingValid(simValue)){
            throw new IllegalArgumentException(errorMessage);
        }

        // 3.
        String[] words = simValue.split("-");
        int windSpeed = parseInt(words[0]);
        String windDirection = words[1];
        WindValue readingValue = new WindValue(windSpeed,windDirection);

        //4.
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

    /**
     * This method is used to avoid having to deal with multiple types of exceptions that may result from the validations
     * required.
     * @param reading This reading reflects the reading obtained by simValue
     * @return True or false
     */
    private boolean isReadingValid(String reading){
        if (reading == null || reading.trim().isEmpty()) {
            return false;
        }
        try {
            String[] words = reading.split("-");
            parseInt(words[0]);
            String windDirection = words[1];
            // The above variable is not being called. But the line is checking for an ArrayOutOfBoundsException
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}