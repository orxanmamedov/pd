package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;

public interface RegistrationService {
  void registerUser(final UserRegistrationRequestDto dto);
}
