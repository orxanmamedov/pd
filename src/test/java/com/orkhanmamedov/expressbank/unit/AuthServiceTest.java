package com.orkhanmamedov.expressbank.unit;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.config.KeycloakProvider;
import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.exception.SecurityException;
import com.orkhanmamedov.expressbank.service.impl.AuthServiceImpl;
import javax.ws.rs.NotAuthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {
  private LoginRequestDto request;

  @Mock private KeycloakProvider keycloakProvider;
  @InjectMocks private AuthServiceImpl authService;

  @BeforeEach
  public void setup() {
    request = MockData.getValidLoginRequestDto();
  }

  @Test
  void whenAuthorization_thenDenied() {
    // when
    when(keycloakProvider.newKeycloakBuilderWithPasswordCredentials(
            request.email(), request.password()))
        .thenThrow(AuthorizationDeniedException.class);

    // then
    assertThrows(SecurityException.class, () -> authService.login(request));
    verify(keycloakProvider, times(1))
        .newKeycloakBuilderWithPasswordCredentials(request.email(), request.password());
  }

  @Test
  void whenAuthorization_thenThrowHttpResponseException() {
    // when
    when(keycloakProvider.newKeycloakBuilderWithPasswordCredentials(
            request.email(), request.password()))
        .thenThrow(HttpResponseException.class);

    // then
    assertThrows(SecurityException.class, () -> authService.login(request));
    verify(keycloakProvider, times(1))
        .newKeycloakBuilderWithPasswordCredentials(request.email(), request.password());
  }

  @Test
  void whenAuthorization_thenThrowNotAuthorizedException() {
    // when
    when(keycloakProvider.newKeycloakBuilderWithPasswordCredentials(
            request.email(), request.password()))
        .thenThrow(NotAuthorizedException.class);

    // then
    assertThrows(SecurityException.class, () -> authService.login(request));
    verify(keycloakProvider, times(1))
        .newKeycloakBuilderWithPasswordCredentials(request.email(), request.password());
  }
}
