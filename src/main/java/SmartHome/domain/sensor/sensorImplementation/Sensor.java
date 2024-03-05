package SmartHome.domain.sensor.sensorImplementation;


import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

import java.util.ArrayList;

/**
 * Represents a generic sensor interface.
 */

public interface Sensor {
    /**
     * @return Sensor name
     */
    String getName();

    /**
     * @return Sensor unit
     */
    String getUnit();

    /**
     * @return Sensor log
     */
    ArrayList<String> getLog();
}
