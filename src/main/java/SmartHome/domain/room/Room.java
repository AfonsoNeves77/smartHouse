package SmartHome.domain.room;


import SmartHome.domain.device.Device;
import SmartHome.domain.device.FactoryDevice;
import SmartHome.domain.device.ListOfDevices;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Room {
    private String roomName;
    private int houseFloor;
    private RoomDimensions dimensions;
    private ListOfDevices deviceList = new ListOfDevices();

    private Map<String,ArrayList<Device>> roomFunctionalities = new HashMap<>();

    /**
     * Room Constructor
     * @param roomName Name of the room
     * @param houseFloor Floor number
     * @throws InstantiationException if room name is not valid
     */
    public Room(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight) {
        if(roomName == null || roomName.trim().isEmpty()){
            throw new IllegalArgumentException("Please insert valid room name.");
        }
        try {
            this.dimensions = createRoomDimensions(roomWidth,roomLength,roomHeight);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please insert valid room dimensions.");
        }
        this.roomName = roomName;
        this.houseFloor = houseFloor;
    }


    /**
     * Sets room dimensions to the Room
     * @param roomWidth Room width
     * @param roomLength Room length
     * @param roomHeight Room Height
     * @throws InstantiationException In case room dimensions are not valid
     */
    private RoomDimensions createRoomDimensions(double roomWidth, double roomLength, double roomHeight) {
        return new RoomDimensions(roomWidth,roomLength,roomHeight);
    }

    public double[] getDimensions() {
        return extractEachDimension();
    }

    /**
     * Extracts room dimensions from RoomDimensions object
     * @return Array with room dimension values
     */
    private double[] extractEachDimension(){
        double[] roomDimensions = new double[3];
        roomDimensions[0] = dimensions.getRoomWidth();
        roomDimensions[1] = dimensions.getRoomLength();
        roomDimensions[2] = dimensions.getRoomHeight();
        return roomDimensions;
    }

    public boolean addDevice(String deviceName, String deviceModel, FactoryDevice factoryDevice) {
        String deviceLocation = this.roomName;
        return deviceList.addDeviceToList(deviceName, deviceModel, deviceLocation, factoryDevice);
    }

    private void updateRoomFunctionalities(){
        List<Device> list = this.deviceList.getDeviceList();
        for(Device device : list){
            List<String> functionList = device.getDeviceFunctionalities();
            for(String function : functionList) {
                ArrayList<Device> deviceList = roomFunctionalities.get(function);
                if (deviceList == null) {
                    deviceList = new ArrayList<>();
                    deviceList.add(device);
                    roomFunctionalities.put(function, deviceList);
                } else {
                    deviceList.add(device);
                }
            }
        }
    }

    /**
     * @return Room name
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * @return House floor of the Room
     */
    public int getHouseFloor() {
        return houseFloor;
    }


    /**
     * @return Object device list
     */
    public List<Device> getListOfDevices() {
        return deviceList.getDeviceList();
    }

    public Map<String, ArrayList<Device>> getRoomFunctionalities(){
        updateRoomFunctionalities();
        return roomFunctionalities;
    }
}
