package SmartHome.domainTest.roomTest;


import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.room.FactoryIndoorRoom;
import SmartHome.domain.room.Room;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;

class FactoryIndoorRoomTest {

    @Test
    void whenConstructorInvokedWithInitializer_ThenMockObjectShouldBeCreated()
    {
        //arrange
        String roomName = "BathRoom";
        int houseFloor = 1;
        double roomWidth = 3;
        double roomLength = 1;
        double roomHeight = 2;

        try(MockedConstruction<Room> roomDouble = mockConstruction(Room.class,(mock, context)-> {
            when(mock.getRoomName()).thenReturn(roomName);
        })) {

            FactoryIndoorRoom factoryIndoorRoom = new FactoryIndoorRoom();

            // act
            Room room = factoryIndoorRoom.createRoom(roomName,houseFloor,roomWidth,roomLength,roomHeight);

            // assert
            List<Room> rooms = roomDouble.constructed();

            assertEquals(1, rooms.size());
            assertEquals(roomName, room.getRoomName());
        }
    }
}