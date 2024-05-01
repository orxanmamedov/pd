package com.orkhanmamedov.expressbank.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.keycloak.OAuth2Constants;
import org.keycloak.adapters.KeycloakConfigResolver;
import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
@RequiredArgsConstructor
@ConditionalOnProperty(
    name = "security.config.use-keycloak",
    havingValue = "true",
    matchIfMissing = true)
public class KeycloakProvider {

  private static Keycloak keycloak = null;

  @Value("${keycloak.auth-server-url}")
  public String serverURL;

  @Value("${keycloak.realm}")
  public String realm;

  @Value("${keycloak.resource}")
  public String clientID;

  @Value("${keycloak.credentials.secret}")
  public String clientSecret;

  public Keycloak getInstance() {
    if (keycloak == null) {
      return KeycloakBuilder.builder()
          .realm(realm)
          .serverUrl(serverURL)
          .clientId(clientID)
          .clientSecret(clientSecret)
          .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
          .build();
    }
    return keycloak;
  }

  public Keycloak newKeycloakBuilderWithPasswordCredentials(String email, String password) {
    return KeycloakBuilder.builder()
        .realm(realm)
        .serverUrl(serverURL)
        .clientId(clientID)
        .clientSecret(clientSecret)
        .username(email)
        .password(password)
        .build();
  }

  @Bean
  public KeycloakConfigResolver keycloakConfigResolver() {
    return new KeycloakSpringBootConfigResolver();
  }
}
