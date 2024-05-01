package com.orkhanmamedov.expressbank.dto.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record MessageResponseDto(@Schema(example = "response message") String message) {
  public static final String DEPOSIT_CREATED = "Your deposit has been successfully created";
  public static final String PAYMENT_IS_SUCCESSFUL = "Payment has been successful";
  public static final String TRANSFER_IS_SUCCESSFUL = "Transfer has been completed successfully";
}
