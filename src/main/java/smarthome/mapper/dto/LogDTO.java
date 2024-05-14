package smarthome.mapper.dto;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor  // Generates a no-argument constructor
@AllArgsConstructor // Generates a constructor with all arguments
@Getter
public class LogDTO extends RepresentationModel<LogDTO> {
        String logID;
        String time;
        String reading;
        String sensorID;
        String deviceID;
        String sensorTypeID;
}
