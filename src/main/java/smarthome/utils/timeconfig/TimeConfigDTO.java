package smarthome.utils.timeconfig;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class TimeConfigDTO extends RepresentationModel<TimeConfigDTO> {

    String initialDate;
    String initialTime;
    String endDate;
    String endTime;
    String deltaMin;

}