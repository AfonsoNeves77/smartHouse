package smarthome.mapper.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;
/**
 * Data Transfer Object (DTO) class for Sensor.
 * This class extends {@link RepresentationModel} which includes links to other resources.
 * The SensorDTO is used to transfer data between different parts of the application,
 * or between applications, and can be used to map domain objects to values that can be used in the view.
 */
@EqualsAndHashCode(callSuper = false)
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SensorDTO extends RepresentationModel<SensorDTO> {
    String sensorID;
    String sensorName;
    String deviceID;
    String sensorTypeID;


}