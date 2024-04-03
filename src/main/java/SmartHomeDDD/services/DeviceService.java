package SmartHomeDDD.services;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Service class responsible for managing devices in the smart home domain (create,save,checking for activation status and retrieving a list of devices in a room).
 */

public class DeviceService {

    private DeviceFactory factoryDevice;
    private DeviceRepository deviceRepository;

    /**
     * Constructs a new DeviceService instance.
     *
     * @param factoryDevice   The factory responsible for creating devices.
     * @param deviceRepository The repository storing and retrieving devices.
     * @throws IllegalArgumentException if either factoryDevice or deviceRepository is null.
     */

    public DeviceService(DeviceFactory factoryDevice, DeviceRepository deviceRepository){
        if(!validParams(factoryDevice,deviceRepository)){
            throw new IllegalArgumentException("Invalid parameters");
        }
        this.factoryDevice = factoryDevice;
        this.deviceRepository = deviceRepository;
    }

    /**
     * Creates a new device with the provided parameters.
     *
     * @param deviceNameVO The name of the device.
     * @param deviceModelVO The model of the device.
     * @param roomIDVO The ID of the room where the device will be located.
     * @return The created device instance, or null if the parameters are invalid.
     */

    public Device createDevice(DeviceNameVO deviceNameVO, DeviceModelVO deviceModelVO, RoomIDVO roomIDVO){
        if(validParams(deviceNameVO, deviceModelVO,roomIDVO)){
            return factoryDevice.createDevice(deviceNameVO,deviceModelVO,roomIDVO);
        }
        return null;
    }

    /**
     * Saves the given device to the repository.
     *
     * @param device The device to be saved.
     * @return true if the device was successfully saved, false otherwise.
     */

    public boolean saveDevice(Device device){
        if(validParams(device)){
            return this.deviceRepository.save(device);
        }
        return false;
    }

    /**
     * Deactivates the device with the given ID.
     *
     * @param deviceIDVO The ID of the device to be deactivated.
     * @return true if the given device ID corresponds to any of the existing devices
     * if it´s status is active and was successfully deactivated, false otherwise.
     */

    public boolean deactivateDevice(DeviceIDVO deviceIDVO){
        Device device =  this.deviceRepository.findById(deviceIDVO);
        return device != null && isActive(device) && device.deactivateDevice();
    }

    /**
     * Retrieves the list of devices located in the specified room.
     *
     * @param roomIDVO The ID of the room.
     * @return The list of devices in the specified room.
     */

    public List<Device> getListOfDevicesInARoom(RoomIDVO roomIDVO){
        return this.deviceRepository.findByRoomID(roomIDVO);
    }

    /**
     * Deactivates the device with the given ID.
     *
     * @param deviceIDVO The ID of the device to be deactivated.
     * @return true if the given device ID corresponds to any of the existing devices
     * and if it´s status is active, false otherwise.
     */

    public boolean isActive(DeviceIDVO deviceIDVO){
       Device device =  this.deviceRepository.findById(deviceIDVO);
        return device != null && isActive(device);
    }

    /**
     * Checks if the given device is active. It queries the given device for it´s state
     * and returns it´s value.
     *
     * @param device The device to check for activity.
     * @return true if the device is active, false otherwise.
     */

    private boolean isActive(Device device){
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        return deviceStatusVO.getValue();
    }

    /**
     * Checks if all parameters passed to the method are non-null.
     * This method ensures that all parameters provided to it are not null before proceeding with further operations.
     * While this function may not be considered type-safe, it is always used after ensuring type safety
     * by type-checking the variables of the calling method (compiler).
     *
     * @param params The parameters to be checked for nullity.
     * @return true if all parameters are non-null, false otherwise.
     */

    private boolean validParams (Object... params){
        for (Object param : params){
            if (param == null){
                return false;
            }
        }
        return true;
    }

    /**
     * This method receives a map with string, List<DeviceIDVO> and replaces all DeviceIDVOs with Device objects.
     * @param map string, List<Device>
     * @return
     */
        public LinkedHashMap<String, List<Device>> getDevices (Map<String, List<DeviceIDVO>> map){
        LinkedHashMap<String, List<Device>> newMap = new LinkedHashMap<>();
        for (String key : map.keySet()){
            List<DeviceIDVO> idList = map.get(key);
            List<Device> deviceList = new ArrayList<>();
            for (DeviceIDVO id : idList){
                Device device = this.deviceRepository.findById(id);
                deviceList.add(device);
            }
            newMap.put(key,deviceList);
        }
        return newMap;
    }


}
