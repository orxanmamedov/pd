package com.orkhanmamedov.expressbank.dto.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder(toBuilder = true)
public record LoginResponseMessageDto(
    @Schema(example = "Bearer") String tokenType,
    @Schema(example = "eyJhbGciOiJSUzI1NiIsInR5...") String token,
    @Schema(example = "eyJhbGciOiJIUzI1NiIsInR5...") String refreshToken) {}
