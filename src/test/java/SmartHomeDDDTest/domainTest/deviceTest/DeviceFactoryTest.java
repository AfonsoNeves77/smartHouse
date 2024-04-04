package SmartHomeDDDTest.domainTest.deviceTest;

import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class DeviceFactoryTest {

    /**
    * Tests that when the createDevice method in DeviceFactory is called, then an object should be created.
    */
    @Test
    void whenConstructorInvoked_ThenMockObjectShouldBeCreated()  {
        //arrange
        DeviceNameVO nameDouble = mock(DeviceNameVO.class);
        DeviceModelVO modelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIdDouble = mock(RoomIDVO.class);

        try(MockedConstruction<Device> deviceDouble = mockConstruction(Device.class,(mock, context)-> {
            when(mock.getDeviceName()).thenReturn(nameDouble);
            when(mock.getDeviceModel()).thenReturn(modelDouble);
            when(mock.getRoomID()).thenReturn(roomIdDouble);
        })) {

            DeviceFactory factoryDevice = new DeviceFactory();

            // act
            Device device = factoryDevice.createDevice(nameDouble, modelDouble, roomIdDouble);

            // assert
            List<Device> devices = deviceDouble.constructed();

            assertEquals(1, devices.size());
            assertEquals(modelDouble, device.getDeviceModel());
            assertEquals(nameDouble, device.getDeviceName());
            assertEquals(roomIdDouble,device.getRoomID());
        }
    }

       /*
    SYSTEM UNDER TEST: FACTORY + DEVICE
    A double of all the other collaborators is done (essentially the required value objects to create the Device).
     */

    /**
     * Tests the behavior of the createDevice method in DeviceFactory when valid parameters are provided.
     * It verifies if the method returns the expected DeviceNameVO.
     */
    @Test
    void createDevice_WhenValidParameters_CorrectDeviceShouldBeReturned()  {
        //arrange
        DeviceNameVO expectedName = mock(DeviceNameVO.class);
        DeviceModelVO expectedModel = mock(DeviceModelVO.class);
        RoomIDVO expectedRoomID = mock(RoomIDVO.class);
        DeviceFactory deviceFactory = new DeviceFactory();

        //Act
        Device device = deviceFactory.createDevice(expectedName,expectedModel,expectedRoomID );
        DeviceNameVO resultName = device.getDeviceName();
        DeviceModelVO resultModel = device.getDeviceModel();
        RoomIDVO roomIDVO = device.getRoomID();

        //Assert
        // Verifying if the returned Device value objects matches the expected values
        assertEquals(expectedName,resultName);
        assertEquals(expectedModel,resultModel);
        assertEquals(expectedRoomID,roomIDVO);
    }

    /**
     * Tests the behavior of the createDevice method in DeviceFactory when null parameters are provided.
     * It ensures that the method propagates an IllegalArgumentException.
     */

    @Test
    void createDevice_WhenNullParameters_ShouldPropagateIllegalArgumentException()  {
        //arrange
        String expected = "Invalid parameters";
        DeviceNameVO nameVO = null;
        DeviceModelVO modelDouble = mock(DeviceModelVO.class);
        RoomIDVO roomIdDouble = mock(RoomIDVO.class);
        DeviceFactory deviceFactory = new DeviceFactory();

        //Act + Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> deviceFactory.createDevice(nameVO,modelDouble,roomIdDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }



}