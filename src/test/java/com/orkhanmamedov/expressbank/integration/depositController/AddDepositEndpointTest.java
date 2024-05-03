package com.orkhanmamedov.expressbank.integration.depositController;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.orkhanmamedov.expressbank.MockData;
import com.orkhanmamedov.expressbank.controller.DepositController;
import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.integration.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WithMockUser
class AddDepositEndpointTest extends AbstractIntegrationTest {

  private AddDepositRequestDto request;

  @BeforeEach
  public void setup() {
    request = MockData.getValidAddDepositRequestDto();
  }

  @Test
  void whenAddDeposit_thenSuccess() throws Exception {
    // when
    // then
    mockMvc
        .perform(
            MockMvcRequestBuilders.post(
                    DepositController.DEPOSITS_URL + DepositController.ADD_DEPOSITS_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(request)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.message", is("Your deposit has been successfully added")));
  }
}
