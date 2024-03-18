package SmartHome.voTest;

import SmartHome.vo.RoomFloorVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFloorVOTest {

    @Test
    void getRoomFloor_Test(){
        //Arrange
        int floor = 5;
        //Act
        RoomFloorVO roomFloor = new RoomFloorVO(floor);
        int result = roomFloor.getRoomFloor();
        //Assert
        assertEquals(floor, result);
    }
    @Test
    void roomFloor_objectCreation_Test(){
        //Arrange
        int floor = -5;
        //Act
        RoomFloorVO roomFloor = new RoomFloorVO(floor);
        //Assert
        assertNotNull(roomFloor);
    }

}