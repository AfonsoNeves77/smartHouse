package SmartHomeDDDTest.domainTest.houseTest;

import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.vo.houseVO.HouseIDVO;
import SmartHomeDDD.vo.houseVO.LocationVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HouseTest {

    /**
     * This test verifies that a `HouseEntity` object can be successfully created with a valid `LocationVO` object.
     *
     * @expected  The test creates a `HouseEntity` with a mocked `LocationVO` and asserts that the created object is not null.
     */
    @Test
    public void whenHouseLocationIsValid_ShouldCreateHouseEntity() {
        //Arrange
        LocationVO location = mock(LocationVO.class);

        //Act
        House houseEntity = new House(location);

        //Assert
        assertNotNull(houseEntity);
    }

    /**
     * This test verifies that the constructor of `HouseEntity` generates a valid `HouseIDVO` object and returns its string representation.
     *
     * @expected The test mocks the construction of `HouseIDVO` to return a specific string ("123") and asserts that the constructed `HouseIDVO` returns the expected string.
     */
    @Test
    public void whenValidIDVOIsConstructed_ReturnsHouseID() {
        //Arrange
        LocationVO location = mock(LocationVO.class);
        try (MockedConstruction<HouseIDVO> mockUUID = mockConstruction(HouseIDVO.class, (mock, context) -> {
            when(mock.toString()).thenReturn("123");
        })) {
            //Act
            House houseEntity = new House(location);
            HouseIDVO houseID = mockUUID.constructed().get(0);
            //Assert
            assertEquals("123", houseID.toString());
        }
    }

    /**
     * This test verifies that creating a `HouseEntity` with a null `LocationVO` throws an `IllegalArgumentException`.
     *
     * @expected An `IllegalArgumentException` is thrown with a specific message ("Invalid House Entity parameters.").
     */
    @Test
    public void whenHouseLocationIsInvalid_ShouldThrowException() {
        // Arrange
        LocationVO location = null; // Invalid location
        String expected = "Invalid House Entity parameters.";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new House(location));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected, result);
    }

    /**
     * This test verifies that the `configureLocation` method successfully updates the internal location of a `HouseEntity` with a new `LocationVO` object.
     *
     * @expected The test creates a `HouseEntity` with a mocked `LocationVO`, attempts to configure it with another mocked `LocationVO`, and asserts that the `configureLocation` method returns `true`.
     */
    @Test
    public void whenHouseLocationIsValid_ShouldConfigureLocation() {
        // Arrange
        LocationVO location = mock(LocationVO.class);
        House houseEntity = new House(location);
        LocationVO newLocation = mock(LocationVO.class);

        // Act
        boolean result = houseEntity.configureLocation(newLocation);

        // Assert
        assertTrue(result);
        assertEquals(newLocation, houseEntity.getLocation());
    }

    /**
     * This test verifies that the `configureLocation` method fails to update the internal location of a `HouseEntity` with a null `LocationVO` object.
     *
     * @expected The test creates a `HouseEntity` with a mocked `LocationVO`, attempts to configure it with a null `LocationVO`, and asserts that the `configureLocation` method returns `false`.
     */
    @Test
    public void whenHouseLocationIsInvalid_ShouldNotConfigureLocation() {
        // Arrange
        LocationVO location = mock(LocationVO.class);
        House houseEntity = new House(location);
        LocationVO newLocation = null;

        // Act
        boolean result = houseEntity.configureLocation(newLocation);

        // Assert
        assertFalse(result);
    }

    /**
     * This test verifies that the `configureLocation` method fails to update the internal location of a `HouseEntity` with a null `LocationVO` object.
     *
     * @expected The test creates a `HouseEntity` with a mocked `LocationVO`, attempts to configure it with a null `LocationVO`, and asserts that the `configureLocation` method returns `false`.
     */
    @Test
    public void whenHouseEntityIsCreated_ShouldGetHouseID() {
        // Arrange
        LocationVO location = mock(LocationVO.class);
        House houseEntity = new House(location);

        // Act
        HouseIDVO houseID = houseEntity.getId();

        // Assert
        assertNotNull(houseID);
    }

    /**
     * This test verifies that the `configureLocation` method fails to update the internal location of a `HouseEntity` with a null `LocationVO` object.
     *
     * @expected The test creates a `HouseEntity` with a mocked `LocationVO`, attempts to configure it with a null `LocationVO`, and asserts that the `configureLocation` method returns `false`.
     */
    @Test
    public void whenHouseEntityIsCreated_ShouldGetLocation() {
        // Arrange
        LocationVO location = mock(LocationVO.class);
        House houseEntity = new House(location);

        // Act
        LocationVO result = houseEntity.getLocation();

        // Assert
        assertEquals(location, result);
    }
}