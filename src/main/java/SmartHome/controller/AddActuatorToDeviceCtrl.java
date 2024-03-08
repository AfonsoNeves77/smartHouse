package SmartHome.controller;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.House;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.device.Device;
import SmartHome.domain.room.Room;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.RoomDTO;

import java.util.List;

public class AddActuatorToDeviceCtrl {
    private House house;
    private final CommonListOfRooms commonListOfRooms;
    private final CommonListOfDevices commonListOfDevices;
    private final ActuatorCatalogue catalogue;
    private Room room;

    /**
     *  This constructor add an actuator to a device in the house, using also the actuatorCatalogue and dynamically
     *  creating a commonListOfRooms and a commonListOfDevices.
     * @param house House
     * @param catalogue ActuatorCatalogue
     */


    public AddActuatorToDeviceCtrl(House house, ActuatorCatalogue catalogue) {
        this.house = house;
        this.commonListOfRooms = new CommonListOfRooms(house);
        this.commonListOfDevices = new CommonListOfDevices(house);
        this.catalogue = catalogue;
    }

    /**
     * This method serves the purpose of delivering a listOfRooms to the user, so he can choose one.
     * @return ListOfRoomDTO
     */
    public List<RoomDTO> getListOfRooms() {
        return commonListOfRooms.getListOfRooms();
    }

    /**
     *  This method serves the purpose of obtaining the listOfDevices of a specific room, it is called after the user
     *  obtaining the listOfRooms and choosing one.
     * @param roomName The name of the chosen room.
     * @return ListOfDevicesDTO
     */
    public List<DeviceDTO> getListOfDevices(String roomName) {
        this.room = commonListOfRooms.getRoomByName(roomName);
        return commonListOfDevices.getListOfDevices(room);
    }

    /**
     * This method requests the listOfActuatorTypes from the catalogue (the catalogue obtains it during instantiation from config.properties)
     * @return A listOfIntantiableActuators
     */

    public List<String> getListOfActuatorTypes(){
        return this.catalogue.getListOfInstantiableActuators();
    }

    /**
     * This method adds anc actuator to a device, to do this ir requires that the user pre-selects a device (deviceName),
     * also the information relevant to creates an actuator(actuatorName and actuatorType, which helps the catalogue identify
     * that that specific type is available). It also requires a simHardwareAct which is a simulation of the connection with
     * an external service.
     * @param actuatorName name of the actuator
     * @param actuatorType type of the actuator
     * @param deviceName the device name
     * @param simHardware the simulation of a conection to an external service
     * @return a boolean that informs the user if the operation was successful or not
     */
    public boolean addActuatorToDevice(String actuatorName, String actuatorType, String deviceName, SimHardwareAct simHardware) {
        Device device = commonListOfDevices.getDeviceByName(deviceName, room);
        return device.addActuator(actuatorName, actuatorType, catalogue, simHardware);
    }
}
