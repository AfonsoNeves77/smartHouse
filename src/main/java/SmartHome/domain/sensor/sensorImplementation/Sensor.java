package SmartHome.domain.sensor.sensorImplementation;


import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;

import java.util.ArrayList;

/**
 * Represents a generic sensor interface.
 */

public interface Sensor {
    /**
     * @return Sensor name
     */
    public String getName();

    /**
     * @return Sensor unit
     */
    public String getUnit();

    /**
     * @return Sensor log
     */
    public ArrayList<String> getLog();

    /**
     * Receives a primitive and converts it dynamically, depending on context, to an implementation of Value.
     * @param simHardware Simulates the connection to a piece of hardware/external api that physically measures the reading
     * @return If the reading provided by simHardware is within expected parameters and of the pre-defined Value type,
     * it gets converted into a value implementation of the sensor in scope
     * @throws InstantiationException If reading is outside a pre-defined interval or does not match the expected input.
     */
   Value<?> getReading(SimHardware simHardware) throws InstantiationException;
}
