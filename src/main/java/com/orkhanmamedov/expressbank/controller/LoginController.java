package com.orkhanmamedov.expressbank.controller;

import com.orkhanmamedov.expressbank.dto.auth.request.LoginRequestDto;
import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseMessageDto;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;

public interface LoginController {

  String API_V1_URI = "/api/v1";
  String LOGIN_URI = "/login";
  String LOGIN_URL = API_V1_URI + LOGIN_URI;

  @Operation(summary = "Login user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "The user has logged in successfully",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = LoginResponseMessageDto.class))),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid email or password",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MessageResponseDto.class))),
      })
  LoginResponseMessageDto login(LoginRequestDto dto);
}
