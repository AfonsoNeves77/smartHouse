package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.SunsetSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SunTimeValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SunsetSensorTest {
//....................................................ISOLATION TESTS...................................................

    /**
     * Successfully executes getSunsetTime() method.
     * Test that completely isolates SunsetSensor Class from its collaborators.
     * It is created a stub for the SunTimeCalculator Class, deciding on its behaviour once computeSunTime() method
     * is invoked. Then, a mocked instance of the SunTimeValue class is created using Mockito's MockedConstruction
     * feature. It mocks the behaviour of this Class when getValueAsString() method is called.
     * Here, two scenarios are shown:
     * 1. Testing the size of the list of constructed SunTimeValue objects from the mocked construction.
     * 2. Testing the result of the getSunsetTime() method is equal to the expected value (in String format).
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
    @Test
    void getSunsetTime_NonEmptyLog_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        String date = "2024-03-06";
        String coordinates = "41.1579 : -8.6291";
        String zoneValueAsString = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        ZonedDateTime zonedTimeParsed = ZonedDateTime.parse("2024-03-06T18:31:47Z[Europe/Lisbon]");

        SunTimeCalculator serviceDouble = mock(SunTimeCalculator.class);
        when(serviceDouble.computeSunsetTime(date,coordinates)).thenReturn(zonedTimeParsed);

        try(MockedConstruction<SunTimeValue> valueDouble = mockConstruction(SunTimeValue.class,(mock, context)-> {
            when(mock.getValueAsString()).thenReturn(zoneValueAsString);
        })) {
            List<SunTimeValue> logValues = valueDouble.constructed();

            SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);

            //Act
            Value<ZonedDateTime> sunTime = sunsetSensor.getSunsetTime(date,coordinates);

            //Assert
            // 1.
            assertEquals(1,logValues.size());
            // 2.
            assertEquals(zoneValueAsString,sunTime.getValueAsString());
        }
    }

    /**
     * Operation fails: Instantiation exception is thrown when result is not valid, i.e., it cannot be converted
     * to a SunTimeValue. It happens when computeSunTime() method returns null. Although an instantiation exception is
     * thrown, Mockito changes it for another exception, making it not possible to fully isolate this test case.
     * Test that partially isolates SunsetSensor Class from its collaborators (SunTimeValue Class is not mocked).
     * @throws InstantiationException If parameters for Sensor creation are invalid.
     */
    @Test
    void whenGetSunsetTime_ThenThrowsInstantiationException_PartialIsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        String date = "2024-03-06";
        String coordinates = "777.1579 : -8.6291";

        SunTimeCalculator serviceDouble = mock(SunTimeCalculator.class);
        when(serviceDouble.computeSunsetTime(date,coordinates)).thenReturn(null);
        String expected = "Invalid sun time value";
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);

        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            sunsetSensor.getSunsetTime(date,coordinates);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected, result);
    }

    /**
     * Tests SunsetSensor is created by accessing its log, which has no values by default.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
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

    /**
     * Tests SunsetSensor is created by checking its name.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
    @Test
    void createSensor_GetName_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        String expected = sensorName;
        //Act
        String result = sunsetSensor.getName();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests SunsetSensor is created by checking its type.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
    @Test
    void createSensor_GetType_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "KTP Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        String expected = "Sunset Sensor";
        //Act
        String result = sunsetSensor.getType();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that sensor cannot be created with an empty name.
     */
    @Test
    void createSensorWithEmptyName_IsolationTest(){
        //Arrange
        String sensorName = "";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        String expected = "Invalid parameters.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
           new SunsetSensor(sensorName, serviceDouble);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that sensor cannot be created with a null name.
     */
    @Test
    void createSensorWithNullName_IsolationTest(){
        //Arrange
        String sensorName = null;
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        String expected = "Invalid parameters.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new SunsetSensor(sensorName, serviceDouble);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that sensor cannot be created with null service.
     */
    @Test
    void createSensorWithNullService_UnitTest(){
        //Arrange
        String sensorName = "Sensor Test";
        ExternalServices service = null;
        String expected = "Invalid parameters.";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () -> {
            new SunsetSensor(sensorName, service);
        });
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests that a SunsetSensor is created by accessing to its unit.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
    @Test
    void getUnit_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunset Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunsetSensor sunsetSensor = new SunsetSensor(sensorName, serviceDouble);
        String expected = "Zoned Date Time";
        //Act
        String result = sunsetSensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

//....................................................INTEGRATION TESTS.................................................

    /**
     * Integration tests - Successfully retrieves sunset time for a given date and location.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
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

    /**
     * Integration tests - Operation fails since given location is not valid.
     * @throws InstantiationException If parameters for Sensor creation are invalid or SunTimeValue is null.
     */
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
}