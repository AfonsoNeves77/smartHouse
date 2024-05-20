package smarthome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import smarthome.domain.device.Device;
import smarthome.domain.sensor.Sensor;
import smarthome.domain.sensor.TemperatureSensor;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.sensortype.SensorTypeIDVO;
import smarthome.domain.vo.sensorvo.SensorIDVO;
import smarthome.domain.vo.sensorvo.SensorNameVO;
import smarthome.mapper.dto.SensorDTO;
import smarthome.persistence.DeviceRepository;
import smarthome.persistence.SensorRepository;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
public class SensorCTRLWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceRepository deviceRepository;

    @MockBean
    private SensorRepository sensorRepository;

    /**
     * This test verifies that when a GET request is made with a valid sensor ID,
     * the response contains the expected sensor data and a status of OK (200).
     * The test sets up a mock sensor with a known ID, name, device ID, and sensor type ID.
     * It then expects that the sensor repository will return this sensor when queried with the sensor ID.
     * The test performs a GET request with the sensor ID and asserts that the response status is OK and the body contains the expected sensor data.
     */
    @Test
    void givenValidSensorID_whenGetSensorByID_thenReturnSensor() throws Exception {
        //Arrange
        String sensorID = "1fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorName = "Sensor1";
        String deviceID = "2fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        SensorIDVO sensorIDVO = new SensorIDVO(UUID.fromString(sensorID));
        SensorNameVO sensorNameVO = new SensorNameVO(sensorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(sensorTypeID);

        Sensor sensor = new TemperatureSensor(sensorIDVO, sensorNameVO, deviceIDVO, sensorTypeIDVO);

        Link expectedLink = linkTo(methodOn(SensorCTRLWeb.class).getSensorByID(sensorID)).withSelfRel();

        when(sensorRepository.isPresent(sensorIDVO)).thenReturn(true);
        when(sensorRepository.findById(sensorIDVO)).thenReturn(sensor);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/" + sensorID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.sensorID").value(sensorID))
                .andExpect(jsonPath("$.sensorName").value(sensorName))
                .andExpect(jsonPath("$.deviceID").value(deviceID))
                .andExpect(jsonPath("$.sensorTypeID").value(sensorTypeID))
                .andExpect(jsonPath("$._links.self.href").value(expectedLink.getHref()))
                .andReturn();
    }

    /**
     * This test verifies that when a GET request is made with a sensor ID that does not exist in the sensor repository,
     * the response status is NOT FOUND (404).
     * The test sets up a mock sensor with a known ID, name, device ID, and sensor type ID.
     * It then expects that the sensor repository will return false when queried with the sensor ID.
     * The test performs a GET request with the sensor ID and asserts that the response status is NOT FOUND.
     */
    @Test
    void givenNotSavedDeviceID_whenGetSensorByID_thenReturnNotFoundCode() throws Exception {
        //Arrange
        String sensorID = "1fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorName = "Sensor1";
        String deviceID = "2fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        SensorIDVO sensorIDVO = new SensorIDVO(UUID.fromString(sensorID));
        SensorNameVO sensorNameVO = new SensorNameVO(sensorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));
        SensorTypeIDVO sensorTypeIDVO = new SensorTypeIDVO(sensorTypeID);

        Sensor sensor = new TemperatureSensor(sensorIDVO, sensorNameVO, deviceIDVO, sensorTypeIDVO);

        when(sensorRepository.isPresent(sensorIDVO)).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/" + sensorID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn();
    }

    /**
     * This test verifies that when a GET request is made with an invalid sensor ID (null in this case),
     * the response status is BAD REQUEST (400).
     * The test performs a GET request with a null sensor ID and asserts that the response status is BAD REQUEST.
     */
    @Test
    void givenInvalidDeviceId_whenGetSensorByID_thenReturnBadRequestCode() throws Exception {
        //Arrange
        SensorIDVO sensorIDVO = null;

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/sensors/" + sensorIDVO)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies that when a POST request is made with a valid sensor DTO and the sensor is successfully saved,
     * the response contains the expected sensor data and a status of CREATED (201).
     * The test sets up a mock sensor DTO with a known name, device ID, and sensor type ID.
     * It then expects that the sensor repository will return true when the sensor is saved.
     * The test performs a POST request with the sensor DTO and asserts that the response status is CREATED and the body contains the expected sensor data.
     */
    @Test
    void givenValidDeviceDTO_whenAddSensorToDevice_thenReturnCreatedCode() throws Exception {
        //Arrange
        String sensorName = "Sensor1";
        String deviceID = "1fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));


        Device device = mock(Device.class);
        when(device.isActive()).thenReturn(true);
        when(device.getId()).thenReturn(deviceIDVO);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);

        SensorDTO sensorDTO = SensorDTO.builder()
                .sensorName(sensorName)
                .deviceID(deviceID)
                .sensorTypeID(sensorTypeID)
                .build();

        String sensorAsJSON = objectMapper.writeValueAsString(sensorDTO);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(sensorRepository.save(any(Sensor.class))).thenReturn(true);

        //Act + Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(sensorAsJSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.sensorName").value(sensorName))
                .andExpect(jsonPath("$.deviceID").value(deviceID))
                .andExpect(jsonPath("$.sensorTypeID").value(sensorTypeID))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andReturn();
    }

    /**
     * This test verifies that when a POST request is made with a valid sensor DTO but the device does not exist in the device repository,
     * the response status is BAD REQUEST (400).
     * The test sets up a mock sensor DTO with a known name, device ID, and sensor type ID.
     * It then expects that the device repository will return false when queried with the device ID.
     * The test performs a POST request with the sensor DTO and asserts that the response status is BAD REQUEST.
     */
    @Test
    void givenNotSavedDevice_whenAddSensorToDevice_thenReturnBadRequestCode() throws Exception {
        //Arrange
        String sensorName = "Sensor1";
        String deviceID = "2fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));

        SensorDTO sensorDTO = SensorDTO.builder()
                .sensorName(sensorName)
                .deviceID(deviceID)
                .sensorTypeID(sensorTypeID)
                .build();

        String sensorAsJSON = objectMapper.writeValueAsString(sensorDTO);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(sensorAsJSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies that when a POST request is made with an invalid sensor DTO (null sensor type ID in this case),
     * the response status is BAD REQUEST (400).
     * The test sets up a mock sensor DTO with a known name and device ID, but a null sensor type ID.
     * The test performs a POST request with the sensor DTO and asserts that the response status is BAD REQUEST.
     */
    @Test
    void givenInvalidDeviceDTO_whenAddSensorToDevice_thenReturnBadRequestCode() throws Exception {
        //Arrange
        String sensorName = "Sensor1";
        String deviceID = "2fa85f64-5717-4562-b3fc-2c963f66afa6";
        SensorDTO sensorDTO = SensorDTO.builder()
                .sensorName(sensorName)
                .deviceID(deviceID)
                .sensorTypeID(null)
                .build();

        String sensorAsJSON = objectMapper.writeValueAsString(sensorDTO);
        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(sensorAsJSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies that when a POST request is made with a valid sensor DTO but the sensor fails to save,
     * the response status is UNPROCESSABLE ENTITY (422).
     * The test sets up a mock sensor DTO with a known name, device ID, and sensor type ID.
     * It then expects that the sensor repository will return false when the sensor is saved.
     * The test performs a POST request with the sensor DTO and asserts that the response status is UNPROCESSABLE ENTITY.
     */
    @Test
    void givenValidDeviceDTO_whenSavingFails_thenReturnUnprocessableEntityCode() throws Exception {
        //Arrange
        String sensorName = "Sensor1";
        String deviceID = "1fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));

        Device device = mock(Device.class);
        when(device.isActive()).thenReturn(true);
        when(device.getId()).thenReturn(deviceIDVO);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);

        SensorDTO sensorDTO = SensorDTO.builder()
                .sensorName(sensorName)
                .deviceID(deviceID)
                .sensorTypeID(sensorTypeID)
                .build();

        String sensorAsJSON = objectMapper.writeValueAsString(sensorDTO);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(sensorRepository.save(any(Sensor.class))).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(sensorAsJSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies that when a POST request is made with a valid sensor DTO but the sensor already exists,
     * the response status is UNPROCESSABLE ENTITY (422).
     * The test sets up a mock sensor DTO with a known name, device ID, and sensor type ID.
     * It then expects that the sensor repository will return false when the sensor is saved.
     * The test performs a POST request with the sensor DTO and asserts that the response status is UNPROCESSABLE ENTITY.
     */
    @Test
    void givenAlreadyExistentSensor_whenAddSensorToDevice_thenReturnConflictCode() throws Exception {
        //Arrange
        String sensorName = "Sensor1";
        String deviceID = "1fa85f64-5717-4562-b3fc-2c963f66afa6";
        String sensorTypeID = "TemperatureSensor";

        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceID));

        Device device = mock(Device.class);
        when(device.isActive()).thenReturn(true);
        when(device.getId()).thenReturn(deviceIDVO);

        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);

        SensorDTO sensorDTO = SensorDTO.builder()
                .sensorName(sensorName)
                .deviceID(deviceID)
                .sensorTypeID(sensorTypeID)
                .build();

        String sensorAsJSON = objectMapper.writeValueAsString(sensorDTO);

        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(sensorRepository.save(any(Sensor.class))).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/sensors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(sensorAsJSON))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }
}