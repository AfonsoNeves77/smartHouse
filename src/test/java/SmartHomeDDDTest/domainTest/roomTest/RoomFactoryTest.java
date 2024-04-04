package SmartHomeDDDTest.domainTest.roomTest;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class RoomFactoryTest {

    /**
     * Tests that when the createRoom method in RoomFactory is called, then an object should be created.
     */
    @Test
    void whenConstructorInvoked_ThenMockObjectShouldBeCreated()  {
        //arrange
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO dimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIdDouble = mock(HouseIDVO.class);

        try(MockedConstruction<Room> roomDouble = mockConstruction(Room.class,(mock, context)-> {
            when(mock.getRoomName()).thenReturn(nameDouble);
        })) {

            RoomFactory factoryRoom = new RoomFactory();

            // act
            Room room = factoryRoom.createRoom(nameDouble, floorDouble, dimensionsDouble, houseIdDouble);

            // assert
            List<Room> rooms = roomDouble.constructed();

            assertEquals(1, rooms.size());
            assertEquals(nameDouble, room.getRoomName());
        }
    }

       /*
    SYSTEM UNDER TEST: FACTORY + Room
    A double of all the other collaborators is done (essentially the required value objects to create the room).
     */

    /**
     * Tests that when the createRoom method in RoomFactory is called with invalid (null)
     * parameters, then an IllegalArgumentException should be propagated.
     */
    @Test
    void createRoom_WhenInvalidParameters_ShouldPropagateIllegalArgumentException()  {
        //Arrange
        String expected = "Invalid Parameters";
        RoomNameVO nameDouble = null;
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO dimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIdDouble = mock(HouseIDVO.class);
        RoomFactory roomFactory = new RoomFactory();

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> roomFactory.createRoom(nameDouble,floorDouble,dimensionsDouble,houseIdDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that when the createRoom method in RoomFactory is called with valid
     * parameters, then a Room with the passed value objects should be returned.
     */
    @Test
    void createRoom_WhenValidParameters_ShouldReturnCorrectRoom()  {
        //Arrange
        RoomNameVO expectedName = mock(RoomNameVO.class);
        RoomFloorVO expectedFloor = mock(RoomFloorVO.class);
        RoomDimensionsVO expectedDimensions = mock(RoomDimensionsVO.class);
        HouseIDVO expectedHouseId = mock(HouseIDVO.class);
        RoomFactory roomFactory = new RoomFactory();

        //Act
        Room room = roomFactory.createRoom(expectedName,expectedFloor,expectedDimensions,expectedHouseId);
        RoomNameVO resultName = room.getRoomName();
        RoomFloorVO resultFloor = room.getFloor();
        RoomDimensionsVO resultDimensions = room.getRoomDimensions();
        HouseIDVO resultHouseID = room.getHouseID();
        //Assert
        assertEquals(expectedName,resultName);
        assertEquals(expectedFloor,resultFloor);
        assertEquals(expectedDimensions,resultDimensions);
        assertEquals(expectedHouseId,resultHouseID);
    }

}
