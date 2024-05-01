package com.orkhanmamedov.expressbank;

import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;
import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import java.math.BigDecimal;

public class MockData {

  public static LoginResponseDto getLoginResponseMessageDto() {
    return LoginResponseDto.builder()
        .tokenType("Bearer")
        .token("token")
        .refreshToken("refreshToken")
        .build();
  }

  public static LoginRequestDto getValidLoginRequestDto() {
    return LoginRequestDto.builder().email("email@mail.com").password("Password1").build();
  }

  public static AddDepositRequestDto getValidAddDepositRequestDto() {
    return AddDepositRequestDto.builder().depositAmount(BigDecimal.valueOf(1000.25)).build();
  }

  public static UserRequestDto getValidUserRequestDto() {
    return UserRequestDto.builder()
        .name("test")
        .email("test@gmail.com")
        .password("Password1")
        .build();
  }
}
