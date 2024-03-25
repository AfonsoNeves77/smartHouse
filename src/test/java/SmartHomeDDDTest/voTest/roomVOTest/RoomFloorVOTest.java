package SmartHomeDDDTest.voTest.roomVOTest;

import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoomFloorVOTest {

    @Test
    void getRoomFloor_Test(){
        //Arrange
        int floor = 5;
        //Act
        RoomFloorVO roomFloor = new RoomFloorVO(floor);
        int result = roomFloor.getValue();
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