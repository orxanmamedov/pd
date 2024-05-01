package com.orkhanmamedov.expressbank.controller;

import com.orkhanmamedov.expressbank.deposit.request.AddDepositRequestDto;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;

public interface DepositController {

  String API_V1_URI = "/api/v1";
  String DEPOSITS_URI = "/deposits";
  String ADD_DEPOSITS_URI = "/add";
  String DEPOSITS_URL = API_V1_URI + DEPOSITS_URI;

  @Operation(summary = "Add deposit")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Information provided",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array =
                        @ArraySchema(schema = @Schema(implementation = MessageResponseDto.class)))),
        @ApiResponse(
            responseCode = "401",
            description = "User is not authorized",
            content =
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MessageResponseDto.class)))
      })
  MessageResponseDto addDeposit(@RequestBody AddDepositRequestDto dto);
}
