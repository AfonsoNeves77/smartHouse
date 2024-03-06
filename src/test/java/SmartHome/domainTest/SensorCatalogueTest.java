package SmartHome.domainTest;
import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.Actuator;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.actuator.SwitchActuator;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.SunriseSensor;
import SmartHome.domain.sensor.sensorImplementation.TemperatureSensor;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;


class SensorCatalogueTest {


    //.............................................................................ISOLATION TESTS..............................................................................

    @Test
    void creatingSunriseSensor_IsolationTest() throws InstantiationException
    {
        // arrange
        String expected = "Sunrise Sensor";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.SunriseSensor";
        SunTimeCalculator sunTimeCalculatorDouble = mock(SunTimeCalculator.class);

        try(MockedConstruction<SunriseSensor> sensorDouble = mockConstruction(SunriseSensor.class,(mock, context)-> {
            when(mock.getType()).thenReturn(expected);
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

    @Test
    void sensorTypeDoesntResist_ShouldReturnNull_IsolationTest() throws InstantiationException {

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

    @Test
    void invalidCreationCatalogue_BlancFilePath(){

        //Arrange
        String filePath = "      ";
        String expected = "Invalid parameters";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }
    @Test
    void invalidCreationCatalogue_InvalidFilePath(){

        //Arrange
        String filePath = "cbdhwbdcvjvjv";
        String expected = "Error reading file";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> new SensorCatalogue(filePath));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getListOfSensorTypes() throws InstantiationException {
        //Arrange
        SensorCatalogue catalogue = new SensorCatalogue("config.properties");
        String expected = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";
        //Act
        List<String> listOfSensorTypes= catalogue.getListOfInstantiableSensors();
        String result = listOfSensorTypes.get(1);
        //Assert
        assertEquals(expected,result);
    }

    //.............................................................................INTEGRATION TESTS..............................................................................


    @Test
    void sensorCatalogueInitializedWithConfiguration_ShouldReturnSensorTypeList() throws InstantiationException {

        //Arrange
        Configuration configuration = new PropertyListConfiguration();
        configuration.addProperty("sensor","SensorTypeOne");
        configuration.addProperty("sensor","SensorTypeTwo");
        SensorCatalogue catalogue = new SensorCatalogue(configuration);
        String expected = "SensorTypeTwo";

        //Act
        List<String> listOfSensorTypes= catalogue.getListOfInstantiableSensors();
        String result = listOfSensorTypes.get(1);

        //Assert
        assertEquals(expected,result);
    }

}



