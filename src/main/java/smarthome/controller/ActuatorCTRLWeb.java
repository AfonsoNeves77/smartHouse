package smarthome.controller;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.actuatorvo.Settings;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.mapper.ActuatorMapper;
import smarthome.mapper.dto.ActuatorDTO;
import smarthome.service.ActuatorService;

import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/actuators")
public class ActuatorCTRLWeb {

    private final ActuatorService actuatorService;


    /**
     * Constructor for ActuatorCTRLWeb, it receives an ActuatorService, which is the service that will be used to
     * perform the operations related to the Actuator.
     * @param actuatorService actuator service
     */
    public ActuatorCTRLWeb(ActuatorService actuatorService) {
        if (actuatorService == null) {
            throw new IllegalArgumentException("Invalid service");
        }
        this.actuatorService = actuatorService;
    }

    /**
     * This method receives an ActuatorDTO and creates an Actuator with the information provided in the DTO.
     * It first creates the ActuatorNameVO, ActuatorTypeIDVO, DeviceIDVO and Settings objects from the DTO.
     * Then it calls the addActuator method from the ActuatorService, passing the created objects, and creates a
     * Optional<Actuator> with the returned value.
     * If the Optional is present, it creates an ActuatorDTO from the Actuator and adds a self link to it.
     * If the Optional is not present, it returns an UNPROCESSABLE_ENTITY status.
     * If an IllegalArgumentException is thrown, it returns a BAD_REQUEST status.
     * @param actuatorDTO actuatorDTO
     * @return ResponseEntity<ActuatorDTO>
     */
    @PostMapping("")
    public ResponseEntity<ActuatorDTO> addActuator(@RequestBody ActuatorDTO actuatorDTO) {
        try {
            ActuatorNameVO actuatorNameVO = ActuatorMapper.createActuatorNameVO(actuatorDTO);
            ActuatorTypeIDVO actuatorTypeIDVO = ActuatorMapper.createActuatorTypeIDVO(actuatorDTO);
            DeviceIDVO deviceIDVO = ActuatorMapper.createDeviceIDVO(actuatorDTO);
            Settings settings = ActuatorMapper.createSettingsVO(actuatorDTO);

            Optional<Actuator> optionalActuator = this.actuatorService.addActuator(actuatorNameVO, actuatorTypeIDVO, deviceIDVO, settings);

            if (optionalActuator.isPresent()) {
                Actuator savedActuator = optionalActuator.get();
                ActuatorDTO createdActuatorDTO = ActuatorMapper.domainToDTO(savedActuator);

                Link selfLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(createdActuatorDTO.getActuatorId())).withSelfRel();
                createdActuatorDTO.add(selfLink);

                return new ResponseEntity<>(createdActuatorDTO, HttpStatus.CREATED);
            } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * This method receives an actuator id and retrieves an Actuator with the given id.
     * It first creates an ActuatorIDVO from the id and calls the getActuatorById method from the ActuatorService,
     * creating an Optional<Actuator> with the returned value.
     * If the Optional is present, it creates an ActuatorDTO from the Actuator and adds a self link to it.
     * If the Optional is not present, it returns a NOT_FOUND status.
     * If an IllegalArgumentException is thrown, it returns a BAD_REQUEST status.
     * @param actuatorId actuatorId
     * @return ResponseEntity<ActuatorDTO>
     */
    @GetMapping(path = "/{actuatorId}")
    public ResponseEntity<ActuatorDTO> getActuatorById(@PathVariable ("actuatorId") String actuatorId) {
        try {
            ActuatorIDVO actuatorIDVO = ActuatorMapper.createActuatorIDVO(actuatorId);
            Optional<Actuator> optionalActuator = this.actuatorService.getActuatorById(actuatorIDVO);

            if (optionalActuator.isPresent()) {
                Actuator savedActuator = optionalActuator.get();
                ActuatorDTO actuatorDTO = ActuatorMapper.domainToDTO(savedActuator);

                Link selfLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(actuatorDTO.getActuatorId())).withSelfRel();
                actuatorDTO.add(selfLink);

                return new ResponseEntity<>(actuatorDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

