package smarthome.mapper;


import smarthome.domain.sensor.Sensor;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;
import smarthome.mapper.dto.SensorDTO;

import java.util.UUID;


public class SensorMapper {
    private static final String INVALID_DTO_ERROR = "Invalid DTO, Value Object cannot be created";

    /**
     * Creates a SensorNameVO from a SensorDTO.
     * @param sensorDTO the SensorDTO from which to create the SensorNameVO.
     * @return a SensorNameVO with the same name as the SensorDTO.
     * @throws IllegalArgumentException if the SensorDTO is null.
     */
    public static SensorNameVO createSensorNameVO(SensorDTO sensorDTO) {
        if (sensorDTO == null)
            throw new IllegalArgumentException(INVALID_DTO_ERROR);

        String sensorName = sensorDTO.getSensorName();
        return new SensorNameVO(sensorName);
    }

    /**
     * Maps a Sensor domain object to a SensorDTO.
     * @param sensor the Sensor to map to a SensorDTO.
     * @return a SensorDTO with the same attributes as the Sensor.
     * @throws IllegalArgumentException if the Sensor is null.
     */
    public static SensorDTO domainToDTO(Sensor sensor) {
        if (sensor == null) {
            throw new IllegalArgumentException("Invalid Sensor, DTO cannot be created");
        }
        return SensorDTO.builder()
                .sensorID(sensor.getId().getID())
                .sensorName(sensor.getSensorName().getValue())
                .deviceID(sensor.getDeviceID().getID())
                .sensorTypeID(sensor.getSensorTypeID().getID())
                .build();
    }

    /**
     * Creates a DeviceIDVO from a SensorDTO.
     * @param sensorDTO the SensorDTO from which to create the DeviceIDVO.
     * @return a DeviceIDVO with the same ID as the device ID of the SensorDTO.
     * @throws IllegalArgumentException if the SensorDTO is null or if the device ID of the SensorDTO is not a valid UUID.
     */
    public static DeviceIDVO createDeviceID(SensorDTO sensorDTO) {
        if (sensorDTO == null) {
            throw new IllegalArgumentException(INVALID_DTO_ERROR);
        }

        String deviceID = sensorDTO.getDeviceID();
        if (!isDeviceIDValid(deviceID)) {
            throw new IllegalArgumentException("Invalid device ID");
        }
        UUID id = UUID.fromString(deviceID);
        return new DeviceIDVO(id);
    }

    /**
     * Creates a SensorTypeIDVO from a SensorDTO.
     * @param sensorDTO the SensorDTO from which to create the SensorTypeIDVO.
     * @return a SensorTypeIDVO with the same ID as the sensor type ID of the SensorDTO.
     * @throws IllegalArgumentException if the SensorDTO is null.
     */
    public static SensorTypeIDVO createSensorTypeIDVO(SensorDTO sensorDTO) {
        if (sensorDTO == null) {
            throw new IllegalArgumentException(INVALID_DTO_ERROR);
        }

        String sensorTypeID = sensorDTO.getSensorTypeID();
        return new SensorTypeIDVO(sensorTypeID);
    }

    /**
     * Checks if a device ID is a valid UUID.
     * @param deviceID the device ID to check.
     * @return true if the device ID is a valid UUID, false otherwise.
     */
    private static boolean isDeviceIDValid(String deviceID) {
        try {
            UUID.fromString(deviceID);
        } catch (NullPointerException | IllegalArgumentException e) {
            return false;
        }
        return true;
    }
}