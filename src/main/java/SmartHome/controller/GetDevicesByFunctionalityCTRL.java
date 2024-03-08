package SmartHome.controller;
import SmartHome.domain.House;
import SmartHome.domain.device.Device;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceDTOMapper;

import java.util.ArrayList;
import java.util.Map;

public class GetDevicesByFunctionalityCTRL {
    private House house;

    /**
     * Controller Constructor for Use Case: List devices by functionality. Include device location.
     * @param house House to be considered
     */
    public GetDevicesByFunctionalityCTRL(House house) {
        this.house = house;
    }

    /**
     * Requests House to retrieve its functionalities in a Map format, containing functionalities as Keys and lists
     * of Devices as Values.
     * Forwards the Map to DeviceDTOMapper Class to convert each Value in a list of DeviceDTOs.
     * @return A Map with functionalities as Keys and deviceDTO Lists as Values.
     */
    public Map<String, ArrayList<DeviceDTO>> getDevicesByFunctionality(){
        Map<String, ArrayList<Device>> devicesPerFunctionality = house.getHouseFunctionalities();
        return DeviceDTOMapper.domainMapToDTO(devicesPerFunctionality);
    }
}
