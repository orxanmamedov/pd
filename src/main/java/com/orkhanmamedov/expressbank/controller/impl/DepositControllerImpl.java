package com.orkhanmamedov.expressbank.controller.impl;

import com.orkhanmamedov.expressbank.controller.DepositController;
import com.orkhanmamedov.expressbank.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import com.orkhanmamedov.expressbank.interceptor.AuthenticationHolder;
import com.orkhanmamedov.expressbank.service.DepositService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(DepositController.DEPOSITS_URL)
@RequiredArgsConstructor
public class DepositControllerImpl implements DepositController {
  private final DepositService depositService;
  private final AuthenticationHolder authenticationHolder;

  @Override
  @PostMapping(ADD_DEPOSITS_URI)
  public MessageResponseDto addDeposit(@RequestBody AddDepositRequestDto dto) {
    log.info("{openNewDeposit} -> Request to open new deposit");

    return depositService.addDeposit(authenticationHolder.getUserId(), dto);
  }
}
