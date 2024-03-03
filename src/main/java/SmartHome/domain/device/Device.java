package SmartHome.domain.device;

import java.util.ArrayList;
import java.util.List;

import SmartHome.domain.SensorCatalogue;
import SmartHome.domain.sensor.ListOfSensors;
import SmartHome.domain.sensor.sensorImplementation.sensorValues.Value;
import SmartHome.domain.sensor.simHardware.SimHardware;

public class Device {
    private String deviceName;
    private String deviceModel; //We need a way to validate that the model is compatible with the system.
    private String deviceLocation;
    private boolean status;
    private ListOfSensors listOfSensors;
    private List<String> deviceFunctionalities = new ArrayList<>();

    /**
     * Constructor for device, it initializes status and listOfSensors as null;
     * @param deviceName Device name
     * @param deviceModel Device model
     * @param deviceLocation Room where the device is located
     */

    public Device(String deviceName, String deviceModel, String deviceLocation) {
        if (!areDeviceParametersValid(deviceName,deviceModel,deviceLocation)) {
            throw new IllegalArgumentException("Invalid parameter.");
        }
            this.deviceName = deviceName;
            this.deviceModel = deviceModel;
            this.deviceLocation = deviceLocation;
            this.status = true;
            this.listOfSensors = new ListOfSensors();

    }

    private boolean areDeviceParametersValid(String deviceName, String deviceModel, String deviceLocation) {
        if (deviceName == null || deviceName.trim().isEmpty()) {
            return false;
        }
        if (deviceModel == null || deviceModel.trim().isEmpty()) {
            return false;
        }
        return deviceLocation != null && !deviceLocation.trim().isEmpty();
    }

    public boolean addSensor(String sensorName, String sensorType, SensorCatalogue catalogue) {
        if(listOfSensors.addSensor(sensorName, sensorType, catalogue)){
            updateDeviceFunctionalities(sensorType);
            return true;
        }
        return false;

    }

    private void updateDeviceFunctionalities(String sensorType){
        if(!deviceFunctionalities.contains(sensorType)){
            deviceFunctionalities.add(sensorType);
        }
    }

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

    public Value<?> getReading(String sensorName, SimHardware simHardware) throws InstantiationException {
        return this.listOfSensors.getReading(sensorName,simHardware);
    }

}
