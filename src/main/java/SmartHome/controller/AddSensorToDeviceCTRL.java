package SmartHome.controller;

import java.util.List;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SimHardware;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

public class AddSensorToDeviceCTRL {
    private House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private final SensorCatalogue catalogue;
    private Room room;


    /**
     * Controller Constructor for Use Case: Add a sensor to an existing device in a room
     * @param house House in use
     * @param catalogue Sensor catalogue to use
     */
    public AddSensorToDeviceCTRL(House house, SensorCatalogue catalogue){
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
        this.catalogue = catalogue;
    }

    /**
     * Retrieves a manipulated list of RoomDTOs. The DTOs correspond to their related domain Room object
     * @return List containing RoomDTOs
     */
    public List<RoomDTO> getListOfRooms(){
        return commonListOfRooms.getListOfRooms();
    }

    /**
     * This method serves the purpose of obtaining the list of available sensors.
     * @return List of available sensors
     */
    public List<String> getListOfSensorTypes(){
        return this.catalogue.getListOfInstantiableSensors();
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
     *
     * @param sensorName Sensor name
     * @param sensorType Sensor type
     * @param deviceName Device name to indicate where sensor is going to be added
     * @param externalServices Simulation of a connection to an external service were sensor readings are get from
     * @return True in case Sensor is successfully added to the Device
     */
    public boolean addSensorToDevice(String sensorName, String sensorType, String deviceName, ExternalServices externalServices){
        Device device = commonListOfDevices.getDeviceByName(deviceName,room);
        return device.addSensor(sensorName, sensorType, catalogue, externalServices);
    }
}
