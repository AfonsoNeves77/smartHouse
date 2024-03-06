package SmartHome.controller;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class AddActuatorToDeviceCtrl {
    private House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private final ActuatorCatalogue catalogue;
    private Room room;

    public AddActuatorToDeviceCtrl(House house, ActuatorCatalogue catalogue){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
        this.catalogue = catalogue;
    }

    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    public List<String> getListOfActuatorTypes(){
        return this.catalogue.getListOfInstantiableActuators();
    }

    public List<DeviceDTO> getListOfDevices(String roomName){
        this.room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }

    public boolean addActuatorToDevice(String actuatorName, String actuatorType, String deviceName, SimHardwareAct simHardware){
        Device device = commonListOfDevices.getDeviceByName(deviceName,room);
        return device.addActuator(actuatorName, actuatorType, catalogue, simHardware);
    }
}
