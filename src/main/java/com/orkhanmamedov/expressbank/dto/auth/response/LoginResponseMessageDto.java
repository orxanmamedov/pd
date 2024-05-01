package com.orkhanmamedov.expressbank.dto.auth.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record LoginResponseMessageDto(String tokenType, String token, String refreshToken) {}
