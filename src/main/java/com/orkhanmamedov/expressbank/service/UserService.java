package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.user.request.UserRegistrationRequestDto;

public interface UserService {

  void saveUser(UserRegistrationRequestDto userDto);
}
