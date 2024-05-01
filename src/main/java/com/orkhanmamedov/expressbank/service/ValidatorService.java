package com.orkhanmamedov.expressbank.service;

public interface ValidatorService {
  boolean isConfirmationCodeValidToRegister(final String confirmationCode);
}
