package SmartHome.controller;

import SmartHome.domain.House;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.room.Room;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class AddDeviceToRoomCTRL {

    private House myHouse;
    private final CommonListOfRooms commonListOfRooms;
    private FactoryDevice factoryDevice;

    /**
     * Controller Constructor for Use Case: Add a new device to a room
     *
     * @param myHouse
     */
    public AddDeviceToRoomCTRL(House myHouse, FactoryDevice factoryDevice) {
        this.myHouse = myHouse;
        this.factoryDevice = factoryDevice;
        this.commonListOfRooms = new CommonListOfRooms(myHouse);
    }

    public List<RoomDTO> getListOfRooms() {
        return commonListOfRooms.getListOfRooms();
    }

    public boolean addDeviceToRoom(String deviceName, String deviceModel, String roomName) {
        Room room = commonListOfRooms.getRoomByName(roomName);
        return room.addDevice(deviceName, deviceModel, factoryDevice);
    }

}
