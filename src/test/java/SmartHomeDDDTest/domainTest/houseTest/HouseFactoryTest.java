package SmartHomeDDDTest.domainTest.houseTest;

import SmartHomeDDD.domain.house.House;
import SmartHomeDDD.domain.house.HouseFactory;
import SmartHomeDDD.vo.houseVO.LocationVO;
import org.junit.jupiter.api.Test;
import org.mockito.MockedConstruction;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockConstruction;

/**
 * Test class for the HouseFactory class.
 */

public class HouseFactoryTest {

    /**
     * Test that verifies if the HouseFactory creates a House Entity with the given locationVO.
     */

    @Test
    void givenLocation_whenCreatingHouse_thenReturnHouseWithMockedLocation() {
//        Arrange
        LocationVO locationDouble = mock(LocationVO.class);
        HouseFactory factoryHouse = new HouseFactory();

        try (MockedConstruction<House> houseDouble = mockConstruction(House.class)) {
//            Act
            House house = factoryHouse.createHouse(locationDouble);
//            Assert
            List<House> houses = houseDouble.constructed();
            House houseResult = houses.get(0);
            assertEquals(house, houseResult);
        }
    }

    /**
     * Test that verifies if the HouseFactory returns null when the locationVO is null.
     */

    @Test
    void givenNullLocation_whenCreatingHouse_thenReturnNull() {
//        Arrange
        LocationVO locationDouble = null;
        HouseFactory factoryHouse = new HouseFactory();
//        Act
        House house = factoryHouse.createHouse(locationDouble);
//        Assert
        assertNull(house);
    }
}