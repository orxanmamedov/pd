package com.orkhanmamedov.expressbank.dto.auth.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

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
            regexp =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@!$?*()\\[\\]{}'\";:\\\\/<>,._\\-])"
                    + "[a-zA-Z0-9@!$?*()\\[\\]{}'\";:\\\\/<>,._\\-]{8,50}$",
            message = "Invalid characters in password")
        @Schema(example = "Password1_")
        String password) {}
