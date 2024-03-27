package SmartHomeDDDTest.domainTest.sensorTest;

import SmartHome.domain.sensor.sensorImplementation.sensorValues.TemperatureValue;
import SmartHomeDDD.domain.sensor.externalServices.SimHardware;

import SmartHomeDDD.domain.sensor.TemperatureSensor;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.sensorType.SensorTypeIDVO;
import SmartHomeDDD.vo.sensorVO.SensorIDVO;
import SmartHomeDDD.vo.sensorVO.SensorNameVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class TemperatureSensorTest {
    /**
     * Test if the constructor throws an exception when the name is null.
     */
    @Test
    void whenSensorNameIsNull_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = null;
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(sensorName, deviceID, sensorType));
        String result = exception.getMessage();

        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(0, listOfMockedSensorIDVO.size());
    }
    }

    /**
     * Test if the constructor throws an exception when the name is empty.
     */
    @Test
    void whenSensorNameIsEmpty_thenExceptionIsThrown() {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = null;
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Invalid parameters.";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new TemperatureSensor(sensorName, deviceID, sensorType));
        String result = exception.getMessage();

        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(0, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the constructor throws an exception when the sensorType is null.
     */
        @Test
        void whenSensorTypeIsNull_thenExceptionIsThrown() {
            //Arrange
            SensorNameVO sensorName = mock(SensorNameVO.class);
            DeviceIDVO deviceID = mock(DeviceIDVO.class);
            SensorTypeIDVO sensorType = null;
            String expected = "Invalid parameters.";
            try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {

            //Act
            Exception exception = assertThrows(IllegalArgumentException.class, () ->
                    new TemperatureSensor(sensorName, deviceID, sensorType));
            String result = exception.getMessage();

            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(expected, result);
            assertEquals(0, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test if the constructor returns the correct sensor name.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorName() throws InstantiationException {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        when(sensorName.toString()).thenReturn("Sensor1");
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "Sensor1";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

        //Act
        String result = sensor.getSensorName().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the constructor returns the correct sensor ID.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorID() throws InstantiationException {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);

        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class, (mock, context) -> {
            when(mock.toString()).thenReturn("08");
        })) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

            //Act
            String result = sensor.getId().toString();
            List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

            //Assert
            assertEquals(1, listOfMockedSensorIDVO.size());
            assertEquals(expected, result);
        }
    }

    /**
     * Test if the correct sensor type is returned, after the sensor is created.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsSensorType() throws InstantiationException {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        when(sensorType.toString()).thenReturn("08");
        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);
        //Act
        String result = sensor.getSensorTypeID().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
    }
}

    /**
     * Test if the correct device ID is returned, after the sensor is created.
     */
    @Test
    void whenSensorIsSuccessfullyCreated_thenReturnsDeviceID() throws InstantiationException {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        when(deviceID.toString()).thenReturn("08");
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        String expected = "08";
        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);

        //Act
        String result = sensor.getDeviceID().toString();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());
        }
    }

    /**
     * Test if the constructor throws an exception when the hardware is null.
     */
    @Test
    void whenSimHardwareIsNull_ThrowsIllegalArgumentException()
    {
        //Arrange
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        SimHardware simHardware = null;

        try (MockedConstruction<SensorIDVO> mockedConstruction = mockConstruction(SensorIDVO.class)) {
            TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);
            String expected = "Invalid parameters.";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sensor.getReading(simHardware));
        String result = exception.getMessage();
        List<SensorIDVO> listOfMockedSensorIDVO = mockedConstruction.constructed();

        //Assert
        assertEquals(expected, result);
        assertEquals(1, listOfMockedSensorIDVO.size());

        }
}

    /**
    * Test if the getReading method returns the correct value when the reading is correct.
     */
    @Test
    void whenReadingIsCorrect_thenReturnsValueSuccessfully() throws InstantiationException {
        //Arrange
        SimHardware simHardware = mock(SimHardware.class);
        SensorNameVO sensorName = mock(SensorNameVO.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        SensorTypeIDVO sensorType = mock(SensorTypeIDVO.class);
        TemperatureSensor sensor = new TemperatureSensor(sensorName, deviceID, sensorType);
        String expected = "36.1";

        try (MockedConstruction<TemperatureValue> temperatureValueDouble = mockConstruction(TemperatureValue.class, (mock, context)
                -> {
            when(mock.getValueAsString()).thenReturn("36.1");
        })) {

            //Act
            TemperatureValue actualTemperatureValue = (TemperatureValue) sensor.getReading(simHardware);
            List<TemperatureValue> constructedTemperatureValues = temperatureValueDouble.constructed();
            String actualTemperatureValueString = actualTemperatureValue.getValueAsString();

            //Assert
            assertEquals(expected, actualTemperatureValueString);
            assertEquals(1, constructedTemperatureValues.size());
        }
    }
}

