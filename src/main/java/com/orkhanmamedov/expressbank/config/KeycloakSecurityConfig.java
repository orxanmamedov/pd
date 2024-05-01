package com.orkhanmamedov.expressbank.config;

import com.orkhanmamedov.expressbank.controller.LoginController;
import com.orkhanmamedov.expressbank.controller.RegistrationController;
import org.keycloak.adapters.springsecurity.KeycloakConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@KeycloakConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class KeycloakSecurityConfig {
  private static final String[] SWAGGER_URL = {
    "/api-docs/**", "/swagger-ui/**", "/swagger-ui.html"
  };
  private static final String ACTUATOR_URI = "/actuator/**";

  @Bean
  protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    return http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.NEVER)
        .and()
        .cors()
        .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .mvcMatchers(RegistrationController.REGISTRATIONS_URL)
        .permitAll()
        .mvcMatchers(LoginController.LOGIN_URL)
        .permitAll()
        .mvcMatchers(SWAGGER_URL)
        .permitAll()
        .mvcMatchers(ACTUATOR_URI)
        .permitAll()
        .anyRequest()
        .fullyAuthenticated()
        .and()
        .exceptionHandling()
        .authenticationEntryPoint(new KeycloakAuthenticationFailureHandler())
        .and()
        .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
        .build();
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    config.addAllowedHeader("*");
    config.addAllowedMethod("*");
    config.addAllowedOriginPattern("*");
    config.addExposedHeader(HttpHeaders.AUTHORIZATION);
    source.registerCorsConfiguration("/**", config);
    return new CorsFilter(source);
  }
}
