package com.orkhanmamedov.expressbank.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orkhanmamedov.expressbank.config.KeycloakProvider;
import com.orkhanmamedov.expressbank.service.impl.AuthServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.DepositServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.RegistrationServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.StockServiceImpl;
import com.orkhanmamedov.expressbank.service.impl.UserServiceImpl;
import javax.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleMappingResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.RoleScopeResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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

  @MockBean protected UserServiceImpl userService;

  @MockBean protected KeycloakProvider kcProvider;

  @MockBean protected DepositServiceImpl depositService;

  @MockBean protected StockServiceImpl stockService;

  @SpyBean protected RegistrationServiceImpl service;

  public void setUpKc() {
    Keycloak keycloak = mock(Keycloak.class);
    RealmResource realmResource = mock(RealmResource.class);
    UsersResource usersResource = mock(UsersResource.class);
    UserResource userResource = mock(UserResource.class);
    RolesResource rolesResource = mock(RolesResource.class);
    RoleResource roleResource = mock(RoleResource.class);
    RoleMappingResource roleMappingResource = mock(RoleMappingResource.class);
    RoleScopeResource roleScopeResource = mock(RoleScopeResource.class);

    when(kcProvider.getInstance()).thenReturn(keycloak);
    when(keycloak.realm(any())).thenReturn(realmResource);
    when(realmResource.users()).thenReturn(usersResource);
    when(realmResource.roles()).thenReturn(rolesResource);
    when(rolesResource.get(any())).thenReturn(roleResource);
    when(usersResource.create(any(UserRepresentation.class)))
        .thenReturn(Response.status(201).build());
    when(usersResource.get(any())).thenReturn(userResource);
    when(userResource.roles()).thenReturn(roleMappingResource);
    when(roleMappingResource.realmLevel()).thenReturn(roleScopeResource);
  }

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
