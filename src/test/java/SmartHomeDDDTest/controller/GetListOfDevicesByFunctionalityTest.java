package SmartHomeDDDTest.controller;

import SmartHomeDDD.controller.GetListOfDevicesByFunctionalityCTRL;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuator.RollerBlindActuator;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.domain.device.Device;
import SmartHomeDDD.domain.device.DeviceFactory;
import SmartHomeDDD.domain.sensor.HumiditySensor;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.domain.sensor.SunriseSensor;
import SmartHomeDDD.dto.DeviceDTO;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.repository.DeviceRepository;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.services.DeviceService;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.actuatorVO.ActuatorNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetListOfDevicesByFunctionalityTest {

    /**
     * This test ensures that when getListOfDevicesByFunctionality is called, within a success scenario, the return does
     * the following:
     * 1. Returns a map, with a string as key (related to the type) and a DeviceDTO list per key.
     * 2. No deviceDTO references should be duplicated inside a list of deviceDTO of a particular key.
     * 3. The deviceDTOs, when getName is called successfully return their deviceNames.
     * 4. To validate the above pressupositions, the test asserts that 1. The map has the expected keys. 2. Each key
     * has a particular device in their list. 3. No duplicated devices.
     * Note:
     * Device1 contains a SunriseSensor and SwitchSensor
     * Device2 contains a SunriseSensor, Switch Actuator, Switch Actuator (this is the duplicated type).
     * Device3 contains a SunsetSensor and RollerBlindActuator
     * Device4 contains a HumiditySensor
     * @throws ConfigurationException If actuator service path is invalid.
     */
    @Test
    void getListOfDevicesByFunctionalityTest_whenSuccessCase_DeliversAStrToDeviceDTOList() throws ConfigurationException {
        // Arrange

            // Device creation - RoomVO is created independently for brevity
        DeviceNameVO name1 = new DeviceNameVO("name1");
        DeviceModelVO model1 = new DeviceModelVO("model1");
        RoomIDVO room1 = new RoomIDVO(UUID.randomUUID());
        Device device1 = new Device(name1,model1,room1);

        DeviceNameVO name2 = new DeviceNameVO("name2");
        DeviceModelVO model2 = new DeviceModelVO("model2");
        RoomIDVO room2 = new RoomIDVO(UUID.randomUUID());
        Device device2 = new Device(name2,model2,room2);

        DeviceNameVO name3 = new DeviceNameVO("name3");
        DeviceModelVO model3 = new DeviceModelVO("model3");
        RoomIDVO room3 = new RoomIDVO(UUID.randomUUID());
        Device device3 = new Device(name3,model3,room3);

        DeviceNameVO name4 = new DeviceNameVO("name4");
        DeviceModelVO model4 = new DeviceModelVO("model4");
        RoomIDVO room4 = new RoomIDVO(UUID.randomUUID());
        Device device4 = new Device(name4,model4,room4);

            // Device repository instantiation + save
        DeviceRepository deviceRepository = new DeviceRepository();
        deviceRepository.save(device1);
        deviceRepository.save(device2);
        deviceRepository.save(device3);
        deviceRepository.save(device4);

            // Device1 sensors + actuators
        SensorNameVO sensorName1 = new SensorNameVO("sensor1");
        SensorTypeIDVO senType1 = new SensorTypeIDVO("SunriseSensor");
        SunriseSensor sensor1 = new SunriseSensor(sensorName1,device1.getId(),senType1);

        SensorNameVO sensorName2 = new SensorNameVO("sensor2");
        SensorTypeIDVO senType2 = new SensorTypeIDVO("SwitchSensor");
        SunriseSensor sensor2 = new SunriseSensor(sensorName2,device1.getId(),senType2);

            // Device2 sensors + actuators
        SensorNameVO sensorName3 = new SensorNameVO("sensor3");
        SunriseSensor sensor3 = new SunriseSensor(sensorName3,device2.getId(),senType1);

        ActuatorNameVO actuatorName1 = new ActuatorNameVO("actuator1");
        ActuatorTypeIDVO actType1 = new ActuatorTypeIDVO("SwitchActuator");
        SwitchActuator actuator1 = new SwitchActuator(actuatorName1,actType1,device2.getId());

        ActuatorNameVO actuatorName2 = new ActuatorNameVO("actuator2"); // Same type and device as actuator1
        SwitchActuator actuator2 = new SwitchActuator(actuatorName2,actType1,device2.getId());

            // Device3 sensors + actuators
        SensorNameVO sensorName4 = new SensorNameVO("sensor4");
        SensorTypeIDVO senType4 = new SensorTypeIDVO("SunsetSensor");
        SunriseSensor sensor4 = new SunriseSensor(sensorName4,device3.getId(),senType4);

        ActuatorNameVO actuatorName3 = new ActuatorNameVO("actuator3");
        ActuatorTypeIDVO actType2 = new ActuatorTypeIDVO("RollerBlindActuator");
        RollerBlindActuator actuator3 = new RollerBlindActuator(actuatorName3,actType2,device3.getId());

            // Device4 sensor
        SensorNameVO sensorName5 = new SensorNameVO("sensor5");
        SensorTypeIDVO senType5 = new SensorTypeIDVO("HumiditySensor");
        HumiditySensor sensor5 = new HumiditySensor(sensorName5,device4.getId(),senType5);

            // Sensor Repository instantiation + save
        SensorRepository sensorRepository = new SensorRepository();
        sensorRepository.save(sensor1);
        sensorRepository.save(sensor2);
        sensorRepository.save(sensor3);
        sensorRepository.save(sensor4);
        sensorRepository.save(sensor5);

            // Actuator Repository instantiation + save
        ActuatorRepository actuatorRepository = new ActuatorRepository();
        actuatorRepository.save(actuator1);
        actuatorRepository.save(actuator2);
        actuatorRepository.save(actuator3);

            // Services instantiation
        SensorFactory sensorFactory = new SensorFactory("sensor.properties");
        SensorService sensorService = new SensorService(sensorRepository,sensorFactory);

        ActuatorFactory actuatorFactory = new ActuatorFactory();
        ActuatorService actuatorService = new ActuatorService(actuatorFactory,actuatorRepository);

        DeviceFactory deviceFactory = new DeviceFactory();
        DeviceService deviceService = new DeviceService(deviceFactory,deviceRepository);

            // CTRL instantiation and getListOfDevices call to create resultMap
        GetListOfDevicesByFunctionalityCTRL ctrl = new GetListOfDevicesByFunctionalityCTRL(sensorService,actuatorService,deviceService);

        LinkedHashMap<String, List<DeviceDTO>> resultMap = ctrl.getListOfDevicesByFunctionality();

            // Expected keys
        List<String> expectedKeys = new ArrayList<>();
        expectedKeys.add("SunriseSensor");
        expectedKeys.add("SwitchSensor");
        expectedKeys.add("SunsetSensor");
        expectedKeys.add("HumiditySensor");
        expectedKeys.add("SwitchActuator");
        expectedKeys.add("RollerBlindActuator");

          List<String> expectedFirstKeyDTOnames = new ArrayList<>();
          expectedFirstKeyDTOnames.add("name1");
          expectedFirstKeyDTOnames.add("name2");

          List<String> expectedSecondKeyDTOnames = new ArrayList<>();
          expectedSecondKeyDTOnames.add("name1");

          List<String> expectedThirdKeyDTOnames = new ArrayList<>();
          expectedThirdKeyDTOnames.add("name3");

          List<String> expectedFourthKeyDTOnames = new ArrayList<>();
          expectedFourthKeyDTOnames.add("name4");

          List<String> expectedFifthKeyDTOnames = new ArrayList<>();
          expectedFifthKeyDTOnames.add("name2");

          List<String> expectedSixthKeyDTOnames = new ArrayList<>();
          expectedSixthKeyDTOnames.add("name3");


        // Act

            // prep to assert1 - testing key strings
        List<String> resultKeys = new ArrayList<>(resultMap.keySet());

            // prep to assert2 - testing each individual DTO name by key list
        List<String> resultFirstKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SunriseSensor")){
            resultFirstKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultSecondKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SwitchSensor")){
            resultSecondKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultThirdKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SunsetSensor")){
            resultThirdKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultFourthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("HumiditySensor")){
            resultFourthKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultFifthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("SwitchActuator")){
            resultFifthKeyDTOnames.add(dto.getDeviceName());
        }

        List<String> resultSixthKeyDTOnames = new ArrayList<>();
        for (DeviceDTO dto : resultMap.get("RollerBlindActuator")){
            resultSixthKeyDTOnames.add(dto.getDeviceName());
        }

        // Assert
        assertEquals(expectedKeys,resultKeys);

        assertEquals(expectedFirstKeyDTOnames,resultFirstKeyDTOnames);
        assertEquals(expectedSecondKeyDTOnames,resultSecondKeyDTOnames);
        assertEquals(expectedThirdKeyDTOnames,resultThirdKeyDTOnames);
        assertEquals(expectedFourthKeyDTOnames,resultFourthKeyDTOnames);
        assertEquals(expectedFifthKeyDTOnames,resultFifthKeyDTOnames);
        assertEquals(expectedSixthKeyDTOnames,resultSixthKeyDTOnames);

    }
}
