package SmartHomeDDDTest.voTest.houseVOTest;


import SmartHomeDDD.vo.houseVO.HouseIDVO;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class HouseIDVOTest {

    /**
     * Test case to verify that the HouseIDVO constructor throws an IllegalArgumentException
     * when given a null identifier. The result exception description must also match the expected one.
     */
    @Test
    public void givenNullIdentifier_ShouldThrowIllegalArgumentException(){

        //Arrange
        String expected = "Invalid Identifier";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new HouseIDVO(null));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);

    }

    /**
     * Test case to verify that the getID method of HouseIDVO returns the correct ID as a string.
     */
    @Test
    public void getIDShouldReturnCorrectIDAsString(){

        //Arrange
        UUID houseIdentifier = UUID.randomUUID();
        String expected = houseIdentifier.toString();

        //Act
        HouseIDVO houseID = new HouseIDVO(houseIdentifier);
        String result = houseID.getID();

        //Assert
        assertEquals(expected,result);

    }
}
