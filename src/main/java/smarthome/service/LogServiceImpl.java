package smarthome.service;

import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.room.Room;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;
import smarthome.utils.timeconfig.TimeConfig;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;
    private static final int DELTA = 5; // Default time delta in minutes for comparisons of instant matches on readings.

    /**
     * Constructor for LogServiceImpl.
     * @param logRepository the repository used for data access
     * @throws IllegalArgumentException if the logRepository is null
     */
    public LogServiceImpl(LogRepository logRepository, DeviceRepository deviceRepository, RoomRepository roomRepository) {
        if (areParamsNull(logRepository, deviceRepository, roomRepository)) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        this.logRepository = logRepository;
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
    }

    /**
     * Retrieves all logs associated with a specific device within a given time period.
     * @param deviceID the ID of the device
     * @param time     the time configuration object
     * @return an Iterable of logs that match the given criteria
     * @throws IllegalArgumentException if any of the parameters are null
     */
    @Override
    public List<Log> findReadingsFromDeviceInATimePeriod(DeviceIDVO deviceID, TimeConfig time) {
        if (areParamsNull(deviceID, time)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        LocalDateTime from = time.getInitialTimeStamp();
        LocalDateTime to = time.getEndTimeStamp();

        try {
            Iterable<Log> iterable = logRepository.findByDeviceIDAndTimeBetween(deviceID, from, to);
            return convertToList(iterable);
        } catch (IllegalArgumentException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Checks if any of the provided parameters are null.
     * <p>
     * This method takes a variable number of parameters and iterates over them to determine if any of them are null.
     * If any parameter is found to be null, the method returns true; otherwise, it returns false. This method is useful
     * for checking multiple parameters for nullability in a concise way.
     * @param params The parameters to be checked for null.
     * @return true if any of the parameters are null, false otherwise.
     */
    private boolean areParamsNull(Object... params){
        for(Object param : params){
            if (param == null){
                return true;
            }
        }
        return false;
    }

    /**
     * Converts an Iterable of Log objects into an ArrayList.
     * @param logIterable The Iterable containing Log objects to be converted.
     * @return An ArrayList containing the Log objects from the Iterable.
     */
    private List<Log> convertToList (Iterable<Log> logIterable){
        List<Log> listLogs = new ArrayList<>();
        for (Log log : logIterable){
            listLogs.add(log);
        }
        return listLogs;
    }

    /**
     * Computes the maximum instantaneous temperature difference between an indoor and an outdoor device over
     * a given time period and delta.
     * Verifies device locations, checks logs within the specified time window, and determines the
     * maximum temperature difference.
     *
     * @param outdoorDevice The identification of the outdoor device.
     * @param indoorDevice The identification of the indoor device.
     * @param timeConfig The configuration containing the initial and end timestamps of the time period, and the
     * delta for matching log timestamps.
     * @return String message detailing the maximum temperature difference and the precise moment it
     * occurred, or a relevant error message.
     * @throws IllegalArgumentException if parameters are null, if the specified devices are not located
     * correctly (outdoor/indoor), or if no logs exist within the time span.
     */

    public String getMaxInstantaneousTempDifference(DeviceIDVO outdoorDevice, DeviceIDVO indoorDevice, TimeConfig timeConfig) {

        // Checks that params are not null
        if(areParamsNull(outdoorDevice, indoorDevice, timeConfig)){
            throw new IllegalArgumentException("Invalid Parameters");
        }

        // Checks that the outdoor deviceID is actually from a room on the exterior (Height =0), otherwise for indoor room
        if(!isOutdoorDeviceInTheExterior(outdoorDevice) || !isIndoorDeviceInTheInterior(indoorDevice)){
            throw new IllegalArgumentException("Invalid Device Location");
        }

        // Gets the initialDateTime, finalDateTime and delta that represent the time window to search for logs, and the delta
        LocalDateTime initialDateTime = timeConfig.getInitialTimeStamp();
        LocalDateTime finalDateTime = timeConfig.getEndTimeStamp();

        // Sets delta at 5 if the user did not select a valid delta
        int delta = configureDelta(timeConfig);

        // Defines the sensorTypeID for the desired query
        String sensorTypeID = "TemperatureSensor";

        // Gets the Logs that result from the query for the Logs for the desired devices, with the desired sensor type, within the desired period/time frame
        Iterable<Log> outdoorDeviceLog = logRepository.getDeviceTemperatureLogs(outdoorDevice, sensorTypeID, initialDateTime, finalDateTime);
        Iterable<Log> indoorDeviceLog = logRepository.getDeviceTemperatureLogs(indoorDevice, sensorTypeID, initialDateTime, finalDateTime);

        return retrieveMaxTempDiffInAnInstant(outdoorDeviceLog, indoorDeviceLog, delta);
    }


    /**
     * Retrieves the maximum instantaneous temperature difference between an indoor and an outdoor device over a given
     * time period.
     * @param outdoorDeviceLog The logs from the outdoor device.
     * @param indoorDeviceLog The logs from the indoor device.
     * @param delta The time window allowed for the same readings to be considered as being in the same instant (in minutes)
     * @return A string message detailing the maximum temperature difference and the precise moment it occurred, or a relevant error message.
     */
    private String retrieveMaxTempDiffInAnInstant(Iterable<Log> outdoorDeviceLog, Iterable<Log> indoorDeviceLog, int delta){
        // Defines the two variables needed: Maximum temperature difference and the Instant where it occurred.
        double maxTempDiff = 0;
        String instantTime = null;

        // Checks if there are any logs/records from the query
        if(!outdoorDeviceLog.iterator().hasNext() || !indoorDeviceLog.iterator().hasNext()){
            return "There are no records available for the given period";
        }

        // Checks the outdoor device and the indoor device for logs/records that are within the defined delta (in case
        // there are records/logs found in the query)
        // Compares the two logs to get the maximum temperature difference and the instant where it happened
        for (Log interiorLog : indoorDeviceLog) {
            LocalDateTime intTime = interiorLog.getTime();
            for (Log exteriorLog : outdoorDeviceLog) {
                LocalDateTime extTime = exteriorLog.getTime();
                if (isWithinTimeWindow(intTime, extTime, delta)) {
                    double temp = getAbsoluteDifference(interiorLog, exteriorLog);
                    if (temp>maxTempDiff){
                        maxTempDiff=temp;
                        instantTime = interiorLog.getTime().toString();
                    }
                }
            }
        }
        // Checks if the instantTime variable has not been altered since it has been initialized
        // In case the variable has not been altered (is still null) it means there were no matches that were
        // in the same instant (instant is defined by the delta)
        // In case the variable has a new value, it means that there were matches found that are in the same instant
        // and retrieves the biggest temperature difference between all the instantaneous readings, as well
        // as the instant time when that difference occurred.
        if(instantTime==null){
            return "Readings were found within the provided time span, but with no matches within the delta provided";
        } else{
            return "The Maximum Temperature Difference within the selected Period was of " +maxTempDiff+ " Cº which happened at " +instantTime;
        }
    }
    /**
     * Checks if the outdoor device is located in the exterior of the House.
     * The method checks the room where the device is located and checks if the room's height is 0, which means
     * the room is located in the exterior.
     * @param outdoorDevice The identification of the outdoor device.
     * @return true if the outdoor device is located in the exterior, false otherwise.
     */
    private boolean isOutdoorDeviceInTheExterior(DeviceIDVO outdoorDevice){
        Device outDevice = deviceRepository.findById(outdoorDevice);
        RoomIDVO outRoomID = outDevice.getRoomID();
        Room outRoom = roomRepository.findById(outRoomID);

        return outRoom.getRoomDimensions().getRoomHeight()==0;
    }

    /**
     * Checks if the indoor device is located in the interior of the House.
     * The method checks the room where the device is located and checks if the room's height is
     * greater than 0, which means the room is located in the interior.
     * @param indoorDevice The identification of the indoor device.
     * @return true if the indoor device is located in the interior, false otherwise.
     */
    private boolean isIndoorDeviceInTheInterior(DeviceIDVO indoorDevice){
        Device inDevice = deviceRepository.findById(indoorDevice);
        RoomIDVO inRoomID = inDevice.getRoomID();
        Room inRoom = roomRepository.findById(inRoomID);

        return inRoom.getRoomDimensions().getRoomHeight()>0;
    }

    /**
     * Configures the delta for the comparison of the logs' reading timestamps.
     * If the user does not provide a valid delta, the method sets the delta to 5 minutes.
     * @param timeConfig The configuration containing the initial and end timestamps of the time period, as well
     * as the delta for matching log timestamps.
     * @return The delta for the comparison of logs.
     */
    private int configureDelta(TimeConfig timeConfig) {
        int delta = timeConfig.getDeltaMin();
        if (delta <= 0) {
            delta = DELTA;
        }
        return delta;
    }


    /**
     * Gets the absolute difference between the readings of two logs.
     * @param interiorLog The log from the indoor device.
     * @param exteriorLog The log from the outdoor device.
     * @return The absolute difference between the readings of the two logs.
     */
    private double getAbsoluteDifference(Log interiorLog, Log exteriorLog){
        return Math.abs((Double) interiorLog.getReading().getValue() - (Double) exteriorLog.getReading().getValue());
    }

    /**
     * Checks if the reading timestamps of the two logs from the interior and exterior place
     * devices are within a given time window.
     * @param intTime The timestamp of the indoor device's reading.
     * @param extTime The timestamp of the outdoor device's reading.
     * @param delta The time window in minutes.
     * @return true if the timestamps are within the time window, false otherwise.
     */
    private boolean isWithinTimeWindow(LocalDateTime intTime, LocalDateTime extTime, int delta) {
        return intTime.plusMinutes(delta).isAfter(extTime) && intTime.minusMinutes(delta).isBefore(extTime);
    }


}
