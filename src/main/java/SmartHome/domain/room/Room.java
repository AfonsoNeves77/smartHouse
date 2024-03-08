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
     * @param roomWidth room width
     * @param roomLength room length
     * @param roomHeight room height
     * @throws InstantiationException If room name or dimensions given are not valid
     */
    public Room(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight) throws InstantiationException {
        if(roomName == null || roomName.trim().isEmpty()){
            throw new InstantiationException("Please insert valid room name.");
        }
        try {
            this.dimensions = createRoomDimensions(roomWidth,roomLength,roomHeight);
        } catch (InstantiationException e) {
            throw new InstantiationException("Please insert valid room dimensions.");
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
    private RoomDimensions createRoomDimensions(double roomWidth, double roomLength, double roomHeight) throws InstantiationException {
        return new RoomDimensions(roomWidth,roomLength,roomHeight);
    }

    /**
     * @return Room dimensions in a double array
     */
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

    /**
     * Adds a device to a Room.
     * @param deviceName Device name
     * @param deviceModel Device model
     * @param factoryDevice Factory to create devices
     * @return True if device added successfully.
     */
    public boolean addDevice(String deviceName, String deviceModel, FactoryDevice factoryDevice) {
        String deviceLocation = this.roomName;
        return deviceList.addDeviceToList(deviceName, deviceModel, deviceLocation, factoryDevice);
    }

    /**
     * Requests each Device in the room for its functionalities.
     * Each functionality (Key) and corresponding Devices (Values) are stored in a Map named roomFunctionalities.
     */
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

    /**
     * Uses updateRoomFunctionalities() method to get all functionalities in the room.
     * @return A Map with functionalities as Keys and Lists of Devices as Values.
     */
    public Map<String, ArrayList<Device>> getRoomFunctionalities(){
        updateRoomFunctionalities();
        return roomFunctionalities;
    }
}
