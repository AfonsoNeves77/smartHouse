package smarthome.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.Link;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import smarthome.domain.actuator.Actuator;
import smarthome.domain.actuator.DecimalValueActuator;
import smarthome.domain.actuator.IntegerValueActuator;
import smarthome.domain.actuator.SwitchActuator;
import smarthome.domain.device.Device;
import smarthome.domain.vo.actuatortype.ActuatorTypeIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorIDVO;
import smarthome.domain.vo.actuatorvo.ActuatorNameVO;
import smarthome.domain.vo.actuatorvo.DecimalSettingsVO;
import smarthome.domain.vo.actuatorvo.IntegerSettingsVO;
import smarthome.domain.vo.devicevo.DeviceIDVO;
import smarthome.domain.vo.devicevo.DeviceModelVO;
import smarthome.domain.vo.devicevo.DeviceNameVO;
import smarthome.domain.vo.roomvo.RoomIDVO;
import smarthome.mapper.dto.ActuatorDTO;
import smarthome.persistence.ActuatorRepository;
import smarthome.persistence.ActuatorTypeRepository;
import smarthome.persistence.DeviceRepository;
import smarthome.service.ActuatorService;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ActuatorCTRLWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DeviceRepository deviceRepository;

    @MockBean
    private ActuatorRepository actuatorRepository;

    @MockBean
    private ActuatorTypeRepository actuatorTypeRepository;


    /**
     * Test for the ActuatorCTRLWeb constructor. It tests if an IllegalArgumentException is thrown when the
     * ActuatorService is null.
     */
    @Test
    void whenActuatorServiceIsNull_thenThrowsIllegalArgumentException() {
        //Arrange
        ActuatorService actuatorService = null;

        String expected = "Invalid service";

        //Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new ActuatorCTRLWeb(actuatorService));
        String result = exception.getMessage();

        //Assert
        assertEquals(expected,result);
    }


    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO when a null actuatorType is
     * passed. The test creates an ActuatorDTO with a null actuatorType and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the actuatorType is a required field for creating an actuator, so a null actuatorType should
     * result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullActuatorType_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = null;
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO when a null deviceId is passed.
     * The test creates an ActuatorDTO with a null deviceId and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the deviceId is a required field for creating an actuator, so a null deviceId should result in
     * a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullDeviceId_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = null;
        String actuatorTypeID = "RollerBlindActuator";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a null actuatorName is
     * passed. The test creates an ActuatorDTO with a null actuatorName and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the actuatorName is a required field for creating an actuator, so a null actuatorName should
     * result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullActuatorName_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = null;
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a null lowerLimit is passed.
     * The test creates an ActuatorDTO with a null lowerLimit and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the lowerLimit is a required field for creating an actuator, so a null lowerLimit should result
     * in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullLowerLimit_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = null;
        String upperLimit = "30";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with an invalid lowerLimit
     * is passed. The test creates an ActuatorDTO with a lowerLimit that is a decimal value, which is invalid because
     * the lowerLimit should be an integer.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the lowerLimit is a required field for creating an actuator, and it should be an integer, so an
     * invalid lowerLimit should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithInvalidLowerLimit_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a null upperLimit is passed.
     * The test creates an ActuatorDTO with a null upperLimit and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the upperLimit is a required field for creating an actuator, so a null upperLimit should result
     * in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullUpperLimit_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10";
        String upperLimit = null;
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with an invalid upperLimit is
     * passed. The test creates an ActuatorDTO with an upperLimit that is a decimal value, which is invalid because
     * the upperLimit should be an integer.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the upperLimit is a required field for creating an actuator, and it should be an integer, so
     * an invalid upperLimit should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithInvalidUpperLimit_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10";
        String upperLimit = "30.0";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a lowerLimit higher than
     * the upperLimit is passed. The test creates an ActuatorDTO with a lowerLimit of "30" and an upperLimit of "10",
     * which is invalid because the lowerLimit should not be higher than the upperLimit.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the lowerLimit should not be higher than the upperLimit for creating an actuator, so an invalid
     * lowerLimit and upperLimit should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithLowerHigherThanUpperInteger_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "30";
        String upperLimit = "10";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a null precision is passed.
     * The test creates an ActuatorDTO with a null precision and converts it to a JSON string.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the precision is a required field for creating an actuator, so a null precision should result
     * in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithNullPrecision_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = null;
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with an invalid precision is
     * passed. The test creates an ActuatorDTO with a precision of "1", which is invalid because the precision should
     * be a decimal value less than 1.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the precision is a required field for creating an actuator, and it should be a decimal value
     * less than 1, so an invalid precision should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithInvalidPrecision_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = "1";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a lowerLimit higher than
     * the upperLimit is passed. The test creates an ActuatorDTO with a lowerLimit of "30.0" and an upperLimit of
     * "10.0", which is invalid because the lowerLimit should not be higher than the upperLimit.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the lowerLimit should not be higher than the upperLimit for creating an actuator, so an invalid
     * lowerLimit and upperLimit should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithLowerHigherThanUpperDecimal_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "30.0";
        String upperLimit = "10.0";
        String precision = "0.1";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a precision equal to zero
     * is passed. The test creates an ActuatorDTO with a precision of "-0.0", which is invalid because the precision
     * should be a decimal value greater than 0 and less than 1.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the precision is a required field for creating an actuator, and it should be a decimal value
     * greater than 0 and less than 1, so a precision equal to zero should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithPrecisionEqualToZero_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = "-0.0";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a precision equal to one
     * is passed. The test creates an ActuatorDTO with a precision of "1", which is invalid because the precision
     * should be a decimal value less than 1.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the precision is a required field for creating an actuator, and it should be a decimal value
     * less than 1, so a precision equal to one should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorDTOWithPrecisionEqualToOne_whenAddActuatorCalled_thenReturnBadRequest() throws Exception {
        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = "1";
        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when an ActuatorDTO with a non-existing actuatorType
     * is passed. The test creates an ActuatorDTO with an actuatorType of "NotPresent", which is invalid.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the actuatorType is a required field for creating an actuator, and it should exist in the
     * actuatorTypeRepository, so a non-existing actuatorType should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidDTO_whenAddActuatorCalled_ifActuatorTypeNotPresent_thenReturnBadRequest() throws Exception {

        //Arrange
        String actuatorName = "Blind Roller";
        String deviceId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "NotPresent";
        String lowerLimit = "30.0";
        String upperLimit = "10.0";
        String precision = "0.1";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed but the device
     * associated with the ActuatorDTO does not exist in the system.
     * The test creates a valid ActuatorDTO and a Device object, but the device repository is stubbed to return false.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO
     * as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the device associated with the ActuatorDTO is a required field for creating an actuator, and
     * it should exist in the device repository, so a non-existing device should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalled_ifDeviceNotPresent_thenReturnUnprocessableEntityCode()
            throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);
        device.deactivateDevice();

        String actuatorName = "Blind Roller";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "30.0";
        String upperLimit = "10.0";
        String precision = "0.1";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));



        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed but the device
     * associated with the ActuatorDTO is deactivated in the system.
     * The test creates a valid ActuatorDTO and a Device object, but the device is deactivated.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO
     * as the request body.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the device associated with the ActuatorDTO is a required field for creating an actuator, and
     * it should be active in the device repository, so a deactivated device should result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalled_ifDeviceDeactivated_thenReturnBadRequest()
            throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);
        device.deactivateDevice();

        String actuatorName = "Blind Roller";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "30.0";
        String upperLimit = "10.0";
        String precision = "0.1";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));



        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed but the saving
     * of the Actuator in the repository fails.
     * The test creates a valid ActuatorDTO and a Device object, and the repositories are stubbed to return true for
     * the existence of the actuator type and the device, but false for the saving of the Actuator.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO as the
     * request body.
     * The test asserts that the response status is UNPROCESSABLE_ENTITY (422) and that the response body is empty.
     * This is because the saving of the Actuator in the repository is a required operation for creating an actuator,
     * and if it fails, it should result in an unprocessable entity response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalled_ifSavingFails_thenReturnUnprocessableEntityCode()
            throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);

        String actuatorName = "Blind Roller";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "RollerBlindActuator";
        String lowerLimit = "30.0";
        String upperLimit = "10.0";
        String precision = "0.1";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(actuatorRepository.save(any(Actuator.class))).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed for a
     * DecimalValueActuator. The test creates a valid ActuatorDTO and a Device object, and the repositories are stubbed
     * to return true for the existence of the actuator type and the device, and for the saving of the Actuator.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO as the
     * request body.
     * The test asserts that the response status is CREATED (201) and that the response body contains the correct
     * actuator details.
     * This is because the ActuatorDTO is valid and the actuator type and device exist in their respective repositories,
     * and the saving of the Actuator in the repository is successful, so it should result in a created response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalledForDecimalValueActuator_thenReturnCreatedCode() throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);

        String actuatorName = "Actuator";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "DecimalValueActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = "0.1";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .precision(precision)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(actuatorRepository.save(any(Actuator.class))).thenReturn(true);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$.lowerLimit").value(lowerLimit))
                .andExpect(jsonPath("$.upperLimit").value(upperLimit))
                .andExpect(jsonPath("$.precision").value(precision))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed for an
     * IntegerValueActuator. The test creates a valid ActuatorDTO and a Device object, and the repositories are stubbed
     * to return true for the existence of the actuator type and the device, and for the saving of the Actuator.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO as the
     * request body.
     * The test asserts that the response status is CREATED (201) and that the response body contains the correct
     * actuator details.
     * This is because the ActuatorDTO is valid and the actuator type and device exist in their respective repositories,
     * and the saving of the Actuator in the repository is successful, so it should result in a created response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalledForIntegerValueActuator_thenReturnCreatedCode() throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);

        String actuatorName = "Actuator";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "IntegerValueActuator";
        String lowerLimit = "10";
        String upperLimit = "30";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .lowerLimit(lowerLimit)
                .upperLimit(upperLimit)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(actuatorRepository.save(any(Actuator.class))).thenReturn(true);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$.lowerLimit").value(lowerLimit))
                .andExpect(jsonPath("$.upperLimit").value(upperLimit))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed for a
     * RollerBlindActuator. The test creates a valid ActuatorDTO and a Device object, and the repositories are stubbed
     * to return true for the existence of the actuator type and the device, and for the saving of the Actuator.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO as the
     * request body.
     * The test asserts that the response status is CREATED (201) and that the response body contains the correct
     * actuator details.
     * This is because the ActuatorDTO is valid and the actuator type and device exist in their respective repositories,
     * and the saving of the Actuator in the repository is successful, so it should result in a created response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalledForRollerBlindActuator_thenReturnCreatedCode() throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);

        String actuatorName = "Actuator";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "RollerBlindActuator";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(actuatorRepository.save(any(Actuator.class))).thenReturn(true);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the addActuator endpoint when a valid ActuatorDTO is passed for a
     * SwitchActuator. The test creates a valid ActuatorDTO and a Device object, and the repositories are stubbed to
     * return true for the existence of the actuator type and the device, and for the saving of the Actuator.
     * It then performs a POST request to the "/actuators" endpoint with the JSON string of the ActuatorDTO as the
     * request body.
     * The test asserts that the response status is CREATED (201) and that the response body contains the correct
     * actuator details.
     * This is because the ActuatorDTO is valid and the actuator type and device exist in their respective repositories,
     * and the saving of the Actuator in the repository is successful, so it should result in a created response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenValidActuatorDTO_whenAddActuatorCalledForSwitchActuator_thenReturnCreatedCode() throws Exception {
        //Arrange
        String deviceName = "Device Name";
        String deviceModel = "Device Model";
        String deviceRoomID = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        DeviceNameVO deviceNameVO = new DeviceNameVO(deviceName);
        DeviceModelVO deviceModelVO = new DeviceModelVO(deviceModel);
        RoomIDVO deviceRoomIDVO = new RoomIDVO(UUID.fromString(deviceRoomID));

        Device device = new Device(deviceNameVO, deviceModelVO, deviceRoomIDVO);

        String actuatorName = "Actuator";
        String deviceId = device.getId().getID();
        String actuatorTypeID = "SwitchActuator";

        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));

        ActuatorDTO actuatorDTO = ActuatorDTO.builder()
                .actuatorName(actuatorName)
                .deviceID(deviceId)
                .actuatorType(actuatorTypeID)
                .build();

        String actuatorJson = objectMapper.writeValueAsString(actuatorDTO);

        when(actuatorTypeRepository.isPresent(actuatorTypeIDVO)).thenReturn(true);
        when(deviceRepository.findById(deviceIDVO)).thenReturn(device);
        when(deviceRepository.isPresent(deviceIDVO)).thenReturn(true);
        when(actuatorRepository.save(any(Actuator.class))).thenReturn(true);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/actuators")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(actuatorJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").exists())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the getActuatorById endpoint when a null ActuatorID is passed.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the null ActuatorID as the path
     * variable.
     * The test asserts that the response status is BAD_REQUEST (400) and that the response body is empty.
     * This is because the ActuatorID is a required parameter for retrieving an actuator, and if it is null, it should
     * result in a bad request.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenInvalidActuatorId_whenGetActuatorByIdCalled_thenReturnsBadRequest() throws Exception {
        //Arrange
        ActuatorIDVO actuatorId = null;

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the getActuatorById endpoint when an ActuatorID is passed that does not exist
     * in the repository.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the non-existent ActuatorID as the
     * path variable.
     * The test asserts that the response status is NOT_FOUND (404) and that the response body is empty.
     * This is because the ActuatorID is a required parameter for retrieving an actuator, and if it does not exist in
     * the repository, it should result in a not found response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenNonExistentActuatorId_whenGetActuatorByIdCalled_theReturnNotFound() throws Exception {
        //Arrange
        String actuatorId = "3fa85f64-5717-4562-b3fc-2c963f66afa6";

        ActuatorIDVO actuatorIDVO = new ActuatorIDVO(UUID.fromString(actuatorId));

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(false);

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").doesNotExist())
                .andReturn();
    }

    /**
     * This test verifies the behavior of the getActuatorById endpoint when a valid ActuatorID is passed and the
     * actuator is of type DecimalValueActuator.
     * The test creates a DecimalValueActuator and the repositories are stubbed to return true for the existence of the
     * actuator and to return the created actuator.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the ActuatorID as the path
     * variable.
     * The test asserts that the response status is OK (200) and that the response body contains the correct actuator
     * details. This is because the ActuatorID is valid and the actuator exists in the repository, so it should result
     * in an OK response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenActuatorId_whenGetActuatorById_ifDecimalValueActuator_thenReturnActuatorDTO() throws Exception {
        //Arrange
        String actuatorId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorName = "Actuator";
        String deviceId = "fa85f642-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "DecimalValueActuator";
        String lowerLimit = "10.0";
        String upperLimit = "30.0";
        String precision = "0.1";

        ActuatorIDVO actuatorIDVO = new ActuatorIDVO(UUID.fromString(actuatorId));
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO(actuatorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        DecimalSettingsVO decimalSettingsVO = new DecimalSettingsVO(lowerLimit,upperLimit,precision);

        Actuator actuator = new DecimalValueActuator(actuatorIDVO,actuatorNameVO,actuatorTypeIDVO,deviceIDVO,decimalSettingsVO);

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(true);
        when(actuatorRepository.findById(actuatorIDVO)).thenReturn(actuator);

        Link expectedLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(actuatorId)).withSelfRel();

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actuatorId").value(actuatorId))
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$.lowerLimit").value(lowerLimit))
                .andExpect(jsonPath("$.upperLimit").value(upperLimit))
                .andExpect(jsonPath("$.precision").value(precision))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").value(expectedLink.getHref()))
                .andReturn();
    }

    /**
     * This test verifies the behavior of the getActuatorById endpoint when a valid ActuatorID is passed and the
     * actuator is of type IntegerValueActuator.
     * The test creates an IntegerValueActuator and the repositories are stubbed to return true for the existence of
     * the actuator and to return the created actuator.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the ActuatorID as the
     * path variable.
     * The test asserts that the response status is OK (200) and that the response body contains the correct actuator
     * details. This is because the ActuatorID is valid and the actuator exists in the repository, so it should result
     * in an OK response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenActuatorId_whenGetActuatorById_ifIntegerValueActuator_thenReturnActuatorDTO() throws Exception {
        //Arrange
        String actuatorId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorName = "Actuator";
        String deviceId = "fa85f642-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "IntegerValueActuator";
        String lowerLimit = "10";
        String upperLimit = "30";

        ActuatorIDVO actuatorIDVO = new ActuatorIDVO(UUID.fromString(actuatorId));
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO(actuatorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);
        IntegerSettingsVO integerSettingsVO = new IntegerSettingsVO(lowerLimit,upperLimit);

        Actuator actuator = new IntegerValueActuator(actuatorIDVO,actuatorNameVO,actuatorTypeIDVO,deviceIDVO,integerSettingsVO);

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(true);
        when(actuatorRepository.findById(actuatorIDVO)).thenReturn(actuator);

        Link expectedLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(actuatorId)).withSelfRel();

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actuatorId").value(actuatorId))
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$.lowerLimit").value(lowerLimit))
                .andExpect(jsonPath("$.upperLimit").value(upperLimit))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").value(expectedLink.getHref()))
                .andReturn();
    }

    /**
     * This test verifies the behavior of the getActuatorById endpoint when a valid ActuatorID is passed and the
     * actuator is of type SwitchActuator.
     * The test creates a SwitchActuator and the repositories are stubbed to return true for the existence of the
     * actuator and to return the created actuator.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the ActuatorID as the path
     * variable.
     * The test asserts that the response status is OK (200) and that the response body contains the correct actuator
     * details. This is because the ActuatorID is valid and the actuator exists in the repository, so it should result
     * in an OK response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenActuatorId_whenGetActuatorById_ifSwitchActuator_thenReturnActuatorDTO() throws Exception {
        //Arrange
        String actuatorId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorName = "Actuator";
        String deviceId = "fa85f642-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "SwitchActuator";

        ActuatorIDVO actuatorIDVO = new ActuatorIDVO(UUID.fromString(actuatorId));
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO(actuatorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);


        Actuator actuator = new SwitchActuator(actuatorIDVO,actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(true);
        when(actuatorRepository.findById(actuatorIDVO)).thenReturn(actuator);

        Link expectedLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(actuatorId)).withSelfRel();

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actuatorId").value(actuatorId))
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").value(expectedLink.getHref()))
                .andReturn();
    }


    /**
     * This test verifies the behavior of the getActuatorById endpoint when a valid ActuatorID is passed and the
     * actuator is of type RollerBlindActuator.
     * The test creates a SwitchActuator (which seems to be a mistake, it should be a RollerBlindActuator) and the
     * repositories are stubbed to return true for the existence of the actuator and to return the created actuator.
     * It then performs a GET request to the "/actuators/{actuatorId}" endpoint with the ActuatorID as the
     * path variable.
     * The test asserts that the response status is OK (200) and that the response body contains the correct actuator
     * details. This is because the ActuatorID is valid and the actuator exists in the repository, so it should result
     * in an OK response.
     *
     * @throws Exception if any exception occurs during the execution of the request or the assertions
     */
    @Test
    void givenActuatorId_whenGetActuatorById_ifRollerBlindActuator_thenReturnActuatorDTO() throws Exception {
        //Arrange
        String actuatorId = "f642fa85-4562-b3fc-5717-6afa62c963f6";
        String actuatorName = "Actuator";
        String deviceId = "fa85f642-4562-b3fc-5717-6afa62c963f6";
        String actuatorTypeID = "RollerBlindActuator";

        ActuatorIDVO actuatorIDVO = new ActuatorIDVO(UUID.fromString(actuatorId));
        ActuatorNameVO actuatorNameVO = new ActuatorNameVO(actuatorName);
        DeviceIDVO deviceIDVO = new DeviceIDVO(UUID.fromString(deviceId));
        ActuatorTypeIDVO actuatorTypeIDVO = new ActuatorTypeIDVO(actuatorTypeID);


        Actuator actuator = new SwitchActuator(actuatorIDVO,actuatorNameVO,actuatorTypeIDVO,deviceIDVO);

        when(actuatorRepository.isPresent(actuatorIDVO)).thenReturn(true);
        when(actuatorRepository.findById(actuatorIDVO)).thenReturn(actuator);

        Link expectedLink = linkTo(methodOn(ActuatorCTRLWeb.class).getActuatorById(actuatorId)).withSelfRel();

        //Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/actuators/" + actuatorId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.actuatorId").value(actuatorId))
                .andExpect(jsonPath("$.actuatorName").value(actuatorName))
                .andExpect(jsonPath("$.actuatorType").value(actuatorTypeID))
                .andExpect(jsonPath("$.deviceID").value(deviceId))
                .andExpect(jsonPath("$._links.self").exists())
                .andExpect(jsonPath("$._links.self.href").value(expectedLink.getHref()))
                .andReturn();
    }
}
