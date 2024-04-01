package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.domain.room.RoomFactory;
import SmartHomeDDD.repository.RoomRepository;
import SmartHomeDDD.services.RoomService;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RoomServiceTest {

    /**
     * This test ensures that, when given a null repository, the constructor throws and IllegalArgumentException.
     * First, a roomFactory is mocked, then the constructor is called with a null repository, and finally, the exception
     * message is compared to the expected message.
     */
    @Test
    void whenGivenAnInvalidRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        RoomFactory factory = mock(RoomFactory.class);
        String expected = "Invalid parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomService(null,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null factory, the constructor throws and IllegalArgumentException.
     * First, a repository is mocked, then the constructor is called with a null factory, and finally, the exception
     * message is compared to the expected message.
     */
    @Test
    void whenGivenAnInvalidFactory_ConstructorThrowsIllegalArgument(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        String expected = "Invalid parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomService(repository,null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when createRoom is given a null RoomNameVO, it returns null.
     * First, a repository and a factory are mocked, as well as all the other parameters necessary, then the createRoom method is called with a null RoomNameVO, and finally
     * the result is compared to null.
     */
    @Test
    void whenCreateRoomIsGivenInvalidRoomNameVO_returnsNull(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        RoomFactory factory = mock(RoomFactory.class);

        RoomFloorVO roomFloorVO = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsVO = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDVO = mock(HouseIDVO.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        Room result = roomService.createRoom(null,roomFloorVO,roomDimensionsVO,houseIDVO);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that, when createRoom is given a null RoomFloorVO, it returns null.
     * First, a repository and a factory are mocked, as well as all the other parameters necessary, then the createRoom method is called with a null RoomFloorVO, and finally
     * the result is compared to null.
     */
    @Test
    void whenCreateRoomIsGivenInvalidRoomFloorVO_returnsNull(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        RoomFactory factory = mock(RoomFactory.class);

        RoomNameVO roomNameVO= mock(RoomNameVO.class);
        RoomDimensionsVO roomDimensionsVO = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDVO = mock(HouseIDVO.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        Room result = roomService.createRoom(roomNameVO,null,roomDimensionsVO,houseIDVO);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that, when createRoom is given a null RoomDimensionsVO, it returns null.
     * First, a repository and a factory are mocked, as well as all the other parameters necessary, then the createRoom method is called with a null RoomDimensionsVO, and finally
     * the result is compared to null.
     */
    @Test
    void whenCreateRoomIsGivenInvalidRoomDimensionsVO_returnsNull(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        RoomFactory factory = mock(RoomFactory.class);

        RoomNameVO roomNameVO= mock(RoomNameVO.class);
        RoomFloorVO roomFloorVO = mock(RoomFloorVO.class);
        HouseIDVO houseIDVO = mock(HouseIDVO.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        Room result = roomService.createRoom(roomNameVO,roomFloorVO,null,houseIDVO);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that, when createRoom is given a null HouseIDVO, it returns null.
     * First, a repository and a factory are mocked, as well as all the other parameters necessary, then the createRoom method is called with a null HouseIDVO, and finally
     * the result is compared to null.
     */
    @Test
    void whenCreateRoomIsGivenInvalidHouseIDVO_returnsNull(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        RoomFactory factory = mock(RoomFactory.class);

        RoomNameVO roomNameVO= mock(RoomNameVO.class);
        RoomFloorVO roomFloorVO = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsVO = mock(RoomDimensionsVO.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        Room result = roomService.createRoom(roomNameVO,roomFloorVO,roomDimensionsVO,null);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that, when createRoom is given valid parameters, it returns a Room.
     * First, a repository and a factory are mocked, as well as all the necessary parameters, then the createRoom method is called and finally
     * the result is compared to the expected Room.
     */
    @Test
    void whenCreateRoomIsGivenValidParameters_returnsRoom(){
        // Arrange
        RoomNameVO roomNameVO= mock(RoomNameVO.class);
        RoomFloorVO roomFloorVO = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsVO = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDVO = mock(HouseIDVO.class);

        Room room = mock(Room.class);

        RoomRepository repository = mock(RoomRepository.class);

        RoomFactory factory = mock(RoomFactory.class);
        when(factory.createRoom(roomNameVO,roomFloorVO,roomDimensionsVO,houseIDVO)).thenReturn(room);


        RoomService roomService = new RoomService(repository,factory);

        // Act
        Room result = roomService.createRoom(roomNameVO,roomFloorVO,roomDimensionsVO,houseIDVO);

        // Assert
        assertEquals(room,result);
    }

    /**
     * This test ensures that, when saveRoom is given a null Room, it returns false.
     * First, a repository and a factory are mocked, after the RoomService is instantiated the saveRoom method is called with a null Room, and finally
     * the result is compared to false.
     */
    @Test
    void whenSaveRoomIsGivenNullRoom_returnsFalse(){
        // Arrange
        RoomRepository repository = mock(RoomRepository.class);
        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        boolean result = roomService.saveRoom(null);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that, when saveRoom is given a valid Room, it returns true.
     * First, a repository and a factory are mocked, after the RoomService is instantiated the saveRoom method is called with a valid Room, and finally
     * the result is compared to true.
     */
    @Test
    void whenSaveRoomIsGivenValidRoom_returnsTrue(){
        // Arrange
        Room room = mock(Room.class);

        RoomRepository repository = mock(RoomRepository.class);
        when(repository.save(room)).thenReturn(true);

        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        boolean result = roomService.saveRoom(room);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that, when findAll method is called, then a list of rooms is returned.
     * First two rooms are mocked, then a list of rooms is created and both rooms added to it.
     * An iterable is created from the list and the repository mocked to return the iterable when findAll is called.
     * A RoomFactory is mocked and the RoomService is instantiated with the repository and factory.
     * An integer is set to 2, the expected size of the list.
     * The findAll method is called and the result stored in a list of rooms, the first room is stored in a resultRoom1 variable.
     * Finally, the size of the result list is compared to the expected size and the first room in the list is compared to the first room in the expected list.
     */
    @Test
    void whenFindAllMethodIsCalled_returnsListOfRooms(){
        // Arrange
        Room room1 = mock(Room.class);
        Room room2 = mock(Room.class);
        List<Room> roomList = new ArrayList<>();
        roomList.add(room1);
        roomList.add(room2);
        Iterable<Room> iterable = () -> roomList.stream().iterator();

        RoomRepository repository = mock(RoomRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        int expected = 2;

        // Act
        List<Room> result = roomService.findAll();
        Room resultRoom1 = result.get(0);

        // Assert
        assertEquals(expected,result.size());
        assertEquals(room1,resultRoom1);
    }

    /**
     * This test ensures that, when findAll method is called and the repository is empty, an empty list is returned.
     * First, an empty list of rooms is created and an iterable is created from it.
     * The repository is mocked to return the iterable when findAll is called.
     * A RoomFactory is mocked and the RoomService is instantiated with the repository and factory.
     * An integer is set to 0, the expected size of the list.
     * The findAll method is called and the result stored in a list of rooms.
     * Finally, the size of the result list is compared to the expected size.
     */
    @Test
    void whenFindAllMethodIsCalled_ifRepoIsEmpty_returnsEmptyList(){
        // Arrange
        List<Room> roomList = new ArrayList<>();
        Iterable<Room> iterable = () -> roomList.stream().iterator();

        RoomRepository repository = mock(RoomRepository.class);
        when(repository.findAll()).thenReturn(iterable);

        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        int expected = 0;

        // Act
        List<Room> result = roomService.findAll();

        // Assert
        assertEquals(expected,result.size());
    }

    /**
     * This test ensures that, when isPresent method is called and the room is present, it returns true.
     * First, a RoomIDVO is mocked.
     * The repository is mocked to return true when isPresent is called with the RoomIDVO.
     * A RoomFactory is mocked and the RoomService is instantiated with the repository and factory.
     * The isPresent method is called and the result stored in a boolean.
     * Finally, the result is compared to true.
     */
    @Test
    void whenIsPresentMethodIsCalled_ifRoomPresent_returnsTrue(){
        // Arrange
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        RoomRepository repository = mock(RoomRepository.class);
        when(repository.isPresent(roomIDVO)).thenReturn(true);

        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        boolean result = roomService.isPresent(roomIDVO);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that, when isPresent method is called and the room is not present, it returns false.
     * First, a RoomIDVO is mocked.
     * The repository is mocked to return false when isPresent is called with the RoomIDVO.
     * A RoomFactory is mocked and the RoomService is instantiated with the repository and factory.
     * The isPresent method is called and the result stored in a boolean.
     * Finally, the result is compared to false.
     */
    @Test
    void whenIsPresentMethodIsCalled_ifRoomNotPresent_returnsFalse(){
        // Arrange
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        RoomRepository repository = mock(RoomRepository.class);
        when(repository.isPresent(roomIDVO)).thenReturn(false);

        RoomFactory factory = mock(RoomFactory.class);

        RoomService roomService = new RoomService(repository,factory);

        // Act
        boolean result = roomService.isPresent(roomIDVO);

        // Assert
        assertFalse(result);
    }
}