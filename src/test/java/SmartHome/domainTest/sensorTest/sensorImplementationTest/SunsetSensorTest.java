package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.SunsetSensor;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class SunsetSensorTest {

    @Test
    void getSunsetTime_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, service);
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        //Act
        String result = sunsetSensor.getSunsetTime(date, coordinates).getValueAsString();
        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getSunsetTime_ErrorResult_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, service);
        String date = "2024-03-06";
        String coordinates = "888.1579 : -7.6291";
        String expected = "Invalid sun time value";
        Exception exception = assertThrows(InstantiationException.class, () ->{
            sunsetSensor.getSunsetTime(date,coordinates);
        });
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getLog_EmptyLog_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        int expected = 0;
        //Act
        int  result = sunsetSensor.getLog().size();
        //Assert
        assertEquals(expected,result);
    }

    @Test
    void getName_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        String expected = "Zone Date Time";
        //Act
        String result = sunsetSensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    @Test
    void getUnit_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        String expected = "Zone Date Time";
        //Act
        String result = sunsetSensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }


}