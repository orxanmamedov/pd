package com.orkhanmamedov.expressbank.dto.auth.request;

import lombok.Builder;

@Builder
public record LoginRequestDto(String email, String password) {}
