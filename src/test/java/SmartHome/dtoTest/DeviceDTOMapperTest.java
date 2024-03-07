package SmartHome.dtoTest;

import SmartHome.domain.device.Device;
import SmartHome.dto.DeviceDTO;
import SmartHome.dto.DeviceDTOMapper;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DeviceDTOMapperTest {

    @Test
    void domainToDTO() throws InstantiationException {
        String deviceName = "device1";
        String deviceModel = "model1";
        String deviceLocation = "location1";
        Device device = new Device(deviceName, deviceModel, deviceLocation);
        DeviceDTO result = DeviceDTOMapper.domainToDTO(device);
        assertEquals(deviceName, result.getName());
        assertEquals(deviceModel, result.getModel());
        assertEquals(deviceLocation, result.getLocation());
        assertTrue(result.getStatus());
    }

    @Test
    void testDeviceAndDeviceDTOInHashMap() throws InstantiationException {
        // Arrange
        String deviceName = "device1";
        String deviceModel = "model1";
        String deviceLocation = "location1";
        Device device = new Device(deviceName, deviceModel, deviceLocation);
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);

        // Act
       Map<DeviceDTO, Device> result = DeviceDTOMapper.getDeviceDTOList(deviceList);

        // Assert
        assertFalse(result.isEmpty());
    }
}