package SmartHomeDDDTest.servicesTest;

import SmartHomeDDD.domain.sensor.Sensor;
import SmartHomeDDD.domain.sensor.SensorFactory;
import SmartHomeDDD.repository.SensorRepository;
import SmartHomeDDD.services.SensorService;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;

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
}
