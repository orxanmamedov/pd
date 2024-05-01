package com.orkhanmamedov.expressbank.service;

import com.orkhanmamedov.expressbank.dto.deposit.request.AddDepositRequestDto;

public interface DepositService {

  void addDeposit(final String userId, final AddDepositRequestDto dto);
}
