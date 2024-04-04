package SmartHomeDDD.controller;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.mapper.DeviceMapper;
import SmartHomeDDD.mapper.RoomMapper;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

public class AddDeviceToRoomCTRL {
    private final RoomService roomService;
    private final DeviceService deviceService;

    /**
     * Constructor for AddDeviceCTRL, it receives a room and device services, encapsulating them.
     * @param roomService Room Service
     * @param deviceService Device Service
     */
    public AddDeviceToRoomCTRL(RoomService roomService, DeviceService deviceService){
        if(!validServices(roomService, deviceService))
            throw new IllegalArgumentException("Invalid services provided");
        this.deviceService = deviceService;
        this.roomService = roomService;
    }

    /**
     * This method is responsible for adding a Device. It takes in a roomDTO and deviceDTO as parameters. Initially, it
     * converts the roomDTO into a RoomIDVO object using a room mapper. Subsequently, it invokes the private method
     * createDevices, which commences by extracting all the information from the DeviceDTO, converting it to Value Object
     * (VO) objects, and then proceeding to request the device creation from the device service. After receiving a new
     * Device instance (might be null), it utilizes the device service once more to save the Device. The method returns true if no issues
     * arise, and false otherwise.
     * @param roomDTO RoomDTO object
     * @param deviceDTO DeviceDTO object
     * @return True if device is successfully added, false otherwise.
     */
    public boolean addDeviceToRoom(RoomDTO roomDTO, DeviceDTO deviceDTO){
        try{
            RoomIDVO roomIDVO = RoomMapper.createRoomIDVO(roomDTO);
            Device newDevice = createDevice(roomIDVO, deviceDTO);
            return this.deviceService.saveDevice(newDevice);
        } catch (NullPointerException | IllegalArgumentException | InstantiationException e) {
            return false;
        }
    }

    /**
     * This method employs the DeviceMapper to generate Value Objects (VOs) from the information contained in the DeviceDTO.
     * Subsequently, it calls the device service to create a Device object.
     * @param roomIDVO RoomIDVO object
     * @param deviceDTO DeviceDTO object
     * @return Propagates the return from the device service.
     */
    private Device createDevice(RoomIDVO roomIDVO, DeviceDTO deviceDTO) {
        if (!roomService.isPresent(roomIDVO)){
            throw new IllegalArgumentException("Room ID does not exist");
        }
        DeviceNameVO deviceNameVO = DeviceMapper.createDeviceName(deviceDTO);
        DeviceModelVO deviceModelVO = DeviceMapper.createDeviceModel(deviceDTO);
        return deviceService.createDevice(deviceNameVO, deviceModelVO, roomIDVO);
    }

    /**
     * Verifies whether the provided services are valid (not null).
     * @param roomService Room Service
     * @param deviceService Device Service
     * @return True if both services are valid, false otherwise.
     */
    private boolean validServices(RoomService roomService, DeviceService deviceService){
        return (roomService != null && deviceService != null);
    }
}
