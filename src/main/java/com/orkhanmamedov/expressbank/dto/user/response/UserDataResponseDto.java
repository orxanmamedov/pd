package com.orkhanmamedov.expressbank.dto.user.response;

import java.time.ZonedDateTime;
import lombok.Builder;

@Builder(toBuilder = true)
public record UserDataResponseDto(
    String id, String email, String name, ZonedDateTime createAt, ZonedDateTime updateAt) {}
