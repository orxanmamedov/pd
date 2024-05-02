package com.orkhanmamedov.expressbank.integration.registrationController;

import static com.orkhanmamedov.expressbank.MockData.getValidUserRequestDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.orkhanmamedov.expressbank.controller.RegistrationController;
import com.orkhanmamedov.expressbank.dto.common.response.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import com.orkhanmamedov.expressbank.integration.AbstractIntegrationTest;
import java.util.stream.Stream;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class RegistrationEndpointTest extends AbstractIntegrationTest {
  @ParameterizedTest
  @MethodSource("registrationTestData")
  void whenUserRegister_thenReturnMessage(
      HttpStatus status, UserRequestDto request, MessageResponseDto expected) throws Exception {

    // when
    setUpKc();
    // then
    mockMvc
        .perform(
            post(RegistrationController.REGISTRATIONS_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().is(status.value()))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message").value(expected.message()));
  }

  private Stream<Arguments> registrationTestData() {
    return Stream.of(
        Arguments.of(
            HttpStatus.OK,
            getValidUserRequestDto(),
            new MessageResponseDto(
                String.format(
                    "User with email: %s added, check your email for verification link",
                    getValidUserRequestDto().email()))),
        Arguments.of(
            HttpStatus.BAD_REQUEST,
            getValidUserRequestDto().toBuilder().email("invalid_email").build(),
            new MessageResponseDto("Invalid email format")));
  }
}
