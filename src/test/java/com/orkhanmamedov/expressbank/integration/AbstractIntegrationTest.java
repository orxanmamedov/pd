package com.orkhanmamedov.expressbank.integration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkhanmamedov.expressbank.service.impl.AuthServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.DepositServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.RegistrationServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
    scripts = "file:src/test/resources/data/clean-up-data.sql",
    executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class AbstractIntegrationTest {

  @Autowired protected MockMvc mockMvc;
  @Autowired protected ObjectMapper objectMapper;
  @MockBean protected AuthServiceImpl authService;
  @MockBean protected DepositServiceImpl depositService;
  @MockBean protected RegistrationServiceImpl registrationService;
  @MockBean protected StockServiceImpl stockService;

  static PostgreSQLContainer postgresqlContainer =
      (PostgreSQLContainer)
          new PostgreSQLContainer("postgres:14-alpine")
              .withDatabaseName("transaction_service")
              .withUsername("postgres")
              .withPassword("postgres")
              .withReuse(true);

  static {
    postgresqlContainer.start();
  }

  protected <T> String toJson(final T object) throws JsonProcessingException {
    return objectMapper.writeValueAsString(object);
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.datasource.url", postgresqlContainer::getJdbcUrl);
    registry.add("spring.datasource.username", postgresqlContainer::getUsername);
    registry.add("spring.datasource.password", postgresqlContainer::getPassword);
  }
}
