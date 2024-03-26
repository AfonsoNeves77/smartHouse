package SmartHomeDDDTest;

import SmartHomeDDD.SimHardwareAct;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimHardwareActTest {

    /**
     * Test case to verify that the executeCommandSim method of SimHardwareAct returns true.

     * This test instantiates a SimHardwareAct object and invokes its executeCommandSim method.
     * It then asserts that the result is true, indicating successful execution of the command.

     */

    @Test
    void executeCommandSimShouldReturnTrue(){
        //Act
        SimHardwareAct simHardwareAct = new SimHardwareAct();
        boolean result = simHardwareAct.executeCommandSim();
        //Assert
        assertTrue(result);
    }
}
