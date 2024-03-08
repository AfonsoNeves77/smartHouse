package SmartHome.domain;

import SmartHome.domain.device.Device;
import SmartHome.domain.location.Address;
import SmartHome.domain.location.GPS;
import SmartHome.domain.location.FactoryLocation;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.domain.room.ListOfRooms;
import SmartHome.domain.room.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class House {
    /**
     * The Class House manages its name, location, the list of rooms and the list of sensor types;
     */
    private String houseName;
    private GPS gps;
    private Address address;
    private ListOfRooms listOfRooms = new ListOfRooms();
    private Map<String, ArrayList<Device>> houseFunctionalities = new HashMap<>();

    /**
     * Creates a house object, considering the name is valid.
     * @param houseName Name of the house
     * @throws InstantiationException If invalid name.
     */
    public House(String houseName) throws InstantiationException {
        if (houseName == null || houseName.trim().isEmpty())
            throw new InstantiationException ("Please insert a valid house name.");
        this.houseName = houseName;
    }

    /**
     * Receives all the necessary parameters in order to create a gps and address objects. All parameter validations are
     * performed on object instantiation. If the objects are creates successfully then they get saved on the house's own
     * attributes returning true. If any validations fail, the method will return false.
     * @param doorReference Door reference.
     * @param buildingNumber Building number.
     * @param streetName Stress name.
     * @param city City
     * @param country Country
     * @param zipCode ZipCode
     * @param latitude Latitude
     * @param longitude Longitude
     * @param factoryLocation Factory location
     * @return True or False
     */
    public boolean configureLocation(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode, double latitude, double longitude, FactoryLocation factoryLocation) {
        try{
            this.address = factoryLocation.createAddress(doorReference, buildingNumber, streetName, city, country,zipCode);
            this.gps = factoryLocation.createGPS(latitude,longitude);
            return true;
        } catch (InstantiationException e) {
            return false;
        }

    }

    /**
     * Propagates the room instantiation parameters to own attribute listOfRooms. It then receives a boolean answer from listOfRooms
     * propagating the same boolean answer.
     * @param roomName Room name
     * @param houseFloor House floor
     * @param roomWidth Room width
     * @param roomLength Room length
     * @param roomHeight Room height
     * @param factoryRoom Room factory
     * @return True or false
     */
    public boolean addRoom(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight, FactoryRoom factoryRoom) {
        return listOfRooms.addRoomToList(roomName, houseFloor, roomWidth, roomLength, roomHeight, factoryRoom);
    }

    /**
     * Requests each Room in the house for its device functionalities.
     * Each functionality (Key) and corresponding Devices (Values) are stored in a Map named houseFunctionalities.
     */
    private void updateHouseFunctionalities(){
        List<Room> roomList = listOfRooms.getRoomList();
        for(Room room : roomList){
            Map<String, ArrayList<Device>> roomMap = room.getRoomFunctionalities();
            for(Map.Entry<String, ArrayList<Device>> entry : roomMap.entrySet()){
                String key = entry.getKey();
                ArrayList<Device> devicesToAdd = entry.getValue();
                if(houseFunctionalities.get(key) == null){
                    houseFunctionalities.put(key,devicesToAdd);
                } else {
                    ArrayList<Device> existingList = houseFunctionalities.get(key);
                    existingList.addAll(devicesToAdd);
                    houseFunctionalities.put(key, existingList);
                }
            }
        }
    }

    /**
     * Requests a list of room objects from own attribute listOfRooms.
      * @return List of room objects.
     */
    public List<Room> getListOfRooms() {
        return listOfRooms.getRoomList();
    }

    /**
     * Uses updateHouseFunctionalities() method to get all functionalities in the house.
     * @return A Map with functionalities as Keys and device Lists as Values
     */
    public Map<String,ArrayList<Device>> getHouseFunctionalities(){
        updateHouseFunctionalities();
        return houseFunctionalities;
    }

}
