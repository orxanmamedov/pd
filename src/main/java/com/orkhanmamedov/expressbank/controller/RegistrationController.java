package com.orkhanmamedov.expressbank.controller;

import com.orkhanmamedov.expressbank.dto.common.response.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.user.request.UserRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

public interface RegistrationController {

  String API_V1_URI = "/api/v1";
  String REGISTRATIONS_URI = "/registrations";

  String REGISTRATIONS_URL = API_V1_URI + REGISTRATIONS_URI;

  @Operation(summary = "Register user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Account for user with username: 'new_Created_User' created",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MessageResponseDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid email format ",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MessageResponseDto.class))),
        @ApiResponse(
            responseCode = "409",
            description = "User already exists",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MessageResponseDto.class)))
      })
  MessageResponseDto registerUser(UserRequestDto dto);
}
