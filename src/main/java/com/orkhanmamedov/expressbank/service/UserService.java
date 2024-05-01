package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;

public interface UserService {

  void saveUser(UserRequestDto userDto);
}
