package com.orkhanmamedov.expressbank.controller;

import com.orkhanmamedov.expressbank.dto.auth.response.LoginResponseMessageDto;
import com.orkhanmamedov.expressbank.dto.common.MessageResponseDto;
import com.orkhanmamedov.expressbank.dto.stock.response.StockResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.MediaType;

public interface StockController {
  String API_V1_URI = "/api/v1";
  String STOCKS_URI = "/stocks";

  String STOCKS_URL = API_V1_URI + STOCKS_URI;

  @Operation(summary = "Get list off available stock symbols")
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
  List<StockResponseDto> getStocks();
}
