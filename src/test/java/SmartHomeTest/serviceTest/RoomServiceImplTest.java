package SmartHomeTest.serviceTest;

import smarthome.domain.room.RoomFactoryImpl;
import smarthome.persistence.mem.HouseRepositoryMem;
import smarthome.persistence.mem.RoomRepositoryMem;
import smarthome.service.RoomServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class RoomServiceImplTest {

    /**
     * This test ensures that, when given a null Roomrepository, the constructor throws and IllegalArgumentException.
     */
    @Test
    void whenGivenAnInvalidRoomRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        HouseRepositoryMem houseRepository = mock(HouseRepositoryMem.class);
        RoomFactoryImpl factory = mock(RoomFactoryImpl.class);
        String expected = "Invalid parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomServiceImpl(houseRepository,null,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null factory, the constructor throws and IllegalArgumentException.
     */
    @Test
    void whenGivenAnInvalidFactory_ConstructorThrowsIllegalArgument(){
        // Arrange
        HouseRepositoryMem houseRepository = mock(HouseRepositoryMem.class);
        RoomRepositoryMem repository = mock(RoomRepositoryMem.class);
        String expected = "Invalid parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomServiceImpl(houseRepository,repository,null));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }

    /**
     * This test ensures that, when given a null houseRepository, the constructor throws and IllegalArgumentException.
     */
    @Test
    void whenGivenAnInvalidHouseRepository_ConstructorThrowsIllegalArgument(){
        // Arrange
        RoomRepositoryMem repository = mock(RoomRepositoryMem.class);
        RoomFactoryImpl factory = mock(RoomFactoryImpl.class);
        String expected = "Invalid parameters";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RoomServiceImpl(null,repository,factory));
        String result = exception.getMessage();

        // Assert
        assertEquals(expected,result);
    }


}