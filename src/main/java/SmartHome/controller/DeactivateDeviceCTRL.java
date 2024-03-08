package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

import java.util.List;


public class DeactivateDeviceCTRL {
    private final House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private Room room;

    /**
     * Controller Constructor for Use Case: To deactivate a device. It should be possible to access its
     * configuration and activity log.
     * @param house House in scope
     */
    public DeactivateDeviceCTRL(House house) {
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
    }

    /**
     * Retrieves a manipulated list of RoomDTOs. The DTOs correspond to their related domain Room object
     * @return List containing RoomDTOs
     */
    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    /**
     * This method serves the purpose of obtaining the listOfDevices of a specific room, it is called after the user
     * obtaining the listOfRooms and choosing one.
     * @param roomName Room identification from which is required a list of devices
     * @return List containing DeviceDTOs
     */
    public List<DeviceDTO> getListOfDevices(String roomName){
       this.room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }

    /**
     * This method finds a Device in the selected Room, by matching its identification (device name) and then attempts
     * to deactivate it.
     * @param deviceName Identification of the Device to be deactivated
     * @return True in case Device is successfully deactivated
     */
    public boolean deactivateDevice(String deviceName){
        Device device = commonListOfDevices.getDeviceByName(deviceName,room);
        return device.deactivateDevice();
    }
}
