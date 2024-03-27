package SmartHomeDDD.domain.device;

import SmartHomeDDD.domain.DomainEntity;
import SmartHomeDDD.vo.deviceVO.DeviceIDVO;
import SmartHomeDDD.vo.deviceVO.DeviceModelVO;
import SmartHomeDDD.vo.deviceVO.DeviceNameVO;
import SmartHomeDDD.vo.deviceVO.DeviceStatusVO;
import SmartHomeDDD.vo.roomVO.RoomIDVO;

import java.util.UUID;

public class Device implements DomainEntity {

        private DeviceNameVO deviceName;
        private final DeviceModelVO deviceModel;
        private final RoomIDVO roomID;
        private DeviceStatusVO deviceStatus;
        private DeviceIDVO deviceID;


        /**
         * Constructs a new Device object with the specified device name, model, and room ID.
         * Validates the provided parameters to ensure they are not null.
         * Sets the initial device status to as true, representing that the device is active.
         * Assigns a unique device ID using a randomly generated UUID.
         */
        public Device(DeviceNameVO deviceName, DeviceModelVO deviceModel, RoomIDVO roomID) {
            if (!allParametersAreValid(deviceName, deviceModel, roomID)) {
                throw new IllegalArgumentException("Invalid parameters");
            }
            this.deviceName = deviceName;
            this.deviceModel = deviceModel;
            this.roomID = roomID;
            this.deviceStatus = new DeviceStatusVO(true);
            this.deviceID = new DeviceIDVO(UUID.randomUUID());
        }

        /**
         * Deactivates the device by setting its status to inactive.
         * This method creates a new DeviceStatusVO object with the value false, representing that the device is inactive,
         * and assigns it to the device's internal status field. It then returns the newly created DeviceStatusVO object.
         * @return a new DeviceStatusVO object reflecting the deactivated device status
         */
        public DeviceStatusVO deactivateDevice() {
            this.deviceStatus = new DeviceStatusVO(false);
            return this.deviceStatus;
        }

        /**
         * Checks if all the provided device parameters are valid (not null).
         * @param deviceName the name of the device
         * @param deviceModel the model of the device
         * @param roomID the ID of the room where the device is located
         * @return true if all parameters are valid, false otherwise
         */
        private boolean allParametersAreValid(DeviceNameVO deviceName, DeviceModelVO deviceModel, RoomIDVO roomID){
            return deviceName != null && deviceModel != null && roomID != null;
        }

        /**
         * Gets the device's name.
         * @return the device name object (DeviceNameVO)
         */
        public DeviceNameVO getDeviceName() {
            return this.deviceName;
        }

        /**
         * Gets the device's model.
         * @return the device model object (DeviceModelVO)
         */
        public DeviceModelVO getDeviceModel() {
            return this.deviceModel;
        }

        /**
         * Gets the device's status.
         * @return the device status object (DeviceStatusVO)
         */
        public DeviceStatusVO getDeviceStatus() {
            return this.deviceStatus;
        }

        /**
         * Gets the device's ID.
         * @return the device ID object (DeviceIDVO)
         */
        public RoomIDVO getRoomID() {
            return this.roomID;
        }

        /**
         * Gets the device's ID.
         * @return the device ID object (DeviceIDVO)
         */
        @Override
        public DeviceIDVO getId() {
            return this.deviceID;
        }
}
