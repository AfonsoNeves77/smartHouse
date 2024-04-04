package SmartHomeDDDTest.controllerTest;

import SmartHomeDDD.controller.AddRoomCTRL;
import SmartHomeDDD.domain.house.HouseFactory;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.repository.HouseRepository;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.services.HouseService;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.houseVO.*;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AddRoomCTRLTest {


    /**
     * Test to check if the addRoom method returns true when a room is successfully added
     * when a valid RoomDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsTrueAndRoomIsCreatedAndAddedToTheRepository() {
        // Arrange
        String id = "63421bcc-f21d-4780-89d5-1fd788b4a7c3";
        String roomName = "bedRoom";
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        String houseID = "00000000-0000-0000-0000-000000000000";
        RoomDTO roomDTO = new RoomDTO(id, roomName,floor,roomHeight, roomLength, roomWidth, houseID);

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepository roomRepository = new RoomRepository();
        RoomFactory roomFactory = new RoomFactory();
        RoomService roomService = new RoomService(roomRepository, roomFactory);
        HouseFactory houseFactory = new HouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService(houseRepository, houseFactory);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseService.createNewHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(houseService, roomService);
        boolean expected = true;

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test to check if the roomRepository contains the room that was added when
     * the method addRoom from the controller is called and a valid RoomDTO
     * is given as input.
     */
    @Test
    void whenAddRoomIsCalled_RoomIsAddedToTheRepository() {
        // Arrange
        String id = "63421bcc-f21d-4780-89d5-1fd788b4a7c3";
        String roomName = "bedRoom";
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        String houseID = "00000000-0000-0000-0000-000000000000";
        RoomDTO roomDTO = new RoomDTO(id, roomName,floor,roomHeight, roomLength, roomWidth, houseID);

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepository roomRepository = new RoomRepository();
        RoomFactory roomFactory = new RoomFactory();
        RoomService roomService = new RoomService(roomRepository, roomFactory);
        HouseFactory houseFactory = new HouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService(houseRepository, houseFactory);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseService.createNewHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(houseService, roomService);


        // Act
        addRoomCTRL.addRoom(roomDTO);
        RoomIDVO roomIDVO = new RoomIDVO(UUID.fromString(id));

        // Assert
        roomRepository.findAll().forEach(room -> {
            if (room.getId().equals(roomIDVO)) {
                assertEquals(roomName, room.getRoomName().getValue());
                assertEquals(floor, room.getFloor().getValue());
                assertEquals(roomHeight, room.getRoomDimensions().getRoomHeight());
                assertEquals(roomLength, room.getRoomDimensions().getRoomLength());
                assertEquals(roomWidth, room.getRoomDimensions().getRoomWidth());
                assertEquals(houseID, room.getHouseID().getID());
            }
        });
    }


    /**
     * Test to check if the addRoom method returns false when a null
     * RoomDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsFalseForNullRoom() {
        // Arrange
        RoomDTO roomDTO = null;

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepository roomRepository = new RoomRepository();
        RoomFactory roomFactory = new RoomFactory();
        RoomService roomService = new RoomService(roomRepository, roomFactory);
        HouseFactory houseFactory = new HouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService(houseRepository, houseFactory);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseService.createNewHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(houseService, roomService);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertFalse(result);
    }


    /**
     * Test to check if the addRoom method returns false when a null
     * RoomNameDTO is given as input.
     */
    @Test
    void whenAddRoomIsCalled_ReturnsFalseGivenNullRoomNameDTO()  {
        // Arrange
        String id = "63421bcc-f21d-4780-89d5-1fd788b4a7c3";
        String roomName = null;
        int floor = 2;
        double roomHeight = 2.2;
        double roomLength = 4.5;
        double roomWidth = 5.0;
        String houseID = "00000000-0000-0000-0000-000000000000";
        RoomDTO roomDTO = new RoomDTO(id, roomName,floor,roomHeight, roomLength, roomWidth, houseID);

        String door = "1";
        String street = "Rua de Santa Catarina";
        String city = "Porto";
        String country = "Portugal";
        String postalCode = "PT-4000-009";
        double latitude = 41.14961;
        double longitude = -8.61099;

        RoomRepository roomRepository = new RoomRepository();
        RoomFactory roomFactory = new RoomFactory();
        RoomService roomService = new RoomService(roomRepository, roomFactory);
        HouseFactory houseFactory = new HouseFactory();
        HouseRepository houseRepository = new HouseRepository();
        HouseService houseService = new HouseService(houseRepository, houseFactory);

        DoorVO doorVO = new DoorVO(door);
        StreetVO streetVO = new StreetVO(street);
        CityVO cityVO = new CityVO(city);
        CountryVO countryVO = new CountryVO(country);
        PostalCodeVO postalCodeVO = new PostalCodeVO(postalCode);

        LatitudeVO latitudeVO = new LatitudeVO(latitude);
        LongitudeVO longitudeVO = new LongitudeVO(longitude);

        AddressVO addressVO = new AddressVO(doorVO, streetVO, cityVO, countryVO, postalCodeVO);
        GpsVO gspVO = new GpsVO(latitudeVO, longitudeVO);
        LocationVO locationVO = new LocationVO(addressVO, gspVO);
        houseService.createNewHouse(locationVO);

        AddRoomCTRL addRoomCTRL = new AddRoomCTRL(houseService, roomService);

        // Act
        boolean result = addRoomCTRL.addRoom(roomDTO);

        // Assert
        assertFalse(result);
    }
}
