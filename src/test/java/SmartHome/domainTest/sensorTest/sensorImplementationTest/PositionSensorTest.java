package SmartHome.domainTest.sensorTest.sensorImplementationTest;


import SmartHome.domain.sensor.sensorImplementation.PositionSensor;
import SmartHome.domain.sensor.sensorImplementation.Sensor;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.PositionValue;

import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PositionSensorTest {

//        ### ISOLATION TESTS ###

    /**
     * Tests the constructor of the PositionSensor class with a null name.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameNull(){
        //Arrange
        SimHardware simHardwareDouble = mock(SimHardware.class);
        String sensorName = null;
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new PositionSensor(sensorName, simHardwareDouble));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests the constructor of the PositionSensor class with an empty name.
     */
    @Test
    void sensorConstructor_throwsExceptionIfNameEmpty(){
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "   ";
        String expected = "Invalid parameter";
        //Act
        Exception exception = assertThrows(InstantiationException.class, () ->
                new PositionSensor(sensorName, simHardware));
        String result = exception.getMessage();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Tests the getName method of the PositionSensor class.
     * @throws InstantiationException
     */
    @Test
    void getName_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new PositionSensor(sensorName, simHardware);
        //Act
        String result = sensor.getName();
        //Assert
        assertEquals(sensorName,result);
    }

    /**
     * Tests the getUnit method of the PositionSensor class.
     * @throws InstantiationException
     */
    @Test
    void getUnit_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        Sensor sensor = new PositionSensor(sensorName, simHardware);
        String expected = "%";
        //Act
        String result = sensor.getUnit();
        //Assert
        assertEquals(expected,result);
    }

    /**
     *
     * @throws InstantiationException
     */
    @Test
    void getType_SuccessfullyReturns() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        String sensorName = "Sensor1";
        PositionSensor sensor = new PositionSensor(sensorName, simHardware);
        String expected = "Position";
        //Act
        String result = sensor.getType();
        //Assert
        assertEquals(expected,result);
    }

    /**
     * Isolation test for the getReading method of the PositionSensor class.
     * @throws InstantiationException
     */
    @Test
    void getReading_ReturnsValueCorrectly_Isolation() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        String sensorName = "Sensor 1";
        String expected = "36";
        PositionSensor sensor = new PositionSensor(sensorName,simHardware);
        try(MockedConstruction<PositionValue> positionValueDouble = mockConstruction(PositionValue.class, (mock, context)
        -> {when(mock.getValueAsString()).thenReturn("36");})){
            //Act
            PositionValue value = (PositionValue) sensor.getReading();

            //Assert
            List<PositionValue> values = positionValueDouble.constructed();
            assertEquals(1, values.size());

            String result = values.get(0).getValueAsString();
            assertEquals(expected,result);

            String mockedPositionValue = value.getValueAsString();
            assertEquals(expected,mockedPositionValue);
        }
    }

    /**
     * Tests the getLog method of the PositionSensor class.
     * @throws InstantiationException
     */
    @Test
    void getLog_SuccessfullyReturnsEmptyList_Isolation() throws InstantiationException {
        //Arrange
        String sensorName = "Sensor1";
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        PositionSensor sensor = new PositionSensor(sensorName, simHardware);

        ArrayList<String> expected = new ArrayList<>();

        //Act
        ArrayList<String> result = sensor.getLog();

        //Assert
        assertEquals(expected,result);
    }

//     ### INTEGRATION TESTS ###

    /**
     * Tests the getReading method of the PositionSensor class.
     * @throws InstantiationException
     */
    @Test
    void getReading_ReturnsValueCorrectly_Integration() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        when(simHardware.getValue()).thenReturn("36");

        String sensorName = "Sensor 1";
        PositionSensor sensor = new PositionSensor(sensorName,simHardware);

        String expected = "36";

        //Act
        PositionValue value = (PositionValue) sensor.getReading();
        String result = value.getValueAsString();

        //Assert
        assertEquals(expected,result);
    }
}