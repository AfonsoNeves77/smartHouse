package SmartHome.controller;


import SmartHome.domain.House;
import SmartHome.domain.room.FactoryRoom;
import SmartHome.dto.RoomDTO;

public class AddRoomCTRL {
    private final House house;
    private final FactoryRoom factoryRoom;

    /**
     * Controller Constructor for Use Case: Add a new room to the house
     * @param house House to be considered
     */
    public AddRoomCTRL(House house, FactoryRoom factoryRoom) {
        this.house = house;
        this.factoryRoom = factoryRoom;
    }

    /**
     * Requests to add a new Room to the house
     * @param roomDTO DTO with external information to add a new room
     * @return Code 0: Room successfully added;
     * Code 1: Impossible to instantiate a new Room;
     * Code 2: Room already exists in the house (duplication);
     */
    public boolean addRoom(RoomDTO roomDTO){
        String roomName = roomDTO.getRoomName();
        int houseFloor = roomDTO.getHouseFloor();
        double width = roomDTO.getRoomWidth();
        double length = roomDTO.getRoomLength();
        double height = roomDTO.getRoomHeight();
        return this.house.addRoom(roomName,houseFloor,width,length,height, factoryRoom);
    }
}
