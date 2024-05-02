package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.config.KeycloakProvider;
import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;
import com.orkhanmamedov.expressbank.exception.SecurityException;
import com.orkhanmamedov.expressbank.service.AuthService;
import javax.ws.rs.NotAuthorizedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.keycloak.representations.AccessTokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

  private final KeycloakProvider keycloakProvider;

  @Override
  public LoginResponseDto login(LoginRequestDto dto) {
    log.info("{login} -> start login for user {}", dto.email());
    AccessTokenResponse accessToken = getAccessToken(dto);
    log.info("{login} -> end login for user {} successfully", dto.email());

    return LoginResponseDto.builder()
        .tokenType(accessToken.getTokenType())
        .token(accessToken.getToken())
        .refreshToken(accessToken.getRefreshToken())
        .build();
  }

  private AccessTokenResponse getAccessToken(LoginRequestDto dto) {
    try (Keycloak keycloak =
        keycloakProvider.newKeycloakBuilderWithPasswordCredentials(dto.email(), dto.password())) {
      return keycloak.tokenManager().getAccessToken();
    } catch (NotAuthorizedException | AuthorizationDeniedException | HttpResponseException ex) {
      log.debug("Exception when login {}", dto.email(), ex);
      log.info("end login for user {} goes wrong", dto.password());

      throw new SecurityException(HttpStatus.BAD_REQUEST, SecurityException.LOGIN_ERROR);
    }
  }
}
