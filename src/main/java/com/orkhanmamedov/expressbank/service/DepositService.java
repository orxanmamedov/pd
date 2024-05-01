package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;

public interface DepositService {

  MessageResponseDto addDeposit(final String userId, final AddDepositRequestDto dto);
}
