package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;

public interface RegistrationService {
  void registerUser(final UserRequestDto dto);
}
