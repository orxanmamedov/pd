package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseMessageDto;

public interface AuthService {
  LoginResponseMessageDto login(LoginRequestDto dto);
}
