package smarthome.mapper.assembler;

import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.ActuatorFactory;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.*;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.persistence.jpa.datamodel.ActuatorDataModel;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ActuatorAssembler {

    /**
     * Method to convert an Actuator object to an ActuatorDataModel object.
     *
     * @param actuator Actuator object
     * @return ActuatorDataModel object
     */
    public static ActuatorDataModel toDataModel(Actuator actuator) {
        return new ActuatorDataModel(
                actuator.getId().getID(),
                actuator.getActuatorName().getValue(),
                actuator.getActuatorTypeID().getID(),
                actuator.getDeviceID().getID(),
                actuator.getLowerLimit(),
                actuator.getUpperLimit(),
                actuator.getPrecision()
        );
    }

    /**
     * Method to convert an ActuatorDataModel object to an Actuator object.
     *
     * @param actuatorFactory   ActuatorFactory object
     * @param actuatorDataModel ActuatorDataModel object
     * @return Actuator object
     */
    public static Actuator toDomain(ActuatorFactory actuatorFactory, ActuatorDataModel actuatorDataModel) {
        UUID actuatorUUID = UUID.fromString(actuatorDataModel.getActuatorID());
        ActuatorIDVO actuatorID = new ActuatorIDVO(actuatorUUID);
        ActuatorNameVO actuatorName = new ActuatorNameVO(actuatorDataModel.getActuatorName());
        ActuatorTypeIDVO actuatorType = new ActuatorTypeIDVO(actuatorDataModel.getActuatorTypeID());
        UUID deviceUUID = UUID.fromString(actuatorDataModel.getDeviceID());
        DeviceIDVO deviceID = new DeviceIDVO(deviceUUID);
        String lowerLimit = actuatorDataModel.getLowerLimit();
        String upperLimit = actuatorDataModel.getUpperLimit();
        String precision = actuatorDataModel.getPrecision();
        Settings settings = null;
        if (precision == null && lowerLimit != null && upperLimit != null) {
            settings = new IntegerSettingsVO(lowerLimit, upperLimit);

        }
        if (precision != null && lowerLimit != null && upperLimit != null) {
            settings = new DecimalSettingsVO(lowerLimit, upperLimit, precision);
        }
        return actuatorFactory.createActuator(actuatorID, actuatorName, actuatorType, deviceID, settings);
    }

    /**
     * Method to convert a list of ActuatorDataModel objects to a list of Actuator objects.
     *
     * @param actuatorFactory    ActuatorFactory object
     * @param actuatorDataModels List of ActuatorDataModel objects
     * @return List of Actuator objects
     */
    public static List<Actuator> toDomainList(ActuatorFactory actuatorFactory, List<ActuatorDataModel> actuatorDataModels) {
        List<Actuator> actuators = new ArrayList<>();
        for (ActuatorDataModel actuatorDataModel : actuatorDataModels) {
            actuators.add(toDomain(actuatorFactory, actuatorDataModel));
        }
        return actuators;
    }

    /**
     * Method to convert a list of Actuator objects to a list of ActuatorDataModel objects.
     *
     * @param actuators List of Actuator objects
     * @return List of ActuatorDataModel objects
     */
    public static List<ActuatorDataModel> toDataModelList(List<Actuator> actuators) {
        List<ActuatorDataModel> actuatorDataModels = new ArrayList<>();
        for (Actuator actuator : actuators) {
            actuatorDataModels.add(toDataModel(actuator));
        }
        return actuatorDataModels;
    }
}
