package SmartHome.domainTest.actuatorTest;

import SmartHome.domain.actuator.SimHardwareAct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimHardwareActTest {
    @Test
    void testSimHardwareAct_onCommand() {

        //arrange
        SimHardwareAct simHardwareAct = new SimHardwareAct();

        //act
        boolean result = simHardwareAct.executeCommandSim("On");

        //assert
        assertTrue(result);
    }

    @Test
    void testSimHardwareAct_offCommand() {

        //arrange
        SimHardwareAct simHardwareAct = new SimHardwareAct();

        //act
        boolean result = simHardwareAct.executeCommandSim("Off");

        //assert
        assertTrue(result);
    }
    @Test
    void testSimHardwareAct_DecimalSetCommand() {

        //arrange
        SimHardwareAct simHardwareAct = new SimHardwareAct();

        //act
        boolean result = simHardwareAct.executeDecimalCommandSim(15);

        //assert
        assertTrue(result);
    }

    @Test
    void testSimHardwareAct_IntegerCommand() {

        //arrange
        SimHardwareAct simHardwareAct = new SimHardwareAct();

        //act
        boolean result = simHardwareAct.executeIntegerCommandSim(50);

        //assert
        assertTrue(result);
    }
}