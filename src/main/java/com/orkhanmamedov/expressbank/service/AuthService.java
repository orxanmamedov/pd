package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;

public interface AuthService {
  LoginResponseDto login(LoginRequestDto dto);
}
