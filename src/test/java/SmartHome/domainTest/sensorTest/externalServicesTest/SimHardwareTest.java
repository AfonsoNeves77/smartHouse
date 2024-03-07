package SmartHome.domainTest.sensorTest.externalServicesTest;

import SmartHome.domain.sensor.externalServices.SimHardware;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SimHardwareTest {
    /**
     * This test ensures the getValue method returns the sample value successfully.
     */
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
