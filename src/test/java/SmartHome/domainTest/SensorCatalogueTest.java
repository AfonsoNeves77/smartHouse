package SmartHome.domainTest;
import SmartHome.domain.House;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.sensorImplementation.Sensor;
import SmartHome.domain.sensor.sensorImplementation.TemperatureSensor;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.plist.PropertyListConfiguration;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockConstruction;
import static org.mockito.Mockito.when;


class SensorCatalogueTest {

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
    void validCreationCatalogueUsingConfigurationObject() throws InstantiationException {

        //Arrange
        Configuration configuration = new PropertyListConfiguration();
        //Act
        SensorCatalogue catalogue = new SensorCatalogue(configuration);
        //Assert
        assertNotNull(catalogue);
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

    @Test
    void creatingTemperatureSensor_IsolationTest() throws InstantiationException
    {
        // arrange
        String sensorName = "SensorName";
        String sensorType = "SmartHome.domain.sensor.sensorImplementation.TemperatureSensor";

        try(MockedConstruction<TemperatureSensor> sensorDouble = mockConstruction(TemperatureSensor.class,(mock, context)-> {
            when(mock.getName()).thenReturn(sensorName);
        })) {

            SensorCatalogue sensorCatalogue = new SensorCatalogue("config.properties");

            // act
             Sensor tempSensor =  sensorCatalogue.createSensor(sensorName, sensorType);

            // assert
            List<TemperatureSensor> sensors = sensorDouble.constructed();
            assertEquals(1, sensors.size());

            assertEquals(sensorName, sensorDouble.constructed().get(0).getName());
            assertEquals(sensorName, tempSensor.getName());


        }

    }






}



