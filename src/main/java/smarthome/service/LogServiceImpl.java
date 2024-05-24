package smarthome.service;

import org.springframework.stereotype.Service;
import smarthome.domain.device.Device;
import smarthome.domain.log.Log;
import smarthome.domain.log.LogFactory;
import smarthome.domain.room.Room;
import smarthome.domain.sensor.sensorvalues.SensorValueObject;
import smarthome.domain.vo.DeltaVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.logvo.TimeStampVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.LogRepository;
import smarthome.persistence.RoomRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final DeviceRepository deviceRepository;
    private final RoomRepository roomRepository;
    private final LogFactory logFactory;

    /**
     * Constructor for LogServiceImpl.
     * @param logRepository the repository used for data access
     * @param deviceRepository the repository used for data access
     * @param roomRepository the repository used for data access
     * @throws IllegalArgumentException if the logRepository is null
     */
    public LogServiceImpl(LogRepository logRepository, DeviceRepository deviceRepository, RoomRepository roomRepository, LogFactory logFactory){
        if (areParamsNull(logRepository, deviceRepository, roomRepository, logFactory)) {
            throw new IllegalArgumentException("Repository cannot be null.");
        }
        this.logRepository = logRepository;
        this.deviceRepository = deviceRepository;
        this.roomRepository = roomRepository;
        this.logFactory = logFactory;
    }

    /**
     * Adds a new log entry to the log repository.
     * <p>
     * This method creates a new {@code Log} object using the provided sensor value, sensor ID, device ID,
     * and sensor type ID.
     * It first validates that none of the parameters are null. If any parameter is null, it throws
     * an {@code IllegalArgumentException}.
     * After validation, it uses the {@code LogFactory} to create a {@code Log} object and attempts to save it to
     * the log repository.
     * If the log is successfully saved, it returns an {@code Optional} containing the {@code Log} object. If the
     * log cannot be saved, it returns an empty {@code Optional}.
     * </p>
     *
     * @param value the sensor value associated with the log, must not be null
     * @param sensor the sensor ID associated with the log, must not be null
     * @param device the device ID associated with the log, must not be null
     * @param sensorType the sensor type ID associated with the log, must not be null
     * @return an {@code Optional<Log>} containing the log if it was successfully added, or an empty {@code Optional} if the log could not be added
     * @throws IllegalArgumentException if any of the parameters are null
     */
    public Optional<Log> addLog(SensorValueObject<?> value, SensorIDVO sensor, DeviceIDVO device, SensorTypeIDVO sensorType){
        if (areParamsNull(value, sensor, device, sensorType)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        Log log = logFactory.createLog(value, sensor, device, sensorType);
        if (logRepository.save(log)) {
            return Optional.of(log);
        }
        return Optional.empty();
    }


    /**
     * Retrieves all logs associated with a specific device within a given time period.
     * @param deviceID the ID of the device
     * @param initialTimeStamp the initial timestamp that represents the beginning of the time period
     * @param finalTimeStamp the final timestamp that represents the end of the time period
     * @return an Iterable of logs that match the given criteria
     * @throws IllegalArgumentException if any of the parameters are null
     */
    @Override
    public List<Log> findReadingsFromDeviceInATimePeriod (DeviceIDVO deviceID, TimeStampVO initialTimeStamp, TimeStampVO finalTimeStamp) {
        if (areParamsNull(deviceID)) {
            throw new IllegalArgumentException("Invalid parameters");
        }

        if (areTimeStampsInvalid(initialTimeStamp, finalTimeStamp)) {
            throw new IllegalArgumentException("Invalid Time Stamps");
        }

        try {
            Iterable<Log> iterable = logRepository.findByDeviceIDAndTimeBetween(deviceID, initialTimeStamp, finalTimeStamp);
            return convertToList(iterable);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
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
     * @param initialTimeStamp The initial timestamp that represents the beginning of the time period.
     * @param finalTimeStamp The final timestamp that represents the end of the time period.
     * @param deltaMin The minute difference allowed for the same readings to be considered as being in the same instant.
     * @return String message detailing the maximum temperature difference and the precise moment it
     * occurred, or a relevant error message.
     * @throws IllegalArgumentException if parameters are null, if the specified devices are not located
     * correctly (outdoor/indoor), or if no logs exist within the time span.
     */

    public String getMaxInstantaneousTempDifference(DeviceIDVO outdoorDevice, DeviceIDVO indoorDevice, TimeStampVO initialTimeStamp, TimeStampVO finalTimeStamp, DeltaVO deltaMin) {

        // Checks that params are not null
        if(areParamsNull(outdoorDevice, indoorDevice, initialTimeStamp, finalTimeStamp, deltaMin)){
            throw new IllegalArgumentException("Invalid Parameters");
        }

        // Checks that the outdoor deviceID is actually from a room on the exterior (Height =0), otherwise for indoor room
        if(!isOutdoorDeviceInTheExterior(outdoorDevice) || !isIndoorDeviceInTheInterior(indoorDevice)){
            throw new IllegalArgumentException("Invalid Device Location");
        }


        // Checks if the initial date time and final date time are valid, and that the final date time is not in the future
        // and that the initial date is before the final date.
        if(areTimeStampsInvalid(initialTimeStamp, finalTimeStamp)){
            throw new IllegalArgumentException("Invalid Time Stamps");
        }

        // Defines the sensorTypeID for the desired query
        String sensorTypeID = "TemperatureSensor";

        // Gets the Logs that result from the query for the Logs for the desired devices, with the desired sensor type, within the desired period/time frame
        Iterable<Log> outdoorDeviceLog = logRepository.getDeviceTemperatureLogs(outdoorDevice, sensorTypeID, initialTimeStamp, finalTimeStamp);
        Iterable<Log> indoorDeviceLog = logRepository.getDeviceTemperatureLogs(indoorDevice, sensorTypeID, initialTimeStamp, finalTimeStamp);

        return retrieveMaxTempDiffInAnInstant(outdoorDeviceLog, indoorDeviceLog, deltaMin);
    }


    /**
     * Retrieves the maximum instantaneous temperature difference between an indoor and an outdoor device over a given
     * time period.
     * @param outdoorDeviceLog The logs from the outdoor device.
     * @param indoorDeviceLog The logs from the indoor device.
     * @param delta The time window allowed for the same readings to be considered as being in the same instant (in minutes)
     * @return A string message detailing the maximum temperature difference and the precise moment it occurred, or a relevant error message.
     */
    private String retrieveMaxTempDiffInAnInstant(Iterable<Log> outdoorDeviceLog, Iterable<Log> indoorDeviceLog, DeltaVO delta){
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
            TimeStampVO intTime = interiorLog.getTime();
            for (Log exteriorLog : outdoorDeviceLog) {
                TimeStampVO extTime = exteriorLog.getTime();
                if (isWithinTimeWindow(intTime, extTime, delta)) {
                    double temp = getAbsoluteDifference(interiorLog, exteriorLog);
                    if (temp>maxTempDiff){
                        maxTempDiff=temp;
                        instantTime = interiorLog.getTime().getValue().toString();
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
     * @param deltaMin The time window in minutes.
     * @return true if the timestamps are within the time window, false otherwise.
     */
    private boolean isWithinTimeWindow(TimeStampVO intTime, TimeStampVO extTime, DeltaVO deltaMin) {
        LocalDateTime intT= intTime.getValue();
        LocalDateTime extT = extTime.getValue();
        int delta = deltaMin.getValue();
        return intT.plusMinutes(delta).isAfter(extT) && intT.minusMinutes(delta).isBefore(extT);
    }

    /**
     * Checks if the provided timestamps are invalid, i.e., if they are null, if the initial timestamp is after the final
     * and if the final timestamp is after the current time.
     * @param initial The initial timestamp.
     * @param end The final timestamp.
     * @return true if the timestamps are invalid, false otherwise.
     */
    private boolean areTimeStampsInvalid(TimeStampVO initial, TimeStampVO end){
        if (areParamsNull(initial,end)){
            return true;
        }
        LocalDateTime from = initial.getValue();
        LocalDateTime to = end.getValue();

        return from.isAfter(to) || to.isAfter(LocalDateTime.now());
    }


    /**
     * Retrieves the peak power consumption of the house within the specified time period.
     * The method performs several checks to ensure that the input parameters are valid and that there are available logs
     * within the specified time frame. If valid logs are found, it calculates the peak power consumption considering all
     * logs from the grid power meter and power source devices within the specified delta time window.
     *
     * @param start the start timestamp of the period, represented as a TimeStampVO object.
     * @param end the end timestamp of the period, represented as a TimeStampVO object.
     * @param delta the delta value to define the time window for matching logs, represented as a DeltaVO object.
     * @return a String message with the peak power consumption or an error message if no valid logs are found.
     * @throws IllegalArgumentException if the parameters are invalid or the timestamps are not valid.
     */
    public String getPeakPowerConsumption(TimeStampVO start, TimeStampVO end, DeltaVO delta) {
        if (areParamsNull(start, end, delta)) {
            throw new IllegalArgumentException("Invalid parameters");
        }
        // Checks if the initial date time and final date time are valid, and that the final date time is not in the future
        // and that the initial date is before the final date.
        if(areTimeStampsInvalid(start, end)){
            throw new IllegalArgumentException("Invalid Time Stamps");
        }

        // Retrieves the Power Grid Meter deviceID and its sensorTypeID from the system properties
        // and queries the database for logs from the Grid Power Meter within the specified time frame.
        // Also, queries the database for logs from Power Source Devices that have the same SensorType but
        // are not the Grid Power Meter, and that have negative readings, within the specified time frame.
        String deviceID = System.getProperty("Grid Power Meter device");
        String sensorTypeID = System.getProperty("Grid Power Meter sensor type");
        Iterable<Log> powerGridLogs = logRepository.findByDeviceIDAndSensorTypeAndTimeBetween(deviceID, sensorTypeID, start, end);
        Iterable<Log> powerSourceLogs = logRepository.findByNegativeReadingAndNotDeviceIDAndSensorTypeAndTimeBetween(deviceID, sensorTypeID, start, end);

        // Checks if there are no results for Logs from the Grid Power Meter, within the time frame provided.
        if(!powerGridLogs.iterator().hasNext()){
            return "There are no records available from the Grid Power Meter for the given period";
        }

        // Checks if there are any Power Source Devices from the query, and, if
        // not, retrieves the peak consumption from the Grid Power Meter Only.
        if(!powerSourceLogs.iterator().hasNext()){
            Log maxValueLog = getMaxValue(powerGridLogs);
            return "The Peak Power Consumption from the Grid within the selected Period was " + maxValueLog.getReading().getValue() +
                    " Wh which happened at " + maxValueLog.getTime().getValue() + " (No Power Source Device Logs were found within the selected period)";
        }

        int peakConsumption = 0;
        String instantTime = null;
        boolean foundInstantLogMatch = false;

        // Checks the power grid device and all power source devices for logs/records that are within the defined delta
        // (in case there are records/logs found in the query)
        // Compares the two logs to get the peak power consumption and the instant where it happened
        for (Log powerGridLog : powerGridLogs) {
            TimeStampVO gridTime = powerGridLog.getTime();
            int totalValue = (int) powerGridLog.getReading().getValue();

            for (Log powerSourceLog : powerSourceLogs) {
                TimeStampVO sourceTime = powerSourceLog.getTime();

                if (isWithinTimeWindow(gridTime, sourceTime, delta)) {
                    int sourceValue = (int) powerSourceLog.getReading().getValue();
                    totalValue += Math.abs(sourceValue);
                    foundInstantLogMatch = true;

                }
            }
            if (totalValue>peakConsumption){
                peakConsumption = totalValue;
                instantTime = powerGridLog.getTime().getValue().toString();
            }
        }
        // Checks if the foundInstantLogMatch variable has not been altered since it has been initialized
        // In case the variable has not been altered (is still false) it means there were no matches
        // in the same instant (instant is defined by the delta)
        // In case the variable is true, it means that there were matches found that are in the same instant
        // and retrieves the peak consumption of all the instantaneous readings, as well as the instant time when
        // that occurred.
        if(!foundInstantLogMatch){
            return "Readings were found within the provided time span, but with no instant matches within the delta provided";
        } else{
            return "The Peak Power Consumption of the House within the selected Period was of " + peakConsumption+ " Wh which happened at " + instantTime;
        }
    }

    /**
     * Retrieves the log with the maximum reading value from the provided list of logs.
     *
     * @param list an Iterable of Log objects.
     * @return the Log object with the maximum reading value.
     */
    private Log getMaxValue (Iterable<Log> list){
        int peakGridConsumption = (int) list.iterator().next().getReading().getValue();
        Log biggestReadingLog = null;
        for (Log log : list) {
            int powerGridValue = (int) log.getReading().getValue();
            if (powerGridValue >= peakGridConsumption) {
                peakGridConsumption = powerGridValue;
                biggestReadingLog = log;
            }
        }
        return biggestReadingLog;
    }

}
