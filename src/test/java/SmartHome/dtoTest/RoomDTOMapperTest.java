package SmartHome.dtoTest;

import SmartHome.dto.RoomDTO;
import SmartHome.dto.RoomDTOMapper;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import SmartHome.domain.room.Room;

import static org.junit.jupiter.api.Assertions.*;

class RoomDTOMapperTest {

    @Test
    void domainToDTO() {
        String roomName = "room1";
        int houseFloor = 1;
        double width = 2.0;
        double length = 3.0;
        double height = 4.0;
        Room room = new Room(roomName, houseFloor, width, length, height);
        RoomDTO result = RoomDTOMapper.domainToDTO(room);
        assertEquals(roomName, result.getRoomName());
        assertEquals(houseFloor, result.getHouseFloor());
        assertEquals(width, result.getRoomWidth());
        assertEquals(length, result.getRoomLength());
        assertEquals(height, result.getRoomHeight());
    }

    @Test
    void getRoomDTOList() {
        // Arrange
        String roomName = "room1";
        int houseFloor = 1;
        double width = 2.0;
        double length = 3.0;
        double height = 4.0;
        Room room = new Room(roomName, houseFloor, width, length, height);
        List<Room> roomList = new ArrayList<>();
        roomList.add(room);

        // Act
        Map<RoomDTO, Room> result = RoomDTOMapper.getRoomDTOList(roomList);

        // Assert
        assertFalse(result.isEmpty());
    }
}