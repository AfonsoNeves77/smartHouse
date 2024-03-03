package SmartHome.domain.device;

import java.util.ArrayList;
import java.util.List;
public class ListOfDevices {
    private ArrayList<Device> deviceList;

    /**
     * Constructor for ListOfDevices, it initialized an empty arrayList as attribute.
     */
    public ListOfDevices (){
        this.deviceList = new ArrayList<>();
    }

    /**
     * Attempts to add a new device to the list of devices.
     * It first checks if there is already a device with the same name in the list.
     * If not, it creates a new device using the `createDevice()` method and adds it to the list.
     * Otherwise, it returns an error code indicating that the device name is duplicated.
     * @param deviceName The name of the device to add.
     * @param deviceModel The model of the device to add. For now, this method does not validate the device model.
     * @param location The location of the device to add.
     * @return A zero if the device was added successfully; a 1 if the device name is duplicated; a 2 if there is an error creating the device.
     */
    //For now this method can´t validate the device model.
    public boolean addDeviceToList (String deviceName, String deviceModel, String location, FactoryDevice factoryDevice){
        //device com o mm nome
        if (!isDeviceDuplicated(deviceName)){
            try{
                Device newDevice = factoryDevice.createDevice(deviceName, deviceModel, location);
                this.deviceList.add(newDevice);
                return true;
            } catch (IllegalArgumentException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if there is already a device with the specified device name in the list of devices.
     * @param deviceName The device name to check.
     * @return `true` if a device with the specified device name already exists in the list; `false` otherwise.
     */
    private boolean isDeviceDuplicated (String deviceName){
        if(!this.deviceList.isEmpty()){
            for(Device singleDevice : this.deviceList) {
                if (deviceName.equalsIgnoreCase(singleDevice.getDeviceName())) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the list of devices stored in this object.
     * @return The ArrayList containing the list of devices.
     */
    public List<Device> getDeviceList() {
        return new ArrayList<>(deviceList);
    }
}
