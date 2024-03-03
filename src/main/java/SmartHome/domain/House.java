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

    public House(String houseName) throws InstantiationException {
        if (houseName == null || houseName.trim().isEmpty())
            throw new InstantiationException ("Please insert a valid house name.");
    }

    public boolean configureLocation(String doorReference, String buildingNumber, String streetName, String city, String country, String zipCode, double latitude, double longitude, FactoryLocation factoryLocation) {
        try{
            this.address = factoryLocation.createAddress(doorReference, buildingNumber, streetName, city, country,zipCode);
            this.gps = factoryLocation.createGPS(latitude,longitude);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    public boolean addRoom(String roomName, int houseFloor, double roomWidth, double roomLength, double roomHeight, FactoryRoom factoryRoom) {
        return listOfRooms.addRoomToList(roomName, houseFloor, roomWidth, roomLength, roomHeight, factoryRoom);
    }

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

    public List<Room> getListOfRooms() {
        return listOfRooms.getRoomList();
    }

    public Map<String,ArrayList<Device>> getHouseFunctionalities(){
        updateHouseFunctionalities();
        return houseFunctionalities;
    }

}
