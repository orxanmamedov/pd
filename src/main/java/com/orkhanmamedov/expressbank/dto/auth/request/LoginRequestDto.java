package com.orkhanmamedov.expressbank.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;

@Builder
public record LoginRequestDto(
    @NotBlank(message = "Email cannot be blank or null")
        @Pattern(
            regexp =
                "^(?!.*[._-]{2})[a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?@[a-zA-Z0-9](?:[a-zA-Z0-9.-]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format")
        @Schema(example = "username@domain.com")
        String email,
    @NotBlank(message = "Password cannot be blank or null")
        @Pattern(
            regexp = "^[a-zA-Z0-9]{6,}$",
            message =
                "Password must be at least 6 characters long and contain only alphanumeric characters")
        @Schema(example = "Password1")
        @NotBlank
        String password) {}
