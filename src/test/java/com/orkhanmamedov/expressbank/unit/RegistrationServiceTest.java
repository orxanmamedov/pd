package com.orkhanmamedov.expressbank.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import com.orkhanmamedov.expressbank.service.impl.RegistrationServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RegistrationServiceTest {

  @Test
  void whenCreatePasswordCredentialsMethod_thenSuccess() {
    // given
    final String password = "myPassword";

    // when
    CredentialRepresentation result = RegistrationServiceImpl.createPasswordCredentials(password);

    // then
    assertFalse(result.isTemporary());
    assertEquals(CredentialRepresentation.PASSWORD, result.getType());
    assertEquals(password, result.getValue());
  }
}
