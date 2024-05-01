package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;
import com.orkhanmamedov.expressbank.dto.user.response.UserDataResponseDto;

public interface UserService {

  void saveUser(UserRegistrationRequestDto userDto);

  UserDataResponseDto getUserDataById(final String userId);
}
