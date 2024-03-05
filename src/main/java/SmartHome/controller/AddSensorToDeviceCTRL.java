package SmartHome.controller;

import java.util.List;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

public class AddSensorToDeviceCTRL {
    private House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private final SensorCatalogue catalogue;
    private Room room;

    public AddSensorToDeviceCTRL(House house, SensorCatalogue catalogue){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
        this.catalogue = catalogue;
    }

    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    public List<String> getListOfSensorTypes(){
        return this.catalogue.getListOfInstantiableSensors();
    }

    public List<DeviceDTO> getListOfDevices(String roomName){
        this.room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }

    public boolean addSensorToDevice(String sensorName, String sensorType, String deviceName, SimHardware simHardware){
        Device device = commonListOfDevices.getDeviceByName(deviceName,room);
        return device.addSensor(sensorName, sensorType, catalogue, simHardware);
    }
}
