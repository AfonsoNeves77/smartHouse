package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.room.Room;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class AddDeviceToRoomCTRL {

    private House house;
    private final CommonListOfRooms commonListOfRooms;
    private FactoryDevice factoryDevice;

    /**
     * Controller Constructor for Use Case: Add a new device to a room
     * @param house House in use
     * @param factoryDevice Factory Device to be used to create new instances of Device
     */
    public AddDeviceToRoomCTRL(House house, FactoryDevice factoryDevice) {
        this.house = house;
        this.factoryDevice = factoryDevice;
        this.commonListOfRooms = new CommonListOfRooms(house);
    }

    /**
     * Retrieves a manipulated list of RoomDTOs. The DTOs correspond to their related domain Room object
     * @return List containing RoomDTOs
     */
    public List<RoomDTO> getListOfRooms() {
        return commonListOfRooms.getListOfRooms();
    }

    /**
     * Adds a Device to a selected Room, retrieving the final operation result
     * @param deviceName Device name
     * @param deviceModel device model
     * @param roomName Room name which indicates the location of the Device
     * @return True in case Device is successfully added
     */
    public boolean addDeviceToRoom(String deviceName, String deviceModel, String roomName) {
        Room room = commonListOfRooms.getRoomByName(roomName);
        return room.addDevice(deviceName, deviceModel, factoryDevice);
    }

}
