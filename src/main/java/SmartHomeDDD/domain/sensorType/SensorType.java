package SmartHomeDDD.domain.sensorType;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.DomainID;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;

/**
 * Represents a Type of Sensor.
 * A Sensor type is characterized by its id and unit.
 * The constructor of this class validates the parameters and throws an IllegalArgumentException
 * if any of them are null.
 */
public class SensorType implements DomainEntity {

    private final SensorTypeIDVO sensorTypeID;

    private final UnitVO sensorUnit;

    /**
     * Constructs a SensorType with the specified parameters, all of them being Value-Objects.
     *
     * @param sensorTypeID The id of the sensor type.
     * @param sensorUnit The unit of the sensor type.
     * @throws IllegalArgumentException If any of the parameters are null.
     */

    public SensorType (SensorTypeIDVO sensorTypeID, UnitVO sensorUnit){
        if (!areParamsValid(sensorTypeID, sensorUnit)){
            throw new IllegalArgumentException("Invalid Parameters");
        } else {
            this.sensorTypeID = sensorTypeID;
            this.sensorUnit = sensorUnit;
        }
    }

    private boolean areParamsValid(SensorTypeIDVO sensorTypeID, UnitVO sensorUnit){
        if (sensorTypeID == null || sensorUnit == null){
            return false;
        } else {
            return true;
        }
    }

    /**
     * Getter method for the sensor type id.
     * @return The sensor type id.
     */

    @Override
    public DomainID getId() {
        return sensorTypeID;
    }

    /**
     * Getter method for the sensor type unit.
     * @return  The sensor type unit.
     */
    public UnitVO getUnit(){
        return sensorUnit;
    }
}
