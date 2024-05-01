package com.orkhanmamedov.expressbank.dto.user.response;

import lombok.Builder;

@Builder(toBuilder = true)
public record UserRegistrationResponseDto(String id, String email, String name) {}
