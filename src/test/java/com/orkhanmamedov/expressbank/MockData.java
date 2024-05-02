package com.orkhanmamedov.expressbank;

import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseDto;
import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import com.orkhanmamedov.expressbank.entity.RoleEntity;
import com.orkhanmamedov.expressbank.entity.UserEntity;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

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

  public static RoleEntity getValidRoleEntity() {
    return RoleEntity.builder().roleName("USER").id(1L).build();
  }

  public static UserEntity getValidUserEntity() {
    return UserEntity.builder()
        .id("user123")
        .depositBalance(BigDecimal.TEN)
        .email("test@example.com")
        .name("test")
        .roles(Set.of(getValidRoleEntity()))
        .build();
  }

  public static List<StockResponseDto> getValidListStocks() {
    return List.of(
        StockResponseDto.builder()
            .stockSymbol("NIFTY 50")
            .price(22604.85)
            .lastUpdateTime("30-Apr-2024 16:00:00")
            .build(),
        StockResponseDto.builder()
            .stockSymbol("BAJFINANCE")
            .price(6903.05)
            .lastUpdateTime("30-Apr-2024 15:59:35")
            .build(),
        StockResponseDto.builder()
            .stockSymbol("M&M")
            .price(2159.9)
            .lastUpdateTime("30-Apr-2024 15:59:56")
            .build());
  }
}
