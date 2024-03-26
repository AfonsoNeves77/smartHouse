package SmartHomeDDD.domain.sensor;


import SmartHome.domain.sensor.sensorImplementation.sensorValues.SwitchValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;

import java.util.UUID;

public class SwitchSensor implements Sensor, DomainEntity {

    private SensorNameVO nameVO;
    private final DeviceIDVO deviceIDVO;
    private final SensorTypeIDVO sensorTypeIDVO;
    private final SensorIDVO sensorIDVO;

    /**
     * Constructor for SwitchSensor, it validates the receiving VOs and creates a new SensorIDVO utilizing the UUID
     * library. Received VOs and SensorID are encapsulated within the object.
     *
     * @param nameVO         Sensor name
     * @param deviceIDVO     Device ID
     * @param sensorTypeIDVO SensorType ID
     */
    public SwitchSensor(SensorNameVO nameVO, DeviceIDVO deviceIDVO, SensorTypeIDVO sensorTypeIDVO) {
        if (nameVO == null || deviceIDVO == null || sensorTypeIDVO == null)
            throw new IllegalArgumentException("Invalid parameters.");

        this.sensorIDVO = new SensorIDVO(UUID.randomUUID());
        this.nameVO = nameVO;
        this.sensorTypeIDVO = sensorTypeIDVO;
        this.deviceIDVO = deviceIDVO;
    }

    /**
     * Simple getter method, implemented from EntityDomain interface
     *
     * @return The encapsulated VO for SensorID;
     */
    @Override
    public SensorIDVO getId() {
        return sensorIDVO;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for Name;
     */
    public SensorNameVO getSensorName() {
        return nameVO;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for SensorTypeID;
     */
    public SensorTypeIDVO getSensorTypeID() {
        return sensorTypeIDVO;
    }

    /**
     * Simple getter method
     *
     * @return The encapsulated VO for DeviceID;
     */
    public DeviceIDVO getDeviceID() {
        return deviceIDVO;
    }

    /**
     * Obtains the switch value. It receives a SimHardware external service (validating it)
     *
     * @param simHardware SimHardware external service
     * @return SwitchValue object
     * @throws InstantiationException
     */
    public Value<String> getReading(SimHardware simHardware) throws InstantiationException {
        if (simHardware == null) throw new IllegalArgumentException("Invalid external service");
        String simValue = simHardware.getValue();
        return new SwitchValue(simValue);
    }
}
