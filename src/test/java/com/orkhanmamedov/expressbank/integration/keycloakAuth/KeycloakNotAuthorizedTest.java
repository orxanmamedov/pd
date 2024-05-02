package com.orkhanmamedov.expressbank.integration.keycloakAuth;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.controller.DepositController;
import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class KeycloakNotAuthorizedTest extends AbstractIntegrationTest {
  private AddDepositRequestDto request;

  @BeforeEach
  public void setup() {
    request = MockData.getValidAddDepositRequestDto();
  }

  @Test
  void whenAddDeposit_thenSuccess() throws Exception {

    // then
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(
                    DepositController.DEPOSITS_URL + DepositController.ADD_DEPOSITS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().isUnauthorized());
  }
}
