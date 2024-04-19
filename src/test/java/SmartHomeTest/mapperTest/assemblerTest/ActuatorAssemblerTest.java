package SmartHomeTest.mapperTest.assemblerTest;

import org.apache.commons.configuration2.ex.ConfigurationException;
import org.junit.jupiter.api.Test;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.actuator.ActuatorFactoryImpl;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.actuatorvo.DecimalSettingsVO;
import smarthome.domain.vo.actuatorvo.IntegerSettingsVO;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.mapper.assembler.ActuatorAssembler;
import smarthome.persistence.jpa.datamodel.ActuatorDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ActuatorAssemblerTest {

    /**
     * Test to verify if the ActuatorAssembler can convert an Actuator object to an ActuatorDataModel object.
     * The test creates an Actuator object with valid parameters and then converts it to an ActuatorDataModel object.
     *
     * @throws ConfigurationException
     * @result The ActuatorDataModel object is created with the same parameters as the Actuator object.
     */
    @Test
    void givenValidIntegerActuator_whenCreateDataModel_thenReturnDataModel() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        ActuatorNameVO actuatorName = new ActuatorNameVO("actuatorName");
        ActuatorTypeIDVO actuatorTypeID = new ActuatorTypeIDVO("IntegerValueActuator");
        UUID id = UUID.randomUUID();
        DeviceIDVO deviceID = new DeviceIDVO(id);
        Settings settings = new IntegerSettingsVO("1", "3");
        Actuator actuator = actuatorFactory.createActuator(actuatorName, actuatorTypeID, deviceID, settings);

        //Act
        ActuatorDataModel dataModel = ActuatorAssembler.toDataModel(actuator);

        //Assert
        assertEquals(actuator.getId().getID(), dataModel.getActuatorID());
        assertEquals(actuator.getActuatorName().getValue(), dataModel.getActuatorName());
        assertEquals(actuator.getActuatorTypeID().getID(), dataModel.getActuatorTypeID());
        assertEquals(actuator.getDeviceID().getID(), dataModel.getDeviceID());
        assertEquals(actuator.getLowerLimit(), dataModel.getLowerLimit());
        assertEquals(actuator.getUpperLimit(), dataModel.getUpperLimit());
        assertEquals(actuator.getPrecision(), dataModel.getPrecision());
    }

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
        List<Actuator> result = ActuatorAssembler.toDomainList(actuatorFactory, dataModels);

        //Assert
        assertEquals(dataModels.get(0).getActuatorID(), result.get(0).getId().getID());
        assertEquals(dataModels.get(1).getActuatorName(), result.get(1).getActuatorName().getValue());
        assertEquals(dataModels.get(2).getActuatorTypeID(), result.get(2).getActuatorTypeID().getID());
        assertEquals(dataModels.get(3).getDeviceID(), result.get(3).getDeviceID().getID());
        assertEquals(dataModels.get(0).getLowerLimit(), result.get(0).getLowerLimit());
        assertEquals(dataModels.get(0).getUpperLimit(), result.get(0).getUpperLimit());
        assertEquals(dataModels.get(1).getPrecision(), result.get(1).getPrecision());
    }

    /**
     * Test to verify if the ActuatorAssembler can convert a list of Actuator objects to a list of ActuatorDataModel objects.
     * The test creates a list of Actuator objects with valid parameters and then converts it to a list of ActuatorDataModel objects.
     *
     * @throws ConfigurationException
     * @result The list of ActuatorDataModel objects is created with the same parameters as the list of Actuator objects.
     */
    @Test
    void givenValidActuatorList_whenToDataModelList_thenReturnListOfActuatorDataModels() throws ConfigurationException {
        //Arrange
        ActuatorFactory actuatorFactory = new ActuatorFactoryImpl();
        ActuatorNameVO actuatorName1 = new ActuatorNameVO("actuatorName1");
        ActuatorTypeIDVO actuatorTypeID1 = new ActuatorTypeIDVO("IntegerValueActuator");
        UUID id1 = UUID.randomUUID();
        DeviceIDVO deviceID1 = new DeviceIDVO(id1);
        Settings settings1 = new IntegerSettingsVO("1", "3");
        Actuator actuator1 = actuatorFactory.createActuator(actuatorName1, actuatorTypeID1, deviceID1, settings1);

        ActuatorNameVO actuatorName2 = new ActuatorNameVO("actuatorName2");
        ActuatorTypeIDVO actuatorTypeID2 = new ActuatorTypeIDVO("DecimalValueActuator");
        UUID id2 = UUID.randomUUID();
        DeviceIDVO deviceID2 = new DeviceIDVO(id2);
        Settings settings2 = new DecimalSettingsVO("1.0", "3.0", "0.1");
        Actuator actuator2 = actuatorFactory.createActuator(actuatorName2, actuatorTypeID2, deviceID2, settings2);

        ActuatorNameVO actuatorName3 = new ActuatorNameVO("actuatorName3");
        ActuatorTypeIDVO actuatorTypeID3 = new ActuatorTypeIDVO("SwitchActuator");
        UUID id3 = UUID.randomUUID();
        DeviceIDVO deviceID3 = new DeviceIDVO(id3);
        Actuator actuator3 = actuatorFactory.createActuator(actuatorName3, actuatorTypeID3, deviceID3, null);

        ActuatorNameVO actuatorName4 = new ActuatorNameVO("actuatorName4");
        ActuatorTypeIDVO actuatorTypeID4 = new ActuatorTypeIDVO("RollerBlindActuator");
        UUID id4 = UUID.randomUUID();
        DeviceIDVO deviceID4 = new DeviceIDVO(id4);
        Actuator actuator4 = actuatorFactory.createActuator(actuatorName4, actuatorTypeID4, deviceID4, null);

        List<Actuator> actuators = new ArrayList<>();
        actuators.add(actuator1);
        actuators.add(actuator2);
        actuators.add(actuator3);
        actuators.add(actuator4);

        //Act
        List<ActuatorDataModel> result = ActuatorAssembler.toDataModelList(actuators);

        //Assert
        assertEquals(actuators.get(0).getId().getID(), result.get(0).getActuatorID());
        assertEquals(actuators.get(1).getActuatorName().getValue(), result.get(1).getActuatorName());
        assertEquals(actuators.get(2).getActuatorTypeID().getID(), result.get(2).getActuatorTypeID());
        assertEquals(actuators.get(3).getDeviceID().getID(), result.get(3).getDeviceID());
        assertEquals(actuators.get(0).getLowerLimit(), result.get(0).getLowerLimit());
        assertEquals(actuators.get(0).getUpperLimit(), result.get(0).getUpperLimit());
        assertEquals(actuators.get(1).getPrecision(), result.get(1).getPrecision());
    }
}