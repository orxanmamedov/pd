package com.orkhanmamedov.expressbank.controller.impl;

import com.orkhanmamedov.expressbank.controller.RegistrationController;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;
import com.orkhanmamedov.expressbank.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(RegistrationControllerImpl.REGISTRATIONS_URL)
public class RegistrationControllerImpl implements RegistrationController {

  private final RegistrationService registrationService;

  @Override
  @PostMapping
  public MessageResponseDto registerUser(final @RequestBody @Valid UserRegistrationRequestDto dto) {
    log.info(
        "{RegistrationController -> registerUser} -> "
            + "User registration request to auth-service from user with email {}.",
        dto.email());

    registrationService.registerUser(dto);
    return new MessageResponseDto(String.format("User with email: %s added", dto.email()));
  }
}
