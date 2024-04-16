package SmartHomeDDD.JPATest;

/*ADICIONAR AO POM.XML
<dependency>
    <groupId>jakarta.persistence</groupId>
    <artifactId>jakarta.persistence-api</artifactId>
    <version>3.0.0</version>
</dependency>
 */

//import jakarta.persistence.*;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.*;

import java.util.UUID;

//@Entity
//@Table(name = "ROOM")

public class RoomDataModel {
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //private String roomID;
    private String roomName;
    private int floor;
    private double roomLength;
    private double roomWidth;
    private double roomHeight;
    private String houseID;

    public RoomDataModel() {


    }

    public RoomDataModel(Room room) {
        //this.roomID = room.getId().toString();
        this.roomName = room.getRoomName().toString();
        this.floor = room.getFloor().getValue();
        this.roomLength = room.getRoomDimensions().getRoomLength();
        this.roomWidth = room.getRoomDimensions().getRoomWidth();
        this.roomHeight = room.getRoomDimensions().getRoomHeight();
        this.houseID = room.getHouseID().toString();
    }

    static public Room toDomain(RoomDataModel roomDataModel, RoomFactory factory) {
        RoomNameVO roomName = new RoomNameVO(roomDataModel.roomName);
        RoomFloorVO floor = new RoomFloorVO(roomDataModel.floor);
        RoomLengthVO roomLength = new RoomLengthVO(roomDataModel.roomLength);
        RoomWidthVO roomWidth = new RoomWidthVO(roomDataModel.roomWidth);
        RoomHeightVO roomHeight = new RoomHeightVO(roomDataModel.roomHeight);
        RoomDimensionsVO roomDimensions = new RoomDimensionsVO(roomLength, roomWidth, roomHeight);
        HouseIDVO houseID = new HouseIDVO(UUID.fromString(roomDataModel.houseID));

        Room roomDomain = factory.createRoom(roomName, floor, roomDimensions, houseID);

        return roomDomain;
    }
}
