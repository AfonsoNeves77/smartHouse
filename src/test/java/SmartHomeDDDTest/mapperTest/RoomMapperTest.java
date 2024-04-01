package SmartHomeDDDTest.mapperTest;

import SmartHomeDDD.domain.room.Room;
import SmartHomeDDD.dto.RoomDTO;
import SmartHomeDDD.mapper.RoomMapper;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.roomVO.RoomDimensionsVO;
import SmartHomeDDD.vo.roomVO.RoomFloorVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.roomVO.RoomNameVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoomMapperTest {

    private RoomDTO roomDTO;
    private String roomID;
    private String roomName;
    private int floor;
    private double roomHeight, roomLength, roomWidth;
    private String houseID;


    /**
     * Common setup for the following tests.
     * Creates a RoomDTO object with the same values for each test.
     */
    @BeforeEach
    void setUp() {
        // Common setup for multiple tests
        roomID = "123e4567-e89b-12d3-a456-426655440000";
        roomName = "BedRoom";
        floor = 2;
        roomHeight = 2.5;
        roomLength = 5.6;
        roomWidth = 3.2;
        houseID = "00000000-0000-0000-0000-000000000000";
        roomDTO = new RoomDTO(roomID, roomName, floor, roomHeight, roomLength, roomWidth, houseID);
    }


    /**
     * Test to verify that the RoomNameVO object is created correctly.
     */
    @Test
    public void whenCreateRoomNameVO_RoomNameVOisRetrieved() {
        // Arrange
        String expected = "BedRoom";

        // Act
        RoomNameVO result = RoomMapper.createRoomNameVO(roomDTO);

        // Assert
        assertEquals(expected, result.getValue());
    }

    /**
     * Test to verify that the RoomFloorVO object is created correctly.
     */
    @Test
    public void whenCreateRoomFloorVO_RoomFloorVOisRetrieved() {
        // Arrange
        int expected = 2;

        // Act
        RoomFloorVO result = RoomMapper.createRoomFloorVO(roomDTO);

        // Assert
        assertEquals(expected, result.getValue());
    }

    /**
     * Test to verify that the RoomDimensionsVO object is created correctly.
     */
    @Test
    public void whenCreateRoomDimensionsVO_RoomDimensionsVOisRetrieved() {
        // Arrange
        double expectedLength = 5.6;
        double expectedWidth = 3.2;
        double expectedHeight = 2.5;

        // Act
        RoomDimensionsVO result = RoomMapper.createRoomDimensionsVO(roomDTO);

        // Assert
        assertEquals(expectedLength, result.getRoomLength());
        assertEquals(expectedWidth, result.getRoomWidth());
        assertEquals(expectedHeight, result.getRoomHeight());
    }

    /**
     * Test to verify that the RoomIDVO object is created correctly.
     */
    @Test
    public void whenCreateRoomIDVO_RoomIDVOisRetrieved() {
        // Arrange
        String expected = "123e4567-e89b-12d3-a456-426655440000";

        // Act
        RoomIDVO result = RoomMapper.createRoomIDVO(roomDTO);

        // Assert
        assertEquals(expected, result.getID());
    }


    /**
     * Test to verify that the HouseIDVO object is created correctly.
     */
    @Test
    public void whenCreateHouseIDVO_HouseIDVOisRetrieved() {
        // Arrange
        String expected = "00000000-0000-0000-0000-000000000000";

        // Act
        HouseIDVO result = RoomMapper.createHouseIDVO(roomDTO);

        // Assert
        assertEquals(expected, result.getID());
    }

    /**
     * Test to verify that a list of Room objects of size 1 is correctly converted
     * to a list of RoomDTO objects with the same size
     */
    @Test
    public void whenDomainToDTOisCalled_ThenExpectedListOfRoomsDTOExpectedSizeIsOf1(){
        // Arrange
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        Room room = new Room(nameDouble, floorDouble, roomDimensionsDouble, houseIDDouble);
        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(room);
        int expectedSizeOfList = 1;

        //Act
        List<RoomDTO> listOfRoomsDTO = RoomMapper.domainToDTO(listOfRooms);

        //Assert
        assertEquals(expectedSizeOfList, listOfRoomsDTO.size());
    }


    /**
     * Test to verify that a list of Room objects of size 2 is correctly converted
     * to a list of RoomDTO objects with the same size
     */
    @Test
    public void whenDomainToDTOisCalled_ThenExpectedListOfRoomsDTOExpectedSizeIsOf2(){
        // Arrange
        RoomNameVO nameDouble = mock(RoomNameVO.class);
        RoomFloorVO floorDouble = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble = mock(HouseIDVO.class);
        RoomNameVO nameDouble2 = mock(RoomNameVO.class);
        RoomFloorVO floorDouble2 = mock(RoomFloorVO.class);
        RoomDimensionsVO roomDimensionsDouble2 = mock(RoomDimensionsVO.class);
        HouseIDVO houseIDDouble2 = mock(HouseIDVO.class);
        Room room1 = new Room(nameDouble, floorDouble, roomDimensionsDouble, houseIDDouble);
        Room room2 = new Room(nameDouble2, floorDouble2, roomDimensionsDouble2, houseIDDouble2);
        List<Room> listOfRooms = new ArrayList<>();
        listOfRooms.add(room1);
        listOfRooms.add(room2);
        int expectedSizeOfList = 2;

        //Act
        List<RoomDTO> listOfRoomsDTO = RoomMapper.domainToDTO(listOfRooms);

        //Assert
        assertEquals(expectedSizeOfList, listOfRoomsDTO.size());
    }


    /**
     * Test to verify that the values from the RoomDTO object match the values from the Room object.
     * First, mock the dependencies inside Room and stub the methods to return specific values.
     * Then, mock the Room object and stub to return the mocked VO's.
     * After that, call the domainToDTO method and check if the values match.
     */
    @Test
    public void whenDomainToDTOisCalled_thenValuesFromRoomDTOObjectMatchValuesFromRoomObject() {

        // Arrange

            // Mock dependencies inside Room
        RoomFloorVO mockRoomFloorVO = mock(RoomFloorVO.class);
        RoomDimensionsVO mockRoomDimensionsVO = mock(RoomDimensionsVO.class);
        RoomNameVO mockRoomNameVO = mock(RoomNameVO.class);
        RoomIDVO mockRoomIDVO = mock(RoomIDVO.class);
        HouseIDVO mockHouseIDVO = mock(HouseIDVO.class);

            // Arrange stubbing for the methods to return specific values
        when(mockRoomFloorVO.getValue()).thenReturn(2);
        when(mockRoomDimensionsVO.getRoomLength()).thenReturn(5.6);
        when(mockRoomDimensionsVO.getRoomWidth()).thenReturn(3.2);
        when(mockRoomDimensionsVO.getRoomHeight()).thenReturn(2.5);
        when(mockRoomNameVO.getValue()).thenReturn("BedRoom");
        when(mockRoomIDVO.getID()).thenReturn("123e4567-e89b-12d3-a456-426655440000");
        when(mockHouseIDVO.getID()).thenReturn("00000000-0000-0000-0000-000000000000");

            // Mock Room and stub to return mocked VOs
        Room roomDouble = mock(Room.class);
        when(roomDouble.getFloor()).thenReturn(mockRoomFloorVO);
        when(roomDouble.getRoomDimensions()).thenReturn(mockRoomDimensionsVO);
        when(roomDouble.getRoomName()).thenReturn(mockRoomNameVO);
        when(roomDouble.getId()).thenReturn(mockRoomIDVO);
        when(roomDouble.getHouseID()).thenReturn(mockHouseIDVO);

            // Expected values are in BeforeEach Setup

        // Act
        List<RoomDTO> result = RoomMapper.domainToDTO(List.of(roomDouble));

        // Assert
        assertEquals(roomName, result.get(0).getRoomName());
        assertEquals(floor, result.get(0).getFloor());
        assertEquals(roomLength, result.get(0).getRoomLength());
        assertEquals(roomWidth, result.get(0).getRoomWidth());
        assertEquals(roomHeight, result.get(0).getRoomHeight());
        assertEquals(roomID, result.get(0).getId());
        assertEquals(houseID, result.get(0).getHouseID());
    }
}
