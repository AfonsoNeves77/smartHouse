package SmartHome.domain.sensorTest.simHardware;

import SmartHome.domain.sensor.simHardware.SimHardware;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimHardwareTest {
    @Test
    void successfullyReturnsSample(){
        //Arrange
        SimHardware sim = new SimHardware();
        String expected = "Sample";
        //Act
        String result = sim.getValue();
        //Assert
        assertEquals(expected,result);
    }
}
