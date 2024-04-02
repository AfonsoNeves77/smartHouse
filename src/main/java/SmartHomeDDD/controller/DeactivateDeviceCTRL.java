package SmartHomeDDD.controller;

import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;

/**
 * The DeactivateDeviceCTRL class represents a controller responsible for deactivating devices.
 * It interacts with a DeviceService to perform device deactivation operations.
 */

public class DeactivateDeviceCTRL {

    private DeviceService deviceService;


    /**
     * Constructs a new DeactivateDeviceCTRL object with the specified DeviceService.

     * @param deviceService The DeviceService to be used by the controller.
     * @throws IllegalArgumentException if the provided DeviceService is null.
     */

    public DeactivateDeviceCTRL(DeviceService deviceService) {
        if(isNull(deviceService)){
            throw new IllegalArgumentException("Invalid service");
        }
        this.deviceService = deviceService;
    }

    /**
     * Deactivates the device specified by the provided DeviceDTO.
     * This method returns false if:
     *   - The provided DeviceDTO is null,
     *   - An IllegalArgumentException is thrown during the creation of DeviceIDVO from the DTO id information,
     *   - Device deactivation operation fails.
     * Otherwise, it returns true.
     *
     * @param deviceDTO The DeviceDTO representing the device to be deactivated.
     * @return true if the device deactivation was successful, false otherwise.
     */

    public boolean deactivateDevice(DeviceDTO deviceDTO){
        if(isNull(deviceDTO)){
            return false;
        }
        try {
            DeviceIDVO deviceIDVO = DeviceMapper.createDeviceID(deviceDTO);;
            return deviceService.deactivateDevice(deviceIDVO);
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * Checks if the passed parameter to the method is non-null.
     * This method ensures that the provided parameter is not null before proceeding with further operations.
     * While this function may not be considered type-safe, it is always used after ensuring type safety
     * by type-checking the variables of the calling method (compile-time type checking).
     *
     * @param object The parameters to be checked for nullity.
     * @return true if the parameter is non-null, false otherwise.
     */

    private boolean isNull(Object object){
        return object == null;
    }
}
