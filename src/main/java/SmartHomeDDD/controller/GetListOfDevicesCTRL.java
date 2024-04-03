package SmartHomeDDD.controller;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.mapper.RoomMapper;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GetListOfDevicesCTRL {

    private final RoomService roomService;
    private final DeviceService deviceService;

    /**
     * Constructor for GetListOfDevicesCTRL, injected with RoomService and DeviceService.
     * @param roomService
     * @param deviceService
     */
    public GetListOfDevicesCTRL(RoomService roomService, DeviceService deviceService) {
        if(areParamsNull(roomService, deviceService)){
            throw new IllegalArgumentException("Invalid parameters.");
        }
        this.roomService = roomService;
        this.deviceService = deviceService;
    }

    /**
     * Method to get the list of devices in a room.
     * It receives a Room DTO, then creates a RoomIDVO from it.
     * If the room is not present, it returns an empty list.
     * Otherwise, it gets the list of devices in the room and maps them to DeviceDTO.
     * @param roomDTO
     * @return List of DeviceDTO
     * @throws InstantiationException
     */
    public List<DeviceDTO> getListOfDevices(RoomDTO roomDTO) throws InstantiationException {
        RoomIDVO roomIDVO = RoomMapper.createRoomIDVO(roomDTO);
        if(!roomService.isPresent(roomIDVO)){
            return new ArrayList<>();
        }
        List<Device> deviceList = deviceService.getListOfDevicesInARoom(roomIDVO);
        return DeviceMapper.domainToDTO(deviceList);
    }

    /**
     * This method checks if any of the parameters are null.
     * @param params
     * @return
     */
    private boolean areParamsNull(Object... params){
        for (Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }
}
