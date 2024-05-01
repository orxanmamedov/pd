package com.orkhanmamedov.expressbank.dto.common.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record MessageResponseDto(@Schema(example = "response message") String message) {
  public static final String DEPOSIT_ADDED = "Your deposit has been successfully added";
}
