package com.orkhanmamedov.expressbank.dto.user.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRegistrationRequestDto(
    String id,
    @NotBlank(message = "Name cannot be blank or null")
        @Pattern(
            regexp = "^(?![- ])(?!.*[- ]$)[a-zA-Z\\s-]{2,30}$",
            message = "Invalid characters in first name")
        @Schema(example = "Orkhan")
        @NotBlank
        String name,
    @NotBlank(message = "Email cannot be blank or null")
        @Pattern(
            regexp =
                "^(?!.*[._-]{2})[a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?@[a-zA-Z0-9](?:[a-zA-Z0-9.-]*[a-zA-Z0-9])?\\.[a-zA-Z]{2,6}$",
            message = "Invalid email format")
        @Schema(example = "username@domain.com")
        @NotBlank
        String email,
    @NotBlank(message = "Password cannot be blank or null")
        @Pattern(
            regexp =
                "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@!$?*()\\[\\]{}'\";:\\\\/<>,._\\-])[a-zA-Z0-9@!$?*()\\[\\]{}'\";:\\\\/<>,._\\-]{8,50}$",
            message = "Invalid characters in password")
        @Schema(example = "Password1_")
        @NotBlank
        String password) {}
