package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.config.KeycloakProvider;
import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;
import com.orkhanmamedov.expressbank.exception.BusinessException;
import com.orkhanmamedov.expressbank.service.RegistrationService;
import com.orkhanmamedov.expressbank.service.UserService;
import java.util.Collections;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RegistrationServiceImpl implements RegistrationService {

  private static final String ROLE_NAME = "USER";
  private final KeycloakProvider kcProvider;
  private final UserService userService;

  @Value("${keycloak.realm}")
  public String realm;

  public RegistrationServiceImpl(KeycloakProvider kcProvider, UserService userService) {
    this.kcProvider = kcProvider;
    this.userService = userService;
  }

  public static CredentialRepresentation createPasswordCredentials(String password) {
    CredentialRepresentation passwordCredentials = new CredentialRepresentation();
    passwordCredentials.setTemporary(false);
    passwordCredentials.setType(CredentialRepresentation.PASSWORD);
    passwordCredentials.setValue(password);
    return passwordCredentials;
  }

  @Override
  public void registerUser(final UserRegistrationRequestDto dto) {

    log.info("{RegistrationService -> registerUser} -> Request to create user in Keycloak");

    try {
      UsersResource usersResource = kcProvider.getInstance().realm(realm).users();
      UserRepresentation kcUser = new UserRepresentation();
      kcUser.setUsername(dto.email());
      kcUser.setEmail(dto.email());
      kcUser.setEnabled(true);
      kcUser.setEmailVerified(true);

      CredentialRepresentation credentialRepresentation = createPasswordCredentials(dto.password());
      kcUser.setCredentials(Collections.singletonList(credentialRepresentation));
      log.info("{registerUser} -> Credential representation is completed");

      Response response = usersResource.create(kcUser);
      String userId = CreatedResponseUtil.getCreatedId(response);

      UserResource userResource = usersResource.get(userId);
      userResource.resetPassword(credentialRepresentation);

      log.info("{registerUser} -> User created in keycloak server #{}", userId);

      assignUserRole(userId);
      log.info("{registerUser} -> User was assigned to role USER");

      UserRegistrationRequestDto userDto =
          UserRegistrationRequestDto.builder()
              .id(userId)
              .name(dto.name())
              .email(dto.email())
              .build();
      userService.saveUser(userDto);
      log.info("User data was saved");
    } catch (WebApplicationException e) {
      log.info(e.getMessage());
      throw new BusinessException(HttpStatus.CONFLICT, BusinessException.USER_ALREADY_EXISTS);
    }
  }

  private void assignUserRole(final String userId) {
    RealmResource realmResource = kcProvider.getInstance().realm(realm);
    RolesResource rolesResource = realmResource.roles();
    RoleRepresentation userRole = rolesResource.get(ROLE_NAME).toRepresentation();
    realmResource.users().get(userId).roles().realmLevel().add(Collections.singletonList(userRole));
  }
}
