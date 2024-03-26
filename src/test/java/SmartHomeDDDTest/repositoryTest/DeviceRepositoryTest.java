package SmartHomeDDDTest.repositoryTest;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceRepositoryTest {
    /**
     * Tests if a device is saved when it is null.
     * This test case verifies that a device cannot be saved when it is null.
     */
    @Test
    public void whenDeviceIsNull_thenItCannotBeSaved() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        Device device = null;

        //Act
        boolean result = deviceRepository.save(device);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if a device is saved when its ID is null.
     * This test case verifies that a device cannot be saved when its ID is null.
     */
    @Test
    public void whenDeviceIDIsNull_thenItCannotBeSaved() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        Device device = mock(Device.class);
        when(device.getId()).thenReturn(null);

        //Act
        boolean result = deviceRepository.save(device);

        //Assert
        assertFalse(result);
    }

    /**
     * Tests if a device is saved when it is valid.
     * This test case verifies that a device can be saved when it is valid.
     */
    @Test
    public void whenDeviceIsValid_thenItCanBeSaved() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        Device device = mock(Device.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(device.getId()).thenReturn(deviceID);

        //Act
        boolean result = deviceRepository.save(device);

        //Assert
        assertTrue(result);
    }

    /**
     * Tests if a device is saved and can be found by its ID.
     * The test case verifies that a device can be successfully saved to the repository
     * and subsequently retrieved using its ID.
     */
    @Test
    public void whenDeviceIsSaved_thenItCanBeFoundById() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        Device device = mock(Device.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(device.getId()).thenReturn(deviceID);
        Device expected = device;

        //Act
        deviceRepository.save(device);
        Device result = deviceRepository.findById(deviceID);

        //Assert
        assertEquals(expected, result);

    }

    /**
     * This test case ensures that when a device  saved in the repository,
     * it does appear in the list of devices returned by the findAll method.
     */
   @Test
   public void whenDeviceIsSaved_thenItShouldAppearInFindAll() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device1 = mock(Device.class);
        Device device2 = mock(Device.class);
        Device device3 = mock(Device.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(device2.getId()).thenReturn(deviceID);

        deviceRepository.save(device1);
        deviceRepository.save(device2);
        deviceRepository.save(device3);

        //Act
        Iterable<Device> iterable = deviceRepository.findAll();
       boolean isDevice2Present = StreamSupport.stream(iterable.spliterator(), false)
               .anyMatch(device -> device.equals(device2));

        //Assert

       assertTrue(isDevice2Present);
        }

    /**
     * Tests if a device is not saved and does not appear in the findAll method.
     * This test case ensures that when a device is not saved in the repository,
     * it does not appear in the list of devices returned by the findAll method.
     */
    @Test
    public void whenDeviceIsNotSaved_thenItShouldNotAppearInFindAll() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device1 = mock(Device.class);
        Device device2 = mock(Device.class);
        Device device3 = mock(Device.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(device2.getId()).thenReturn(deviceID);

        deviceRepository.save(device1);
        deviceRepository.save(device3);

        //Act
        Iterable<Device> iterable = deviceRepository.findAll();
        boolean isDevice2Present = StreamSupport.stream(iterable.spliterator(), false)
                .anyMatch(device -> device.equals(device2));

        //Assert
        assertFalse(isDevice2Present);
    }

    /**
     * Tests if a device that is not present in the repository cannot be found by its ID.
     * This test case ensures that when a device is not saved in the repository,
     * attempting to find it by its ID returns null.
     */
    @Test
    public void whenDeviceIsNotPresent_thenItCannotBeFoundById() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device1 = mock(Device.class);
        Device device2 = mock(Device.class);
        Device device3 = mock(Device.class);

        DeviceIDVO device2ID = mock(DeviceIDVO.class);

        when(device2.getId()).thenReturn(device2ID);

        deviceRepository.save(device1);
        deviceRepository.save(device3);

        //Act
        Device result = deviceRepository.findById(device2ID);

        //Assert
        assertNull(result);
    }

    /**
        *Tests if when RoomID is not found, an empty list is returned.
        */
    @Test
    public void whenRoomIDIsNotPresent_thenReturnEmptyList() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();

        Device device1 = mock(Device.class);
        Device device2 = mock(Device.class);

        deviceRepository.save(device1);
        deviceRepository.save(device2);

        RoomIDVO roomID = mock(RoomIDVO.class);
        int expected = 0;

        //Act
        int result = deviceRepository.findByRoomID(roomID).size();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test case verifies that a list of devices associated with a given room ID
     * is returned when the room ID is present in the repository.
     */
    @Test
    public void whenRoomIDIsPresent_ShouldReturnListOfDevicesInRoom() {
        //Arrange
        DeviceRepository deviceRepository = new DeviceRepository();
        RoomIDVO roomID = mock(RoomIDVO.class);

        Device device1 = mock(Device.class);
        Device device2 = mock(Device.class);

        DeviceIDVO deviceID1 = mock(DeviceIDVO.class);
        DeviceIDVO deviceID2 = mock(DeviceIDVO.class);

        when(device1.getId()).thenReturn(deviceID1);
        when(device2.getId()).thenReturn(deviceID2);

        deviceRepository.save(device1);
        deviceRepository.save(device2);

        when(device1.getRoomID()).thenReturn(roomID);
        when(device2.getRoomID()).thenReturn(roomID);

        //Act
        List<Device> listOfDevicesInARoom = deviceRepository.findByRoomID(roomID);

        //Assert
        assertTrue(listOfDevicesInARoom.contains(device1));
        assertTrue(listOfDevicesInARoom.contains(device2));
    }
}