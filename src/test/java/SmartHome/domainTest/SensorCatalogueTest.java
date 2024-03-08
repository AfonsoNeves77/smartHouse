package SmartHome.domainTest;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.SunriseSensor;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


class SensorCatalogueTest {



    /**
     * Unit test ensuring the correct creation of a SunriseSensor using the SensorCatalogue class with isolation.

     *  The test verifies that the SensorCatalogue can properly create a SunriseSensor instance.
     *  It uses the MockedConstruction feature to isolate the construction of SunriseSensor and
     *  asserts that the created sensor has the expected type.

     *  Throws InstantiationException if an unexpected issue occurs during instantiation
     */
    @Test
    void creatingSunriseSensor_IsolationTest() throws InstantiationException
    {
        // arrange
        String expected = "Sunrise Sensor";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.SunriseSensor";
        SunTimeCalculator sunTimeCalculatorDouble = mock(SunTimeCalculator.class);

        try(MockedConstruction<SunriseSensor> sensorDouble = mockConstruction(SunriseSensor.class,(mock, context)-> {
            String actualType = (String) context.arguments().get(0);
            when(mock.getType()).thenReturn(actualType);
        })) {

            SensorCatalogue sensorCatalogue = new SensorCatalogue("config.properties");

            // act
            SunriseSensor sunriseSensor = (SunriseSensor) sensorCatalogue.createSensor(expected, sensorType,sunTimeCalculatorDouble);

            // assert
            List<SunriseSensor> sensors = sensorDouble.constructed();
            assertEquals(1, sensors.size());
            assertEquals(expected, sunriseSensor.getType());
        }

    }

    /**
     * Unit test to verify the behavior when a sensor throws InstantiationException during construction.

     * The test ensures that if a sensor construction fails with InstantiationException,
     * the SensorCatalogue should return null, and no instances of the sensor are constructed.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */

    @Test
    void sensorThrowsInstantiationException_ShouldReturnNull_IsolationTest() throws InstantiationException {

        // arrange
        String sensorName = "Sensor One";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.SunriseSensor";
        SunTimeCalculator sunTimeCalculatorDouble = mock(SunTimeCalculator.class);

        try(MockedConstruction<SunriseSensor> sensorDouble = mockConstruction(SunriseSensor.class,(mock, context)-> {
            throw new InstantiationException("Invalid Parameters");
        })) {

            SensorCatalogue sensorCatalogue = new SensorCatalogue("config.properties");

            // act
            Sensor sensor = sensorCatalogue.createSensor(sensorName, sensorType,sunTimeCalculatorDouble);

            // assert
            List<SunriseSensor> sensors = sensorDouble.constructed();
            assertEquals(0, sensors.size());
            assertNull(sensor);
        }

    }

    /**
     * Unit test to verify the behavior when a sensor type doesn't exist in the SensorCatalogue.

     * The test ensures that if a sensor type specified in the SensorCatalogue doesn't exist,
     * the SensorCatalogue should return null, and no instances of the sensor are constructed.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */

    @Test
    void sensorTypeDoesntExist_ShouldReturnNull_IsolationTest() throws InstantiationException {

        // arrange
        String sensorName = "Sensor One";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.NOXSensor";
        SunTimeCalculator sunTimeCalculatorDouble = mock(SunTimeCalculator.class);

        try(MockedConstruction<SunriseSensor> sensorDouble = mockConstruction(SunriseSensor.class,(mock, context)-> {
            throw new InstantiationException("Invalid Parameters");
        })) {

            SensorCatalogue sensorCatalogue = new SensorCatalogue("config.properties");

            // act
            Sensor sensor = sensorCatalogue.createSensor(sensorName, sensorType,sunTimeCalculatorDouble);

            // assert
            List<SunriseSensor> sensors = sensorDouble.constructed();
            assertEquals(0, sensors.size());
            assertNull(sensor);
        }

    }

    /**
     * Unit test to verify the behavior when attempting to create a SensorCatalogue with a null Configuration object.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */

    @Test
    void invalidCreationCatalogue_NullConfigurationObject(){

        //Arrange
        Configuration configuration = null;
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(configuration));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Unit test to verify the behavior when attempting to create a SensorCatalogue with an empty file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */

    @Test
    void invalidCreationCatalogue_EmptyFilePath(){

        //Arrange
        String filePath = "";
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Unit test to verify the behavior when attempting to create a SensorCatalogue with a null file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */


    @Test
    void invalidCreationCatalogue_NullFilePath(){

        //Arrange
        String filePath = null;
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Unit test to verify the behavior when attempting to create a SensorCatalogue with a blank file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Invalid parameters."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_BlankFilePath() {
        // Arrange
        String filePath = "      ";
        String expectedErrorMessage = "Invalid parameters";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior when attempting to create a SensorCatalogue with an invalid file path.

     * The test checks for the following:
     * - An InstantiationException is expected to be thrown.
     * - The expected error message is "Error reading file."

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void invalidCreationCatalogue_InvalidFilePath() {
        // Arrange
        String filePath = "cbdhwbdcvjvjv";
        String expectedErrorMessage = "Error reading file";

        // Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String actualErrorMessage = exception.getMessage();

        // Assert
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }

    /**
     * Unit test to verify the behavior of getting the list of sensor types from a SensorCatalogue initialized with a valid configuration file.

     * The test checks for the following:
     * - The SensorCatalogue is created successfully with a valid configuration file.
     * - The list of instantiable sensor types is retrieved, and the second element is checked against the expected value.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void getListOfSensorTypes() throws InstantiationException {
        // Arrange
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        String expectedSensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";

        // Act
        List<String> listOfSensorTypes = catalogue.getListOfInstantiableSensors();
        String actualSensorType = listOfSensorTypes.get(1);

        // Assert
        assertEquals(expectedSensorType, actualSensorType);
    }

    /**
     * Integration test to verify the behavior of initializing a SensorCatalogue with a Configuration object.

     * The test checks for the following:
     * - A Configuration object is created with sensor types.
     * - A SensorCatalogue is created with the Configuration object.
     * - The list of instantiable sensor types is retrieved, and the second element is checked against the expected value.

     * Throws InstantiationException if an unexpected issue occurs during instantiation.
     */
    @Test
    void sensorCatalogueInitializedWithConfiguration_ShouldReturnSensorTypeList() throws InstantiationException {
        // Arrange
        Configuration configuration = new PropertyListConfiguration();
        configuration.addProperty("sensor", "SensorTypeOne");
        configuration.addProperty("sensor", "SensorTypeTwo");
        SensorCatalogue catalogue = new SensorCatalogue(configuration);
        String expectedSensorType = "SensorTypeTwo";

        // Act
        List<String> listOfSensorTypes = catalogue.getListOfInstantiableSensors();
        String actualSensorType = listOfSensorTypes.get(1);

        // Assert
        assertEquals(expectedSensorType, actualSensorType);
    }

}



