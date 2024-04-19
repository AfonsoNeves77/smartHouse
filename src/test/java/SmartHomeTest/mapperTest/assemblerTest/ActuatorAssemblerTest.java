package SmartHomeTest.mapperTest.assemblerTest;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.actuator.ActuatorFactoryImpl;
import smarthome.mapper.assembler.ActuatorAssembler;
import smarthome.persistence.jpa.datamodel.ActuatorDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActuatorAssemblerTest {

    /**
     * Test to verify if the ActuatorAssembler can convert an ActuatorDataModel object to an Integer Actuator object.
     * The test creates an ActuatorDataModel object with valid parameters and then converts it to an Integer Actuator object.
     *
     * @throws ConfigurationException
     * @result The Actuator object is created with the same parameters as the ActuatorDataModel object.
     */
    @Test
    void givenValidDataModel_whenToDomain_thenReturnIntegerActuator() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        String actuatorID = randomUUID().toString();
        String actuatorName = "actuatorName";
        String actuatorTypeID = "IntegerValueActuator";
        String deviceID = randomUUID().toString();
        String lowerLimit = "1";
        String upperLimit = "3";
        String precision = null;
        ActuatorDataModel dataModel = new ActuatorDataModel(actuatorID, actuatorName, actuatorTypeID, deviceID, lowerLimit, upperLimit, precision);

        //Act
        Actuator actuator = ActuatorAssembler.toDomain(actuatorFactory, dataModel);

        //Assert
        assertEquals(dataModel.getActuatorID(), actuator.getId().getID());
        assertEquals(dataModel.getActuatorName(), actuator.getActuatorName().getValue());
        assertEquals(dataModel.getActuatorTypeID(), actuator.getActuatorTypeID().getID());
        assertEquals(dataModel.getDeviceID(), actuator.getDeviceID().getID());
        assertEquals(dataModel.getLowerLimit(), actuator.getLowerLimit());
        assertEquals(dataModel.getUpperLimit(), actuator.getUpperLimit());
        assertEquals(dataModel.getPrecision(), actuator.getPrecision());
    }

    /**
     * Test to verify if the ActuatorAssembler can convert an ActuatorDataModel object to a Decimal Actuator object.
     * The test creates an ActuatorDataModel object with valid parameters and then converts it to a Decimal Actuator object.
     *
     * @throws ConfigurationException
     * @result The Actuator object is created with the same parameters as the ActuatorDataModel object.
     */
    @Test
    void givenValidDataModel_whenToDomain_thenReturnDecimalActuator() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        String actuatorID = randomUUID().toString();
        String actuatorName = "actuatorName";
        String actuatorTypeID = "DecimalValueActuator";
        String deviceID = randomUUID().toString();
        String lowerLimit = "1.0";
        String upperLimit = "3.0";
        String precision = "0.1";
        ActuatorDataModel dataModel = new ActuatorDataModel(actuatorID, actuatorName, actuatorTypeID, deviceID, lowerLimit, upperLimit, precision);

        //Act
        Actuator actuator = ActuatorAssembler.toDomain(actuatorFactory, dataModel);

        //Assert
        assertEquals(dataModel.getActuatorID(), actuator.getId().getID());
        assertEquals(dataModel.getActuatorName(), actuator.getActuatorName().getValue());
        assertEquals(dataModel.getActuatorTypeID(), actuator.getActuatorTypeID().getID());
        assertEquals(dataModel.getDeviceID(), actuator.getDeviceID().getID());
        assertEquals(dataModel.getLowerLimit(), actuator.getLowerLimit());
        assertEquals(dataModel.getUpperLimit(), actuator.getUpperLimit());
        assertEquals(dataModel.getPrecision(), actuator.getPrecision());
    }

    /**
     * Test to verify if the ActuatorAssembler can convert an ActuatorDataModel object to a Switch Actuator object.
     * The test creates an ActuatorDataModel object with valid parameters and then converts it to a Switch Actuator object.
     *
     * @throws ConfigurationException
     * @result The Actuator object is created with the same parameters as the ActuatorDataModel object.
     */
    @Test
    void givenValidDataModel_whenToDomain_thenReturnSwitchActuator() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        String actuatorID = randomUUID().toString();
        String actuatorName = "actuatorName";
        String actuatorTypeID = "SwitchActuator";
        String deviceID = randomUUID().toString();
        ActuatorDataModel dataModel = new ActuatorDataModel(actuatorID, actuatorName, actuatorTypeID, deviceID, null, null, null);

        //Act
        Actuator actuator = ActuatorAssembler.toDomain(actuatorFactory, dataModel);

        //Assert
        assertEquals(dataModel.getActuatorID(), actuator.getId().getID());
        assertEquals(dataModel.getActuatorName(), actuator.getActuatorName().getValue());
        assertEquals(dataModel.getActuatorTypeID(), actuator.getActuatorTypeID().getID());
        assertEquals(dataModel.getDeviceID(), actuator.getDeviceID().getID());
        assertEquals(dataModel.getLowerLimit(), actuator.getLowerLimit());
        assertEquals(dataModel.getUpperLimit(), actuator.getUpperLimit());
        assertEquals(dataModel.getPrecision(), actuator.getPrecision());
    }

    /**
     * Test to verify if the ActuatorAssembler can convert an ActuatorDataModel object to a Roller Blind Actuator object.
     * The test creates an ActuatorDataModel object with valid parameters and then converts it to a Roller Blind Actuator object.
     *
     * @throws ConfigurationException
     * @result The Actuator object is created with the same parameters as the ActuatorDataModel object.
     */
    @Test
    void givenValidDataModel_whenToDomain_thenReturnRollerBlindActuator() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        String actuatorID = randomUUID().toString();
        String actuatorName = "actuatorName";
        String actuatorTypeID = "RollerBlindActuator";
        String deviceID = randomUUID().toString();
        ActuatorDataModel dataModel = new ActuatorDataModel(actuatorID, actuatorName, actuatorTypeID, deviceID, null, null, null);

        //Act
        Actuator actuator = ActuatorAssembler.toDomain(actuatorFactory, dataModel);

        //Assert
        assertEquals(dataModel.getActuatorID(), actuator.getId().getID());
        assertEquals(dataModel.getActuatorName(), actuator.getActuatorName().getValue());
        assertEquals(dataModel.getActuatorTypeID(), actuator.getActuatorTypeID().getID());
        assertEquals(dataModel.getDeviceID(), actuator.getDeviceID().getID());
        assertEquals(dataModel.getLowerLimit(), actuator.getLowerLimit());
        assertEquals(dataModel.getUpperLimit(), actuator.getUpperLimit());
        assertEquals(dataModel.getPrecision(), actuator.getPrecision());
    }

    /**
     * Test to verify if the ActuatorAssembler can convert a list of ActuatorDataModel objects to a list of Actuator objects.
     * The test creates a list of ActuatorDataModel objects with valid parameters and then converts it to a list of Actuator objects.
     *
     * @throws ConfigurationException
     * @result The list of Actuator objects is created with the same parameters as the list of ActuatorDataModel objects.
     */
    @Test
    void givenListOfDataModels_whenToDomainList_thenReturnListOfActuators() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        ActuatorDataModel atuator1 = new ActuatorDataModel(randomUUID().toString(), "actuatorName1", "IntegerValueActuator", randomUUID().toString(), "1", "3", null);
        ActuatorDataModel atuator2 = new ActuatorDataModel(randomUUID().toString(), "actuatorName2", "DecimalValueActuator", randomUUID().toString(), "1.0", "3.0", "0.1");
        ActuatorDataModel atuator3 = new ActuatorDataModel(randomUUID().toString(), "actuatorName3", "SwitchActuator", randomUUID().toString(), null, null, null);
        ActuatorDataModel atuator4 = new ActuatorDataModel(randomUUID().toString(), "actuatorName4", "RollerBlindActuator", randomUUID().toString(), null, null, null);
        List<ActuatorDataModel> dataModels = new ArrayList<>();
        dataModels.add(atuator1);
        dataModels.add(atuator2);
        dataModels.add(atuator3);
        dataModels.add(atuator4);

        //Act
        Iterable<Actuator> actuatorToDomain = ActuatorAssembler.toDomainList(actuatorFactory, dataModels);


        //Assert
        List<Actuator> result = new ArrayList<>();
        actuatorToDomain.forEach(result::add);
        assertEquals(dataModels.get(0).getActuatorID(), result.get(0).getId().getID());
        assertEquals(dataModels.get(1).getActuatorName(), result.get(1).getActuatorName().getValue());
        assertEquals(dataModels.get(2).getActuatorTypeID(), result.get(2).getActuatorTypeID().getID());
        assertEquals(dataModels.get(3).getDeviceID(), result.get(3).getDeviceID().getID());
        assertEquals(dataModels.get(0).getLowerLimit(), result.get(0).getLowerLimit());
        assertEquals(dataModels.get(0).getUpperLimit(), result.get(0).getUpperLimit());
        assertEquals(dataModels.get(1).getPrecision(), result.get(1).getPrecision());
    }
}