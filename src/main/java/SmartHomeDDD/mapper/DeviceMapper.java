package SmartHomeDDD.mapper;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;

import java.util.*;

public class DeviceMapper {

    /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device name is valid, meaning not null, empty or blank.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceNameVO createDeviceName(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException("DeviceDTO cannot be null.");
        }
        String deviceName = deviceDTO.getDeviceName();
        if (!isDeviceNameValid(deviceName)) {
            throw new IllegalArgumentException("Invalid device name.");
        }
        return new DeviceNameVO(deviceName);
    }

   /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device model is valid, meaning not null, empty or blank.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceModelVO createDeviceModel(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException("DeviceDTO cannot be null.");
        }
        String deviceModel = deviceDTO.getDeviceModel();
        if (!isDeviceModelValid(deviceModel)) {
            throw new IllegalArgumentException("Invalid device model.");
        }
        return new DeviceModelVO(deviceModel);
    }

    /**
     * Creates a new Device object from the provided DeviceDTO object.
     * Validates the provided DeviceDTO object to ensure it is not null
     * Then ensures the device ID is valid, meaning not null, empty or blank.
     * Then, instantiates a new UUID object from the provided device ID string.
     * @param deviceDTO the DeviceDTO object to be converted to a Device object
     * @return a new Device object created from the provided DeviceDTO object
     * @throws IllegalArgumentException if the provided DeviceDTO object is null
     */
    public static DeviceIDVO createDeviceID(DeviceDTO deviceDTO) {
        if (deviceDTO == null) {
            throw new IllegalArgumentException("DeviceDTO cannot be null.");
        }
        String deviceID = deviceDTO.getDeviceID();
        if (!isDeviceIDValid(deviceID)) {
            throw new IllegalArgumentException("Invalid device ID.");
        }
        UUID id = UUID.fromString(deviceID);
        return new DeviceIDVO(id);
    }

    /**
     * Converts a list of Device objects to a list of DeviceDTO objects.
     * First, iterates through the provided list of Device objects and extracts the device name, model, room ID, status, and ID.
     * Then, creates a new DeviceDTO object from the extracted information and adds it to the list of DeviceDTO objects.
     * @param deviceList the list of Device objects to be converted to DeviceDTO objects
     * @return a list of DeviceDTO objects created from the provided list of Device objects
     */
    public static List<DeviceDTO> domainToDTO(List<Device> deviceList) {
        List<DeviceDTO> deviceDTOList = new ArrayList<>();
        for (Device device : deviceList) {
            String name = device.getDeviceName().getValue();
            String model = device.getDeviceModel().getValue();
            String roomID = device.getRoomID().getID();
            String status = device.getDeviceStatus().getValue().toString();
            String deviceID = device.getId().getID();

            DeviceDTO deviceDTO = new DeviceDTO(deviceID, name, model, status, roomID);

            deviceDTOList.add(deviceDTO);
        }
        return deviceDTOList;
    }

    public static Map<String,List<DeviceDTO>> domainToDTO(Map<String, List<Device>> map) {
        LinkedHashMap<String,List<DeviceDTO>> newMap = new LinkedHashMap<>();
        for (String key : map.keySet()) {
            List<Device> deviceList = map.get(key);
            List<DeviceDTO> deviceDTOList = new ArrayList<>();
            for (Device device : deviceList) {
                String name = device.getDeviceName().getValue();
                String model = device.getDeviceModel().getValue();
                String roomID = device.getRoomID().getID();
                String status = device.getDeviceStatus().getValue().toString();
                String deviceID = device.getId().getID();
                DeviceDTO deviceDTO = new DeviceDTO(deviceID, name, model, status, roomID);
                deviceDTOList.add(deviceDTO);
            }
            newMap.put(key, deviceDTOList);
        }
        return newMap;
    }

    /**
     * Validates the device name.
     * @param deviceName the device name to be validated
     * @return true if the device name is valid, false otherwise
     */
    private static boolean isDeviceNameValid(String deviceName) {
        if (deviceName == null || deviceName.isEmpty()) {
            return false;
        }
        return !deviceName.trim().isEmpty();
    }

    /**
     * Validates the device model.
     * @param deviceModel the device model to be validated
     * @return true if the device model is valid, false otherwise
     */
    private static boolean isDeviceModelValid(String deviceModel) {
        if (deviceModel == null || deviceModel.isEmpty()) {
            return false;
        }
        return !deviceModel.trim().isEmpty();
    }

    /**
     * Validates the device ID.
     * @param deviceID the device ID to be validated
     * @return true if the device ID is valid, false otherwise
     */
    private static boolean isDeviceIDValid(String deviceID) {
        if (deviceID == null || deviceID.isEmpty()) {
            return false;
        }
        return !deviceID.trim().isEmpty();
    }


}
