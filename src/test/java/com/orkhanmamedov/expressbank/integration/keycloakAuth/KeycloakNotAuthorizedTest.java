package com.orkhanmamedov.expressbank.integration.keycloakAuth;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.orkhanmamedov.expressbank.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class KeycloakNotAuthorizedTest extends AbstractIntegrationTest {

  @Test
  void whenNotAuthorizedAction_thenThrowException() throws Exception {

    // then
    mockMvc.perform(MockMvcRequestBuilders.get("/api/v1")).andExpect(status().isUnauthorized());
  }
}
