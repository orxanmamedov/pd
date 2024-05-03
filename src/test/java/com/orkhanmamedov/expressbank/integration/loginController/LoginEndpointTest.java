package com.orkhanmamedov.expressbank.integration.loginController;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.controller.LoginController;
import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;
import com.orkhanmamedov.expressbank.exception.SecurityException;
import com.orkhanmamedov.expressbank.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class LoginEndpointTest extends AbstractIntegrationTest {

  private LoginRequestDto request;
  private LoginResponseDto response;

  @BeforeEach
  public void setup() {
    request = MockData.getValidLoginRequestDto();
    response = MockData.getLoginResponseMessageDto();
  }

  @Test
  void whenUserLogins_thenSuccess() throws Exception {
    // when
    when(authService.login(any(LoginRequestDto.class))).thenReturn(response);

    // then
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(LoginController.LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.tokenType", is(response.tokenType())))
        .andExpect(jsonPath("$.token", is(response.token())))
        .andExpect(jsonPath("$.refreshToken", is(response.refreshToken())));

    verify(authService, times(1)).login(request);
  }

  @Test
  void whenUserLoginsWithWrongUserNameOrPassword_thenThrowSecurityException() throws Exception {
    // when
    when(authService.login(request))
        .thenThrow(new SecurityException(HttpStatus.BAD_REQUEST, SecurityException.LOGIN_ERROR));

    // then
    mockMvc
        .perform(
            post(LoginController.LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
        .andExpect(jsonPath("$.message", is(SecurityException.LOGIN_ERROR)))
        .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION));

    verify(authService, times(1)).login(request);
  }

  @Test
  void whenUserLoginsWithNotVerifiedEmail_thenThrowSecurityException() throws Exception {
    // when
    when(authService.login(request))
        .thenThrow(
            new SecurityException(
                HttpStatus.FORBIDDEN, SecurityException.EMAIL_NOT_VERIFIED_ERROR));

    // then
    mockMvc
        .perform(
            post(LoginController.LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().is4xxClientError())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(HttpStatus.FORBIDDEN.value()))
        .andExpect(jsonPath("$.message", is(SecurityException.EMAIL_NOT_VERIFIED_ERROR)))
        .andExpect(header().doesNotExist(HttpHeaders.AUTHORIZATION));

    verify(authService, times(1)).login(request);
  }
}
