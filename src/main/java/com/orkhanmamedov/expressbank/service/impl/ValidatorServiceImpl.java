package com.orkhanmamedov.expressbank.service.impl;

import com.orkhanmamedov.expressbank.service.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ValidatorServiceImpl implements ValidatorService {

  private static final String VALID_CODE = "1234";

  @Override
  public boolean isConfirmationCodeValidToRegister(final String confirmationCode) {
    return confirmationCode.equals(VALID_CODE);
  }
}
