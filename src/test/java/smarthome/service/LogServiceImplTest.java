package smarthome.service;

import org.junit.jupiter.api.Test;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.persistence.LogRepository;
import smarthome.utils.timeconfig.TimeConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class LogServiceImplTest {
    /**
     * Test to verify that IllegalArgumentException is thrown when given null parameters.
     */
    @Test
    void whenGivenNullRepository_construtorThrowsIllegalArgumentException(){
        // Arrange
        String expected = "LogRepository cannot be null.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, ()
                -> new LogServiceImpl(null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * Test to verify that IllegalArgumentException is thrown when given null parameters.
     */
    @Test
    void whenGivenNullParameters_throwsIllegalArgumentException(){
        // Arrange
        String expected = "Invalid parameters";

        LogRepository repository = mock(LogRepository.class);
        DeviceIDVO deviceID = mock(DeviceIDVO.class);
        TimeConfig timeConfig = mock(TimeConfig.class);
        LogServiceImpl service = new LogServiceImpl(repository);

        // Act
        Exception exception1 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,timeConfig));
        String result1 = exception1.getMessage();

        Exception exception2 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(deviceID,null));
        String result2 = exception2.getMessage();

        Exception exception3 = assertThrows(IllegalArgumentException.class, ()
                -> service.findReadingsFromDeviceInATimePeriod(null,null));
        String result3 = exception3.getMessage();

        // Assert
        assertEquals(expected,result1);
        assertEquals(expected,result2);
        assertEquals(expected,result3);
    }
}
