package SmartHome.domainTest.sensorTest.sensorImplementationTest;

import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.externalServices.SunTimeCalculator;
import SmartHome.domain.sensor.sensorImplementation.SunriseSensor;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.SunTimeValue;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.time.ZonedDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SunriseSensorTest {

    //.............................................................................ISOLATION TESTS..............................................................................

    /**
     * Unit test for ensuring that an InstantiationException is thrown
     * when attempting to create a SunriseSensor with a null sensorName.
     */

    @Test
    void whenNullName_thenThrowsInstantiationException_IsolationTest(){

        //Arrange
        String sensorName = null;
        SunTimeCalculator service = mock(SunTimeCalculator.class);
        String expected = "Invalid parameters";

       //Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SunriseSensor(sensorName,service));
        String result = exception.getMessage();

        assertEquals(result,expected);
    }

    /**
     * Unit test for ensuring that an InstantiationException is thrown
     * when attempting to create a SunriseSensor with an empty sensorName.
     */

    @Test
    void whenEmptyName_thenThrowsInstantiationException_IsolationTest(){

        //Arrange
        String sensorName = "";
        SunTimeCalculator service = mock(SunTimeCalculator.class);
        String expected = "Invalid parameters";


        //Act + Assert
        Exception exception = assertThrows(InstantiationException.class, () -> new SunriseSensor(sensorName,service));
        String result = exception.getMessage();

        assertEquals(result,expected);
    }

    /**
     * Unit test for verifying the behavior of the getSunriseTime method
     * in the SunriseSensor class under isolated conditions.
     * This test ensures that the method constructs the expected SunTimeValue,
     * retrieves the correct result, and maintains the expected list size.
     * The test uses mocking to isolate the SunTimeCalculator service and
     * MockedConstruction for SunTimeValue instances.
     * @throws InstantiationException If sensor parameters invalid.
     */


    @Test
    void getSunriseTime_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        String date = "2024-03-20";
        String coordinates = "88.1579 : 3.6291";
        String expected = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        ZonedDateTime zoneDateTimeParsed = ZonedDateTime.parse(expected);
        int expectedSize = 1;


        SunTimeCalculator service = mock(SunTimeCalculator.class);
        when(service.computeSunrise(date, coordinates)).thenReturn(zoneDateTimeParsed);

        try (MockedConstruction<SunTimeValue> mockedConstruction = mockConstruction(SunTimeValue.class, (mock, context) -> {
            when(mock.getValueAsString()).thenReturn(expected);
        })) {

            //Act
            SunriseSensor sunriseSensor = new SunriseSensor(sensorName, service);
            List<SunTimeValue> sunTimesConstructed = mockedConstruction.constructed();

            Value<ZonedDateTime> resultValue = sunriseSensor.getSunriseTime(date, coordinates);
            String result = resultValue.getValueAsString();
            int resultSize = sunTimesConstructed.size();


            //Assert
            assertEquals(expected, result);
            assertEquals(expectedSize,resultSize);

        }
    }

    /**
     * Unit test for verifying that the getSunriseTime method in the SunriseSensor class
     * correctly throws InstantiationException when the SunTimeCalculator service returns null,
     * indicating an invalid sun time value. This test isolates the behavior of the
     * SunriseSensor class while mocking the SunTimeCalculator service.
     * The test sets up the SunTimeCalculator mock to return null, simulating an invalid calculation.
     * It then creates a SunriseSensor instance and asserts that calling getSunriseTime with the
     * invalid coordinates results in an InstantiationException with the expected error message.
     @throws InstantiationException If sensor parameters invalid.
     */


    @Test
    void getSunriseTime_ShouldThrowInstantiationException_IsolatingSunTimeCalculator() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        String date = "2024-03-20";
        String coordinates = "88.1579 : 77.6291";
        String expected = "Invalid sun time value";


        SunTimeCalculator service = mock(SunTimeCalculator.class);
        when(service.computeSunrise(date, coordinates)).thenReturn(null);

        //Act
        SunriseSensor sunriseSensor = new SunriseSensor(sensorName,service);

        //Assert
        Exception exception = assertThrows(InstantiationException.class, () -> sunriseSensor.getSunriseTime(date,coordinates));
        String result = exception.getMessage();

        assertEquals(result,expected);

    }

    /**
     * Unit test for verifying that the getLog method in the SunriseSensor class
     * returns an empty log when no sunrise time calculations have been performed.
     * This test isolates the behavior of the SunriseSensor class by using a mock
     * for the ExternalServices (SunTimeCalculator) and creating an instance of SunriseSensor.

     * The test initializes the SunriseSensor with a mock SunTimeCalculator and then
     * asserts that calling getLog on the SunriseSensor instance results in an empty log,
     * as no sunrise time calculations have been performed yet.
     @throws InstantiationException If sensor parameters invalid.
     */

    @Test
    void getLog_EmptyLog_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, serviceDouble);
        int expected = 0;
        //Act
        int  result = SunriseSensor.getLog().size();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Unit test for verifying that the getLog method in the SunriseSensor class
     * returns a log with one record after performing a sunrise time calculation.
     * This test isolates the behavior of the SunriseSensor class by using a mock
     * for the SunTimeCalculator and MockedConstruction for SunTimeValue instances.

     * The test sets up the SunTimeCalculator mock to return a specific ZonedDateTime,
     * simulating a successful sunrise time calculation. It then creates a SunriseSensor
     * instance and performs a sunrise time calculation. The test verifies that calling
     * getLog on the SunriseSensor instance results in a log containing the expected record.
     *  @throws InstantiationException If sensor parameters invalid.
     */
    @Test
    void getLog_LogWithOneRecord_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        String date = "2024-03-20";
        String coordinates = "88.1579 : 3.6291";
        String expected = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        ZonedDateTime zoneDateTimeParsed = ZonedDateTime.parse(expected);


        SunTimeCalculator service = mock(SunTimeCalculator.class);
        when(service.computeSunrise(date, coordinates)).thenReturn(zoneDateTimeParsed);

        try (MockedConstruction<SunTimeValue> mockedConstruction = mockConstruction(SunTimeValue.class, (mock, context) -> {
            when(mock.getValueAsString()).thenReturn(expected);
        })) {

            //Act
            SunriseSensor sunriseSensor = new SunriseSensor(sensorName, service);
            sunriseSensor.getSunriseTime(date, coordinates);

            List<String> log = sunriseSensor.getLog();
            String result = log.get(0);

            //Assert
            assertEquals(expected, result);

        }

    }

    /**
     * Unit test for verifying that the getLog method in the SunriseSensor class
     * returns a log with two records after performing two sunrise time calculations.
     * This test isolates the behavior of the SunriseSensor class by using a mock
     * for the SunTimeCalculator and MockedConstruction for SunTimeValue instances.
     *
     * The test sets up the SunTimeCalculator mock to return a specific ZonedDateTime,
     * simulating a successful sunrise time calculation. It then creates a SunriseSensor
     * instance and performs two sunrise time calculations. The test verifies that calling
     * getLog on the SunriseSensor instance results in a log containing the expected records.
     *  @throws InstantiationException If sensor parameters invalid.
     */

    @Test
    void getLog_LogWithTwoRecords_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        String date = "2024-03-20";
        String coordinates = "88.1579 : 3.6291";
        String expectedTwo = "2024-03-06T18:31:47Z[Europe/Lisbon]";
        String expected = "2024-03-06T18:39:47Z[Europe/Lisbon]";
        ZonedDateTime zoneDateTimeParsed = ZonedDateTime.parse(expected);
        int expectedLogSize = 2;


        SunTimeCalculator service = mock(SunTimeCalculator.class);
        when(service.computeSunrise(date, coordinates)).thenReturn(zoneDateTimeParsed);

        try (MockedConstruction<SunTimeValue> mockedConstruction = mockConstruction(SunTimeValue.class, (mock, context) -> {
            if (context.getCount() == 0) {

                when(mock.getValueAsString()).thenReturn(expectedTwo);
            } else {

                when(mock.getValueAsString()).thenReturn(expected);
            }
        })) {

            //Act
            SunriseSensor sunriseSensor = new SunriseSensor(sensorName, service);
            sunriseSensor.getSunriseTime(date, coordinates);
            sunriseSensor.getSunriseTime(date, coordinates);

            List<String> log = sunriseSensor.getLog();
            int resultLogSize = log.size();
            String result = log.get(1);

            //Assert
            assertEquals(expected, result);
            assertEquals(expectedLogSize,resultLogSize);

        }

    }

    /**
     * Unit test for verifying that the getName method in the SunriseSensor class
     * returns the correct sensor name. This test isolates the behavior of the
     * SunriseSensor class by using a mock for the ExternalServices (SunTimeCalculator)
     * and creating an instance of SunriseSensor with a specified sensor name.

     * The test calls the getName method on the SunriseSensor instance and asserts
     * that the returned name matches the originally provided sensor name.
     *  @throws InstantiationException If sensor parameters invalid.
     */

    @Test
    void getName_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, serviceDouble);

        //Act
        String result = SunriseSensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    @Test
    void getUnit_IsolationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices serviceDouble = mock(SunTimeCalculator.class);
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, serviceDouble);
        String expected = "Zone Date Time";
        //Act
        String result = SunriseSensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    //.............................................................................INTEGRATION TESTS..............................................................................

    @Test
    void getSunriseTime_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, service);
        String date = "2024-03-19";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-19T06:39:03Z[Europe/Lisbon]";
        //Act
        String result = SunriseSensor.getSunriseTime(date, coordinates).getValueAsString();
        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getSunriseTime_ErrorResult_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, service);
        String date = "2024-03-20";
        String coordinates = "888.1579 : -7.6291";
        String expected = "Invalid sun time value";
        Exception exception = assertThrows(InstantiationException.class, () ->{
            SunriseSensor.getSunriseTime(date,coordinates);
        });
        //Act
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getEmptyLog_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunriseSensor SunriseSensor = new SunriseSensor(sensorName, service);

        //Act
        List<String> log = SunriseSensor.getLog();
        //Assert
        assertTrue(log.isEmpty());

    }

    @Test
    void getLogWithOneRecord_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunriseSensor sunriseSensor = new SunriseSensor(sensorName, service);
        String date = "2024-03-19";
        String coordinates = "41.1579 : -8.6291";
        String expected = "2024-03-19T06:39:03Z[Europe/Lisbon]";

        //Act
        sunriseSensor.getSunriseTime(date, coordinates);

        List<String> log = sunriseSensor.getLog();
        String result = log.get(0);

        //Assert
        assertEquals(expected,result);

    }

    @Test
    void getLogWithTwoRecord_IntegrationTest() throws InstantiationException {
        //Arrange
        String sensorName = "Sunrise Sensor";
        ExternalServices service = new SunTimeCalculator();
        SunriseSensor sunriseSensor = new SunriseSensor(sensorName, service);
        String date = "2024-03-19";
        String coordinates = "41.1579 : -8.6291";
        String coordinatesTwo = "8.1579 : -8.6291";
        String expected = "2024-03-19T06:39:02Z[Europe/Lisbon]";

        //Act
        sunriseSensor.getSunriseTime(date, coordinates);
        sunriseSensor.getSunriseTime(date, coordinatesTwo);

        List<String> log = sunriseSensor.getLog();
        String result = log.get(1);

        //Assert
        assertEquals(expected,result);

    }


}
