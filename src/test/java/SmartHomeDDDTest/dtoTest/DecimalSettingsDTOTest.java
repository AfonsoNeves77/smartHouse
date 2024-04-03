package SmartHomeDDDTest.dtoTest;

import SmartHomeDDD.dto.DecimalSettingsDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalSettingsDTOTest {
    /**
     * Test scenario to verify that invalid information (null) can arrive to the controller.
     * DTO is created successfully, even though all attributes are null. The verification is applied in the domain.
     * All the asserts are done in the same test, since it is a peculiar example, there is no need for code repetition.
     */
    @Test
    void givenNullAttributes_DtoIsCreated_WhenGetAllAttributes_ThenAllShouldReturnNull(){
        //Arrange
        String lowerLimit = null;
        String upperLimit = null;
        String precision = null;
        DecimalSettingsDTO settingsDTO = new DecimalSettingsDTO(lowerLimit, upperLimit, precision);
        //Act
        String resultLower = settingsDTO.getLowerLimit();
        String resultUpper = settingsDTO.getUpperLimit();
        String resultPrecision = settingsDTO.getPrecision();
        //Assert
        assertNull(resultLower);
        assertNull(resultUpper);
        assertNull(resultPrecision);
    }

    /**
     * Test scenario to verify that invalid information (non-numeric) can arrive to the controller.
     * DTO is created successfully, even though all attributes are not numeric. The verification is applied in the domain.
     * All the asserts are done in the same test, there is no need for code repetition.
     */
    @Test
    void givenNonNullAttributes_DtoIsCreated_WhenGetAllAttributes_ThenAllShouldBeReturnedAsExpected(){
        //Arrange
        String lowerLimit = "lower";
        String upperLimit = "upper";
        String precision = "precision";
        DecimalSettingsDTO settingsDTO = new DecimalSettingsDTO(lowerLimit, upperLimit, precision);
        //Act
        String resultLower = settingsDTO.getLowerLimit();
        String resultUpper = settingsDTO.getUpperLimit();
        String resultPrecision = settingsDTO.getPrecision();
        //Assert
        assertEquals(lowerLimit, resultLower);
        assertEquals(upperLimit, resultUpper);
        assertEquals(precision, resultPrecision);
    }

    /**
     * Test scenario create a DTO with valid information (numeric in string format).
     * DTO is created successfully. The verification/validation of the attributes' relationship is applied in the domain.
     * All the asserts are done in the same test, there is no need for code repetition.
     */
    @Test
    void givenNumberAttributesInStringFormat_DtoIsCreated_WhenGetAllAttributes_ThenAllShouldBeReturnedAsExpected(){
        //Arrange
        String lowerLimit = "34.5";
        String upperLimit = "100.5";
        String precision = "0.1";
        DecimalSettingsDTO settingsDTO = new DecimalSettingsDTO(lowerLimit, upperLimit, precision);
        //Act
        String resultLower = settingsDTO.getLowerLimit();
        String resultUpper = settingsDTO.getUpperLimit();
        String resultPrecision = settingsDTO.getPrecision();
        //Assert
        assertEquals(lowerLimit, resultLower);
        assertEquals(upperLimit, resultUpper);
        assertEquals(precision, resultPrecision);
    }
}