package SmartHomeDDD.domain.sensor;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.TemperatureValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;

import java.util.UUID;

public class TemperatureSensor implements Sensor, DomainEntity {
    private SensorNameVO name;
    private final DeviceIDVO deviceID;
    private final SensorTypeIDVO sensorTypeID;

    private final SensorIDVO temperatureSensorID;

    /**
     * Constructor for the TemperatureSensorTP class.
     * @param name Name of the sensor.
     * @param deviceID ID of the device to which the sensor is connected.
     * @param sensorTypeID ID of the sensor type.
     */
    public TemperatureSensor(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        if (!validateParameters(name, deviceID, sensorTypeID)) {
            throw new IllegalArgumentException("Invalid parameters.");
        }

        this.temperatureSensorID = new SensorIDVO(UUID.randomUUID());
        this.name = name;
        this.sensorTypeID = sensorTypeID;
        this.deviceID = deviceID;
    }

    /**
     * Validates the parameters of the constructor.
     * @param name Name of the sensor.
     * @param deviceID ID of the device to which the sensor is connected.
     * @param sensorTypeID ID of the sensor type.
     * @return True if the parameters are valid, false otherwise.
     */
    private boolean validateParameters(SensorNameVO name, DeviceIDVO deviceID, SensorTypeIDVO sensorTypeID) {
        return name != null && deviceID != null && sensorTypeID != null;
    }

    @Override
    public SensorIDVO getId() {
        return this.temperatureSensorID;
    }

    public SensorNameVO getSensorName() {
        return this.name;
    }

    public SensorTypeIDVO getSensorTypeID() {
        return this.sensorTypeID;
    }

    public DeviceIDVO getDeviceID() {
        return this.deviceID;
    }

    /**
     * Gets the reading from the hardware.
     * @param simHardware Hardware from which to get the reading.
     * @return Value of the reading.
     * @throws InstantiationException Exception thrown when the reading is invalid.
     */
    public Value<Double> getReading(SimHardware simHardware) throws InstantiationException {
        if (simHardware == null) {
            throw new IllegalArgumentException("Invalid parameters.");
        }
        String value = simHardware.getValue();
        return new TemperatureValue(value);
    }
}