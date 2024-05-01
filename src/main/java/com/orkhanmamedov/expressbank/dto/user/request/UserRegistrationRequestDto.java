package com.orkhanmamedov.expressbank.dto.user.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Builder;

@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public record UserRegistrationRequestDto(
    @Schema(hidden = true) String id,
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
            regexp = "^[a-zA-Z0-9]{6,}$",
            message =
                "Password must be at least 6 characters long and contain only alphanumeric characters")
        @Schema(example = "Password1")
        @NotBlank
        String password) {}
