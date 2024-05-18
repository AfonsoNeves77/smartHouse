package smarthome.utils.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.RollerBlindActuator;
import smarthome.domain.device.Device;
import smarthome.domain.house.House;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.AveragePowerConsumptionSensor;
import smarthome.domain.sensor.Sensor;
import smarthome.domain.sensor.TemperatureSensor;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.housevo.*;
import smarthome.domain.vo.roomvo.*;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;
import smarthome.persistence.*;

import java.util.UUID;

/**
 * The ApplicationBootstrap Class is a Spring Boot component that implements CommandLineRunner to initialize and save
 * default data into various repositories when the application starts. It creates and persists a default house,
 * several rooms, devices, sensors, and an actuator, ensuring the application has initial required data to work with.
 * A default House is added for its location to be configured as desired;
 * Three Rooms are added in order to add strategic devices to meet use cases needs (Electrical Circuit Room, Garden
 * and Kitchen);
 * Three Devices are added, one to each Room mentioned above. A Power Meter Device, two Devices that have the
 * capacity to measure the surrounding temperature, and another one that adjusts the roller blinds;
 * An Average Power Consumption Sensor is added to the Power Meter Device;
 * The temperature controller Devices get a Temperature Sensor;
 * An Actuator is placed on the roller blind Device controller.
 */
@Component
public class ApplicationBootstrap implements CommandLineRunner {

    HouseRepository houseRepository;

    RoomRepository roomRepository;

    DeviceRepository deviceRepository;

    SensorRepository sensorRepository;

    ActuatorRepository actuatorRepository;

    public ApplicationBootstrap(HouseRepository houseRepository, RoomRepository roomRepository, DeviceRepository deviceRepository,
                                SensorRepository sensorRepository, ActuatorRepository actuatorRepository){
        this.houseRepository = houseRepository;
        this.roomRepository = roomRepository;
        this.deviceRepository = deviceRepository;
        this.sensorRepository = sensorRepository;
        this.actuatorRepository = actuatorRepository;
    }

    @Override
    public void run(String... args) {

        //Add a House to the System:
        LocationVO locationVO = new LocationVO(
                new AddressVO(new DoorVO("default door"), new StreetVO("default street"), new CityVO("default city"),
                        new CountryVO("Portugal"), new PostalCodeVO("PT-1234-567")),
                new GpsVO(new LatitudeVO(0), new LongitudeVO(0)));

        House defaultHouse = new House(locationVO);
        houseRepository.save(defaultHouse);

        //Add Rooms to the System:
        String houseId = defaultHouse.getId().getID();
        Room electricalRoom = new Room(
                new RoomNameVO("Electrical Circuit Room"), new RoomFloorVO(0),
                new RoomDimensionsVO(new RoomLengthVO(2), new RoomWidthVO(2), new RoomHeightVO(2)),
                new HouseIDVO(UUID.fromString(houseId)));

        Room outdoorRoom = new Room(
                new RoomNameVO("Garden"), new RoomFloorVO(-1),
                new RoomDimensionsVO(new RoomLengthVO(40), new RoomWidthVO(70), new RoomHeightVO(0)),
                new HouseIDVO(UUID.fromString(houseId)));

        Room indoorRoom = new Room(
                new RoomNameVO("Kitchen"), new RoomFloorVO(1),
                new RoomDimensionsVO(new RoomLengthVO(7), new RoomWidthVO(6), new RoomHeightVO(2)),
                new HouseIDVO(UUID.fromString(houseId)));

        roomRepository.save(electricalRoom);
        roomRepository.save(outdoorRoom);
        roomRepository.save(indoorRoom);

        //Add Devices to the System:
        String electricalRoomId = electricalRoom.getId().getID();
        String outdoorRoomId = outdoorRoom.getId().getID();
        String indoorRoomId = indoorRoom.getId().getID();

        Device powerMeter = new Device(new DeviceNameVO("Electric Power Meter"),
                new DeviceModelVO("XP-Y568"),
                new RoomIDVO(UUID.fromString(electricalRoomId)));

        Device outdoorDevice = new Device(new DeviceNameVO("Garden Temperature Controller"),
                new DeviceModelVO("WS-5050"),
                new RoomIDVO(UUID.fromString(outdoorRoomId)));

        Device indoorDeviceOne = new Device(new DeviceNameVO("Kitchen Temperature Controller"),
                new DeviceModelVO("WS-2000"),
                new RoomIDVO(UUID.fromString(indoorRoomId)));

        Device indoorDeviceTwo = new Device(new DeviceNameVO("Kitchen Natural Light Controller"),
                new DeviceModelVO("RB-370"),
                new RoomIDVO(UUID.fromString(indoorRoomId)));

        deviceRepository.save(powerMeter);
        deviceRepository.save(outdoorDevice);
        deviceRepository.save(indoorDeviceOne);
        deviceRepository.save(indoorDeviceTwo);

        //Add Temperature Sensors and Power Consumption Sensor:
        String powerMeterDeviceId = powerMeter.getId().getID();
        String outdoorDevId = outdoorDevice.getId().getID();
        String indoorDevIdOne = indoorDeviceOne.getId().getID();
        String indoorDevIdTwo = indoorDeviceTwo.getId().getID();

        Sensor avgPowerConsumptionSensor = new AveragePowerConsumptionSensor(
                new SensorNameVO("Average Power Meter"),
                new DeviceIDVO(UUID.fromString(powerMeterDeviceId)),
                new SensorTypeIDVO("AveragePowerConsumptionSensor"));

        Sensor outTempSensor = new TemperatureSensor(
                new SensorNameVO("Outdoor Temperature Sensor"),
                new DeviceIDVO(UUID.fromString(outdoorDevId)),
                new SensorTypeIDVO("TemperatureSensor"));

        Sensor inTempSensor = new TemperatureSensor(
                new SensorNameVO("Indoor Temperature Sensor"),
                new DeviceIDVO(UUID.fromString(indoorDevIdOne)),
                new SensorTypeIDVO("TemperatureSensor"));

        sensorRepository.save(avgPowerConsumptionSensor);
        sensorRepository.save(outTempSensor);
        sensorRepository.save(inTempSensor);

        //Add Roller Blind Actuator:
        Actuator rollerBlindActuator = new RollerBlindActuator(
                new ActuatorNameVO("Kitchen Roller"),
                new ActuatorTypeIDVO("RollerBlindActuator"),
                new DeviceIDVO(UUID.fromString(indoorDevIdTwo)));

        actuatorRepository.save(rollerBlindActuator);

    }
}
