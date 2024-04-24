package smarthome.utils.timeconfig;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TimeConfigDTOTest {

    /**
     * Unit test to verify that a TimeConfigDTO object is created successfully
     * and its fields are accessible.
     * <p>
     * This test method verifies that a TimeConfigDTO object can be created
     * with the provided parameters (iDate, eDate, iTime, eTime, deltaMin).
     * It then checks that each field of the resulting TimeConfigDTO object
     * is accessible and contains the expected value.
     */
    @Test
    void whenFullObjectIsCreatedSuccessfully_dataIsReachable(){
        // Arrange
        String iDate = "idate";
        String iTime = "iTime";
        String eDate = "edate";
        String eTime = "eTime";
        String deltaMin = "deltaMin";
        TimeConfigDTO result = new TimeConfigDTO(iDate,iTime,eDate,eTime,deltaMin);

        // Act
        String resultIDate = result.iDate;
        String resultEDate = result.eDate;
        String resultITime = result.iTime;
        String resultETime = result.eTime;
        String resultDeltaMin = result.deltaMin;

        // Assert
        assertEquals(iDate, resultIDate);
        assertEquals(eDate, resultEDate);
        assertEquals(iTime, resultITime);
        assertEquals(eTime, resultETime);
        assertEquals(deltaMin, resultDeltaMin);
    }

    /**
     * Unit test to verify that a TimeConfigDTO object is created successfully
     * without the deltaMin parameter and its fields are accessible.
     * <p>
     * This test method verifies that a TimeConfigDTO object can be created
     * with the provided parameters (iDate, eDate, iTime, eTime) without
     * specifying the deltaMin parameter. It then checks that each field
     * of the resulting TimeConfigDTO object is accessible and contains
     * the expected value. Additionally, it ensures that the deltaMin field
     * of the TimeConfigDTO object is null.
     */
    @Test
    void whenObjectWithoutDeltaIsCreatedSuccessfully_dataIsReachable(){
        // Arrange
        String iDate = "idate";
        String iTime = "iTime";
        String eDate = "edate";
        String eTime = "eTime";
        TimeConfigDTO result = new TimeConfigDTO(iDate,iTime,eDate,eTime);

        // Act
        String resultIDate = result.iDate;
        String resultEDate = result.eDate;
        String resultITime = result.iTime;
        String resultETime = result.eTime;
        String resultDeltaMin = result.deltaMin;

        // Assert
        assertEquals(iDate, resultIDate);
        assertEquals(eDate, resultEDate);
        assertEquals(iTime, resultITime);
        assertEquals(eTime, resultETime);
        assertNull(resultDeltaMin);
    }
}
