package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeviceServiceTest {


     /**
     * The following tests verify that if any of the parameters are null, DeviceService instantiation should throw an Illegal Argument Exception with
     * the  message "Invalid Parameters".
     * Regarding test syntax it´s used a reusable assertion method assertThrowsIllegalArgumentExceptionWithNullParameter()
     * for the similar nature of the following tests.
     * @param factoryDevice that creates new Device
     * @param deviceRepository responsible for saving, accessing and retrieving Device instances
     */


    private void assertThrowsIllegalArgumentExceptionWithNullParameter(DeviceFactory factoryDevice, DeviceRepository deviceRepository) {
        //Arrange
        String expected = "Invalid parameters";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new DeviceService(factoryDevice,deviceRepository));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if DeviceFactory parameter is null then DeviceService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullDeviceFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceFactory deviceFactory = null;
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceFactory,deviceRepository);
    }

    /**
     *The following test verifies that if DeviceRepository parameter is null then DeviceService instantiation should throw an Illegal Argument Exception with
     the message "Invalid Parameters".
     */

    @Test
    void whenNullDeviceRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = null;

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceFactory,deviceRepository);
    }

    /**
     * Test case to verify that creating a device with a null DeviceNameVO parameter returns null.
     * All DeviceService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     *
     */

    @Test
    void createDevice_WhenNullDeviceNameVO_ShouldReturnNull(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = null;
        DeviceModelVO deviceModelVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        //Act
        Device result = deviceService.createDevice(deviceNameVO,deviceModelVO,roomIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating a device with a null DeviceModelVO parameter returns null.
     * All DeviceService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     *
     */

    @Test
    void createDevice_WhenNullDeviceModelVO_ShouldReturnNull(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelVO = null;
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        //Act
        Device result = deviceService.createDevice(deviceNameVO,deviceModelVO,roomIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating a device with a null RoomIDVO parameter returns null.
     * All DeviceService collaborators, that are involved in this operation are doubled, ensuring proper isolation.
     */

    @Test
    void createDevice_WhenNullRoomIDVO_ShouldReturnNull(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = null;

        //Act
        Device result = deviceService.createDevice(deviceNameVO,deviceModelVO,roomIDVO);

        //Assert
        assertNull(result);
    }

    /**
     * Test case to verify that creating a device with valid parameters returns a non-null device instance.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test case, the behavior of DeviceFactory is configured to return a doubled device when the createDevice method is called.
     * The assertion is performed by comparing the returned device object with the expected device.</p>
     */
    @Test
    void createDevice_ShouldReturnDevice(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = mock(DeviceNameVO.class);
        DeviceModelVO deviceModelVO = mock(DeviceModelVO.class);
        RoomIDVO roomIDVO = mock(RoomIDVO.class);

        Device expected = mock(Device.class);
        when(deviceFactory.createDevice(deviceNameVO,deviceModelVO,roomIDVO)).thenReturn(expected);

        //Act
        Device result = deviceService.createDevice(deviceNameVO,deviceModelVO,roomIDVO);

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify that saving a null device returns false.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     */

    @Test
    void saveDevice_WhenNullDevice_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        Device device = null;

        //Act
        boolean result = deviceService.saveDevice(device);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that saving a device in the repository, which results in an unsuccessful save operation, returns false.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is doubled, and its behavior is configured to return false when the saving operation is called.
     */

    @Test
    void saveDevice_WhenUnsuccessfulSaveInRepository_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        Device device = mock(Device.class);
        when(deviceRepository.save(device)).thenReturn(false);

        //Act
        boolean result = deviceService.saveDevice(device);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that saving a device in the repository, which results in a successful save operation, returns true.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is doubled, and its behavior is configured to return true when the saving operation is called.
     */

    @Test
    void saveDevice_WhenSuccessfulSaveInRepository_ShouldReturnTrue(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        Device device = mock(Device.class);
        when(deviceRepository.save(device)).thenReturn(true);

        //Act
        boolean result = deviceService.saveDevice(device);

        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify that deactivating a device, when the device is not present in the repository, returns false.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is doubled, and its behavior is conditioned to return null when findById method is called
     * with a doubled deviceIDVO.
     */

    @Test
    void deactivateDevice_WhenDeviceIsNotPresentInRepository_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = null;
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);

        //Act
        boolean result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that deactivating a device, when the device is already deactivated, returns false.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is doubled, and its behavior is conditioned to return a device double
     * when a mocked deviceID is provided. The mocked device is further configured to return a mocked deviceStatusVO,
     * which in turn is configured to return false (indicating deactivated status) when its value is queried
     * (via the getValue method).
     * This setup ensures that the deactivation operation returns false.
     */

    @Test
    void deactivateDevice_WhenDeviceIsDeactivated_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        DeviceStatusVO deviceStatusVO = mock(DeviceStatusVO.class);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.getDeviceStatus()).thenReturn(deviceStatusVO);
        when(deviceStatusVO.getValue()).thenReturn(false);

        //Act
        boolean result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that deactivating a device, when the device fails to update its status, returns false.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return a mocked device
     * when a mocked deviceID is provided. The mocked device is further configured to return a mocked deviceStatusVO,
     * which in turn is configured to return true (indicating an active status) when its value is queried (via the getValue method).
     * Additionally, the deactivateDevice method of the device is mocked to return false, indicating that the status update failed.
     * This setup ensures that the deactivation operation returns false.</p>
     */

    @Test
    void deactivateDevice_WhenDeviceDoesNotUpdateStatus_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        DeviceStatusVO deviceStatusVO = mock(DeviceStatusVO.class);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.getDeviceStatus()).thenReturn(deviceStatusVO);
        when(deviceStatusVO.getValue()).thenReturn(true);
        when(device.deactivateDevice()).thenReturn(false);

        //Act
        boolean result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that deactivating a device, when the device is successfully deactivated, returns true.
     *All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return a mocked device
     * when a mocked deviceID is provided. The mocked device is further configured to return a mocked deviceStatusVO,
     * which in turn is configured to return true (indicating an active status) when its value is queried
     * (via the getValue method).
     * Additionally, the deactivateDevice method of the device is mocked to return true, indicating a successful deactivation.
     * This setup ensures that the deactivation operation returns true.</p>
     */

    @Test
    void deactivateDevice_ShouldReturnTrue(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        DeviceStatusVO deviceStatusVO = mock(DeviceStatusVO.class);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.getDeviceStatus()).thenReturn(deviceStatusVO);
        when(deviceStatusVO.getValue()).thenReturn(true);
        when(device.deactivateDevice()).thenReturn(true);

        //Act
        boolean result = deviceService.deactivateDevice(deviceIDVO);

        //Assert
        assertTrue(result);
    }

    /**
     * Test case to verify that retrieving a list of devices in a room returns the correct list of devices.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return a list of mocked devices
     * when findByRoomID method is called with a mocked roomIDVO. The second device in the list is further configured
     * to return a mocked deviceNameVO when getDeviceName method is called. The test verifies that the retrieved list of devices
     * matches the expected list, ensuring correct behavior (retrieved list matches the expected order since the second object
     * when has it´s name queried it returns the previously configured deviceNameVO).
     */

    @Test
    void getLisOfDevicesInARoom_ShouldReturnCorrectListOfDevices(){

        //Arrange
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        List<Device> listOfDevices = new ArrayList<>();
        Device device = mock(Device.class);
        Device secondDevice = mock(Device.class);
        DeviceNameVO expected = mock(DeviceNameVO.class);
        when(secondDevice.getDeviceName()).thenReturn(expected);
        listOfDevices.add(device);
        listOfDevices.add(secondDevice);

        when(deviceRepository.findByRoomID(roomIDVO)).thenReturn(listOfDevices);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        //Act
       List<Device> resulList = deviceService.getListOfDevicesInARoom(roomIDVO);
       DeviceNameVO result = resulList.get(1).getDeviceName();

        //Assert
        assertEquals(expected,result);
    }

    /**
     * Test case to verify that retrieving a list of devices in a room returns an empty list when the room has no devices.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return an empty list
     * when findByRoomID method is called with a mocked roomIDVO. The test verifies that the result of
     * getListOfDevicesInARoom method is an empty list, ensuring correct behavior.</p>
     */

    @Test
    void getLisOfDevicesInARoom_WhenRoomHasNoDevices_ShouldReturnEmptyList(){

        //Arrange
        RoomIDVO roomIDVO = mock(RoomIDVO.class);
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);

        List<Device> listOfDevices = new ArrayList<>();
        when(deviceRepository.findByRoomID(roomIDVO)).thenReturn(listOfDevices);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        //Act
        List<Device> result = deviceService.getListOfDevicesInARoom(roomIDVO);

        //Assert
        assertTrue(result.isEmpty());
    }


    /**
     * Test case to verify that isActive method returns false when the device is not present in the repository.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return null
     * when findById method is called with a mocked deviceIDVO. The test verifies that isActive method
     * returns false.
     */

    @Test
    void isActive_WhenDeviceIsNotPresentInRepository_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = null;
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);

        //Act
        boolean result = deviceService.isActive(deviceIDVO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test case to verify that isActive method returns false when the device is deactivated.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return a mocked device
     * when findById method is called with a mocked deviceIDVO. The device's status is further configured
     * to return false when getValue method of deviceStatusVO is called. The test verifies that isActive method
     * returns false in this scenario.
     */

    @Test
    void isActive_WhenDeviceIsDeactivated_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        DeviceStatusVO deviceStatusVO = mock(DeviceStatusVO.class);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.getDeviceStatus()).thenReturn(deviceStatusVO);
        when(deviceStatusVO.getValue()).thenReturn(false);

        //Act
        boolean result = deviceService.isActive(deviceIDVO);

        //Assert
        assertFalse(result);
    }

    /**

     * Test case to verify that isActive method returns true when the device is active.
     * All collaborators of DeviceService involved in this operation are mocked to ensure proper isolation.
     * In this test, the deviceRepository is mocked, and its behavior is conditioned to return a mocked device
     * when findById method is called with a mocked deviceIDVO. The device's status is further configured
     * to return true when getValue method of deviceStatusVO is called. The test verifies that isActive method
     * returns true.
     */

    @Test
    void isActive_WhenActiveDevice_ShouldReturnTrue(){

        //Arrange
        DeviceFactory deviceFactory = mock(DeviceFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Device device = mock(Device.class);
        DeviceStatusVO deviceStatusVO = mock(DeviceStatusVO.class);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.getDeviceStatus()).thenReturn(deviceStatusVO);
        when(deviceStatusVO.getValue()).thenReturn(true);

        //Act
        boolean result = deviceService.isActive(deviceIDVO);

        //Assert
        assertTrue(result);
    }

    //Integration tests

    /**

     * Integration test to verify that deactivating an existing active device returns true.
     * Although the non-functional requirements suggest that only isolation tests are adequate for this part,
     * it was deemed necessary to elaborate integration tests to ensure proper unit integration.
     */

    @Test
    void deactivateDevice_DeviceExistsInRepositoryAndIsActive_ShouldReturnTrue(){

        //Arrange
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = new DeviceNameVO("Device Name");
        DeviceModelVO deviceModelVO = new DeviceModelVO("XK-Z99");
        RoomIDVO roomIDVO = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO,deviceModelVO,roomIDVO);

        deviceRepository.save(device);

        //Act
        boolean result = deviceService.deactivateDevice(device.getId());

        //Assert
        assertTrue(result);
    }

    /**
     * Integration test to verify that deactivating an existing deactivated device returns false.
     * Although the non-functional requirements suggest that only isolation tests are adequate for this part,
     * it was deemed necessary to elaborate integration tests to ensure proper unit integration.
     */

    @Test
    void deactivateDevice_DeviceIsNotActive_ShouldReturnFalse(){

        //Arrange
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

        DeviceNameVO deviceNameVO = new DeviceNameVO("Device Name");
        DeviceModelVO deviceModelVO = new DeviceModelVO("XK-Z99");
        RoomIDVO roomIDVO = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO,deviceModelVO,roomIDVO);

        deviceRepository.save(device);
        //Deactivation
        device.deactivateDevice();

        //Act
        boolean result = deviceService.deactivateDevice(device.getId());

        //Assert
        assertFalse(result);
    }

    /**
     * This test ensures getDevices successfully converts all DeviceIDVO instances with Device.
     */
    @Test
    void getDevices_whenGivenAListWithDeviceIDVO_SuccessfullyreplacesWithDeviceObjects(){
        // Arrange
        DeviceFactory factory = mock(DeviceFactory.class);

        // Device + DeviceIDVO
        Device device1 = mock(Device.class);
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        when(device1.getId()).thenReturn(id1);

        Device device2 = mock(Device.class);
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        when(device2.getId()).thenReturn(id2);

        DeviceRepository repository = mock(DeviceRepository.class);
        when(repository.findById(id1)).thenReturn(device1);
        when(repository.findById(id2)).thenReturn(device2);

        DeviceService service = new DeviceService(factory,repository);

        // Entry parameter arrange.
        Map<String, List<DeviceIDVO>> map = new HashMap<>();

        List<DeviceIDVO> firstKeyList = new ArrayList<>();
        firstKeyList.add(id1);
        firstKeyList.add(id2);
        map.put("key1",firstKeyList);

        // Pre-act
        int expectedSize = 2;
        Map<String, List<Device>> resultMap = service.getDevices(map);

        // Act
        Device resultDevice = resultMap.get("key1").get(0);
        int resultSize = resultMap.get("key1").size();

        // Assert
        assertEquals(expectedSize,resultSize);
        assertEquals(device1,resultDevice);
    }

    /**
     * This test ensures getDevices successfully converts all DeviceIDVO instances with Device.
     */
    @Test
    void getDevices_whenGivenAListWithDeviceIDVO_SuccessfullyReplacesWithDeviceObjects(){
        // Arrange
        DeviceFactory factory = mock(DeviceFactory.class);

        // Device + DeviceIDVO
        Device device1 = mock(Device.class);
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        when(device1.getId()).thenReturn(id1);

        Device device2 = mock(Device.class);
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        when(device2.getId()).thenReturn(id2);

        Device device3 = mock(Device.class);
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");
        when(device3.getId()).thenReturn(id3);

        Device device4 = mock(Device.class);
        DeviceIDVO id4 = mock(DeviceIDVO.class);
        when(id4.getID()).thenReturn("id4");
        when(device4.getId()).thenReturn(id4);

        DeviceRepository repository = mock(DeviceRepository.class);
        when(repository.findById(id1)).thenReturn(device1);
        when(repository.findById(id2)).thenReturn(device2);
        when(repository.findById(id3)).thenReturn(device3);
        when(repository.findById(id4)).thenReturn(device4);

        DeviceService service = new DeviceService(factory,repository);

        // Entry parameter arrange.
        Map<String, List<DeviceIDVO>> map = new HashMap<>();

        List<DeviceIDVO> firstKeyList = new ArrayList<>();
        firstKeyList.add(id1);
        firstKeyList.add(id2);
        map.put("key1",firstKeyList);

        List<DeviceIDVO> secondKeyList = new ArrayList<>();
        secondKeyList.add(id3);
        secondKeyList.add(id4);
        map.put("key2",secondKeyList);

        // Pre-act
        int expectedSize = 2;
        Map<String, List<Device>> resultMap = service.getDevices(map);

        // Act
        Device resultDevice = resultMap.get("key2").get(0);
        String resultDeviceID = resultDevice.getId().getID();
        int resultSize = resultMap.get("key2").size();

        // Assert
        assertEquals(expectedSize,resultSize);
        assertEquals("id3",resultDeviceID);
        assertEquals(device3,resultDevice);
    }
}
