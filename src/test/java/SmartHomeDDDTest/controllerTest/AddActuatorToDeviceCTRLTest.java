package SmartHomeDDDTest.controllerTest;

import SmartHomeDDD.controller.AddActuatorToDeviceCTRL;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuatorType.ActuatorTypeFactory;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.dto.ActuatorDTO;
import SmartHomeDDD.dto.ActuatorTypeDTO;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.repository.ActuatorTypeRepository;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.services.ActuatorTypeService;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.vo.Settings;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.actuatorVO.IntegerSettingsVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.*;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddActuatorToDeviceCTRLTest {

    /**
     * Test for the constructor of AddActuatorToDeviceCTRL
     * Test if the constructor throws an IllegalArgumentException when the actuatorTypeService is null
     *
     * @throws ConfigurationException
     */
    @Test
    void whenNullActuatorTypeService_constructorThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        String expectedMessage = "Invalid service";
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeService actuatorTypeService = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the constructor of AddActuatorToDeviceCTRL
     * Test if the constructor throws an IllegalArgumentException when the actuatorService is null
     *
     * @throws ConfigurationException
     */
    @Test
    void whenNullActuatorService_constructorThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        String expectedMessage = "Invalid service";
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        ActuatorService actuatorService = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the constructor of AddActuatorToDeviceCTRL
     * Test if the constructor throws an IllegalArgumentException when the deviceService is null
     *
     * @throws ConfigurationException
     */
    @Test
    void whenNullDeviceService_constructorThrowsIllegalArgumentException() throws ConfigurationException {
        //Arrange
        String expectedMessage = "Invalid service";
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = null;
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        String path = "actuator.properties";
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the getListOfActuatorTypes method of AddActuatorToDeviceCTRL
     * Test if the method returns an ActuatorTypeDTO of an available actuator type
     *
     * @throws ConfigurationException
     */
    @Test
    void whenGetListOfActuatorTypes_thenReturnActuatorTypeDTO() throws ConfigurationException {
        //Arrange
        String expectedActuatorTypeID = "SwitchActuator";
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);

        //Act
        List<ActuatorTypeDTO> result = addActuatorToDeviceCtrl.getListOfActuatorTypes();

        //Assert
        assertEquals(expectedActuatorTypeID, result.get(0).getActuatorTypeID());
    }


    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns true when adding an actuator to a device
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorToDevice_thenReturnTrue() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("SwitchActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertTrue(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns true when adding an actuator with valid Integer settings to a device
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorWithValidIntegerSettingsToDevice_thenReturnTrue() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("IntegerValueActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        String lowerLimit = "1";
        String upperLimit = "10";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID, lowerLimit, upperLimit);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertTrue(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns true when adding an actuator with valid Decimal settings to a device
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorWithValidDecimalSettingsToDevice_thenReturnTrue() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("DecimalValueActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        String lowerLimit = "1";
        String upperLimit = "10";
        String precision = "0.1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID, lowerLimit, upperLimit, precision);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertTrue(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns true when adding an actuator with invalid Decimal settings to a device
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorWithInvalidUpperLimitToDevice_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("DecimalValueActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        String lowerLimit = "10";
        String upperLimit = "9";
        String precision = "0.1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID, lowerLimit, upperLimit, precision);
        ;

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns false when adding an actuator with invalid Decimal settings to a device
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorWithInvalidPrecisionToDevice_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("DecimalValueActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        String lowerLimit = "10";
        String upperLimit = "15";
        String precision = "-0.1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID, lowerLimit, upperLimit, precision);


        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns false when adding an actuator to a device with an inactive status
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddSensorToDeviceWithInactiveStatus_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        device.deactivateDevice();
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("SwitchActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns false when adding an actuator to a device that does not exist in the repository
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddSensorToDeviceNonExistent_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("SwitchActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns false when adding an actuator to a device with a non-existent actuator type
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddSensorToDeviceWithNonExistentSensorType_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("OpenDoorActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID);

        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addActuatorToDevice method of AddActuatorToDeviceCTRL
     * Test if the method returns false when adding an IntegerValue actuator with precision to a device.
     *
     * @throws ConfigurationException
     */
    @Test
    void whenAddActuatorWithValidPrecisionToIntegerDevice_thenReturnFalse() throws ConfigurationException {
        //Arrange
        String path = "actuator.properties";
        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory, actuatorRepository);
        ActuatorTypeRepository actuatorTypeRepository = new ActuatorTypeRepository();
        ActuatorTypeFactory actuatorTypeFactory = new ActuatorTypeFactory();
        ActuatorTypeService actuatorTypeService = new ActuatorTypeService(actuatorTypeRepository, actuatorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddActuatorToDeviceCTRL addActuatorToDeviceCtrl = new AddActuatorToDeviceCTRL(actuatorService, actuatorTypeService, deviceService);
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);
        DeviceIDVO deviceIDVO = device.getId();
        String deviceID = deviceIDVO.getID();
        DeviceStatusVO deviceStatusVO = device.getDeviceStatus();
        DeviceDTO deviceDTO = new DeviceDTO(deviceIDVO.getID(), deviceNameVO.getValue(), deviceModelVO.getValue(), deviceStatusVO.getValue().toString(), roomID.getID());
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO("IntegerValueActuator");
        ActuatorTypeDTO actuatorTypeDTO = new ActuatorTypeDTO(actuatorTypeIDVO.getID());
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO("Actuator1");
        String lowerLimit = "10";
        String upperLimit = "15";
        String precision = "0.1";
        ActuatorDTO actuatorDTO = new ActuatorDTO(actuatorNameVO.getValue(), actuatorTypeIDVO.getID(), deviceID, lowerLimit, upperLimit, precision);


        //Act
        boolean result = addActuatorToDeviceCtrl.addActuatorToDevice(actuatorDTO, actuatorTypeDTO, deviceDTO);

        //Assert
        assertFalse(result);
    }
}