package com.orkhanmamedov.expressbank.controller.impl;

import com.orkhanmamedov.expressbank.controller.LoginController;
import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;
import com.orkhanmamedov.expressbank.service.AuthService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(LoginControllerImpl.API_V1_URI)
@RequiredArgsConstructor
public class LoginControllerImpl implements LoginController {

  private final AuthService authService;

  @Override
  @PostMapping(LOGIN_URI)
  public LoginResponseDto login(@RequestBody @Valid LoginRequestDto dto) {
    log.info("{login} -> Login request from user with email {}.", dto.email());

    return authService.login(dto);
  }
}
