package com.orkhanmamedov.expressbank.config;

import com.orkhanmamedov.expressbank.exception.SecurityException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.adapters.springsecurity.authentication.KeycloakCookieBasedRedirect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Slf4j
@Configuration
public class KeycloakAuthenticationFailureHandler
    implements AuthenticationFailureHandler, AuthenticationEntryPoint {

  public static final String ERROR = "error: ";
  public static final String USER_NOT_AUTHORIZED = "User is not authorized";

  @Override
  public void onAuthenticationFailure(
      HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
      throws IOException {
    if (!response.isCommitted()) {
      log.warn("{onAuthenticationFailure} -> {}", exception.getMessage());

      if (KeycloakCookieBasedRedirect.getRedirectUrlFromCookie(request) != null) {
        response.addCookie(KeycloakCookieBasedRedirect.createCookieFromRedirectUrl(null));
      }
      response.setContentType(MediaType.APPLICATION_JSON_VALUE);
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.getOutputStream().println("{ " + ERROR + USER_NOT_AUTHORIZED + " }");
    } else if (200 <= response.getStatus() && response.getStatus() < 300) {
      throw new SecurityException(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
  }

  @Override
  public void commence(
      HttpServletRequest request,
      HttpServletResponse response,
      AuthenticationException authException)
      throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.getOutputStream().println("{ " + ERROR + USER_NOT_AUTHORIZED + " }");
  }
}
