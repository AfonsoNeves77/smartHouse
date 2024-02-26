package SmartHome.controller;

import SmartHome.domain.Catalogue;
import SmartHome.domain.Device;
import SmartHome.domain.House;
import SmartHome.domain.Room;
import SmartHome.domain.Sensor;
import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddSensorToDeviceController
{
    private final House _house;
    private final Catalogue _catalogue;
    private Map<RoomDTO, Room> _roomsDTOAndRooms = new HashMap<>();

    public AddSensorToDeviceController( House house, Catalogue catalogue ) throws InstantiationException {
        if( !isValidConstructorArguments(house, catalogue) )
            throw( new InstantiationException("Invalid arguments"));

        _house = house;
        _catalogue = catalogue;
    }

    private boolean isValidConstructorArguments( House house, Catalogue catalogue )
    {
        return house != null && catalogue != null;

        // implement here the rest of validations
    }

    public List<RoomDTO> getRooms( )
    {
        List<Room> rooms = _house.getRooms();

        _roomsDTOAndRooms = RoomMapper.Domain2DTO( rooms );

        return _roomsDTOAndRooms.keySet().stream().toList();
    }

    public List<Device> getDevicesFromRoom( RoomDTO roomDTO )
    {
        Room room = _roomsDTOAndRooms.get( roomDTO );
        return room.getDevices();
    }

    public List<String> getSensorsModels()
    {
        return _catalogue.getSensorModels();
    }

    public Sensor addSensorToDevice(Device device, String strSensorModel )
    {
        Sensor sensor = device.addSensor( strSensorModel, this._catalogue );
        return sensor;
    }
}