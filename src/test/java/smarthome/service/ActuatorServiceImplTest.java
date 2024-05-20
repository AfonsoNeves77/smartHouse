package smarthome.service;

import smarthome.domain.actuator.*;
import smarthome.domain.device.Device;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.persistence.ActuatorRepository;
import smarthome.persistence.ActuatorTypeRepository;
import smarthome.persistence.DeviceRepository;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ActuatorServiceImplTest {

    /**
     * The following tests verify that if any of the parameters are null, ActuatorService instantiation should throw an
     * Illegal Argument Exception with the  message "Invalid Parameters".
     * Regarding test syntax it uses a reusable assertion method assertThrowsIllegalArgumentExceptionWithNullParameter()
     * for the similar nature of the following tests.
     * @param deviceRepository Device Repository
     * @param actuatorTypeRepository Actuator type repository
     * @param factoryActuator actuator factory
     * @param memActuatorRepository actuator repository
     */
    private void assertThrowsIllegalArgumentExceptionWithNullParameter(
            DeviceRepository deviceRepository,
            ActuatorTypeRepository actuatorTypeRepository,
            ActuatorFactory factoryActuator,
            ActuatorRepository memActuatorRepository) {

        //Arrange
        String expected = "Parameters cannot be null";

        //Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> new ActuatorServiceImpl(deviceRepository,actuatorTypeRepository,
                                            factoryActuator, memActuatorRepository));
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    /**
     *The following test verifies that if DeviceRepository parameter is null then ActuatorService instantiation should
     * throw an Illegal Argument Exception with the message "Invalid Parameters".
     */
    @Test
    void whenNullDeviceRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(null,actuatorTypeRepository,
                                                                actuatorFactory,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorTypeRepository parameter is null then ActuatorService instantiation
     * should throw an Illegal Argument Exception with the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorTypeRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,null,
                                                               actuatorFactory,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorFactory parameter is null then ActuatorService instantiation should
     * throw an Illegal Argument Exception with the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorFactory_thenThrowsIllegalArgumentException() {
        //Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,actuatorTypeRepository,
                                                                null,actuatorRepository);
    }

    /**
     *The following test verifies that if ActuatorRepository parameter is null then ActuatorService instantiation
     * should throw an Illegal Argument Exception with the message "Invalid Parameters".
     */
    @Test
    void whenNullActuatorRepository_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);

        //Assert
        assertThrowsIllegalArgumentExceptionWithNullParameter(deviceRepository,actuatorTypeRepository,
                                                            actuatorFactory,null);
    }

    /**
     * Test to verify that addActuator method throws IllegalArgumentException when receiving null parameters.
     *
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * correctly throws an {@code IllegalArgumentException} when any of the parameters passed to it are null.
     * </p>
     *
     * @throws IllegalArgumentException if any of the parameters passed to the {@code addActuator} method are null.
     */
    @Test
    void whenReceivingNullParameters_addActuatorThrowsIllegalArgumentException(){
        // Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository,actuatorTypeRepository,
                                                                actuatorFactory,actuatorRepository);

        String expected = "Parameters cannot be null";

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, () ->
                service.addActuator(null,actuatorTypeIDVO,deviceIDVO,settings));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO,null,deviceIDVO,settings));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO,actuatorTypeIDVO,null,settings));
        String result3 = exception3.getMessage();

        // Assert
        assertEquals(expected,result1); // null actuatorName
        assertEquals(expected,result2); // null actuatorTypeID
        assertEquals(expected,result3); // null deviceID
    }

    /**
     * Test to verify that addActuator method throws IllegalArgumentException when given a DeviceIDVO for an inactive
     * device.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * correctly throws an {@code IllegalArgumentException} when attempting to add an actuator to an inactive device.
     * </p>
     * @throws IllegalArgumentException if the device associated with the provided DeviceIDVO is not active.
     */
    @Test
    void givenDeviceIDVOForAnInactiveDevice_addActuatorThrowsIllegalArgumentException() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        Device device = mock(Device.class);

        // Mock behavior of DeviceRepository and Device
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(false);

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        // Expected error message
        String expected = "Device is not active";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that addActuator method throws IllegalArgumentException when given a DeviceIDVO for a non-existing
     * device.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * correctly throws an {@code IllegalArgumentException} when attempting to add an actuator to a device that does not
     * exist.
     * </p>
     * @throws IllegalArgumentException if the device associated with the provided DeviceIDVO does not exist.
     */
    @Test
    void givenDeviceIDVOForADeviceThatDoesNotExist_addActuatorThrowsIllegalArgumentException() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);

        // Mock behavior of DeviceRepository
        when(deviceRepository.findById(deviceIDVO)).thenReturn(null);

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        // Expected error message
        String expected = "Device is not active";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that addActuator method throws IllegalArgumentException when given an ActuatorTypeIDVO for a
     * non-existent actuator type.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * correctly throws an {@code IllegalArgumentException} when attempting to add an actuator with a non-existent
     * actuator type.
     * </p>
     * @throws IllegalArgumentException if the ActuatorTypeIDVO provided does not correspond to an existing actuator
     * type.
     */
    @Test
    void givenActuatorTypeIDVOForANonExistentActuatorType_addActuatorThrowsIllegalArgumentException() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        Device device = mock(Device.class);

        // Mock behavior of DeviceRepository and ActuatorTypeRepository
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(false);

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        // Expected error message
        String expected = "Actuator type is not present";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * Test to verify that addActuator method propagates IllegalArgumentException thrown by ActuatorFactory.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * correctly propagates an {@code IllegalArgumentException} thrown by the ActuatorFactory.
     * </p>
     * @throws IllegalArgumentException if the ActuatorFactory throws an IllegalArgumentException when attempting to
     * create an actuator.
     */
    @Test
    void givenInvalidVOs_ActuatorFactoryThrowsIllegalArgumentException_addActuatorPropagatesSaidException() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        Device device = mock(Device.class);

        // Mock behavior of DeviceRepository, ActuatorTypeRepository, and ActuatorFactory
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(actuatorFactory.createActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings))
                .thenThrow(new IllegalArgumentException("Invalid actuator parameters"));

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        // Expected error message
        String expected = "Invalid actuator parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }



    /**
     * Test to verify that addActuator method returns an empty Optional when ActuatorFactory returns null.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * returns an empty {@code Optional} when the ActuatorFactory returns null.
     * </p>
     * @see java.util.Optional
     */
    @Test
    void whenActuatorFactoryReturnsNull_addActuatorReturnsEmptyOptional() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        Device device = mock(Device.class);

        // Mock behavior of DeviceRepository, ActuatorTypeRepository, ActuatorFactory, and ActuatorRepository
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(actuatorFactory.createActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings)).thenReturn(null);
        when(actuatorRepository.save(null)).thenReturn(false);

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        Optional<Actuator> expected = Optional.empty();

        // Act
        Optional<Actuator> result = service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings);

        // Assert
        assertEquals(expected, result);
    }


    /**
     * Test to verify that addActuator method returns an Optional containing an Actuator object when given correct
     * parameters and no issues arise.
     * <p>
     * This test verifies that the {@code addActuator} method of the {@code ActuatorServiceImpl} class
     * returns an {@code Optional} containing an Actuator object when given correct parameters and no issues arise
     * during the process.
     * </p>
     * @see java.util.Optional
     */
    @Test
    void whenGivenCorrectParametersAndNoIssuesArise_addActuatorReturnsOptionalWithAnActuatorObject() {
        // Arrange
        // Mock repositories and factory
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        // Mock value objects and settings
        ActuatorNameVO actuatorNameVO = mock(ActuatorNameVO.class);
        ActuatorTypeIDVO actuatorTypeIDVO = mock(ActuatorTypeIDVO.class);
        DeviceIDVO deviceIDVO = mock(DeviceIDVO.class);
        Settings settings = mock(Settings.class);
        Device device = mock(Device.class);
        Actuator actuator = mock(Actuator.class);

        // Mock behavior of DeviceRepository, ActuatorTypeRepository, ActuatorFactory, and ActuatorRepository
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(device.isActive()).thenReturn(true);
        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(actuatorFactory.createActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings))
                .thenReturn(actuator);
        when(actuatorRepository.save(actuator)).thenReturn(true);

        // Create ActuatorServiceImpl instance with mocked dependencies
        ActuatorServiceImpl service = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        Optional<Actuator> expected = Optional.of(actuator);

        // Act
        Optional<Actuator> result = service.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings);

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that if the ActuatorIDVO is null, the getActuatorById method should throw an
     * IllegalArgumentException.
     * First it creates mocks of the DeviceRepository, ActuatorTypeRepository, ActuatorFactory and ActuatorRepository.
     * Then, it creates an ActuatorServiceImpl instance with the mocked dependencies.
     * Next, it creates an expected string with the error message.
     * It then creates an exception variable that is assigned the result of the getActuatorById method called with a
     * null argument and retrieves the message of the exception to a string variable.
     * Finally, it asserts that the expected string is equal to the result of the exception message.
     */
    @Test
    void givenNullActuatorIDVO_whenGetActuatorByIDCalled_thenReturnsIllegalArgumentException() {
        //Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        ActuatorService actuatorService = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        String expected = "ActuatorIDVO cannot be null";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                actuatorService.getActuatorById(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that if the ActuatorIDVO is not present in the ActuatorRepository it returns an empty
     * Optional.
     * It creates mocks of the DeviceRepository, ActuatorTypeRepository, ActuatorFactory and ActuatorRepository.
     * Next, it creates an ActuatorIDVO mock and sets the behavior of the ActuatorRepository to return false when
     * the isPresent method is called with the ActuatorIDVO as an argument.
     * Then, it creates an ActuatorServiceImpl instance with the mocked dependencies.
     * After that, it creates an Optional variable and assigns the result of the getActuatorById method called with the
     * ActuatorIDVO as an argument.
     * Finally, it asserts that the Optional is empty.
     */
    @Test
    void givenNonExistentActuatorIDVO_whenGetActuatorByIDCalled_thenReturnsEmptyOptional() {
        //Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        ActuatorIDVO actuatorIDVO = mock(ActuatorIDVO.class);
        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(false);

        ActuatorService actuatorService = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        //Act
        Optional<Actuator> result = actuatorService.getActuatorById(actuatorIDVO);

        //Assert
        assertTrue(result.isEmpty());
    }

    /**
     * This test verifies that if the ActuatorIDVO is present in the ActuatorRepository it returns an Optional with the
     * Actuator.
     * It creates mocks of the DeviceRepository, ActuatorTypeRepository, ActuatorFactory and ActuatorRepository.
     * Next, it creates an ActuatorIDVO mock and an Actuator mock.
     * Then, it sets the behavior of the ActuatorRepository to return true when the isPresent method is called with the
     * ActuatorIDVO as an argument, and to return the actuator double when the findById method is called with the
     * ActuatorIDVO as an argument.
     * After that, it creates an ActuatorServiceImpl instance with the mocked dependencies.
     * It then creates an Optional variable and assigns the result of the getActuatorById method called with the
     * ActuatorIDVO as an argument.
     * Finally, it asserts that the Optional is present and that is equal to the actuator double.
     */
    @Test
    void givenExistentActuatorIDVO_whenGetActuatorByIDCalled_thenReturnsActuatorOptional() {
        //Arrange
        DeviceRepository deviceRepository = mock(DeviceRepository.class);
        ActuatorTypeRepository actuatorTypeRepository = mock(ActuatorTypeRepository.class);
        ActuatorFactory actuatorFactory = mock(ActuatorFactory.class);
        ActuatorRepository actuatorRepository = mock(ActuatorRepository.class);

        ActuatorIDVO actuatorIDVO = mock(ActuatorIDVO.class);
        Actuator actuatorDouble = mock(Actuator.class);

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(true);
        when(actuatorRepository.findById(actuatorIDVO)).thenReturn(actuatorDouble);

        ActuatorService actuatorService = new ActuatorServiceImpl(deviceRepository, actuatorTypeRepository,
                actuatorFactory, actuatorRepository);

        //Act
        Optional<Actuator> result = actuatorService.getActuatorById(actuatorIDVO);

        //Assert
        assertTrue(result.isPresent());
        assertEquals(actuatorDouble, result.get());
    }
}
