package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.actuator.Actuator;
import SmartHomeDDD.domain.actuator.ActuatorFactory;
import SmartHomeDDD.domain.actuator.RollerBlindActuator;
import SmartHomeDDD.domain.actuator.SwitchActuator;
import SmartHomeDDD.domain.sensor.Sensor;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.domain.sensor.SunriseSensor;
import SmartHomeDDD.domain.sensor.SwitchSensor;
import SmartHomeDDD.repository.ActuatorRepository;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.services.ActuatorService;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.vo.actuatorType.ActuatorTypeIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SensorServiceTest {

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid Repository
     */
    @Test
    void whenGivenAnInvalidRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        SensorFactory factory = mock(SensorFactory.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorService(null, factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that the constructor throws an Illegal Argument exception when receiving an invalid Factory
     */
    @Test
    void whenGivenAnInvalidFactory_ConstructorThrowsIllegalArgument(){
        // Arrange
        SensorRepository repository = mock(SensorRepository.class);
        String expected = "Invalid repository";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new SensorService(repository, null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that the method create sensor returns null if receiving sensorNameVO is null
     */
    @Test
    void whenCreateSensorIsGivenInvalidSensorNameVO_returnsNull(){
        // Arrange
        SensorRepository repository = mock(SensorRepository.class);
        SensorFactory factory = mock(SensorFactory.class);

        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        Sensor result = service.createSensor(null,deviceID,sensorTypeID);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that the method create sensor returns null if receiving DeviceIDVO is null
     */
    @Test
    void whenCreateSensorIsGivenInvalidDeviceIDVO_returnsNull(){
        // Arrange
        SensorRepository repository = mock(SensorRepository.class);
        SensorFactory factory = mock(SensorFactory.class);

        SensorNameVO sensorName = mock(SensorNameVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        Sensor result = service.createSensor(sensorName,null,sensorTypeID);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that the method create sensor returns null if receiving SensorTypeIDVO is null
     */
    @Test
    void whenCreateSensorIsGivenInvalidSensorTypeIDVO_returnsNull(){
        // Arrange
        SensorRepository repository = mock(SensorRepository.class);
        SensorFactory factory = mock(SensorFactory.class);

        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        Sensor result = service.createSensor(sensorName,deviceID,null);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that when create sensor is called with valid parameters and all the communication with the factory
     * is successful, returns a Sensor Object.
     */
    @Test
    void whenCreateSensorIsCalledWithValidParameters_returnsSensorObject(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SensorRepository repository = mock(SensorRepository.class);

        Sensor sensor = mock(Sensor.class);

        SensorFactory factory = mock(SensorFactory.class);
        when(factory.createSensor(sensorName,deviceID,sensorTypeID)).thenReturn(sensor);

        SensorService service = new SensorService(repository,factory);

        // Act
        Sensor result = service.createSensor(sensorName,deviceID,sensorTypeID);

        // Assert
        assertEquals(sensor,result);
    }

    /**
     * This test ensures that when create sensor is called with valid parameters, if there is an issue at the factory level
     * regarding the Sensor creation, it propagates the receiving return (null)
     */
    @Test
    void whenCreateSensorIsCalledWithValidParameters_ifThereIsAnyIssueWithRepo_propagatesNull(){
        // Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorTypeID = mock(SensorTypeIDVO.class);

        SensorRepository repository = mock(SensorRepository.class);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        Sensor result = service.createSensor(sensorName,deviceID,sensorTypeID);

        // Assert
        assertNull(result);
    }

    /**
     * This test ensures that when SaveSensor is called with valid parameters, returns true
     */
    @Test
    void whenSaveSensorIsCalledWithValidParameters_ReturnsTrue(){
        // Arrange
        Sensor sensor = mock(Sensor.class);

        SensorRepository repository = mock(SensorRepository.class);
        when(repository.save(sensor)).thenReturn(true);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        boolean result = service.saveSensor(sensor);

        // Assert
        assertTrue(result);
    }

    /**
     * This test ensures that if saveSensor receives a null Sensor, automatically returns false
     */
    @Test
    void whenSaveSensorIsCalledWithNullSensor_ReturnsFalse(){
        // Arrange
        SensorRepository repository = mock(SensorRepository.class);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        boolean result = service.saveSensor(null);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that if saveSensor receives a valid Sensor, but there was an issue saving it, it
     * propagates the return from the repository (false)
     */
    @Test
    void whenSaveSensorIsCalledWithValidParameters_ifThereIsAnIssueWithTheRepo_propagatesReturn(){
        // Arrange
        Sensor sensor = mock(Sensor.class);
        SensorRepository repository = mock(SensorRepository.class);
        when(repository.save(sensor)).thenReturn(false);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        // Act
        boolean result = service.saveSensor(sensor);

        // Assert
        assertFalse(result);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, only returns a map with 2 entries. Entries are related to the unique types.
     */
    @Test
    void getListOfDeviceIDByFunctionality_whenRepositoryHas3devicesButTwoRepeatedTypes_returnHasSizeTwo(){
        // Arrange
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        SensorTypeIDVO type1 = mock(SensorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        SensorTypeIDVO type2 = mock(SensorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        SensorTypeIDVO type3 = mock(SensorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Sensor sensor1 = mock(SwitchSensor.class);
        when(sensor1.getSensorTypeID()).thenReturn(type1);
        when(sensor1.getDeviceID()).thenReturn(id1);
        Sensor sensor2 = mock(SunriseSensor.class);
        when(sensor2.getSensorTypeID()).thenReturn(type2);
        when(sensor2.getDeviceID()).thenReturn(id2);
        Sensor sensor3 = mock(SwitchSensor.class);
        when(sensor3.getSensorTypeID()).thenReturn(type3);
        when(sensor3.getDeviceID()).thenReturn(id3);

        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);

        SensorRepository repository = mock(SensorRepository.class);
        when(repository.findAll()).thenReturn(sensors);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality();

        int expectedSize = 2;
        // Act
        int resultSize = map.size();

        // Assert
        assertEquals(expectedSize,resultSize);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, it returns a map where the first two keys match the expected ones.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_theReturningFirstTwoKeysAreSimilar(){
        // Arrange
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        SensorTypeIDVO type1 = mock(SensorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        SensorTypeIDVO type2 = mock(SensorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        SensorTypeIDVO type3 = mock(SensorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Sensor sensor1 = mock(SwitchSensor.class);
        when(sensor1.getSensorTypeID()).thenReturn(type1);
        when(sensor1.getDeviceID()).thenReturn(id1);
        Sensor sensor2 = mock(SunriseSensor.class);
        when(sensor2.getSensorTypeID()).thenReturn(type2);
        when(sensor2.getDeviceID()).thenReturn(id2);
        Sensor sensor3 = mock(SwitchSensor.class);
        when(sensor3.getSensorTypeID()).thenReturn(type3);
        when(sensor3.getDeviceID()).thenReturn(id3);

        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);

        SensorRepository repository = mock(SensorRepository.class);
        when(repository.findAll()).thenReturn(sensors);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality();

        String expectedFirstKey = "SwitchActuator";
        String expectedSecondKey = "RollerBlindActuator";
        String expectedThirdKey = null;
        // Act
        String resultFirstKey = map.keySet().stream().findFirst().orElse(null);
        String resultSecondKey = map.keySet().stream().skip(1).findFirst().orElse(null);
        String resultThirdKey = map.keySet().stream().skip(2).findFirst().orElse(null);

        // Assert
        assertEquals(expectedFirstKey,resultFirstKey);
        assertEquals(expectedSecondKey,resultSecondKey);
        assertEquals(expectedThirdKey,resultThirdKey);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types, it returns a map where the values of first two keys match the expected ones.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_ReturnsListOfExpectedDeviceIDRefs(){
        // Arrange
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        SensorTypeIDVO type1 = mock(SensorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        SensorTypeIDVO type2 = mock(SensorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        SensorTypeIDVO type3 = mock(SensorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Sensor sensor1 = mock(SwitchSensor.class);
        when(sensor1.getSensorTypeID()).thenReturn(type1);
        when(sensor1.getDeviceID()).thenReturn(id1);
        Sensor sensor2 = mock(SunriseSensor.class);
        when(sensor2.getSensorTypeID()).thenReturn(type2);
        when(sensor2.getDeviceID()).thenReturn(id2);
        Sensor sensor3 = mock(SwitchSensor.class);
        when(sensor3.getSensorTypeID()).thenReturn(type3);
        when(sensor3.getDeviceID()).thenReturn(id3);

        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);

        SensorRepository repository = mock(SensorRepository.class);
        when(repository.findAll()).thenReturn(sensors);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality();

        String firstKey = map.keySet().stream().findFirst().orElse(null);
        String secondKey = map.keySet().stream().skip(1).findFirst().orElse(null);

        String firstListIDRefs = "[" + id1.toString() + ", " + id3.toString() + "]";
        String secondListIDRefs = "[" + id2.toString() + "]";

        // Act
        String resultfirstKey = map.get(firstKey).toString();
        String resultsecondKey = map.get(secondKey).toString();

        // Assert
        assertEquals(firstListIDRefs,resultfirstKey);
        assertEquals(secondListIDRefs,resultsecondKey);
    }

    /**
     * This test ensures that when getListOfDeviceIDByFunctionality is called, when communicating with a repository with
     * three devices that have two repeated types and 2 similar DeviceIDs, it returns a map where the values of first
     * two keys match the expected ones, considering no DeviceIDs should be duplicated within the same key:value pair.
     */
    @Test
    void whenRepositoryHasThreeDevicesButOnlyTwoTypesOfID_getListOfDevicesByFuncionalities_ReturnsListOfExpectedDeviceIDRefsWithNoDuplicatedIds(){
        // Arrange
        DeviceIDVO id1 = mock(DeviceIDVO.class);
        when(id1.getID()).thenReturn("id1");
        DeviceIDVO id2 = mock(DeviceIDVO.class);
        when(id2.getID()).thenReturn("id2");
        DeviceIDVO id3 = mock(DeviceIDVO.class);
        when(id3.getID()).thenReturn("id3");

        SensorTypeIDVO type1 = mock(SensorTypeIDVO.class);
        when(type1.getID()).thenReturn("SwitchActuator");
        SensorTypeIDVO type2 = mock(SensorTypeIDVO.class);
        when(type2.getID()).thenReturn("RollerBlindActuator");
        SensorTypeIDVO type3 = mock(SensorTypeIDVO.class);
        when(type3.getID()).thenReturn("SwitchActuator");

        Sensor sensor1 = mock(SwitchSensor.class);
        when(sensor1.getSensorTypeID()).thenReturn(type1);
        when(sensor1.getDeviceID()).thenReturn(id1);
        Sensor sensor2 = mock(SunriseSensor.class);
        when(sensor2.getSensorTypeID()).thenReturn(type2);
        when(sensor2.getDeviceID()).thenReturn(id2);
        Sensor sensor3 = mock(SwitchSensor.class);
        when(sensor3.getSensorTypeID()).thenReturn(type3);
        when(sensor3.getDeviceID()).thenReturn(id3);

        Sensor sensor4 = mock(SwitchSensor.class);
        when(sensor4.getSensorTypeID()).thenReturn(type3);
        when(sensor4.getDeviceID()).thenReturn(id3);

        List<Sensor> sensors = new ArrayList<>();
        sensors.add(sensor1);
        sensors.add(sensor2);
        sensors.add(sensor3);

        SensorRepository repository = mock(SensorRepository.class);
        when(repository.findAll()).thenReturn(sensors);

        SensorFactory factory = mock(SensorFactory.class);

        SensorService service = new SensorService(repository,factory);

        Map<String, List<DeviceIDVO>> map = service.getListOfDeviceIDsByFunctionality();

        String firstKey = map.keySet().stream().findFirst().orElse(null);
        String secondKey = map.keySet().stream().skip(1).findFirst().orElse(null);

        String firstListIDRefs = "[" + id1.toString() + ", " + id3.toString() + "]";
        String secondListIDRefs = "[" + id2.toString() + "]";

        // Act
        String resultfirstKey = map.get(firstKey).toString();
        String resultsecondKey = map.get(secondKey).toString();

        // Assert
        assertEquals(firstListIDRefs,resultfirstKey);
        assertEquals(secondListIDRefs,resultsecondKey);
    }
}
