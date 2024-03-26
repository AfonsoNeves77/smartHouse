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
import static org.mockito.Mockito.*;

public class DeviceFactoryTest {

    /**
    * Tests that when the createDevice method in DeviceFactory is called, then an object should be created.
    */
@Test
void whenConstructorInvoked_ThenMockObjectShouldBeCreated() throws InstantiationException {
    //arrange
    DeviceNameVO nameDouble = mock(DeviceNameVO.class);
    DeviceModelVO modelDouble = mock(DeviceModelVO.class);
    RoomIDVO roomIdDouble = mock(RoomIDVO.class);

    try(MockedConstruction<Device> deviceDouble = mockConstruction(Device.class,(mock, context)-> {
        when(mock.getDeviceName()).thenReturn(nameDouble);
    })) {

        DeviceFactory factoryDevice = new DeviceFactory();

        // act
        Device device = factoryDevice.createDevice(nameDouble, modelDouble, roomIdDouble);

        // assert
        List<Device> devices = deviceDouble.constructed();

        assertEquals(1, devices.size());
        assertEquals(nameDouble, device.getDeviceName());
    }
}
}