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
import static org.mockito.Mockito.*;

public class RoomFactoryTest {

    /**
     * Tests that when the createRoom method in RoomFactory is called, then an object should be created.
     */
    @Test
    void whenConstructorInvoked_ThenMockObjectShouldBeCreated() throws InstantiationException {
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
}
