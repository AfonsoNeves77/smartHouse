package SmartHome.domain.sensor.sensorImplementation;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SolarIrradianceValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;

import java.util.ArrayList;
import java.util.List;

/**
 * SolarIrradianceSensor class for SolarIrradianceSensor
 * Implements Sensor
 * @see Sensor
 */

public class SolarIrradianceSensor implements Sensor {

    /**
     * sensorName String to store the name of the sensor
     * simHardware SimHardware to store the SimHardware object
     * TYPE String to store the type of the sensor
     * UNIT String to store the unit of the sensor
     * log List of Value<Integer> to store the log of the sensor
     */

    private String sensorName;
    private final String TYPE = "SolarIrradiance";
    private SimHardware simHardware;
    private final String UNIT = "W/m2";
    private final List<Value<Integer>> log = new ArrayList<>();

    /**
     * SolarIrradianceSensor constructor
     * @param sensorName String to store the name of the sensor
     * @param externalServices ExternalServices to store the ExternalServices object
     * @throws InstantiationException if the sensor name is null or empty or the externalServices object is null
     */

    public SolarIrradianceSensor(String sensorName, ExternalServices externalServices) throws InstantiationException {
        if (sensorName == null || sensorName.trim().isEmpty() || externalServices == null) {
            throw new InstantiationException("Invalid parameter");
        }
        this.sensorName = sensorName;
        this.simHardware = (SimHardware) externalServices;
    }

    /**
     * getName method to get the name of the sensor
     * @return String containing the name of the sensor
     */

    public String getName() {
        return this.sensorName;
    }

    /**
     * getUnit method to get the unit of the sensor
     * @return String containing the unit of the sensor
     */

    public String getUnit() {
        return this.UNIT;
    }

    /**
     * getLog method to get the log of the sensor
     * @return ArrayList<String> containing the log of the sensor
     */

    public ArrayList<String> getLog() {
        ArrayList<String> logList = new ArrayList<>();
        for (Value<Integer> value : log) {
            logList.add(value.getValueAsString());
        }
        return logList;
    }

    /**
     * addValueToLog method to add a value to the log of the sensor
     * @param value Value<Integer> to store the value to be added to the log
     */

    private void addValueToLog(Value<Integer> value) {
        log.add(value);
    }

    /**
     * getReading method to get the reading of the sensor
     * @return Value<Integer> containing the reading of the sensor
     * @throws InstantiationException if unable to create value
     */

    public Value<Integer> getReading() throws InstantiationException {
        String simValue = this.simHardware.getValue();

        SolarIrradianceValue readingValue = new SolarIrradianceValue(simValue);

        addValueToLog(readingValue);

        return readingValue;
    }

    /**
     * getType method to get the type of the sensor
     * @return String containing the type of the sensor
     */

    public String getType() {
        return TYPE;
    }
}