package SmartHome.domain.device;

import java.util.ArrayList;
import java.util.List;

import SmartHome.domain.ActuatorCatalogue;
import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.actuator.ListOfActuators;
import SmartHome.domain.actuator.SimHardwareAct;
import SmartHome.domain.sensor.ListOfSensors;
import SmartHome.domain.sensor.externalServices.ExternalServices;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.externalServices.SimHardware;

public class Device {
    private String deviceName;
    private String deviceModel; //We need a way to validate that the model is compatible with the system.
    private String deviceLocation;
    private boolean status;
    private ListOfSensors listOfSensors;

    private ListOfActuators listOfActuators;
    private List<String> deviceFunctionalities = new ArrayList<>();

    /**
     * Constructor for device, it initializes status and listOfSensors as null;
     * @param deviceName Device name
     * @param deviceModel Device model
     * @param deviceLocation Room where the device is located
     */

    public Device(String deviceName, String deviceModel, String deviceLocation) throws InstantiationException {
        if (!areDeviceParametersValid(deviceName,deviceModel,deviceLocation)) {
            throw new InstantiationException("Invalid parameter");
        }
        this.deviceName = deviceName;
        this.deviceModel = deviceModel;
        this.deviceLocation = deviceLocation;
        this.status = true;
        this.listOfSensors = new ListOfSensors();
        this.listOfActuators = new ListOfActuators();
    }

    /**
     * This method confirms if the parameters are null or empty
     * @param deviceName deviceName
     * @param deviceModel deviceModel
     * @param deviceLocation deviceLocation
     * @return a boolean
     */

    private boolean areDeviceParametersValid(String deviceName, String deviceModel, String deviceLocation) {
        if (deviceName == null || deviceName.trim().isEmpty()) {
            return false;
        }
        if (deviceModel == null || deviceModel.trim().isEmpty()) {
            return false;
        }
        return deviceLocation != null && !deviceLocation.trim().isEmpty();
    }

    /**
     *  This method adds a sensor to a device. If the sensor is created successfully it also updates the deviceFunctionalitiesList.
     * @param sensorName sensorName
     * @param sensorType sensorType
     * @param catalogue SensorCatalogue
     * @param externalServices ExternalServices
     * @return a boolean
     */

    public boolean addSensor(String sensorName, String sensorType, SensorCatalogue catalogue, ExternalServices externalServices) {
            if (!this.status) {
                return false;
            }
            if (listOfSensors.addSensor(sensorName, sensorType, catalogue, externalServices)) {

                updateDeviceFunctionalities(sensorType);
                return true;
            }
            return false;
    }

    /**
     * This methods verifies if the actuator is active, if it is, it calls addActuator method onto the encapsulated listOfActuators
     * @param actuatorName actuatorName
     * @param actuatorType actuatorTYpe
     * @param catalogue ActuatorCatalogue
     * @param simHardwareAct SimHardwareAct
     * @return a boolean to confirm the operation
     */

    public boolean addActuator(String actuatorName, String actuatorType, ActuatorCatalogue catalogue, SimHardwareAct simHardwareAct){
        if(!this.status){
            return false;
        }
        return listOfActuators.addActuator(actuatorName,actuatorType,catalogue, simHardwareAct);
    }

    /**
     * This method verifies that the specified sensor type exists. If it does not, it adds it to the list of functionalities.
     * @param sensorType sensorType
     */

    private void updateDeviceFunctionalities(String sensorType){
        if(!deviceFunctionalities.contains(sensorType)){
            deviceFunctionalities.add(sensorType);
        }
    }

    /**
     * This method changes the deviceStatus to false
     * @return the resuling status of a device
     */

    public boolean deactivateDevice(){
        this.status = false;
        return this.status;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public boolean getStatus() {
        return status;
    }

    public List<String> getDeviceFunctionalities(){
        return deviceFunctionalities;
    }

    public ArrayList<String> getLog(String sensorName){
        return listOfSensors.getLog(sensorName);
    }
}
