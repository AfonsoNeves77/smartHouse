//package smarthome.mapper.assembler;
//
//import smarthome.domain.log.Log;
//import smarthome.domain.log.LogDataModel;
//import smarthome.domain.log.LogFactory;
//import smarthome.domain.sensor.values.SensorValueFactory;
//import smarthome.domain.sensor.values.SensorValueObject;
//import smarthome.domain.vo.devicevo.DeviceIDVO;
//import smarthome.domain.vo.logvo.LogIDVO;
//import smarthome.domain.vo.sensortype.SensorTypeIDVO;
//import smarthome.domain.vo.sensorvo.SensorIDVO;
//
//import java.time.LocalDateTime;
//
//public class LogAssembler {
//
//    /**
//     * Converts a LogDataModel object to a Log domain object using the provided factories.
//     * This method constructs a Log object using the given LogFactory and SensorValueFactory,
//     * populating its fields with data extracted from the LogDataModel object.
//     *
//     * <p>The SensorValueObject<?> employs generics to allow flexibility in data representation.
//     * The specific type for the SensorValueObject is dynamically determined by the implementation
//     * of the SensorValueFactory used.</p>
//     *
//     * @param logFactory     The factory responsible for creating Log objects.
//     * @param valueFactory   The factory responsible for creating SensorValueObjects.
//     * @param logDataModel   The LogDataModel object to convert to a Log domain object.
//     * @return A Log domain object representing the data from the LogDataModel.
//     * @throws NullPointerException     If any of the factories or the LogDataModel is null.
//     * @throws IllegalArgumentException If the LogDataModel contains invalid data or if there
//     *                                  is an error while creating the Log or SensorValueObject.
//     * @throws DateTimeParseException   If an error occurs while parsing the date and time string
//     *                                  from the LogDataModel.
//     * @throws NumberFormatException    If an error occurs while parsing numeric data from the
//     *                                  LogDataModel.
//     * @throws UnsupportedOperationException If the SensorValueFactory does not support the
//     *                                        sensor type specified in the LogDataModel.
//     */
//    public static Log toDomain(LogFactory logFactory, SensorValueFactory<?> valueFactory, LogDataModel logDataModel){
//        LogIDVO logID = new LogIDVO(logDataModel.getID());
//        LocalDateTime time = LocalDateTime.parse(logDataModel.getTime());
//        SensorIDVO sensorID = new SensorIDVO(logDataModel.getSensorID());
//        DeviceIDVO deviceIDVO = new DeviceIDVO(logDataModel.getDeviceID());
//        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(logDataModel.getSensorTypeID());
//        SensorValueObject<?> reading = valueFactory.createSensorValue(logDataModel.getReading(), sensorTypeIDVO);
//        return logFactory.createLog(logID,time,reading,sensorID,deviceIDVO,sensorTypeIDVO);
//    }
//
//}
