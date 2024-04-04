package SmartHomeDDDTest.controllerTest;

import SmartHomeDDD.controller.AddSensorToDeviceController;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.domain.sensorType.SensorTypeFactory;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.dto.SensorDTO;
import SmartHomeDDD.dto.SensorTypeDTO;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.repository.SensorTypeRepository;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.services.SensorTypeService;
import SmartHomeDDD.vo.UnitVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.*;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AddSensorToDeviceControllerTest {

    /**
     * Test for the constructor of AddSensorToDeviceController.
     * Tests if the constructor throws an IllegalArgumentException when the sensorTypeService is null.
     */
    @Test
    void whenNullSensorTypeService_constructorThrowsIllegalArgumentException() {
        //Arrange
        String expectedMessage = "Invalid service";
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        String path = "sensor.properties";
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);
        SensorTypeService sensorTypeService = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the constructor of AddSensorToDeviceController.
     * Tests if the constructor throws an IllegalArgumentException when the sensorService is null.
     */
    @Test
    void whenNullSensorService_constructorThrowsIllegalArgumentException() {
        //Arrange
        String expectedMessage = "Invalid service";
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        String path = "sensor.properties";
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);
        SensorService sensorService = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the constructor of AddSensorToDeviceController.
     * Tests if the constructor throws an IllegalArgumentException when the deviceService is null.
     */
    @Test
    void whenNullDeviceService_constructorThrowsIllegalArgumentException() {
        //Arrange
        String expectedMessage = "Invalid service";
        String path = "sensor.properties";
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);
        DeviceService deviceService = null;

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService));

        //Assert
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }

    /**
     * Test for the getListOfSensorTypes method of AddSensorToDeviceController.
     * Tests if the method returns a list of SensorTypeDTO of all the available sensor types.
     * The expected size of the list is 12, which corresponds to the number of sensor types in the sensor.properties file.
     * The first sensor type in the list is HumiditySensor.
     * The method should return a list of SensorTypeDTO with a size of 12 and the first sensor type id should be
     * HumiditySensor.
     */
    @Test
    void whenGetListOfSensorTypes_thenReturnListOfSensorTypeDTO() {
        //Arrange
        int expectedSize = 12;
        String expectedSensorTypeID = "HumiditySensor";
        String path = "sensor.properties";
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService);

        //Act
        List<SensorTypeDTO> result = addSensorToDeviceController.getListOfSensorTypes();

        //Assert
        assertEquals(expectedSize, result.size());
        assertEquals(expectedSensorTypeID, result.get(0).getSensorTypeID());
    }

    /**
     * Test for the addSensorToDevice method of AddSensorToDeviceController.
     * Tests if the method returns true when adding a sensor to a device.
     * 1. Every service is initialized with the necessary repositories and factories. The SensorFactory and the
     * SensorTypeService are initialized with the path to the sensor.properties file.
     * 2. The controller is instantiated with the services.
     * 3. The VO's needed to create a device are initialized and a device is created and saved in the device repository.
     * 4. Then all the necessary VO's to create DeviceDTO, SensorTypeDTO and SensorDTO are initialized and their String
     * values are extracted. The roomID is generated with a random UUID to simplify the test.
     * 5. The method addSensorToDevice is called with the deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * 6. The method should return true since the device exists in the repository and the sensor type "TemperatureSensor"
     * is valid.
     */
    @Test
    void whenAddSensorToDevice_thenReturnTrue() {
        //Arrange
        String path = "sensor.properties";

            // SensorService instantiation
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);

            // SensorTypeService instantiation
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);

            // DeviceService instantiation
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

            // AddSensorToDeviceController instantiation
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService);

            // Device instantiation. Device is saved in the repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepository.save(device);

            // DeviceDTO instantiation
        String deviceID = device.getId().getID();
        String deviceName = deviceNameVO.getValue();
        String deviceModel = deviceModelVO.getValue();
        String deviceStatus = device.getDeviceStatus().getValue().toString();
        String roomIDString = roomID.getID();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomIDString);

            // SensorTypeDTO instantiation
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO("TemperatureSensor");
        UnitVO unitVO = new UnitVO("Celsius");
        String sensorTypeID = sensorTypeIDVO.getID();
        String unit = unitVO.getUnit();
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, unit);

            // SensorDTO instantiation
        SensorNameVO sensorNameVO = new SensorNameVO("Sensor1");
        String sensorName = sensorNameVO.getValue();
        SensorDTO sensorDTO = new SensorDTO(sensorName);

        //Act
        boolean result = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorTypeDTO, sensorDTO);

        //Assert
        assertTrue(result);
    }

    /**
     * Test for the addSensorToDevice method of AddSensorToDeviceController.
     * Tests if the method returns false when adding a sensor to a device with an inactive status.
     * 1. Every service is initialized with the necessary repositories and factories. The SensorFactory and the
     * SensorTypeService are initialized with the path to the sensor.properties file.
     * 2. The controller is instantiated with the services.
     * 3. The VO's needed to create a device are initialized and a device is created and saved in the device repository.
     * 4. Then a call to deactivateDevice is made to change the device status to inactive.
     * 5. All the necessary VO's to create DeviceDTO, SensorTypeDTO and SensorDTO are initialized and their String
     * values are extracted. The roomID is generated with a random UUID to simplify the test.
     * 6. The method addSensorToDevice is called with the deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * 7. The method should return false since the device exists in the repository, but it is inactive.
     */
    @Test
    void whenAddSensorToDeviceWithInactiveStatus_thenReturnFalse(){
        //Arrange
        String path = "sensor.properties";

            // SensorService instantiation
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);

            // SensorTypeService instantiation
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);

            // DeviceService instantiation
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

            // AddSensorToDeviceController instantiation
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService);

            // Device instantiation. Device is saved in the repository and then deactivated
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceRepository.save(device);
        device.deactivateDevice();

            // DeviceDTO instantiation
        String deviceID = device.getId().getID();
        String deviceName = deviceNameVO.getValue();
        String deviceModel = deviceModelVO.getValue();
        String deviceStatus = device.getDeviceStatus().getValue().toString();
        String roomIDString = roomID.getID();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomIDString);

            // SensorTypeDTO instantiation
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO("TemperatureSensor");
        UnitVO unitVO = new UnitVO("Celsius");
        String sensorTypeID = sensorTypeIDVO.getID();
        String unit = unitVO.getUnit();
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, unit);

            // SensorDTO instantiation
        SensorNameVO sensorNameVO = new SensorNameVO("Sensor1");
        String sensorName = sensorNameVO.getValue();
        SensorDTO sensorDTO = new SensorDTO(sensorName);

        //Act
        boolean result = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorTypeDTO, sensorDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addSensorToDevice method of AddSensorToDeviceController.
     * Tests if the method returns false when adding a sensor to a device that does not exist in the repository.
     * 1. Every service is initialized with the necessary repositories and factories. The SensorFactory and the
     * SensorTypeService are initialized with the path to the sensor.properties file.
     * 2. The controller is instantiated with the services.
     * 3. The VO's needed to create a device are initialized and a device is created. The device isn't saved in the
     * repository.
     * 4. All the necessary VO's to create DeviceDTO, SensorTypeDTO and SensorDTO are initialized and their String
     * values are extracted. The roomID is generated with a random UUID to simplify the test.
     * 5. The method addSensorToDevice is called with the deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * 6. The method should return false since the device does not exist in the repository.
     */
    @Test
    void whenAddSensorToDeviceNonExistent_thenReturnFalse(){
        //Arrange
        String path = "sensor.properties";

            // SensorService instantiation
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);

            // SensorTypeService instantiation
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);

            // DeviceService instantiation
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

            // AddSensorToDeviceController instantiation
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService);

            // Device instantiation. Device is not saved in the repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);

            // DeviceDTO instantiation
        String deviceID = device.getId().getID();
        String deviceName = deviceNameVO.getValue();
        String deviceModel = deviceModelVO.getValue();
        String deviceStatus = device.getDeviceStatus().getValue().toString();
        String roomIDString = roomID.getID();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomIDString);

            // SensorTypeDTO instantiation
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO("TemperatureSensor");
        UnitVO unitVO = new UnitVO("Celsius");
        String sensorTypeID = sensorTypeIDVO.getID();
        String unit = unitVO.getUnit();
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, unit);

            // SensorDTO instantiation
        SensorNameVO sensorNameVO = new SensorNameVO("Sensor1");
        String sensorName = sensorNameVO.getValue();
        SensorDTO sensorDTO = new SensorDTO(sensorName);

        //Act
        boolean result = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorTypeDTO, sensorDTO);

        //Assert
        assertFalse(result);
    }

    /**
     * Test for the addSensorToDevice method of AddSensorToDeviceController.
     * Tests if the method returns false when adding a sensor to a device with a non-existent sensor type.
     * 1. Every service is initialized with the necessary repositories and factories. The SensorFactory and the
     * SensorTypeService are initialized with the path to the sensor.properties file.
     * 2. The controller is instantiated with the services.
     * 3. The VO's needed to create a device are initialized and a device is created and saved in the device repository.
     * 4. All the necessary VO's to create DeviceDTO, SensorTypeDTO and SensorDTO are initialized and their String
     * values are extracted. The roomID is generated with a random UUID to simplify the test.
     * 5. The sensor type ID is initialized with a non-existent sensor type.
     * 6. The method addSensorToDevice is called with the deviceDTO, sensorTypeDTO and sensorDTO as parameters.
     * 7. The method should return false since the sensor type does not exist in the repository.
     */
    @Test
    void whenAddSensorToDeviceWithNonExistentSensorType_thenReturnFalse(){
        //Arrange
        String path = "sensor.properties";

            // SensorService instantiation
        SensorFactory sensorFactory = new SensorFactory(path);
        SensorRepository sensorRepository = new SensorRepository();
        SensorService sensorService = new SensorService(sensorRepository, sensorFactory);

            // SensorTypeService instantiation
        SensorTypeRepository sensorTypeRepository = new SensorTypeRepository();
        SensorTypeFactory sensorTypeFactory = new SensorTypeFactory();
        SensorTypeService sensorTypeService = new SensorTypeService(sensorTypeRepository, sensorTypeFactory, path);

            // DeviceService instantiation
        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceRepository deviceRepository = new DeviceRepository();
        DeviceService deviceService = new DeviceService(deviceFactory, deviceRepository);

            // AddSensorToDeviceController instantiation
        AddSensorToDeviceController addSensorToDeviceController = new AddSensorToDeviceController(sensorTypeService, sensorService, deviceService);

            // Device instantiation. Device is saved in the repository
        DeviceNameVO deviceNameVO = new DeviceNameVO("Device1");
        DeviceModelVO deviceModelVO = new DeviceModelVO("Model1");
        RoomIDVO roomID = new RoomIDVO(UUID.randomUUID());
        Device device = new Device(deviceNameVO, deviceModelVO, roomID);
        deviceService.saveDevice(device);

            // DeviceDTO instantiation
        String deviceID = device.getId().getID();
        String deviceName = deviceNameVO.getValue();
        String deviceModel = deviceModelVO.getValue();
        String deviceStatus = device.getDeviceStatus().getValue().toString();
        String roomIDString = roomID.getID();
        DeviceDTO deviceDTO = new DeviceDTO(deviceID, deviceName, deviceModel, deviceStatus, roomIDString);

            // SensorTypeDTO instantiation
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO("FireSensor");
        UnitVO unitVO = new UnitVO("Celsius");
        String sensorTypeID = sensorTypeIDVO.getID();
        String unit = unitVO.getUnit();
        SensorTypeDTO sensorTypeDTO = new SensorTypeDTO(sensorTypeID, unit);

            // SensorDTO instantiation
        SensorNameVO sensorNameVO = new SensorNameVO("Sensor1");
        String sensorName = sensorNameVO.getValue();
        SensorDTO sensorDTO = new SensorDTO(sensorName);

        //Act
        boolean result = addSensorToDeviceController.addSensorToDevice(deviceDTO, sensorTypeDTO, sensorDTO);

        //Assert
        assertFalse(result);
    }

}